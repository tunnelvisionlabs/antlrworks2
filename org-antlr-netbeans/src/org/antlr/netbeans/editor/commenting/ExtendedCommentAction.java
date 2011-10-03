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
package org.antlr.netbeans.editor.commenting;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentSpan;
import org.netbeans.editor.ext.ExtKit.CommentAction;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class ExtendedCommentAction extends CommentAction {

    private final Commenter commenter;

    public ExtendedCommentAction(Commenter commenter) {
        super(null);
        Parameters.notNull("commenter", commenter);

        this.commenter = commenter;
    }

    @Override
    public void actionPerformed(ActionEvent evt, JTextComponent target) {
        try {
            List<DocumentSpan> spans = new ArrayList<DocumentSpan>();
            Document document = target.getDocument();
            if (!(document instanceof StyledDocument)) {
                return;
            }

            DocumentSpan selection = new DocumentSpan((StyledDocument)document, target.getSelectionStart(), target.getSelectionEnd());
            spans.add(selection);

            List<DocumentSpan> updatedSelection = commenter.commentSpans(spans);

            if (!updatedSelection.isEmpty()) {
                target.select(updatedSelection.get(0).getStart().getOffset(), updatedSelection.get(0).getEnd().getOffset());
            }
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
