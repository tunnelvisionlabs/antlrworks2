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

public interface TemplateParserVisitor<Symbol extends Token, Result> extends ParseTreeVisitor<Symbol, Result> {
	Result visitMemberExpr(TemplateParser.MemberExprContext ctx);

	Result visitRegion(TemplateParser.RegionContext ctx);

	Result visitBigstringTemplateNoNewline(TemplateParser.BigstringTemplateNoNewlineContext ctx);

	Result visitDict(TemplateParser.DictContext ctx);

	Result visitDictDef(TemplateParser.DictDefContext ctx);

	Result visitGroupName(TemplateParser.GroupNameContext ctx);

	Result visitExpr(TemplateParser.ExprContext ctx);

	Result visitAnonymousTemplate(TemplateParser.AnonymousTemplateContext ctx);

	Result visitEscape(TemplateParser.EscapeContext ctx);

	Result visitArg(TemplateParser.ArgContext ctx);

	Result visitAndConditional(TemplateParser.AndConditionalContext ctx);

	Result visitDef(TemplateParser.DefContext ctx);

	Result visitMapTemplateRef(TemplateParser.MapTemplateRefContext ctx);

	Result visitTemplateDef(TemplateParser.TemplateDefContext ctx);

	Result visitPrimary(TemplateParser.PrimaryContext ctx);

	Result visitExprNoComma(TemplateParser.ExprNoCommaContext ctx);

	Result visitBigstringTemplate(TemplateParser.BigstringTemplateContext ctx);

	Result visitIfstat(TemplateParser.IfstatContext ctx);

	Result visitAnonymousTemplateParameters(TemplateParser.AnonymousTemplateParametersContext ctx);

	Result visitListElement(TemplateParser.ListElementContext ctx);

	Result visitOption(TemplateParser.OptionContext ctx);

	Result visitStringTemplate(TemplateParser.StringTemplateContext ctx);

	Result visitOldStyleHeader(TemplateParser.OldStyleHeaderContext ctx);

	Result visitDelimiters(TemplateParser.DelimitersContext ctx);

	Result visitKeyValuePair(TemplateParser.KeyValuePairContext ctx);

	Result visitMapExpr(TemplateParser.MapExprContext ctx);

	Result visitList(TemplateParser.ListContext ctx);

	Result visitDictPairs(TemplateParser.DictPairsContext ctx);

	Result visitFormalArgs(TemplateParser.FormalArgsContext ctx);

	Result visitExprTag(TemplateParser.ExprTagContext ctx);

	Result visitArgExprList(TemplateParser.ArgExprListContext ctx);

	Result visitNotConditional(TemplateParser.NotConditionalContext ctx);

	Result visitSubtemplate(TemplateParser.SubtemplateContext ctx);

	Result visitArguments(TemplateParser.ArgumentsContext ctx);

	Result visitFormalArg(TemplateParser.FormalArgContext ctx);

	Result visitIncludeExpr(TemplateParser.IncludeExprContext ctx);

	Result visitString(TemplateParser.StringContext ctx);

	Result visitExprOptions(TemplateParser.ExprOptionsContext ctx);

	Result visitConditional(TemplateParser.ConditionalContext ctx);

	Result visitNamedArg(TemplateParser.NamedArgContext ctx);

	Result visitGroup(TemplateParser.GroupContext ctx);

	Result visitTemplateBody(TemplateParser.TemplateBodyContext ctx);

	Result visitDefaultValuePair(TemplateParser.DefaultValuePairContext ctx);

	Result visitKeyValue(TemplateParser.KeyValueContext ctx);
}