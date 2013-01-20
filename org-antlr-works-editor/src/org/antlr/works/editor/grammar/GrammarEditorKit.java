/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.StyledDocument;
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
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseKit;
import org.netbeans.editor.Utilities;
import org.netbeans.editor.ext.ExtKit;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.editor.NbEditorKit;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileObject;
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
            new ToggleLegacyModeAction(),
            new GrammarGoToDeclarationAction(),
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

    public static boolean isLegacyMode(@NonNull DocumentSnapshot snapshot) {
        Document document = snapshot.getVersionedDocument().getDocument();
        if (document == null) {
            return isDefaultLegacyMode(snapshot.getVersionedDocument().getFileObject());
        }

        return isLegacyMode(document);
    }

    public static boolean isLegacyMode(@NonNull Document document) {
        Boolean mode = (Boolean)document.getProperty(PROP_LEGACY_MODE);
        if (mode == null) {
            return isDefaultLegacyMode(NbEditorUtilities.getFileObject(document));
        }

        return mode;
    }

    private static boolean isDefaultLegacyMode(@NullAllowed FileObject fileObject) {
        if (fileObject == null) {
            return false;
        }

        String name = fileObject.getName();
        String nameExt = fileObject.getNameExt();
        if (name != null && nameExt != null && name.length() < nameExt.length()) {
            String extension = nameExt.substring(name.length());
            boolean v4grammar = ".g4".equals(extension);
            return !v4grammar;
        }

        return false;
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
                updateState();
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

    @NbBundle.Messages({
        "generate-goto-popup=Go To",
        "goto-source=Go to Source",
        "goto_source_open_source_not_formatted=Go to Source",
    })
    @EditorActionRegistration(name = generateGoToPopupAction, mimeType = GRAMMAR_MIME_TYPE)
    public static class GrammarGenerateGoToPopupAction extends NbGenerateGoToPopupAction {

        @Override
        public void actionPerformed(ActionEvent evt, JTextComponent target) {
        }

        private void addAcceleretors(Action a, JMenuItem item, JTextComponent target){
            // Try to get the accelerator
            Keymap km = target.getKeymap();
            if (km != null) {

                KeyStroke[] keys = km.getKeyStrokesForAction(a);
                if (keys != null && keys.length > 0) {
                    item.setAccelerator(keys[0]);
                }else if (a!=null){
                    KeyStroke ks = (KeyStroke)a.getValue(Action.ACCELERATOR_KEY);
                    if (ks!=null) {
                        item.setAccelerator(ks);
                    }
                }
            }
        }

        private void addAction(JTextComponent target, JMenu menu, Action a){
            if (a != null) {
                String actionName = (String) a.getValue(Action.NAME);
                JMenuItem item = null;
                if (a instanceof BaseAction) {
                    item = ((BaseAction)a).getPopupMenuItem(target);
                }
                if (item == null) {
                    // gets trimmed text that doesn' contain "go to"
                    String itemText = (String)a.getValue(ExtKit.TRIMMED_TEXT);
                    if (itemText == null){
                        itemText = getItemText(target, actionName, a);
                    }
                    if (itemText != null) {
                        item = new JMenuItem(itemText);
                        Mnemonics.setLocalizedText(item, itemText);
                        item.addActionListener(a);
                        addAcceleretors(a, item, target);
                        item.setEnabled(a.isEnabled());
                        Object helpID = a.getValue ("helpID"); // NOI18N
                        if (helpID != null && (helpID instanceof String))
                            item.putClientProperty ("HelpID", helpID); // NOI18N
                    }else{
                        if (ExtKit.gotoSourceAction.equals(actionName)){
                            item = new JMenuItem(Bundle.goto_source_open_source_not_formatted());
                            addAcceleretors(a, item, target);
                            item.setEnabled(false);
                        }
                    }
                }

                if (item != null) {
                    menu.add(item);
                }

            }
        }

        protected void addAction(JTextComponent target, JMenu menu, String actionName) {
            BaseKit kit = Utilities.getKit(target);
            if (kit == null) return;
            Action a = kit.getActionByName(actionName);
            if (a!=null){
                addAction(target, menu, a);
            } else { // action-name is null, add the separator
                menu.addSeparator();
            }
        }

        protected String getItemText(JTextComponent target, String actionName, Action a) {
            String itemText;
            if (a instanceof BaseAction) {
                itemText = ((BaseAction)a).getPopupMenuText(target);
            } else {
                itemText = actionName;
            }
            return itemText;
        }

        @Override
        public JMenuItem getPopupMenuItem(final JTextComponent target) {
            String menuText = Bundle.generate_goto_popup();
            JMenu jm = new JMenu(menuText);
            //addAction(target, jm, ExtKit.gotoSourceAction);
            addAction(target, jm, ExtKit.gotoDeclarationAction);
            //addAction(target, jm, gotoSuperImplementationAction);
            //addAction(target, jm, ExtKit.gotoAction);
            return jm;
        }

    }

    /**
     * @deprecated Not actually deprecated...
     */
    @Deprecated
    public static class GrammarGoToDeclarationAction extends GotoDeclarationAction {

        public GrammarGoToDeclarationAction() {
        }

        @Override
        public boolean gotoDeclaration(JTextComponent target) {
            if (!(target.getDocument() instanceof StyledDocument))
                return false;

            GoToSupport.goTo((StyledDocument) target.getDocument(), target.getCaretPosition(), false);
            return true;
        }
    }

}
