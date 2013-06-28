/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class TraceToken implements Token {
    private final int startIndex;
    private final int stopIndex;
    private final int tokenIndex;
    private final int type;
    private final int channel;
    private final int mode;
    private final String text;

    public TraceToken(@NonNull Document document, int startIndex, int stopIndex, int tokenIndex, int type, int channel, int mode) {
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
        this.tokenIndex = tokenIndex;
        this.type = type;
        this.channel = channel;
        this.mode = mode;
        String tokenText = null;
        try {
            tokenText = document.getText(startIndex, stopIndex - startIndex + 1).toString();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.text = tokenText;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public int getTokenIndex() {
        return tokenIndex;
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int getStopIndex() {
        return stopIndex;
    }

    public int getMode() {
        return mode;
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCharPositionInLine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TokenSource getTokenSource() {
        throw new UnsupportedOperationException("No token source is available.");
    }

    @Override
    public CharStream getInputStream() {
        throw new UnsupportedOperationException("No input stream is available.");
    }

    @Override
    public String toString() {
        return toString(null);
    }

    public String toString(@NullAllowed List<String> tokenNames) {
        String txt = getText();
        if (txt != null) {
            if (txt.length() > 100) {
                txt = txt.substring(0, 100) + "...";
            }
            txt = txt.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
        }

        String typeText = "";
        if (tokenNames != null && getType() >= 0 && getType() < tokenNames.size()) {
            typeText = "=" + tokenNames.get(getType());
        } else if (getType() == Token.EOF) {
            typeText = "=EOF";
        }

        String channelTxt = "";
        if (getChannel() != 0) {
            channelTxt = ",channel=" + getChannel();
        }

        String modeTxt = "";
        if (getMode() != 0) {
            modeTxt = ",mode=" + getMode();
        }

        return String.format("[@%d,[%d..%d)='%s',<%d%s>%s%s]", getTokenIndex(), getStartIndex(), getStopIndex() + 1, txt, getType(), typeText, channelTxt, modeTxt);
    }
}
