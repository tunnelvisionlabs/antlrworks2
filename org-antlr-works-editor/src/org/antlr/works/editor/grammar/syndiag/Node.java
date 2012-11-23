/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.v4.tool.Grammar;
import org.netbeans.api.editor.settings.FontColorNames;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.text.Line;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class Node extends JComponent implements Element {

    private final String label;
    private final SnapshotPositionRegion sourceSpan;
    private final int labelPaddingX;
    private final int labelPaddingY;
    private final int arcSize;

    public Node(String label, SnapshotPositionRegion sourceSpan, int labelPaddingX, int labelPaddingY, int arcSize, AttributeSet attributes) {
        Parameters.notNull("label", label);
        this.label = label;
        this.sourceSpan = sourceSpan;
        this.labelPaddingX = labelPaddingX;
        this.labelPaddingY = labelPaddingY;
        this.arcSize = arcSize;

        AttributeSet defaultAttributes = Diagram.lookupAttributes(FontColorNames.DEFAULT_COLORING);

        Color foreground = (Color)attributes.getAttribute(StyleConstants.Foreground);
        if (foreground == null) {
            foreground = (Color)defaultAttributes.getAttribute(StyleConstants.Foreground);
        }

        if (foreground != null) {
            this.setForeground(foreground);
        }

        StyleContext context = new StyleContext();
        Font font = context.getFont(attributes);
        if (font == null) {
            font = context.getFont(defaultAttributes);
        }

        if (font != null) {
            this.setFont(font);
        }

        Color background = (Color)attributes.getAttribute(StyleConstants.Background);
        if (background == null) {
            background = (Color)defaultAttributes.getAttribute(StyleConstants.Background);
        }

        if (background != null) {
            this.setBackground(background);
        }

        Dimension size = getLabelSize();
        setPreferredSize(size);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    public String getLabel() {
        return label;
    }

    protected static String getCategory(String label) {
        Parameters.notNull("label", label);
        if (label.startsWith("'")) {
            return "stringliteral";
        } else if (!label.isEmpty() && Grammar.isTokenName(label)) {
            return "lexerrule";
        } else {
            return "parserrule";
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getLabelSize();
        Graphics2D g2 = (Graphics2D)g;
        Object prevAaHint = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Color previousColor = g2.getColor();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            Color background = getBackground();
            if (background != null) {
                g.setColor(background);
                g.fillRoundRect(0, 0, size.width - 1, size.height - 1, arcSize, arcSize);
            }

            g.setColor(Color.black);
            g.drawRoundRect(0, 0, size.width - 1, size.height - 1, arcSize, arcSize);

            FontMetrics m = getFontMetrics(getFont());
            g.setColor(getForeground());
            int x = labelPaddingX;
            int y = labelPaddingY + m.getAscent();
            paintLabel(g, x, y);
        } finally {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, prevAaHint);
            g2.setColor(previousColor);
        }
    }

    @Override
    public void updatePositions() {
    }

    @Override
    public int getConnectionPointY() {
        return (int)getBounds().getCenterY();
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (!e.isConsumed() && e.getClickCount() == 2) {
            if (sourceSpan != null) {
                VersionedDocument textBuffer = sourceSpan.getSnapshot().getVersionedDocument();
                Document document = textBuffer.getDocument();
                DocumentSnapshot currentSnapshot = textBuffer.getCurrentSnapshot();
                TrackingPosition trackingTarget = sourceSpan.getSnapshot().createTrackingPosition(sourceSpan.getStart().getOffset(), TrackingPosition.Bias.Forward);
                SnapshotPosition targetPoint = trackingTarget.getPosition(currentSnapshot);
                int column = targetPoint.getContainingLine().getStart().difference(targetPoint);
                NbEditorUtilities.getLine(document, targetPoint.getOffset(), true).show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
            }

            e.consume();
        }
    }

    protected void paintLabel(Graphics g, int x, int y) {
        g.drawString(getLabel(), x, y);
    }

    protected final Dimension getLabelSize() {
        FontMetrics m = getFontMetrics(getFont());
        Rectangle2D r = m.getStringBounds(this.label, getGraphics());
        int labelWidth = (int)r.getWidth() + 2 * this.labelPaddingX;
        int labelHeight = (int)r.getHeight() + 2 * this.labelPaddingY;
        Dimension size = new Dimension(labelWidth, labelHeight);
        return size;
    }
}
