/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar;

import org.netbeans.editor.Acceptor;
import org.netbeans.editor.AcceptorFactory;

/**
 *
 * @author Sam Harwell
 */
public abstract class NbGrammarSettingsInitializer {

    public static Acceptor getIdentifierAcceptor() {
        return AcceptorFactory.JAVA_IDENTIFIER;
    }

    private NbGrammarSettingsInitializer() {
    }
}
