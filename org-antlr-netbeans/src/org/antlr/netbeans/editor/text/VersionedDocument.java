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
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public interface VersionedDocument {

    public @NonNull DocumentSnapshot getCurrentSnapshot();

    public @CheckForNull Document getDocument();

    public @CheckForNull FileObject getFileObject();

    public @CheckForNull String getMimeType();

    public @CheckForNull Object getProperty(Object key);

    public @CheckForNull Object putProperty(Object key, Object value);

}
