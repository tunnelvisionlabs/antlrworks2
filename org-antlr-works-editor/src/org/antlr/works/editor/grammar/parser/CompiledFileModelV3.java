/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import java.util.List;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompiledFileModelV3 extends CompiledFileModel {

    @NonNull
    private final ANTLRErrorProvidingParser parser;
    @NullAllowed
    private final GrammarWrapper grammar;
    @NullAllowed
    private final ANTLRParser.grammar__return result;

    public CompiledFileModelV3(@NonNull ANTLRErrorProvidingParser parser, @NullAllowed GrammarWrapper grammar, @NullAllowed ANTLRParser.grammar__return result, @NonNull FileObject fileObject, @NullAllowed CommonToken[] tokens) {
        super(fileObject, tokens);
        Parameters.notNull("parser", parser);

        this.parser = parser;
        this.grammar = grammar;
        this.result = result;
    }

    @CheckForNull
    public GrammarWrapper getGrammar() {
        return grammar;
    }

    @CheckForNull
    public ANTLRParser.grammar__return getResult() {
        return result;
    }

    @NonNull
    public ANTLRErrorProvidingParser getParser() {
        return parser;
    }

    @Override
    public List<? extends SyntaxError> getSyntaxErrors() {
        return getParser().getSyntaxErrors();
    }

}
