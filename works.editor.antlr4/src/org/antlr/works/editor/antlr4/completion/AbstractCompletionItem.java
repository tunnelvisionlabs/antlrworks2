/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.api.editor.completion.Completion;
import com.tvl.spi.editor.completion.CompletionController;
import com.tvl.spi.editor.completion.CompletionItem;
import com.tvl.spi.editor.completion.CompletionTask;
import com.tvl.spi.editor.completion.support.CompletionUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.GuardedDocument;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCompletionItem implements CompletionItem {
    // -J-Dorg.antlr.works.editor.antlr4.completion.AbstractCompletionItem.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractCompletionItem.class.getName());

    protected static final Object CT_HANDLER_DOC_PROPERTY = "code-template-insert-handler"; // NOI18N

    public static final String COLOR_END = "</font>"; //NOI18N
    public static final String STRIKE = "<s>"; //NOI18N
    public static final String STRIKE_END = "</s>"; //NOI18N
    public static final String BOLD = "<b>"; //NOI18N
    public static final String BOLD_END = "</b>"; //NOI18N

    protected AbstractCompletionItem() {
    }

    public boolean allowInitialSelection() {
        return true;
    }

    @Override
    public final void defaultAction(JTextComponent component) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", BaseCompletionController.class.getSimpleName())); //NOI18N
    }

    public void defaultAction(@NonNull JTextComponent component, @NonNull BaseCompletionController controller, boolean isSelected) {
        Completion.get().hideDocumentation();
        Completion.get().hideCompletion();
        if (!isSelected) {
            return;
        }

        TrackingPositionRegion applicableTo = controller.getApplicableTo();
        if (applicableTo == null) {
            return;
        }

        Document document = component.getDocument();
        if (document == null) {
            return;
        }

        VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
        DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
        SnapshotPositionRegion replacementSpan = applicableTo.getRegion(snapshot);
        substituteText(component, replacementSpan.getStart().getOffset(), replacementSpan.getLength(), getInsertPrefix().toString());
        BaseCompletionController.addRecentCompletion(getInsertPrefix().toString());
    }

    @Override
    public final void processKeyEvent(KeyEvent evt) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", BaseCompletionController.class.getSimpleName())); //NOI18N
    }

    public void processKeyEvent(KeyEvent evt, BaseCompletionController controller, boolean isSelected) {
        //if (evt.getID() == KeyEvent.KEY_TYPED) {
        //    if (!AbstractCompletionProvider.autoPopupOnIdentifierPart()
        //        && evt.getModifiers() == 0
        //        && AbstractCompletionProvider.getCompletionSelectors().indexOf(evt.getKeyChar()) >= 0) {
        //        throw new UnsupportedOperationException("Not supported yet.");
        //    }
        //} else if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isControlDown()) {
        //    throw new UnsupportedOperationException("Not supported yet.");
        //} else if (evt.getID() == KeyEvent.KEY_PRESSED && evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isAltDown()) {
        //    throw new UnsupportedOperationException("Not supported yet.");
        //}
        JTextComponent comp = controller.getComponent();
        boolean compEditable = (comp != null && comp.isEditable());
        Document doc = comp != null ? comp.getDocument() : null;
        boolean guardedPos = doc instanceof GuardedDocument && ((GuardedDocument)doc).isPosGuarded(comp.getSelectionEnd());

        if (evt.getKeyCode() == KeyEvent.VK_TAB && doc.getProperty(CT_HANDLER_DOC_PROPERTY) == null) {
            evt.consume();
            if (guardedPos) {
                Toolkit.getDefaultToolkit().beep();
            } else if (compEditable && evt.getID() == KeyEvent.KEY_PRESSED) {
                controller.defaultAction(this, true);
                evt.consume();
            }

            return;
        }

        if (evt.getID() == KeyEvent.KEY_TYPED && !Character.isJavaIdentifierPart(evt.getKeyChar())) {
            Completion.get().hideCompletion();
        }
    }

    @Override
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(getLeftHtmlText(), getRightHtmlText(), g, defaultFont);
    }

    @Override
    public final void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", BaseCompletionController.class.getSimpleName())); //NOI18N
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
        throw new UnsupportedOperationException(String.format("This functionality is handled by %s.", BaseCompletionController.class.getSimpleName())); //NOI18N
    }

    public boolean instantSubstitution(@NonNull JTextComponent component, @NonNull BaseCompletionController controller) {
        if (controller.getApplicableTo() == null) {
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
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                }
            }
        });
    }
}
