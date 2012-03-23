/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import javax.xml.bind.DatatypeConverter;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=EditorKit.class)
public class LexerDebuggerEditorKit extends NbEditorKit {
    private static final Logger LOG = Logger.getLogger(LexerDebuggerEditorKit.class.getName());
    private static final String UTF_8 = "UTF-8"; // NOI18N

    private static final boolean INTERNAL_PARSE = true;

    public static final String PROP_TRACE = "Lexer Trace";
    public static final String PROP_TOKENS = "Trace Tokens";
    public static final String PROP_SELECTED_TOKENS = "Selected Trace Tokens";
    public static final String PROP_CHANNELS = "Channels";

    public static final String PROP_TOKEN_NAMES = "Token Names";
    public static final String PROP_MODE_NAMES = "Mode Names";

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

    @Override
    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException {
        String data = readAllText(in, 0);
        byte[] binary = DatatypeConverter.parseBase64Binary(data);
        int inputSize = readInteger(binary, 0);
        InputStream inputStream = new ByteArrayInputStream(binary, 4, inputSize);
        super.read(new InputStreamReader(inputStream, UTF_8), doc, pos);

        // read the token names
        int tokenNamesOffset = 4 + inputSize;
        int tokenNamesSize = readInteger(binary, tokenNamesOffset);
        String[] tokenNames = readStrings(binary, tokenNamesOffset + 4, tokenNamesSize);
        doc.putProperty(PROP_TOKEN_NAMES, tokenNames);

        // read the mode names
        int modeNamesOffset = tokenNamesOffset + 4 + tokenNamesSize;
        int modeNamesSize = readInteger(binary, modeNamesOffset);
        String[] modeNames = readStrings(binary, modeNamesOffset + 4, modeNamesSize);
        doc.putProperty(PROP_MODE_NAMES, modeNames);

        // read the trace
        int traceOffset = modeNamesOffset + 4 + modeNamesSize;
        int traceSize = readInteger(binary, traceOffset);
        byte[] traceData = Arrays.copyOfRange(binary, traceOffset + 4, traceOffset + 4 + traceSize);
        doc.putProperty(PROP_TRACE, traceData);
    }

    private static String readAllText(Reader reader, int estimatedSize) throws IOException {
        StringBuilder builder = new StringBuilder(Math.max(16, estimatedSize));
        char[] buffer = new char[1024];
        while (true) {
            int count = reader.read(buffer, 0, buffer.length);
            if (count < 0) {
                break;
            }

            builder.append(buffer, 0, count);
        }

        return builder.toString();
    }

    private static int readInteger(byte[] data, int offset) {
        int value = (data[offset++] & 0xFF);
        value |= (data[offset++] & 0xFF) << 8;
        value |= (data[offset++] & 0xFF) << 16;
        value |= (data[offset++] & 0xFF) << 24;
        return value;
    }

    private static String[] readStrings(byte[] data, int offset, int length) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(data, offset, length);
        InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
        String text = readAllText(reader, length);
        return text.split("\\n");
    }

    public static class TraceData {
        public final TraceToken[] tokens;
        public final String[] tokenNames;
        public final String[] modes;

        public TraceData(TraceToken[] tokens, String[] tokenNames, String[] modes) {
            Parameters.notNull("tokens", tokens);
            Parameters.notNull("tokenNames", tokenNames);
            Parameters.notNull("modes", modes);

            this.tokens = tokens;
            this.tokenNames = tokenNames;
            this.modes = modes;
        }
    }

    public TraceToken[] getTokens(Document document) {
        TraceToken[] tokens = (TraceToken[])document.getProperty(PROP_TOKENS);
        if (tokens == null) {
            byte[] traceData = (byte[])document.getProperty(PROP_TRACE);
            tokens = loadTokens(document, new ByteArrayInputStream(traceData));
            //if (INTERNAL_PARSE) {
            //    Lexer lexer = LexerDebuggerTest.createLexer(new DocumentCharStreamV4((StyledDocument)document));
            //    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //    lexer.getInterpreter().setTraceStream(outputStream);
            //    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            //    tokenStream.fill();
            //    outputStream.flush();
            //    LOG.info(String.format("Lexer trace consumes %d bytes.\n", outputStream.size()));
            //    document.putProperty(PROP_TRACE, outputStream.toByteArray());
            //    tokens = loadTokens(document, new ByteArrayInputStream(outputStream.toByteArray()));
            //} else {
            //    String sourceFileName = (String)document.getProperty(Document.StreamDescriptionProperty);
            //    String traceFileName = sourceFileName + ".trace";
            //    File traceFile = new File(traceFileName);
            //    tokens = loadTokens(document, new BufferedInputStream(new FileInputStream(traceFileName)));
            //}
            document.putProperty(PROP_TOKENS, tokens);
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
