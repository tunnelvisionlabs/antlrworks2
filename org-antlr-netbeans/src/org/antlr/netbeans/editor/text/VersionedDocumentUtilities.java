/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.impl.NbVersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class VersionedDocumentUtilities {

    private static final Object lock = new Object();

    private VersionedDocumentUtilities() {
    }

    public static @NonNull VersionedDocument getVersionedDocument(@NonNull FileObject fileObject) {
        Parameters.notNull("fileObject", fileObject);
        return new NbVersionedDocument(fileObject);
    }

    public static @NonNull VersionedDocument getVersionedDocument(@NonNull Document document) {
        Parameters.notNull("document", document);

        if (!(document instanceof BaseDocument)) {
            throw new UnsupportedOperationException("Incompatible document type.");
        }

        synchronized (lock) {
            VersionedDocument result = (VersionedDocument)document.getProperty(VersionedDocument.class);
            if (result == null) {
                result = new NbVersionedDocument((BaseDocument)document);
                document.putProperty(VersionedDocument.class, result);
            }

            return result;
        }
    }

}
