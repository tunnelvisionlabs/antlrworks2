/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar ActionHighlighterLexerBase;

options {
	language=Java;
	TokenLabelType=CommonToken;
	tokenVocab=GrammarHighlighterLexerBase;
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
