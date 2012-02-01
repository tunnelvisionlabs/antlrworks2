/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import org.antlr.v4.runtime.atn.LexerATNSimulator.LexerOpCode;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.spi.editor.highlighting.HighlightsContainer;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=HighlightsLayerFactory.class)
public class LexerDebuggerTokenHighlighterLayerFactory implements HighlightsLayerFactory {
    private static final boolean INTERNAL_PARSE = true;

    private static final Color[] ModeColors =
    {
        Color.red,
        Color.blue,
        Color.orange,
        Color.green,
        Color.magenta,
        Color.cyan,
        Color.yellow,
        Color.pink,
        Color.black,
    };

    private static final AttributeSet[] TokenOutlineAttributes =
    {
        createOutlineAttributeSet(0),
        createOutlineAttributeSet(1),
        createOutlineAttributeSet(2),
        createOutlineAttributeSet(3),
        createOutlineAttributeSet(4),
        createOutlineAttributeSet(5),
        createOutlineAttributeSet(6),
        createOutlineAttributeSet(7),
        createOutlineAttributeSet(8),
    };

    private static Color getColorForMode(int mode) {
        if (mode >= 0 && mode < ModeColors.length) {
            return ModeColors[mode];
        }
        
        return ModeColors[ModeColors.length - 1];
    }

    public static Color getColorForMode(int mode, double alpha) {
        return applyAlpha(getColorForMode(mode), alpha);
    }

    public static Color applyAlpha(Color color, double alpha) {
        int red = applyAlpha(color.getRed(), alpha);
        int green = applyAlpha(color.getGreen(), alpha);
        int blue = applyAlpha(color.getBlue(), alpha);

        return new Color(red, green, blue);
    }

    private static int applyAlpha(int value, double alpha) {
        double result = value + (1.0 - alpha) * (255 - value);
        int rounded = (int)Math.round(result);
        return Math.max(0, Math.min(255, rounded));
    }

    private static AttributeSet createOutlineAttributeSet(int mode) {
        Color color = getColorForMode(mode, 0.3);
        MutableAttributeSet attributes = new SimpleAttributeSet();
        attributes.addAttribute(EditorStyleConstants.LeftBorderLineColor, color);
        attributes.addAttribute(EditorStyleConstants.RightBorderLineColor, color);
        attributes.addAttribute(EditorStyleConstants.BottomBorderLineColor, color);
        attributes.addAttribute(EditorStyleConstants.TopBorderLineColor, color);
        return attributes;
    }

    private static AttributeSet getTokenOutlineAttributes(int mode) {
        if (mode >= 0 && mode < TokenOutlineAttributes.length) {
            return TokenOutlineAttributes[mode];
        }

        return TokenOutlineAttributes[TokenOutlineAttributes.length - 1];
    }

    @Override
    public HighlightsLayer[] createLayers(Context context) {
        HighlightsContainer highlighter = loadHighlights(context.getComponent());
        return new HighlightsLayer[] { HighlightsLayer.create(LexerDebuggerTokenHighlighterLayerFactory.class.getName(), ZOrder.SYNTAX_RACK, true, highlighter) };
    }

    private static HighlightsContainer loadHighlights(JTextComponent component) {
        Document document = component.getDocument();
        EditorKit kit = component.getUI().getEditorKit(component);
        if (!(kit instanceof LexerDebuggerEditorKit)) {
            return new OffsetsBag(document);
        }

        LexerDebuggerEditorKit dbgkit = (LexerDebuggerEditorKit)kit;
        TraceToken[] tokens = dbgkit.getTokens(component.getDocument());
        if (tokens == null || tokens.length == 0) {
            return new OffsetsBag(document);
        }

        OffsetsBag highlights = new OffsetsBag(document);
        for (TraceToken token : tokens) {
            AttributeSet tokenAttributes = getTokenOutlineAttributes(token.getMode());
            highlights.addHighlight(token.getStartIndex(), token.getStopIndex() + 1, tokenAttributes);
        }

        return highlights;
//        try {
//            if (INTERNAL_PARSE) {
//                GrammarLexer lexer = new GrammarLexer(new DocumentCharStreamV4((StyledDocument)document));
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                lexer.getInterpreter().trace = outputStream;
//                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
//                tokenStream.fill();
//                lexer.getInterpreter().trace.flush();
//                return loadHighlights(document, new ByteArrayInputStream(outputStream.toByteArray()));
//            } else {
//                String sourceFileName = (String)document.getProperty(Document.StreamDescriptionProperty);
//                String traceFileName = sourceFileName + ".trace";
//                File traceFile = new File(traceFileName);
//                return loadHighlights(document, new BufferedInputStream(new FileInputStream(traceFileName)));
//            }
//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//            return new OffsetsBag(document);
//        }
    }

    private static HighlightsContainer loadHighlights(Document document, InputStream reader) {
        OffsetsBag highlights = new OffsetsBag(document);

        AttributeSet tokenAttributes = getTokenOutlineAttributes(0);
        int previousStop = -1;

        try {
            while (true) {
                int opcodeValue = reader.read();
                if (opcodeValue == -1) {
                    break;
                }

                LexerOpCode opcode = LexerOpCode.values()[opcodeValue];
                switch (opcode) {
                case BeginMatch:
                {
                    byte mode = (byte)reader.read();
                    tokenAttributes = getTokenOutlineAttributes(mode);
                    reader.skip(opcode.getArgumentSize() - 1);
                    break;
                }
                case EndMatch:
                case MatchATN:
                case MatchDFA:
                case FailOverToATN:
                case AcceptState:
                case Predict:
                    reader.skip(opcode.getArgumentSize());
                    break;

                case Seek:
                case Consume:
                case Lookahead1:
                    reader.skip(opcode.getArgumentSize());
                    break;

                case PushMode:
                case PopMode:
                    reader.skip(opcode.getArgumentSize());
                    break;

                case Emit:
                    int startIndex = readInteger(reader);
                    int stopIndex = readInteger(reader);
                    //int type = readInteger(reader);
                    //int channel = readInteger(reader);
                    reader.skip(opcode.getArgumentSize() - 4 * (Integer.SIZE / 8));
                    if (stopIndex > previousStop && startIndex < stopIndex + 1) {
                        previousStop = stopIndex;
                        highlights.addHighlight(startIndex, stopIndex + 1, tokenAttributes);
                    }
                    break;

                default:
                    throw new UnsupportedOperationException("Invalid debugger opcode.");
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        return highlights;
    }

    private static int readInteger(InputStream reader) throws IOException {
        int value = reader.read();
        value |= reader.read() << 8;
        value |= reader.read() << 16;
        value |= reader.read() << 24;
        return value;
    }
}
