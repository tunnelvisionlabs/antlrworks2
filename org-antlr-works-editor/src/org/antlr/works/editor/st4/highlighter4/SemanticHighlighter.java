/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.st4.highlighter4;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion.Bias;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
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
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.highlighting.HighlightsChangeEvent;
import org.netbeans.spi.editor.highlighting.HighlightsChangeListener;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractHighlightsContainer {

    private static final RequestProcessor RP = new RequestProcessor(SemanticHighlighter.class);

    private final StyledDocument document;
    private final VersionedDocument versionedDocument;
    private final DataListener dataListener;
    private final EditorRegistryListener editorRegistryListener;
    private final OffsetsBag container;

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

    private final Set<JTextComponent> components = new HashSet<JTextComponent>();

    private SemanticHighlighter(@NonNull StyledDocument document) {
        Parameters.notNull("document", document);
        this.document = document;
        this.versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        this.dataListener = new DataListener();
        this.editorRegistryListener = new EditorRegistryListener();
        this.container = new OffsetsBag(document, true);
        this.container.addHighlightsChangeListener(new HighlightsChangeListener() {
            @Override
            public void highlightChanged(HighlightsChangeEvent event) {
                fireHighlightsChange(event.getStartOffset(), event.getEndOffset());
            }
        });

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

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return container.getHighlights(startOffset, endOffset);
    }

    private void initialize() {
    }

    private void addComponent(JTextComponent component) {
        components.add(component);
        if (components.size() == 1) {
            ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
            taskManager.addDataListener(TemplateParserDataDefinitions.REFERENCE_PARSE_TREE, dataListener);
            EditorRegistry.addPropertyChangeListener(editorRegistryListener);
        }
    }

    private void removeComponent(JTextComponent component) {
        if (components.remove(component) && components.isEmpty()) {
            ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
            taskManager.removeDataListener(TemplateParserDataDefinitions.REFERENCE_PARSE_TREE, dataListener);
            EditorRegistry.removePropertyChangeListener(editorRegistryListener);
        }
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            Document document = context.getDocument();
            if (!(document instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            SemanticHighlighter highlighter = (SemanticHighlighter)document.getProperty(SemanticHighlighter.class);
            if (highlighter == null) {
                highlighter = new SemanticHighlighter((StyledDocument)document);
                highlighter.initialize();
                document.putProperty(SemanticHighlighter.class, highlighter);
            }

            highlighter.addComponent(context.getComponent());
            return new HighlightsLayer[] { HighlightsLayer.create(SemanticHighlighter.class.getName(), ZOrder.SYNTAX_RACK.forPosition(3), true, highlighter) };
        }

    }

    private static class SemanticAnalyzerListener extends BlankTemplateParserListener {
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

    private class DataListener implements ParserDataListener<ParserRuleContext<Token>> {

        @Override
        public void dataChanged(ParserDataEvent<ParserRuleContext<Token>> event) {
            final ParserData<ParserRuleContext<Token>> parserData = event.getData();
            if (parserData == null) {
                return;
            }

            final DocumentSnapshot snapshot = parserData.getSnapshot();
            if (snapshot == null || !versionedDocument.equals(snapshot.getVersionedDocument())) {
                return;
            }

            RP.post(new Runnable() {
                @Override
                public void run() {
                    final SemanticAnalyzerListener listener = new SemanticAnalyzerListener();
                    ParseTreeWalker.DEFAULT.walk(listener, parserData.getData());
                    ((BaseDocument)document).runAtomicAsUser(new Runnable() {
                        @Override
                        public void run() {
                            container.clear();
                            DocumentSnapshot currentSnapshot = snapshot.getVersionedDocument().getCurrentSnapshot();
                            addHighlights(container, snapshot, currentSnapshot, listener.getTemplateDeclarations(), templateDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getTemplateUses(), templateUseAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getRegionDeclarations(), regionDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getRegionUses(), regionUseAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getDictionaryDeclarations(), dictionaryDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getDictionaryUses(), dictionaryUseAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getParameterUses(), parameterUseAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getAttributeUses(), attributeUseAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getOptions(), expressionOptionAttributes);
                        }
                    });
                }
            });
        }

        private void addHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
            for (Token token : tokens) {
                TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1), Bias.Forward);
                SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
                container.addHighlight(region.getStart().getOffset(), region.getEnd().getOffset(), attributes);
            }
        }

    }

    private class EditorRegistryListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(EditorRegistry.COMPONENT_REMOVED_PROPERTY)) {
                Object component = evt.getOldValue();
                if (component instanceof JTextComponent) {
                    removeComponent((JTextComponent)component);
                }
            }
        }

    }

}
