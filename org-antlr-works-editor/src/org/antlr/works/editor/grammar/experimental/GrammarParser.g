/*
 [The "BSD license"]
 Copyright (c) 2010 Jim Idle, Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/** The definitive ANTLR v3 grammar to parse ANTLR v4 grammars.
 *  The grammar builds ASTs that are sniffed by subsequent stages.
 */
parser grammar GrammarParser;

options {
	// Target language is Java, which is the default but being specific
	// here as this grammar is also meant as a good example grammar for
	// for users.
	//language      = Java;

	// The output of this grammar is going to be an AST upon which
	// we run a semantic checking phase, then the rest of the analysis
	// including final code generation.
	//output        = AST;

	// The vocabulary (tokens and their int token types) we are using
	// for the parser. This is generated by the lexer. The vocab will be extended
	// to include the imaginary tokens below.
	tokenVocab    = GrammarLexer;

	//ASTLabelType  = GrammarAST;
}

// Imaginary Tokens
//
// Imaginary tokens do not exist as far as the lexer is concerned, and it cannot
// generate them. However we sometimes need additional 'tokens' to use as root
// nodes for the AST we are generating. The tokens section is where we
// specify any such tokens
tokens {
    LEXER;
    RULE;
	PREC_RULE; // flip to this if we find that it's left-recursive
    RULES;
    RULEMODIFIERS;
    RULEACTIONS;
    BLOCK;
    REWRITE_BLOCK;
    REWRITE_SEQ;
    OPTIONAL;
    CLOSURE;
    POSITIVE_CLOSURE;
    RANGE;
    SET;
    CHAR_RANGE;
    EPSILON;
    ALT;
    ALTLIST;
    ID;
    ARG;
    ARGLIST;
    RET;
	COMBINED;
    INITACTION;
    LABEL;                // $x used in rewrite rules
    TEMPLATE;
    WILDCARD;
    // A generic node indicating a list of something when we don't
    // really need to distinguish what we have a list of as the AST
    // will 'kinow' by context.
    //
    LIST;
    ELEMENT_OPTIONS;      // TOKEN<options>
    ST_RESULT;			  // distinguish between ST and tree rewrites
    RESULT;
    ALT_REWRITE;		  // indicate ALT is rewritten
    
    DOWN_TOKEN;			  // AST node representing DOWN node in tree parser code gen
    UP_TOKEN;
}

// Include the copyright in this source and also the generated source
//
@header {
/*
 [The "BSD licence"]
 Copyright (c) 2005-2009 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.antlr.works.editor.grammar.experimental;

//import org.antlr.v4.tool.*;
//import org.antlr.v4.tool.ast.*;
}

@members {
//Stack paraphrases = new Stack();
/** Affects tree construction; no SET collapsing if AST (ID|INT) would hide them from rewrite.
 *  Could use for just AST ops, but we can't see -> until after building sets.
boolean buildAST;
 */
}

// The main entry point for parsing a V3 grammar from top to toe. This is
// the method call from whence to obtain the AST for the parse.
//
grammarSpec
//@after {
//GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.OPTIONS);
//if ( options!=null ) {
//	Grammar.setNodeOptions($tree, options);
//}
//}
    :
      // The grammar itself can have a documenation comment, which is the
      // first terminal in the file.
      //
      DOC_COMMENT?

      // Next we should see the type and name of the grammar file that
      // we are about to parse.
      //
      grammarType id SEMI

      // There now follows zero or more declaration sections that should
      // be given to us before the rules are declared
      //
// A number of things can be declared/stated before the grammar rules
// 'proper' are parsed. These include grammar imports (delegate), grammar
// options, imaginary token declarations, global scope declarations,
// and actions such as @header. In this rule we allow any number of
// these constructs in any order so that the grammar author is not
// constrained by some arbitrary order of declarations that nobody
// can remember. In the next phase of the parse, we verify that these
// constructs are valid, not repeated and so on.
      /*sync*/ ( prequelConstruct /*sync*/ )*

	  // We should now see at least one ANTLR EBNF style rule
	  // declaration. If the rules are missing we will let the
	  // semantic verification phase tell the user about it.
	  //
	  rules

	  mode_*

      // And we force ANTLR to process everything it finds in the input
      // stream by specifying hte need to match End Of File before the
      // parse is complete.
      //
      EOF

      // Having parsed everything in the file and accumulated the relevant
      // subtrees, we can now rewrite everything into the main AST form
      // that our tree walkers are expecting.
      //

      //-> ^(grammarType       // The grammar type is our root AST node
      //       id              // We need to identify the grammar of course
      //       DOC_COMMENT?    // We may or may not have a global documentation comment for the file
      //       prequelConstruct* // The set of declarations we accumulated
      //       rules           // And of course, we need the set of rules we discovered
      //       mode*
      //   )
	;

grammarType
//@after {
//	if ( $t!=null ) ((GrammarRootAST)$tree).grammarType = $t.type;
//	else ((GrammarRootAST)$tree).grammarType=COMBINED;
//}
    :	(	t=LEXER g=GRAMMAR  //-> GRAMMAR<GrammarRootAST>[$g, "LEXER_GRAMMAR"]
		| // A standalone parser specification
		  	t=PARSER g=GRAMMAR //-> GRAMMAR<GrammarRootAST>[$g, "PARSER_GRAMMAR"]

		| // A standalone tree parser specification
		  	t=TREE g=GRAMMAR   //-> GRAMMAR<GrammarRootAST>[$g, "TREE_GRAMMAR"]

		// A combined lexer and parser specification
		| 	g=GRAMMAR          //-> GRAMMAR<GrammarRootAST>[$g, "COMBINED_GRAMMAR"]
		)
    ;

// This is the list of all constructs that can be declared before
// the set of rules that compose the grammar, and is invoked 0..n
// times by the grammarPrequel rule.
prequelConstruct
	: // A list of options that affect analysis and/or code generation
	  optionsSpec

    | // A list of grammars to which this grammar will delegate certain
      // parts of the parsing sequence - a set of imported grammars
      delegateGrammars

    | // The declaration of any token types we need that are not already
      // specified by a preceeding grammar, such as when a parser declares
      // imaginary tokens with which to construct the AST, or a rewriting
      // tree parser adds further imaginary tokens to ones defined in a prior
      // {tree} parser.
      tokensSpec

    | // A declaration of language target implemented constructs. All such
      // action sections start with '@' and are given to the language target's
      // StringTemplate group. For instance @parser::header and @lexer::header
      // are gathered here.
      action
    ;

// A list of options that affect analysis and/or code generation
optionsSpec
	:	OPTIONS (option SEMI)* RBRACE //-> ^(OPTIONS[$OPTIONS, "OPTIONS"] option+)
    ;

option
    :   id ASSIGN/*^*/ optionValue
/*
    	{
    	if ( $id.text.equals("output") ) {
    		if ( $optionValue.text.equals("AST") ) buildAST = true;
    	}
    	}
    	*/
    ;

// ------------
// Option Value
//
// The actual value of an option - Doh!
//
optionValue
    : // If the option value is a single word that conforms to the
      // lexical rules of token or rule names, then the user may skip quotes
      // and so on. Many option values meet this description
      //
      qid

    | // The value is a long string
      //
      STRING_LITERAL<TerminalAST>

    | // The value was an integer number
      //
      INT

    | // Asterisk, used for things like k=*
      //
      STAR
    ;

// A list of grammars to which this grammar will delegate certain
// parts of the parsing sequence - a set of imported grammars
delegateGrammars
	: IMPORT delegateGrammar (COMMA delegateGrammar)* SEMI //-> ^(IMPORT delegateGrammar+)
	;

// A possibly named grammar file that should be imported to this gramamr
// and delgated to for the rules it specifies
delegateGrammar
    :   id ASSIGN/*^*/ id
    |   id
    ;

/** The declaration of any token types we need that are not already
 *  specified by a preceeding grammar, such as when a parser declares
 *  imaginary tokens with which to construct the AST, or a rewriting
 *  tree parser adds further imaginary tokens to ones defined in a prior
 *  {tree} parser.
 */
tokensSpec
	: TOKENS tokenSpec+ RBRACE //-> ^(TOKENS tokenSpec+)
	;

tokenSpec
	:	id
		(	ASSIGN STRING_LITERAL	//-> ^(ASSIGN id STRING_LITERAL<TerminalAST>)
		|							//-> id
		)
		SEMI
	|	RULE_REF // INVALID! (an error alt)
	;

actionBlock
    :   BEGIN_ACTION
            (   actionBlock
            |   actionExpression
            |   actionScopeExpression
            |   actionIgnored
            |   ACTION_COMMENT
            |   ACTION_LITERAL
            |   ACTION_TEXT
            |   ACTION_LT
            |   ACTION_GT
            |   ACTION_LPAREN
            |   ACTION_RPAREN
            |   ACTION_LBRACK
            |   ACTION_RBRACK
            |   ACTION_EQUALS
            |   ACTION_COMMA
            |   ACTION_ESCAPE
            |   ACTION_WORD
            |   ACTION_REFERENCE
            |   ACTION_COLON
            |   ACTION_COLON2
            |   ACTION_MINUS
            |   ACTION_DOT
            )*
        END_ACTION
    ;

actionExpression
    :   ACTION_REFERENCE actionIgnored* ACTION_DOT actionIgnored* ACTION_WORD
    ;

actionScopeExpression
    :   ACTION_REFERENCE actionIgnored* (ACTION_LBRACK actionIgnored* (ACTION_MINUS actionIgnored*)? ACTION_WORD actionIgnored* ACTION_RBRACK actionIgnored*)? ACTION_COLON2 actionIgnored* ACTION_WORD
    ;

actionIgnored
    :   ACTION_WS
    |   ACTION_NEWLINE
    |   ACTION_COMMENT
    ;

argActionBlock
    :   BEGIN_ARG_ACTION
            (   ARG_ACTION_ELEMENT
            |   ARG_ACTION_TEXT
            |   ARG_ACTION_LT
            |   ARG_ACTION_GT
            |   ARG_ACTION_LPAREN
            |   ARG_ACTION_RPAREN
            |   ARG_ACTION_EQUALS
            |   ARG_ACTION_COMMA
            |   ARG_ACTION_ESCAPE
            |   ARG_ACTION_WORD
            )*
        END_ARG_ACTION
    ;

// A declaration of a language target specifc section,
// such as @header, @includes and so on. We do not verify these
// sections, they are just passed on to the language target.
/** Match stuff like @parser::members {int i;} */
action
	:	AT (actionScopeName COLONCOLON)? id actionBlock //-> ^(AT actionScopeName? id ACTION<ActionAST>)
	;

/** Sometimes the scope names will collide with keywords; allow them as
 *  ids for action scopes.
 */
actionScopeName
	:	id
	|	LEXER	//-> ID[$LEXER]
    |   PARSER	//-> ID[$PARSER]
	;

mode_:	MODE id SEMI /*sync*/ (rule /*sync*/)+  /*-> ^(MODE id rule+)*/ ;

rules
    :	/*sync*/ (rule /*sync*/)*
      // Rewrite with an enclosing node as this is good for counting
      // the number of rules and an easy marker for the walker to detect
      // that there are no rules.
      //->^(RULES rule*)
    ;

//sync
//@init {
//	BitSet followSet = computeErrorRecoverySet();
//	if ( input.LA(1)!=Token.EOF && !followSet.member(input.LA(1)) ) {
//		reportError(new NoViableAltException("",0,0,input));
//       	beginResync();
//       	consumeUntil(input, followSet);
//       	endResync();
//	}
//}
//	:
//	;

// The specification of an EBNF rule in ANTLR style, with all the
// rule level parameters, declarations, actions, rewrite specs and so
// on.
//
// Note that here we allow any number of rule declaration sections (such
// as scope, returns, etc) in any order and we let the upcoming semantic
// verification of the AST determine if things are repeated or if a
// particular functional element is not valid in the context of the
// grammar type, such as using returns in lexer rules and so on.
rule
//@init { paraphrases.push("matching a rule"); }
//@after {
//	paraphrases.pop();
//	GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.OPTIONS);
//	if ( options!=null ) {
//		Grammar.setNodeOptions($tree, options);
//	}
//}
    : // A rule may start with an optional documentation comment
      DOC_COMMENT?

      // Following the documentation, we can declare a rule to be
      // public, private and so on. This is only valid for some
      // language targets of course but the target will ignore these
      // modifiers if they make no sense in that language.
      ruleModifiers?

	  // Next comes the rule name. Here we do not distinguish between
	  // parser or lexer rules, the semantic verification phase will
	  // reject any rules that make no sense, such as lexer rules in
	  // a pure parser or tree parser.
	  name=id

	  // Immediately following the rulename, there may be a specification
	  // of input parameters for the rule. We do not do anything with the
	  // parameters here except gather them for future phases such as
	  // semantic verifcation, type assignment etc. We require that
	  // the input parameters are the next syntactically significant element
	  // following the rule id.
	  argActionBlock?

	  ruleReturns?

	  throwsSpec?

	  locals_?

	  // Now, before the rule specification itself, which is introduced
	  // with a COLON, we may have zero or more configuration sections.
	  // As usual we just accept anything that is syntactically valid for
	  // one form of the rule or another and let the semantic verification
	  // phase throw out anything that is invalid.
// At the rule level, a programmer may specify a number of sections, such
// as scope declarations, rule return elements, @ sections (which may be
// language target specific) and so on. We allow any number of these in any
// order here and as usual rely onthe semantic verification phase to reject
// anything invalid using its addinotal context information. Here we are
// context free and just accept anything that is a syntactically correct
// construct.
//
      rulePrequels

      COLON

      // The rule is, at the top level, just a list of alts, with
      // finer grained structure defined within the alts.
      ruleBlock

      SEMI

      exceptionGroup

      //-> ^( RULE<RuleAST> id DOC_COMMENT? ruleModifiers? ARG_ACTION<ActionAST>?
      //		ruleReturns? throwsSpec? locals? rulePrequels? ruleBlock exceptionGroup*
      //	  )
    ;

// Many language targets support exceptions and the rule will
// generally be able to throw the language target equivalent
// of a recognition exception. The grammar programmar can
// specify a list of exceptions to catch or a generic catch all
// and the target language code generation template is
// responsible for generating code that makes sense.
exceptionGroup
    : exceptionHandler* finallyClause?
    ;

// Specifies a handler for a particular type of exception
// thrown by a rule
exceptionHandler
	: CATCH argActionBlock actionBlock //-> ^(CATCH ARG_ACTION<ActionAST> ACTION<ActionAST>)
	;

// Specifies a block of code to run after the rule and any
// expcetion blocks have exceuted.
finallyClause
	: FINALLY actionBlock //-> ^(FINALLY ACTION<ActionAST>)
	;

rulePrequels
//@init { paraphrases.push("matching rule preamble"); }
//@after { paraphrases.pop(); }
	:	/*sync*/ (rulePrequel /*sync*/)* //-> rulePrequel*
	;

// An individual rule level configuration as referenced by the ruleActions
// rule above.
//
rulePrequel
    : optionsSpec
    | ruleAction
    ;

// A rule can return elements that it constructs as it executes.
// The return values are specified in a 'returns' prequel element,
// which contains COMMA separated declarations, where the declaration
// is target language specific. Here we see the returns declaration
// as a single lexical action element, to be processed later.
//
ruleReturns
	: RETURNS/*^*/ argActionBlock/*<ActionAST>*/
	;

// --------------
// Exception spec
//
// Some target languages, such as Java and C# support exceptions
// and they are specified as a prequel element for each rule that
// wishes to throw its own exception type. Note that the name of the
// exception is just a single word, so the header section of the grammar
// must specify the correct import statements (or language equivalent).
// Target languages that do not support exceptions just safely ignore
// them.
//
throwsSpec
    : THROWS qid (COMMA qid)* //-> ^(THROWS qid+)
    ;

// locals [Cat x, float g]
locals_ : LOCALS/*^*/ argActionBlock/*<ActionAST>*/ ;

// @ Sections are generally target language specific things
// such as local variable declarations, code to run before the
// rule starts and so on. Fir instance most targets support the
// @init {} section where declarations and code can be placed
// to run before the rule is entered. The C target also has
// an @declarations {} section, where local variables are declared
// in order that the generated code is C89 copmliant.
//
/** Match stuff like @init {int i;} */
ruleAction
	:	AT id actionBlock //-> ^(AT id ACTION<ActionAST>)
	;

// A set of access modifiers that may be applied to rule declarations
// and which may or may not mean something to the target language.
// Note that the parser allows any number of these in any order and the
// semantic pass will throw out invalid combinations.
//
ruleModifiers
    : ruleModifier+ //-> ^(RULEMODIFIERS ruleModifier+)
    ;

// An individual access modifier for a rule. The 'fragment' modifier
// is an internal indication for lexer rules that they do not match
// from the input but are like subroutines for other lexer rules to
// reuse for certain lexical patterns. The other modifiers are passed
// to the code generation templates and may be ignored by the template
// if they are of no use in that language.
ruleModifier
    : PUBLIC
    | PRIVATE
    | PROTECTED
    | FRAGMENT
    ;

// A set of alts, rewritten as a BLOCK for generic processing
// in tree walkers. Used by the rule 'rule' so that the list of
// alts for a rule appears as a BLOCK containing the alts and
// can be processed by the generic BLOCK rule. Note that we
// use a separate rule so that the BLOCK node has start and stop
// boundaries set correctly by rule post processing of rewrites.
ruleBlock
//@init {Token colon = input.LT(-1);}
    :	ruleAltList //-> ^(BLOCK<BlockAST>[colon,"BLOCK"] ruleAltList)
    ;
  //  catch [ResyncToEndOfRuleBlock e] {
  //  	// just resyncing; ignore error
		//retval.tree = (GrammarAST)adaptor.errorNode(input, retval.start, input.LT(-1), null);
  //  }

ruleAltList
	:	labeledAlt (OR labeledAlt)* //-> labeledAlt+
	;
	
labeledAlt
	:	alternative (POUND id /*{((AltAST)$alternative.tree).altLabel=$id.tree;}*/)?
		//-> alternative
	;
	
altList
    :	alternative (OR alternative)* //-> alternative+
    ;

// An individual alt with an optional rewrite clause for the
// elements of the alt.
alternative
//@init { paraphrases.push("matching alternative"); }
//@after { paraphrases.pop(); }
    :	elements
    	(	rewrite //-> ^(ALT_REWRITE elements rewrite)
    	|			//-> elements
    	)
    |	rewrite		//-> ^(ALT_REWRITE ^(ALT<AltAST> EPSILON) rewrite) // empty alt with rewrite
    |				//-> ^(ALT<AltAST> EPSILON) // empty alt
    ;

elements
    : e+=element+ //-> ^(ALT<AltAST> $e+)
    ;

element
//@init {
//	paraphrases.push("looking for rule element");
//	int m = input.mark();
//}
//@after { paraphrases.pop(); }
	:	labeledElement
		(	ebnfSuffix	//-> ^( ebnfSuffix ^(BLOCK<BlockAST>[$labeledElement.start,"BLOCK"] ^(ALT<AltAST> labeledElement ) ))
		|				//-> labeledElement
		)
	|	atom
		(	ebnfSuffix	//-> ^( ebnfSuffix ^(BLOCK<BlockAST>[$atom.start,"BLOCK"] ^(ALT<AltAST> atom) ) )
		|				//-> atom
		)
	|	ebnf
	|   actionBlock QUESTION? /*<ActionAST>*/ // SEMPRED is actionBlock followed by QUESTION
	//|   SEMPRED //-> SEMPRED<PredAST>
	|   treeSpec
		(	ebnfSuffix	//-> ^( ebnfSuffix ^(BLOCK<BlockAST>[$treeSpec.start,"BLOCK"] ^(ALT<AltAST> treeSpec ) ) )
		|				//-> treeSpec
		)
	;
 //   catch [RecognitionException re] {
 //   	retval.tree = (GrammarAST)adaptor.errorNode(input, retval.start, input.LT(-1), re);
 //   	int ttype = input.get(input.range()).getType();
	//    // look for anything that really belongs at the start of the rule minus the initial ID
 //   	if ( ttype==COLON || ttype==RETURNS || ttype==CATCH || ttype==FINALLY || ttype==AT ) {
	//		RecognitionException missingSemi =
	//			new v4ParserException("unterminated rule (missing ';') detected at '"+
	//								  input.LT(1).getText()+" "+input.LT(2).getText()+"'", input);
	//		reportError(missingSemi);
	//		if ( ttype==CATCH || ttype==FINALLY ) {
	//			input.seek(input.range()); // ignore what's before rule trailer stuff
	//		}
	//		if ( ttype==RETURNS || ttype==AT ) { // scan back looking for ID of rule header
	//			int p = input.index();
	//			Token t = input.get(p);
	//			while ( t.getType()!=RULE_REF && t.getType()!=TOKEN_REF ) {
	//				p--;
	//				t = input.get(p);
	//			}
	//			input.seek(p);
	//		}
	//		throw new ResyncToEndOfRuleBlock(); // make sure it goes back to rule block level to recover
	//	}
 //       reportError(re);
 //       recover(input,re);
	//}

labeledElement
	:	id (ass=ASSIGN|ass=PLUS_ASSIGN)
		(	atom						//-> ^($ass id atom)
		|	block (op=ROOT|op=BANG)?	//-> {$op!=null}? ^($ass id ^($op block))
										//->				^($ass id block)
/*
		|	{buildAST}? blockSet
			{
			RecognitionException e =
				new v4ParserException("can't  '"+
									  input.LT(1).getText()+" "+input.LT(2).getText()+"'", input);
			reportError(missingSemi);
			}
*/
		)
	;

// Tree specifying alt
// Tree grammars need to have alts that describe a tree structure they
// will walk of course. Alts for trees therefore start with ^( XXX, which
// says we will see a root node of XXX then DOWN etc
treeSpec
//@after {
//	GrammarAST down = new DownAST(DOWN_TOKEN, $begin);
//	GrammarAST up = new UpAST(UP_TOKEN, $begin);
//	int i = 1; // skip root element
//	GrammarAST p = (GrammarAST)$tree.getChild(i);
//	while ( p.getType()==ACTION || p.getType()==SEMPRED ) {
//		i++;
//		p = (GrammarAST)$tree.getChild(i);
//	}
//	$tree.insertChild(i, down); // ADD DOWN
//	i = $tree.getChildCount()-1;
//	p = (GrammarAST)$tree.getChild(i);
//	while ( p.getType()==ACTION || p.getType()==SEMPRED ) {
//		i--;
//		p = (GrammarAST)$tree.getChild(i);
//	}
//	if ( i+1 >= $tree.getChildCount() ) $tree.addChild(up);
//   	else $tree.insertChild(i+1, up); // ADD UP
//}
    : begin=TREE_BEGIN
         // Only a subset of elements are allowed to be a root node. However
         // we allow any element to appear here and reject silly ones later
         // when we walk the AST.
         root=element
         // After the tree root we get the usual suspects,
         // all members of the element set.
         (kids+=element)+
      RPAREN
      //-> ^( TREE_BEGIN<TreePatternAST> $root $kids+ )
    ;

// A block of gramamr structure optionally followed by standard EBNF
// notation, or ANTLR specific notation. I.E. ? + ^ and so on
ebnf
    : block
      // And now we see if we have any of the optional suffixs and rewrite
      // the AST for this rule accordingly
      (	blockSuffix	//-> ^(blockSuffix block)
      |				//-> block
      )
    ;

// The standard EBNF suffixes with additional components that make
// sense only to ANTLR, in the context of a grammar block.
blockSuffix
    : ebnfSuffix // Standard EBNF

	  // ANTLR Specific Suffixes
    | ROOT
//    | IMPLIES   // We will change this to syn/sem pred in the next phase
    | BANG
    ;

ebnfSuffix
	:	QUESTION	//-> OPTIONAL<OptionalBlockAST>[$start]
  	|	STAR 		//-> CLOSURE<StarBlockAST>[$start]
   	|	PLUS	 	//-> POSITIVE_CLOSURE<PlusBlockAST>[$start]
	;

atom
//@after {
//	if ( $tree.getType()==DOT ) {
//		GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.OPTIONS);
//		if ( options!=null ) {
//			Grammar.setNodeOptions($tree, options);
//		}
//	}
//}
	:	// Qualified reference delegate.rule. This must be
	    // lexically contiguous (no spaces either side of the DOT)
	    // otherwise it is two references with a wildcard in between
	    // and not a qualified reference.
	    /*
	    {
	    	input.LT(1).getCharPositionInLine()+input.LT(1).getText().length()==
	        input.LT(2).getCharPositionInLine() &&
	        input.LT(2).getCharPositionInLine()+1==input.LT(3).getCharPositionInLine()
	    }?
	    id DOT ruleref -> ^(DOT id ruleref)
	    
    |
    	*/
        range (ROOT/*^*/ | BANG/*^*/)? // Range x..y - only valid in lexers
	|	terminal (ROOT/*^*/ | BANG/*^*/)?
    |   ruleref
    |	notSet   (ROOT/*^*/|BANG/*^*/)?
	|   // Wildcard '.' means any character in a lexer, any
		// token in parser and any node or subtree in a tree parser
		// Because the terminal rule is allowed to be the node
		// specification for the start of a tree rule, we must
		// later check that wildcard was not used for that.
	    DOT elementOptions?	(astop=ROOT|astop=BANG)?
	    //-> {astop!=null}?	^($astop ^(WILDCARD<TerminalAST>[$DOT] elementOptions?))
	    //-> 					^(WILDCARD<TerminalAST>[$DOT] elementOptions?)
    ;
    //catch [RecognitionException re] { throw re; } // pass upwards to element

// --------------------
// Inverted element set
//
// A set of characters (in a lexer) or terminal tokens, if a parser,
// that are then used to create the inverse set of them.
notSet
    : NOT setElement	//-> ^(NOT<NotAST>[$NOT] ^(SET<SetAST>[$setElement.start,"SET"] setElement))
    | NOT blockSet		//-> ^(NOT<NotAST>[$NOT] blockSet)
    ;

blockSet
//@init {
//	Token t;
//	boolean ebnf = false;
//}
    :	LPAREN setElement (OR setElement)* RPAREN
/*		{
		t = input.LT(1);
		ebnf = t!=null && (t.getType()==QUESTION || t.getType()==STAR || t.getType()==PLUS);
	    }
	    */
		//-> ^(BLOCK<BlockAST>[$LPAREN,"BLOCK"] ^(ALT setElement)+ )
/*
		-> {ebnf}?	^(BLOCK<BlockAST>[$LPAREN,"BLOCK"] ^(ALT ^(SET[$LPAREN,"SET"] setElement+ )))
		-> 			^(SET[$LPAREN,"SET"] setElement+ )
*/
    ;

setElement
	:	TOKEN_REF<TerminalAST>
	|	STRING_LITERAL<TerminalAST>
	|	range
	;

// -------------
// Grammar Block
//
// Anywhere where an element is valid, the grammar may start a new block
// of alts by surrounding that block with ( ). A new block may also have a set
// of options, which apply only to that block.
//
block
//@after {
//GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.OPTIONS);
//if ( options!=null ) {
//	Grammar.setNodeOptions($tree, options);
//}
//}
 	:	LPAREN
        ( optionsSpec? ra+=ruleAction* COLON )?
        altList
		RPAREN
      //-> ^(BLOCK<BlockAST>[$LPAREN,"BLOCK"] optionsSpec? $ra* altList )
    ;

// ----------------
// Parser rule ref
//
// Reference to a parser rule with optional arguments and optional
// directive to become the root node or ignore the tree produced
//
ruleref
    :	RULE_REF argActionBlock?
		(	(op=ROOT|op=BANG)	//-> ^($op ^(RULE_REF ARG_ACTION<ActionAST>?))
		|						//-> ^(RULE_REF ARG_ACTION<ActionAST>?)
		)
    ;
    //catch [RecognitionException re] { throw re; } // pass upwards to element

// ---------------
// Character Range
//
// Specifies a range of characters. Valid for lexer rules only, but
// we do not check that here, the tree walkers shoudl do that.
// Note also that the parser also allows through more than just
// character literals so that we can produce a much nicer semantic
// error about any abuse of the .. operator.
//
range
    : STRING_LITERAL<TerminalAST> RANGE<RangeAST>/*^*/ STRING_LITERAL<TerminalAST>
    ;

terminal
//@after {
//GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.ELEMENT_OPTIONS);
//if ( options!=null ) {
//	Grammar.setNodeOptions($tree, options);
//}
//}
    :   TOKEN_REF elementOptions?		//-> ^(TOKEN_REF<TerminalAST> elementOptions?)
	|   STRING_LITERAL elementOptions?	//-> ^(STRING_LITERAL<TerminalAST> elementOptions?)
	;

// Terminals may be adorned with certain options when
// reference in the grammar: TOK<,,,>
elementOptions
    : LT elementOption (COMMA elementOption)* GT //-> ^(ELEMENT_OPTIONS[$LT,"ELEMENT_OPTIONS"] elementOption+)
    ;

// WHen used with elements we can specify what the tree node type can
// be and also assign settings of various options  (which we do not check here)
elementOption
    : // This format indicates the default node option
      qid

    | // This format indicates option assignment
      id ASSIGN/*^*/ (qid | STRING_LITERAL<TerminalAST>)
    ;

rewrite
	:	predicatedRewrite* nakedRewrite //-> predicatedRewrite* nakedRewrite
	;

predicatedRewrite
	:	RARROW SEMPRED rewriteAlt
		//-> {$rewriteAlt.isTemplate}? ^(ST_RESULT[$RARROW] SEMPRED<PredAST> rewriteAlt)
		//-> ^(RESULT[$RARROW] SEMPRED<PredAST> rewriteAlt)
	;

nakedRewrite
	:	RARROW rewriteAlt //-> {$rewriteAlt.isTemplate}? ^(ST_RESULT[$RARROW] rewriteAlt)
	 					  //-> ^(RESULT[$RARROW] rewriteAlt)
	;

// distinguish between ST and tree rewrites; for ETC/EPSILON and trees,
// rule altAndRewrite makes REWRITE root. for ST, we use ST_REWRITE
rewriteAlt returns [boolean isTemplate]
//options {backtrack=true;}
    : // If we are not building templates, then we must be
      // building ASTs or have rewrites in a grammar that does not
      // have output=AST; options. If that is the case, we will issue
      // errors/warnings in the next phase, so we just eat them here
      rewriteTreeAlt

	| // try to parse a template rewrite
      rewriteTemplate /*{$isTemplate=true;}*/ // must be 2nd so "ACTION ..." matches as tree rewrite

    | ETC

    | /* empty rewrite */ //-> EPSILON
    ;

rewriteTreeAlt
    :	rewriteTreeElement+ //-> ^(REWRITE_SEQ rewriteTreeElement+)
    ;

rewriteTreeElement
	:	rewriteTreeAtom ebnfSuffix?
	//|	rewriteTreeAtom ebnfSuffix //-> ^( ebnfSuffix ^(REWRITE_BLOCK ^(REWRITE_SEQ rewriteTreeAtom)) )
	|   rewriteTree
		(	ebnfSuffix
			//-> ^(ebnfSuffix ^(REWRITE_BLOCK ^(REWRITE_SEQ rewriteTree)) )
		|	//-> rewriteTree
		)
	|   rewriteTreeEbnf
	;

rewriteTreeAtom
//@after {
//GrammarAST options = (GrammarAST)$tree.getFirstChildWithType(ANTLRParser.OPTIONS);
//if ( options!=null ) {
//	Grammar.setNodeOptions($tree, options);
//}
//}
    :   TOKEN_REF elementOptions? argActionBlock? //-> ^(TOKEN_REF<TerminalAST> elementOptions? ARG_ACTION<ActionAST>?) // for imaginary nodes
    |   RULE_REF
	|   STRING_LITERAL elementOptions?		  //-> ^(STRING_LITERAL<TerminalAST> elementOptions?)
	|   DOLLAR id //-> LABEL[$DOLLAR,$id.text] // reference to a label in a rewrite rule
	|	actionBlock/*<ActionAST>*/
	;

rewriteTreeEbnf
//@init {
//    Token firstToken = input.LT(1);
//}
//@after {
//	$rewriteTreeEbnf.tree.getToken().setLine(firstToken.getLine());
//	$rewriteTreeEbnf.tree.getToken().setCharPositionInLine(firstToken.getCharPositionInLine());
//}
	:	lp=LPAREN rewriteTreeAlt RPAREN rewriteEbnfSuffix
		//-> ^(rewriteEbnfSuffix ^(REWRITE_BLOCK[$lp,"REWRITE_BLOCK"] rewriteTreeAlt))
	;

rewriteEbnfSuffix
	:	QUESTION	//-> OPTIONAL[$start]
  	|	STAR 		//-> CLOSURE[$start]
	;

rewriteTree
	:	TREE_BEGIN rewriteTreeAtom rewriteTreeElement* RPAREN
		//-> ^(TREE_BEGIN rewriteTreeAtom rewriteTreeElement* )
	;

/** Build a tree for a template rewrite:
      ^(TEMPLATE (ID|ACTION) ^(ARGLIST ^(ARG ID ACTION) ...) )
    ID can be "template" keyword.  If first child is ACTION then it's
    an indirect template ref

    -> foo(a={...}, b={...})
    -> ({string-e})(a={...}, b={...})  // e evaluates to template name
    -> {%{$ID.text}} // create literal template from string (done in ActionTranslator)
	-> {st-expr} // st-expr evaluates to ST
 */
rewriteTemplate
	:   // -> template(a={...},...) "..."    inline template
		TEMPLATE LPAREN rewriteTemplateArgs RPAREN
		( str=DOUBLE_QUOTE_STRING_LITERAL | str=DOUBLE_ANGLE_STRING_LITERAL )
		//-> ^(TEMPLATE[$TEMPLATE,"TEMPLATE"] rewriteTemplateArgs? $str)

	|	// -> foo(a={...}, ...)
		rewriteTemplateRef

	|	// -> ({expr})(a={...}, ...)
		rewriteIndirectTemplateHead

	|	// -> {...}
		actionBlock/*<ActionAST>*/
	;

/** -> foo(a={...}, ...) */
rewriteTemplateRef
	:	id LPAREN rewriteTemplateArgs RPAREN
		//-> ^(TEMPLATE[$LPAREN,"TEMPLATE"] id rewriteTemplateArgs?)
	;

/** -> ({expr})(a={...}, ...) */
rewriteIndirectTemplateHead
	:	lp=LPAREN actionBlock RPAREN LPAREN rewriteTemplateArgs RPAREN
		//-> ^(TEMPLATE[$lp,"TEMPLATE"] ACTION<ActionAST> rewriteTemplateArgs?)
	;

rewriteTemplateArgs
	:	rewriteTemplateArg (COMMA rewriteTemplateArg)*
		//-> ^(ARGLIST rewriteTemplateArg+)
	|
	;

rewriteTemplateArg
	:   id ASSIGN actionBlock //-> ^(ARG[$ASSIGN] id ACTION<ActionAST>)
	;

// The name of the grammar, and indeed some other grammar elements may
// come through to the parser looking like a rule reference or a token
// reference, hence this rule is used to pick up whichever it is and rewrite
// it as a generic ID token.
id
//@init { paraphrases.push("looking for an identifier"); }
//@after { paraphrases.pop(); }
    : RULE_REF  //->ID[$RULE_REF]
    | TOKEN_REF //->ID[$TOKEN_REF]
    | TEMPLATE  //->ID[$TEMPLATE] // keyword
    ;

qid
//@init { paraphrases.push("looking for a qualified identifier"); }
//@after { paraphrases.pop(); }
	:	id (DOT id)* //-> ID[$qid.start, $text]
	;

alternativeEntry : alternative EOF ; // allow gunit to call alternative and see EOF afterwards
elementEntry : element EOF ;
ruleEntry : rule EOF ;
blockEntry : block EOF ;
