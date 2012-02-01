/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar InsideHighlighterLexerBase;

options {
	language=Java;
	TokenLabelType=CommonToken;
	tokenVocab=OutsideHighlighterLexerBase;
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

@members {
protected char getCloseDelimiter() {
    throw new UnsupportedOperationException("This operation is not supported by the base class.");
}
}

DOT : '.';
ELLIPSIS : '...';
COMMA : ',';
COLON : ':';
SEMI : ';';
LPAREN : '(';
RPAREN : ')';
LBRACK : '[';
RBRACK : ']';
EQUALS : '=';
NOT : '!';
OR : '||';
AND : '&&';
LBRACE : '{';
RBRACE : '}';

REGION_REF
	:	'@' EXPR_IDENTIFIER
	;

EXPR_IDENTIFIER
	:	('a'..'z' | 'A'..'Z' | '_')
		('a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '/')*
	;

WS
	:	(' '|'\t')+
	;

NEWLINE
	:	('\r'|'\n')+
	;

STRING
	:	'"'
		(	~('"'|'\\')
		|	'\\' .
		)*
		'"'
	;

//LDELIM	: {input.LA(1) == getOpenDelimiter()}? => .;
RDELIM	: {input.LA(1) == getCloseDelimiter()}? => .;

ANYCHAR
	:	.
	;
