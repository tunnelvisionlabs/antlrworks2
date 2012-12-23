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
import org.antlr.v4.analysis.AnalysisPipeline;
import org.antlr.v4.automata.ATNFactory;
import org.antlr.v4.automata.ParserATNFactory;
import org.antlr.v4.codegen.CodeGenPipeline;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.semantics.SemanticPipeline;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarTransformPipeline;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;

/**
 *
 * @author Sam Harwell
 */
public class TracingTool extends Tool {

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
				lexerg = new TracingLexerGrammar(this, lexerAST);
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

    @Override
    public Grammar createGrammar(GrammarRootAST ast) {
		final Grammar g;
		if ( ast.grammarType==ANTLRParser.LEXER ) g = new TracingLexerGrammar(this, ast);
		else g = new Grammar(this, ast);
		g.tokenStream = ast.tokenStream;

		// ensure each node has pointer to surrounding grammar
		GrammarTransformPipeline.setGrammarPtr(g, ast);
		return g;
    }

    @Override
    public void processNonCombinedGrammar(Grammar g, boolean gencode) {
		if ( g.ast!=null && internalOption_PrintGrammarTree ) System.out.println(g.ast.toStringTree());
		//g.ast.inspect();

		if ( g.ast.hasErrors ) return;

		boolean ruleFail = checkForRuleIssues(g);
		if ( ruleFail ) return;

		int prevErrors = errMgr.getNumErrors();
		// MAKE SURE GRAMMAR IS SEMANTICALLY CORRECT (FILL IN GRAMMAR OBJECT)
		SemanticPipeline sem = new SemanticPipeline(g);
		sem.process();

		if ( errMgr.getNumErrors()>prevErrors ) return;

		// BUILD ATN FROM AST
		ATNFactory factory;
		if ( g.isLexer() ) factory = new TracingLexerATNFactory((LexerGrammar)g);
		else factory = new ParserATNFactory(g);
		g.atn = factory.createATN();

        if (factory instanceof TracingLexerATNFactory) {
            ((TracingLexerGrammar)g)._actionsMap = ((TracingLexerATNFactory)factory)._actionsMap;
        }

		if ( generate_ATN_dot ) generateATNs(g);

		// PERFORM GRAMMAR ANALYSIS ON ATN: BUILD DECISION DFAs
		AnalysisPipeline anal = new AnalysisPipeline(g);
		anal.process();

		//if ( generate_DFA_dot ) generateDFAs(g);

		if ( g.tool.getNumErrors()>prevErrors ) return;

		// GENERATE CODE
		if ( gencode ) {
			CodeGenPipeline gen = new CodeGenPipeline(g);
			gen.process();
		}
    }

}
