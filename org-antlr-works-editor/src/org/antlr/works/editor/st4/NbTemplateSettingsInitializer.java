/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4;

import org.netbeans.editor.Acceptor;

/**
 *
 * @author Sam Harwell
 */
public abstract class NbTemplateSettingsInitializer {

    private static final Acceptor IDENTIFIER_ACCEPTOR = new Acceptor() {
        @Override
        public boolean accept(char ch) {
            return ch == '-' || ch == '_' || Character.isLetterOrDigit(ch);
        }
    };

    public static Acceptor getIdentifierAcceptor() {
        return IDENTIFIER_ACCEPTOR;
    }

    private NbTemplateSettingsInitializer() {
    }
}
