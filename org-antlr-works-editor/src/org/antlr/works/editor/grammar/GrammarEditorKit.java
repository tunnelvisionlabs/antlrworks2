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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import org.antlr.netbeans.editor.commenting.BlockCommentFormat;
import org.antlr.netbeans.editor.commenting.ExtendedCommentAction;
import org.antlr.netbeans.editor.commenting.ExtendedUncommentAction;
import org.antlr.netbeans.editor.commenting.LineCommentFormat;
import org.antlr.netbeans.editor.commenting.StandardCommenter;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.util.ContextAwareAction;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.Presenter;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=EditorKit.class)
public class GrammarEditorKit extends NbEditorKit {

    public static final String PROP_LEGACY_MODE = "legacy-mode";

    public static final String GRAMMAR_MIME_TYPE = "text/x-antlr3";

    private static final LineCommentFormat LINE_COMMENT_FORMAT = new LineCommentFormat("//");
    private static final BlockCommentFormat BLOCK_COMMENT_FORMAT = new BlockCommentFormat("/*", "*/");

    @Override
    protected void initDocument(BaseDocument doc) {
        super.initDocument(doc);

        Preferences preferences = MimeLookup.getLookup(GRAMMAR_MIME_TYPE).lookup(Preferences.class);
        if (preferences != null) {
            preferences.putInt(SimpleValueNames.COMPLETION_AUTO_POPUP_DELAY, 0);
        }
    }

    @Override
    public String getContentType() {
        return GRAMMAR_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        StandardCommenter commenter = new StandardCommenter(LINE_COMMENT_FORMAT, BLOCK_COMMENT_FORMAT);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedCommentAction commentAction = new ExtendedCommentAction(commenter);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedUncommentAction uncommentAction = new ExtendedUncommentAction(commenter);

        Action[] actions = {
            new ToggleLegacyModeAction()
        };

        actions = TextAction.augmentList(superActions, actions);
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] instanceof CommentAction) {
                actions[i] = commentAction;
            } else if (actions[i] instanceof UncommentAction) {
                actions[i] = uncommentAction;
            }
        }

        return actions;
    }

    public static boolean isLegacyMode(DocumentSnapshot snapshot) {
        Document document = snapshot.getVersionedDocument().getDocument();
        if (document == null) {
            return false;
        }

        return isLegacyMode(document);
    }

    public static boolean isLegacyMode(Document document) {
        Boolean mode = (Boolean)document.getProperty(PROP_LEGACY_MODE);
        if (mode == null) {
            return false;
        }

        return mode;
    }

    @NbBundle.Messages({
        "toggle-legacy-mode=Toggle Legacy Mode"
    })
    public static class ToggleLegacyModeAction extends BaseAction implements Presenter.Toolbar, ContextAwareAction, PropertyChangeListener {

        /** Toggle legacy mode action */
        public static final String toggleLegacyModeAction = "toggle-legacy-mode"; // NOI18N

        private final Document document;

        private JToggleButton button;

        public ToggleLegacyModeAction() {
            this(null);
        }

        @SuppressWarnings("LeakingThisInConstructor")
        public ToggleLegacyModeAction(Document document) {
            super(toggleLegacyModeAction);
            putValue(Action.SMALL_ICON, ImageUtilities.loadImageIcon("org/antlr/works/editor/grammar/resources/legacy-mode.png", false)); // NOI18N
            putValue("noIconInMenu", true); // NOI18N
            this.document = document;
            if (document != null) {
                DocumentUtilities.addPropertyChangeListener(document, this);
                updateState();
            }
        }

        @Override
        protected Class<?> getShortDescriptionBundleClass() {
            return ToggleLegacyModeAction.class;
        }

        @Override
        public void actionPerformed(ActionEvent evt, JTextComponent target) {
            if (target != null) {
                boolean current = isLegacyMode(target.getDocument());
                target.getDocument().putProperty(PROP_LEGACY_MODE, !current);

                ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
                taskManager.reschedule(VersionedDocumentUtilities.getVersionedDocument(target.getDocument()), null, 50, TimeUnit.MILLISECONDS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
            }
        }

        @Override
        public Component getToolbarPresenter() {
            if (button == null) {
                button = new JToggleButton();
                button.putClientProperty("hideActionText", true);
                button.setIcon((Icon)getValue(SMALL_ICON));
                button.setAction(this);
            }

            return button;
        }

        @Override
        public Action createContextAwareInstance(Lookup actionContext) {
            JEditorPane pane = actionContext.lookup(JEditorPane.class);
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Document document = pane != null ? pane.getDocument() : null;
            if (document != null) {
                ToggleLegacyModeAction action = new ToggleLegacyModeAction(document);
                return action;
            }

            return this;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (document == evt.getSource()) {
                if (PROP_LEGACY_MODE.equals(evt.getPropertyName())) {
                    updateState();
                }
            }
        }

        private void updateState() {
            if (document != null) {
                boolean legacyMode = isLegacyMode(document);
                if (button != null) {
                    button.setSelected(legacyMode);
                    button.setContentAreaFilled(legacyMode);
                    button.setBorderPainted(legacyMode);
                }
            }
        }
    }

}
