/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.ParameterModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.grammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.locals_Context;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleReturnsContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends BlankGrammarParserListener {
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
    public void exitRule(grammarSpecContext ctx) {
        fileModel = new FileModel(snapshot, rules);
    }

    @Override
    public void enterRule(ruleContext ctx) {
        labelUses = new HashMap<String, Collection<SnapshotPositionRegion>>();
        parameters = new ArrayList<ParameterModel>();
        returnValues = new ArrayList<ParameterModel>();
        locals = new ArrayList<ParameterModel>();

        if (ctx.parameters != null) {
            handleParameters(ctx.parameters.parameters_list, parameters);
        }
    }

    @Override
    public void exitRule(ruleContext ctx) {
        Token name = ctx.name.start;
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
    public void enterRule(ruleReturnsContext ctx) {
        if (ctx.values != null) {
            handleParameters(ctx.values.parameters_list, returnValues);
        }
    }

    @Override
    public void enterRule(locals_Context ctx) {
        if (ctx.values != null) {
            handleParameters(ctx.values.parameters_list, locals);
        }
    }

    @Override
    public void enterRule(labeledElementContext ctx) {
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

        Token startToken = context.start;
        Token stopToken = context.stop;
        if (startToken != null && stopToken != null) {
            int startIndex = startToken.getStartIndex();
            int stopIndex = stopToken.getStopIndex() + 1;
            return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startIndex, stopIndex));
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
