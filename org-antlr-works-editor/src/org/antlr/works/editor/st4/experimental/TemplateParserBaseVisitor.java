// Generated from TemplateParser.g4 by ANTLR 4.0-SNAPSHOT
package org.antlr.works.editor.st4.experimental;
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class TemplateParserBaseVisitor<Result> extends AbstractParseTreeVisitor<Token, Result> implements TemplateParserVisitor<Token, Result> {
	@Override public Result visitMemberExpr(TemplateParser.MemberExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRegion(TemplateParser.RegionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBigstringTemplateNoNewline(TemplateParser.BigstringTemplateNoNewlineContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDict(TemplateParser.DictContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDictDef(TemplateParser.DictDefContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGroupName(TemplateParser.GroupNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExpr(TemplateParser.ExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAnonymousTemplate(TemplateParser.AnonymousTemplateContext ctx) { return visitChildren(ctx); }

	@Override public Result visitEscape(TemplateParser.EscapeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArg(TemplateParser.ArgContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAndConditional(TemplateParser.AndConditionalContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDef(TemplateParser.DefContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMapTemplateRef(TemplateParser.MapTemplateRefContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTemplateDef(TemplateParser.TemplateDefContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPrimary(TemplateParser.PrimaryContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprNoComma(TemplateParser.ExprNoCommaContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBigstringTemplate(TemplateParser.BigstringTemplateContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIfstat(TemplateParser.IfstatContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAnonymousTemplateParameters(TemplateParser.AnonymousTemplateParametersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitListElement(TemplateParser.ListElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOption(TemplateParser.OptionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitStringTemplate(TemplateParser.StringTemplateContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOldStyleHeader(TemplateParser.OldStyleHeaderContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDelimiters(TemplateParser.DelimitersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKeyValuePair(TemplateParser.KeyValuePairContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMapExpr(TemplateParser.MapExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitList(TemplateParser.ListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDictPairs(TemplateParser.DictPairsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFormalArgs(TemplateParser.FormalArgsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprTag(TemplateParser.ExprTagContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgExprList(TemplateParser.ArgExprListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitNotConditional(TemplateParser.NotConditionalContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSubtemplate(TemplateParser.SubtemplateContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArguments(TemplateParser.ArgumentsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFormalArg(TemplateParser.FormalArgContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIncludeExpr(TemplateParser.IncludeExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitString(TemplateParser.StringContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprOptions(TemplateParser.ExprOptionsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConditional(TemplateParser.ConditionalContext ctx) { return visitChildren(ctx); }

	@Override public Result visitNamedArg(TemplateParser.NamedArgContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGroup(TemplateParser.GroupContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTemplateBody(TemplateParser.TemplateBodyContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDefaultValuePair(TemplateParser.DefaultValuePairContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKeyValue(TemplateParser.KeyValueContext ctx) { return visitChildren(ctx); }
}