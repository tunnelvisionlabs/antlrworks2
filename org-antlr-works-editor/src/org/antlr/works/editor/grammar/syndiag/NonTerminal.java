/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import org.antlr.netbeans.editor.text.SnapshotPositionRegion;

public class NonTerminal extends Node {
	public NonTerminal(String label, SnapshotPositionRegion sourceSpan) {
		super(label, sourceSpan, 12, 3, 30, Diagram.lookupAttributes(getCategory(label)));
	}
}
