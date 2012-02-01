/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.Locale;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class DeclarationCompletionItem extends GrammarCompletionItem {
    private final Document document;
    private final TrackingPositionRegion applicableTo;

    private String leftText;

    public DeclarationCompletionItem(@NonNull Document document, @NonNull TrackingPositionRegion applicableTo) {
        Parameters.notNull("document", document);
        Parameters.notNull("applicableTo", applicableTo);
        this.document = document;
        this.applicableTo = applicableTo;
    }

    public Document getDocument() {
        return document;
    }

    public TrackingPositionRegion getApplicableTo() {
        return applicableTo;
    }

    @Override
    public boolean allowInitialSelection() {
        return false;
    }

    @Override
    protected String getSortTextImpl() {
        return getInsertPrefix().toString().toLowerCase(Locale.getDefault());
    }

    @Override
    public int getSortPriority() {
        return DECLARATION_SORT_PRIORITY;
    }

    @Override
    public CharSequence getInsertPrefix() {
        VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(getDocument());
        DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
        SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
        return applicableSpan.getText();
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();

            builder.append(METHOD_COLOR);
            builder.append(getInsertPrefix());
            builder.append(COLOR_END);

            leftText = builder.toString();
        }

        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        return "Label";
    }
}
