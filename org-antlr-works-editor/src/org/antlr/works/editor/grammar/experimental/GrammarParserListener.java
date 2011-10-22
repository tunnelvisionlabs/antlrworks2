
/*
 [The "BSD licence"]
 Copyright (c) 2005-2009 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.antlr.works.editor.grammar.experimental;

//import org.antlr.v4.tool.*;
//import org.antlr.v4.tool.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

//@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public interface GrammarParserListener extends ParseTreeListener {
    void enterRule(GrammarParser.grammarSpecContext ctx);
    void exitRule(GrammarParser.grammarSpecContext ctx);

    void enterRule(GrammarParser.grammarTypeContext ctx);
    void exitRule(GrammarParser.grammarTypeContext ctx);

    void enterRule(GrammarParser.prequelConstructContext ctx);
    void exitRule(GrammarParser.prequelConstructContext ctx);

    void enterRule(GrammarParser.optionsSpecContext ctx);
    void exitRule(GrammarParser.optionsSpecContext ctx);

    void enterRule(GrammarParser.optionContext ctx);
    void exitRule(GrammarParser.optionContext ctx);

    void enterRule(GrammarParser.optionValueContext ctx);
    void exitRule(GrammarParser.optionValueContext ctx);

    void enterRule(GrammarParser.delegateGrammarsContext ctx);
    void exitRule(GrammarParser.delegateGrammarsContext ctx);

    void enterRule(GrammarParser.delegateGrammarContext ctx);
    void exitRule(GrammarParser.delegateGrammarContext ctx);

    void enterRule(GrammarParser.tokensSpecContext ctx);
    void exitRule(GrammarParser.tokensSpecContext ctx);

    void enterRule(GrammarParser.tokenSpecContext ctx);
    void exitRule(GrammarParser.tokenSpecContext ctx);

    void enterRule(GrammarParser.actionBlockContext ctx);
    void exitRule(GrammarParser.actionBlockContext ctx);

    void enterRule(GrammarParser.argActionBlockContext ctx);
    void exitRule(GrammarParser.argActionBlockContext ctx);

    void enterRule(GrammarParser.actionContext ctx);
    void exitRule(GrammarParser.actionContext ctx);

    void enterRule(GrammarParser.actionScopeNameContext ctx);
    void exitRule(GrammarParser.actionScopeNameContext ctx);

    void enterRule(GrammarParser.mode_Context ctx);
    void exitRule(GrammarParser.mode_Context ctx);

    void enterRule(GrammarParser.rulesContext ctx);
    void exitRule(GrammarParser.rulesContext ctx);

    void enterRule(GrammarParser.ruleContext ctx);
    void exitRule(GrammarParser.ruleContext ctx);

    void enterRule(GrammarParser.exceptionGroupContext ctx);
    void exitRule(GrammarParser.exceptionGroupContext ctx);

    void enterRule(GrammarParser.exceptionHandlerContext ctx);
    void exitRule(GrammarParser.exceptionHandlerContext ctx);

    void enterRule(GrammarParser.finallyClauseContext ctx);
    void exitRule(GrammarParser.finallyClauseContext ctx);

    void enterRule(GrammarParser.rulePrequelsContext ctx);
    void exitRule(GrammarParser.rulePrequelsContext ctx);

    void enterRule(GrammarParser.rulePrequelContext ctx);
    void exitRule(GrammarParser.rulePrequelContext ctx);

    void enterRule(GrammarParser.ruleReturnsContext ctx);
    void exitRule(GrammarParser.ruleReturnsContext ctx);

    void enterRule(GrammarParser.throwsSpecContext ctx);
    void exitRule(GrammarParser.throwsSpecContext ctx);

    void enterRule(GrammarParser.locals_Context ctx);
    void exitRule(GrammarParser.locals_Context ctx);

    void enterRule(GrammarParser.ruleActionContext ctx);
    void exitRule(GrammarParser.ruleActionContext ctx);

    void enterRule(GrammarParser.ruleModifiersContext ctx);
    void exitRule(GrammarParser.ruleModifiersContext ctx);

    void enterRule(GrammarParser.ruleModifierContext ctx);
    void exitRule(GrammarParser.ruleModifierContext ctx);

    void enterRule(GrammarParser.ruleBlockContext ctx);
    void exitRule(GrammarParser.ruleBlockContext ctx);

    void enterRule(GrammarParser.ruleAltListContext ctx);
    void exitRule(GrammarParser.ruleAltListContext ctx);

    void enterRule(GrammarParser.labeledAltContext ctx);
    void exitRule(GrammarParser.labeledAltContext ctx);

    void enterRule(GrammarParser.altListContext ctx);
    void exitRule(GrammarParser.altListContext ctx);

    void enterRule(GrammarParser.alternativeContext ctx);
    void exitRule(GrammarParser.alternativeContext ctx);

    void enterRule(GrammarParser.elementsContext ctx);
    void exitRule(GrammarParser.elementsContext ctx);

    void enterRule(GrammarParser.elementContext ctx);
    void exitRule(GrammarParser.elementContext ctx);

    void enterRule(GrammarParser.labeledElementContext ctx);
    void exitRule(GrammarParser.labeledElementContext ctx);

    void enterRule(GrammarParser.treeSpecContext ctx);
    void exitRule(GrammarParser.treeSpecContext ctx);

    void enterRule(GrammarParser.ebnfContext ctx);
    void exitRule(GrammarParser.ebnfContext ctx);

    void enterRule(GrammarParser.blockSuffixContext ctx);
    void exitRule(GrammarParser.blockSuffixContext ctx);

    void enterRule(GrammarParser.ebnfSuffixContext ctx);
    void exitRule(GrammarParser.ebnfSuffixContext ctx);

    void enterRule(GrammarParser.atomContext ctx);
    void exitRule(GrammarParser.atomContext ctx);

    void enterRule(GrammarParser.notSetContext ctx);
    void exitRule(GrammarParser.notSetContext ctx);

    void enterRule(GrammarParser.blockSetContext ctx);
    void exitRule(GrammarParser.blockSetContext ctx);

    void enterRule(GrammarParser.setElementContext ctx);
    void exitRule(GrammarParser.setElementContext ctx);

    void enterRule(GrammarParser.blockContext ctx);
    void exitRule(GrammarParser.blockContext ctx);

    void enterRule(GrammarParser.rulerefContext ctx);
    void exitRule(GrammarParser.rulerefContext ctx);

    void enterRule(GrammarParser.rangeContext ctx);
    void exitRule(GrammarParser.rangeContext ctx);

    void enterRule(GrammarParser.terminalContext ctx);
    void exitRule(GrammarParser.terminalContext ctx);

    void enterRule(GrammarParser.elementOptionsContext ctx);
    void exitRule(GrammarParser.elementOptionsContext ctx);

    void enterRule(GrammarParser.elementOptionContext ctx);
    void exitRule(GrammarParser.elementOptionContext ctx);

    void enterRule(GrammarParser.rewriteContext ctx);
    void exitRule(GrammarParser.rewriteContext ctx);

    void enterRule(GrammarParser.predicatedRewriteContext ctx);
    void exitRule(GrammarParser.predicatedRewriteContext ctx);

    void enterRule(GrammarParser.nakedRewriteContext ctx);
    void exitRule(GrammarParser.nakedRewriteContext ctx);

    void enterRule(GrammarParser.rewriteAltContext ctx);
    void exitRule(GrammarParser.rewriteAltContext ctx);

    void enterRule(GrammarParser.rewriteTreeAltContext ctx);
    void exitRule(GrammarParser.rewriteTreeAltContext ctx);

    void enterRule(GrammarParser.rewriteTreeElementContext ctx);
    void exitRule(GrammarParser.rewriteTreeElementContext ctx);

    void enterRule(GrammarParser.rewriteTreeAtomContext ctx);
    void exitRule(GrammarParser.rewriteTreeAtomContext ctx);

    void enterRule(GrammarParser.rewriteTreeEbnfContext ctx);
    void exitRule(GrammarParser.rewriteTreeEbnfContext ctx);

    void enterRule(GrammarParser.rewriteEbnfSuffixContext ctx);
    void exitRule(GrammarParser.rewriteEbnfSuffixContext ctx);

    void enterRule(GrammarParser.rewriteTreeContext ctx);
    void exitRule(GrammarParser.rewriteTreeContext ctx);

    void enterRule(GrammarParser.rewriteTemplateContext ctx);
    void exitRule(GrammarParser.rewriteTemplateContext ctx);

    void enterRule(GrammarParser.rewriteTemplateRefContext ctx);
    void exitRule(GrammarParser.rewriteTemplateRefContext ctx);

    void enterRule(GrammarParser.rewriteIndirectTemplateHeadContext ctx);
    void exitRule(GrammarParser.rewriteIndirectTemplateHeadContext ctx);

    void enterRule(GrammarParser.rewriteTemplateArgsContext ctx);
    void exitRule(GrammarParser.rewriteTemplateArgsContext ctx);

    void enterRule(GrammarParser.rewriteTemplateArgContext ctx);
    void exitRule(GrammarParser.rewriteTemplateArgContext ctx);

    void enterRule(GrammarParser.idContext ctx);
    void exitRule(GrammarParser.idContext ctx);

    void enterRule(GrammarParser.qidContext ctx);
    void exitRule(GrammarParser.qidContext ctx);

    void enterRule(GrammarParser.alternativeEntryContext ctx);
    void exitRule(GrammarParser.alternativeEntryContext ctx);

    void enterRule(GrammarParser.elementEntryContext ctx);
    void exitRule(GrammarParser.elementEntryContext ctx);

    void enterRule(GrammarParser.ruleEntryContext ctx);
    void exitRule(GrammarParser.ruleEntryContext ctx);

    void enterRule(GrammarParser.blockEntryContext ctx);
    void exitRule(GrammarParser.blockEntryContext ctx);
}