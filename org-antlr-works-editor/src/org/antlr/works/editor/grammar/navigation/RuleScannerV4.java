/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import java.util.Collection;
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

            GrammarNode.GrammarNodeDescription rootDescription = new GrammarNode.GrammarNodeDescription();
            rootDescription.setFileObject(model.getSnapshot().getVersionedDocument().getFileObject());

            GrammarNode.GrammarNodeDescription parserRulesRootDescription = new GrammarNode.GrammarNodeDescription("1" + Bundle.LBL_ParserRules());
            parserRulesRootDescription.setHtmlHeader(Bundle.LBL_ParserRules());

            GrammarNode.GrammarNodeDescription lexerRulesRootDescription = new GrammarNode.GrammarNodeDescription("2" + Bundle.LBL_LexerRules());
            lexerRulesRootDescription.setHtmlHeader(Bundle.LBL_LexerRules());

            for (CompiledFileModelV4 importedParseResult : model.getImportedGrammarResults()) {
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

        FileObject fileObject = result.getFileObject();

        Set<GrammarAST> topLevelRules = new HashSet<GrammarAST>(parseResult.getNodesWithType(ANTLRParser.RULE));
        Set<GrammarAST> modes = new HashSet<GrammarAST>(parseResult.getNodesWithType(ANTLRParser.MODE));
        Map<GrammarAST, Set<GrammarAST>> modeRules = new HashMap<GrammarAST, Set<GrammarAST>>();
        for (GrammarAST mode : modes) {
            Set<GrammarAST> rules = new HashSet<GrammarAST>(mode.getNodesWithType(ANTLRParser.RULE));
            modeRules.put(mode, rules);
            topLevelRules.removeAll(rules);
        }

        processRules(snapshot, result, topLevelRules, parserRulesRootDescription.getChildren(), lexerRulesRootDescription.getChildren());
        for (Map.Entry<GrammarAST, Set<GrammarAST>> entry : modeRules.entrySet()) {
            String modeName = getModeName(entry.getKey());
            GrammarNode.GrammarNodeDescription modeDescription = new GrammarNode.GrammarNodeDescription("_" + modeName);
            modeDescription.setHtmlHeader("mode " + modeName);
            modeDescription.setOffset(snapshot, result.getFileObject(), getElementOffset(entry.getKey()));
            modeDescription.setSpan(getSpan(snapshot, result, entry.getKey()));
            modeDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

            lexerRulesRootDescription.getChildren().add(modeDescription);
            processRules(snapshot, result, entry.getValue(), modeDescription.getChildren(), modeDescription.getChildren());
        }

        GrammarAST tokensSpec = (GrammarAST)parseResult.getFirstDescendantWithType(ANTLRParser.TOKENS_SPEC);
        if (tokensSpec != null) {
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

                    GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(ruleName);
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

                    GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(ruleName);
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

            String ruleName = child.getChild(0).getText();
            if ("Tokens".equals(ruleName)) {
                continue;
            }

            GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(ruleName);
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
