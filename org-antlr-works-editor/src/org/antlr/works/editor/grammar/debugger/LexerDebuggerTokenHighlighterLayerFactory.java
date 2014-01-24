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

    private static final int FULL = 0;
    private static final int START = 1;
    private static final int MIDDLE = 2;
    private static final int STOP = 3;

    private static final Color[] ModeColors =
    {
        Color.black,
        Color.red,
        Color.blue,
        Color.orange,
        Color.green,
        Color.magenta,
        Color.cyan,
        Color.yellow,
        Color.pink,
    };

    private static final AttributeSet[] TokenFullOutlineAttributes =
    {
        createOutlineAttributeSet(FULL, 0),
        createOutlineAttributeSet(FULL, 1),
        createOutlineAttributeSet(FULL, 2),
        createOutlineAttributeSet(FULL, 3),
        createOutlineAttributeSet(FULL, 4),
        createOutlineAttributeSet(FULL, 5),
        createOutlineAttributeSet(FULL, 6),
        createOutlineAttributeSet(FULL, 7),
        createOutlineAttributeSet(FULL, 8),
    };

    private static final AttributeSet[] TokenStartOutlineAttributes =
    {
        createOutlineAttributeSet(START, 0),
        createOutlineAttributeSet(START, 1),
        createOutlineAttributeSet(START, 2),
        createOutlineAttributeSet(START, 3),
        createOutlineAttributeSet(START, 4),
        createOutlineAttributeSet(START, 5),
        createOutlineAttributeSet(START, 6),
        createOutlineAttributeSet(START, 7),
        createOutlineAttributeSet(START, 8),
    };

    private static final AttributeSet[] TokenMiddleOutlineAttributes =
    {
        createOutlineAttributeSet(MIDDLE, 0),
        createOutlineAttributeSet(MIDDLE, 1),
        createOutlineAttributeSet(MIDDLE, 2),
        createOutlineAttributeSet(MIDDLE, 3),
        createOutlineAttributeSet(MIDDLE, 4),
        createOutlineAttributeSet(MIDDLE, 5),
        createOutlineAttributeSet(MIDDLE, 6),
        createOutlineAttributeSet(MIDDLE, 7),
        createOutlineAttributeSet(MIDDLE, 8),
    };

    private static final AttributeSet[] TokenStopOutlineAttributes =
    {
        createOutlineAttributeSet(STOP, 0),
        createOutlineAttributeSet(STOP, 1),
        createOutlineAttributeSet(STOP, 2),
        createOutlineAttributeSet(STOP, 3),
        createOutlineAttributeSet(STOP, 4),
        createOutlineAttributeSet(STOP, 5),
        createOutlineAttributeSet(STOP, 6),
        createOutlineAttributeSet(STOP, 7),
        createOutlineAttributeSet(STOP, 8),
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

    private static AttributeSet createOutlineAttributeSet(int part, int mode) {
        Color color = getColorForMode(mode, 0.3);
        MutableAttributeSet attributes = new SimpleAttributeSet();
        if (part == START || part == FULL) {
            attributes.addAttribute(EditorStyleConstants.LeftBorderLineColor, color);
        }

        if (part == STOP || part == FULL) {
            attributes.addAttribute(EditorStyleConstants.RightBorderLineColor, color);
        }

        return attributes;
    }

    private static AttributeSet getTokenOutlineAttributes(int part, int mode) {
        AttributeSet[] attributes;
        switch (part) {
        case FULL:
            attributes = TokenFullOutlineAttributes;
            break;
        case START:
            attributes = TokenStartOutlineAttributes;
            break;
        case MIDDLE:
            attributes = TokenMiddleOutlineAttributes;
            break;
        case STOP:
            attributes = TokenStopOutlineAttributes;
            break;
        default:
            return SimpleAttributeSet.EMPTY;
        }

        if (mode >= 0 && mode < attributes.length) {
            return attributes[mode];
        }

        return attributes[attributes.length - 1];
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
            int start = token.getStartIndex();
            int stop = token.getStopIndex();
            addHighlights(highlights, start, stop, token.getMode());
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

    private static void addHighlights(OffsetsBag highlights, int start, int stop, int mode) {
        if (stop < start) {
            return;
        }

        if (stop == start) {
            AttributeSet tokenAttributes = getTokenOutlineAttributes(FULL, mode);
            highlights.addHighlight(start, stop + 1, tokenAttributes);
        } else {
            AttributeSet tokenStartAttributes = getTokenOutlineAttributes(START, mode);
            highlights.addHighlight(start, start + 1, tokenStartAttributes);
            if (stop > start + 1) {
                AttributeSet tokenMiddleAttributes = getTokenOutlineAttributes(MIDDLE, mode);
                highlights.addHighlight(start + 1, stop, tokenMiddleAttributes);
            }
            AttributeSet tokenStopAttributes = getTokenOutlineAttributes(STOP, mode);
            highlights.addHighlight(stop, stop + 1, tokenStopAttributes);
        }
    }

    private static HighlightsContainer loadHighlights(Document document, InputStream reader) {
        OffsetsBag highlights = new OffsetsBag(document);

        int mode = 0;
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
                    mode = (byte)reader.read();
                    reader.skip(opcode.getArgumentSize() - 1);
                    break;
                }
                case EndMatch:
                case AcceptState:
                case Predict:
                    reader.skip(opcode.getArgumentSize());
                    break;

                case Seek:
                case Consume:
                case Lookahead1:
                    reader.skip(opcode.getArgumentSize());
                    break;

                case Transition:
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
                        addHighlights(highlights, startIndex, stopIndex, mode);
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

    public enum LexerOpCode {
        BeginMatch(5),
        EndMatch(0),
        Transition(1),
        AcceptState(4),
        Predict(4),

        Seek(4),
        Consume(8),
        Lookahead1(0),

        PushMode(1),
        PopMode(0),
        Emit(16);

        private final int argumentSize;

        private LexerOpCode(int argumentSize) {
            this.argumentSize = argumentSize;
        }

        public int getArgumentSize() {
            return argumentSize;
        }
    }
}
