/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Terence Parr
 *  Copyright (c) 2011 Sam Harwell
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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlusBlock extends Block {

    public static int POSITIVE_LOOPBACK_HEIGHT = 20;

    public PlusBlock() {
        this(0);
    }

    public PlusBlock(int gutterBelowHeight) {
        super(POSITIVE_LOOPBACK_HEIGHT, gutterBelowHeight);
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

        Graphics2D g2 = null;
        Object prevAaHint = null;
        if (g instanceof Graphics2D) {
            g2 = (Graphics2D)g;
            prevAaHint = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        }

        try {
            if (g2 != null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }

            int loopbackPositionY = getY() + getInsets().top / 2;

            int leftJoinLineX = getLeftJoinLineX();
            int rightJoinLineX = getRightJoinLineX();
            int topOfJoinLineY = getTopOfJoinLineY();

            // extend vertical join line on left connecting alts
            g.drawLine(leftJoinLineX, loopbackPositionY, leftJoinLineX, topOfJoinLineY);

            // draw horizontal loopback line
            g.drawLine(leftJoinLineX, loopbackPositionY, rightJoinLineX, loopbackPositionY);

            // extend vertical line on right connecting alts
            g.drawLine(rightJoinLineX, loopbackPositionY, rightJoinLineX, topOfJoinLineY);

            if (g2 != null) {
                Diagram.drawArrow(leftJoinLineX, topOfJoinLineY, Math.PI / 2, g2);
            }
        } finally {
            if (g2 != null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, prevAaHint);
            }
        }
	}
}
