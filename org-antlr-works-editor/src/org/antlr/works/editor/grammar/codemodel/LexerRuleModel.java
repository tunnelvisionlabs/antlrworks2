/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import org.netbeans.api.annotations.common.CheckForNull;

/**
 * Provides an interface model for a lexer rule. Extends {@link RuleModel} to
 * provide information about the token type, if any, that is created for the
 * lexer rule.
 *
 * @author Sam Harwell
 */
public interface LexerRuleModel extends RuleModel {

    /**
     * Determines if the lexer rule is declared as a {@code fragment} rule.
     */
    boolean isFragment();

    /**
     * Gets information about the token type created for this lexer rule. Some
     * lexer rules do not have an associated token type; for these rules this
     * method returns {@code null}.
     * <p/>
     * The following are some reasons why a lexer rule would not have an
     * associated token type.
     *
     * <ul>
     *  <li>The rule is a {@code fragment} rule.</li>
     *  <li>The rule is followed by a {@code more}, {@code skip}, and/or {@code type} command.</li>
     * </ul>
     *
     * @return Returns a {@link TokenData} instance with information about the
     * token type defined by this lexer rule, or {@code null} if no token type
     * is created for this rule.
     */
    @CheckForNull
    TokenData getTokenData();

}
