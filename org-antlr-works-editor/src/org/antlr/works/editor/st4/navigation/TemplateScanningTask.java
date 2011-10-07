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

package org.antlr.works.editor.st4.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.text.Position;
import org.antlr.netbeans.editor.navigation.CurrentDocumentStateScheduler;
import org.antlr.works.editor.st4.navigation.TemplateNode.Description;
import org.antlr.works.editor.st4.parser.TemplateGroupWrapper.TemplateInformation;
import org.antlr.works.editor.st4.parser.TemplateParser.TemplateGroupRuleReturnScope;
import org.antlr.works.editor.st4.parser.TemplateParser.TemplateParserResult;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;

public class TemplateScanningTask extends ParserResultTask<TemplateParserResult> {

    private static final Logger LOG = Logger.getLogger(TemplateScanningTask.class.getName());
    private static final String TYPE_COLOR = "#707070";
    private static final String INHERITED_COLOR = "#7D694A";
    private final AtomicBoolean cancel = new AtomicBoolean();

    public TemplateScanningTask() {
    }

    @Override
    public void run(TemplateParserResult result, SchedulerEvent event) {
        try {
            TemplatesPanelUI ui = findTemplateRulesPanelUI();
            if (ui == null) {
                return;
            }

            // don't update if there were errors and a result is already displayed
            if (!result.getParser().getSyntaxErrors().isEmpty() && !ui.isShowingWaitNode()) {
                return;
            }

            Description rootDescription = new Description(ui);
            rootDescription.fileObject = result.getSnapshot().getSource().getFileObject();
            rootDescription.children = new ArrayList<Description>();

            TemplateGroupRuleReturnScope parseResult = result.getResult();

            if (result != null) {
                for (TemplateInformation templateInfo : parseResult.getGroup().getTemplateInformation()) {
                    CompiledST template = templateInfo.getTemplate();
                    Interval sourceInterval = templateInfo.getGroupInterval();

                    if (template.isAnonSubtemplate) {
                        continue;
                    }

                    boolean isRegion = templateInfo.getEnclosingTemplateName() != null && templateInfo.getEnclosingTemplateName().length() > 0;
                    if (isRegion)
                    {
                        String sig = String.format("%s.%s()", templateInfo.getEnclosingTemplateName(), templateInfo.getNameToken().getText());

                        Description description = new Description(ui, sig);
                        description.fileObject = rootDescription.fileObject;
                        description.setOffset(result.getSnapshot(), sourceInterval.a);
                        description.htmlHeader = String.format("%s.%s<font color='808080'>()</font>", templateInfo.getEnclosingTemplateName(), templateInfo.getNameToken().getText());
                        rootDescription.children.add(description);

                        //string sig = string.Format("{0}({1})", name, string.Join(", ", args));
                        /*EditorNavigationType navigationType = EditorNavigationTypeRegistryService.GetEditorNavigationType(StringTemplateEditorNavigationTypes.Templates);
                        Interval sourceInterval = templateInfo.GroupInterval;
                        SnapshotSpan span = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, sourceInterval.Length));
                        SnapshotSpan seek = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, 0));
                        ImageSource glyph = _provider.GlyphService.GetGlyph(StandardGlyphGroup.GlyphGroupNamespace, StandardGlyphItem.GlyphItemPublic);
                        NavigationTargetStyle style = NavigationTargetStyle.None;
                        navigationTargets.Add(new EditorNavigationTarget(sig, navigationType, span, seek, glyph, style));*/
                    }
                    else
                    {
                        // always pull the name from the templateInfo because the template itself could be an aliased template
                        String name = templateInfo.getNameToken().getText();
                        Iterable<String> argumentNames = template.formalArguments != null ? template.formalArguments.keySet() : Collections.<String>emptyList();
                        String sig = String.format("%s(%s)", name, Misc.join(argumentNames.iterator(), ", "));

                        Description description = new Description(ui, sig);
                        description.fileObject = rootDescription.fileObject;
                        description.setOffset(result.getSnapshot(), sourceInterval.a);
                        description.htmlHeader = String.format("%s<font color='808080'>(%s)</font>", name, Misc.join(argumentNames.iterator(), ", "));
                        rootDescription.children.add(description);

                        /*string name = templateInfo.NameToken.Text;
                        IEnumerable<string> args = template.FormalArguments != null ? template.FormalArguments.Select(i => i.Name) : Enumerable.Empty<string>();
                        string sig = string.Format("{0}({1})", name, string.Join(", ", args));
                        IEditorNavigationType navigationType = EditorNavigationTypeRegistryService.GetEditorNavigationType(StringTemplateEditorNavigationTypes.Templates);
                        Interval sourceInterval = templateInfo.GroupInterval;
                        SnapshotSpan span = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, sourceInterval.Length));
                        SnapshotSpan seek = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, 0));
                        bool isAlias = false;
                        StandardGlyphGroup glyphGroup = isAlias ? StandardGlyphGroup.GlyphGroupTypedef : StandardGlyphGroup.GlyphGroupTemplate;
                        ImageSource glyph = _provider.GlyphService.GetGlyph(StandardGlyphGroup.GlyphGroupTemplate, StandardGlyphItem.GlyphItemPublic);
                        NavigationTargetStyle style = NavigationTargetStyle.None;
                        navigationTargets.Add(new EditorNavigationTarget(sig, navigationType, span, seek, glyph, style));*/
                    }
                }

                //foreach (var dictionaryInfo in result.Group.GetDictionaryInformation())
                //{
                //    string name = dictionaryInfo.Name;
                //    IEditorNavigationType navigationType = EditorNavigationTypeRegistryService.GetEditorNavigationType(PredefinedEditorNavigationTypes.Members);
                //    Interval sourceInterval = dictionaryInfo.GroupInterval;
                //    SnapshotSpan span = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, sourceInterval.Length));
                //    SnapshotSpan seek = new SnapshotSpan(e.Snapshot, new Span(sourceInterval.Start, 0));
                //    ImageSource glyph = _provider.GetGlyph(StandardGlyphGroup.GlyphGroupModule, StandardGlyphItem.GlyphItemPublic);
                //    NavigationTargetStyle style = NavigationTargetStyle.None;
                //    navigationTargets.Add(new EditorNavigationTarget(sig, navigationType, span, seek, glyph, style));
                //}
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
    private static final String CONTENT_TYPE = "text/x-stringtemplate4";
    private static final Lookup.Template<NavigatorPanel> NAV_PANEL_TEMPLATE = new Lookup.Template<NavigatorPanel>(NavigatorPanel.class);

    private TemplatesPanelUI findTemplateRulesPanelUI() {
        final TemplatesPanelUI[] result = new TemplatesPanelUI[1];
        if (SwingUtilities.isEventDispatchThread()) {
            findTemplateRulesPanelUI(result);
        } else {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        findTemplateRulesPanelUI(result);
                    }
                });
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            } catch (InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }

        return result[0];
    }

    private void findTemplateRulesPanelUI(final TemplatesPanelUI[] result) {
        result[0] = null;

        String path = PANELS_FOLDER + CONTENT_TYPE;
        Lookup.Result<NavigatorPanel> lookupResult = Lookups.forPath(path).lookup(NAV_PANEL_TEMPLATE);
        Collection<? extends NavigatorPanel> panels = lookupResult.allInstances();
        assert panels.size() <= 1;
        if (panels.isEmpty()) {
            return;
        }

        NavigatorPanel panel = panels.iterator().next();
        assert panel instanceof TemplatesPanel;
        if (!(panel instanceof TemplatesPanel)) {
            return;
        }

        TemplatesPanel grammarRulesPanel = (TemplatesPanel)panel;
        JComponent component = grammarRulesPanel.getComponent();
        assert component == null || component instanceof TemplatesPanelUI;
        if (!(component instanceof TemplatesPanelUI)) {
            return;
        }

        result[0] = (TemplatesPanelUI)component;
    }
}
