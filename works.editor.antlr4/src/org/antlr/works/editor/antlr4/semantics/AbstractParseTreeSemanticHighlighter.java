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
package org.antlr.works.editor.antlr4.semantics;

import java.util.concurrent.Callable;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractParseTreeSemanticHighlighter<Listener extends ParseTreeListener<Token>> extends AbstractSemanticHighlighter<ParserRuleContext<Token>> {

    protected AbstractParseTreeSemanticHighlighter(@NonNull StyledDocument document, ParserDataDefinition<ParserRuleContext<Token>> semanticDataDefinition) {
        super(document, semanticDataDefinition);
    }

    protected abstract Listener createListener();

    protected abstract void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Listener listener);

    @Override
    protected Callable<Void> createAnalyzerTask(final ParserData<? extends ParserRuleContext<Token>> parserData) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                final Listener listener = createListener();

                try {
                    ParseTreeWalker.DEFAULT.walk(listener, parserData.getData());
                } catch (RuntimeException ex) {
                    Exceptions.printStackTrace(ex);
                    throw ex;
                }

                ((BaseDocument)getDocument()).render(new Runnable() {
                    @Override
                    public void run() {
                        getContainer().clear();
                        DocumentSnapshot sourceSnapshot = parserData.getSnapshot();
                        DocumentSnapshot currentSnapshot = sourceSnapshot.getVersionedDocument().getCurrentSnapshot();
                        updateHighlights(getContainer(), sourceSnapshot, currentSnapshot, listener);
                    }
                });

                return null;
            }
        };
    }
}
