%{
#include <ctype.h>
#include <stdio.h>
#define YYSTYPE double
%}

%{
create_parser.YYSTYPE yyval = (create_parser.YYSTYPE)yyparserref.yyvalref

%}

\+ if(yyleng>0) return create_parser.token_add;
\- if(yylrng>0) return create_parser.token_sub;
\* if(yyleng>0) return create_parser.token_mul;
\/ if(yyleng>0) return create_parser.token_div;
\( if(yyleng>0) return create_parser.token_open_bracket;
\) if(yyleng>0) return create_parser.token_close_bracket;
\n if(yyleng>0) return create_parser.token_semi;

[a-zA-Z][a-zA-Z0-9*] if(yyleng>0) return create_parser.token_id;
[1-9][0-9]* if(yyleng>0) return create_parser.token_const;

.;

%%

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

