/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.TemplateParser.AnonymousTemplateContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.AnonymousTemplateParametersContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.DictDefContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.FormalArgContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.GroupContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.IncludeExprContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.OptionContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.PrimaryContext;
import org.antlr.works.editor.st4.experimental.TemplateParser.TemplateDefContext;
import org.antlr.works.editor.st4.experimental.TemplateParserBaseListener;
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
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener, ParserRuleContext<Token>> {

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
    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected ParseTree<Token> getParseTree(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return parserData.getData();
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        OffsetsBag container = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
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
        targetContainer.setHighlights(container);
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

    public static class SemanticAnalyzerListener extends TemplateParserBaseListener {
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
        public void enterDictDef(DictDefContext ctx) {
            if (ctx.name != null) {
                dictionaryDeclarations.add(ctx.name);
            }
        }

        @Override
        public void enterTemplateDef(TemplateDefContext ctx) {
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
        public void exitTemplateDef(TemplateDefContext ctx) {
            parameters.pop();
        }

        @Override
        public void enterFormalArg(FormalArgContext ctx) {
            if (ctx.name != null) {
                parameterDeclarations.add(ctx.name);

                Set<String> currentParameters = parameters.peek();
                currentParameters.add(ctx.name.getText());
            }
        }

        @Override
        public void enterAnonymousTemplate(AnonymousTemplateContext ctx) {
            parameters.push(new HashSet<String>());
        }

        @Override
        public void exitAnonymousTemplate(AnonymousTemplateContext ctx) {
            parameters.pop();
        }

        @Override
        public void enterAnonymousTemplateParameters(AnonymousTemplateParametersContext ctx) {
            if (ctx.names != null) {
                parameterDeclarations.addAll(ctx.names);

                Set<String> currentParameters = parameters.peek();
                for (Token token : ctx.names) {
                    currentParameters.add(token.getText());
                }
            }
        }

        @Override
        public void enterOption(OptionContext ctx) {
            if (ctx.name != null) {
                options.add(ctx.name);
            }
        }

        @Override
        public void enterIncludeExpr(IncludeExprContext ctx) {
            if (ctx.templateName != null) {
                if (ctx.AT() != null) {
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
        public void enterPrimary(PrimaryContext ctx) {
            TerminalNode<Token> id = ctx.ID();
            if (id != null) {
                Set<String> currentParameters = parameters.isEmpty() ? null : parameters.peek();
                if (currentParameters != null && currentParameters.contains(id.getSymbol().getText())) {
                    parameterUses.add(id.getSymbol());
                } else {
                    attributeUses.add(id.getSymbol());
                }
            }
        }

        @Override
        public void exitGroup(GroupContext ctx) {
        }
    }

}
