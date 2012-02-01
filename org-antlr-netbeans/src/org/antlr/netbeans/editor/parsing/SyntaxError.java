/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.parsing;

import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.spi.editor.hints.Severity;

/**
 *
 * @author Sam Harwell
 */
public class SyntaxError {
    private final SnapshotPositionRegion location;
    private final String message;
    private final Severity severity;

    public SyntaxError(SnapshotPositionRegion location, String message, Severity severity) {
        this.location = location;
        this.message = message;
        this.severity = severity;
    }

    public SnapshotPositionRegion getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }
}
