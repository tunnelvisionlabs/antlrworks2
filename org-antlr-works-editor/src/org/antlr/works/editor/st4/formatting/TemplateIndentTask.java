/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.formatting;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class TemplateIndentTask implements IndentTask {
    private final Context context;

    public TemplateIndentTask(Context context) {
        this.context = context;
    }

    @Override
    public void reindent() throws BadLocationException {
        if (!(context.document() instanceof StyledDocument)) {
            return;
        }

        StyledDocument document = (StyledDocument)context.document();
        int startLine = NbDocument.findLineNumber(document, context.startOffset());

        int endLine;
        if (context.endOffset() <= context.startOffset()) {
            endLine = startLine;
        } else {
            endLine = NbDocument.findLineNumber(document, context.endOffset() - 1);
        }

        int previousIndent;
        if (startLine == 0) {
            previousIndent = 0;
        } else {
            int previousLineOffset = NbDocument.findLineOffset(document, startLine - 1);
            previousIndent = context.lineIndent(previousLineOffset);
        }

        for (int line = startLine; line <= endLine; line++) {
            int currentOffset = NbDocument.findLineOffset(document, startLine);
            int currentIndent = context.lineIndent(currentOffset);
            if (currentIndent == 0 && previousIndent > 0) {
                context.modifyIndent(currentOffset, previousIndent);
            }

            previousIndent = currentIndent;
        }
    }

    @Override
    public ExtraLock indentLock() {
        return null;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=IndentTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public IndentTask createTask(Context context) {
            return new TemplateIndentTask(context);
        }

    }
}
