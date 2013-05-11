/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
                        assert g2 != null;
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
