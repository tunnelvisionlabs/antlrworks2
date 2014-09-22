/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.DocumentParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.DelegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.DelegateGrammarsContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.OptionsSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.PrequelConstructContext;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public final class SemanticAnalyzerParserTask implements ParserTask {
    // -J-Dorg.antlr.works.editor.grammar.semantics.SemanticAnalyzerParserTask.level=FINE
    private static final Logger LOGGER = Logger.getLogger(SemanticAnalyzerParserTask.class.getName());

    private final Object lock = new Object();

    private SemanticAnalyzerParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF)
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE)) {
            synchronized (lock) {
                ParserData<GrammarAnnotatedParseTree> parseTreeResult = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS)).get();
                if (parseTreeResult != null) {
                    results.addResult(parseTreeResult);
                    return;
                }

                GrammarSpecContext referenceParseTree = null;
                try {
                    Future<ParserData<GrammarSpecContext>> futureRefParseTreeData = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                    ParserData<GrammarSpecContext> refParseTreeData = futureRefParseTreeData != null ? futureRefParseTreeData.get() : null;
                    referenceParseTree = refParseTreeData != null ? refParseTreeData.getData() : null;
                } catch (InterruptedException | ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }

                if (referenceParseTree != null) {
                    updateImportedFiles(snapshot.getVersionedDocument(), referenceParseTree);
                    GrammarAnnotatedParseTree annotatedParseTree = new GrammarAnnotatedParseTree(referenceParseTree);
                    SemanticAnalyzerListener listener = new SemanticAnalyzerListener(annotatedParseTree.getTreeDecorator(), annotatedParseTree.getTokenDecorator());
                    ParseTreeWalker.DEFAULT.walk(listener, referenceParseTree);
                    parseTreeResult = new BaseParserData<>(context, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, snapshot, annotatedParseTree);
                } else {
                    parseTreeResult = new BaseParserData<>(context, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, snapshot, null);
                }

                results.addResult(parseTreeResult);
            }
        }
    }

    private ParserTaskManager getTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_prequelConstruct, version=6, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_delegateGrammars, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionsSpec, version=3, dependents=Dependents.SELF),
    })
    private void updateImportedFiles(VersionedDocument document, GrammarSpecContext grammarSpec) {
        for (PrequelConstructContext prequelConstruct : grammarSpec.prequelConstruct()) {
            if (prequelConstruct.delegateGrammars() != null) {
                handleImportedGrammars(document, prequelConstruct.delegateGrammars());
            }

            if (prequelConstruct.optionsSpec() != null) {
                handleImportOptions(document, prequelConstruct.optionsSpec());
            }
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionsSpec, version=3, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_option, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0, dependents=Dependents.SELF),
    })
    private void handleImportOptions(VersionedDocument document, OptionsSpecContext optionsSpec) {
        for (OptionContext option : optionsSpec.option()) {
            IdContext id = option.id();
            if (id != null && id.start != null) {
                String optionName = id.start.getText();
                if ("tokenVocab".equals(optionName)) {
                    String vocabName = GrammarParser.getOptionValue(option.optionValue());
                    if (vocabName != null && !vocabName.isEmpty()) {
                        FileObject fileObject = document.getFileObject();
                        if (fileObject == null) {
                            LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                            continue;
                        }

                        // try to find a lexer in the same folder with this name
                        FileObject containingFolder = fileObject.getParent();
                        if (containingFolder == null) {
                            LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                            continue;
                        }

                        FileObject sourceFileObject = containingFolder.getFileObject(vocabName, "g4");
                        if (sourceFileObject == null) {
                            sourceFileObject = containingFolder.getFileObject(vocabName, "g3");
                        }

                        if (sourceFileObject == null) {
                            sourceFileObject = containingFolder.getFileObject(vocabName, "g");
                        }

                        if (sourceFileObject == null) {
                            LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                            continue;
                        }

                        VersionedDocument sourceDocument = VersionedDocumentUtilities.getVersionedDocument(sourceFileObject);
                        Future<? extends ParserData<?>> futureData = getTaskManager().getData(sourceDocument.getCurrentSnapshot(), GrammarParserDataDefinitions.FILE_MODEL);
                        if (futureData == null) {
                            LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                            continue;
                        }

                        ParserData<?> data;
                        try {
                            data = futureData.get();
                        } catch (InterruptedException | ExecutionException ex) {
                            LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                            continue;
                        }

                        if (data == null) {
                            LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                        }
                    }
                }
            }
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_delegateGrammars, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_delegateGrammar, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
    })
    private void handleImportedGrammars(VersionedDocument document, DelegateGrammarsContext delegateGrammars) {
        for (DelegateGrammarContext delegateGrammar : delegateGrammars.delegateGrammar()) {
            List<? extends IdContext> idContexts = delegateGrammar.id();
            if (idContexts.isEmpty()) {
                continue;
            }

            IdContext grammarNameContext = idContexts.get(idContexts.size() - 1);
            String grammarName = grammarNameContext.getText();
            if (grammarName == null || grammarName.isEmpty()) {
                continue;
            }

            FileObject fileObject = document.getFileObject();
            if (fileObject == null) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
                continue;
            }

            // try to find the grammar in the same folder with this name
            FileObject containingFolder = fileObject.getParent();
            if (containingFolder == null) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
                continue;
            }

            FileObject sourceFileObject = containingFolder.getFileObject(grammarName, "g4");
            if (sourceFileObject == null) {
                sourceFileObject = containingFolder.getFileObject(grammarName, "g3");
            }

            if (sourceFileObject == null) {
                sourceFileObject = containingFolder.getFileObject(grammarName, "g");
            }

            if (sourceFileObject == null) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
                continue;
            }

            VersionedDocument sourceDocument = VersionedDocumentUtilities.getVersionedDocument(sourceFileObject);
            Future<? extends ParserData<?>> futureData = getTaskManager().getData(sourceDocument.getCurrentSnapshot(), GrammarParserDataDefinitions.FILE_MODEL);
            if (futureData == null) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
                continue;
            }

            ParserData<?> data;
            try {
                data = futureData.get();
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
                continue;
            }

            if (data == null) {
                LOGGER.log(Level.WARNING, "Could not find source for imported grammar.");
            }
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Semantic Analyzer", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new SemanticAnalyzerParserTask();
        }

    }

}
