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
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.codemodel.impl.FileModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ImportDeclarationModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LabelModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LexerRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ModeModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ParameterModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.ParserRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.RuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.TokenRuleModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.TokenVocabDeclarationModelImpl;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionParametersContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.DelegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ModeSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.TokensSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.antlr.works.editor.grammar.semantics.LiteralLexerRuleValueVisitor;
import org.antlr.works.editor.grammar.semantics.LiteralLexerRuleVisitor;
import org.antlr.works.editor.grammar.semantics.SuppressTokenTypeVisitor;
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

    private final Deque<ModeModelImpl> modeModelStack = new ArrayDeque<ModeModelImpl>();
    private final Deque<RuleModelImpl> ruleModelStack = new ArrayDeque<RuleModelImpl>();
    private final Deque<Collection<ModeModelImpl>> modeContainerStack = new ArrayDeque<Collection<ModeModelImpl>>();
    private final Deque<Collection<RuleModelImpl>> ruleContainerStack = new ArrayDeque<Collection<RuleModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> parameterContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> returnValueContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> localContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<Collection<LabelModelImpl>> labelContainerStack = new ArrayDeque<Collection<LabelModelImpl>>();
    private final Deque<Map<String, Collection<TerminalNode<Token>>>> labelUses = new ArrayDeque<Map<String, Collection<TerminalNode<Token>>>>();

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
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.PARENTS)
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
        this.modeContainerStack.push(this.fileModel.getModes());
        this.ruleContainerStack.push(this.fileModel.getRules());
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.PARENTS)
    public void exitGrammarSpec(GrammarSpecContext ctx) {
        this.fileModel.freeze();
        this.modeContainerStack.pop();
        this.ruleContainerStack.pop();
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS),
    })
    private void handleEnterRule(ParserRuleContext<Token> ctx, TerminalNode<Token> name) {
        String ruleName = name != null ? name.getText() : "?";
        RuleModelImpl ruleModel;
        if (ctx instanceof GrammarParser.ParserRuleSpecContext) {
            ruleModel = new ParserRuleModelImpl(ruleName, fileModel, name, ctx);
        } else if (ctx instanceof GrammarParser.LexerRuleContext) {
            boolean generateTokenType = !SuppressTokenTypeVisitor.INSTANCE.visit(ctx);
            String literal = null;
            if (generateTokenType && LiteralLexerRuleVisitor.INSTANCE.visit(ctx)) {
                TerminalNode<Token> terminal = LiteralLexerRuleValueVisitor.INSTANCE.visit(ctx);
                literal = terminal != null ? terminal.getSymbol().getText() : null;
            }

            ruleModel = new LexerRuleModelImpl(ruleName, modeModelStack.peek(), generateTokenType, literal, fileModel, name, ctx);
        } else {
            throw new UnsupportedOperationException();
        }

        ruleContainerStack.peek().add(ruleModel);
        ruleModelStack.push(ruleModel);
        parameterContainerStack.push(ruleModel.getParameters());
        returnValueContainerStack.push(ruleModel.getReturnValues());
        localContainerStack.push(ruleModel.getLocals());
        labelContainerStack.push(ruleModel.getLabels());
        labelUses.push(new HashMap<String, Collection<TerminalNode<Token>>>());
    }

    private void handleExitRule(ParserRuleContext<Token> ctx) {
        Collection<LabelModelImpl> labels = labelContainerStack.peek();
        for (Map.Entry<String, Collection<TerminalNode<Token>>> labelUsage : labelUses.peek().entrySet()) {
            //labels.add(new LabelModelImpl(labelUsage.getKey(), labelUsage.getValue()));
            labels.add(new LabelModelImpl(labelUsage.getKey(), fileModel, labelUsage.getValue(), null));
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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0, dependents=Dependents.SELF),
    })
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
        handleEnterRule(ctx, ctx.RULE_REF());

        ArgActionParametersContext ctxparameters = ctx.argActionParameters();
        if (ctxparameters != null) {
            handleParameters(ctxparameters.parameters, parameterContainerStack.peek());
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.PARENTS)
    public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
        handleExitRule(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
    public void enterLexerRule(LexerRuleContext ctx) {
        handleEnterRule(ctx, ctx.TOKEN_REF());
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
    public void exitLexerRule(LexerRuleContext ctx) {
        handleExitRule(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_tokensSpec, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
    })
    public void enterTokensSpec(TokensSpecContext ctx) {
        for (IdContext id : ctx.id()) {
            TerminalNode<Token> token = ParseTrees.getStartNode(id);
            if (token == null) {
                continue;
            }

            String tokenName = token.getText();
            RuleModelImpl ruleModel = new TokenRuleModelImpl(tokenName, null, fileModel, token, ctx);
            ruleContainerStack.peek().add(ruleModel);
        }
    }

    @Override
    public void exitTokensSpec(TokensSpecContext ctx) {
        super.exitTokensSpec(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=3, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
    })
    public void enterModeSpec(ModeSpecContext ctx) {
        String name = null;
        IdContext nameId = ctx.id();
        TerminalNode<Token> nameToken = ParseTrees.getStartNode(nameId);

        if (nameToken != null) {
            name = nameToken.getText();
        }

        if (name == null || name.isEmpty()) {
            name = "?mode?";
        }

        ModeModelImpl modeModel = new ModeModelImpl(name, fileModel, nameToken, ctx);
        modeContainerStack.peek().add(modeModel);
        modeModelStack.push(modeModel);
        ruleContainerStack.push(modeModel.getRules());
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=3, dependents=Dependents.PARENTS),
        //@RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
    })
    public void exitModeSpec(ModeSpecContext ctx) {
        modeModelStack.pop();
        ruleContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0, dependents=Dependents.SELF),
    })
    public void enterRuleReturns(RuleReturnsContext ctx) {
        ArgActionParametersContext values = ctx.argActionParameters();
        if (values != null) {
            handleParameters(values.parameters, returnValueContainerStack.peek());
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0, dependents=Dependents.SELF),
    })
    public void enterLocalsSpec(LocalsSpecContext ctx) {
        ArgActionParametersContext values = ctx.argActionParameters();
        if (values != null) {
            handleParameters(values.parameters, localContainerStack.peek());
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
    })
    public void enterLabeledElement(LabeledElementContext ctx) {
        IdContext label = ctx.label;
        if (label != null) {
            TerminalNode<Token> nameToken = ParseTrees.getStartNode(label);
            String name = nameToken.getText();
            Collection<TerminalNode<Token>> uses = labelUses.peek().get(name);
            if (uses == null) {
                uses = new ArrayList<TerminalNode<Token>>();
                labelUses.peek().put(name, uses);
            }

            uses.add(nameToken);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_option, version=3, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0, dependents=Dependents.SELF),
    })
    public void enterOption(OptionContext ctx) {
        IdContext id = ctx.id();
        if (id != null && id.start != null) {
            String optionName = id.start.getText();
            if ("tokenVocab".equals(optionName)) {
                String vocabName = GrammarParser.getOptionValue(ctx.optionValue());
                if (vocabName != null && !vocabName.isEmpty()) {
                    fileModel.getTokenVocabDeclaration().add(new TokenVocabDeclarationModelImpl(vocabName, fileModel));
                }
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_delegateGrammar, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
    })
    public void enterDelegateGrammar(DelegateGrammarContext ctx) {
        List<? extends IdContext> nodes = ctx.id();
        if (nodes.isEmpty()) {
            return;
        }

        String name = getText(nodes.get(0));
        String target = getText(nodes.get(nodes.size() - 1));
        ImportDeclarationModelImpl importDeclarationModel =
            new ImportDeclarationModelImpl(name, target, fileModel, ParseTrees.getStartNode(ctx), ctx);

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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameter, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameterType, version=0, dependents=Dependents.SELF),
    })
    private void handleParameters(@NullAllowed Collection<ArgActionParameterContext> contexts, @NonNull Collection<ParameterModelImpl> models) {
        if (contexts == null) {
            return;
        }

        for (ArgActionParameterContext context : contexts) {
            String type = getText(context.type);
            TerminalNode<Token> name = ParseTrees.findTerminalNode(context, context.name);
            ParameterModelImpl parameter = new ParameterModelImpl(name != null ? name.getText() : "?", type, fileModel, name, context);
            models.add(parameter);
        }
    }

    private String getText(ParserRuleContext<Token> context) {
        if (context == null || context.start == null || context.stop == null) {
            return "";
        }

        return tokens.getText(context.start, context.stop);
    }

}
