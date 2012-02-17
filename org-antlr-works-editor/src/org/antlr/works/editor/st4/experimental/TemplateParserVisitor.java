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

public interface TemplateParserVisitor<T> {
	T memberExprVisit(TemplateParser.memberExprContext ctx);

	T regionVisit(TemplateParser.regionContext ctx);

	T bigstringTemplateNoNewlineVisit(TemplateParser.bigstringTemplateNoNewlineContext ctx);

	T dictVisit(TemplateParser.dictContext ctx);

	T dictDefVisit(TemplateParser.dictDefContext ctx);

	T groupNameVisit(TemplateParser.groupNameContext ctx);

	T exprVisit(TemplateParser.exprContext ctx);

	T anonymousTemplateVisit(TemplateParser.anonymousTemplateContext ctx);

	T escapeVisit(TemplateParser.escapeContext ctx);

	T argVisit(TemplateParser.argContext ctx);

	T andConditionalVisit(TemplateParser.andConditionalContext ctx);

	T defVisit(TemplateParser.defContext ctx);

	T mapTemplateRefVisit(TemplateParser.mapTemplateRefContext ctx);

	T templateDefVisit(TemplateParser.templateDefContext ctx);

	T primaryVisit(TemplateParser.primaryContext ctx);

	T exprNoCommaVisit(TemplateParser.exprNoCommaContext ctx);

	T bigstringTemplateVisit(TemplateParser.bigstringTemplateContext ctx);

	T ifstatVisit(TemplateParser.ifstatContext ctx);

	T anonymousTemplateParametersVisit(TemplateParser.anonymousTemplateParametersContext ctx);

	T listElementVisit(TemplateParser.listElementContext ctx);

	T optionVisit(TemplateParser.optionContext ctx);

	T stringTemplateVisit(TemplateParser.stringTemplateContext ctx);

	T oldStyleHeaderVisit(TemplateParser.oldStyleHeaderContext ctx);

	T delimitersVisit(TemplateParser.delimitersContext ctx);

	T keyValuePairVisit(TemplateParser.keyValuePairContext ctx);

	T mapExprVisit(TemplateParser.mapExprContext ctx);

	T listVisit(TemplateParser.listContext ctx);

	T dictPairsVisit(TemplateParser.dictPairsContext ctx);

	T formalArgsVisit(TemplateParser.formalArgsContext ctx);

	T exprTagVisit(TemplateParser.exprTagContext ctx);

	T argExprListVisit(TemplateParser.argExprListContext ctx);

	T notConditionalVisit(TemplateParser.notConditionalContext ctx);

	T subtemplateVisit(TemplateParser.subtemplateContext ctx);

	T argumentsVisit(TemplateParser.argumentsContext ctx);

	T formalArgVisit(TemplateParser.formalArgContext ctx);

	T includeExprVisit(TemplateParser.includeExprContext ctx);

	T stringVisit(TemplateParser.stringContext ctx);

	T exprOptionsVisit(TemplateParser.exprOptionsContext ctx);

	T conditionalVisit(TemplateParser.conditionalContext ctx);

	T namedArgVisit(TemplateParser.namedArgContext ctx);

	T groupVisit(TemplateParser.groupContext ctx);

	T templateBodyVisit(TemplateParser.templateBodyContext ctx);

	T defaultValuePairVisit(TemplateParser.defaultValuePairContext ctx);

	T keyValueVisit(TemplateParser.keyValueContext ctx);
}