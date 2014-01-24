/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.spi.editor.highlighting.HighlightsContainer;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=HighlightsLayerFactory.class)
public class LexerDebuggerSelectedTokenHighlighterLayerFactory implements HighlightsLayerFactory {
    private static final AttributeSet[] SelectedTokenAttributes =
        {
            createSelectedTokenAttributeSet(0),
            createSelectedTokenAttributeSet(1),
            createSelectedTokenAttributeSet(2),
            createSelectedTokenAttributeSet(3),
            createSelectedTokenAttributeSet(4),
            createSelectedTokenAttributeSet(5),
            createSelectedTokenAttributeSet(6),
            createSelectedTokenAttributeSet(7),
            createSelectedTokenAttributeSet(8),
        };

    @Override
    public HighlightsLayer[] createLayers(Context context) {
        HighlightsContainer container = createSelectedTokensContainer(context.getComponent());
        return new HighlightsLayer[] { HighlightsLayer.create(getClass().getName(), ZOrder.SYNTAX_RACK, true, container) };
    }

    private HighlightsContainer createSelectedTokensContainer(JTextComponent component) {
        final OffsetsBag container = new OffsetsBag(component.getDocument());

        DocumentUtilities.addPropertyChangeListener(component.getDocument(), new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                case LexerDebuggerEditorKit.PROP_SELECTED_TOKENS:
                    container.clear();
                    List<?> tokens = (List<?>)evt.getNewValue();
                    for (Object obj : tokens) {
                        if (obj instanceof TraceToken) {
                            TraceToken token = (TraceToken)obj;
                            container.addHighlight(token.getStartIndex(), token.getStopIndex() + 1, getSelectedTokenAttributes(token.getMode()));
                        }
                    }
                    break;

                case LexerDebuggerEditorKit.PROP_SELECTED_CHARACTERS:
                    container.clear();
                    TupleIntInt[] characters = (TupleIntInt[])evt.getNewValue();
                    if (characters != null) {
                        for (TupleIntInt character : characters) {
                            container.addHighlight(character.getItem1(), character.getItem1() + 1, getSelectedTokenAttributes(character.getItem2() - 1));
                        }
                    }
                    break;

                default:
                    break;
                }
            }
        });

        return container;
    }

    private static AttributeSet getSelectedTokenAttributes(int mode) {
        if (mode >= 0 && mode < SelectedTokenAttributes.length) {
            return SelectedTokenAttributes[mode];
        }

        return SelectedTokenAttributes[SelectedTokenAttributes.length - 1];
    }

    private static AttributeSet createSelectedTokenAttributeSet(int mode) {
        MutableAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(StyleConstants.Background, LexerDebuggerTokenHighlighterLayerFactory.getColorForMode(mode, 0.15));
        return attributes;
    }

}
