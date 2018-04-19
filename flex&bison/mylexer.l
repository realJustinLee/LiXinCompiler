%{
/****************************************************************************
mylexer.l
ParserWizard generated Lex file.

Date: 2018Äê4ÔÂ19ÈÕ
****************************************************************************/
%}

/////////////////////////////////////////////////////////////////////////////
// declarations section

// lexical analyser name
%name create_lexer

// class members
{
		// place any extra class members here
}

// constructor
{
		// place any extra initialisation code here
}

// place any declarations here

%%

/////////////////////////////////////////////////////////////////////////////
// rules section

%{
		// extract yylval for use later on in actions
		create_parser.YYSTYPE yylval = (create_parser.YYSTYPE)yyparserref.yylvalref;
%}

// place your Lex rules here

\+										if(yyleng>0)	return  create_parser.token_add;
\-										if(yyleng>0)	return  create_parser.token_sub;
\*										if(yyleng>0)	return  create_parser.token_mul;
\/										if(yyleng>0)	return  create_parser.token_div;
\(										if(yyleng>0)	return create_parser.token_left;
\)										if(yyleng>0)	return create_parser.token_right;
\n										if(yyleng>0)	return 	create_parser.token_semi;
[a-zA-Z][a-zA-Z0-9]*		if(yyleng>0)	return create_parser.token_id;
[1-9][0-9]*							if(yyleng>0)	return 	create_parser.token_const;
.				;

%%

/////////////////////////////////////////////////////////////////////////////
// programs section

}

