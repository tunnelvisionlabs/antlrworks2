/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.GroupLexer;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelParser {
    // -J-Dorg.antlr.works.editor.st4.parser.CompiledModelParser.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CompiledModelParser.class.getName());

    private final Object lock = new Object();
    private DocumentSnapshot lastSnapshot;
    private CompiledFileModel lastResult;

    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        try {
            if (requestedData.contains(TemplateParserDataDefinitions.COMPILED_MODEL)) {
                CompiledModel result = parseImpl(taskManager, component, snapshot);
                BaseParserData<CompiledModel> data = new BaseParserData<CompiledModel>(TemplateParserDataDefinitions.COMPILED_MODEL, snapshot, result);
                results.addResult(data);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An error occurred while parsing.", ex);
            }
        }
    }

    protected CompiledModel parseImpl(@NonNull ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                return new CompiledModel(snapshot, lastResult);
            }
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Reparsing snapshot {0}", new Object[] { snapshot });
        }

        try {
            final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();
            ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
            input.name = snapshot.getVersionedDocument().getFileObject().getNameExt();

            GroupLexer lexer = new GroupLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GroupParserWrapper parser = new GroupParserWrapper(tokens, snapshot);
            TemplateGroupWrapper group = new TemplateGroupWrapper('<', '>');
            try {
                parser.group(group, "/");
                TemplateGroupRuleReturnScope returnScope = buildAstForGroupTemplates(group);
                FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
                @SuppressWarnings("unchecked")
                CommonToken[] groupTokens = (CommonToken[])tokens.getTokens().toArray(new CommonToken[0]);
                return new CompiledModel(snapshot, new CompiledFileModel(parser, returnScope, syntaxErrors, fileObject, groupTokens));
            } catch (RecognitionException ex) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE, "A recognition exception occurred while parsing.", ex);
                }
                return null;
            }
        } catch (Exception ex) {
            throw new ExecutionException("An unexpected error occurred.", ex);
        }
    }

    private TemplateGroupRuleReturnScope buildAstForGroupTemplates(TemplateGroupWrapper group) {
        TreeAdaptor adaptor = new CommonTreeAdaptor();
        Object tree = adaptor.nil();
        for (CompiledST template : group.getCompiledTemplates()) {
            adaptor.addChild(tree, template.ast);
        }

        return new TemplateGroupRuleReturnScope(group, (CommonTree)tree);
    }

}
