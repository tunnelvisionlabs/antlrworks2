/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4;

import javax.swing.Action;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import org.antlr.netbeans.editor.commenting.BlockCommentFormat;
import org.antlr.netbeans.editor.commenting.Commenter;
import org.antlr.netbeans.editor.commenting.ExtendedCommentAction;
import org.antlr.netbeans.editor.commenting.ExtendedUncommentAction;
import org.antlr.netbeans.editor.commenting.LineCommentFormat;
import org.antlr.netbeans.editor.commenting.StandardCommenter;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.NbEditorKit;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=EditorKit.class)
public class StringTemplateEditorKit extends NbEditorKit {

    public static final String TEMPLATE_MIME_TYPE = "text/x-stringtemplate4";

    private static final LineCommentFormat LINE_COMMENT_FORMAT = new LineCommentFormat("//");
    private static final BlockCommentFormat OUTER_BLOCK_COMMENT_FORMAT = new BlockCommentFormat("/*", "*/");
    private static final BlockCommentFormat INNER_BLOCK_COMMENT_FORMAT = new BlockCommentFormat("<!", "!>");

    @Override
    public String getContentType() {
        return TEMPLATE_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        Commenter commenter = new StandardCommenter(LINE_COMMENT_FORMAT, OUTER_BLOCK_COMMENT_FORMAT, INNER_BLOCK_COMMENT_FORMAT);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedCommentAction commentAction = new ExtendedCommentAction(commenter);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedUncommentAction uncommentAction = new ExtendedUncommentAction(commenter);

        Action[] extraActions = {
        };

        Action[] actions = TextAction.augmentList(superActions, extraActions);
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] instanceof CommentAction) {
                actions[i] = commentAction;
            } else if (actions[i] instanceof UncommentAction) {
                actions[i] = uncommentAction;
            }
        }

        return actions;
    }

}
