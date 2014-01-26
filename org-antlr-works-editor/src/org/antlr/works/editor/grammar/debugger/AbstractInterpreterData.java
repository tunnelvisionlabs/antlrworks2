/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.antlr.v4.misc.Utils;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.grammar.debugger.LexerDebuggerControllerTopComponent.TokenDescriptor;

/**
 *
 * @author Sam Harwell
 */
public class AbstractInterpreterData {

    public String grammarFileName;
    public String serializedAtn;
    public List<TokenDescriptor> tokenNames;
    public List<String> ruleNames;
    public int startRuleIndex;

    public static TokenDescriptor[] getTokenNames(Grammar grammar) {
        int numTokens = grammar.getMaxTokenType();
        List<String> typeToStringLiteralList = new ArrayList<>(grammar.typeToStringLiteralList);
        Utils.setSize(typeToStringLiteralList, numTokens + 1);
        for (Map.Entry<String, Integer> entry : grammar.stringLiteralToTypeMap.entrySet()) {
            if (entry.getValue() < 0 || entry.getValue() >= typeToStringLiteralList.size()) {
                continue;
            }

            typeToStringLiteralList.set(entry.getValue(), entry.getKey());
        }

        TokenDescriptor[] tokenNames = new TokenDescriptor[numTokens+1];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = new TokenDescriptor();
        }

        for (String tokenName : grammar.tokenNameToTypeMap.keySet()) {
            Integer ttype = grammar.tokenNameToTypeMap.get(tokenName);
            if (ttype < 0 || ttype >= tokenNames.length) {
                continue;
            }

            if ( tokenName!=null && tokenName.startsWith(Grammar.AUTO_GENERATED_TOKEN_NAME_PREFIX) ) {
                if (ttype < typeToStringLiteralList.size()) {
                    String literal = typeToStringLiteralList.get(ttype);
                    if (literal != null) {
                        tokenNames[ttype].literal = literal;
                    }
                }
            }

            tokenNames[ttype].name = tokenName;
            tokenNames[ttype].value = ttype;
        }

        return tokenNames;
    }

}
