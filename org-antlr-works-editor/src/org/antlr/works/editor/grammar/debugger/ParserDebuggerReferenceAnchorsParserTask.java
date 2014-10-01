/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
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
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.ParserInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.atn.RuleTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;
import org.antlr.works.editor.antlr4.parsing.SyntaxErrorListener;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ParserDebuggerReferenceAnchorsParserTask implements ParserTask {

    private final VersionedDocument document;

    private final Object lock = new Object();

    private ParserDebuggerReferenceAnchorsParserTask(VersionedDocument document) {
        this.document = document;
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        //ParserDebuggerEditorKit.LEX
        synchronized (lock) {
            ParserData<FileParseResult> fileParseResultData = taskManager.getData(snapshot, ParserDebuggerParserDataDefinitions.FILE_PARSE_RESULT, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
            ParserData<ParserRuleContext> parseTreeResult = taskManager.getData(snapshot, ParserDebuggerParserDataDefinitions.REFERENCE_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
            if (fileParseResultData == null || parseTreeResult == null) {
                Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, ParserDebuggerParserDataDefinitions.LEXER_TOKENS);
                Tagger<TokenTag<Token>> tagger = futureTokensData.get().getData();
                TaggerTokenSource tokenSource = new TaggerTokenSource(tagger, snapshot);
                InterruptableTokenStream tokenStream = new InterruptableTokenStream(tokenSource);
                ParserRuleContext parseResult;

                ParserInterpreterData parserInterpreterData = (ParserInterpreterData)snapshot.getVersionedDocument().getDocument().getProperty(ParserDebuggerEditorKit.PROP_PARSER_INTERP_DATA);
                String grammarFileName = parserInterpreterData.grammarFileName;
                Vocabulary vocabulary = parserInterpreterData.vocabulary;
                List<String> ruleNames = parserInterpreterData.ruleNames;
                ATN atn = new ATNDeserializer().deserialize(parserInterpreterData.serializedAtn.toCharArray());
                TracingParserInterpreter parser = new TracingParserInterpreter(grammarFileName, vocabulary, ruleNames, atn, tokenStream);

                long startTime = System.nanoTime();
                parser.setInterpreter(new StatisticsParserATNSimulator(parser, atn));
                parser.getInterpreter().optimize_ll1 = false;
                parser.getInterpreter().reportAmbiguities = true;
                parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
                parser.removeErrorListeners();
                parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
                parser.addErrorListener(new StatisticsParserErrorListener());
                SyntaxErrorListener syntaxErrorListener = new SyntaxErrorListener(snapshot);
                parser.addErrorListener(syntaxErrorListener);
                parser.setBuildParseTree(true);
                parser.setErrorHandler(new DefaultErrorStrategy());
                parseResult = parser.parse(parserInterpreterData.startRuleIndex);

                String sourceName = (String)document.getDocument().getProperty(Document.TitleProperty);
                FileParseResult fileParseResult = new FileParseResult(sourceName, 0, parseResult, syntaxErrorListener.getSyntaxErrors(), tokenStream.size(), startTime, null, parser);
                fileParseResultData = new BaseParserData<>(context, ParserDebuggerParserDataDefinitions.FILE_PARSE_RESULT, snapshot, fileParseResult);
                parseTreeResult = new BaseParserData<>(context, ParserDebuggerParserDataDefinitions.REFERENCE_PARSE_TREE, snapshot, parseResult);
            }

            results.addResult(fileParseResultData);
            results.addResult(parseTreeResult);
        }
    }

    public static class TracingParserInterpreter extends ParserInterpreter {
        public final Map<ParseTree, Transition> associatedTransitions = new IdentityHashMap<>();

        public TracingParserInterpreter(String grammarFileName, Vocabulary vocabulary, Collection<String> ruleNames, ATN atn, TokenStream input) {
            super(grammarFileName, vocabulary, ruleNames, atn, input);
        }

        @Override
        protected void visitState(ATNState p) {
            super.visitState(p);

            if (p.getNumberOfTransitions() > 1) {
                return;
            }

            Transition transition = p.transition(0);
            if (transition instanceof RuleTransition) {
                // rule transition created a new context
                associatedTransitions.put(_ctx, transition);
            }
            else if (!p.onlyHasEpsilonTransitions()) {
                // match transition created a new terminal or error node
                associatedTransitions.put(_ctx.getChild(_ctx.getChildCount() - 1), transition);
            }
        }
    }

    private static class InterruptableTokenStream extends CommonTokenStream {
        public InterruptableTokenStream(TokenSource tokenSource) {
            super(tokenSource);
        }

        @Override
        public void consume() {
            if (Thread.interrupted()) {
                throw new CancellationException();
            }

            super.consume();
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                ParserDebuggerParserDataDefinitions.FILE_PARSE_RESULT,
                ParserDebuggerParserDataDefinitions.REFERENCE_PARSE_TREE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Parser Debugger Reference Anchors", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new ParserDebuggerReferenceAnchorsParserTask(document);
        }

    }
}
