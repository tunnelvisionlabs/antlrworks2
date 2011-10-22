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
package org.antlr.works.editor.grammar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;

/**
 *
 * @author sam
 */
public abstract class GrammarCompletionItem implements CompletionItem {

    public static final int KEYWORD_SORT_PRIORITY = 100;
    public static final int RULE_SORT_PRIORITY = 100;

    public static final String KEYWORD_COLOR = "<font color=#000099>"; //NOI18N
    public static final String FIELD_COLOR = "<font color=#008618>"; //NOI18N
    public static final String METHOD_COLOR = "<font color=#000000>"; //NOI18N
    public static final String PARAMETER_NAME_COLOR = "<font color=#a06001>"; //NOI18N

    public static final String COLOR_END = "</font>"; //NOI18N
    public static final String STRIKE = "<s>"; //NOI18N
    public static final String STRIKE_END = "</s>"; //NOI18N
    public static final String BOLD = "<b>"; //NOI18N
    public static final String BOLD_END = "</b>"; //NOI18N

    public static final Collection<KeywordItem> KEYWORD_ITEMS =
        new ArrayList<KeywordItem>() {{
            add(new KeywordItem(0, "lexer"));
            add(new KeywordItem(0, "parser"));
            add(new KeywordItem(0, "catch"));
            add(new KeywordItem(0, "finally"));
            add(new KeywordItem(0, "grammar"));
            add(new KeywordItem(0, "mode"));
            add(new KeywordItem(0, "private"));
            add(new KeywordItem(0, "protected"));
            add(new KeywordItem(0, "public"));
            add(new KeywordItem(0, "returns"));
            add(new KeywordItem(0, "throws"));
            add(new KeywordItem(0, "tree"));
            add(new KeywordItem(0, "scope"));
            add(new KeywordItem(0, "import"));
            add(new KeywordItem(0, "fragment"));
            add(new KeywordItem(0, "tokens"));
            add(new KeywordItem(0, "options"));
        }};

    private int substitutionOffset;

    protected GrammarCompletionItem(int substitutionOffset) {
        this.substitutionOffset = substitutionOffset;
    }

    @Override
    public void defaultAction(JTextComponent component) {
        if (component != null) {
            Completion.get().hideDocumentation();
            Completion.get().hideCompletion();
            int caretOffset = component.getSelectionEnd();
            substituteText(component, substitutionOffset, caretOffset - substitutionOffset, null, false);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt) {
        if (evt.getID() == KeyEvent.KEY_TYPED) {
            if (!GrammarCompletionProvider.autoPopupOnGrammarIdentifierPart()
                && !evt.isAltDown() && !evt.isControlDown() && !evt.isMetaDown() && !evt.isAltGraphDown()
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
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(getIcon(), getLeftHtmlText(), getRightHtmlText(), g, defaultFont, defaultColor, width, height, selected);
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
    public boolean instantSubstitution(JTextComponent component) {
        if (component != null) {
            try {
                int caretOffset = component.getSelectionEnd();
                if (caretOffset > substitutionOffset) {
                    String text = component.getDocument().getText(substitutionOffset, caretOffset - substitutionOffset);
                    if (!getInsertPrefix().toString().startsWith(text)) {
                        return false;
                    }
                }
            } catch (BadLocationException ex) {
            }
        }

        defaultAction(component);
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

    protected ImageIcon getIcon() {
        return null;
    }

    protected String getLeftHtmlText() {
        return null;
    }

    protected String getRightHtmlText() {
        return null;
    }

    protected void substituteText(JTextComponent component, int offset, int length, String replacement, boolean assign) {
        BaseDocument document = (BaseDocument)component.getDocument();
        CharSequence prefix = getInsertPrefix();
        if (prefix == null) {
            return;
        }

        StringBuilder text = new StringBuilder(prefix);
    }

    public static class KeywordItem extends GrammarCompletionItem {

        private final String keyword;

        private String leftText;

        public KeywordItem(int substitutionOffset, String keyword) {
            super(substitutionOffset);
            this.keyword = keyword;
        }

        @Override
        public int getSortPriority() {
            return KEYWORD_SORT_PRIORITY;
        }

        @Override
        public CharSequence getSortText() {
            return keyword;
        }

        @Override
        public CharSequence getInsertPrefix() {
            return keyword;
        }

        @Override
        protected String getLeftHtmlText() {
            if (leftText == null) {
                StringBuilder builder = new StringBuilder();
                builder.append(KEYWORD_COLOR);
                builder.append(BOLD);
                builder.append(keyword);
                builder.append(BOLD_END);
                builder.append(COLOR_END);
                leftText = builder.toString();
            }
            
            return leftText;
        }

    }

}
