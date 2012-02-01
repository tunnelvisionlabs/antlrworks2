/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar GrammarHighlighterLexerBase;

options {
	language=Java;
	//tokenVocab=ANTLR;
}

@header {/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
