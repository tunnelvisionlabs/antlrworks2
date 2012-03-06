/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.codemodel.impl.FileModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LabelModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ParameterModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.RuleModelImpl;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParametersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleReturnsContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends GrammarParserBaseListener {
    private final DocumentSnapshot snapshot;
    private final TokenStream<? extends Token> tokens;

    // final result
    private FileModelImpl fileModel;

    // elements of the current grammar (file)
    private Collection<RuleModelImpl> rules;

    // elements of the current rule
    private Map<String, Collection<SnapshotPositionRegion>> labelUses;

    private Collection<ParameterModelImpl> parameters;
    private Collection<ParameterModelImpl> returnValues;
    private Collection<ParameterModelImpl> locals;

    public CodeModelBuilderListener(DocumentSnapshot snapshot, TokenStream<? extends Token> tokens) {
        this.snapshot = snapshot;
        this.tokens = tokens;
        rules = new ArrayList<RuleModelImpl>();
    }

    public FileModelImpl getFileModel() {
        return fileModel;
    }

    @Override
    public void exitGrammarSpec(GrammarSpecContext ctx) {
        fileModel = new FileModelImpl(snapshot, rules);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0),
    })
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
        labelUses = new HashMap<String, Collection<SnapshotPositionRegion>>();
        parameters = new ArrayList<ParameterModelImpl>();
        returnValues = new ArrayList<ParameterModelImpl>();
        locals = new ArrayList<ParameterModelImpl>();

        ArgActionParametersContext ctxparameters = ctx.argActionParameters();
        if (ctxparameters != null) {
            handleParameters(ctxparameters.parameters, parameters);
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0)
    public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
        Token name = ctx.name;
        SnapshotPositionRegion nameSpan = getSpan(ctx.name);
        SnapshotPositionRegion ruleSpan = getSpan(ctx);

        Collection<LabelModelImpl> labels = new ArrayList<LabelModelImpl>();
        for (Map.Entry<String, Collection<SnapshotPositionRegion>> labelUsage : labelUses.entrySet()) {
            labels.add(new LabelModelImpl(labelUsage.getKey(), labelUsage.getValue()));
        }

        if (name != null) {
            RuleModelImpl ruleModel = new RuleModelImpl(ruleSpan, nameSpan, name.getText(), parameters, returnValues, locals, labels);
            rules.add(ruleModel);
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
    public void enterLexerRule(LexerRuleContext ctx) {
        labelUses = new HashMap<String, Collection<SnapshotPositionRegion>>();
        parameters = new ArrayList<ParameterModelImpl>();
        returnValues = new ArrayList<ParameterModelImpl>();
        locals = new ArrayList<ParameterModelImpl>();
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
    public void exitLexerRule(LexerRuleContext ctx) {
        Token name = ctx.name;
        SnapshotPositionRegion nameSpan = getSpan(ctx.name);
        SnapshotPositionRegion ruleSpan = getSpan(ctx);

        Collection<LabelModelImpl> labels = new ArrayList<LabelModelImpl>();
        for (Map.Entry<String, Collection<SnapshotPositionRegion>> labelUsage : labelUses.entrySet()) {
            labels.add(new LabelModelImpl(labelUsage.getKey(), labelUsage.getValue()));
        }

        RuleModelImpl ruleModel = new RuleModelImpl(ruleSpan, nameSpan, name.getText(), parameters, returnValues, locals, labels);
        rules.add(ruleModel);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0),
    })
    public void enterRuleReturns(RuleReturnsContext ctx) {
        ArgActionParametersContext values = ctx.argActionParameters();
        if (values != null) {
            handleParameters(values.parameters, returnValues);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0),
    })
    public void enterLocalsSpec(LocalsSpecContext ctx) {
        ArgActionParametersContext values = ctx.argActionParameters();
        if (values != null) {
            handleParameters(values.parameters, locals);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLabeledElement(LabeledElementContext ctx) {
        if (ctx.label != null) {
            String name = ctx.label.start.getText();
            Collection<SnapshotPositionRegion> uses = labelUses.get(name);
            if (uses == null) {
                uses = new ArrayList<SnapshotPositionRegion>();
                labelUses.put(name, uses);
            }

            SnapshotPositionRegion use = getSpan(ctx.label);
            uses.add(use);
        }
    }

    private SnapshotPositionRegion getSpan(Token token) {
        if (token == null) {
            return null;
        }
        
        return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1));
    }

    private SnapshotPositionRegion getSpan(ParserRuleContext<Token> context) {
        if (context == null) {
            return null;
        }

        Interval sourceInterval = ParseTrees.getSourceInterval(context);
        if (sourceInterval != null && sourceInterval.b + 1 >= sourceInterval.a) {
            return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(sourceInterval.a, sourceInterval.b + 1));
        }

        return null;
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameter, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameterType, version=0),
    })
    private void handleParameters(@NullAllowed Collection<ArgActionParameterContext> contexts, @NonNull Collection<ParameterModelImpl> models) {
        if (contexts == null) {
            return;
        }

        for (ArgActionParameterContext context : contexts) {
            SnapshotPositionRegion typeSpan = getSpan(context.type);
            String type = getText(context.type);
            Token name = context.name;
            SnapshotPositionRegion nameSpan = getSpan(context.name);
            ParameterModelImpl parameter = new ParameterModelImpl(nameSpan, typeSpan, name != null ? name.getText() : "?", type);
            models.add(parameter);
        }
    }

    private String getText(ParserRuleContext<Token> context) {
        if (context == null || context.start == null || context.stop == null) {
            return "";
        }

        return tokens.toString(context.start, context.stop);
    }
}
