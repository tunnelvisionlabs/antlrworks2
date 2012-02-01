/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledDocument;
import javax.swing.text.TextAction;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.works.editor.antlr4.highlighting.DocumentCharStreamV4;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=EditorKit.class)
public class LexerDebuggerEditorKit extends NbEditorKit {
    private static final Logger LOG = Logger.getLogger(LexerDebuggerEditorKit.class.getName());

    private static final boolean INTERNAL_PARSE = true;

    public static final String PROP_TRACE = "Lexer Trace";
    public static final String PROP_TOKENS = "Trace Tokens";
    public static final String PROP_SELECTED_TOKENS = "Selected Trace Tokens";
    public static final String PROP_CHANNELS = "Channels";

    public static final String LEXER_DEBUGGER_MIME_TYPE = "text/x-antlr3-ldbg";

    @Override
    protected void initDocument(BaseDocument doc) {
        super.initDocument(doc);
        if (INTERNAL_PARSE && !LOG.isLoggable(Level.INFO)) {
            LOG.setLevel(Level.INFO);
        }
    }

    @Override
    public String getContentType() {
        return LEXER_DEBUGGER_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        Action[] actions = {
        };

        actions = TextAction.augmentList(superActions, actions);
        return actions;
    }

    public TraceToken[] getTokens(Document document) {
        TraceToken[] tokens = (TraceToken[])document.getProperty(PROP_TOKENS);
        if (tokens == null) {
            try {
                Set<Integer> channels = new HashSet<Integer>();

                if (INTERNAL_PARSE) {
                    Lexer lexer = LexerDebuggerTest.createLexer(new DocumentCharStreamV4((StyledDocument)document));
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    lexer.getInterpreter().setTraceStream(outputStream);
                    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                    tokenStream.fill();
                    outputStream.flush();
                    LOG.info(String.format("Lexer trace consumes %d bytes.\n", outputStream.size()));
                    document.putProperty(PROP_TRACE, outputStream.toByteArray());
                    tokens = loadTokens(document, new ByteArrayInputStream(outputStream.toByteArray()));
                } else {
                    String sourceFileName = (String)document.getProperty(Document.StreamDescriptionProperty);
                    String traceFileName = sourceFileName + ".trace";
                    File traceFile = new File(traceFileName);
                    tokens = loadTokens(document, new BufferedInputStream(new FileInputStream(traceFileName)));
                }

                document.putProperty(PROP_TOKENS, tokens);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return tokens;
    }

    private TraceToken[] loadTokens(final Document document, InputStream reader) {
        final List<TraceToken> tokens = new ArrayList<TraceToken>();

        loadTrace(document, reader, new AbstractLexerTraceListener() {
            int mode;

            @Override
            public void beginMatch(int mode, int index) {
                this.mode = mode;
            }

            @Override
            public void emit(int startIndex, int stopIndex, int type, int channel) {
                tokens.add(new TraceToken(document, startIndex, stopIndex, tokens.size(), type, channel, mode));
            }

        });

        return tokens.toArray(new TraceToken[0]);
    }

    public void processTrace(Document document, LexerTraceListener listener) {
        byte[] trace = (byte[])document.getProperty(PROP_TRACE);        
        if (trace != null) {
            loadTrace(document, new ByteArrayInputStream(trace), listener);
        }
    }

    private void loadTrace(Document document, InputStream reader, LexerTraceListener listener) {
        try {
            while (true) {
                int opcodeValue = reader.read();
                if (opcodeValue == -1) {
                    break;
                }

                LexerATNSimulator.LexerOpCode opcode = LexerATNSimulator.LexerOpCode.values()[opcodeValue];
                switch (opcode) {
                case BeginMatch:
                {
                    assert opcode.getArgumentSize() == 5;
                    int mode = reader.read();
                    int index = readInteger(reader);
                    listener.beginMatch(mode, index);
                    break;
                }

                case EndMatch:
                    assert opcode.getArgumentSize() == 0;
                    listener.endMatch();
                    break;

                case MatchATN:
                    assert opcode.getArgumentSize() == 0;
                    listener.matchATN();
                    break;

                case MatchDFA:
                    assert opcode.getArgumentSize() == 0;
                    listener.matchDFA();
                    break;

                case FailOverToATN:
                    assert opcode.getArgumentSize() == 0;
                    listener.failOverToATN();
                    break;

                case AcceptState:
                {
                    assert opcode.getArgumentSize() == 4;
                    int tokenType = readInteger(reader);
                    listener.acceptState(tokenType);
                    break;
                }

                case Predict:
                {
                    assert opcode.getArgumentSize() == 4;
                    int tokenType = readInteger(reader);
                    listener.predict(tokenType);
                    break;
                }

                case Seek:
                {
                    assert opcode.getArgumentSize() == 4;
                    int index = readInteger(reader);
                    listener.seek(index);
                    break;
                }

                case Consume:
                {
                    assert opcode.getArgumentSize() == 8;
                    int symbol = readInteger(reader);
                    int index = readInteger(reader);
                    listener.consume(symbol, index);
                    break;
                }

                case Lookahead1:
                    assert opcode.getArgumentSize() == 0;
                    listener.lookahead(1);
                    break;

                case PushMode:
                {
                    assert opcode.getArgumentSize() == 1;
                    int mode = reader.read();
                    listener.pushMode(mode);
                    break;
                }

                case PopMode:
                    assert opcode.getArgumentSize() == 0;
                    listener.popMode();
                    break;

                case Emit:
                {
                    assert opcode.getArgumentSize() == 16;
                    int startIndex = readInteger(reader);
                    int stopIndex = readInteger(reader);
                    int type = readInteger(reader);
                    int channel = readInteger(reader);
                    listener.emit(startIndex, stopIndex, type, channel);
                    break;
                }

                default:
                    throw new UnsupportedOperationException("Invalid debugger opcode.");
                }
            }
        } catch (IOException ex) {
        }
    }

    private static int readInteger(InputStream reader) throws IOException {
        int value = reader.read();
        value |= reader.read() << 8;
        value |= reader.read() << 16;
        value |= reader.read() << 24;
        return value;
    }
}
