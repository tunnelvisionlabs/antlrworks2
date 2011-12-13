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
package org.antlr.works.editor.grammar.parser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.openide.util.Exceptions;

public class SyntaxErrorsHighlightingTask extends GrammarParserResultTask {
    private static final EnumSet<Input> inputs = EnumSet.<Input>of(Input.SyntaxErrors);

    public SyntaxErrorsHighlightingTask() {
    }

    @Override
    public void run(GrammarParser.GrammarParserResult result, SchedulerEvent event) {
        try {
            List<? extends SyntaxError> syntaxErrors = result.getResult().getSyntaxErrors();
            Document document = result.getSnapshot().getSource().getDocument(false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (SyntaxError syntaxError : syntaxErrors) {
                CommonToken offendingToken = syntaxError.getOffendingToken() instanceof CommonToken ? (CommonToken)syntaxError.getOffendingToken() : null;
                RecognitionException exception = syntaxError.getException();
                String message = syntaxError.getMessage();

                if (offendingToken == null && exception != null && exception.token instanceof CommonToken) {
                    offendingToken = (CommonToken)exception.token;
                }

                // first try to get the specific location from a token
                if (offendingToken != null) {
                    int startOffset = result.getSnapshot().getOriginalOffset(offendingToken.getStartIndex());
                    int endOffsetInclusive = result.getSnapshot().getOriginalOffset(offendingToken.getStopIndex());

                    if (startOffset < 0 || endOffsetInclusive < 0) {
                        continue;
                    }

                    try {
                        ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(syntaxError.getSeverity(), message, document, document.createPosition(startOffset), document.createPosition(endOffsetInclusive + 1));
                        errors.add(errorDescription);
                        continue;
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

                int line = exception != null ? exception.line : -1;
                if (line <= 0) {
                    continue;
                }

                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(syntaxError.getSeverity(), message, document, line);
                errors.add(errorDescription);
            }

            HintsController.setErrors(document, "antlr3-syntax", errors);
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public EnumSet<Input> getTaskInputs() {
        return inputs;
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel() {
    }

}
