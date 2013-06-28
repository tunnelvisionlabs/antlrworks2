/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.semantics;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;

/**
 *
 * @author Sam Harwell
 * @param <Listener>
 * @param <Data>
 */
public abstract class AbstractParseTreeSemanticHighlighter<Listener extends ParseTreeListener, Data> extends AbstractSemanticHighlighter<Data> {
    // -J-Dorg.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractParseTreeSemanticHighlighter.class.getName());

    protected AbstractParseTreeSemanticHighlighter(@NonNull StyledDocument document, ParserDataDefinition<? extends Data> semanticDataDefinition) {
        super(document, semanticDataDefinition);
    }

    protected abstract Listener createListener(@NonNull ParserData<? extends Data> parserData);

    protected abstract ParseTree getParseTree(@NonNull final ParserData<? extends Data> parserData);

    protected abstract void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Listener listener);

    protected ParseTreeWalker getParseTreeWalker(@NonNull final ParserData<? extends Data> parserData) {
        return ParseTreeWalker.DEFAULT;
    }

    @Override
    protected Callable<Void> createAnalyzerTask(@NonNull final ParserData<? extends Data> parserData) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                final Listener listener = createListener(parserData);
                if (listener == null) {
                    return null;
                }

                try {
                    ParseTreeWalker.DEFAULT.walk(listener, getParseTree(parserData));
                } catch (RuntimeException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while walking the parse tree.", ex);
                    throw ex;
                }

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ((BaseDocument)getDocument()).render(new Runnable() {

                            @Override
                            public void run() {
                                DocumentSnapshot sourceSnapshot = parserData.getSnapshot();
                                DocumentSnapshot currentSnapshot = sourceSnapshot.getVersionedDocument().getCurrentSnapshot();
                                updateHighlights(getContainer(), sourceSnapshot, currentSnapshot, listener);
                            }

                        });
                    }

                });

                return null;
            }
        };
    }

}
