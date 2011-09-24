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
lexer grammar ActionHighlighterLexerBase;

options {
	language=Java;
	TokenLabelType=CommonToken;
	tokenVocab=GrammarHighlighterLexerBase;
}

@header {
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

package org.antlr.works.editor.grammar.highlighter;
}

@members {
protected ANTLRHighlighterLexer getLexer() {
    throw new UnsupportedOperationException("This method is not implemented in the base lexer.");
}

private static boolean isIdStartChar(int c) {
    return (c >= 'a' && c <= 'z')
        || (c >= 'A' && c <= 'Z')
        || c == '_';
}
}

ACTION_REFERENCE
	:	'$' IDENTIFIER
	;

fragment
IDENTIFIER
	:	('a'..'z' | 'A'..'Z' | '_')
		('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*
	;

DOLLAR : '$' ;

ACTION_CHAR_LITERAL
	:	'\'' CONTINUE_CHAR_LITERAL {$type = state.type;}
	;

fragment END_CHAR_LITERAL : ;

fragment CONTINUE_CHAR_LITERAL
	:	(	~('\r'|'\n'|'\''|'\\')
		|	'\\' (options{greedy=true;} : .)?
		)*
		(	'\'' {state.type = END_CHAR_LITERAL;}
		|	{state.type = CONTINUE_CHAR_LITERAL;}
		)
	;

ACTION_STRING_LITERAL
	:	'"' CONTINUE_STRING_LITERAL {$type = state.type;}
	;

fragment END_STRING_LITERAL : ;

fragment CONTINUE_STRING_LITERAL
	:	(	~('\r'|'\n'|'"'|'\\')
		|	'\\' (options{greedy=true;} : .)?
		)*
		(	'"' {state.type = END_STRING_LITERAL;}
		|	{state.type = CONTINUE_STRING_LITERAL;}
		)
	;

ACTION_COMMENT
	:	'//' ~('\r' | '\n')*
	;

ACTION_ML_COMMENT
	:	'/*' CONTINUE_COMMENT {$type = state.type;}
	;

fragment END_COMMENT : ;

fragment
CONTINUE_COMMENT
	:	(	~('\r'|'\n'|'*')
		|	{input.LA(2) != '/'}? => '*'
		)*
		(	'*/' {state.type = END_COMMENT;}
		|	{state.type = CONTINUE_COMMENT;}
		)
	;

ACTION_ESCAPE
	:	'\\' .
	;

ACTION_TEXT
	:	(	~('\r' | '\n' | '\\' | '/' | '{' | '}' | ']' | '$' | '\'' | '"')
		|	{!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION)}? => ('{' | '}')
		|	{!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION)}? => ']'
		|	{!isIdStartChar(input.LA(2))}? => '$'
		|	{input.LA(2) != '/' && input.LA(2) != '*'}? => '/'
		)+
	;

NEWLINE
	:	'\r'? '\n'
	;

ANYCHAR
	:	.
	;
