/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.fold;

import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldOperation;

/**
 *
 * @author Sam Harwell
 */
public class AbstractFoldManager implements FoldManager {

    private FoldOperation operation;
    final ArrayList<Fold> currentFolds = new ArrayList<>();

    public static AbstractFoldManager getFoldManager(VersionedDocument document) {
        return (AbstractFoldManager)document.getProperty(AbstractFoldManager.class);
    }

    public FoldOperation getOperation() {
        return operation;
    }

    @Override
    public void init(FoldOperation operation) {
        this.operation = operation;
        Document document = operation.getHierarchy().getComponent().getDocument();
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        versionedDocument.putProperty(AbstractFoldManager.class, this);
    }

    @Override
    public void initFolds(FoldHierarchyTransaction transaction) {
    }

    @Override
    public void insertUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void removeUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void changedUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void removeEmptyNotify(Fold emptyFold) {
        removeDamagedNotify(emptyFold);
    }

    @Override
    public void removeDamagedNotify(Fold damagedFold) {
        currentFolds.remove(damagedFold);
    }

    @Override
    public void expandNotify(Fold expandedFold) {
    }

    @Override
    public void release() {
        operation = null;
        currentFolds.clear();
    }
}
