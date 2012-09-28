parser grammar GrammarTemplate;

options {
    abstract=true;
}

tokens {
    LITERAL,
    PSEUDO_TOKEN,
}

compileUnit
    : EOF
    ;

WS : (' ' | '\t')+;
