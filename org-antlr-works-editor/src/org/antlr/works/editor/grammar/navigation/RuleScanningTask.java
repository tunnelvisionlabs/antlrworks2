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
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.netbeans.editor.navigation.CurrentDocumentStateScheduler;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.works.editor.grammar.navigation.GrammarNode.Description;
import org.antlr.works.editor.grammar.parser.ANTLRErrorProvidingParser;
import org.antlr.works.editor.grammar.parser.GrammarParser;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

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
            GrammarRulesPanelUI ui = findGrammarRulesPanelUI();            
            if (ui == null) {
                return;
            }

            Description rootDescription = new Description(ui);
            rootDescription.fileObject = result.getSnapshot().getSource().getFileObject();
            rootDescription.children = new ArrayList<Description>();

            Description parserRulesRootDescription = new Description(ui, NbBundle.getMessage(RuleScanningTask.class, "LBL_ParserRules"));
            parserRulesRootDescription.fileObject = rootDescription.fileObject;
            parserRulesRootDescription.children = new HashSet<Description>();

            Description lexerRulesRootDescription = new Description(ui, NbBundle.getMessage(RuleScanningTask.class, "LBL_LexerRules"));
            lexerRulesRootDescription.fileObject = rootDescription.fileObject;
            lexerRulesRootDescription.children = new HashSet<Description>();

            grammar__return parseResult = result.getResult();
            Tree tree = (Tree)parseResult.getTree();
            ANTLRErrorProvidingParser.grammar_Adaptor adaptor = new ANTLRErrorProvidingParser.grammar_Adaptor(result.getParser());
            int childCount = adaptor.getChildCount(tree);
            for (int i = 0; i < childCount; i++) {
                Object childObject = adaptor.getChild(tree, i);
                if (!(childObject instanceof CommonTree)) {
                    continue;
                }

                CommonTree child = (CommonTree) childObject;
                if (child.getChildCount() > 0 && "rule".equals(child.getText())) {
                    String ruleName = child.getChild(0).getText();
                    if ("Tokens".equals(ruleName)) {
                        continue;
                    }

                    Description ruleDescription = new Description(ui, ruleName);
                    ruleDescription.fileObject = rootDescription.fileObject;
                    ruleDescription.pos = ((CommonToken)((CommonTree)child.getChild(0)).getToken()).getStartIndex();
                    if (Character.isLowerCase(ruleName.charAt(0))) {
                        parserRulesRootDescription.children.add(ruleDescription);
                    } else {
                        lexerRulesRootDescription.children.add(ruleDescription);
                    }
                } else if (child.getText() != null && child.getText().startsWith("tokens")) {
                    for (int j = 0; j < child.getChildCount(); j++) {
                        Tree tokenChild = child.getChild(j);
                        if ("=".equals(tokenChild.getText()) && tokenChild.getChildCount() == 2) {
                            String ruleName = tokenChild.getChild(0).getText();
                            if (ruleName == null || ruleName.length() == 0) {
                                continue;
                            }

                            Description ruleDescription = new Description(ui, ruleName);
                            ruleDescription.fileObject = rootDescription.fileObject;
                            ruleDescription.pos = ((CommonToken)((CommonTree)tokenChild.getChild(0)).getToken()).getStartIndex();
                            if (Character.isLowerCase(ruleName.charAt(0))) {
                                parserRulesRootDescription.children.add(ruleDescription);
                            } else {
                                lexerRulesRootDescription.children.add(ruleDescription);
                            }
                        } else if (tokenChild.getChildCount() == 0) {
                            String ruleName = tokenChild.getText();
                            if (ruleName == null || ruleName.length() == 0) {
                                continue;
                            }

                            Description ruleDescription = new Description(ui, ruleName);
                            ruleDescription.fileObject = rootDescription.fileObject;
                            ruleDescription.pos = ((CommonToken)((CommonTree)tokenChild).getToken()).getStartIndex();
                            if (Character.isLowerCase(ruleName.charAt(0))) {
                                parserRulesRootDescription.children.add(ruleDescription);
                            } else {
                                lexerRulesRootDescription.children.add(ruleDescription);
                            }
                        }
                    }
                }
            }

            if (parserRulesRootDescription.children.size() > 0) {
                rootDescription.children.add(parserRulesRootDescription);
            }

            if (lexerRulesRootDescription.children.size() > 0) {
                rootDescription.children.add(lexerRulesRootDescription);
            }

            ui.refresh(rootDescription);
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
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

    private static final String PANELS_FOLDER = "/Navigator/Panels/";
    private static final String CONTENT_TYPE = "text/x-antlr3";
    private static final Lookup.Template<NavigatorPanel> NAV_PANEL_TEMPLATE = new Lookup.Template<NavigatorPanel>(NavigatorPanel.class);

    private GrammarRulesPanelUI findGrammarRulesPanelUI() {
        String path = PANELS_FOLDER + CONTENT_TYPE;
        Lookup.Result<NavigatorPanel> result = Lookups.forPath(path).lookup(NAV_PANEL_TEMPLATE);
        Collection<? extends NavigatorPanel> panels = result.allInstances();
        assert panels.size() <= 1;
        if (panels.isEmpty()) {
            return null;
        }

        NavigatorPanel panel = panels.iterator().next();
        assert panel instanceof GrammarRulesPanel;
        if (!(panel instanceof GrammarRulesPanel)) {
            return null;
        }

        GrammarRulesPanel grammarRulesPanel = (GrammarRulesPanel)panel;
        JComponent component = grammarRulesPanel.getComponent();
        assert component == null || component instanceof GrammarRulesPanelUI;
        if (!(component instanceof GrammarRulesPanelUI)) {
            return null;
        }

        return (GrammarRulesPanelUI)component;
    }
}
