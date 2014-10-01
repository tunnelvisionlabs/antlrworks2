/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
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
import java.util.Map;
import java.util.TreeMap;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.xml.bind.DatatypeConverter;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.works.editor.grammar.debugger.LexerDebuggerTokenHighlighterLayerFactory.LexerOpCode;
import org.netbeans.modules.editor.NbEditorKit;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractGrammarDebuggerEditorKit extends NbEditorKit {
    protected static final String UTF_8 = "UTF-8"; // NOI18N

    public static final String PROP_TRACE = "Lexer Trace";
    public static final String PROP_LEXER_INTERP_DATA = "Lexer Interpreter Data";
    public static final String PROP_PARSER_INTERP_DATA = "Parser Interpreter Data";
    public static final String PROP_TOKENS = "Trace Tokens";
    public static final String PROP_ATN_CHARACTERS = "Trace ATN Transitions";
    public static final String PROP_DFA_CHARACTERS = "Trace DFA Transitions";
    public static final String PROP_SELECTED_TOKENS = "Selected Trace Tokens";
    public static final String PROP_SELECTED_CHARACTERS = "Selected Characters";
    public static final String PROP_CHANNELS = "Channels";

    /**
     * The token vocabulary, stored as a {@link Vocabulary} instance.
     */
    public static final String PROP_VOCABULARY = "Vocabulary";
    /**
     * The names of rules in the associated grammar, stored as an array of
     * strings {@link String}{@code []}.
     */
    public static final String PROP_RULE_NAMES = "Rule Names";
    /**
     * The names of modes in the associated grammar, stored as an array of
     * strings {@link String}{@code []}.
     */
    public static final String PROP_MODE_NAMES = "Mode Names";

    @Override
    public void read(Reader in, Document doc, int pos) throws IOException, BadLocationException {
        FileObject fileObject = NbEditorUtilities.getFileObject(doc);
        if (fileObject.hasExt("linterp") || fileObject.hasExt("pinterp")) {
            super.read(in, doc, pos);
            return;
        }

        String data = readAllText(in, 0);
        byte[] binary = DatatypeConverter.parseBase64Binary(data);
        int inputSize = readInteger(binary, 0);
        InputStream inputStream = new ByteArrayInputStream(binary, 4, inputSize);
        super.read(new InputStreamReader(inputStream, UTF_8), doc, pos);

        // read the token names
        int literalNamesOffset = 4 + inputSize;
        int literalNamesSize = readInteger(binary, literalNamesOffset);
        String[] literalNames = readStrings(binary, literalNamesOffset + 4, literalNamesSize);

        int symbolicNamesOffset = literalNamesOffset + 4 + literalNamesSize;
        int symbolicNamesSize = readInteger(binary, symbolicNamesOffset);
        String[] symbolicNames = readStrings(binary, symbolicNamesOffset + 4, symbolicNamesSize);

        doc.putProperty(PROP_VOCABULARY, new VocabularyImpl(literalNames, symbolicNames));

        // read the rule names
        int ruleNamesOffset = symbolicNamesOffset + 4 + symbolicNamesSize;
        int ruleNamesSize = readInteger(binary, ruleNamesOffset);
        String[] ruleNames = readStrings(binary, ruleNamesOffset + 4, ruleNamesSize);
        doc.putProperty(PROP_RULE_NAMES, ruleNames);

        // read the mode names
        int modeNamesOffset = ruleNamesOffset + 4 + ruleNamesSize;
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
            LexerTraceAnalyzer analyzer = new LexerTraceAnalyzer(document, true, false, false);
            byte[] traceData = (byte[])document.getProperty(PROP_TRACE);
            if (traceData != null) {
                loadTokens(document, new ByteArrayInputStream(traceData), analyzer);
            }
            else {
                LexerInterpreterData interpreterData = (LexerInterpreterData)document.getProperty(PROP_LEXER_INTERP_DATA);
                if (interpreterData != null) {
                    loadTokens(document, interpreterData, analyzer);
                }
            }

            tokens = analyzer.tokens.toArray(new TraceToken[analyzer.tokens.size()]);
            document.putProperty(PROP_TOKENS, tokens);
        }

        return tokens;
    }

    public TupleIntInt[] getAtnTransitions(Document document) {
        TupleIntInt[] transitions = (TupleIntInt[])document.getProperty(PROP_ATN_CHARACTERS);
        if (transitions == null) {
            LexerTraceAnalyzer analyzer = new LexerTraceAnalyzer(document, false, true, false);
            byte[] traceData = (byte[])document.getProperty(PROP_TRACE);
            if (traceData != null) {
                loadTokens(document, new ByteArrayInputStream(traceData), analyzer);
            }
            else {
                LexerInterpreterData interpreterData = (LexerInterpreterData)document.getProperty(PROP_LEXER_INTERP_DATA);
                if (interpreterData != null) {
                    loadTokens(document, interpreterData, analyzer);
                }
            }

            transitions = getCharacterData(analyzer.atnCharacters);
            document.putProperty(PROP_ATN_CHARACTERS, transitions);
        }

        return transitions;
    }

    public TupleIntInt[] getDfaTransitions(Document document) {
        TupleIntInt[] transitions = (TupleIntInt[])document.getProperty(PROP_DFA_CHARACTERS);
        if (transitions == null) {
            LexerTraceAnalyzer analyzer = new LexerTraceAnalyzer(document, false, false, true);
            byte[] traceData = (byte[])document.getProperty(PROP_TRACE);
            if (traceData != null) {
                loadTokens(document, new ByteArrayInputStream(traceData), analyzer);
            }
            else {
                LexerInterpreterData interpreterData = (LexerInterpreterData)document.getProperty(PROP_LEXER_INTERP_DATA);
                if (interpreterData != null) {
                    loadTokens(document, interpreterData, analyzer);
                }
            }

            transitions = getCharacterData(analyzer.dfaCharacters);
            document.putProperty(PROP_DFA_CHARACTERS, transitions);
        }

        return transitions;
    }

    private TupleIntInt[] getCharacterData(Map<Integer, Integer> map) {
        List<TupleIntInt> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(new TupleIntInt(entry.getKey(), entry.getValue()));
        }

        return result.toArray(new TupleIntInt[result.size()]);
    }

    private void loadTokens(final Document document, LexerInterpreterData interpreterData, LexerTraceAnalyzer analyzer) {
        try {
            TracingCharStream charStream = new TracingCharStream(analyzer, document.getText(0, document.getLength()));
            TracingLexer lexer = new TracingLexer(interpreterData, analyzer, charStream);
            ATN atn = new ATNDeserializer().deserialize(interpreterData.serializedAtn.toCharArray());
            TracingLexerATNSimulator atnSimulator = new TracingLexerATNSimulator(analyzer, lexer, atn);
            lexer.setInterpreter(atnSimulator);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            commonTokenStream.fill();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void loadTokens(final Document document, InputStream reader, LexerTraceAnalyzer analyzer) {
        loadTrace(document, reader, analyzer);
    }

    private static class LexerTraceAnalyzer extends AbstractLexerTraceListener {
        public final List<TraceToken> tokens = new ArrayList<>();
        public final Map<Integer, Integer> atnCharacters = new TreeMap<>();
        public final Map<Integer, Integer> dfaCharacters = new TreeMap<>();

        private final Document document;
        private final boolean calculateTokens;
        private final boolean calculateAtnCharacters;
        private final boolean calculateDfaCharacters;

        private int mode;
        private int inputIndex;

        public LexerTraceAnalyzer(Document document, boolean calculateTokens, boolean calculateAtnCharacters, boolean calculateDfaCharacters) {
            this.document = document;
            this.calculateTokens = calculateTokens;
            this.calculateAtnCharacters = calculateAtnCharacters;
            this.calculateDfaCharacters = calculateDfaCharacters;
        }

        @Override
        public void beginMatch(int mode, int index) {
            this.mode = mode;
        }

        @Override
        public void seek(int index) {
            inputIndex = index;
        }

        @Override
        public void consume(int symbol, int nextIndex) {
            inputIndex = nextIndex;
        }

        @Override
        public void transition(boolean computed) {
            if (computed && !calculateAtnCharacters) {
                return;
            } else if (!computed && !calculateDfaCharacters) {
                return;
            }

            Map<Integer, Integer> map = computed ? atnCharacters : dfaCharacters;
            Integer previous = map.put(inputIndex, 1);
            if (previous != null) {
                map.put(inputIndex, previous + 1);
            }
        }

        @Override
        public void emit(int startIndex, int stopIndex, int type, int channel) {
            if (!calculateTokens) {
                return;
            }

            tokens.add(new TraceToken(document, startIndex, stopIndex, tokens.size(), type, channel, mode));
        }
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

                LexerOpCode opcode = LexerOpCode.values()[opcodeValue];
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

                case Transition:
                    assert opcode.getArgumentSize() == 1;
                    boolean computed = reader.read() != 0;
                    listener.transition(computed);
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
