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
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.openide.util.Lookup;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class Diagram extends JPanel {
    private final Rule rule;
    private static Reference<FontColorSettings> weakSettings = new WeakReference<>(null);

    public Diagram(Rule rule) {
        Parameters.notNull("rule", rule);
        this.rule = rule;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);
        setBackground(Color.white);
        add(rule);
    }

    public Rule getRule() {
        return rule;
    }

    public static void drawArrow(int x, int y, double theta, Graphics2D graphics) {
        Parameters.notNull("graphics", graphics);

        // push a transform so we can just draw it at position (0,0) facing right
        AffineTransform transform = graphics.getTransform();
        try {
            graphics.translate(x, y);
            graphics.rotate(theta);
            Path2D.Double path = new Path2D.Double();
            path.moveTo(-10, -3);
            path.lineTo(0, 0);
            path.lineTo(-10, 3);
            graphics.fill(path);
        } finally {
            graphics.setTransform(transform);
        }
    }

    protected static AttributeSet lookupAttributes(String category) {
        Parameters.notNull("category", category);
        FontColorSettings settings = weakSettings.get();
        if (settings == null) {
            Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
            settings = lookup.lookup(FontColorSettings.class);
            weakSettings = new WeakReference<>(settings);
        }

        return settings.getTokenFontColors(category);
    }
}
