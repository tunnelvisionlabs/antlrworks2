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

public class TemplateParserBaseVisitor<T> extends ParseTreeVisitor<T> implements TemplateParserVisitor<T> {
	@Override public T memberExprVisit(TemplateParser.memberExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T regionVisit(TemplateParser.regionContext ctx) { visitChildren(ctx); return null; }

	@Override public T bigstringTemplateNoNewlineVisit(TemplateParser.bigstringTemplateNoNewlineContext ctx) { visitChildren(ctx); return null; }

	@Override public T dictVisit(TemplateParser.dictContext ctx) { visitChildren(ctx); return null; }

	@Override public T dictDefVisit(TemplateParser.dictDefContext ctx) { visitChildren(ctx); return null; }

	@Override public T groupNameVisit(TemplateParser.groupNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprVisit(TemplateParser.exprContext ctx) { visitChildren(ctx); return null; }

	@Override public T anonymousTemplateVisit(TemplateParser.anonymousTemplateContext ctx) { visitChildren(ctx); return null; }

	@Override public T escapeVisit(TemplateParser.escapeContext ctx) { visitChildren(ctx); return null; }

	@Override public T argVisit(TemplateParser.argContext ctx) { visitChildren(ctx); return null; }

	@Override public T andConditionalVisit(TemplateParser.andConditionalContext ctx) { visitChildren(ctx); return null; }

	@Override public T defVisit(TemplateParser.defContext ctx) { visitChildren(ctx); return null; }

	@Override public T mapTemplateRefVisit(TemplateParser.mapTemplateRefContext ctx) { visitChildren(ctx); return null; }

	@Override public T templateDefVisit(TemplateParser.templateDefContext ctx) { visitChildren(ctx); return null; }

	@Override public T primaryVisit(TemplateParser.primaryContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprNoCommaVisit(TemplateParser.exprNoCommaContext ctx) { visitChildren(ctx); return null; }

	@Override public T bigstringTemplateVisit(TemplateParser.bigstringTemplateContext ctx) { visitChildren(ctx); return null; }

	@Override public T ifstatVisit(TemplateParser.ifstatContext ctx) { visitChildren(ctx); return null; }

	@Override public T anonymousTemplateParametersVisit(TemplateParser.anonymousTemplateParametersContext ctx) { visitChildren(ctx); return null; }

	@Override public T listElementVisit(TemplateParser.listElementContext ctx) { visitChildren(ctx); return null; }

	@Override public T optionVisit(TemplateParser.optionContext ctx) { visitChildren(ctx); return null; }

	@Override public T stringTemplateVisit(TemplateParser.stringTemplateContext ctx) { visitChildren(ctx); return null; }

	@Override public T oldStyleHeaderVisit(TemplateParser.oldStyleHeaderContext ctx) { visitChildren(ctx); return null; }

	@Override public T delimitersVisit(TemplateParser.delimitersContext ctx) { visitChildren(ctx); return null; }

	@Override public T keyValuePairVisit(TemplateParser.keyValuePairContext ctx) { visitChildren(ctx); return null; }

	@Override public T mapExprVisit(TemplateParser.mapExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T listVisit(TemplateParser.listContext ctx) { visitChildren(ctx); return null; }

	@Override public T dictPairsVisit(TemplateParser.dictPairsContext ctx) { visitChildren(ctx); return null; }

	@Override public T formalArgsVisit(TemplateParser.formalArgsContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprTagVisit(TemplateParser.exprTagContext ctx) { visitChildren(ctx); return null; }

	@Override public T argExprListVisit(TemplateParser.argExprListContext ctx) { visitChildren(ctx); return null; }

	@Override public T notConditionalVisit(TemplateParser.notConditionalContext ctx) { visitChildren(ctx); return null; }

	@Override public T subtemplateVisit(TemplateParser.subtemplateContext ctx) { visitChildren(ctx); return null; }

	@Override public T argumentsVisit(TemplateParser.argumentsContext ctx) { visitChildren(ctx); return null; }

	@Override public T formalArgVisit(TemplateParser.formalArgContext ctx) { visitChildren(ctx); return null; }

	@Override public T includeExprVisit(TemplateParser.includeExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T stringVisit(TemplateParser.stringContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprOptionsVisit(TemplateParser.exprOptionsContext ctx) { visitChildren(ctx); return null; }

	@Override public T conditionalVisit(TemplateParser.conditionalContext ctx) { visitChildren(ctx); return null; }

	@Override public T namedArgVisit(TemplateParser.namedArgContext ctx) { visitChildren(ctx); return null; }

	@Override public T groupVisit(TemplateParser.groupContext ctx) { visitChildren(ctx); return null; }

	@Override public T templateBodyVisit(TemplateParser.templateBodyContext ctx) { visitChildren(ctx); return null; }

	@Override public T defaultValuePairVisit(TemplateParser.defaultValuePairContext ctx) { visitChildren(ctx); return null; }

	@Override public T keyValueVisit(TemplateParser.keyValueContext ctx) { visitChildren(ctx); return null; }
}