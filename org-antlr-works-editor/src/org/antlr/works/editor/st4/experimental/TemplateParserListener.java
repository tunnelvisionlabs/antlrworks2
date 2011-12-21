
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

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface TemplateParserListener extends ParseTreeListener<Token> {
	void enterRule(TemplateParser.groupContext ctx);
	void exitRule(TemplateParser.groupContext ctx);

	void enterRule(TemplateParser.oldStyleHeaderContext ctx);
	void exitRule(TemplateParser.oldStyleHeaderContext ctx);

	void enterRule(TemplateParser.groupNameContext ctx);
	void exitRule(TemplateParser.groupNameContext ctx);

	void enterRule(TemplateParser.delimitersContext ctx);
	void exitRule(TemplateParser.delimitersContext ctx);

	void enterRule(TemplateParser.defContext ctx);
	void exitRule(TemplateParser.defContext ctx);

	void enterRule(TemplateParser.templateDefContext ctx);
	void exitRule(TemplateParser.templateDefContext ctx);

	void enterRule(TemplateParser.formalArgsContext ctx);
	void exitRule(TemplateParser.formalArgsContext ctx);

	void enterRule(TemplateParser.formalArgContext ctx);
	void exitRule(TemplateParser.formalArgContext ctx);

	void enterRule(TemplateParser.dictDefContext ctx);
	void exitRule(TemplateParser.dictDefContext ctx);

	void enterRule(TemplateParser.dictContext ctx);
	void exitRule(TemplateParser.dictContext ctx);

	void enterRule(TemplateParser.dictPairsContext ctx);
	void exitRule(TemplateParser.dictPairsContext ctx);

	void enterRule(TemplateParser.defaultValuePairContext ctx);
	void exitRule(TemplateParser.defaultValuePairContext ctx);

	void enterRule(TemplateParser.keyValuePairContext ctx);
	void exitRule(TemplateParser.keyValuePairContext ctx);

	void enterRule(TemplateParser.keyValueContext ctx);
	void exitRule(TemplateParser.keyValueContext ctx);

	void enterRule(TemplateParser.stringContext ctx);
	void exitRule(TemplateParser.stringContext ctx);

	void enterRule(TemplateParser.stringTemplateContext ctx);
	void exitRule(TemplateParser.stringTemplateContext ctx);

	void enterRule(TemplateParser.bigstringTemplateContext ctx);
	void exitRule(TemplateParser.bigstringTemplateContext ctx);

	void enterRule(TemplateParser.bigstringTemplateNoNewlineContext ctx);
	void exitRule(TemplateParser.bigstringTemplateNoNewlineContext ctx);

	void enterRule(TemplateParser.anonymousTemplateContext ctx);
	void exitRule(TemplateParser.anonymousTemplateContext ctx);

	void enterRule(TemplateParser.anonymousTemplateParametersContext ctx);
	void exitRule(TemplateParser.anonymousTemplateParametersContext ctx);

	void enterRule(TemplateParser.templateBodyContext ctx);
	void exitRule(TemplateParser.templateBodyContext ctx);

	void enterRule(TemplateParser.escapeContext ctx);
	void exitRule(TemplateParser.escapeContext ctx);

	void enterRule(TemplateParser.exprTagContext ctx);
	void exitRule(TemplateParser.exprTagContext ctx);

	void enterRule(TemplateParser.regionContext ctx);
	void exitRule(TemplateParser.regionContext ctx);

	void enterRule(TemplateParser.subtemplateContext ctx);
	void exitRule(TemplateParser.subtemplateContext ctx);

	void enterRule(TemplateParser.ifstatContext ctx);
	void exitRule(TemplateParser.ifstatContext ctx);

	void enterRule(TemplateParser.conditionalContext ctx);
	void exitRule(TemplateParser.conditionalContext ctx);

	void enterRule(TemplateParser.andConditionalContext ctx);
	void exitRule(TemplateParser.andConditionalContext ctx);

	void enterRule(TemplateParser.notConditionalContext ctx);
	void exitRule(TemplateParser.notConditionalContext ctx);

	void enterRule(TemplateParser.exprOptionsContext ctx);
	void exitRule(TemplateParser.exprOptionsContext ctx);

	void enterRule(TemplateParser.optionContext ctx);
	void exitRule(TemplateParser.optionContext ctx);

	void enterRule(TemplateParser.exprNoCommaContext ctx);
	void exitRule(TemplateParser.exprNoCommaContext ctx);

	void enterRule(TemplateParser.exprContext ctx);
	void exitRule(TemplateParser.exprContext ctx);

	void enterRule(TemplateParser.mapExprContext ctx);
	void exitRule(TemplateParser.mapExprContext ctx);

	void enterRule(TemplateParser.mapTemplateRefContext ctx);
	void exitRule(TemplateParser.mapTemplateRefContext ctx);

	void enterRule(TemplateParser.memberExprContext ctx);
	void exitRule(TemplateParser.memberExprContext ctx);

	void enterRule(TemplateParser.includeExprContext ctx);
	void exitRule(TemplateParser.includeExprContext ctx);

	void enterRule(TemplateParser.primaryContext ctx);
	void exitRule(TemplateParser.primaryContext ctx);

	void enterRule(TemplateParser.argumentsContext ctx);
	void exitRule(TemplateParser.argumentsContext ctx);

	void enterRule(TemplateParser.argExprListContext ctx);
	void exitRule(TemplateParser.argExprListContext ctx);

	void enterRule(TemplateParser.argContext ctx);
	void exitRule(TemplateParser.argContext ctx);

	void enterRule(TemplateParser.namedArgContext ctx);
	void exitRule(TemplateParser.namedArgContext ctx);

	void enterRule(TemplateParser.listContext ctx);
	void exitRule(TemplateParser.listContext ctx);

	void enterRule(TemplateParser.listElementContext ctx);
	void exitRule(TemplateParser.listElementContext ctx);
}