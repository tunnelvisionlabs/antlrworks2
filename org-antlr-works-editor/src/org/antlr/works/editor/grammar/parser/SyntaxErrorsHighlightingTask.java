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
import java.util.List;
import javax.swing.text.Document;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.works.editor.grammar.parser.ANTLRErrorProvidingParser.SyntaxError;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;

public class SyntaxErrorsHighlightingTask extends ParserResultTask {

    public SyntaxErrorsHighlightingTask() {
    }

    @Override
    public void run(Result result, SchedulerEvent event) {
        try {
            GrammarParser.GrammarParserResult parserResult = (GrammarParser.GrammarParserResult)result;
            List<SyntaxError> syntaxErrors = parserResult.getParser().getSyntaxErrors();
            Document document = parserResult.getSnapshot().getSource().getDocument(false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (SyntaxError syntaxError : syntaxErrors) {
                if (!(syntaxError.getException() instanceof RecognitionException))
                    continue;

                RecognitionException exception = (RecognitionException)syntaxError.getException();
                String message = syntaxError.getMessage();

                // first try to get the specific location from a token
                if (exception.token instanceof CommonToken) {
                    CommonToken commonToken = (CommonToken)exception.token;
                    int startOffset = result.getSnapshot().getOriginalOffset(commonToken.getStartIndex());
                    int endOffsetInclusive = result.getSnapshot().getOriginalOffset(commonToken.getStopIndex());

                    if (startOffset < 0 || endOffsetInclusive < 0) {
                        continue;
                    }

                    ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(Severity.ERROR, message, document, document.createPosition(startOffset), document.createPosition(endOffsetInclusive + 1));
                    errors.add(errorDescription);
                } else {
                int line = exception.line;
                    if (line <= 0) {
                        continue;
                    }

                    ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(Severity.ERROR, message, document, line);
                    errors.add(errorDescription);
                }
            }

            HintsController.setErrors(document, "antlr3-syntax", errors);
        } catch (Exception ex) {
        }
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
