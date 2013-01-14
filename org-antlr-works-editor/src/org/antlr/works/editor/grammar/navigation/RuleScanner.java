/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.works.editor.grammar.parser.CompiledFileModel;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ParserRules=Parser Rules",
    "LBL_LexerRules=Lexer Rules"
})
public abstract class RuleScanner {

    public Description scan(CompiledModel model) {
        GrammarNode.GrammarNodeDescription rootDescription = scanImpl(model);
        return rootDescription;
    }

    protected abstract GrammarNode.GrammarNodeDescription scanImpl(CompiledModel model);

    @CheckForNull
    protected CommonToken getToken(@NonNull CompiledFileModel result, int tokenIndex) {
        CommonToken[] tokens = result.getTokens();
        if (tokens != null && tokenIndex >= 0 && tokenIndex < tokens.length) {
            return tokens[tokenIndex];
        }

        return null;
    }

    @CheckForNull
    protected SnapshotPositionRegion getSpan(@NullAllowed DocumentSnapshot snapshot, @NullAllowed CommonToken startToken, @NullAllowed CommonToken stopToken) {
        if (snapshot == null) {
            return null;
        }

        if (startToken == null || stopToken == null) {
            return null;
        }

        if (startToken.getStartIndex() > stopToken.getStopIndex() + 1) {
            return null;
        }

        return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
    }

    @CheckForNull
    protected SnapshotPositionRegion getSpan(@NullAllowed DocumentSnapshot snapshot, @NonNull CompiledFileModel result, @NullAllowed CommonTree tree) {
        if (snapshot == null || tree == null) {
            return null;
        }

        CommonToken startToken = getToken(result, tree.getTokenStartIndex());
        CommonToken stopToken = getToken(result, tree.getTokenStopIndex());
        return getSpan(snapshot, startToken, stopToken);
    }

}
