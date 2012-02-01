/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.fold;

import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldManagerFactory;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=FoldManagerFactory.class)
public class TemplateFoldManagerFactory implements FoldManagerFactory {

    @Override
    public FoldManager createFoldManager() {
        return new TemplateFoldManager();
    }

}
