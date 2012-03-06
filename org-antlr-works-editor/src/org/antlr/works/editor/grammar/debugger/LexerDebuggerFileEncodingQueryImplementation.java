/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.nio.charset.Charset;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.queries.FileEncodingQueryImplementation;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=FileEncodingQueryImplementation.class)
public class LexerDebuggerFileEncodingQueryImplementation extends FileEncodingQueryImplementation {
    private static final String UTF_8 = "UTF-8"; // NOI18N

    @Override
    public Charset getEncoding(FileObject file) {
        if (file.getMIMEType().equals(LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE)) {
            return Charset.forName(UTF_8);
        }

        return null;
    }

}
