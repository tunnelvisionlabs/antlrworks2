/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import javax.swing.text.Document;

/**
 *
 * @author sam
 */
public interface TextBuffer {

    public TextSnapshot getCurrentSnapshot();

    public Document getDocument();

}
