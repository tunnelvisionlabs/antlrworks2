/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface TemplateParserListener extends ParseTreeListener<Token> {
	void memberExprEnter(TemplateParser.memberExprContext ctx);
	void memberExprExit(TemplateParser.memberExprContext ctx);

	void regionEnter(TemplateParser.regionContext ctx);
	void regionExit(TemplateParser.regionContext ctx);

	void bigstringTemplateNoNewlineEnter(TemplateParser.bigstringTemplateNoNewlineContext ctx);
	void bigstringTemplateNoNewlineExit(TemplateParser.bigstringTemplateNoNewlineContext ctx);

	void dictEnter(TemplateParser.dictContext ctx);
	void dictExit(TemplateParser.dictContext ctx);

	void dictDefEnter(TemplateParser.dictDefContext ctx);
	void dictDefExit(TemplateParser.dictDefContext ctx);

	void groupNameEnter(TemplateParser.groupNameContext ctx);
	void groupNameExit(TemplateParser.groupNameContext ctx);

	void exprEnter(TemplateParser.exprContext ctx);
	void exprExit(TemplateParser.exprContext ctx);

	void anonymousTemplateEnter(TemplateParser.anonymousTemplateContext ctx);
	void anonymousTemplateExit(TemplateParser.anonymousTemplateContext ctx);

	void escapeEnter(TemplateParser.escapeContext ctx);
	void escapeExit(TemplateParser.escapeContext ctx);

	void argEnter(TemplateParser.argContext ctx);
	void argExit(TemplateParser.argContext ctx);

	void andConditionalEnter(TemplateParser.andConditionalContext ctx);
	void andConditionalExit(TemplateParser.andConditionalContext ctx);

	void defEnter(TemplateParser.defContext ctx);
	void defExit(TemplateParser.defContext ctx);

	void mapTemplateRefEnter(TemplateParser.mapTemplateRefContext ctx);
	void mapTemplateRefExit(TemplateParser.mapTemplateRefContext ctx);

	void templateDefEnter(TemplateParser.templateDefContext ctx);
	void templateDefExit(TemplateParser.templateDefContext ctx);

	void primaryEnter(TemplateParser.primaryContext ctx);
	void primaryExit(TemplateParser.primaryContext ctx);

	void exprNoCommaEnter(TemplateParser.exprNoCommaContext ctx);
	void exprNoCommaExit(TemplateParser.exprNoCommaContext ctx);

	void bigstringTemplateEnter(TemplateParser.bigstringTemplateContext ctx);
	void bigstringTemplateExit(TemplateParser.bigstringTemplateContext ctx);

	void ifstatEnter(TemplateParser.ifstatContext ctx);
	void ifstatExit(TemplateParser.ifstatContext ctx);

	void anonymousTemplateParametersEnter(TemplateParser.anonymousTemplateParametersContext ctx);
	void anonymousTemplateParametersExit(TemplateParser.anonymousTemplateParametersContext ctx);

	void listElementEnter(TemplateParser.listElementContext ctx);
	void listElementExit(TemplateParser.listElementContext ctx);

	void optionEnter(TemplateParser.optionContext ctx);
	void optionExit(TemplateParser.optionContext ctx);

	void stringTemplateEnter(TemplateParser.stringTemplateContext ctx);
	void stringTemplateExit(TemplateParser.stringTemplateContext ctx);

	void oldStyleHeaderEnter(TemplateParser.oldStyleHeaderContext ctx);
	void oldStyleHeaderExit(TemplateParser.oldStyleHeaderContext ctx);

	void delimitersEnter(TemplateParser.delimitersContext ctx);
	void delimitersExit(TemplateParser.delimitersContext ctx);

	void keyValuePairEnter(TemplateParser.keyValuePairContext ctx);
	void keyValuePairExit(TemplateParser.keyValuePairContext ctx);

	void mapExprEnter(TemplateParser.mapExprContext ctx);
	void mapExprExit(TemplateParser.mapExprContext ctx);

	void listEnter(TemplateParser.listContext ctx);
	void listExit(TemplateParser.listContext ctx);

	void dictPairsEnter(TemplateParser.dictPairsContext ctx);
	void dictPairsExit(TemplateParser.dictPairsContext ctx);

	void formalArgsEnter(TemplateParser.formalArgsContext ctx);
	void formalArgsExit(TemplateParser.formalArgsContext ctx);

	void exprTagEnter(TemplateParser.exprTagContext ctx);
	void exprTagExit(TemplateParser.exprTagContext ctx);

	void argExprListEnter(TemplateParser.argExprListContext ctx);
	void argExprListExit(TemplateParser.argExprListContext ctx);

	void notConditionalEnter(TemplateParser.notConditionalContext ctx);
	void notConditionalExit(TemplateParser.notConditionalContext ctx);

	void subtemplateEnter(TemplateParser.subtemplateContext ctx);
	void subtemplateExit(TemplateParser.subtemplateContext ctx);

	void argumentsEnter(TemplateParser.argumentsContext ctx);
	void argumentsExit(TemplateParser.argumentsContext ctx);

	void formalArgEnter(TemplateParser.formalArgContext ctx);
	void formalArgExit(TemplateParser.formalArgContext ctx);

	void includeExprEnter(TemplateParser.includeExprContext ctx);
	void includeExprExit(TemplateParser.includeExprContext ctx);

	void stringEnter(TemplateParser.stringContext ctx);
	void stringExit(TemplateParser.stringContext ctx);

	void exprOptionsEnter(TemplateParser.exprOptionsContext ctx);
	void exprOptionsExit(TemplateParser.exprOptionsContext ctx);

	void conditionalEnter(TemplateParser.conditionalContext ctx);
	void conditionalExit(TemplateParser.conditionalContext ctx);

	void namedArgEnter(TemplateParser.namedArgContext ctx);
	void namedArgExit(TemplateParser.namedArgContext ctx);

	void groupEnter(TemplateParser.groupContext ctx);
	void groupExit(TemplateParser.groupContext ctx);

	void templateBodyEnter(TemplateParser.templateBodyContext ctx);
	void templateBodyExit(TemplateParser.templateBodyContext ctx);

	void defaultValuePairEnter(TemplateParser.defaultValuePairContext ctx);
	void defaultValuePairExit(TemplateParser.defaultValuePairContext ctx);

	void keyValueEnter(TemplateParser.keyValueContext ctx);
	void keyValueExit(TemplateParser.keyValueContext ctx);
}