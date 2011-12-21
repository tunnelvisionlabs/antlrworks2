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
parser grammar TemplateParser;

options {
    //language=Java;
    //TokenLabelType=CommonToken;
    tokenVocab=TemplateLexerBase;
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
package org.antlr.works.editor.st4.experimental;
}

group
    :   oldStyleHeader?
        delimiters?
        (   'import' string
        )*
        def+
    ;

oldStyleHeader
    :   'group' ID (':' ID)?
        ('implements' ID (',' ID)*)?
        ';'
    ;

groupName
    :   ID ('.' ID)*
    ;

delimiters
    :   'delimiters' DelimitersOpenSpec_DELIMITER_STRING ',' DelimitersCloseSpec_DELIMITER_STRING
    ;

def
    :   templateDef
    |   dictDef
    ;

templateDef
    :   (   '@' enclosing=ID '.' name=ID '(' ')'
        |   name=ID '(' formalArgs ')'
        )
        '::='
        (   stringTemplate
        |   bigstringTemplate
        |   bigstringTemplateNoNewline
        )
    |   alias=ID '::=' target=ID
    ;

formalArgs
    :   formalArg (',' formalArg)*
    |
    ;

formalArg
    :   name=ID
        (   '=' (string | anonymousTemplate | TRUE | FALSE)
        )?
    ;

dictDef
    :   name=ID '::=' dict
    ;

dict
    :   '[' dictPairs ']'
    ;

dictPairs
    :   keyValuePair (',' keyValuePair)* (',' defaultValuePair)?
    |   defaultValuePair
    ;

defaultValuePair
    :   'default' ':' keyValue
    ;

keyValuePair
    :   string ':' keyValue
    ;

keyValue
    :   bigstringTemplate
    |   bigstringTemplateNoNewline
    |   anonymousTemplate
    |   string
    |   TRUE
    |   FALSE
    |   ID
    ;

string
    :   stringTemplate
    ;

stringTemplate
    :   QUOTE
        templateBody
        QUOTE
    ;

bigstringTemplate
    :   BIGSTRING
        templateBody
        BigStringTemplate_END
    ;

bigstringTemplateNoNewline
    :   BIGSTRINGLINE
        templateBody
        BigStringLineTemplate_END
    ;

anonymousTemplate
    :   LBRACE
        anonymousTemplateParameters?
        templateBody
        RBRACE
    ;

anonymousTemplateParameters
    :   names+=TEMPLATE_PARAMETER (COMMA names+=TEMPLATE_PARAMETER)* PIPE
    ;

templateBody
    :   (   NEWLINE
        |   COMMENT
        |   ifstat
        |   region
        |   exprTag
        |   escape
        |   TEXT
        )*
    ;

escape
    :   OPEN_DELIMITER ESCAPE CLOSE_DELIMITER
    ;

exprTag
    :   OPEN_DELIMITER
        expr (SEMI exprOptions)?
        CLOSE_DELIMITER
    ;

region
    :   OPEN_DELIMITER REGION_ID CLOSE_DELIMITER
        templateBody
        OPEN_DELIMITER REGION_END CLOSE_DELIMITER
    ;

subtemplate
    :   anonymousTemplate
    ;

ifstat
    :   OPEN_DELIMITER IF LPAREN conditional RPAREN CLOSE_DELIMITER
        templateBody
        (OPEN_DELIMITER ELSEIF LPAREN conditional RPAREN CLOSE_DELIMITER templateBody)*
        (OPEN_DELIMITER ELSE CLOSE_DELIMITER templateBody)?
        OPEN_DELIMITER ENDIF CLOSE_DELIMITER
    ;

conditional
    :   andConditional (OR andConditional)*
    ;

andConditional
    :   notConditional (AND notConditional)*
    ;

notConditional
    :   NOT notConditional
    |   memberExpr
    ;

exprOptions
    :   options_+=option (COMMA options_+=option)*
    ;

option
    :   name=ID
        (   EQUALS value=exprNoComma
        )?
    ;

exprNoComma
    :   memberExpr
        (COLON mapTemplateRef)?
    ;

expr
    :   mapExpr
    ;

mapExpr
    :   memberExpr
        ((COMMA memberExpr)+ COLON mapTemplateRef)?
        (COLON mapTemplateRef (COMMA mapTemplateRef)*)*
    ;

mapTemplateRef
    :   ID LPAREN arguments RPAREN
    |   subtemplate
    |   LPAREN mapExpr RPAREN LPAREN argExprList? RPAREN
    ;

memberExpr
    :   includeExpr
        (   DOT ID
        |   DOT LPAREN mapExpr RPAREN
        )*
    ;

includeExpr
    :   super_=SUPER DOT templateName=ID LPAREN args=arguments RPAREN
    |   templateName=ID LPAREN args=arguments RPAREN
    |   at=AT super_=SUPER DOT templateName=ID LPAREN RPAREN
    |   regionName=REGION_ID LPAREN RPAREN
    |   primary
    ;

primary
    :   id=ID
    |   STRING
    |   TRUE
    |   FALSE
    |   subtemplate
    |   list
    |   LPAREN conditional RPAREN
    |   LPAREN expr RPAREN (LPAREN argExprList? RPAREN)?
    ;

arguments
    :   argExprList
    |   namedArg (COMMA namedArg)* (COMMA ELLIPSIS)?
    |   ELLIPSIS
    |   // empty
    ;

argExprList
    :   arg (COMMA arg)*
    ;

arg
    :   exprNoComma
    ;

namedArg
    :   name=ID EQUALS value=arg
    ;

list
    :   LBRACK RBRACK
    |   LBRACK elements+=listElement (COMMA elements+=listElement)* RBRACK
    ;

listElement
    :   exprNoComma?
    ;
