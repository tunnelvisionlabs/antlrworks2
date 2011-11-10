/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import javax.swing.text.Document;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TextBuffer {

    public @NonNull TextSnapshot getCurrentSnapshot();

    public @NonNull Document getDocument();

}
