/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class TemplateParserBaseListener implements TemplateParserListener {
	@Override public void memberExprEnter(TemplateParser.memberExprContext ctx) { }
	@Override public void memberExprExit(TemplateParser.memberExprContext ctx) { }

	@Override public void regionEnter(TemplateParser.regionContext ctx) { }
	@Override public void regionExit(TemplateParser.regionContext ctx) { }

	@Override public void bigstringTemplateNoNewlineEnter(TemplateParser.bigstringTemplateNoNewlineContext ctx) { }
	@Override public void bigstringTemplateNoNewlineExit(TemplateParser.bigstringTemplateNoNewlineContext ctx) { }

	@Override public void dictEnter(TemplateParser.dictContext ctx) { }
	@Override public void dictExit(TemplateParser.dictContext ctx) { }

	@Override public void dictDefEnter(TemplateParser.dictDefContext ctx) { }
	@Override public void dictDefExit(TemplateParser.dictDefContext ctx) { }

	@Override public void groupNameEnter(TemplateParser.groupNameContext ctx) { }
	@Override public void groupNameExit(TemplateParser.groupNameContext ctx) { }

	@Override public void exprEnter(TemplateParser.exprContext ctx) { }
	@Override public void exprExit(TemplateParser.exprContext ctx) { }

	@Override public void anonymousTemplateEnter(TemplateParser.anonymousTemplateContext ctx) { }
	@Override public void anonymousTemplateExit(TemplateParser.anonymousTemplateContext ctx) { }

	@Override public void escapeEnter(TemplateParser.escapeContext ctx) { }
	@Override public void escapeExit(TemplateParser.escapeContext ctx) { }

	@Override public void argEnter(TemplateParser.argContext ctx) { }
	@Override public void argExit(TemplateParser.argContext ctx) { }

	@Override public void andConditionalEnter(TemplateParser.andConditionalContext ctx) { }
	@Override public void andConditionalExit(TemplateParser.andConditionalContext ctx) { }

	@Override public void defEnter(TemplateParser.defContext ctx) { }
	@Override public void defExit(TemplateParser.defContext ctx) { }

	@Override public void mapTemplateRefEnter(TemplateParser.mapTemplateRefContext ctx) { }
	@Override public void mapTemplateRefExit(TemplateParser.mapTemplateRefContext ctx) { }

	@Override public void templateDefEnter(TemplateParser.templateDefContext ctx) { }
	@Override public void templateDefExit(TemplateParser.templateDefContext ctx) { }

	@Override public void primaryEnter(TemplateParser.primaryContext ctx) { }
	@Override public void primaryExit(TemplateParser.primaryContext ctx) { }

	@Override public void exprNoCommaEnter(TemplateParser.exprNoCommaContext ctx) { }
	@Override public void exprNoCommaExit(TemplateParser.exprNoCommaContext ctx) { }

	@Override public void bigstringTemplateEnter(TemplateParser.bigstringTemplateContext ctx) { }
	@Override public void bigstringTemplateExit(TemplateParser.bigstringTemplateContext ctx) { }

	@Override public void ifstatEnter(TemplateParser.ifstatContext ctx) { }
	@Override public void ifstatExit(TemplateParser.ifstatContext ctx) { }

	@Override public void anonymousTemplateParametersEnter(TemplateParser.anonymousTemplateParametersContext ctx) { }
	@Override public void anonymousTemplateParametersExit(TemplateParser.anonymousTemplateParametersContext ctx) { }

	@Override public void listElementEnter(TemplateParser.listElementContext ctx) { }
	@Override public void listElementExit(TemplateParser.listElementContext ctx) { }

	@Override public void optionEnter(TemplateParser.optionContext ctx) { }
	@Override public void optionExit(TemplateParser.optionContext ctx) { }

	@Override public void stringTemplateEnter(TemplateParser.stringTemplateContext ctx) { }
	@Override public void stringTemplateExit(TemplateParser.stringTemplateContext ctx) { }

	@Override public void oldStyleHeaderEnter(TemplateParser.oldStyleHeaderContext ctx) { }
	@Override public void oldStyleHeaderExit(TemplateParser.oldStyleHeaderContext ctx) { }

	@Override public void delimitersEnter(TemplateParser.delimitersContext ctx) { }
	@Override public void delimitersExit(TemplateParser.delimitersContext ctx) { }

	@Override public void keyValuePairEnter(TemplateParser.keyValuePairContext ctx) { }
	@Override public void keyValuePairExit(TemplateParser.keyValuePairContext ctx) { }

	@Override public void mapExprEnter(TemplateParser.mapExprContext ctx) { }
	@Override public void mapExprExit(TemplateParser.mapExprContext ctx) { }

	@Override public void listEnter(TemplateParser.listContext ctx) { }
	@Override public void listExit(TemplateParser.listContext ctx) { }

	@Override public void dictPairsEnter(TemplateParser.dictPairsContext ctx) { }
	@Override public void dictPairsExit(TemplateParser.dictPairsContext ctx) { }

	@Override public void formalArgsEnter(TemplateParser.formalArgsContext ctx) { }
	@Override public void formalArgsExit(TemplateParser.formalArgsContext ctx) { }

	@Override public void exprTagEnter(TemplateParser.exprTagContext ctx) { }
	@Override public void exprTagExit(TemplateParser.exprTagContext ctx) { }

	@Override public void argExprListEnter(TemplateParser.argExprListContext ctx) { }
	@Override public void argExprListExit(TemplateParser.argExprListContext ctx) { }

	@Override public void notConditionalEnter(TemplateParser.notConditionalContext ctx) { }
	@Override public void notConditionalExit(TemplateParser.notConditionalContext ctx) { }

	@Override public void subtemplateEnter(TemplateParser.subtemplateContext ctx) { }
	@Override public void subtemplateExit(TemplateParser.subtemplateContext ctx) { }

	@Override public void argumentsEnter(TemplateParser.argumentsContext ctx) { }
	@Override public void argumentsExit(TemplateParser.argumentsContext ctx) { }

	@Override public void formalArgEnter(TemplateParser.formalArgContext ctx) { }
	@Override public void formalArgExit(TemplateParser.formalArgContext ctx) { }

	@Override public void includeExprEnter(TemplateParser.includeExprContext ctx) { }
	@Override public void includeExprExit(TemplateParser.includeExprContext ctx) { }

	@Override public void stringEnter(TemplateParser.stringContext ctx) { }
	@Override public void stringExit(TemplateParser.stringContext ctx) { }

	@Override public void exprOptionsEnter(TemplateParser.exprOptionsContext ctx) { }
	@Override public void exprOptionsExit(TemplateParser.exprOptionsContext ctx) { }

	@Override public void conditionalEnter(TemplateParser.conditionalContext ctx) { }
	@Override public void conditionalExit(TemplateParser.conditionalContext ctx) { }

	@Override public void namedArgEnter(TemplateParser.namedArgContext ctx) { }
	@Override public void namedArgExit(TemplateParser.namedArgContext ctx) { }

	@Override public void groupEnter(TemplateParser.groupContext ctx) { }
	@Override public void groupExit(TemplateParser.groupContext ctx) { }

	@Override public void templateBodyEnter(TemplateParser.templateBodyContext ctx) { }
	@Override public void templateBodyExit(TemplateParser.templateBodyContext ctx) { }

	@Override public void defaultValuePairEnter(TemplateParser.defaultValuePairContext ctx) { }
	@Override public void defaultValuePairExit(TemplateParser.defaultValuePairContext ctx) { }

	@Override public void keyValueEnter(TemplateParser.keyValueContext ctx) { }
	@Override public void keyValueExit(TemplateParser.keyValueContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}