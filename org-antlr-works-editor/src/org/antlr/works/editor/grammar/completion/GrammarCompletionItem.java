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
package org.antlr.works.editor.grammar.completion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionController;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;

/**
 *
 * @author Sam Harwell
 */
public abstract class GrammarCompletionItem implements CompletionItem {

    public static final int KEYWORD_SORT_PRIORITY = 100;
    public static final int RULE_SORT_PRIORITY = 100;
    public static final int ELEMENT_REFERENCE_SORT_PRIORITY = 100;
    public static final int PROPERTY_SORT_PRIORITY = 100;
    public static final int MEMBER_SORT_PRIORITY = 100;
    public static final int DECLARATION_SORT_PRIORITY = -100;

    public static final String KEYWORD_COLOR = "<font color=#000099>"; //NOI18N
    public static final String FIELD_COLOR = "<font color=#008618>"; //NOI18N
    public static final String METHOD_COLOR = "<font color=#000000>"; //NOI18N
    public static final String PARAMETER_NAME_COLOR = "<font color=#a06001>"; //NOI18N
    public static final String REFERENCE_COLOR = "<font color=#a06001>"; //NOI18N

    public static final String COLOR_END = "</font>"; //NOI18N
    public static final String STRIKE = "<s>"; //NOI18N
    public static final String STRIKE_END = "</s>"; //NOI18N
    public static final String BOLD = "<b>"; //NOI18N
    public static final String BOLD_END = "</b>"; //NOI18N

    protected GrammarCompletionItem() {
    }

    public boolean allowInitialSelection() {
        return true;
    }

    @Override
    public final void defaultAction(JTextComponent component) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", GrammarCompletionController.class.getSimpleName())); //NOI18N
    }

    public void defaultAction(@NonNull JTextComponent component, @NonNull GrammarCompletionController controller, boolean isSelected) {
        Completion.get().hideDocumentation();
        Completion.get().hideCompletion();
        if (!isSelected) {
            return;
        }

        GrammarCompletionQuery query = controller.getQuery();

        TrackingPositionRegion applicableTo = query.getApplicableTo();
        if (applicableTo == null) {
            return;
        }

        Document document = component.getDocument();
        if (document == null) {
            return;
        }

        VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
        if (textBuffer == null) {
            return;
        }

        DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
        if (snapshot == null) {
            return;
        }

        SnapshotPositionRegion replacementSpan = query.getApplicableTo().getRegion(snapshot);
        substituteText(component, replacementSpan.getStart().getOffset(), replacementSpan.getLength(), getInsertPrefix().toString());
        GrammarCompletionController.addRecentCompletion(getInsertPrefix().toString());
    }

    @Override
    public final void processKeyEvent(KeyEvent evt) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", GrammarCompletionController.class.getSimpleName())); //NOI18N
    }

    public void processKeyEvent(KeyEvent evt, GrammarCompletionController controller, boolean isSelected) {
        if (evt.getID() == KeyEvent.KEY_TYPED) {
            if (!GrammarCompletionProvider.autoPopupOnGrammarIdentifierPart()
                && evt.getModifiers() == 0
                && GrammarCompletionProvider.getGrammarCompletionSelectors().indexOf(evt.getKeyChar()) >= 0) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        } else if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isControlDown()) {
            throw new UnsupportedOperationException("Not supported yet.");
        } else if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isAltDown()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(getLeftHtmlText(), getRightHtmlText(), g, defaultFont);
    }

    @Override
    public final void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", GrammarCompletionController.class.getSimpleName())); //NOI18N
    }

    public void render(CompletionController controller, Graphics g, Font defaultFont, Color foregroundColor, Color backgroundColor, Color selectedForegroundColor, Color selectedBackgroundColor, int width, int height, boolean isBestMatch, boolean isSelected) {
        CompletionUtilities.renderHtml(getIcon(), getLeftHtmlText(), getRightHtmlText(), g, defaultFont, foregroundColor, selectedBackgroundColor, width, height, isBestMatch, isSelected);
    }

    @Override
    public CompletionTask createDocumentationTask() {
        return null;
    }

    @Override
    public CompletionTask createToolTipTask() {
        return null;
    }

    @Override
    public final boolean instantSubstitution(JTextComponent component) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", GrammarCompletionController.class.getSimpleName())); //NOI18N
    }

    public boolean instantSubstitution(@NonNull JTextComponent component, @NonNull GrammarCompletionController controller) {
        if (controller.getQuery().getApplicableTo() == null) {
            return false;
        }

        defaultAction(component, controller, true);
        return true;
    }

//    @Override
//    public int getSortPriority() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public CharSequence getSortText() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public CharSequence getInsertPrefix() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    @Override
    public final CharSequence getSortText() {
        return getSortTextImpl().toLowerCase(Locale.getDefault());
    }

    protected abstract String getSortTextImpl();

    protected ImageIcon getIcon() {
        return null;
    }

    protected String getLeftHtmlText() {
        return null;
    }

    protected String getRightHtmlText() {
        return null;
    }

    protected void substituteText(final JTextComponent component, final int offset, final int length, final String replacement) {
        final BaseDocument document = (BaseDocument)component.getDocument();

        document.runAtomic (new Runnable () {
            @Override
            public void run () {
                try {
                    if (document.getText(offset, length).equals(replacement)) {
                        component.setCaretPosition(offset + replacement.length());
                        return;
                    }

                    document.remove(offset, length);
                    document.insertString(offset, replacement, null);
//                    component.setCaretPosition(offset + replacement.length());
                } catch (BadLocationException e) {
                    // Can't update
                }
            }
        });
    }
}
