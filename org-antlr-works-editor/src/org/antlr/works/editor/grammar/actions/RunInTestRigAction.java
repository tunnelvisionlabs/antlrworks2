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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.StyledDocument;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.util.NotificationIcons;
import org.antlr.v4.runtime.misc.TestRig;
import org.antlr.works.editor.grammar.GrammarDataObject;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codegen.CodeGenerator;
import org.antlr.works.editor.grammar.codegen.CodeGenerator.OutputWriterStream;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.RuleKind;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenVocabDeclarationModel;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;
import org.antlr.works.editor.grammar.codemodel.impl.FileVocabModelImpl;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.NotificationDisplayer;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
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
        if (context == null) {
            displayError("This command is only valid in the context of a file.");
            return;
        }

        if (!(context instanceof GrammarDataObject)) {
            displayError("This command is only valid for ANTLR grammar files.");
            return;
        }

        EditorCookie editorCookie = context.getLookup().lookup(EditorCookie.class);
        if (editorCookie != null) {
            StyledDocument document = editorCookie.getDocument();
            if (document != null && GrammarEditorKit.isLegacyMode(document)) {
                displayError("This command is not valid in legacy (ANTLR 3) mode.");
                return;
            }
        }

        FileObject fileObject = context.getPrimaryFile();
        if (fileObject == null) {
            displayError("No FileObject is available for the DataObject");
            return;
        }

        if (fileObject.hasExt("g") || fileObject.hasExt("g3")) {
            displayError("ANTLR grammar files ending in *.g and *.g3 default to legacy (ANTLR 3) mode. If this is an ANTLR 4 grammar, open the file and uncheck the Legacy Mode button on the toolbar before running TestRig.");
            return;
        }

        WizardDescriptor wizard = new WizardDescriptor(new RunInTestRigWizardIterator());
        wizard.setTitle("{0} ({1})");
        wizard.setTitle("Run in TestRig");

        FileModel fileModel = getFileModel(fileObject);
        List<String> availableRules = getAvailableRules(fileModel);
        if (fileModel != null) {
            String initialStartRule = RunInTestRigAction.getFirstRule(fileModel, true);
            wizard.putProperty(RunInTestRigWizardPanel.START_RULE, initialStartRule);
        }
        wizard.putProperty(RunInTestRigWizardPanel.AVAILABLE_RULES, availableRules.toArray(new String[availableRules.size()]));
        if (DialogDisplayer.getDefault().notify(wizard) == WizardDescriptor.FINISH_OPTION) {
            File inputFile = new File(RunInTestRigWizardOptions.getInputFile(wizard));
            if (!inputFile.isFile()) {
                return;
            }

            String baseGrammarName = fileObject.getName();
            if (fileModel != null) {
                if (baseGrammarName.endsWith("Parser")) {
                    boolean containsLexerRule = false;
                    for (RuleModel rule : fileModel.getRules()) {
                        if (rule.getRuleKind() == RuleKind.LEXER) {
                            containsLexerRule = true;
                            break;
                        }
                    }

                    if (!containsLexerRule) {
                        baseGrammarName = baseGrammarName.substring(0, baseGrammarName.length() - "Parser".length());
                    }
                } else if (baseGrammarName.endsWith("Lexer")) {
                    boolean containsParserRule = false;
                    for (RuleModel rule : fileModel.getRules()) {
                        if (rule.getRuleKind() == RuleKind.PARSER) {
                            containsParserRule = true;
                            break;
                        }
                    }

                    if (!containsParserRule) {
                        baseGrammarName = baseGrammarName.substring(0, baseGrammarName.length() - "Lexer".length());
                    }
                }
            }

            List<FileObject> dependencies = getDependencies(fileModel);
            TestRigTask task = new TestRigTask(baseGrammarName, fileObject, dependencies, inputFile);
            task.startRule = RunInTestRigWizardOptions.getStartRule(wizard);
            task.encodingSpecified = RunInTestRigWizardOptions.isEncodingSpecified(wizard);
            task.encoding = RunInTestRigWizardOptions.getEncoding(wizard);
            task.showTokens = RunInTestRigWizardOptions.isShowTokens(wizard);
            task.showTree = RunInTestRigWizardOptions.isShowTree(wizard);
            task.showTreeInGUI = RunInTestRigWizardOptions.isShowTreeInGUI(wizard);
            CodeGenerator.REFERENCE_RP.post(task);
        }
    }

    private void displayError(String message) {
        NotificationDisplayer.getDefault().notify("Run in TestRig", NotificationIcons.ERROR, message, null);
    }

    @CheckForNull
    public static FileModel getFileModel(FileObject fileObject) {
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
        DocumentSnapshot snapshot = versionedDocument.getCurrentSnapshot();
        return getFileModel(snapshot);
    }

    @CheckForNull
    public static FileModel getFileModel(DocumentSnapshot snapshot) {
        ParserTaskManager parserTaskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        Future<ParserData<FileModel>> futureData = parserTaskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL);
        if (futureData == null) {
            return null;
        }

        ParserData<FileModel> data;
        try {
            data = futureData.get();
        } catch (InterruptedException | ExecutionException ex) {
            return null;
        }

        if (data == null) {
            return null;
        }

        return data.getData();
    }

    @CheckForNull
    public static String getFirstRule(@NonNull FileModel fileModel, boolean preferExplicitEof) {
        List<RuleModel> rules = new ArrayList<>(fileModel.getRules());
        Collections.sort(rules, new Comparator<RuleModel>() {
            @Override
            public int compare(RuleModel o1, RuleModel o2) {
                return Integer.compare(getStart(o1), getStart(o2));
            }

            private int getStart(RuleModel ruleModel) {
                if (ruleModel == null) {
                    return Integer.MAX_VALUE;
                }

                CodeElementPositionRegion seek = ruleModel.getSeek();
                if (seek == null) {
                    return Integer.MAX_VALUE;
                }

                return seek.getOffsetRegion().getStart();
            }
        });

        RuleModel firstRule = null;
        for (RuleModel ruleModel : rules) {
            if (ruleModel.getRuleKind() != RuleKind.PARSER) {
                continue;
            }

            if (firstRule == null) {
                firstRule = ruleModel;
            }

            if (ruleModel.hasExplicitEof()) {
                // no need to keep looking after we get to an explicit EOF rule
                return ruleModel.getName();
            }

            if (!preferExplicitEof) {
                break;
            }
        }

        if (firstRule != null) {
            return firstRule.getName();
        }

        return null;
    }

    @NonNull
    public static List<String> getAvailableRules(@NullAllowed FileModel fileModel) {
        if (fileModel == null) {
            return Collections.emptyList();
        }

        Set<String> set = new HashSet<>();
        for (RuleModel rule : fileModel.getRules()) {
            if (rule.getRuleKind() != RuleKind.PARSER) {
                continue;
            }

            set.add(rule.getName());
        }

        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    @NonNull
    private static List<FileObject> getDependencies(@NullAllowed FileModel fileModel) {
        if (fileModel == null) {
            return Collections.emptyList();
        }

        List<FileObject> result = new ArrayList<>();
        for (TokenVocabDeclarationModel tokenVocabDeclarationModel : fileModel.getTokenVocabDeclaration()) {
            for (TokenVocabModel tokenVocabModel : tokenVocabDeclarationModel.resolve()) {
                if (!(tokenVocabModel instanceof FileVocabModelImpl)) {
                    continue;
                }

                FileVocabModelImpl fileVocabModelImpl = (FileVocabModelImpl)tokenVocabModel;
                result.add(fileVocabModelImpl.getFile().getFileObject());
            }
        }

        return result;
    }

    private static class TestRigTask implements Runnable {

        public String startRule;
        public boolean encodingSpecified;
        public String encoding;
        public boolean showTokens;
        public boolean showTree;
        public boolean showTreeInGUI;

        private final String baseGrammarName;
        private final FileObject grammarFile;
        private final List<FileObject> dependencies;
        private final File inputFile;

        public TestRigTask(String baseGrammarName, FileObject grammarFile, List<FileObject> dependencies, File inputFile) {
            this.baseGrammarName = baseGrammarName;
            this.grammarFile = grammarFile;
            this.dependencies = dependencies;
            this.inputFile = inputFile;
        }

        @Override
        public void run() {
            try {
                File tmpdir = new File(System.getProperty("java.io.tmpdir"),
                    getClass().getSimpleName() + "-" + System.currentTimeMillis());
                tmpdir.mkdir();

                List<FileObject> compiled = new ArrayList<>();
                compiled.add(grammarFile);
                compiled.addAll(dependencies);
                CodeGenerator codeGenerator = new CodeGenerator("Java", compiled.toArray(new FileObject[compiled.size()]));
                codeGenerator.outputDirectory = FileUtil.toFileObject(tmpdir);
                codeGenerator.libDirectory = grammarFile.getParent();
                Task codeGenerationTask = codeGenerator.run();
                codeGenerationTask.waitFinished();

                InputOutput inputOutput = IOProvider.getDefault().getIO("ANTLR TestRig (Java)", false);
                inputOutput.select();
                try (OutputWriter outputWriter = inputOutput.getOut(); OutputWriter errorWriter = inputOutput.getErr()) {
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

                    List<String> compileOptions = new ArrayList<>();
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

                    List<String> testRigArguments = new ArrayList<>();
                    testRigArguments.add(baseGrammarName);
                    testRigArguments.add(startRule);

                    if (encodingSpecified && encoding != null && !encoding.isEmpty()) {
                        testRigArguments.add("-encoding");
                        testRigArguments.add(encoding);
                    }

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
                    outputWriter.println("Arguments: " + testRigArguments);

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
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
