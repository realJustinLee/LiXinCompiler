%{
#include <ctype.h>
#include <stdio.h>
#define YYSTYPE double

create_parser.YYSTYPE yyval = (create_parser.YYSTYPE)yyparserref.yyvalref

%}

\+ return create_parser.token_add
\- return create_parser.token_sub
\* return create_parser.token_mul
\/ return create_parser.token_div
\( return create_parser.token_open_bracket
\) return create_parser.token_close_bracket

[a-zA-Z][a-zA-Z0-9*] return create_parser.token_id ;

%start S
%left token_add token_sub
%left token_mul token_div
%token token_open_bracket token_close_bracket
%token token_id token_const
%%
S : E ;
E : E token_add E ;
E : E token_sub E ;
E : E token_mul E ;
E : E token_div E ;
E : token_open_bracket E token_close_bracket;
E : token_id ;
E : token_const ;
%%

