/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.stringtemplate.v4.compiler.GroupParser;

public class GroupParserWrapper extends GroupParser {

    private final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();

    public GroupParserWrapper(TokenStream input) {
        super(input);
    }

    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        //String header = getErrorHeader(e);
        String message = getErrorMessage(e, tokenNames);
        syntaxErrors.add(new SyntaxError(e, message));

        super.displayRecognitionError(tokenNames, e);
    }

    public static class SyntaxError {
        private final RecognitionException exception;
        private final String message;

        public SyntaxError(RecognitionException exception, String message) {
            this.exception = exception;
            this.message = message;
        }

        public RecognitionException getException() {
            return exception;
        }

        public String getMessage() {
            return message;
        }
    }
    
}
