/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.openide.util.Parameters;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.FormalArgument;
import org.stringtemplate.v4.misc.Interval;

public class TemplateGroupWrapper extends STGroup {

    private final List<TemplateInformation> templateInformation = new ArrayList<>();

    private final Map<CompiledST, TemplateInformation> compiledTemplateInformation =
        new HashMap<>();

    public TemplateGroupWrapper(char delimiterStartChar, char delimiterStopChar) {
        super(delimiterStartChar, delimiterStopChar);
    }

    public Collection<CompiledST> getCompiledTemplates() {
        return templates.values();
    }

    @Override
    public void defineDictionary(String name, Map<String, Object> mapping) {
        // TODO: handle dictionaries
        super.defineDictionary(name, mapping);
    }

    @Override
    public CompiledST defineRegion(String enclosingTemplateName, Token regionT, String template, Token templateToken) {
        CompiledST result = super.defineRegion(enclosingTemplateName, regionT, template, templateToken);
        if (result == null) {
            return null;
        }

        TemplateInformation info = new TemplateInformation(enclosingTemplateName, (CommonToken)regionT, (CommonToken)templateToken, result);
        templateInformation.add(info);
        compiledTemplateInformation.put(result, info);
        return result;
    }

    @Override
    public CompiledST defineTemplate(String fullyQualifiedTemplateName, Token nameT, List<FormalArgument> args, String template, Token templateToken) {
        CompiledST result = super.defineTemplate(fullyQualifiedTemplateName, nameT, args, template, templateToken);
        if (result == null) {
            return null;
        }

        TemplateInformation info = new TemplateInformation((CommonToken)nameT, (CommonToken)templateToken, result);
        templateInformation.add(info);
        compiledTemplateInformation.put(result, info);
        return result;
    }

    @Override
    public CompiledST defineTemplateAlias(Token aliasT, Token targetT) {
        CompiledST result = super.defineTemplateAlias(aliasT, targetT);
        if (result == null) {
            return null;
        }

        templateInformation.add(new TemplateInformation((CommonToken)aliasT, (CommonToken)targetT, result));
        return result;
    }

    public Collection<TemplateInformation> getTemplateInformation() {
        return templateInformation;
    }

    public TemplateInformation getTemplateInformation(CompiledST template) {
        Parameters.notNull("template", template);

        return compiledTemplateInformation.get(template);
    }

    public static class TemplateInformation {
        private final String enclosingTemplateName;
        private final CommonToken nameToken;
        private final CommonToken templateToken;
        private final CompiledST template;

        public TemplateInformation(CommonToken nameToken, CommonToken templateToken, CompiledST template) {
            this(null, nameToken, templateToken, template);
        }

        public TemplateInformation(String enclosingTemplateName, CommonToken nameToken, CommonToken templateToken, CompiledST template) {
            this.enclosingTemplateName = enclosingTemplateName;
            this.nameToken = nameToken;
            this.templateToken = templateToken;
            this.template = template;
        }

        public String getEnclosingTemplateName() {
            return enclosingTemplateName;
        }

        public CommonToken getNameToken() {
            return nameToken;
        }

        public CommonToken getTemplateToken() {
            return templateToken;
        }

        public CompiledST getTemplate() {
            return template;
        }

        public Interval getGroupInterval() {
            return new Interval(getTemplateToken().getStartIndex(), getTemplateToken().getStopIndex());
        }
    }
}
