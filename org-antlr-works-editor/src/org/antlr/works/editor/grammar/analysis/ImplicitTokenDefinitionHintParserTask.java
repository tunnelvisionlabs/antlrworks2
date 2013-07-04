/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
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
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.semantics.GrammarAnnotatedParseTree;
import org.antlr.works.editor.grammar.semantics.GrammarTreeProperties;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.util.Exceptions;

/**
 * This hint finds cases where lexer tokens are implicitly created in parser
 * rules. In parser and combined grammars, these may be TOKEN_REF tokens in
 * parser rules which are not declared in a {@code tokens{}} block, imported via
 * the {@code tokenVocab} option, or (for combined grammars) in a later
 * non-fragment lexer rule which does not contain a {@code more}, {@code skip},
 * or {@code type} command.
 *
 * @author Sam Harwell
 */
public final class ImplicitTokenDefinitionHintParserTask implements ParserTask {
    private static final String HINT_LAYER = "antlr4/implicit-token-definitions";

    private ImplicitTokenDefinitionHintParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results) throws InterruptedException, ExecutionException {

        Document document = context.getDocument().getDocument();
        if (document == null) {
            return;
        }

        if (GrammarEditorKit.isLegacyMode(document)) {
            HintsController.setErrors(document, HINT_LAYER, Collections.<ErrorDescription>emptyList());
            return;
        }

        CompiledModel model = getCachedData(taskManager, context, snapshot, GrammarParserDataDefinitions.COMPILED_MODEL);
        GrammarAnnotatedParseTree grammarAnnotatedParseTree = getCachedData(taskManager, context, snapshot, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);
        if (model == null || grammarAnnotatedParseTree == null) {
            return;
        }

        Set<String> declaredTokens = new HashSet<>();
        declaredTokens.add("EOF");
        FileModel fileModel = GrammarParserDataDefinitions.tryGetData(taskManager, snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
        if (fileModel == null) {
            return;
        }

        for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
            declaredTokens.add(tokenData.getName());
            String literal = tokenData.getLiteral();
            if (literal != null) {
                declaredTokens.add(literal);
            }
        }

        Listener listener = new Listener(grammarAnnotatedParseTree, declaredTokens);
        ParseTreeWalker.DEFAULT.walk(listener, grammarAnnotatedParseTree.getParseTree());

        List<ErrorDescription> hints = new ArrayList<>();
        for (Interval interval : listener.getRewriteRanges()) {
            try {
                hints.add(ErrorDescriptionFactory.createErrorDescription(Severity.VERIFIER, "Implicit token definition in parser rule", document, document.createPosition(interval.a), document.createPosition(interval.b + 1)));
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        HintsController.setErrors(document, HINT_LAYER, hints);
    }

    private static <T> T getCachedData(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, ParserDataDefinition<T> definition) throws InterruptedException, ExecutionException {
        Future<ParserData<T>> futureData = taskManager.getData(snapshot, context.getComponent(), definition, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
        ParserData<T> parserData = futureData != null ? futureData.get() : null;
        T data = parserData != null ? parserData.getData() : null;
        return data;
    }

    private static final class Listener extends GrammarParserBaseListener {
        private final IntervalSet _rewriteRanges = new IntervalSet();

        @NonNull
        private final GrammarAnnotatedParseTree _grammarAnnotatedParseTree;
        @NonNull
        private final Set<String> _declaredTokens;

        public Listener(@NonNull GrammarAnnotatedParseTree grammarAnnotatedParseTree, @NonNull Set<String> declaredTokens) {
            this._grammarAnnotatedParseTree = grammarAnnotatedParseTree;
            this._declaredTokens = declaredTokens;

        }

        public List<Interval> getRewriteRanges() {
            return _rewriteRanges.getIntervals();
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            Token token = node.getSymbol();
            if (_declaredTokens.contains(token.getText())) {
                return;
            }

            if (_grammarAnnotatedParseTree.getTokenDecorator().getProperty(token, GrammarTreeProperties.PROP_IMPLICIT_DEF)) {
                int startIndex = token.getStartIndex();
                int stopIndex = token.getStopIndex();
                _rewriteRanges.add(startIndex, stopIndex);
            }
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(GrammarParserDataDefinitions.COMPILED_MODEL, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS = Collections.emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Implicit Token Definition Hint", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        protected ParserTask createTaskImpl() {
            return new ImplicitTokenDefinitionHintParserTask();
        }

    }

}
