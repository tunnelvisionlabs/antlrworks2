
/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.st4.experimental;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class BlankTemplateParserListener implements TemplateParserListener {
	@Override public void enterRule(TemplateParser.groupContext ctx) { }
	@Override public void exitRule(TemplateParser.groupContext ctx) { }

	@Override public void enterRule(TemplateParser.oldStyleHeaderContext ctx) { }
	@Override public void exitRule(TemplateParser.oldStyleHeaderContext ctx) { }

	@Override public void enterRule(TemplateParser.groupNameContext ctx) { }
	@Override public void exitRule(TemplateParser.groupNameContext ctx) { }

	@Override public void enterRule(TemplateParser.delimitersContext ctx) { }
	@Override public void exitRule(TemplateParser.delimitersContext ctx) { }

	@Override public void enterRule(TemplateParser.defContext ctx) { }
	@Override public void exitRule(TemplateParser.defContext ctx) { }

	@Override public void enterRule(TemplateParser.templateDefContext ctx) { }
	@Override public void exitRule(TemplateParser.templateDefContext ctx) { }

	@Override public void enterRule(TemplateParser.formalArgsContext ctx) { }
	@Override public void exitRule(TemplateParser.formalArgsContext ctx) { }

	@Override public void enterRule(TemplateParser.formalArgContext ctx) { }
	@Override public void exitRule(TemplateParser.formalArgContext ctx) { }

	@Override public void enterRule(TemplateParser.dictDefContext ctx) { }
	@Override public void exitRule(TemplateParser.dictDefContext ctx) { }

	@Override public void enterRule(TemplateParser.dictContext ctx) { }
	@Override public void exitRule(TemplateParser.dictContext ctx) { }

	@Override public void enterRule(TemplateParser.dictPairsContext ctx) { }
	@Override public void exitRule(TemplateParser.dictPairsContext ctx) { }

	@Override public void enterRule(TemplateParser.defaultValuePairContext ctx) { }
	@Override public void exitRule(TemplateParser.defaultValuePairContext ctx) { }

	@Override public void enterRule(TemplateParser.keyValuePairContext ctx) { }
	@Override public void exitRule(TemplateParser.keyValuePairContext ctx) { }

	@Override public void enterRule(TemplateParser.keyValueContext ctx) { }
	@Override public void exitRule(TemplateParser.keyValueContext ctx) { }

	@Override public void enterRule(TemplateParser.stringContext ctx) { }
	@Override public void exitRule(TemplateParser.stringContext ctx) { }

	@Override public void enterRule(TemplateParser.stringTemplateContext ctx) { }
	@Override public void exitRule(TemplateParser.stringTemplateContext ctx) { }

	@Override public void enterRule(TemplateParser.bigstringTemplateContext ctx) { }
	@Override public void exitRule(TemplateParser.bigstringTemplateContext ctx) { }

	@Override public void enterRule(TemplateParser.bigstringTemplateNoNewlineContext ctx) { }
	@Override public void exitRule(TemplateParser.bigstringTemplateNoNewlineContext ctx) { }

	@Override public void enterRule(TemplateParser.anonymousTemplateContext ctx) { }
	@Override public void exitRule(TemplateParser.anonymousTemplateContext ctx) { }

	@Override public void enterRule(TemplateParser.anonymousTemplateParametersContext ctx) { }
	@Override public void exitRule(TemplateParser.anonymousTemplateParametersContext ctx) { }

	@Override public void enterRule(TemplateParser.templateBodyContext ctx) { }
	@Override public void exitRule(TemplateParser.templateBodyContext ctx) { }

	@Override public void enterRule(TemplateParser.escapeContext ctx) { }
	@Override public void exitRule(TemplateParser.escapeContext ctx) { }

	@Override public void enterRule(TemplateParser.exprTagContext ctx) { }
	@Override public void exitRule(TemplateParser.exprTagContext ctx) { }

	@Override public void enterRule(TemplateParser.regionContext ctx) { }
	@Override public void exitRule(TemplateParser.regionContext ctx) { }

	@Override public void enterRule(TemplateParser.subtemplateContext ctx) { }
	@Override public void exitRule(TemplateParser.subtemplateContext ctx) { }

	@Override public void enterRule(TemplateParser.ifstatContext ctx) { }
	@Override public void exitRule(TemplateParser.ifstatContext ctx) { }

	@Override public void enterRule(TemplateParser.conditionalContext ctx) { }
	@Override public void exitRule(TemplateParser.conditionalContext ctx) { }

	@Override public void enterRule(TemplateParser.andConditionalContext ctx) { }
	@Override public void exitRule(TemplateParser.andConditionalContext ctx) { }

	@Override public void enterRule(TemplateParser.notConditionalContext ctx) { }
	@Override public void exitRule(TemplateParser.notConditionalContext ctx) { }

	@Override public void enterRule(TemplateParser.exprOptionsContext ctx) { }
	@Override public void exitRule(TemplateParser.exprOptionsContext ctx) { }

	@Override public void enterRule(TemplateParser.optionContext ctx) { }
	@Override public void exitRule(TemplateParser.optionContext ctx) { }

	@Override public void enterRule(TemplateParser.exprNoCommaContext ctx) { }
	@Override public void exitRule(TemplateParser.exprNoCommaContext ctx) { }

	@Override public void enterRule(TemplateParser.exprContext ctx) { }
	@Override public void exitRule(TemplateParser.exprContext ctx) { }

	@Override public void enterRule(TemplateParser.mapExprContext ctx) { }
	@Override public void exitRule(TemplateParser.mapExprContext ctx) { }

	@Override public void enterRule(TemplateParser.mapTemplateRefContext ctx) { }
	@Override public void exitRule(TemplateParser.mapTemplateRefContext ctx) { }

	@Override public void enterRule(TemplateParser.memberExprContext ctx) { }
	@Override public void exitRule(TemplateParser.memberExprContext ctx) { }

	@Override public void enterRule(TemplateParser.includeExprContext ctx) { }
	@Override public void exitRule(TemplateParser.includeExprContext ctx) { }

	@Override public void enterRule(TemplateParser.primaryContext ctx) { }
	@Override public void exitRule(TemplateParser.primaryContext ctx) { }

	@Override public void enterRule(TemplateParser.argumentsContext ctx) { }
	@Override public void exitRule(TemplateParser.argumentsContext ctx) { }

	@Override public void enterRule(TemplateParser.argExprListContext ctx) { }
	@Override public void exitRule(TemplateParser.argExprListContext ctx) { }

	@Override public void enterRule(TemplateParser.argContext ctx) { }
	@Override public void exitRule(TemplateParser.argContext ctx) { }

	@Override public void enterRule(TemplateParser.namedArgContext ctx) { }
	@Override public void exitRule(TemplateParser.namedArgContext ctx) { }

	@Override public void enterRule(TemplateParser.listContext ctx) { }
	@Override public void exitRule(TemplateParser.listContext ctx) { }

	@Override public void enterRule(TemplateParser.listElementContext ctx) { }
	@Override public void exitRule(TemplateParser.listElementContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(Token symbol) { }
}