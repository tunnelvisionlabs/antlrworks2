/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.antlr.v4.Tool;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.Utilities;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Sam Harwell
 */
public class CodeGenerator {
    @StaticResource
    private static final String ANTLR4_COMPLETE_JAR = "org/antlr/works/editor/grammar/resources/antlr4-complete.jar";

    private static File referenceLibrary;
    private static ClassLoader referenceClassLoader;

    public static final RequestProcessor REFERENCE_RP = new RequestProcessor("ANTLR Reference Tool");

    public final String target;
    public final String targetArgument;
    public final FileObject[] grammarFiles;

    public FileObject outputDirectory;
    public FileObject libDirectory;
    public boolean listener;
    public boolean visitor;
    public boolean atn;
    public String encoding;
    public boolean treatWarningsAsErrors;
    public boolean forceATN;
    public Map<String, String> options;
    public List<String> arguments;

    public CodeGenerator(String target, FileObject... grammarFiles) {
        this.target = target;
        this.grammarFiles = grammarFiles;

        if (target.equals("Python 2")) {
            this.targetArgument = "Python2";
        } else if (target.equals("Python 3")) {
            this.targetArgument = "Python3";
        } else {
            if (!target.contains("Java")) {
                throw new UnsupportedOperationException();
            }

            this.targetArgument = null;
        }
    }

    public static ClassLoader getReferenceClassLoader() {
        if (referenceClassLoader != null) {
            return referenceClassLoader;
        }

        REFERENCE_RP.post(new Runnable() {
            @Override
            public void run() {
                if (referenceClassLoader != null) {
                    return;
                }

                try {
                    referenceLibrary = copyCompleteJarToTempDir();
                    referenceClassLoader = new URLClassLoader(new URL[]{Utilities.toURI(referenceLibrary).toURL()}, ClassLoader.getSystemClassLoader());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }).waitFinished();

        return referenceClassLoader;
    }

    public static File getReferenceLibrary() {
        getReferenceClassLoader();
        return referenceLibrary;
    }

    public Task run() {
        ClassLoader targetLoader;
        if (target.contains("sharwell/optimized")) {
            targetLoader = Thread.currentThread().getContextClassLoader();
        } else {
            targetLoader = getReferenceClassLoader();
        }

        if (targetLoader == null) {
            return RequestProcessor.Task.EMPTY;
        }

        final ClassLoader loader = targetLoader;

        return REFERENCE_RP.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Class<?> toolClass = loader.loadClass(Tool.class.getName());
                    Constructor<?> ctor = toolClass.getConstructor(String[].class);
                    Method processGrammarsOnCommandLine = toolClass.getMethod("processGrammarsOnCommandLine");

                    List<String> args = getCommandArguments();
                    for (FileObject grammarFile : grammarFiles) {
                        args.add(FileUtil.toFile(grammarFile).getAbsolutePath());
                    }

                    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                    Thread.currentThread().setContextClassLoader(loader);
                    try {
                        InputOutput inputOutput = IOProvider.getDefault().getIO(String.format("ANTLR Codegen (%s)", target), false);
                        inputOutput.select();
                        PrintStream originalOut = System.out;
                        try (OutputWriter outputWriter = inputOutput.getOut()) {
                            System.setOut(new PrintStream(new OutputWriterStream(outputWriter)));
                            try {
                                PrintStream originalErr = System.err;
                                try (OutputWriter errorWriter = inputOutput.getErr()) {
                                    System.setErr(new PrintStream(new OutputWriterStream(errorWriter)));
                                    try {
                                        outputWriter.format("Arguments: %s%n", args);
                                        Object tool = ctor.newInstance((Object)args.toArray(new String[args.size()]));
                                        processGrammarsOnCommandLine.invoke(tool);
                                    } finally {
                                        System.setErr(originalErr);
                                    }
                                }
                            } finally {
                                System.setOut(originalOut);
                            }
                        }
                    } finally {
                        Thread.currentThread().setContextClassLoader(contextClassLoader);
                    }
                } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    private static File copyCompleteJarToTempDir() throws IOException {
        File tempFile = File.createTempFile("antlr4-complete", ".jar");
        tempFile.deleteOnExit();
        FileObject tempFileObject = FileUtil.toFileObject(tempFile);
        try (OutputStream outputStream = tempFileObject.getOutputStream()) {
            ClassLoader resourceLoader = CodeGenerator.class.getClassLoader();
            try (InputStream inputStream = resourceLoader.getResourceAsStream(ANTLR4_COMPLETE_JAR)) {
                byte[] buffer = new byte[1 << 16];
                while (true) {
                    int read = inputStream.read(buffer);
                    if (read < 0) {
                        break;
                    }

                    outputStream.write(buffer, 0, read);
                }
            }
        }

        return tempFile;
    }

    private List<String> getCommandArguments() {
        List<String> args = new ArrayList<>();

        if (outputDirectory != null) {
            args.add("-o");
            args.add(FileUtil.toFile(outputDirectory).getAbsolutePath());
        }

        // Where do we want ANTLR to look for .tokens and import grammars?
        if (libDirectory != null && libDirectory.isFolder()) {
            args.add("-lib");
            args.add(FileUtil.toFile(libDirectory).getAbsolutePath());
        }

        if (atn) {
            args.add("-atn");
        }

        if (encoding != null && !encoding.isEmpty()) {
            args.add("-encoding");
            args.add(encoding);
        }

        if (listener) {
            args.add("-listener");
        }
        else {
            args.add("-no-listener");
        }

        if (visitor) {
            args.add("-visitor");
        }
        else {
            args.add("-no-visitor");
        }

        if (treatWarningsAsErrors) {
            args.add("-Werror");
        }

        if (forceATN) {
            args.add("-Xforce-atn");
        }

        if (targetArgument != null) {
            args.add("-Dlanguage=" + targetArgument);
        }

        if (options != null) {
            for (Map.Entry<String, String> option : options.entrySet()) {
                args.add(String.format("-D%s=%s", option.getKey(), option.getValue()));
            }
        }

        if (arguments != null) {
            args.addAll(arguments);
        }

        return args;
    }

    public static class OutputWriterStream extends OutputStream {
        private final OutputWriter writer;

        public OutputWriterStream(@NonNull OutputWriter writer) {
            this.writer = writer;
        }

        @Override
        public void close() throws IOException {
            writer.close();
        }

        @Override
        public void flush() throws IOException {
            writer.flush();
        }

        @Override
        public void write(byte[] b) throws IOException {
            writer.write(new String(b));
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            writer.write(new String(b, off, len));
        }

        @Override
        public void write(int b) throws IOException {
            writer.write(b);
        }
    }
}
