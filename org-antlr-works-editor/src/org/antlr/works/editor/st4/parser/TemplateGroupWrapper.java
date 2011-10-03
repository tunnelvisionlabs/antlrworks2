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

    private final List<TemplateInformation> templateInformation = new ArrayList<TemplateInformation>();

    private final Map<CompiledST, TemplateInformation> compiledTemplateInformation =
        new HashMap<CompiledST, TemplateInformation>();

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
        TemplateInformation info = new TemplateInformation(enclosingTemplateName, (CommonToken)regionT, (CommonToken)templateToken, result);
        templateInformation.add(info);
        compiledTemplateInformation.put(result, info);
        return result;
    }

    @Override
    public CompiledST defineTemplate(String fullyQualifiedTemplateName, Token nameT, List<FormalArgument> args, String template, Token templateToken) {
        CompiledST result = super.defineTemplate(fullyQualifiedTemplateName, nameT, args, template, templateToken);
        TemplateInformation info = new TemplateInformation((CommonToken)nameT, (CommonToken)templateToken, result);
        templateInformation.add(info);
        compiledTemplateInformation.put(result, info);
        return result;
    }

    @Override
    public CompiledST defineTemplateAlias(Token aliasT, Token targetT) {
        CompiledST result = super.defineTemplateAlias(aliasT, targetT);
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
