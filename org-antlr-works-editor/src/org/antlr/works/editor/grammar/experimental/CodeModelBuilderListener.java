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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.ParameterModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.grammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.localsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleReturnsContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends GrammarParserBaseListener {
    private final DocumentSnapshot snapshot;
    private final TokenStream tokens;

    // final result
    private FileModel fileModel;

    // elements of the current grammar (file)
    private Collection<RuleModel> rules;

    // elements of the current rule
    private Map<String, Collection<SnapshotPositionRegion>> labelUses;

    private Collection<ParameterModel> parameters;
    private Collection<ParameterModel> returnValues;
    private Collection<ParameterModel> locals;

    public CodeModelBuilderListener(DocumentSnapshot snapshot, TokenStream tokens) {
        this.snapshot = snapshot;
        this.tokens = tokens;
        rules = new ArrayList<RuleModel>();
    }

    public FileModel getFileModel() {
        return fileModel;
    }

    @Override
    public void grammarSpecExit(grammarSpecContext ctx) {
        fileModel = new FileModel(snapshot, rules);
    }

    @Override
    public void parserRuleEnter(parserRuleContext ctx) {
        labelUses = new HashMap<String, Collection<SnapshotPositionRegion>>();
        parameters = new ArrayList<ParameterModel>();
        returnValues = new ArrayList<ParameterModel>();
        locals = new ArrayList<ParameterModel>();

        if (ctx.parameters != null) {
            handleParameters(ctx.parameters.parameters, parameters);
        }
    }

    @Override
    public void parserRuleExit(parserRuleContext ctx) {
        Token name = ctx.name;
        SnapshotPositionRegion nameSpan = getSpan(ctx.name);
        SnapshotPositionRegion ruleSpan = getSpan(ctx);

        Collection<LabelModel> labels = new ArrayList<LabelModel>();
        for (Map.Entry<String, Collection<SnapshotPositionRegion>> labelUsage : labelUses.entrySet()) {
            labels.add(new LabelModel(labelUsage.getKey(), labelUsage.getValue()));
        }

        if (name != null) {
            RuleModel ruleModel = new RuleModel(ruleSpan, nameSpan, name.getText(), parameters, returnValues, locals, labels);
            rules.add(ruleModel);
        }
    }

    @Override
    public void lexerRuleEnter(lexerRuleContext ctx) {
        labelUses = new HashMap<String, Collection<SnapshotPositionRegion>>();
        parameters = new ArrayList<ParameterModel>();
        returnValues = new ArrayList<ParameterModel>();
        locals = new ArrayList<ParameterModel>();
    }

    @Override
    public void lexerRuleExit(lexerRuleContext ctx) {
        Token name = ctx.name;
        SnapshotPositionRegion nameSpan = getSpan(ctx.name);
        SnapshotPositionRegion ruleSpan = getSpan(ctx);

        Collection<LabelModel> labels = new ArrayList<LabelModel>();
        for (Map.Entry<String, Collection<SnapshotPositionRegion>> labelUsage : labelUses.entrySet()) {
            labels.add(new LabelModel(labelUsage.getKey(), labelUsage.getValue()));
        }

        RuleModel ruleModel = new RuleModel(ruleSpan, nameSpan, name.getText(), parameters, returnValues, locals, labels);
        rules.add(ruleModel);
    }

    @Override
    public void ruleReturnsEnter(ruleReturnsContext ctx) {
        if (ctx.values != null) {
            handleParameters(ctx.values.parameters, returnValues);
        }
    }

    @Override
    public void localsSpecEnter(localsSpecContext ctx) {
        if (ctx.values != null) {
            handleParameters(ctx.values.parameters, locals);
        }
    }

    @Override
    public void labeledElementEnter(labeledElementContext ctx) {
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

    private void handleParameters(@NullAllowed Collection<argActionParameterContext> contexts, @NonNull Collection<ParameterModel> models) {
        if (contexts == null) {
            return;
        }

        for (argActionParameterContext context : contexts) {
            SnapshotPositionRegion typeSpan = getSpan(context.type);
            String type = getText(context.type);
            Token name = context.name;
            SnapshotPositionRegion nameSpan = getSpan(context.name);
            ParameterModel parameter = new ParameterModel(nameSpan, typeSpan, name != null ? name.getText() : "?", type);
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
