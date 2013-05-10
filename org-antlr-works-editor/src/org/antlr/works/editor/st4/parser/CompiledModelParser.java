/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParseContext;
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
    private Throwable lastException;

    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        try {
            if (requestedData.contains(TemplateParserDataDefinitions.COMPILED_MODEL)) {
                CompiledModel result = parseImpl(taskManager, context, snapshot);
                BaseParserData<CompiledModel> data = new BaseParserData<>(context, TemplateParserDataDefinitions.COMPILED_MODEL, snapshot, result);
                results.addResult(data);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An error occurred while parsing.", ex);
            }
        }
    }

    protected CompiledModel parseImpl(@NonNull ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                if (lastException != null) {
                    throw new ExecutionException("An unexpected error occurred.", lastException);
                }

                return new CompiledModel(snapshot, lastResult);
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Reparsing snapshot {0}", snapshot);
            }

            try {
                final List<SyntaxError> syntaxErrors = new ArrayList<>();
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
                    CommonToken[] groupTokens = tokens.getTokens().toArray(new CommonToken[0]);
                    lastSnapshot = snapshot;
                    lastResult = new CompiledFileModel(parser, returnScope, syntaxErrors, fileObject, groupTokens);
                    lastException = null;
                    return new CompiledModel(snapshot, lastResult);
                } catch (RecognitionException ex) {
                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE, "A recognition exception occurred while parsing.", ex);
                    }

                    lastSnapshot = snapshot;
                    lastResult = null;
                    lastException = null;
                    return null;
                }
            } catch (Exception ex) {
                lastSnapshot = snapshot;
                lastResult = null;
                lastException = ex;
                throw new ExecutionException("An unexpected error occurred.", ex);
            }
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
