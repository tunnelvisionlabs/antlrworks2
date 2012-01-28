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
package org.antlr.works.editor.st4.highlighter4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.BlankTemplateParserListener;
import org.antlr.works.editor.st4.experimental.TemplateParser.anonymousTemplateContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.anonymousTemplateParametersContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.dictDefContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.formalArgContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.groupContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.includeExprContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.optionContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.primaryContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.templateDefContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener> {

    private final AttributeSet templateDeclarationAttributes;
    private final AttributeSet templateUseAttributes;
    private final AttributeSet regionDeclarationAttributes;
    private final AttributeSet regionUseAttributes;
    private final AttributeSet dictionaryDeclarationAttributes;
    private final AttributeSet dictionaryUseAttributes;
    private final AttributeSet parameterDeclarationAttributes;
    private final AttributeSet parameterUseAttributes;
    private final AttributeSet attributeUseAttributes;
    private final AttributeSet expressionOptionAttributes;

    private SemanticHighlighter(@NonNull StyledDocument document) {
        super(document, TemplateParserDataDefinitions.REFERENCE_PARSE_TREE);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(StringTemplateEditorKit.TEMPLATE_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        this.templateDeclarationAttributes = getFontAndColors(settings, "templateDeclaration");
        this.templateUseAttributes = getFontAndColors(settings, "templateUse");
        this.regionDeclarationAttributes = getFontAndColors(settings, "regionDeclaration");
        this.regionUseAttributes = getFontAndColors(settings, "regionUse");
        this.dictionaryDeclarationAttributes = getFontAndColors(settings, "dictionaryDeclaration");
        this.dictionaryUseAttributes = getFontAndColors(settings, "dictionaryUse");
        this.parameterDeclarationAttributes = getFontAndColors(settings, "parameterDeclaration");
        this.parameterUseAttributes = getFontAndColors(settings, "parameterUse");
        this.attributeUseAttributes = getFontAndColors(settings, "attributeUse");
        this.expressionOptionAttributes = getFontAndColors(settings, "expressionOption");
    }

    @Override
    protected SemanticAnalyzerListener createListener() {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getTemplateDeclarations(), templateDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getTemplateUses(), templateUseAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getRegionDeclarations(), regionDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getRegionUses(), regionUseAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getDictionaryDeclarations(), dictionaryDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getDictionaryUses(), dictionaryUseAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getParameterUses(), parameterUseAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getAttributeUses(), attributeUseAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getOptions(), expressionOptionAttributes);
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory extends AbstractLayerFactory {

        public LayerFactory() {
            super(SemanticHighlighter.class);
        }

        @Override
        protected AbstractSemanticHighlighter<?> createHighlighter(Context context) {
            return new SemanticHighlighter((StyledDocument)context.getDocument());
        }

    }

    public static class SemanticAnalyzerListener extends BlankTemplateParserListener {
        private final Deque<Integer> memberContext = new ArrayDeque<Integer>();
        private final Deque<Set<String>> parameters = new ArrayDeque<Set<String>>();

        private final List<Token> templateDeclarations = new ArrayList<Token>();
        private final List<Token> templateUses = new ArrayList<Token>();
        private final List<Token> regionDeclarations = new ArrayList<Token>();
        private final List<Token> regionUses = new ArrayList<Token>();
        private final List<Token> dictionaryDeclarations = new ArrayList<Token>();
        private final List<Token> dictionaryUses = new ArrayList<Token>();
        private final List<Token> parameterDeclarations = new ArrayList<Token>();
        private final List<Token> parameterUses = new ArrayList<Token>();
        private final List<Token> attributeUses = new ArrayList<Token>();
        private final List<Token> options = new ArrayList<Token>();

        public List<Token> getTemplateDeclarations() {
            return templateDeclarations;
        }

        public List<Token> getTemplateUses() {
            return templateUses;
        }

        public List<Token> getRegionDeclarations() {
            return regionDeclarations;
        }

        public List<Token> getRegionUses() {
            return regionUses;
        }

        public List<Token> getDictionaryDeclarations() {
            return dictionaryDeclarations;
        }

        public List<Token> getDictionaryUses() {
            return dictionaryUses;
        }

        public List<Token> getParameterDeclarations() {
            return parameterDeclarations;
        }

        public List<Token> getParameterUses() {
            return parameterUses;
        }

        public List<Token> getAttributeUses() {
            return attributeUses;
        }

        public List<Token> getOptions() {
            return options;
        }

        @Override
        public void enterRule(dictDefContext ctx) {
            if (ctx.name != null) {
                dictionaryDeclarations.add(ctx.name);
            }
        }

        @Override
        public void enterRule(templateDefContext ctx) {
            parameters.push(new HashSet<String>());

            if (ctx.name != null) {
                if (ctx.enclosing != null) {
                    regionDeclarations.add(ctx.name);
                    templateUses.add(ctx.enclosing);
                } else {
                    templateDeclarations.add(ctx.name);
                }
            }

            if (ctx.alias != null) {
                templateDeclarations.add(ctx.alias);
            }

            if (ctx.target != null) {
                templateUses.add(ctx.target);
            }
        }

        @Override
        public void exitRule(templateDefContext ctx) {
            parameters.pop();
        }

        @Override
        public void enterRule(formalArgContext ctx) {
            if (ctx.name != null) {
                parameterDeclarations.add(ctx.name);

                Set<String> currentParameters = parameters.peek();
                currentParameters.add(ctx.name.getText());
            }
        }

        @Override
        public void enterRule(anonymousTemplateContext ctx) {
            parameters.push(new HashSet<String>());
        }

        @Override
        public void exitRule(anonymousTemplateContext ctx) {
            parameters.pop();
        }

        @Override
        public void enterRule(anonymousTemplateParametersContext ctx) {
            if (ctx.names_list != null) {
                parameterDeclarations.addAll(ctx.names_list);

                Set<String> currentParameters = parameters.peek();
                for (Token token : ctx.names_list) {
                    currentParameters.add(token.getText());
                }
            }
        }

        @Override
        public void enterRule(optionContext ctx) {
            if (ctx.name != null) {
                options.add(ctx.name);
            }
        }

        @Override
        public void enterRule(includeExprContext ctx) {
            if (ctx.templateName != null) {
                if (ctx.at != null) {
                    regionUses.add(ctx.templateName);
                } else {
                    templateUses.add(ctx.templateName);
                }
            }

            if (ctx.regionName != null) {
                regionUses.add(ctx.regionName);
            }
        }

        @Override
        public void enterRule(primaryContext ctx) {
            if (ctx.id != null) {
                Set<String> currentParameters = parameters.isEmpty() ? null : parameters.peek();
                if (currentParameters != null && currentParameters.contains(ctx.id.getText())) {
                    parameterUses.add(ctx.id);
                } else {
                    attributeUses.add(ctx.id);
                }
            }
        }

        @Override
        public void exitRule(groupContext ctx) {
        }
    }

}
