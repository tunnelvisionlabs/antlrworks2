parser grammar GrammarTemplate;

options {
    language=Java;
}

tokens {
    LITERAL="literal";
    PSEUDO_TOKEN;
}

compileUnit
    : EOF
    ;

WS : (' ' | '\t')+;
