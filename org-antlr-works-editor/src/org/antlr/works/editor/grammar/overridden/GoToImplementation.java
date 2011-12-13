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
package org.antlr.works.editor.grammar.overridden;

import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.api.progress.ProgressUtils;
import org.netbeans.editor.BaseAction;
import org.openide.util.NbBundle;

/**
 *
 * @author sam
 */
@EditorActionRegistration(
        name = "goto-implementation",
        mimeType = GrammarEditorKit.GRAMMAR_MIME_TYPE,
        popupText = "#CTL_GoToImplementation"
)
@NbBundle.Messages({
    "CTL_GoToImplementation=Go to Implementation",
    "goto-implementation=Go to Implementation"
})
public class GoToImplementation extends BaseAction {

    public GoToImplementation() {
        super(SAVE_POSITION);
    }

    @Override
    public void actionPerformed(ActionEvent evt, JTextComponent target) {
        goToImplementation(target);
    }

    public static void goToImplementation(final JTextComponent c) {
        final Document doc = c.getDocument();
        final int caretPos = c.getCaretPosition();
        final AtomicBoolean cancel = new AtomicBoolean();

        ProgressUtils.runOffEventDispatchThread(new Runnable() {
            @Override
            public void run() {
                goToImplementation(c, doc, caretPos, cancel);
            }
        }, Bundle.CTL_GoToImplementation(), cancel, false);
    }

    public static void goToImplementation(final JTextComponent c, final Document document, final int caretPosition, final AtomicBoolean cancel) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
