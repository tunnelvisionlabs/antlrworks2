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
                if (LexerDebuggerEditorKit.PROP_SELECTED_TOKENS.equals(evt.getPropertyName())) {
                    container.clear();

                    @SuppressWarnings("unchecked")
                    List<?> tokens = (List<?>)evt.getNewValue();
                    for (Object obj : tokens) {
                        if (obj instanceof TraceToken) {
                            TraceToken token = (TraceToken)obj;
                            container.addHighlight(token.getStartIndex(), token.getStopIndex() + 1, getSelectedTokenAttributes(token.getMode()));
                        }
                    }
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
