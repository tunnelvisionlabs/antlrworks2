/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.AnonymousTemplateContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.AnonymousTemplateParametersContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.DictDefContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.FormalArgContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.GroupContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.IncludeExprContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.OptionContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.PrimaryContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.TemplateDefContext;
import org.antlr.works.editor.st4.experimental.generated.TemplateParserBaseListener;
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
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener, ParserRuleContext> {

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
    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext> parserData) {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected ParseTree getParseTree(ParserData<? extends ParserRuleContext> parserData) {
        return parserData.getData();
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer = new ArrayList<>();

        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getTemplateDeclarations(), templateDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getTemplateUses(), templateUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getRegionDeclarations(), regionDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getRegionUses(), regionUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getDictionaryDeclarations(), dictionaryDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getDictionaryUses(), dictionaryUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getParameterUses(), parameterUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getAttributeUses(), attributeUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getOptions(), expressionOptionAttributes);

        OffsetsBag container = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        fillHighlights(container, intermediateContainer);
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
        private final Deque<Integer> memberContext = new ArrayDeque<>();
        private final Deque<Set<String>> parameters = new ArrayDeque<>();

        private final List<Token> templateDeclarations = new ArrayList<>();
        private final List<Token> templateUses = new ArrayList<>();
        private final List<Token> regionDeclarations = new ArrayList<>();
        private final List<Token> regionUses = new ArrayList<>();
        private final List<Token> dictionaryDeclarations = new ArrayList<>();
        private final List<Token> dictionaryUses = new ArrayList<>();
        private final List<Token> parameterDeclarations = new ArrayList<>();
        private final List<Token> parameterUses = new ArrayList<>();
        private final List<Token> attributeUses = new ArrayList<>();
        private final List<Token> options = new ArrayList<>();

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
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_dictDef, version=0, dependents=Dependents.PARENTS)
        public void enterDictDef(DictDefContext ctx) {
            if (ctx.name != null) {
                dictionaryDeclarations.add(ctx.name);
            }
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_templateDef, version=3, dependents=Dependents.PARENTS)
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
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_templateDef, version=3, dependents=Dependents.PARENTS)
        public void exitTemplateDef(TemplateDefContext ctx) {
            parameters.pop();
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_formalArg, version=1, dependents=Dependents.PARENTS)
        public void enterFormalArg(FormalArgContext ctx) {
            if (ctx.name != null) {
                parameterDeclarations.add(ctx.name);

                Set<String> currentParameters = parameters.peek();
                currentParameters.add(ctx.name.getText());
            }
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_anonymousTemplate, version=2, dependents=Dependents.PARENTS)
        public void enterAnonymousTemplate(AnonymousTemplateContext ctx) {
            parameters.push(new HashSet<String>());
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_anonymousTemplate, version=2, dependents=Dependents.PARENTS)
        public void exitAnonymousTemplate(AnonymousTemplateContext ctx) {
            parameters.pop();
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_anonymousTemplateParameters, version=0, dependents=Dependents.PARENTS)
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
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_option, version=0, dependents=Dependents.PARENTS)
        public void enterOption(OptionContext ctx) {
            if (ctx.name != null) {
                options.add(ctx.name);
            }
        }

        @Override
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_includeExpr, version=0, dependents=Dependents.PARENTS)
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
        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_primary, version=1, dependents=Dependents.PARENTS)
        public void enterPrimary(PrimaryContext ctx) {
            TerminalNode id = ctx.ID();
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
        //@RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_group, version=0)
        public void exitGroup(GroupContext ctx) {
        }
    }

}
