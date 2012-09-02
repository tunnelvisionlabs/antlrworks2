/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

@NbBundle.Messages({
    "StringTemplateResolver=StringTemplate Files",
    "CTL_SourceTabCaption=&Source"
})
@MIMEResolver.ExtensionRegistration(
    position=100,
    displayName="#StringTemplateResolver",
    extension={"st", "stg"},
    mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE,
    showInFileChooser="#StringTemplateResolver")
public class StringTemplateFileTypeDataObject extends MultiDataObject {

    public StringTemplateFileTypeDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor(StringTemplateEditorKit.TEMPLATE_MIME_TYPE, true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
        displayName="#CTL_SourceTabCaption",
        iconBase="",
        persistenceType=TopComponent.PERSISTENCE_ONLY_OPENED,
        preferredID="",
        mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE,
        position=2000)
    public static MultiViewEditorElement createMultiViewEditorElement(Lookup context) {
        return new MultiViewEditorElement(context);
    }
}
