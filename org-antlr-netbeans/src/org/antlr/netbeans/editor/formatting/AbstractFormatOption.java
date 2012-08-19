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
public abstract class AbstractFormatOption {

    private final String _name;

    public AbstractFormatOption(@NonNull String name) {
        Parameters.notWhitespace("name", name);
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public abstract String getDefaultValueAsString();

    public abstract String getValueAsString(Preferences preferences);

}
