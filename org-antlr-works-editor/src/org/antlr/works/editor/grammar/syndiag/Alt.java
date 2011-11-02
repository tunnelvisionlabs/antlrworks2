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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Alt extends JPanel implements Element {

    public static final int ELEMENT_SEP = 20;
    public static final int EMPTY_ALT_HEIGHT = 10;

    public Alt() {
        super(new GridBagLayout());
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        if (getComponentCount() == 0) {
            return new Dimension(ELEMENT_SEP, EMPTY_ALT_HEIGHT);
        }

        return super.getPreferredSize();
    }

    @Override
    public Component add(Component comp) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        if (comp instanceof Block) {
            constraints.insets = new Insets(Block.ALT_SEP / 2, 0, Block.ALT_SEP / 2, 0);
        } else {
            constraints.insets = new Insets(Block.ALT_SEP / 2, ELEMENT_SEP / 2, Block.ALT_SEP / 2, ELEMENT_SEP / 2);
        }

        add(comp, constraints);
        return comp;
    }

    @Override
    public void updatePositions() {
        for (Component child : getComponents()) {
            if (child instanceof Element) {
                ((Element)child).updatePositions();
            }
        }

        GridBagLayout layout = (GridBagLayout)getLayout();
        int connectionPoint = getConnectionPointY() - getY();
        for (Component child : getComponents()) {
            if (!(child instanceof Element)) {
                continue;
            }

            int childConnectionPoint = ((Element)child).getConnectionPointY();
            if (childConnectionPoint == connectionPoint) {
                continue;
            }

            GridBagConstraints childConstraints = layout.getConstraints(child);
            childConstraints.insets.top -= (childConnectionPoint - connectionPoint);
            layout.setConstraints(child, childConstraints);
            invalidate();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (Rule.OUTLINE_ELEMENTS) {
            Color oldColor = g.getColor();
            g.setColor(new Color(Color.red.getRed(), Color.red.getGreen(), Color.red.getBlue(), 0xFF >> 2));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(oldColor);
        }

        if (getComponentCount() > 0) {
            Component[] elems = getComponents();
            Element prev = null;
            for (Component elem : elems) {
                Element n = (Element)elem;
                if (prev != null) {
                    connect(prev, n, g);
                }
                prev = n;
            }
        } else {
            g.drawLine(0, getHeight() / 2, getWidth() - 1, getHeight() / 2);
        }
    }

    public void connect(Element a, Element b, Graphics g) {
        Rectangle aBounds = a.getBounds();
        Rectangle bBounds = b.getBounds();
        int y1 = a.getConnectionPointY();
        int y2 = b.getConnectionPointY();
        g.drawLine((int)(aBounds.x + aBounds.getWidth()), y1, bBounds.x, y2);
    }

    public int getLeftEdge() {
        Graphics g = getGraphics();
        Component[] elems = getComponents();
        return elems[0].getBounds().x;
    }

    @Override
    public int getConnectionPointY() {
        if (getComponentCount() == 0) {
            return (int)getBounds().getCenterY();
        }

        int connectionPoint = 0;
        for (Component child : getComponents()) {
            connectionPoint = Math.max(connectionPoint, getY() + ((Element)child).getConnectionPointY());
        }

        return connectionPoint;
    }

}
