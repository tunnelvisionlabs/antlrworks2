/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar OutsideHighlighterLexerBase;

options {
    language=Java;
    TokenLabelType=CommonToken;
	tokenVocab=GroupHighlighterLexerBase;
	k=2;
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
protected int getAnonymousTemplateLevel() {
    throw new UnsupportedOperationException("This operation is not supported by the base class.");
}

protected char getOpenDelimiter() {
    throw new UnsupportedOperationException("This operation is not supported by the base class.");
}

protected char getCloseDelimiter() {
    throw new UnsupportedOperationException("This operation is not supported by the base class.");
}

protected OutermostTemplate getOutermost() {
    throw new UnsupportedOperationException("This operation is not supported by the base class.");
}
}

//LANGLE	: '<';
COMMA	: ',';
PIPE	: '|';

ID
	:	('a'..'z' | 'A'..'Z' | '_')+
	;

WS
	:	(' ' | '\t')+
	;

//fragment
//RANGLE
//	:	{getOutermost() == OutermostTemplate.BigString}? => '\\>'
//	|	{getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>'}? => '>'
//	;

COMMENT
	:	LDELIM '!' CONTINUE_COMMENT {$type = state.type;}
	;

fragment END_COMMENT : ;

fragment
CONTINUE_COMMENT
	:	(	~('\r'|'\n'|'!')
		|	{input.LA(2) != getCloseDelimiter()}? => '!'
		)*
		(	'!' RDELIM {state.type = END_COMMENT;}
		|	{state.type = CONTINUE_COMMENT;}
		)
	;

ESCAPE_TAG
	:	LDELIM '\\' ({input.LA(1) != EOF && input.LA(1) != getCloseDelimiter()}? => .)* RDELIM
	;

ESCAPE_CHAR
	:	'\\' c=.
		{
			if ($c != '>' && (getOutermost() != OutermostTemplate.String || $c != '"'))
				$type = TEXT;
		}
	;

NEWLINE
	:	('\n' | '\r')+
	;

TEXT
	:	(	{input.LA(1) != getOpenDelimiter()}? => ~('}'|'\n'|'\r'|'"'|'>'|'\\'|'%'|'a'..'z'|'A'..'Z'|'_'|' '|'\t'|'|')
		|	{getAnonymousTemplateLevel() == 0}? => '}'
		|	{getOutermost() != OutermostTemplate.String}? => '"'
		|	{getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>'}? => '>'
		|	{getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>'}? => '%'
		)+
	;

LDELIM	: {input.LA(1) == getOpenDelimiter()}? => ~'\\';

fragment
RDELIM	: {input.LA(1) == getCloseDelimiter()}? => ~'\\';
