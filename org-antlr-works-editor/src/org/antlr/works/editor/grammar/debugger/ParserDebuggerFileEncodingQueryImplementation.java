/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
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
@MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=FileEncodingQueryImplementation.class)
public class ParserDebuggerFileEncodingQueryImplementation extends FileEncodingQueryImplementation {

    @Override
    public Charset getEncoding(FileObject file) {
        if (file.getMIMEType().equals(ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE)) {
            return Charset.forName(AbstractGrammarDebuggerEditorKit.UTF_8);
        }

        return null;
    }

}
