/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
 * @param <T>
 */
public abstract class AbstractParserCache<T extends Parser> {

    private final Deque<Reference<T>> parsers = new ArrayDeque<>();

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
        synchronized (parsers) {
            parsers.add(new SoftReference<>(parser));
        }
    }

    protected abstract T createParser(TokenStream input);

}
