/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.v4.tool.ast.GrammarASTErrorNode;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.parser.CompiledFileModelV4;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.parser.CompiledModelV4;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class RuleScannerV4 extends RuleScanner {

    @Override
    public GrammarNode.GrammarNodeDescription scanImpl(CompiledModel baseModel) {
        try {
            CompiledModelV4 model = (CompiledModelV4)baseModel;

            // don't update if there were errors and a result is already displayed
            /*if (!result.getParser().getSyntaxErrors().isEmpty() && !ui.isShowingWaitNode()) {
                return;
            }*/

            GrammarNode.GrammarNodeDescription rootDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.UNDEFINED);
            rootDescription.setFileObject(model.getSnapshot().getVersionedDocument().getFileObject());

            GrammarNode.GrammarNodeDescription parserRulesRootDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.PARSER_RULE, "1" + Bundle.LBL_ParserRules());
            parserRulesRootDescription.setHtmlHeader(Bundle.LBL_ParserRules());

            GrammarNode.GrammarNodeDescription lexerRulesRootDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.LEXER_RULE, "2" + Bundle.LBL_LexerRules());
            lexerRulesRootDescription.setHtmlHeader(Bundle.LBL_LexerRules());

            Deque<CompiledFileModelV4> importedWorkList = new ArrayDeque<>(model.getImportedGrammarResults());
            Set<String> visitedImports = new HashSet<>();
            Set<String> visitedRules = new HashSet<>();
            while (!importedWorkList.isEmpty()) {
                CompiledFileModelV4 importedParseResult = importedWorkList.pop();
                Grammar grammar = importedParseResult.getGrammar();
                if (grammar == null || grammar.fileName == null) {
                    continue;
                }

                if (visitedImports.add(grammar.fileName) && !importedParseResult.getImportedGrammarResults().isEmpty()) {
                    importedWorkList.push(importedParseResult);
                    importedWorkList.addAll(importedParseResult.getImportedGrammarResults());
                    continue;
                }

                if (!visitedRules.add(grammar.fileName)) {
                    continue;
                }

                processParseResult(null, importedParseResult, parserRulesRootDescription, lexerRulesRootDescription);
            }

            processParseResult(model.getSnapshot(), model.getResult(), parserRulesRootDescription, lexerRulesRootDescription);

            if (!parserRulesRootDescription.getChildren().isEmpty()) {
                rootDescription.getChildren().add(parserRulesRootDescription);
            }

            if (!lexerRulesRootDescription.getChildren().isEmpty()) {
                rootDescription.getChildren().add(lexerRulesRootDescription);
            }

            return rootDescription;
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    private void processParseResult(DocumentSnapshot snapshot,
                                    CompiledFileModelV4 result,
                                    GrammarNode.GrammarNodeDescription parserRulesRootDescription,
                                    GrammarNode.GrammarNodeDescription lexerRulesRootDescription) {

        GrammarRootAST parseResult = result.getResult();
        if (parseResult == null) {
            return;
        }

        GrammarRootAST lexerParseResult = null;
        if (result.getGrammar() != null && result.getGrammar().getImplicitLexer() != null) {
            lexerParseResult = result.getGrammar().getImplicitLexer().ast;
        }

        FileObject fileObject = result.getFileObject();

        Set<GrammarAST> topLevelRules = new HashSet<>(parseResult.getNodesWithType(ANTLRParser.RULE));
        Set<GrammarAST> modes = new HashSet<>(parseResult.getNodesWithType(ANTLRParser.MODE));
        if (lexerParseResult != null) {
            topLevelRules.addAll(lexerParseResult.getNodesWithType(ANTLRParser.RULE));
            modes.addAll(lexerParseResult.getNodesWithType(ANTLRParser.MODE));
        }

        Map<GrammarAST, Set<GrammarAST>> modeRules = new HashMap<>();
        for (GrammarAST mode : modes) {
            Set<GrammarAST> rules = new HashSet<>(mode.getNodesWithType(ANTLRParser.RULE));
            modeRules.put(mode, rules);
            topLevelRules.removeAll(rules);
        }

        processRules(snapshot, result, topLevelRules, parserRulesRootDescription.getChildren(), lexerRulesRootDescription.getChildren());
        for (Map.Entry<GrammarAST, Set<GrammarAST>> entry : modeRules.entrySet()) {
            String modeName = getModeName(entry.getKey());
            GrammarNode.GrammarNodeDescription modeDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.MODE, "_" + modeName);
            modeDescription.setHtmlHeader("mode " + modeName);
            modeDescription.setOffset(snapshot, result.getFileObject(), getElementOffset(entry.getKey()));
            modeDescription.setSpan(getSpan(snapshot, result, entry.getKey()));
            modeDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

            lexerRulesRootDescription.getChildren().add(modeDescription);
            processRules(snapshot, result, entry.getValue(), modeDescription.getChildren(), modeDescription.getChildren());
        }

        Set<GrammarAST> tokensSpecs = new HashSet<>(parseResult.getNodesWithType(ANTLRParser.TOKENS_SPEC));
        if (lexerParseResult != null) {
            tokensSpecs.addAll(lexerParseResult.getNodesWithType(ANTLRParser.TOKENS_SPEC));
        }

        for (GrammarAST tokensSpec : tokensSpecs) {
            for (Object childObject : tokensSpec.getChildren()) {
                if (!(childObject instanceof CommonTree)) {
                    continue;
                }

                CommonTree child = (CommonTree)childObject;
                if (child.getType() == ANTLRParser.ASSIGN && child.getChildCount() == 2) {
                    String ruleName = child.getChild(0).getText();
                    if (ruleName == null || ruleName.length() == 0) {
                        continue;
                    }

                    GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.TOKEN, ruleName);
                    ruleDescription.setOffset(snapshot, fileObject, getElementOffset(child));
                    ruleDescription.setSpan(getSpan(snapshot, result, child));
                    ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                    if (Grammar.isTokenName(ruleName)) {
                        lexerRulesRootDescription.getChildren().add(ruleDescription);
                    } else {
                        parserRulesRootDescription.getChildren().add(ruleDescription);
                    }
                } else if (child.getChildCount() == 0 && child.getToken() != null) {
                    String ruleName = child.getText();
                    if (ruleName == null || ruleName.length() == 0) {
                        continue;
                    }

                    GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.TOKEN, ruleName);
                    ruleDescription.setOffset(snapshot, fileObject, getElementOffset(child));
                    ruleDescription.setSpan(getSpan(snapshot, result, child));
                    ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                    if (Grammar.isTokenName(ruleName)) {
                        lexerRulesRootDescription.getChildren().add(ruleDescription);
                    } else {
                        parserRulesRootDescription.getChildren().add(ruleDescription);
                    }
                }
            }
        }
    }

    private void processRules(DocumentSnapshot snapshot, CompiledFileModelV4 result, Collection<? extends GrammarAST> rules, Collection<Description> parserRules, Collection<Description> lexerRules) {
        for (GrammarAST child : rules) {
            if (child.getChild(0) instanceof GrammarASTErrorNode) {
                continue;
            }

            if (((GrammarAST)child.getChild(0)).g != result.getGrammar()) {
                continue;
            }

            String ruleName = child.getChild(0).getText();
            if ("Tokens".equals(ruleName)) {
                continue;
            }

            DeclarationKind declarationKind;
            if (Grammar.isTokenName(ruleName)) {
                declarationKind = DeclarationKind.LEXER_RULE;
            } else {
                declarationKind = DeclarationKind.PARSER_RULE;
            }

            GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(declarationKind, ruleName);
            ruleDescription.setOffset(snapshot, result.getFileObject(), getElementOffset(child));
            ruleDescription.setSpan(getSpan(snapshot, result, child));
            ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

            if (Grammar.isTokenName(ruleName)) {
                lexerRules.add(ruleDescription);
            } else {
                parserRules.add(ruleDescription);
            }
        }
    }

    private String getModeName(GrammarAST key) {
        if (key.getChildCount() > 0) {
            String name = key.getChild(0).getText();
            if (name != null && !name.isEmpty()) {
                return name;
            }
        }

        return "?";
    }

    private int getElementOffset(CommonTree tree) {
        switch (tree.getType()) {
        case ANTLRParser.MODE:
        case ANTLRParser.ASSIGN:
        case ANTLRParser.RULE:
            if (tree.getChildCount() > 0 && tree.getChild(0) instanceof CommonTree) {
                CommonTree child = (CommonTree)tree.getChild(0);
                if (child.getToken() instanceof CommonToken) {
                    CommonToken token = (CommonToken)child.getToken();
                    return token.getStartIndex();
                }
            }

            break;

        case ANTLRParser.ID:
            break;

        default:
            throw new UnsupportedOperationException();
        }

        if (tree.getToken() instanceof CommonToken) {
            return ((CommonToken)tree.getToken()).getStartIndex();
        }

        return 0;
    }

}
