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
package org.antlr.works.editor.grammar.navigation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.v4.parse.ANTLRParser;
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
            rootDescription.setChildren(new ArrayList<Description>());
            rootDescription.setFileObject(model.getSnapshot().getVersionedDocument().getFileObject());

            GrammarNode.GrammarNodeDescription parserRulesRootDescription = new GrammarNode.GrammarNodeDescription("1" + Bundle.LBL_ParserRules());
            parserRulesRootDescription.setHtmlHeader(Bundle.LBL_ParserRules());
            parserRulesRootDescription.setChildren(new HashSet<Description>());

            GrammarNode.GrammarNodeDescription lexerRulesRootDescription = new GrammarNode.GrammarNodeDescription("2" + Bundle.LBL_LexerRules());
            lexerRulesRootDescription.setHtmlHeader(Bundle.LBL_LexerRules());
            lexerRulesRootDescription.setChildren(new HashSet<Description>());

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
        FileObject fileObject = result.getFileObject();

        List<GrammarAST> modes = parseResult.getNodesWithType(ANTLRParser.MODE);
        // TODO: handle modes

        List<GrammarAST> rules = parseResult.getNodesWithType(ANTLRParser.RULE);
        if (rules != null) {
            for (GrammarAST child : rules) {
                if (child.getChild(0) instanceof GrammarASTErrorNode) {
                    continue;
                }

                String ruleName = child.getChild(0).getText();
                if ("Tokens".equals(ruleName)) {
                    continue;
                }

                GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(ruleName);
                ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)((CommonTree)child.getChild(0)).getToken()).getStartIndex());
                ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                if (Character.isLowerCase(ruleName.charAt(0))) {
                    parserRulesRootDescription.getChildren().add(ruleDescription);
                } else {
                    lexerRulesRootDescription.getChildren().add(ruleDescription);
                }
            }
        }

        GrammarAST tokensSpec = (GrammarAST)parseResult.getFirstDescendantWithType(ANTLRParser.TOKENS);
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
                    ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)((CommonTree)child.getChild(0)).getToken()).getStartIndex());
                    ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                    if (Character.isLowerCase(ruleName.charAt(0))) {
                        parserRulesRootDescription.getChildren().add(ruleDescription);
                    } else {
                        lexerRulesRootDescription.getChildren().add(ruleDescription);
                    }
                } else if (child.getChildCount() == 0 && child.getToken() != null) {
                    String ruleName = child.getText();
                    if (ruleName == null || ruleName.length() == 0) {
                        continue;
                    }

                    GrammarNode.GrammarNodeDescription ruleDescription = new GrammarNode.GrammarNodeDescription(ruleName);
                    ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)child.getToken()).getStartIndex());
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
