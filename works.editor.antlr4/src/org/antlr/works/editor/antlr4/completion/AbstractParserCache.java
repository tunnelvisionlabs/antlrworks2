/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.antlr4.completion;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractParserCache<T extends Parser> {

    private final Deque<Reference<T>> parsers = new ArrayDeque<Reference<T>>();

    public T getParser(TokenStream input) {
        T parser = null;
        synchronized (parsers) {
            while (parser == null && !parsers.isEmpty()) {
                parser = parsers.poll().get();
            }
        }

        if (parser != null) {
            parser.setInputStream(input);
        } else {
            parser = createParser(input);
        }

        return parser;
    }

    public void putParser(T parser) {
        parsers.add(new SoftReference<T>(parser));
    }

    protected abstract T createParser(TokenStream input);

}
