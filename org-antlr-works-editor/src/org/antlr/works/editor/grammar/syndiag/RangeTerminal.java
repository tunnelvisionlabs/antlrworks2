/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
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
