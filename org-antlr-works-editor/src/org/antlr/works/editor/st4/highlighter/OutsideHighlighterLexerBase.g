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
lexer grammar OutsideHighlighterLexerBase;

options {
    language=Java;
    TokenLabelType=CommonToken;
	tokenVocab=GroupHighlighterLexerBase;
	k=2;
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
