/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.BaseDocument;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=EditorKit.class)
public class ParserDebuggerEditorKit extends AbstractGrammarDebuggerEditorKit {
    private static final Logger LOG = Logger.getLogger(ParserDebuggerEditorKit.class.getName());

    public static final String PARSER_DEBUGGER_MIME_TYPE = "text/x-antlr3-pdbg";

    @Override
    protected void initDocument(BaseDocument doc) {
        super.initDocument(doc);
    }

    @Override
    public String getContentType() {
        return PARSER_DEBUGGER_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        Action[] actions = {
        };

        actions = TextAction.augmentList(superActions, actions);
        return actions;
    }
}
