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
import org.antlr.netbeans.editor.formatting.CategorySupport;
import org.antlr.netbeans.editor.formatting.FormatOptions;

/**
 *
 * @author Sam Harwell
 */
public class TemplateFormatOptions extends FormatOptions {

    public static CodeStyleFactory codeStyleFactory;

    public static final CategorySupport.PreviewFormatter PREVIEW_FORMATTER =
        new CategorySupport.PreviewFormatter() {

        @Override
        public String reformat(String text, Preferences preferences) {
            //TemplateCodeStyle codeStyle = codeStyleFactory.create(preferences);
            //return TemplateReformatTask.reformat(text, codeStyle);
            return text;
        }
    };

    public static interface CodeStyleFactory {
        public TemplateCodeStyle create(Preferences preferences);
    }

}
