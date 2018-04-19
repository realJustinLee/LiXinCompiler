%{
/****************************************************************************
myparser.y
ParserWizard generated YACC file.

Date: 2018Äê4ÔÂ19ÈÕ
****************************************************************************/
%}

/////////////////////////////////////////////////////////////////////////////
// declarations section

// parser name
%name create_parser

// class members
{
		// place any extra class members here
}

// constructor
{
		// place any extra initialisation code here
}

// attribute type
%union {
		public int value;

		public void yycopy(yyattribute source, boolean move) {
			YYSTYPE yy = (YYSTYPE)source;
			value = yy.value;
		}
}

// place any declarations here
%start S
%token token_semi
%left	token_add	token_sub
%left	token_mul	token_div
%token	token_left	token_right
%token	token_id	token_const	

%%

/////////////////////////////////////////////////////////////////////////////
// rules section

// place your YACC rules here (there must be at least one)
S	:		E		token_semi
			{
				yyaccept();
				System.out.println("terminated");
			}
			;
E	:		E	token_add	E
			{
				System.out.println("ADD");
			}
			;
E	:		E	token_sub	E
			{
				System.out.println("SUB");
			}
			;
E	:		E	token_mul	E
			{
				System.out.println("MUL");
			}
			;
E	:		E	token_div	E
			{
				System.out.println("DIV");
			}
			;
E	:		token_left		E		token_right
			{
				System.out.println("()");
			}
			;
E	:		token_id
			{
				System.out.println("ID");
			}
			;
E	:		token_const
			{
				System.out.println("const");
			}
			;



%%

/////////////////////////////////////////////////////////////////////////////
// programs section

	public static void main(String args[]) {
		int n = 1;
		create_lexer lexer = new create_lexer();
		create_parser parser = new create_parser();
		if (parser.yycreate(lexer)) {
			if (lexer.yycreate(parser)) {
				n = parser.yyparse();
			}
		}
		System.exit(n);
	}

}

