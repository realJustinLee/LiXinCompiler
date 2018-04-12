# LiXinCompiler

Taken from the Dragon Book

The Language

The language is based on the fragments in Chapter 6: expressions, arrays,
boolean expressions, statements, declarations, blocks:

P  ->  { DD SS }
DD ->  e | DD D | D
D  ->  T id ;
T  ->  T [ num ] | int | float | char | bool
SS ->  e | SS S | S
S  ->  L = E ; | if ( B ) S | if ( B ) S else S | while ( B ) S
   |   do S while ( B ) ; | break ; | { DD SS }
B  ->  B or B | B and B | ! B | ( B ) | E rel E | true | false
E  ->  E + E | E - E | E * E | E / E | L | ( B ) | num
L  ->  L [ B ] | id


Package lexer

class Tag. Tags distinguish tokens.
class Token with subclasses Num, Real, and Word
class Lexer, with procedure scan


Package symbols

class Type.  Put types here.
class Id.  Could have put Id's with expressions; in fact Id extends Expr
clas Env.  Linked symbol tables.


Package inter for intermediate code

For simplicity, the front end builds syntax trees.  Three-address code is
emitted during a subsequent pass.  We generate short-circuit code for
boolean expressions.

An optimizing compiler would presumably create intermediate-code objects
rather than emitting strings.  Further, Chapter 9 has examples with code
that might be produced by backpatching -- that's a variant to be explored
separately.

Package parser

At one point, I had the parser and lexer in one package, called syntax.
The parser is kept separate for readability.  We can present the lexer
early -- the parser "touches" the other packages, so it's best presented
later.

Directories tests and tmp

The makefile automatically runs a compiled front end on tests, presumed
to be in files ending in ".t"; for example, prog0.t is the quicksort
fragment from the running example in Chapter 9.  The expected output is
in a file ending in ".i"; for example, prog0.i.

To distinguish between multiple declarations of the same name, uncomment
the line

//	public String toString() {return "" + op.toString() + offset;}

in class Id in package inter.  The intermediate code will then print the
offset as a suffix to an identifier.

