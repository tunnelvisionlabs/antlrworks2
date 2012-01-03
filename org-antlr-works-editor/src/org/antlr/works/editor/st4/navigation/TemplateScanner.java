/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.st4.navigation;

import java.util.ArrayList;
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
        TemplatesPanelUI ui = TemplatesPanel.findTemplatesPanelUI();
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
            rootDescription.setChildren(new ArrayList<Description>());
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

        if (result != null) {
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
                    description.setHtmlHeader(String.format("%s.%s<font color='808080'>()</font>", templateInfo.getEnclosingTemplateName(), templateInfo.getNameToken().getText()));
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
                    description.setHtmlHeader(String.format("%s<font color='808080'>(%s)</font>", name, Misc.join(argumentNames.iterator(), ", ")));
                    rootDescription.getChildren().add(description);
                }
            }
        }
    }

}
