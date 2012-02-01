/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.commenting;

import java.util.List;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.DocumentSpan;

/**
 *
 * @author Sam Harwell
 */
public interface Commenter {

    public List<DocumentSpan> commentSpans(List<DocumentSpan> spans) throws BadLocationException;

    public List<DocumentSpan> uncommentSpans(List<DocumentSpan> spans) throws BadLocationException;
    
}
