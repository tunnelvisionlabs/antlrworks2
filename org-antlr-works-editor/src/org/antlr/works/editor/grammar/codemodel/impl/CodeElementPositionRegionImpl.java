/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CodeElementPositionRegionImpl implements CodeElementPositionRegion {

    @NonNull
    private final AbstractCodeElementModel _model;
    @NonNull
    private final OffsetRegion _region;
    @NonNull
    private final FileObject _fileObject;

    public CodeElementPositionRegionImpl(@NonNull AbstractCodeElementModel model, @NonNull OffsetRegion region) {
        Parameters.notNull("model", model);
        Parameters.notNull("region", region);

        this._model = model;
        this._region = region;

        FileModelImpl fileModel = model.getFile();
        if (fileModel == null) {
            throw new IllegalArgumentException();
        }

        FileObject fileObject = fileModel.getFileObject();
        if (fileObject == null) {
            throw new IllegalArgumentException();
        }

        this._fileObject = fileObject;
    }

    @Override
    public FileObject getFileObject() {
        return _fileObject;
    }

    @Override
    public OffsetRegion getOffsetRegion() {
        return _region;
    }

    @Override
    public SnapshotPositionRegion getSnapshotPositionRegion(boolean translateToOpenDocument) {
        // TODO: handle translation for already open documents
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(getFileObject());
        return new SnapshotPositionRegion(versionedDocument.getCurrentSnapshot(), _region);
    }

}
