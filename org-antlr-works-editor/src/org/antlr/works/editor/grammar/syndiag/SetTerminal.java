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
import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.experimental.GrammarParser;

/**
 *
 * @author Sam Harwell
 */
public class SetTerminal extends Terminal {
    private final AttributedString attributedLabel;
    private final boolean inverted;

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_setElement, version=4, dependents=Dependents.SELF)
    public SetTerminal(List<GrammarParser.SetElementContext> elements, SnapshotPositionRegion sourceSpan, boolean inverted) {
        this(getAttributedLabel(elements, inverted), sourceSpan, inverted);
    }

    public SetTerminal(TerminalNode element, SnapshotPositionRegion sourceSpan) {
        this(getAttributedLabel(Collections.singletonList(element), false), sourceSpan, false);
    }

    private SetTerminal(AttributedString attributedLabel, SnapshotPositionRegion sourceSpan, boolean inverted) {
        super(getLabelText(attributedLabel), sourceSpan);

        this.attributedLabel = attributedLabel;
        this.inverted = inverted;
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_range, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_setElement, version=4, dependents=Dependents.SELF),
    })
    private static AttributedString getAttributedLabel(List<? extends ParseTree> elements, boolean inverted) {
        IntervalSet foregroundSpans = new IntervalSet();
        IntervalSet literalSpans = new IntervalSet();
        IntervalSet lexerRuleSpans = new IntervalSet();
        IntervalSet parserRuleSpans = new IntervalSet();

        StringBuilder builder = new StringBuilder();
        if (inverted) {
            builder.append("~(");
            foregroundSpans.add(0, 1);
        }

        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                builder.append("|");
                foregroundSpans.add(builder.length() - 1);
            }

            ParseTree element = elements.get(i);
            if (element instanceof GrammarParser.SetElementContext) {
                GrammarParser.SetElementContext context = (GrammarParser.SetElementContext)element;
                if (context.TOKEN_REF() != null) {
                    String tokenName = context.TOKEN_REF().getText();
                    builder.append(tokenName);
                    lexerRuleSpans.add(builder.length() - tokenName.length(), builder.length() - 1);
                } else if (context.STRING_LITERAL() != null) {
                    String text = context.STRING_LITERAL().getText();
                    builder.append(text);
                    literalSpans.add(builder.length() - text.length(), builder.length() - 1);
                } else if (context.LEXER_CHAR_SET() != null) {
                    String text = context.LEXER_CHAR_SET().getText();
                    if (text.length() >= 2 && text.charAt(0) == '[' && text.charAt(text.length() - 1) == ']') {
                        builder.append(text);
                        foregroundSpans.add(builder.length() - text.length());
                        literalSpans.add(builder.length() - text.length() + 1, builder.length() - 2);
                        foregroundSpans.add(builder.length() - 1);
                    } else {
                        builder.append("???");
                    }
                } else if (context.range() != null) {
                    GrammarParser.RangeContext rangeContext = context.range();
                    List<? extends TerminalNode> strings = rangeContext.STRING_LITERAL();
                    if (strings.size() == 2) {
                        builder.append(strings.get(0).getText());
                        literalSpans.add(builder.length() - strings.get(0).getText().length(), builder.length() - 1);

                        builder.append("..");
                        foregroundSpans.add(builder.length() - 2, builder.length() - 1);

                        builder.append(strings.get(1).getText());
                        literalSpans.add(builder.length() - strings.get(1).getText().length(), builder.length() - 1);
                    } else {
                        builder.append("???");
                    }
                } else {
                    builder.append("???");
                }
            } else if (element instanceof TerminalNode) {
                TerminalNode node = (TerminalNode)element;
                String text = node.getText();
                if (text.length() >= 2 && text.charAt(0) == '[' && text.charAt(text.length() - 1) == ']') {
                    builder.append(text);
                    foregroundSpans.add(builder.length() - text.length());
                    literalSpans.add(builder.length() - text.length() + 1, builder.length() - 2);
                    foregroundSpans.add(builder.length() - 1);
                } else {
                    builder.append("???");
                }
            } else {
                builder.append("???");
            }
        }

        if (inverted) {
            builder.append(")");
            foregroundSpans.add(builder.length() - 1);
        }

        AttributedString label = new AttributedString(builder.toString());
        applyAttributes(label, foregroundSpans.getIntervals(), "identifier");
        applyAttributes(label, literalSpans.getIntervals(), "stringliteral");
        applyAttributes(label, lexerRuleSpans.getIntervals(), "lexerrule");
        applyAttributes(label, parserRuleSpans.getIntervals(), "parserrule");
        return label;
    }

    private static void applyAttributes(AttributedString attributedString, List<Interval> intervals, String category) {
        if (intervals.isEmpty()) {
            return;
        }

        AttributeSet attributes = Diagram.lookupAttributes(category);
        Color foreground = attributes != null ? (Color)attributes.getAttribute(StyleConstants.Foreground) : null;
        if (foreground == null) {
            foreground = Color.BLACK;
        }

        for (Interval interval : intervals) {
            attributedString.addAttribute(TextAttribute.FOREGROUND, foreground, interval.a, interval.b + 1);
        }
    }

    private static String getLabelText(AttributedString attributedLabel) {
        CharacterIterator iterator = attributedLabel.getIterator();
        int length = iterator.getEndIndex();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(iterator.setIndex(i));
        }

        return builder.toString();
    }

    @Override
    protected void paintLabel(Graphics g, int x, int y) {
        AttributedString label = new AttributedString(this.attributedLabel.getIterator());
        label.addAttribute(TextAttribute.FONT, g.getFont());
        g.drawString(label.getIterator(), x, y);
    }

}
