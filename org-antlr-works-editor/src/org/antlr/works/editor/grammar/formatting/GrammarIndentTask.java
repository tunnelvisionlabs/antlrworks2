/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.grammar.formatting;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class GrammarIndentTask implements IndentTask {
    private final Context context;

    public GrammarIndentTask(Context context) {
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

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=IndentTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public IndentTask createTask(Context context) {
            return new GrammarIndentTask(context);
        }

    }
}
