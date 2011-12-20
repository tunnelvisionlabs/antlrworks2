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
lexer grammar GrammarHighlighterLexerBase;

options {
	language=Java;
	//tokenVocab=ANTLR;
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

/*//////////////////////////////////////////////////////////////////////////*/
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
// L E X E R

fragment ParserRule : ;
fragment LexerRule : ;
fragment Keyword : ;
fragment ACTION : ;
fragment ARG_ACTION : ;
fragment LABEL : ;

fragment ValidGrammarOption : ;
fragment InvalidGrammarOption : ;
fragment OptionValue : ;

IDENTIFIER
	:	('a'..'z' | 'A'..'Z' | '_')
		('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*
	;

DIRECTIVE
	:	'@' IDENTIFIER
	;

REFERENCE
	:	'$' IDENTIFIER
	;

WS
	:	(	' '
		|	'\t'
		)+
	;

NEWLINE
	:	'\r'? '\n'
	;

COMMENT
	:	'//' (~('\r' | '\n'))*
	;

ML_COMMENT
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

CHAR_LITERAL
	:	'\''
		(	'\\' .
		|	~('\r' | '\n' | '\'' | '\\')
		)*
		'\''?
	;

STRING_LITERAL
	:	'"'
		(	'\\' .
		|	~('\r' | '\n' | '"' | '\\')
		)*
		'"'?
	;

OPEN_ELEMENT_OPTION
	:	'<'
	;

CLOSE_ELEMENT_OPTION
	:	'>'
	;

AMPERSAND : '@';

COMMA : ',';

QUESTION :	'?' ;

TREE_BEGIN : '^(' ;

LPAREN:	'(' ;

RPAREN:	')' ;

COLON :	':' ;

STAR:	'*' ;

PLUS:	'+' ;

ASSIGN : '=' ;

PLUS_ASSIGN : '+=' ;

IMPLIES : '=>' ;

REWRITE : '->' ;

SEMI:	';' ;

ROOT : '^';

BANG : '!';

OR	:	'|' ;

WILDCARD : '.' ;

ETC : '...' ;

RANGE : '..' ;

NOT :	'~' ;

LBRACK:	'['	;

RBRACK:	']'	;

LCURLY:	'{'	;

RCURLY:	'}'	;

DOLLAR : '$' ;

// closes a bracket action
ESCAPE_BRACKET
	:	'\\]'
	;

DOUBLE_ANGLE_STRING_LITERAL
	:	'<<' CONTINUE_DOUBLE_ANGLE_STRING_LITERAL {$type = state.type;}
	;

fragment END_DOUBLE_ANGLE_STRING_LITERAL : ;

fragment
CONTINUE_DOUBLE_ANGLE_STRING_LITERAL
	:	(	~('\r'|'\n'|'>')
		|	{input.LA(2) != '>'}? => '>'
		)*
		(	'>>' {state.type = END_DOUBLE_ANGLE_STRING_LITERAL;}
		|	{state.type = CONTINUE_DOUBLE_ANGLE_STRING_LITERAL;}
		)
	;

fragment
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	'>'
		|	'u' XDIGIT XDIGIT XDIGIT XDIGIT
		|	. // unknown, leave as it is
		)
	;

fragment
XDIGIT
	:	'0' .. '9'
	|	'a' .. 'f'
	|	'A' .. 'F'
	;

INT
	:	('0'..'9')+
	;

ANYCHAR
	:	.
	;
