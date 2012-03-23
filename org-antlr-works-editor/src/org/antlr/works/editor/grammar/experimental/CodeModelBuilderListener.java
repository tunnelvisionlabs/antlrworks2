/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
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
import org.antlr.v4.runtime.tree.ParseTree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.codemodel.impl.FileModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ImportDeclarationModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LabelModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LexerRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ParameterModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ParserRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.RuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.TokenRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.TokenVocabDeclarationModelImpl;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParametersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.DelegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.OptionValueContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.QidContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.TokenSpecContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends GrammarParserBaseListener {
    private final Project project;
    private final DocumentSnapshot snapshot;
    private final TokenStream<? extends Token> tokens;

    // final result
    private FileModelImpl fileModel;

    private final Deque<RuleModelImpl> ruleModelStack = new ArrayDeque<RuleModelImpl>();
    private final Deque<Collection<RuleModelImpl>> ruleContainerStack = new ArrayDeque<Collection<RuleModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> parameterContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> returnValueContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> localContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<LabelModelImpl>> labelContainerStack = new ArrayDeque<Collection<LabelModelImpl>>();
    private final Deque<Map<String, Collection<SnapshotPositionRegion>>> labelUses = new ArrayDeque<Map<String, Collection<SnapshotPositionRegion>>>();

    public CodeModelBuilderListener(DocumentSnapshot snapshot, TokenStream<? extends Token> tokens) {
        FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
        this.project = fileObject != null ? FileOwnerQuery.getOwner(fileObject) : null;
        this.snapshot = snapshot;
        this.tokens = tokens;
    }

    public FileModelImpl getFileModel() {
        return fileModel;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0)
    public void enterGrammarSpec(GrammarSpecContext ctx) {
        FileObject packageFolder = snapshot.getVersionedDocument().getFileObject().getParent();
        FileObject projectFolder = project != null ? project.getProjectDirectory() : null;

        String packagePath;
        if (projectFolder != null) {
            packagePath = FileUtil.getRelativePath(projectFolder, packageFolder);
        } else {
            packagePath = packageFolder.getNameExt();
        }

        FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
        this.fileModel = new FileModelImpl(fileObject, project, packagePath);
        this.ruleContainerStack.push(this.fileModel.getRules());
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0)
    public void exitGrammarSpec(GrammarSpecContext ctx) {
        this.fileModel.freeze();
        this.ruleContainerStack.pop();
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0),
    })
    private void handleEnterRule(ParserRuleContext<Token> ctx, Token name) {
        String ruleName = name != null ? name.getText() : "?";
        RuleModelImpl ruleModel;
        if (ctx instanceof GrammarParser.ParserRuleSpecContext) {
            ruleModel = new ParserRuleModelImpl(ruleName, fileModel);
        } else if (ctx instanceof GrammarParser.LexerRuleContext) {
            ruleModel = new LexerRuleModelImpl(ruleName, fileModel);
        } else {
            throw new UnsupportedOperationException();
        }

        ruleContainerStack.peek().add(ruleModel);
        ruleModelStack.push(ruleModel);
        parameterContainerStack.push(ruleModel.getParameters());
        returnValueContainerStack.push(ruleModel.getReturnValues());
        localContainerStack.push(ruleModel.getLocals());
        labelContainerStack.push(ruleModel.getLabels());
        labelUses.push(new HashMap<String, Collection<SnapshotPositionRegion>>());
    }

    private void handleExitRule(ParserRuleContext<Token> ctx) {
        Collection<LabelModelImpl> labels = labelContainerStack.peek();
        for (Map.Entry<String, Collection<SnapshotPositionRegion>> labelUsage : labelUses.peek().entrySet()) {
            //labels.add(new LabelModelImpl(labelUsage.getKey(), labelUsage.getValue()));
            labels.add(new LabelModelImpl(labelUsage.getKey(), fileModel));
        }

        ruleModelStack.pop();
        parameterContainerStack.pop();
        returnValueContainerStack.pop();
        localContainerStack.pop();
        labelContainerStack.pop();
        labelUses.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0),
    })
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
        handleEnterRule(ctx, ctx.name);

        ArgActionParametersContext ctxparameters = ctx.argActionParameters();
        if (ctxparameters != null) {
            handleParameters(ctxparameters.parameters, parameterContainerStack.peek());
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0)
    public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
        handleExitRule(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
    public void enterLexerRule(LexerRuleContext ctx) {
        handleEnterRule(ctx, ctx.name);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
    public void exitLexerRule(LexerRuleContext ctx) {
        handleExitRule(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_tokenSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterTokenSpec(TokenSpecContext ctx) {
        IdContext id = ctx.id();
        if (id == null) {
            return;
        }

        String tokenName = ctx.id().start.getText();
        String literal = null;
        TerminalNode<Token> literalNode = ctx.STRING_LITERAL();
        if (literalNode != null) {
            literal = literalNode.getSymbol().getText();
            literal = literal.substring(1, literal.length() - 1);
        }

        RuleModelImpl ruleModel = new TokenRuleModelImpl(ctx.id().start.getText(), literal, fileModel);
        ruleContainerStack.peek().add(ruleModel);
    }

    @Override
    public void exitTokenSpec(TokenSpecContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0),
    })
    public void enterRuleReturns(RuleReturnsContext ctx) {
        ArgActionParametersContext values = ctx.argActionParameters();
        if (values != null) {
            handleParameters(values.parameters, returnValueContainerStack.peek());
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
            handleParameters(values.parameters, localContainerStack.peek());
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
            Collection<SnapshotPositionRegion> uses = labelUses.peek().get(name);
            if (uses == null) {
                uses = new ArrayList<SnapshotPositionRegion>();
                labelUses.peek().put(name, uses);
            }

            SnapshotPositionRegion use = getSpan(ctx.label);
            uses.add(use);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_option, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0),
    })
    public void enterOption(OptionContext ctx) {
        IdContext id = ctx.id();
        if (id != null && id.start != null) {
            String optionName = id.start.getText();
            if ("tokenVocab".equals(optionName)) {
                String vocabName = getOptionValue(ctx.optionValue());
                if (vocabName != null && !vocabName.isEmpty()) {
                    fileModel.getTokenVocabDeclaration().add(new TokenVocabDeclarationModelImpl(vocabName, fileModel));
                }
            }
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_qid, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0),
    })
    private String getOptionValue(OptionValueContext ctx) {
        QidContext qid = ctx.qid();
        if (qid != null) {
            return getText(qid);
        }

        TerminalNode<Token> node = ctx.INT();
        if (node != null) {
            return node.getSymbol().getText();
        }

        node = ctx.STAR();
        if (node != null) {
            return node.getSymbol().getText();
        }

        node = ctx.STRING_LITERAL();
        if (node != null) {
            String result = node.getSymbol().getText();
            result = result.substring(0, result.length() - 1);
            result = result.replace("\\\"", "\"");
            return result;
        }

        return null;
    }

    @Override
    public void enterDelegateGrammar(DelegateGrammarContext ctx) {
        List<? extends IdContext> nodes = ctx.id();
        if (nodes.isEmpty()) {
            return;
        }

        String name = getText(nodes.get(0));
        String target = getText(nodes.get(nodes.size() - 1));
        ImportDeclarationModelImpl importDeclarationModel =
            new ImportDeclarationModelImpl(name, target, fileModel);

        fileModel.getImportDeclarations().add(importDeclarationModel);
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
            String type = getText(context.type);
            Token name = context.name;
            ParameterModelImpl parameter = new ParameterModelImpl(name != null ? name.getText() : "?", type, fileModel);
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
