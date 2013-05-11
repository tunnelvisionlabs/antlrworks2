/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.classification;

import org.antlr.netbeans.editor.tagging.Tag;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <Token>
 */
public class TokenTag<Token> implements Tag {
    private final Token token;

    public TokenTag(@NonNull Token token) {
        Parameters.notNull("token", token);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
