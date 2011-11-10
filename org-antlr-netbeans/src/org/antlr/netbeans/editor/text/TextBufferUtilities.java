/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.impl.NbTextBuffer;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class TextBufferUtilities {

    private static final Object lock = new Object();

    private TextBufferUtilities() {
    }

    public static @NonNull TextBuffer getTextBufferForDocument(@NonNull Document document) {
        Parameters.notNull("document", document);

        if (!(document instanceof BaseDocument)) {
            throw new UnsupportedOperationException("Incompatible document type.");
        }

        synchronized (lock) {
            TextBuffer result = (TextBuffer)document.getProperty(TextBuffer.class);
            if (result == null) {
                result = new NbTextBuffer((BaseDocument)document);
                document.putProperty(TextBuffer.class, result);
            }
            
            return result;
        }
    }
}
