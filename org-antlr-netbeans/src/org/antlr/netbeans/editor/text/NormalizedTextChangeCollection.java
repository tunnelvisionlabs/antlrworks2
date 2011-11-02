/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import java.util.List;

/**
 *
 * @author sam
 */
public interface NormalizedTextChangeCollection extends List<TextChange> {

    public boolean getIncludesLineChanges();

    public boolean isReadOnly();

}
