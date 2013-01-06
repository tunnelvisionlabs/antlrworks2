/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.spi.editor.completion.CompletionProvider;
import com.tvl.spi.editor.completion.CompletionTask;
import com.tvl.spi.editor.completion.support.AsyncCompletionTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.v4.runtime.Token;
import org.netbeans.editor.Utilities;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "GCP-imported-items=",
    "GCP-instance-members="
})
public abstract class AbstractCompletionProvider implements CompletionProvider {
    // -J-Dorg.antlr.works.editor.antlr4.completion.AbstractCompletionProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractCompletionProvider.class.getName());

    public static final int AUTO_QUERY_TYPE = 0x00010000;
    public static final int TRIGGERED_QUERY_TYPE = 0x00020000;

    @Override
    public int getAutoQueryTypes(JTextComponent component, String typedText) {
        if (typedText == null || typedText.length() != 1) {
            return 0;
        }

        int queryType = COMPLETION_QUERY_TYPE | AUTO_QUERY_TYPE;
        boolean triggered = getCompletionAutoPopupTriggers().indexOf(typedText.charAt(0)) >= 0;
        if (triggered) {
            queryType |= TRIGGERED_QUERY_TYPE;
        }

        if (triggered || (autoPopupOnIdentifierPart() && isIdentifierPart(typedText))) {
            int offset = component.getSelectionStart() - 1;
            Token contextToken = getContext(component, offset);
            if (contextToken == null) {
                return 0;
            }

            if (!triggered) {
                // the caret must be at the end of the identifier. note that the
                // offset is already 1 position before the caret, so no need to
                // add 1 to contextToken.getStopIndex().
                if (offset != contextToken.getStopIndex()) {
                    return 0;
                }

                // only trigger for the first character of the identifier
                if (contextToken.getStopIndex() > contextToken.getStartIndex()) {
                    return 0;
                }
            }

            if (isContext(contextToken, offset, queryType)) {
                return queryType;
            }
        }

        return 0;
    }

    @Override
    public CompletionTask createTask(int queryType, JTextComponent component) {
        if ((queryType & COMPLETION_QUERY_TYPE) != 0 || (queryType & TOOLTIP_QUERY_TYPE) != 0 || (queryType & DOCUMENTATION_QUERY_TYPE) != 0) {
            int caretOffset = component.getSelectionStart();
            boolean extend;
            try {
                int[] identifier = Utilities.getIdentifierBlock(component, caretOffset);
                extend = identifier != null && caretOffset > identifier[0] && caretOffset <= identifier[1];
            } catch (BadLocationException ex) {
                // this happens when the caret is before the first word of the document
                LOGGER.log(Level.WARNING, ex.getMessage(), ex);
                extend = false;
            }

            return new AsyncCompletionTask(createCompletionQuery(queryType, caretOffset, extend), component);
        }
    
        return null;
    }

    public Future<CompletionQueryResult> executeQuery(int queryType, JTextComponent component, int caretOffset, boolean extend) {
        final AbstractCompletionQuery query = createCompletionQuery(queryType, caretOffset, extend);
        query.prepareQuery(component);
        final Future<Void> task = query.query(component.getDocument(), caretOffset);
        if (task == null) {
            return null;
        }

        return new Future<CompletionQueryResult>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return task.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return task.isCancelled();
            }

            @Override
            public boolean isDone() {
                return task.isDone();
            }

            @Override
            public CompletionQueryResult get() throws InterruptedException, ExecutionException {
                task.get();
                return new CompletionQueryResult(query);
            }

            @Override
            public CompletionQueryResult get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                task.get(timeout, unit);
                return new CompletionQueryResult(query);
            }

        };
    }

    public abstract boolean autoPopupOnIdentifierPart();

    public boolean isIdentifierPart(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isJavaIdentifierPart(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public abstract String getCompletionAutoPopupTriggers();

    public abstract String getCompletionSelectors();

    protected abstract AbstractCompletionQuery createCompletionQuery(int queryType, int caretOffset, boolean extend);

    public Token getContext(JTextComponent component, int offset) {
        return getContext(component.getDocument(), offset);
    }

    public abstract Token getContext(Document document, int offset);

    public boolean isContext(JTextComponent component, int offset, int queryType) {
        return isContext(getContext(component, offset), offset, queryType);
    }

    public abstract boolean isContext(Token token, int offset, int queryType);

}
