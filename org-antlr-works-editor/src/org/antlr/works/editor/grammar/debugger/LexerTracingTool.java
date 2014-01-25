/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import org.antlr.v4.Tool;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarTransformPipeline;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;

/**
 * This extension of {@link Tool} allows for processing only the lexer portion
 * of even combined grammars.
 *
 * @author Sam Harwell
 */
public class LexerTracingTool extends Tool {

    /**
     * This method overrides {@link Tool#process} to return immediately after
     * processing the lexer portion of the grammar.
     */
    @Override
    public void process(Grammar g, boolean gencode) {
		g.loadImportedGrammars();

		GrammarTransformPipeline transform = new GrammarTransformPipeline(g, this);
		transform.process();

		LexerGrammar lexerg;
		GrammarRootAST lexerAST;
		if ( g.ast!=null && g.ast.grammarType== ANTLRParser.COMBINED &&
			 !g.ast.hasErrors )
		{
			lexerAST = transform.extractImplicitLexer(g); // alters g.ast
			if ( lexerAST!=null ) {
				if (grammarOptions != null) {
					lexerAST.cmdLineOptions = grammarOptions;
				}

				lexerg = new LexerGrammar(this, lexerAST);
				lexerg.fileName = g.fileName;
				lexerg.originalGrammar = g;
				g.implicitLexer = lexerg;
				lexerg.implicitLexerOwner = g;
				processNonCombinedGrammar(lexerg, gencode);
                return;
//				System.out.println("lexer tokens="+lexerg.tokenNameToTypeMap);
//				System.out.println("lexer strings="+lexerg.stringLiteralToTypeMap);
			}
		}
		if ( g.implicitLexer!=null ) g.importVocab(g.implicitLexer);
//		System.out.println("tokens="+g.tokenNameToTypeMap);
//		System.out.println("strings="+g.stringLiteralToTypeMap);
		processNonCombinedGrammar(g, gencode);
    }
}
