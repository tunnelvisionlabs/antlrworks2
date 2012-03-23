/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;

/**
 *
 * @author Sam Harwell
 */
public class RangeTerminal extends Terminal {
    private final String startLabel;
    private final String endLabel;
    private final Color foregroundColor;

    public RangeTerminal(String startLabel, String endLabel, SnapshotPositionRegion sourceSpan) {
        super(startLabel + ".." + endLabel, sourceSpan);
        this.startLabel = startLabel;
        this.endLabel = endLabel;

        AttributeSet identifierAttributes = Diagram.lookupAttributes("identifier");
        Color foreground = identifierAttributes != null ? (Color)identifierAttributes.getAttribute(StyleConstants.Foreground) : null;
        if (foreground == null) {
            foreground = Color.BLACK;
        }

        this.foregroundColor = foreground;
    }

    @Override
    protected void paintLabel(Graphics g, int x, int y) {
        AttributedString attributedLabel = new AttributedString(getLabel());
        attributedLabel.addAttribute(TextAttribute.FONT, g.getFont());
        attributedLabel.addAttribute(TextAttribute.FOREGROUND, foregroundColor, startLabel.length(), startLabel.length() + 2);
        g.drawString(attributedLabel.getIterator(), x, y);
    }

}
