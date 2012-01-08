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
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class SyntaxErrorsHighlightingParserTask implements ParserTask {

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        Future<ParserData<CompiledModel>> futureData = taskManager.getData(snapshot, component, TemplateParserDataDefinitions.COMPILED_MODEL);
        ParserData<CompiledModel> parserData = futureData.get();
        CompiledModel model = parserData.getData();
        DocumentSnapshot latestSnapshot = snapshot.getVersionedDocument().getCurrentSnapshot();

        try {
            List<? extends SyntaxError> syntaxErrors = model.getResult().getSyntaxErrors();
            Document document = model.getSnapshot().getVersionedDocument().getDocument();
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
                    int startOffset = offendingToken.getStartIndex();
                    int endOffset = offendingToken.getStopIndex() + 1;
                    if (startOffset < 0 || endOffset < startOffset) {
                        continue;
                    }

                    startOffset = Math.min(startOffset, snapshot.length());
                    endOffset = Math.min(endOffset, snapshot.length());
                    OffsetRegion offsetRegion = OffsetRegion.fromBounds(startOffset, endOffset);
                    TrackingPositionRegion trackingRegion = snapshot.createTrackingRegion(offsetRegion, TrackingPositionRegion.Bias.Forward);
                    SnapshotPositionRegion region = trackingRegion.getRegion(latestSnapshot);

                    try {
                        ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(syntaxError.getSeverity(), message, document, document.createPosition(region.getStart().getOffset()), document.createPosition(region.getEnd().getOffset()));
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

            HintsController.setErrors(document, "stringtemplate-syntax", errors);
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(TemplateParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS = Collections.emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Syntax Errors Highlighting", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider implements ParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTask(VersionedDocument document) {
            return new SyntaxErrorsHighlightingParserTask();
        }

    }

}
