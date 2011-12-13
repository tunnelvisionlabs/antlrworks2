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
package org.antlr.works.editor.st4.fold;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.works.editor.st4.parser.TemplateGroupWrapper;
import org.antlr.works.editor.st4.parser.TemplateParser;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.stringtemplate.v4.ST.RegionType;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.misc.Interval;

/**
 *
 * @author Sam Harwell
 */
public class TemplateFoldManagerTask extends ParserResultTask<TemplateParser.TemplateParserResult> {

    @Override
    @SuppressWarnings("fallthrough")
    public void run(TemplateParser.TemplateParserResult result, SchedulerEvent event) {
        FileObject fileObject = result.getSnapshot().getSource().getFileObject();
        final TemplateFoldManager foldManager = TemplateFoldManager.getFoldManager(fileObject);
        if (foldManager == null) {
            return;
        }

        StyledDocument document = (StyledDocument)result.getSnapshot().getSource().getDocument(false);
        if (document == null) {
            return;
        }

        // calculate the folds
        final List<FoldInfo> folds = new ArrayList<FoldInfo>();
        TemplateParser.TemplateGroupRuleReturnScope parseResult = result.getResult();
        if (parseResult == null) {
            return;
        }

        for (TemplateGroupWrapper.TemplateInformation templateInfo : parseResult.getGroup().getTemplateInformation()) {
            CompiledST template = templateInfo.getTemplate();
            if (template.isAnonSubtemplate) {
                continue;
            }

            if (template.isRegion && template.regionDefType != RegionType.EXPLICIT) {
                continue;
            }

            Interval sourceInterval = templateInfo.getGroupInterval();
            DocumentSpan span;
            try {
                span = new DocumentSpan(document, sourceInterval.a, sourceInterval.b + 1);
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
                continue;
            }

            if (span.getStart().getContainingLine().getLineNumber() == span.getEnd().getContainingLine().getLineNumber()) {
                continue;
            }

            FoldInfo info = new FoldInfo(document, span.getStart().getOffset(), span.getEnd().getOffset(), "...");
            folds.add(info);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FoldOperation operation = foldManager.getOperation();
                operation.getHierarchy().lock();
                try{
                    FoldHierarchyTransaction transaction = operation.openTransaction();
                    synchronized (foldManager.currentFolds) {
                        for (Fold fold : foldManager.currentFolds) {
                            operation.removeFromHierarchy(fold, transaction);
                        }

                        foldManager.currentFolds.clear();

                        for (FoldInfo foldInfo : folds) {
                            FoldType foldType = new FoldType("code-block");
                            String description = foldInfo.blockHint;
                            boolean collapsed = false;
                            int startOffset = foldInfo.startIndex;
                            int endOffset = foldInfo.stopIndex;
                            int startGuardedLength = 0;
                            int endGuardedLength = 0;
                            try {
                                Fold fold = operation.addToHierarchy(foldType, description, collapsed, startOffset, endOffset, startGuardedLength, endGuardedLength, foldInfo, transaction);
                                foldManager.currentFolds.add(fold);
                            } catch (BadLocationException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        }
                    }
                    transaction.commit();
                } finally {
                    operation.getHierarchy().unlock();
                }
            }
        });
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

    public static class FoldInfo {
        private final StyledDocument document;
        private final int startIndex;
        private final int stopIndex;
        private final String blockHint;
        private final String preview;

        private FoldInfo(StyledDocument document, int startIndex, int stopIndex, String blockHint) {
            this.document = document;
            this.startIndex = startIndex;
            this.stopIndex = stopIndex;
            this.blockHint = blockHint;

            @SuppressWarnings("LocalVariableHidesMemberVariable")
            String preview;
            try {
                preview = document.getText(startIndex, stopIndex - startIndex);
            } catch (BadLocationException ex) {
                preview = "";
                Exceptions.printStackTrace(ex);
            }

            this.preview = preview;
        }

    }

}
