/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
lexer grammar GrammarLexer;

options {
    language = Java;
}

tokens {
    TOKEN_REF;
    RULE_REF;
}

@header {/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;
}

// +=====================+
// | Lexer specification |
// +=====================+

// --------
// Comments
//
// ANTLR comments can be multi or single line and we don't care
// which particularly. However we also accept Javadoc style comments
// of the form: /** ... */ and we do take care to distinguish those
// from ordinary multi-line comments
// Note how we guide the lexical PATH because we want to issue a decriptive
// error message in case of a standalone '/' character, which makes no
// sense in ANTLR source code. We alo trap unterminated multi-line comments
//
//fragment DOC_COMMENT : ;
//COMMENT
////@init {
////	// Record the start line and offsets as if we need to report an
////	// unterminated comment, then we want to show the start of the comment
////	// we think is broken, not the end, where people will have to try and work
////	// it out themselves.
////	//
////	int startLine = $line;
////	int offset    = getCharPositionInLine();
////}
//    : // Eat the first character only, then see if we have a comment
//      // or something silly.
//      //
//      '/'  // Comment introducer

//      (
//          // Single line comment, possibly with embedded src/line directives
//          // in a similar style to the C pre-processor, allowing generated
//          // code to refer the programmer back to the original source code
//          // in case of error.
//          //
//          '/'
//            (
//                ' $ANTLR' SRC
//              | (~NLCHARS)*
//            )

//         | // Multi-line comment, which may be a documentation comment
//           // if it starts /** (note that we protect against accidentaly
//           // recognizing a comment /**/ as a documentation comment
//           //
//           '*' (
//           			  { input.LA(2) != '/'}? '*' { $type = DOC_COMMENT; }
//           			| { true }?  // Required to cover all alts with predicates
//           	   )

//               // Should we support embedded multiline comments here?
//               //
//               (
//                   // Pick out end of multiline comment and exit the loop
//                   // if we find it.
//                   //
//               	   {    !(input.LA(1) == '*' && input.LA(2) == '/') }?

//              	      // Anything else other than the non-greedy match of
//              	      // the comment close sequence
//              	      //
//              	      .
//               )*
//            (
//                 // Look for the comment terminator, but if it is accidentally
//                 // unterminated, then we will hit EOF, which will trigger the
//                 // epsilon alt and hence we can issue an error message relative
//                 // to the start of the unterminated multi-line comment
//                 //
//                 '*/'

//               | // Unterminated comment!
//                 //
//                 {
//                   // ErrorManager.msg(Msg.UNTERMINATED_DOC_COMMENT, startLine, offset, $pos, startLine, offset, $pos, (Object)null);
//                 }
//             )

//         | // There was nothing that made sense following the opening '/' and so
//           // we issue an error regarding the malformed comment
//           //
//           {
//           	 // TODO: Insert error message relative to comment start
//             //
//           }
//       )
//       {
//         // Unless we had a documentation comment, then we do not wish to
//         // pass the comments in to the parser. If you are writing a formatter
//         // then you will want to preserve the comments off channel, but could
//         // just skip and save token space if not.
//         //
//         if ($type != DOC_COMMENT) {

//             $channel=2;  // Comments are on channel 2
//         }
//       }
//    ;

DOC_COMMENT
    :   '/**' BLOCK_COMMENT_BODY
    ;

BLOCK_COMMENT
    :   '/*' BLOCK_COMMENT_BODY -> channel(HIDDEN)
    ;

fragment
COMMENT
    :   BLOCK_COMMENT
    |   LINE_COMMENT
    ;

fragment
BLOCK_COMMENT_BODY
    :   .* '*/'
    ;

//LINE_COMMENT
//    :   '//' ~NLCHARS* {$channel = HIDDEN;}
//    ;

LINE_COMMENT
    :   '//' ~('\r'|'\n')* {$channel = HIDDEN;}
    ;

DOUBLE_QUOTE_STRING_LITERAL
	:	'"' ('\\' . | ~'"' )* '"'
	;

DOUBLE_ANGLE_STRING_LITERAL
	:	'<<' .* '>>'
	;

// --------------
// Argument specs
//
// Certain argument lists, such as those specifying call parameters
// to a rule invocation, or input parameters to a rule specification
// are contained within square brackets. In the lexer we consume them
// all at once and sort them out later in the grammar analysis.
//
BEGIN_ARG_ACTION
    :   '[' -> pushMode(ArgAction)
    ;

////@init
////{
////	StringBuffer theText = new StringBuffer();
////}
//	: '['
//         (
//             '\\'
//                 (
//                     ']'
//                       //{
//                       //    // We do not include the \ character itself when picking up an escaped ]
//                       //    //
//                       //    theText.append(']');
//                       //}
//                   | c=.
//                       //{
//                       //    // We DO include the \ character when finding any other escape
//                       //    //
//                       //    theText.append('\\');
//                       //    theText.append((char)$c);
//                       //}
//                 )

//           | as=ACTION_STRING_LITERAL
//                //{
//                //    // Append the embedded string literal test
//                //    //
//                //    theText.append($as.text);
//                //}

//           | ac=ACTION_CHAR_LITERAL
//                //{
//                //    // Append the embedded chracter literal text
//                //    //
//                //    theText.append($ac.text);
//                //}

//           | c2=~']'
//                //{
//                //    // Whatever else we found in the scan
//                //    //
//                //    theText.append((char)$c2);
//                //}
//	     )*

//       ']'
//       //{
//       //    // Set the token text to our gathered string
//       //    //
//       //    setText(theText.toString());
//       //}
//	;

// -------
// Actions
//
// Other than making sure to distinguish between { and } embedded
// within what we have assumed to be literals in the action code, the
// job of the lexer is merely to gather the code within the action
// (delimited by {}) and pass it to the parser as a single token.
// We know that this token will be asked for its text somewhere
// in the upcoming parse, so setting the text here to exclude
// the delimiting {} is no additional overhead.
//
//SEMPRED
//    :   NESTED_ACTION '?'
//    ;

BEGIN_ACTION
	:	'{' -> pushMode(Action) //NESTED_ACTION
	;

// ----------------
// Action structure
//
// Many language targets use {} as block delimiters and so we
// must recursively match {} delimited blocks to balance the
// braces. Additionally, we must make some assumptions about
// literal string representation in the target language. We assume
// that they are delimited by ' or " and so consume these
// in their own alts so as not to inadvertantly match {}.
// This rule calls itself on matching a {
//
//fragment
//NESTED_ACTION
////@init {

////	// Record the start line and offsets as if we need to report an
////	// unterminated block, then we want to show the start of the comment
////	// we think is broken, not the end, where people will have to try and work
////	// it out themselves.
////	//
////	int startLine = getLine();
////	int offset    = getCharPositionInLine();
////}

//    : // Action and other blocks start with opening {
//      //
//	  '{'
//      (
//	    // And now we can match one of a number of embedded
//	    // elements within the action until we find a
//	    // } that balances the opening {. If we do not find
//	    // the balanced } then we will hit EOF and can issue
//	    // an error message about the brace that we belive to
//	    // be mismatched. This won't be foolproof but we will
//	    // be able to at least report an error against the
//	    // opening brace that we feel is in error and this will
//	    // guide the user to the correction as best we can.
//	    //


//          // An embedded {} block
//	      //
//	      NESTED_ACTION

//        | // What appears to be a literal
//          //
//          ACTION_CHAR_LITERAL

//        | // We have assumed that the target language has C/Java
//          // type comments.
//          //
//          COMMENT

//        | // What appears to be a literal
//          //
//          ACTION_STRING_LITERAL

//	    | // What appears to be an escape sequence
//	      //
//	      ACTION_ESC

//	    | // Some other single character that is not
//	      // handled above
//	      //
//	      ~('\\'|'"'|'\''|'/'|'{'|'}')

//      )*

//	(
//	    // Correctly balanced closing brace
//	    //
//	    '}'

//	  | // Looks like have an imblanced {} block, report
//	    // with respect to the opening brace.
//	    //
//	    //{
//	    //    // TODO: Report imbalanced {}
//	    //    System.out.println("Block starting  at line " + startLine + " offset " + (offset+1) + " contains imbalanced {} or is missing a }");
//	    //}
//	)
//   ;


// Keywords
// --------
// keywords used to specify ANTLR v3 grammars. Keywords may not be used as
// labels for rules or in any other context where they woudl be ambiguous
// with the keyword vs some other identifier
// OPTIONS and TOKENS must also consume the opening brace that captures
// their option block, as this is teh easiest way to parse it separate
// to an ACTION block, despite it usingthe same {} delimiters.
//
OPTIONS      : 'options' WSNLCHARS* '{'  ;
TOKENS       : 'tokens'  WSNLCHARS* '{'  ;

IMPORT       : 'import'               ;
FRAGMENT     : 'fragment'             ;
LEXER        : 'lexer'                ;
PARSER       : 'parser'               ;
GRAMMAR      : 'grammar'              ;
PROTECTED    : 'protected'            ;
PUBLIC       : 'public'               ;
PRIVATE      : 'private'              ;
RETURNS      : 'returns'              ;
LOCALS       : 'locals'               ;
THROWS       : 'throws'               ;
CATCH        : 'catch'                ;
FINALLY      : 'finally'              ;
TEMPLATE     : 'template'             ;
MODE         : 'mode'                 ;

// -----------
// Punctuation
//
// Character sequences used as separators, delimters, operators, etc
//
COLON        : ':'                    ;
COLONCOLON   : '::'                   ;
COMMA        : ','                    ;
SEMI         : ';'                    ;
LPAREN       : '('                    ;
RPAREN       : ')'                    ;
RARROW       : '->'                   ;
LT           : '<'                    ;
GT           : '>'                    ;
ASSIGN       : '='                    ;
QUESTION     : '?'                    ;
STAR         : '*'                    ;
PLUS         : '+'                    ;
PLUS_ASSIGN  : '+='                   ;
OR           : '|'                    ;
DOLLAR       : '$'                    ;
DOT		     : '.'                    ; // can be WILDCARD or DOT in qid or imported rule ref
RANGE        : '..'                   ;
ETC          : '...'                  ;
AT           : '@'                    ;
POUND        : '#'                    ;
NOT          : '~'                    ;
RBRACE       : '}'                    ;

/*
// ---------------
// Token reference
//
// The names of all tokens must start with an upper case letter and so
// the lexer can distinguish them directly.
//
TOKEN_REF
    : ('A'..'Z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '_')*
    ;

// --------------
// Rule reference
//
// The names of all rules must start with a lower case letter
// so the lexer can distibguish them directly. The parser takes
// care of the case such as id=rulename
//
RULE_REF
    : ('a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '_')*
    ;
    */

/** Allow unicode rule/token names */
ID			:	NameStartChar NameChar*
				{
				if ( Character.isUpperCase(getInputStream().getText(Interval.of(_tokenStartCharIndex, _tokenStartCharIndex)).charAt(0)) ) $type = TOKEN_REF;
				else $type = RULE_REF;
				};

fragment
NameChar    :   NameStartChar
            |   '0'..'9'
            |   '_'
            |   '\u00B7'
            |   '\u0300'..'\u036F'
            |   '\u203F'..'\u2040'
            ;

fragment
NameStartChar
            :   'A'..'Z' | 'a'..'z'
            |   '\u00C0'..'\u00D6'
            |   '\u00D8'..'\u00F6'
            |   '\u00F8'..'\u02FF'
            |   '\u0370'..'\u037D'
            |   '\u037F'..'\u1FFF'
            |   '\u200C'..'\u200D'
            |   '\u2070'..'\u218F'
            |   '\u2C00'..'\u2FEF'
            |   '\u3001'..'\uD7FF'
            |   '\uF900'..'\uFDCF'
            |   '\uFDF0'..'\uFFFD'
            ; // ignores | ['\u10000-'\uEFFFF] ;

// ----------------------------
// Literals embedded in actions
//
// Note that we have made the assumption that the language used within
// actions uses the fairly standard " and ' delimiters for literals and
// that within these literals, characters are escaped using the \ character.
// There are some languages which do not conform to this in all cases, such
// as by using /string/ and so on. We will have to deal with such cases if
// if they come up in targets.
//

// Within actions, or other structures that are not part of the ANTLR
// syntax, we may encounter literal characters. Within these, we do
// not want to inadvertantly match things like '}' and so we eat them
// specifically. While this rule is called CHAR it allows for the fact that
// some languages may use/allow ' as the string delimiter.
//
fragment
ACTION_CHAR_LITERAL
	:	'\'' (ACTION_ESC | ~'\'' )* '\''
	;

// Within actions, or other structures that are not part of the ANTLR
// syntax, we may encounter literal strings. Within these, we do
// not want to inadvertantly match things like '}' and so we eat them
// specifically.
//
fragment
ACTION_STRING_LITERAL
	:	'"' (ACTION_ESC | ~'"')* '"'
	;

// Within literal strings and characters that are not part of the ANTLR
// syntax, we must allow for escaped character sequences so that we do not
// inadvertantly recognize the end of a string or character when the terminating
// delimiter has been esacped.
//
fragment
ACTION_ESC
	: '\\' .
	;

// -------
// Integer
//
// Obviously (I hope) match an aribtrary long sequence of digits.
//
INT : ('0'..'9')+
    ;

// -----------
// Source spec
//
// A fragment rule for picking up information about an origrinating
// file from which the grammar we are parsing has been generated. This allows
// ANTLR to report errors against the originating file and not the generated
// file.
//

//fragment
//SRC : 'src' WSCHARS+ file=ACTION_STRING_LITERAL WSCHARS+ line=INT
//      {
//         // TODO: Add target specific code to change the source file name and current line number
//         //
//      }
//    ;

// --------------
// Literal string
//
// ANTLR makes no disticintion between a single character literal and a
// multi-character string. All literals are single quote delimited and
// may contain unicode escape sequences of the form \uxxxx, where x
// is a valid hexadecimal number (as per Java basically).
STRING_LITERAL
    :  '\'' ( ( ESC_SEQ | ~('\\'|'\'') ) )* '\''
    ;

// A valid hex digit specification
//
fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

// Any kind of escaped character that we can embed within ANTLR
// literal strings.
//
fragment
ESC_SEQ
    : '\\'
        (
              // The standard escaped character set such as tab, newline,
              // etc.
              //
    		  ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')

    	    | // A Java style Unicode escape sequence
    	      //
    	      UNICODE_ESC

    	    //| // An illegal escape seqeunce
    	    //  //
    	    //  {
    	    //  	// TODO: Issue error message
    	    //  	//
    	    //  }
        )
    ;

fragment
UNICODE_ESC
    :   'u' (HEX_DIGIT (HEX_DIGIT (HEX_DIGIT HEX_DIGIT?)?)?)?
    ;

// ----------
// Whitespace
//
// Characters and character constructs that are of no import
// to the parser and are used to make the grammar easier to read
// for humans.
//
WS
    : (
    	  ' '
        | '\t'
        | '\r'
        | '\n'
        | '\f'
      )+
      -> channel(HIDDEN)
    ;

// A fragment rule for use in recognizing end of line in
// rules like COMMENT.
//
fragment
NLCHARS
    : '\n' | '\r'
    ;

// A fragment rule for recognizing traditional whitespace
// characters within lexer rules.
//
fragment
WSCHARS
    : ' ' | '\t' | '\f'
    ;

// A fragment rule for recognizing both traditional whitespace and
// end of line markers, when we don't care to distinguish but don't
// want any action code going on.
//
fragment
WSNLCHARS
    : ' ' | '\t' | '\f' | '\n' | '\r'
    ;

// -----------------
// Illegal Character
//
// This is an illegal character trap which is always the last rule in the
// lexer specification. It matches a single character of any value and being
// the last rule in the file will match when no other rule knows what to do
// about the character. It is reported as an error but is not passed on to the
// parser. This means that the parser to deal with the gramamr file anyway
// but we will not try to analyse or code generate from a file with lexical
// errors.
//
ERRCHAR
    : .
      //{
      //   // TODO: Issue error message
      //   //
      //   skip();
      //}
    ;

mode ArgAction;

    ARG_ACTION_LT       : '<' ;
    ARG_ACTION_GT       : '>' ;
    ARG_ACTION_LPAREN   : '(' ;
    ARG_ACTION_RPAREN   : ')' ;
    ARG_ACTION_EQUALS   : '=' ;
    ARG_ACTION_COMMA    : ',' ;

    ARG_ACTION_ESCAPE
        :   '\\' .
        ;

    ARG_ACTION_WORD
        :   ('$' | 'a'..'z' | 'A'..'Z' | '0'..'9' | '_')
            ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*
        ;

    ARG_ACTION_ELEMENT
        :   ACTION_STRING_LITERAL
        |   ACTION_CHAR_LITERAL
        ;

    ARG_ACTION_TEXT
        :   ~(  '\'' | '"'
            |   ']'
            |   '\\'
            |   '=' | ',' | '<' | '>' | '(' | ')'
            |   '$' | 'a'..'z' | 'A'..'Z' | '0'..'9' | '_'
            |   ' ' | '\t' | '\r' | '\n'
            )+
        ;

    ARG_ACTION_WS
        :   (' ' | '\t')+
        ;

    ARG_ACTION_NEWLINE
        :   '\r' '\n'?
        |   '\n'
        ;

    END_ARG_ACTION
        :   ']' -> popMode
        ;

mode Action;

    NESTED_ACTION
        :   '{' -> type(BEGIN_ACTION), pushMode(Action)
        ;

    ACTION_DOT      : '.' ;
    ACTION_LT       : '<' ;
    ACTION_GT       : '>' ;
    ACTION_LPAREN   : '(' ;
    ACTION_RPAREN   : ')' ;
    ACTION_LBRACK   : '[' ;
    ACTION_RBRACK   : ']' ;
    ACTION_EQUALS   : '=' ;
    ACTION_COMMA    : ',' ;
    ACTION_COLON2   : '::' ;
    ACTION_COLON    : ':' ;
    ACTION_MINUS    : '-' ;

    ACTION_ESCAPE
        :   '\\' .
        ;

    ACTION_WORD
        :   ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')+
        ;

    ACTION_REFERENCE
        :   '$' ACTION_WORD?
        ;

    ACTION_COMMENT
        :   COMMENT
        ;

    ACTION_LITERAL
        :   ACTION_STRING_LITERAL
        |   ACTION_CHAR_LITERAL
        ;

    ACTION_TEXT
        :   (   '/' ~('*' | '/')
            |   ~(  '\'' | '"'                                          // strings
                |   '{' | '}'                                           // nested actions
                |   '\\'                                                // escapes
                |   '/'                                                 // potential comments
                |   '.' | '=' | ',' | '<' | '>' | '(' | ')' | '[' | ']' | ':' | '-' // delimiters
                |   '$' | 'a'..'z' | 'A'..'Z' | '0'..'9' | '_'          // words
                |   ' ' | '\t' | '\r' | '\n'
                )
            )+
        ;

    ACTION_WS
        :   (' ' | '\t')+
        ;

    ACTION_NEWLINE
        :   '\r' '\n'?
        |   '\n'
        ;

    END_ACTION
        :   '}' -> popMode
        ;
