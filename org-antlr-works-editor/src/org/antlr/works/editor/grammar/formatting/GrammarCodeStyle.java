/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.formatting;

import java.util.prefs.Preferences;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.formatting.CodeStyle;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public final class GrammarCodeStyle extends CodeStyle {

    static {
        GrammarFormatOptions.codeStyleFactory = new Factory();
    }

    private GrammarCodeStyle(@NonNull Preferences preferences) {
        super(preferences);
    }

    public synchronized static GrammarCodeStyle getDefault(Document document) {
        Preferences preferences = CodeStylePreferences.get(document, GrammarEditorKit.GRAMMAR_MIME_TYPE).getPreferences();
        return new GrammarCodeStyle(preferences);
    }

    public synchronized static GrammarCodeStyle getDefault(FileObject file) {
        Preferences preferences = CodeStylePreferences.get(file, GrammarEditorKit.GRAMMAR_MIME_TYPE).getPreferences();
        return new GrammarCodeStyle(preferences);
    }

    public static final class Factory implements GrammarFormatOptions.CodeStyleFactory {
        @Override
        public GrammarCodeStyle create(Preferences preferences) {
            return new GrammarCodeStyle(preferences);
        }
    }

}
