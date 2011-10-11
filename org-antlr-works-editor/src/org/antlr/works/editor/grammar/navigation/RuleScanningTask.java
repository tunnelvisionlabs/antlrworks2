/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.antlr.works.editor.grammar.navigation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.netbeans.editor.navigation.CurrentDocumentStateScheduler;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.works.editor.grammar.navigation.GrammarNode.GrammarNodeDescription;
import org.antlr.works.editor.grammar.parser.GrammarParser;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarFileResult;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

public class RuleScanningTask extends ParserResultTask<GrammarParser.GrammarParserResult> {

    private static final Logger LOG = Logger.getLogger(RuleScanningTask.class.getName());
    private static final String TYPE_COLOR = "#707070";
    private static final String INHERITED_COLOR = "#7D694A";
    private final AtomicBoolean cancel = new AtomicBoolean();

    public RuleScanningTask() {
    }

    @Override
    public void run(GrammarParserResult result, SchedulerEvent event) {
        try {
            GrammarRulesPanelUI ui = GrammarRulesPanel.findGrammarRulesPanelUI();
            if (ui == null) {
                return;
            }

            // don't update if there were errors and a result is already displayed
            if (!result.getParser().getSyntaxErrors().isEmpty() && !ui.isShowingWaitNode()) {
                return;
            }

            GrammarNodeDescription rootDescription = new GrammarNodeDescription(ui);
            rootDescription.setChildren(new ArrayList<Description>());
            rootDescription.setFileObject(result.getSnapshot().getSource().getFileObject());

            GrammarNodeDescription parserRulesRootDescription = new GrammarNodeDescription(ui, NbBundle.getMessage(RuleScanningTask.class, "LBL_ParserRules"));
            parserRulesRootDescription.setChildren(new HashSet<Description>());

            GrammarNodeDescription lexerRulesRootDescription = new GrammarNodeDescription(ui, NbBundle.getMessage(RuleScanningTask.class, "LBL_LexerRules"));
            lexerRulesRootDescription.setChildren(new HashSet<Description>());

            for (GrammarFileResult importedParseResult : result.getImportedGrammarResults()) {
                processParseResult(null, importedParseResult, ui, rootDescription, parserRulesRootDescription, lexerRulesRootDescription);
            }

            processParseResult(result.getSnapshot(), result.getResult(), ui, rootDescription, parserRulesRootDescription, lexerRulesRootDescription);

            if (!parserRulesRootDescription.getChildren().isEmpty()) {
                rootDescription.getChildren().add(parserRulesRootDescription);
            }

            if (!lexerRulesRootDescription.getChildren().isEmpty()) {
                rootDescription.getChildren().add(lexerRulesRootDescription);
            }

            ui.refresh(rootDescription);
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void processParseResult(Snapshot snapshot, GrammarFileResult result, GrammarRulesPanelUI ui, GrammarNodeDescription rootDescription, GrammarNodeDescription parserRulesRootDescription, GrammarNodeDescription lexerRulesRootDescription) {

        grammar__return parseResult = result.getResult();
        FileObject fileObject = result.getFileObject();
        Tree tree = (Tree)parseResult.getTree();
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

                GrammarNodeDescription ruleDescription = new GrammarNodeDescription(ui, ruleName);
                ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)((CommonTree)child.getChild(0)).getToken()).getStartIndex());
                ruleDescription.setInherited(snapshot == null); // for now, go on the fact that snapshots aren't available for imported files

                if (Character.isLowerCase(ruleName.charAt(0))) {
                    parserRulesRootDescription.getChildren().add(ruleDescription);
                } else {
                    lexerRulesRootDescription.getChildren().add(ruleDescription);
                }
            } else if (child.getText() != null && child.getText().startsWith("tokens")) {
                for (int j = 0; j < child.getChildCount(); j++) {
                    Tree tokenChild = child.getChild(j);
                    if ("=".equals(tokenChild.getText()) && tokenChild.getChildCount() == 2) {
                        String ruleName = tokenChild.getChild(0).getText();
                        if (ruleName == null || ruleName.length() == 0) {
                            continue;
                        }

                        GrammarNodeDescription ruleDescription = new GrammarNodeDescription(ui, ruleName);
                        ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)((CommonTree)tokenChild.getChild(0)).getToken()).getStartIndex());
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

                        GrammarNodeDescription ruleDescription = new GrammarNodeDescription(ui, ruleName);
                        ruleDescription.setOffset(snapshot, fileObject, ((CommonToken)((CommonTree)tokenChild).getToken()).getStartIndex());
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

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        //return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
        return CurrentDocumentStateScheduler.class;
    }

    @Override
    public void cancel() {
        cancel.set(true);
    }
}
