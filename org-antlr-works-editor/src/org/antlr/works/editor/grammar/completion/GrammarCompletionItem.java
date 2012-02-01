/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.works.editor.antlr4.completion.AbstractCompletionItem;

/**
 *
 * @author Sam Harwell
 */
public abstract class GrammarCompletionItem extends AbstractCompletionItem {

    public static final int KEYWORD_SORT_PRIORITY = 100;
    public static final int RULE_SORT_PRIORITY = 100;
    public static final int ELEMENT_REFERENCE_SORT_PRIORITY = 100;
    public static final int PROPERTY_SORT_PRIORITY = 100;
    public static final int MEMBER_SORT_PRIORITY = 100;
    public static final int DECLARATION_SORT_PRIORITY = -100;

    public static final String KEYWORD_COLOR = "<font color=#000099>"; //NOI18N
    public static final String FIELD_COLOR = "<font color=#008618>"; //NOI18N
    public static final String METHOD_COLOR = "<font color=#000000>"; //NOI18N
    public static final String PARAMETER_NAME_COLOR = "<font color=#a06001>"; //NOI18N
    public static final String REFERENCE_COLOR = "<font color=#a06001>"; //NOI18N

}
