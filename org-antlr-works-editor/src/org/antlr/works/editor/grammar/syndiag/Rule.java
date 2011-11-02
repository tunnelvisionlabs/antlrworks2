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

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.openide.util.Parameters;

public class Rule extends JPanel implements Element {
    public static boolean OUTLINE_ELEMENTS = false;

	private final String ruleName;
    private final AttributeSet attributes;

	public Rule(String name) {
		super();
        Parameters.notNull("name", name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new EmptyBorder(new Insets(15, 0, 0, 0)));
        setOpaque(false);
        setName("RULE_" + name);
		this.ruleName = name;
        this.attributes = Diagram.lookupAttributes("identifier");

        StyleContext context = new StyleContext();
        setFont(context.getFont(this.attributes));
        setForeground(context.getForeground(attributes));

        String family = StyleConstants.getFontFamily(attributes);
        int size = StyleConstants.getFontSize(attributes);
        int x = 0;
	}

    public String getRuleName() {
        return ruleName;
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

            if (!(getParent() instanceof Diagram)) {
                Stroke oldStroke = null;

                try {
                    if (g2 != null) {
                        oldStroke = g2.getStroke();
                        g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] { 10.0f }, 0.0f));
                    }

                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                } finally {
                    if (oldStroke != null) {
                        g2.setStroke(oldStroke);
                    }
                }
            }

//            g.setFont(getFont());
            g.drawString(ruleName, 0, 15);
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
        if (getComponentCount() > 0 && getComponent(0) instanceof Element) {
            return getY() + ((Element)getComponent(0)).getConnectionPointY();
        }

        return (int)getBounds().getCenterY();
    }

}
