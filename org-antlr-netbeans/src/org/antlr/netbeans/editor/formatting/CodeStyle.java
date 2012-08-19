/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.formatting;

import java.util.prefs.Preferences;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CodeStyle {

    @NonNull
    private final Preferences _preferences;

    public CodeStyle(@NonNull Preferences preferences) {
        Parameters.notNull("preferences", preferences);
        this._preferences = preferences;
    }

    @NonNull
    protected final Preferences getPreferences() {
        return _preferences;
    }

    public boolean expandTabToSpaces() {
        return FormatOptions.EXPAND_TABS.getValue(_preferences);
    }

    public int getTabSize() {
        return FormatOptions.TAB_SIZE.getValue(_preferences);
    }

    public int getSpacesPerTab() {
        return FormatOptions.SPACES_PER_TAB.getValue(_preferences);
    }

    public int getIndentSize() {
        int indentLevel = FormatOptions.INDENT_SHIFT_WIDTH.getValue(_preferences);
        if (indentLevel <= 0) {
            if (expandTabToSpaces()) {
                indentLevel = FormatOptions.SPACES_PER_TAB.getValue(_preferences);
            } else {
                indentLevel = getTabSize();
            }
        }

        return indentLevel;
    }

    public int getTextLimitWidth() {
        return FormatOptions.TEXT_LIMIT_WIDTH.getValue(_preferences);
    }

}
