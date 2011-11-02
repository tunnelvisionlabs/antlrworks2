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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Block extends JPanel implements Element {

    public static final int ALT_SEP = 10;
    public static final int BLOCK_PADDING = 16;

    public Block() {
        this(0, 0);
    }

    public Block(int gutterAboveHeight, int gutterBelowHeight) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(gutterAboveHeight, BLOCK_PADDING, gutterBelowHeight, BLOCK_PADDING));
        setOpaque(false);
    }

    public int getLeftGutterWidth() {
        return getInsets().left;
    }

    public int getRightGutterWidth() {
        return getInsets().right;
    }

    public int getTopGutterHeight() {
        return getInsets().top;
    }

    public int getBottomGutterHeight() {
        return getInsets().bottom;
    }

    public int getLeftJoinLineX() {
        int leftx = getLeftGutterWidth() + Alt.ELEMENT_SEP / 2;
        return getX() + leftx / 2;
    }

    public int getRightJoinLineX() {
        int rightx = getX() + getWidth() - getRightGutterWidth() - Alt.ELEMENT_SEP / 2;
        int brwidth = getRightGutterWidth() + Alt.ELEMENT_SEP / 2;
        return rightx + brwidth / 2;
    }

    public int getTopOfJoinLineY() {
        if (getComponentCount() == 0) {
            return (int)getBounds().getCenterY();
        }

        Component component = getComponent(0);
        if (component instanceof Element) {
            return ((Element)component).getConnectionPointY();
        } else {
            return (int)component.getBounds().getCenterY();
        }
    }

    public int getBottomJoinLineY() {
        if (getComponentCount() == 0) {
            return (int)getBounds().getCenterY();
        }

        Component component = getComponent(getComponentCount() - 1);
        if (component instanceof Element) {
            return ((Element)component).getConnectionPointY();
        } else {
            return (int)component.getBounds().getCenterY();
        }
    }

    @Override
    public Component add(Component comp) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(comp, constraints);
        return comp;
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

            if (Rule.OUTLINE_ELEMENTS) {
                Color oldColor = g.getColor();
                g.setColor(new Color(Color.blue.getRed(), Color.blue.getGreen(), Color.blue.getBlue(), 0xFF >> 2));
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g.setColor(oldColor);
            }

            Component[] elems = getComponents();
            int leftx = getLeftGutterWidth() + Alt.ELEMENT_SEP / 2;
            int rightx = getWidth() - getRightGutterWidth() - Alt.ELEMENT_SEP / 2;
            int brwidth = getRightGutterWidth() + Alt.ELEMENT_SEP / 2;

            int leftJoinLineX = 0 + leftx / 2;
            int rightJoinLineX = rightx + brwidth / 2;

            for (Component elem : elems) {
                Element alt = (Element)elem;
                g.drawLine(leftJoinLineX, alt.getConnectionPointY(), 0 + leftx, alt.getConnectionPointY());
                if (((JComponent)elem).getComponentCount() > 0) {
                    Component lastElem = ((JComponent)alt).getComponent(((JComponent)alt).getComponentCount() - 1);
                    Rectangle lastBounds = lastElem.getBounds();
                    g.drawLine(getLeftGutterWidth() + lastBounds.x + lastBounds.width,
                               alt.getConnectionPointY(),
                               rightJoinLineX,
                               alt.getConnectionPointY());
                } else {
                    g.drawLine(elem.getX() + elem.getWidth(), alt.getConnectionPointY(), rightJoinLineX, alt.getConnectionPointY());
                }
            }

            if (elems.length == 0) {
                return;
            }

            // draw vertical line on left connecting alts
            int topOfJoinLineY = getTopOfJoinLineY();
            int bottomJoinLineY = getBottomJoinLineY();
            g.drawLine(leftJoinLineX, topOfJoinLineY, leftJoinLineX, bottomJoinLineY);

            // draw horizontal line from left edge to left elementin first alt
            g.drawLine(0, topOfJoinLineY, leftx / 2, topOfJoinLineY);

            // draw vertical line on right connecting alts
            g.drawLine(rightJoinLineX, topOfJoinLineY, rightJoinLineX, bottomJoinLineY);

            // draw horizontal line from right element of first alt to right edge
            g.drawLine(rightx, topOfJoinLineY, getX() + getWidth(), topOfJoinLineY);

            if (elems.length > 1 && g2 != null) {
                Diagram.drawArrow(rightJoinLineX, topOfJoinLineY, -Math.PI / 2, g2);
            }
        } finally {
            if (g2 != null) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, prevAaHint);
            }
        }
    }

    @Override
    public void updatePositions() {
        for (Component child : getComponents()) {
            if (child instanceof Element) {
                ((Element)child).updatePositions();
            }
        }
    }

    @Override
    public int getConnectionPointY() {
        if (getComponentCount() > 0) {
            Component firstComponent = getComponent(0);
            if (firstComponent instanceof Element) {
                return getY() + ((Element)firstComponent).getConnectionPointY();
            }
        }

        return (int)getBounds().getCenterY();
    }

}
