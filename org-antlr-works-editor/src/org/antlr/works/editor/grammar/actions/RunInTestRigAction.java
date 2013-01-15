/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.misc.TestRig;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codegen.CodeGenerator;
import org.antlr.works.editor.grammar.codegen.CodeGenerator.OutputWriterStream;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.RuleKind;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Task;
import org.openide.util.Utilities;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

@ActionID(
    category = "Debug",
    id = "org.antlr.works.editor.grammar.actions.RunInTestRigAction")
@ActionRegistration(
    displayName = "#CTL_RunInTestRigAction")
@ActionReference(path = "Menu/BuildProject", position = -5)
@Messages("CTL_RunInTestRigAction=Run in TestRig...")
public final class RunInTestRigAction implements ActionListener {

    private final DataObject context;

    public RunInTestRigAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        final FileObject fileObject = context.getPrimaryFile();
        if (fileObject == null) {
            return;
        }

        String mimeType = fileObject.getMIMEType();
        if (!GrammarEditorKit.GRAMMAR_MIME_TYPE.equals(mimeType)) {
            return;
        }

        WizardDescriptor wizard = new WizardDescriptor(new RunInTestRigWizardIterator());
        wizard.setTitle("{0} ({1})");
        wizard.setTitle("Run in TestRig");

        List<String> availableRules = getAvailableRules(fileObject);
        wizard.putProperty(RunInTestRigWizardPanel.AVAILABLE_RULES, availableRules.toArray(new String[availableRules.size()]));
        if (DialogDisplayer.getDefault().notify(wizard) == WizardDescriptor.FINISH_OPTION) {
            File inputFile = new File(RunInTestRigWizardOptions.getInputFile(wizard));
            if (!inputFile.isFile()) {
                return;
            }

            TestRigTask task = new TestRigTask(fileObject, inputFile);
            task.startRule = RunInTestRigWizardOptions.getStartRule(wizard);
            task.showTokens = RunInTestRigWizardOptions.isShowTokens(wizard);
            task.showTree = RunInTestRigWizardOptions.isShowTree(wizard);
            task.showTreeInGUI = RunInTestRigWizardOptions.isShowTreeInGUI(wizard);
            CodeGenerator.REFERENCE_RP.post(task);
        }
    }

    public static List<String> getAvailableRules(FileObject fileObject) {
        ParserTaskManager parserTaskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
        DocumentSnapshot snapshot = versionedDocument.getCurrentSnapshot();
        Future<ParserData<FileModel>> futureData = parserTaskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL);
        if (futureData == null) {
            return Collections.emptyList();
        }

        ParserData<FileModel> data;
        try {
            data = futureData.get();
        } catch (InterruptedException ex) {
            return Collections.emptyList();
        } catch (ExecutionException ex) {
            return Collections.emptyList();
        }

        if (data == null) {
            return Collections.emptyList();
        }

        FileModel fileModel = data.getData();
        if (fileModel == null) {
            return Collections.emptyList();
        }

        Set<String> set = new HashSet<String>();
        for (RuleModel rule : fileModel.getRules()) {
            if (rule.getRuleKind() != RuleKind.PARSER) {
                continue;
            }

            set.add(rule.getName());
        }

        List<String> result = new ArrayList<String>(set);
        Collections.sort(result);
        return result;
    }

    private static class TestRigTask implements Runnable {

        public String startRule;
        public boolean showTokens;
        public boolean showTree;
        public boolean showTreeInGUI;

        private final FileObject grammarFile;
        private final File inputFile;

        public TestRigTask(FileObject grammarFile, File inputFile) {
            this.grammarFile = grammarFile;
            this.inputFile = inputFile;
        }

        @Override
        public void run() {
            try {
                File tmpdir = new File(System.getProperty("java.io.tmpdir"),
                    getClass().getSimpleName() + "-" + System.currentTimeMillis());
                tmpdir.mkdir();
                CodeGenerator codeGenerator = new CodeGenerator("Java", grammarFile);
                codeGenerator.outputDirectory = FileUtil.toFileObject(tmpdir);
                codeGenerator.libDirectory = grammarFile.getParent();
                Task codeGenerationTask = codeGenerator.run();
                codeGenerationTask.waitFinished();

                InputOutput inputOutput = IOProvider.getDefault().getIO("ANTLR TestRig (Java)", false);
                inputOutput.select();
                OutputWriter outputWriter = inputOutput.getOut();
                try {
                    OutputWriter errorWriter = inputOutput.getErr();
                    try {
                        outputWriter.println("Compiling grammar files...");

                        File[] files = tmpdir.listFiles(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith(".java");
                            }
                        });

                        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

                        Iterable<? extends JavaFileObject> compilationUnits =
                            fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files));

                        List<String> compileOptions = new ArrayList<String>();
                        compileOptions.add("-g");
                        compileOptions.add("-d");
                        compileOptions.add(tmpdir.getAbsolutePath());
                        compileOptions.add("-cp");
                        compileOptions.add(CodeGenerator.getReferenceLibrary().getAbsolutePath());
                        compileOptions.add("-Xlint");
                        compileOptions.add("-Xlint:-serial");

                        JavaCompiler.CompilationTask task =
                            compiler.getTask(errorWriter, fileManager, null, compileOptions, null,
                            compilationUnits);

                        task.call();

                        try {
                            fileManager.close();
                        } catch (IOException ioe) {
                            Exceptions.printStackTrace(ioe);
                            return;
                        }

                        final ClassLoader loader = new URLClassLoader(new URL[]{Utilities.toURI(tmpdir).toURL()}, CodeGenerator.getReferenceClassLoader());
                        Class<?> testRig = loader.loadClass(TestRig.class.getName());
                        Method mainMethod = testRig.getMethod("main", String[].class);

                        List<String> testRigArguments = new ArrayList<String>();
                        testRigArguments.add(grammarFile.getName());
                        testRigArguments.add(startRule);
                        if (showTokens) {
                            testRigArguments.add("-tokens");
                        }

                        if (showTree) {
                            testRigArguments.add("-tree");
                        }

                        if (showTreeInGUI) {
                            testRigArguments.add("-gui");
                        }

                        testRigArguments.add(inputFile.getAbsolutePath());

                        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(loader);
                        try {
                            PrintStream originalOut = System.out;
                            System.setOut(new PrintStream(new OutputWriterStream(outputWriter)));
                            try {
                                PrintStream originalErr = System.err;
                                System.setErr(new PrintStream(new OutputWriterStream(errorWriter)));
                                try {
                                    mainMethod.invoke(null, (Object)testRigArguments.toArray(new String[testRigArguments.size()]));
                                } finally {
                                    System.setErr(originalErr);
                                }
                            } finally {
                                System.setOut(originalOut);
                            }
                        } finally {
                            Thread.currentThread().setContextClassLoader(contextClassLoader);
                        }
                    } finally {
                        errorWriter.close();
                    }
                } finally {
                    outputWriter.close();
                }
            } catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } catch (NoSuchMethodException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SecurityException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
            } catch (MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
