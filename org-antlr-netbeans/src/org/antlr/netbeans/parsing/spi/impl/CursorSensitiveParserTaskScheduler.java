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
package org.antlr.netbeans.parsing.spi.impl;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the active editor window changes, the
 * content of the active document changes, and/or the caret position changes within
 * the active document.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class CursorSensitiveParserTaskScheduler extends CurrentDocumentParserTaskScheduler {

    private JTextComponent currentEditor;
    private CaretListener caretListener;

    @Override
    protected void setEditor(JTextComponent editor) {
        if (currentEditor != null) {
            currentEditor.removeCaretListener(caretListener);
        }

        super.setEditor(editor);
        currentEditor = editor;

        if (editor != null) {
            if (caretListener == null) {
                caretListener = new CaretListenerImpl();
            }

            editor.addCaretListener(caretListener);
        }
    }

    private class CaretListenerImpl implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            schedule(versionedDocument, currentEditor);
        }
    }

}
