/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.tool.Grammar;
import org.antlr.works.editor.grammar.parser.CompiledFileModelV3;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.parser.CompiledModelV3;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class RuleScannerV3 extends RuleScanner {

    @Override
    public GrammarNode.GrammarNodeDescription scanImpl(CompiledModel baseModel) {

        try {
            CompiledModelV3 model = (CompiledModelV3)baseModel;

//            GrammarRulesPanelUI ui = GrammarRulesPanel.findGrammarRulesPanelUI();
//            if (ui == null) {
//                return;
//            }

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

            for (CompiledFileModelV3 importedParseResult : model.getImportedGrammarResults()) {
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
                                    CompiledFileModelV3 result,
                                    GrammarNode.GrammarNodeDescription parserRulesRootDescription,
                                    GrammarNode.GrammarNodeDescription lexerRulesRootDescription) {

        ANTLRParser.grammar__return parseResult = result.getResult();
        if (parseResult == null) {
            return;
        }

        FileObject fileObject = result.getFileObject();
        Tree tree = parseResult.getTree();
        int childCount = tree.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Object childObject = tree.getChild(i);
            if (!(childObject instanceof CommonTree)) {
                continue;
            }

            CommonTree child = (CommonTree) childObject;
            if (child.getChildCount() > 0 && "rule".equals(child.getText())) {
                String ruleName = child.getChild(0).getText();
                if ("Tokens".equals(ruleName)) {
                    continue;
                }

                DeclarationKind declarationKind;
                if (org.antlr.v4.tool.Grammar.isTokenName(ruleName)) {
                    declarationKind = DeclarationKind.LEXER_RULE;
                } else {
                    declarationKind = DeclarationKind.PARSER_RULE;
                }

                GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(declarationKind, ruleName);
                ruleDescription.setOffset(snapshot, fileObject, getElementOffset(child));
                ruleDescription.setSpan(getSpan(snapshot, result, child));
                ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                if (Character.isLowerCase(ruleName.charAt(0))) {
                    parserRulesRootDescription.getChildren().add(ruleDescription);
                } else {
                    lexerRulesRootDescription.getChildren().add(ruleDescription);
                }
            } else if (child.getText() != null && child.getText().startsWith("tokens")) {
                for (int j = 0; j < child.getChildCount(); j++) {
                    CommonTree tokenChild = (CommonTree)child.getChild(j);
                    if ("=".equals(tokenChild.getText()) && tokenChild.getChildCount() == 2) {
                        String ruleName = tokenChild.getChild(0).getText();
                        if (ruleName == null || ruleName.length() == 0) {
                            continue;
                        }

                        GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.TOKEN, ruleName);
                        ruleDescription.setOffset(snapshot, fileObject, getElementOffset(tokenChild));
                        ruleDescription.setSpan(getSpan(snapshot, result, child));
                        ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                        if (Character.isLowerCase(ruleName.charAt(0))) {
                            parserRulesRootDescription.getChildren().add(ruleDescription);
                        } else {
                            lexerRulesRootDescription.getChildren().add(ruleDescription);
                        }
                    } else if (tokenChild.getChildCount() == 0) {
                        String ruleName = tokenChild.getText();
                        if (ruleName == null || ruleName.length() == 0) {
                            continue;
                        }

                        GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(DeclarationKind.TOKEN, ruleName);
                        ruleDescription.setOffset(snapshot, fileObject, getElementOffset(tokenChild));
                        ruleDescription.setSpan(getSpan(snapshot, result, child));
                        ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                        if (Character.isLowerCase(ruleName.charAt(0))) {
                            parserRulesRootDescription.getChildren().add(ruleDescription);
                        } else {
                            lexerRulesRootDescription.getChildren().add(ruleDescription);
                        }
                    }
                }
            }
        }
    }

    private int getElementOffset(CommonTree tree) {
        switch (tree.getType()) {
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

        case ANTLRParser.TOKEN_REF:
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
