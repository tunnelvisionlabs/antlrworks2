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
lexer grammar TemplateLexerBase;

//options {
//    language=Java;
//    TokenLabelType=CommonToken;
//}

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
package org.antlr.works.editor.st4.experimental;
}

tokens {
    OPEN_DELIMITER;
    CLOSE_DELIMITER;
    QUOTE;
}

LPAREN  : '(';
RPAREN  : ')';
COMMA   : ',';
DOT     : '.';
COLON   : ':';
DEFINED : '::=';
EQUALS  : '=';
AT      : '@';
LBRACK  : '[';
RBRACK  : ']';
LBRACE  : '{' ANONYMOUS_PARAMETERS? {pushMode(AnonymousTemplate);};
RBRACE  : '}';

GROUP   : 'group';
DEFAULT : 'default';
IMPORT  : 'import';
TRUE    : 'true';
FALSE   : 'false';
DELIMITERS : 'delimiters' DELIMITERS_SPEC?;

WS      : (' ' | '\t')+ {$channel = HIDDEN;};
NEWLINE : ('\r' '\n'? | '\n')  {$channel = HIDDEN;};

ID      : ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '-' | '_')*
        ;

STRING
    :   '"' {$type = QUOTE; pushMode(StringTemplate);}
    ;

BIGSTRING
    :   '<<' {pushMode(BigStringTemplate);}
    ;

BIGSTRINGLINE
    :   '<%' {pushMode(BigStringLineTemplate);}
    ;

LINE_COMMENT
    :   '//' ~('\r' | '\n')*    {$channel = HIDDEN;}
    ;

COMMENT
    :   '/*'                    {$channel = HIDDEN; pushMode(BlockComment);}
    ;

fragment
ANONYMOUS_PARAMETERS
    :   WS_CHAR* ID (WS_CHAR* COMMA WS_CHAR* ID)* WS_CHAR* '|'
    ;

fragment
DELIMITERS_SPEC
    :   WS_CHAR* DELIMITER_STRING WS_CHAR* COMMA WS_CHAR* DELIMITER_STRING
    ;

fragment
DELIMITER_STRING
    :   '"' (~('\r' | '\n' | '"'))+ '"'
    ;

fragment
WS_CHAR
    :   ' ' | '\t' | '\r' | '\n'
    ;

fragment OPEN_DELIMITER : '\uFFF0';
fragment CLOSE_DELIMITER : '\uFFF1';

ANYCHAR
    :   .
    ;

mode BlockComment;

    BlockComment_NEWLINE : NEWLINE {$type = NEWLINE; $channel = HIDDEN;};

    CONTINUE_COMMENT
        :   ~('\r' | '\n' | '*')+   {$type = COMMENT; $channel = HIDDEN;}
        ;

    END_COMMENT
        :   '*/'                    {$type = COMMENT; $channel = HIDDEN; popMode();}
        ;

    BLOCK_COMMENT_STAR
        :   '*'                     {$type = COMMENT; $channel = HIDDEN;}
        ;

    BlockComment_ANYCHAR : . {$type = ANYCHAR; $channel = HIDDEN;};

mode TemplateComment;

    TemplateComment_NEWLINE : NEWLINE {$type = NEWLINE; $channel = HIDDEN;};

    TemplateComment_CONTINUE_COMMENT
        :   ~('\r' | '\n' | '!')+   {$type = COMMENT; $channel = HIDDEN;}
        ;

    TemplateComment_END_COMMENT
        :   '!' CLOSE_DELIMITER     {$type = COMMENT; $channel = HIDDEN; popMode();}
        ;

    TemplateComment_BLOCK_COMMENT_BANG
        :   '!'                     {$type = COMMENT; $channel = HIDDEN;}
        ;

    TemplateComment_ANYCHAR : . {$type = ANYCHAR; $channel = HIDDEN;};

mode AnonymousTemplate;

    AnonymousTemplate_RBRACE : RBRACE {$type = RBRACE; popMode();};
    AnonymousTemplate_NEWLINE : NEWLINE {$type = NEWLINE;};

    AnonymousTemplate_COMMENT
        :   OPEN_DELIMITER '!' {$type = COMMENT; pushMode(TemplateComment);}
        ;

    AnonymousTemplate_OPEN_DELIMITER  : OPEN_DELIMITER {$type = OPEN_DELIMITER; pushMode(TemplateExpression);};
    TEXT    : (~('\\' | '}' | '\uFFF0' | '\r' | '\n'))+;
    ESCAPE_RBRACE   : '\\' (. | EOF) {$type = TEXT;};

    AnonymousTemplate_ANYCHAR : . {$type = ANYCHAR;};

mode AnonymousTemplateParameters;

    AnonymousTemplateParameters_WS : WS {$type = WS; $channel = HIDDEN;};
    AnonymousTemplateParameters_NEWLINE : NEWLINE {$type = NEWLINE; $channel = HIDDEN;};
    AnonymousTemplateParameters_COMMA : COMMA {$type = COMMA;};

    TEMPLATE_PARAMETER : ID;
    PIPE : '|' {popMode();};

    AnonymousTemplateParameters_ANYCHAR : . {$type = ANYCHAR;};

mode BigStringTemplate;

    BigStringTemplate_NEWLINE : NEWLINE {$type = NEWLINE;};

    BigStringTemplate_COMMENT
        :   OPEN_DELIMITER '!' {$type = COMMENT; pushMode(TemplateComment);}
        ;

    BigStringTemplate_OPEN_DELIMITER : OPEN_DELIMITER {$type = OPEN_DELIMITER; pushMode(TemplateExpression);};
    BigStringTemplate_TEXT
        :   (~('\\' | '>' | '\uFFF0' | '\r' | '\n'))+ {$type = TEXT;}
        ;

    BigStringTemplate_ESCAPE : '\\' (. | EOF) {$type = TEXT;};

    BigStringTemplate_END : '>>' {popMode();};
    BigStringTemplate_LANGLE : '>' {$type = TEXT;};

    BigStringTemplate_ANYCHAR : . {$type = ANYCHAR;};

mode BigStringLineTemplate;

    BigStringLineTemplate_NEWLINE : NEWLINE {$type = NEWLINE;};

    BigStringLineTemplate_COMMENT
        :   OPEN_DELIMITER '!' {$type = COMMENT; pushMode(TemplateComment);}
        ;

    BigStringLineTemplate_OPEN_DELIMITER : OPEN_DELIMITER {$type = OPEN_DELIMITER; pushMode(TemplateExpression);};
    BigStringLineTemplate_TEXT
        :   (~('\\' | '%' | '\uFFF0' | '\r' | '\n'))+ {$type = TEXT;}
        ;

    BigStringLineTemplate_ESCAPE : '\\' (. | EOF) {$type = TEXT;};

    BigStringLineTemplate_END : '%>' {popMode();};
    BigStringLineTemplate_PERCENT : '%' {$type = TEXT;};

    BigStringLineTemplate_ANYCHAR : . {$type = ANYCHAR;};

mode TemplateExpression;

    TemplateExpression_NEWLINE : NEWLINE {$type = NEWLINE; $channel = HIDDEN;};
    //TemplateExpression_AT : AT {$type = AT;};
    TemplateExpression_DOT : DOT {$type = DOT;};
    TemplateExpression_COMMA : COMMA {$type = COMMA;};
    TemplateExpression_COLON : COLON {$type = COLON;};
    TemplateExpression_LPAREN : LPAREN {$type = LPAREN;};
    TemplateExpression_RPAREN : RPAREN {$type = RPAREN;};
    TemplateExpression_LBRACK : LBRACK {$type = LBRACK;};
    TemplateExpression_RBRACK : RBRACK {$type = RBRACK;};
    TemplateExpression_EQUALS : EQUALS {$type = EQUALS;};
    TemplateExpression_LBRACE : LBRACE {$type = LBRACE; pushMode(AnonymousTemplate);};
    TemplateExpression_WS : WS {$type = WS; $channel = HIDDEN;};

    SUPER : 'super';
    IF : 'if';
    ELSEIF : 'elseif';
    ENDIF : 'endif';
    ELSE : 'else';
    END : 'end';
    ELLIPSIS : '...';
    NOT : '!';
    OR : '||';
    AND : '&&';
    SEMI : ';';
    ESCAPE : '\\' (~'\uFFF1')*;

    REGION_END : '@end';
    REGION_ID : '@' ID;
    TemplateExpression_ID : ID {$type = ID;};

    TemplateExpression_CLOSE_DELIMITER : CLOSE_DELIMITER {$type = CLOSE_DELIMITER; popMode();};
    TemplateExpression_STRING
        :   '"' (~('"' | '\\') | '\\' (. | EOF))* '"' {$type = STRING;}
        ;

    TemplateExpression_ANYCHAR : . {$type = ANYCHAR;};

mode StringTemplate;

    StringTemplate_NEWLINE : NEWLINE {$type = NEWLINE;};

    StringTemplate_COMMENT
        :   OPEN_DELIMITER '!' {$type = COMMENT; pushMode(TemplateComment);}
        ;

    StringTemplate_OPEN_DELIMITER : OPEN_DELIMITER {$type = OPEN_DELIMITER; pushMode(TemplateExpression);};
    StringTemplate_TEXT
        :   (~('\\' | '"' | '\uFFF0' | '\r' | '\n'))+ {$type = TEXT;}
        ;

    StringTemplate_ESCAPE : '\\' (. | EOF) {$type = TEXT;};

    StringTemplate_END : '"' {$type = QUOTE; popMode();};
    //StringTemplate_CLOSE_DELIMITER : '>' {$type = TEXT;};

    StringTemplate_ANYCHAR : . {$type = ANYCHAR;};

mode DelimitersOpenSpec;

    DelimitersOpenSpec_WS : WS {$type = WS;};
    DelimitersOpenSpec_NEWLINE : NEWLINE {$type = NEWLINE;};
    DelimitersOpenSpec_DELIMITER_STRING : DELIMITER_STRING;
    DelimitersOpenSpec_COMMA : COMMA {$type = COMMA; mode(DelimitersCloseSpec);};

mode DelimitersCloseSpec;

    DelimitersCloseSpec_WS : WS {$type = WS;};
    DelimitersCloseSpec_NEWLINE : NEWLINE {$type = NEWLINE;};
    DelimitersCloseSpec_DELIMITER_STRING : DELIMITER_STRING {popMode();};
