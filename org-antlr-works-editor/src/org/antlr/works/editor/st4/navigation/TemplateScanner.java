/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.util.Collections;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.works.editor.st4.parser.CompiledFileModel;
import org.antlr.works.editor.st4.parser.CompiledModel;
import org.antlr.works.editor.st4.parser.TemplateGroupRuleReturnScope;
import org.antlr.works.editor.st4.parser.TemplateGroupWrapper;
import org.openide.util.Exceptions;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;

/**
 *
 * @author Sam Harwell
 */
public class TemplateScanner {

    public Description scan(CompiledModel model) {
        TemplatesPanel panel = TemplatesPanel.getInstance();
        TemplatesPanelUI ui = panel != null ? panel.getComponent() : null;
        if (ui == null) {
            return null;
        }

        TemplateNode.TemplateDescription rootDescription = scan(ui, model);
        return rootDescription;
    }

    public TemplateNode.TemplateDescription scan(NavigatorPanelUI ui, CompiledModel model) {
        try {
            // don't update if there were errors and a result is already displayed
            /*if (!result.getParser().getSyntaxErrors().isEmpty() && !ui.isShowingWaitNode()) {
                return;
            }*/

            TemplateNode.TemplateDescription rootDescription = new TemplateNode.TemplateDescription();
            rootDescription.setFileObject(model.getSnapshot().getVersionedDocument().getFileObject());

            for (CompiledFileModel importedParseResult : model.getImportedGroupResults()) {
                processParseResult(null, importedParseResult, ui, rootDescription);
            }

            processParseResult(model.getSnapshot(), model.getResult(), ui, rootDescription);
            return rootDescription;
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    private void processParseResult(DocumentSnapshot snapshot,
                                    CompiledFileModel result,
                                    NavigatorPanelUI ui,
                                    TemplateNode.TemplateDescription rootDescription) {

        TemplateGroupRuleReturnScope parseResult = result.getResult();

        if (parseResult != null) {
            for (TemplateGroupWrapper.TemplateInformation templateInfo : parseResult.getGroup().getTemplateInformation()) {
                CompiledST template = templateInfo.getTemplate();
                Interval sourceInterval = templateInfo.getGroupInterval();

                if (template.isAnonSubtemplate) {
                    continue;
                }

                boolean isRegion = templateInfo.getEnclosingTemplateName() != null && templateInfo.getEnclosingTemplateName().length() > 0;
                if (isRegion)
                {
                    String sig = String.format("%s.%s()", templateInfo.getEnclosingTemplateName(), templateInfo.getNameToken().getText());

                    TemplateNode.TemplateDescription description = new TemplateNode.TemplateDescription(sig);
                    description.setOffset(snapshot, rootDescription.getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(String.format("%s.%s<font color='808080'>()</font>", Description.htmlEscape(templateInfo.getEnclosingTemplateName()), Description.htmlEscape(templateInfo.getNameToken().getText())));
                    rootDescription.getChildren().add(description);
                }
                else
                {
                    // always pull the name from the templateInfo because the template itself could be an aliased template
                    String name = templateInfo.getNameToken().getText();
                    Iterable<String> argumentNames = template.formalArguments != null ? template.formalArguments.keySet() : Collections.<String>emptyList();
                    String sig = String.format("%s(%s)", name, Misc.join(argumentNames.iterator(), ", "));

                    TemplateNode.TemplateDescription description = new TemplateNode.TemplateDescription(sig);
                    description.setOffset(snapshot, rootDescription.getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(String.format("%s<font color='808080'>(%s)</font>", Description.htmlEscape(name), Description.htmlEscape(Misc.join(argumentNames.iterator(), ", "))));
                    rootDescription.getChildren().add(description);
                }
            }
        }
    }

}
