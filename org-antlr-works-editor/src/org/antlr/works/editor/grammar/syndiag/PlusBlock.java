/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlusBlock extends Block {

    public static int POSITIVE_LOOPBACK_HEIGHT = 20;

    private final boolean _greedy;

    public PlusBlock(boolean greedy) {
        super(greedy ? POSITIVE_LOOPBACK_HEIGHT : 0, greedy ? 0 : POSITIVE_LOOPBACK_HEIGHT);
        _greedy = greedy;
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

            int loopbackPositionY;
            if (_greedy) {
                loopbackPositionY = getY() + getTopGutterHeight() / 2;
            }
            else {
                loopbackPositionY = getHeight() - getBottomGutterHeight() / 2;
            }

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
                if (_greedy) {
                    Diagram.drawArrow(leftJoinLineX, topOfJoinLineY, Math.PI / 2, g2);
                }
                else {
                    Diagram.drawArrow(leftJoinLineX, topOfJoinLineY, -Math.PI / 2, g2);
                }
            }
        } finally {
            if (g2 != null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, prevAaHint);
            }
        }
	}
}
