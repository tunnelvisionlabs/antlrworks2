/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.formatting;

import org.netbeans.api.editor.settings.SimpleValueNames;

/**
 *
 * @author Sam Harwell
 */
public class FormatOptions {

    public static final BooleanFormatOption EXPAND_TABS = new BooleanFormatOption(SimpleValueNames.EXPAND_TABS, true);
    public static final IntFormatOption TAB_SIZE = new IntFormatOption(SimpleValueNames.TAB_SIZE, 4);
    public static final IntFormatOption SPACES_PER_TAB = new IntFormatOption(SimpleValueNames.SPACES_PER_TAB, 4);
    public static final IntFormatOption INDENT_SHIFT_WIDTH = new IntFormatOption(SimpleValueNames.INDENT_SHIFT_WIDTH, 4);
    public static final IntFormatOption TEXT_LIMIT_WIDTH = new IntFormatOption(SimpleValueNames.TEXT_LIMIT_WIDTH, 80);

}
