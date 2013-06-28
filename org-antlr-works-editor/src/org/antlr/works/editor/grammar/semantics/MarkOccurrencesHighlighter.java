/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.editor.errorstripe.privatespi.Mark;
import org.netbeans.modules.editor.errorstripe.privatespi.MarkProviderCreator;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.WeakListeners;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ERROR_STRIPE_TOOLTIP=Mark Occurrences"
})
public class MarkOccurrencesHighlighter extends AbstractSemanticHighlighter<CurrentRuleContextData> {
    // -J-Dorg.antlr.works.editor.grammar.semantics.MarkOccurrencesHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(MarkOccurrencesHighlighter.class.getName());

    public static final Color ERROR_STRIPE_COLOR = new Color(175, 172, 102);

    private final DocumentListener documentListener;
    private final JTextComponent component;
    private final AttributeSet markOccurrencesAttributes;
    private final MarkOccurrencesMarkProviderCreator markProviderCreator;

    private MarkOccurrencesHighlighter(@NonNull JTextComponent component) {
        super((StyledDocument)component.getDocument(), GrammarParserDataDefinitions.CURRENT_RULE_CONTEXT);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);

        this.documentListener = new ClearHighlightsOnEditListener();
        this.component = component;
        this.markOccurrencesAttributes = getFontAndColors(settings, "markOccurrences");
        Collection<? extends MarkProviderCreator> markProviderCreators = MimeLookup.getLookup(DocumentUtilities.getMimeType(component)).lookupAll(MarkProviderCreator.class);
        MarkOccurrencesMarkProviderCreator markOccurrencesMarkProviderCreator = null;
        for (MarkProviderCreator creator : markProviderCreators) {
            if (creator instanceof MarkOccurrencesMarkProviderCreator) {
                markOccurrencesMarkProviderCreator = (MarkOccurrencesMarkProviderCreator)creator;
                break;
            }
        }

        this.markProviderCreator = markOccurrencesMarkProviderCreator;
        this.getDocument().addDocumentListener(WeakListeners.document(documentListener, this.getDocument()));
    }

    protected MarkOccurrencesListener createListener(ParserData<? extends CurrentRuleContextData> parserData) {
        if (parserData.getContext().getPosition() == null) {
            return null;
        }

        FileModel fileModel = null;
        GrammarAnnotatedParseTree annotatedParseTree = null;
        try {
            Future<ParserData<FileModel>> futureFileModelData = getTaskManager().getData(parserData.getSnapshot(), GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<FileModel> fileModelData = futureFileModelData != null ? futureFileModelData.get() : null;
            fileModel = fileModelData != null ? fileModelData.getData() : null;

            Future<ParserData<GrammarAnnotatedParseTree>> futureAnnotatedParseTreeData = getTaskManager().getData(parserData.getSnapshot(), GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<GrammarAnnotatedParseTree> annotatedParseTreeData = futureAnnotatedParseTreeData != null ? futureAnnotatedParseTreeData.get() : null;
            annotatedParseTree = annotatedParseTreeData != null ? annotatedParseTreeData.getData() : null;
        } catch (InterruptedException | ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }

        if (fileModel == null || annotatedParseTree == null) {
            return null;
        }

        return new MarkOccurrencesListener(fileModel, annotatedParseTree, parserData.getContext().getPosition());
    }

    private final List<SnapshotPosition> markPositions = new ArrayList<>();

    @Override
    protected void addHighlights(List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        for (Token token : tokens) {
            TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1), TrackingPositionRegion.Bias.Forward);
            SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
            intermediateContainer.add(Tuple.create(region.getRegion(), attributes));
            markPositions.add(region.getStart());
        }
    }

    protected void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, MarkOccurrencesListener listener) {
        markPositions.clear();

        List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer = new ArrayList<>(listener.getMarkedOccurrences().size());
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getMarkedOccurrences(), markOccurrencesAttributes);

        OffsetsBag updateBag = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        fillHighlights(updateBag, intermediateContainer);
        container.setHighlights(updateBag);

        Collection<Mark> marks = MarkOccurrencesMarkProvider.createMarks(currentSnapshot.getVersionedDocument(), markPositions, ERROR_STRIPE_COLOR, Bundle.LBL_ERROR_STRIPE_TOOLTIP());
        MarkOccurrencesMarkProvider markProvider = markProviderCreator.createMarkProvider(component);
        markProvider.setOccurrences(marks);
    }

    @Override
    protected Callable<Void> createAnalyzerTask(@NonNull final ParserData<? extends CurrentRuleContextData> parserData) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                final MarkOccurrencesListener listener = createListener(parserData);
                if (listener == null) {
                    return null;
                }

                try {
                    ParseTreeWalker.DEFAULT.walk(listener, listener.annotatedParseTree.getParseTree());
                } catch (RuntimeException ex) {
                    Exceptions.printStackTrace(ex);
                    throw ex;
                }

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ((BaseDocument)getDocument()).render(new Runnable() {

                            @Override
                            public void run() {
                                DocumentSnapshot sourceSnapshot = parserData.getSnapshot();
                                DocumentSnapshot currentSnapshot = sourceSnapshot.getVersionedDocument().getCurrentSnapshot();
                                updateHighlights(getContainer(), sourceSnapshot, currentSnapshot, listener);
                            }

                        });
                    }

                });

                return null;
            }
        };
    }

    public static Token getContext(SnapshotPosition position) {
        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        DocumentSnapshot snapshot = position.getSnapshot();
        int offset = position.getOffset();

        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
        Tagger<TokenTag<Token>> tagger;
        try {
            ParserData<Tagger<TokenTag<Token>>> tokensData = futureTokensData != null ? futureTokensData.get() : null;
            tagger = tokensData != null ? tokensData.getData() : null;
        } catch (InterruptedException | ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }

        if (tagger == null) {
            return null;
        }

        // get the token(s) at the cursor position, with affinity both directions
        OffsetRegion region = OffsetRegion.fromBounds(Math.max(0, offset - 1), Math.min(snapshot.length(), offset + 1));
        Iterable<TaggedPositionRegion<TokenTag<Token>>> tags = tagger.getTags(new NormalizedSnapshotPositionRegionCollection(new SnapshotPositionRegion(snapshot, region)));

        Token token = null;
        for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
            if (taggedRegion.getTag().getToken().getChannel() != Lexer.DEFAULT_TOKEN_CHANNEL) {
                continue;
            }

            token = taggedRegion.getTag().getToken();
            if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                break;
            }
        }

        if (token == null) {
            // try again without skipping off-channel tokens
            for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
                token = taggedRegion.getTag().getToken();
                if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                    break;
                }
            }
        }

        return token;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory extends AbstractLayerFactory {

        public LayerFactory() {
            super(MarkOccurrencesHighlighter.class);
        }

        @Override
        protected AbstractSemanticHighlighter<?> createHighlighter(HighlightsLayerFactory.Context context) {
            return new MarkOccurrencesHighlighter(context.getComponent());
        }

        @Override
        protected ZOrder getPosition() {
            return ZOrder.CARET_RACK.forPosition(50);
        }

    }

    public static class MarkOccurrencesListener extends GrammarParserBaseListener {

        private final FileModel fileModel;
        private final GrammarAnnotatedParseTree annotatedParseTree;

        private final List<Token> markedOccurrences = new ArrayList<>();

        private final Token referencedToken;

        public MarkOccurrencesListener(FileModel fileModel, GrammarAnnotatedParseTree annotatedParseTree, SnapshotPosition position) {
            this.fileModel = fileModel;
            this.annotatedParseTree = annotatedParseTree;
            this.referencedToken = findReferencedToken(position);
        }

        @NonNull
        public List<Token> getMarkedOccurrences() {
            return markedOccurrences;
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            if (referencedToken == null) {
                return;
            }

            if (node.getSymbol().equals(referencedToken)) {
                markedOccurrences.add(node.getSymbol());
            }

            Token target = annotatedParseTree.getTokenDecorator().getProperty(node.getSymbol(), GrammarTreeProperties.PROP_TARGET);
            if (target != null && target.equals(referencedToken)) {
                markedOccurrences.add(node.getSymbol());
            }
        }

        private Token findReferencedToken(SnapshotPosition position) {
            Token currentToken = getContext(position);
            if (currentToken == null) {
                return null;
            }

            if (annotatedParseTree.isDefinition(currentToken)) {
                return currentToken;
            }

            return annotatedParseTree.getTokenDecorator().getProperty(currentToken, GrammarTreeProperties.PROP_TARGET);
        }

    }

    private class ClearHighlightsOnEditListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            clearHighlights(e.getOffset());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            clearHighlights(e.getOffset());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            clearHighlights(e.getOffset());
        }

        private void clearHighlights(int offset) {
            OffsetsBag container = getContainer();
            HighlightsSequence sequence = container.getHighlights(0, Integer.MAX_VALUE);
            if (!sequence.moveNext()) {
                // no highlights
                return;
            }

            if (sequence.getEndOffset() >= offset) {
                container.clear();
                return;
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getContainer().clear();
                }
            });
        }

    }

}
