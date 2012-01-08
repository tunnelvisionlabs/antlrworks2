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

@header {
/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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

package org.antlr.works.editor.grammar.highlighter4;
}

@members {
protected int getMultilineCommentType() {
    return modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : Action_ML_COMMENT;
}
}

LEXER       : 'lexer';
PARSER      : 'parser';
CATCH       : 'catch';
FINALLY     : 'finally';
GRAMMAR     : 'grammar';
PRIVATE     : 'private';
PROTECTED   : 'protected';
PUBLIC      : 'public';
RETURNS     : 'returns';
THROWS      : 'throws';
IMPORT      : 'import';
FRAGMENT    : 'fragment';
TOKENS      : 'tokens' IGNORED '{' {pushMode(NonActionBrace);};
OPTIONS     : 'options' IGNORED '{' {pushMode(NonActionBrace);};

// v4 only
MODE        : 'mode';
LOCALS      : 'locals';

// v3 only
TREE        : 'tree';
SCOPE       : 'scope';

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

LCURLY:	'{' {$type=Action_TEXT; pushMode(Action);};

RCURLY:	'}'	;

DOLLAR : '$' ;

LABEL
    :   IDENTIFIER IGNORED '='
    ;

IDENTIFIER
	:	('a'..'z' | 'A'..'Z' | '_')
		('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*
	;

INT
	:	('0'..'9')+
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
    :   '/*'                    {pushMode(BlockComment);}
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

fragment
IGNORED
    :   (' ' | '\t' | '\r' | '\n')*
    ;

fragment
XDIGIT
	:	'0' .. '9'
	|	'a' .. 'f'
	|	'A' .. 'F'
	;

ANYCHAR
    :   .
    ;

mode BlockComment;

    BlockComment_NEWLINE : NEWLINE {$type = NEWLINE;};

    CONTINUE_ML_COMMENT
        :   ~('\r' | '\n' | '*')+   {$type = getMultilineCommentType();}
        ;

    END_ML_COMMENT
        :   '*/'                    {$type = getMultilineCommentType(); popMode();}
        ;

    ML_COMMENT_STAR
        :   '*'                     {$type = getMultilineCommentType();}
        ;

    BlockComment_ANYCHAR : . {$type = ANYCHAR;};

mode ArgAction;

    ArgAction_NEWLINE : NEWLINE {$type = NEWLINE;};

    ArgAction_RBRACK
        :   ']' {$type = RBRACK; popMode();}
        ;

    ArgAction_TEXT
        :   (   ~('{' | '}' | '/' | '\r' | '\n' | '$' | '\\' | '\'' | '"')
            )+
        ;

    ArgAction_CHAR_LITERAL
        :   CHAR_LITERAL
        ;

    ArgAction_STRING_LITERAL
        :   STRING_LITERAL
        ;

    ArgAction_ESCAPE
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
            |   ']'
            |	'u' XDIGIT XDIGIT XDIGIT XDIGIT
            )
        ;

    ArgAction_REFERENCE
        :   REFERENCE
        ;

    ArgAction_SPECIAL
        :   ('$' | '/' | '\\') {$type = Action_TEXT;}
        ;

    ArgAction_ANYCHAR : . {$type = ANYCHAR;};

mode NonActionBrace;

    NonActionBrace_NEWLINE : NEWLINE {$type = NEWLINE;};
    NonActionBrace_WS : WS {$type = WS;};
    NonActionBrace_LCURLY : LCURLY {$type = LCURLY; popMode();};

mode Action;

    Action_NEWLINE : NEWLINE {$type = NEWLINE;};

    Action_COMMENT
        :	'//' (~('\r' | '\n'))*
        ;

    Action_ML_COMMENT
        :   '/*'                    {pushMode(BlockComment);}
        ;

    Action_LCURLY
        :   '{' {pushMode(Action); $type = Action_TEXT;}
        ;

    Action_RCURLY
        :   '}' {popMode(); $type = Action_TEXT;}
        ;

    Action_TEXT
        :   (   ~('{' | '}' | '/' | '\r' | '\n' | '$' | '\\' | '\'' | '"')
            )+
        ;

    Action_CHAR_LITERAL
        :   CHAR_LITERAL
        ;

    Action_STRING_LITERAL
        :   STRING_LITERAL
        ;

    Action_ESCAPE
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
            )
        ;

    Action_REFERENCE
        :   REFERENCE
        ;

    Action_SPECIAL
        :   ('$' | '/' | '\\') {$type = Action_TEXT;}
        ;

    Action_ANYCHAR : . {$type = ANYCHAR;};

