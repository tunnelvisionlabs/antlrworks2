/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.formatting;

import java.util.prefs.Preferences;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.formatting.CodeStyle;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public final class TemplateCodeStyle extends CodeStyle {

    static {
        TemplateFormatOptions.codeStyleFactory = new Factory();
    }

    private TemplateCodeStyle(@NonNull Preferences preferences) {
        super(preferences);
    }

    public synchronized static TemplateCodeStyle getDefault(Document document) {
        Preferences preferences = CodeStylePreferences.get(document, StringTemplateEditorKit.TEMPLATE_MIME_TYPE).getPreferences();
        return new TemplateCodeStyle(preferences);
    }

    public synchronized static TemplateCodeStyle getDefault(FileObject file) {
        Preferences preferences = CodeStylePreferences.get(file, StringTemplateEditorKit.TEMPLATE_MIME_TYPE).getPreferences();
        return new TemplateCodeStyle(preferences);
    }

    public static final class Factory implements TemplateFormatOptions.CodeStyleFactory {
        @Override
        public TemplateCodeStyle create(Preferences preferences) {
            return new TemplateCodeStyle(preferences);
        }
    }

}
