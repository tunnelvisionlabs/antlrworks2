/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar GroupHighlighterLexerBase;

options {
    language=Java;
    TokenLabelType=CommonToken;
}

@header {/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;
}

fragment PARAMETER_DEFINITION : ;

LPAREN : '(';
RPAREN : ')';
COMMA : ',';
DOT : '.';
COLON : ':';
DEFINED : '::=';
EQUALS : '=';
AT : '@';
LBRACK : '[';
RBRACK : ']';
LBRACE : '{';
RBRACE : '}';
QUOTE : '"';

BEGIN_BIGSTRING
	:	'<<'
	;

END_BIGSTRING
	:	'>>'
	;

BEGIN_BIGSTRINGLINE
	:	'<%'
	;

END_BIGSTRINGLINE
	:	'%>'
	;

ID	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_')*
	;

fragment
LEGACY_DELIMITERS : ;

LINE_COMMENT
	:	'//'
		(	options{greedy=true;}
		:	'!delimiters' (' ' | '\t')		{$type = LEGACY_DELIMITERS;}
		|	(~('\r'|'\n'))*
		)
	;

COMMENT
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

WS
	:	(' '|'\t')*
	;

NEWLINE
	:	('\r'|'\n')*
	;

fragment
DELIMITER_SPEC
	:	'"' ~('\n' | '\r' | '"')* '"'?
		{state.type = DELIMITER_SPEC;}
	;

ANYCHAR
	:	.
	;
