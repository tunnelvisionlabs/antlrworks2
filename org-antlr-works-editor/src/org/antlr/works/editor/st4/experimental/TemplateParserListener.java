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

public interface TemplateParserListener extends ParseTreeListener<Token> {
	void enterMemberExpr(TemplateParser.MemberExprContext ctx);
	void exitMemberExpr(TemplateParser.MemberExprContext ctx);

	void enterRegion(TemplateParser.RegionContext ctx);
	void exitRegion(TemplateParser.RegionContext ctx);

	void enterBigstringTemplateNoNewline(TemplateParser.BigstringTemplateNoNewlineContext ctx);
	void exitBigstringTemplateNoNewline(TemplateParser.BigstringTemplateNoNewlineContext ctx);

	void enterDict(TemplateParser.DictContext ctx);
	void exitDict(TemplateParser.DictContext ctx);

	void enterDictDef(TemplateParser.DictDefContext ctx);
	void exitDictDef(TemplateParser.DictDefContext ctx);

	void enterGroupName(TemplateParser.GroupNameContext ctx);
	void exitGroupName(TemplateParser.GroupNameContext ctx);

	void enterExpr(TemplateParser.ExprContext ctx);
	void exitExpr(TemplateParser.ExprContext ctx);

	void enterAnonymousTemplate(TemplateParser.AnonymousTemplateContext ctx);
	void exitAnonymousTemplate(TemplateParser.AnonymousTemplateContext ctx);

	void enterEscape(TemplateParser.EscapeContext ctx);
	void exitEscape(TemplateParser.EscapeContext ctx);

	void enterArg(TemplateParser.ArgContext ctx);
	void exitArg(TemplateParser.ArgContext ctx);

	void enterAndConditional(TemplateParser.AndConditionalContext ctx);
	void exitAndConditional(TemplateParser.AndConditionalContext ctx);

	void enterDef(TemplateParser.DefContext ctx);
	void exitDef(TemplateParser.DefContext ctx);

	void enterMapTemplateRef(TemplateParser.MapTemplateRefContext ctx);
	void exitMapTemplateRef(TemplateParser.MapTemplateRefContext ctx);

	void enterTemplateDef(TemplateParser.TemplateDefContext ctx);
	void exitTemplateDef(TemplateParser.TemplateDefContext ctx);

	void enterPrimary(TemplateParser.PrimaryContext ctx);
	void exitPrimary(TemplateParser.PrimaryContext ctx);

	void enterExprNoComma(TemplateParser.ExprNoCommaContext ctx);
	void exitExprNoComma(TemplateParser.ExprNoCommaContext ctx);

	void enterBigstringTemplate(TemplateParser.BigstringTemplateContext ctx);
	void exitBigstringTemplate(TemplateParser.BigstringTemplateContext ctx);

	void enterIfstat(TemplateParser.IfstatContext ctx);
	void exitIfstat(TemplateParser.IfstatContext ctx);

	void enterAnonymousTemplateParameters(TemplateParser.AnonymousTemplateParametersContext ctx);
	void exitAnonymousTemplateParameters(TemplateParser.AnonymousTemplateParametersContext ctx);

	void enterListElement(TemplateParser.ListElementContext ctx);
	void exitListElement(TemplateParser.ListElementContext ctx);

	void enterOption(TemplateParser.OptionContext ctx);
	void exitOption(TemplateParser.OptionContext ctx);

	void enterStringTemplate(TemplateParser.StringTemplateContext ctx);
	void exitStringTemplate(TemplateParser.StringTemplateContext ctx);

	void enterOldStyleHeader(TemplateParser.OldStyleHeaderContext ctx);
	void exitOldStyleHeader(TemplateParser.OldStyleHeaderContext ctx);

	void enterDelimiters(TemplateParser.DelimitersContext ctx);
	void exitDelimiters(TemplateParser.DelimitersContext ctx);

	void enterKeyValuePair(TemplateParser.KeyValuePairContext ctx);
	void exitKeyValuePair(TemplateParser.KeyValuePairContext ctx);

	void enterMapExpr(TemplateParser.MapExprContext ctx);
	void exitMapExpr(TemplateParser.MapExprContext ctx);

	void enterList(TemplateParser.ListContext ctx);
	void exitList(TemplateParser.ListContext ctx);

	void enterDictPairs(TemplateParser.DictPairsContext ctx);
	void exitDictPairs(TemplateParser.DictPairsContext ctx);

	void enterFormalArgs(TemplateParser.FormalArgsContext ctx);
	void exitFormalArgs(TemplateParser.FormalArgsContext ctx);

	void enterExprTag(TemplateParser.ExprTagContext ctx);
	void exitExprTag(TemplateParser.ExprTagContext ctx);

	void enterArgExprList(TemplateParser.ArgExprListContext ctx);
	void exitArgExprList(TemplateParser.ArgExprListContext ctx);

	void enterNotConditional(TemplateParser.NotConditionalContext ctx);
	void exitNotConditional(TemplateParser.NotConditionalContext ctx);

	void enterSubtemplate(TemplateParser.SubtemplateContext ctx);
	void exitSubtemplate(TemplateParser.SubtemplateContext ctx);

	void enterArguments(TemplateParser.ArgumentsContext ctx);
	void exitArguments(TemplateParser.ArgumentsContext ctx);

	void enterFormalArg(TemplateParser.FormalArgContext ctx);
	void exitFormalArg(TemplateParser.FormalArgContext ctx);

	void enterIncludeExpr(TemplateParser.IncludeExprContext ctx);
	void exitIncludeExpr(TemplateParser.IncludeExprContext ctx);

	void enterString(TemplateParser.StringContext ctx);
	void exitString(TemplateParser.StringContext ctx);

	void enterExprOptions(TemplateParser.ExprOptionsContext ctx);
	void exitExprOptions(TemplateParser.ExprOptionsContext ctx);

	void enterConditional(TemplateParser.ConditionalContext ctx);
	void exitConditional(TemplateParser.ConditionalContext ctx);

	void enterNamedArg(TemplateParser.NamedArgContext ctx);
	void exitNamedArg(TemplateParser.NamedArgContext ctx);

	void enterGroup(TemplateParser.GroupContext ctx);
	void exitGroup(TemplateParser.GroupContext ctx);

	void enterTemplateBody(TemplateParser.TemplateBodyContext ctx);
	void exitTemplateBody(TemplateParser.TemplateBodyContext ctx);

	void enterDefaultValuePair(TemplateParser.DefaultValuePairContext ctx);
	void exitDefaultValuePair(TemplateParser.DefaultValuePairContext ctx);

	void enterKeyValue(TemplateParser.KeyValueContext ctx);
	void exitKeyValue(TemplateParser.KeyValueContext ctx);
}