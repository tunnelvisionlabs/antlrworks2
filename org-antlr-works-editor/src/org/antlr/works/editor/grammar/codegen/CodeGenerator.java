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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.antlr.v4.Tool;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

/**
 *
 * @author Sam Harwell
 */
public class CodeGenerator {
    @StaticResource
    private static final String ANTLR4_COMPLETE_JAR = "org/antlr/works/editor/grammar/resources/antlr4-complete.jar";

    public final String target;
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
        if (!target.contains("Java")) {
            throw new UnsupportedOperationException();
        }

        this.target = target;
        this.grammarFiles = grammarFiles;
    }

    public void run() {
        try {
            ClassLoader loader;
            if (target.contains("sharwell/optimized")) {
                loader = Thread.currentThread().getContextClassLoader();
            } else {
                File completeJar = copyCompleteJarToTempDir();
                loader = new URLClassLoader(new URL[] { Utilities.toURI(completeJar).toURL() }, ClassLoader.getSystemClassLoader());
            }

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
                Object tool = ctor.newInstance((Object)args.toArray(new String[args.size()]));
                processGrammarsOnCommandLine.invoke(tool);
            } finally {
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            }
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        } catch (SecurityException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InstantiationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InvocationTargetException ex) {
            Exceptions.printStackTrace(ex);
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static File copyCompleteJarToTempDir() throws IOException {
        File tempFile = File.createTempFile("antlr4-complete", ".jar");
        tempFile.deleteOnExit();
        FileObject tempFileObject = FileUtil.toFileObject(tempFile);
        OutputStream outputStream = tempFileObject.getOutputStream();
        try {
            ClassLoader resourceLoader = CodeGenerator.class.getClassLoader();
            InputStream inputStream = resourceLoader.getResourceAsStream(ANTLR4_COMPLETE_JAR);
            try {
                byte[] buffer = new byte[1 << 16];
                while (true) {
                    int read = inputStream.read(buffer);
                    if (read < 0) {
                        break;
                    }

                    outputStream.write(buffer, 0, read);
                }
            } finally {
                inputStream.close();
            }
        } finally {
            outputStream.close();
        }

        return tempFile;
    }

    private List<String> getCommandArguments() {
        List<String> args = new ArrayList<String>();

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

}
