/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.spi.editor.completion.CompletionDocumentation;
import com.tvl.spi.editor.completion.CompletionItem;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class CompletionQueryResult {

    private final AbstractCompletionQuery query;

    public CompletionQueryResult(AbstractCompletionQuery query) {
        Parameters.notNull("query", query);
        this.query = query;
    }

    public JTextComponent getComponent() {
        return query.getComponent();
    }

    public int getQueryType() {
        return query.getQueryType();
    }

    public int getCaretOffset() {
        return query.getCaretOffset();
    }

    public boolean isExtend() {
        return query.isExtend();
    }

    public boolean isExplicitQuery() {
        return query.isExplicitQuery();
    }

    public List<? extends CompletionItem> getResults() {
        return query.results;
    }

    public boolean isPossibleDeclaration() {
        return query.possibleDeclaration;
    }

    public CompletionDocumentation getDocumentation() {
        return query.getDocumentation();
    }

    public CompletionToolTip getToolTip() {
        return query.getToolTip();
    }

    public TrackingPositionRegion getApplicableTo() {
        return query.getApplicableTo();
    }

    public int getToolTipOffset() {
        return query.getToolTipOffset();
    }

}
