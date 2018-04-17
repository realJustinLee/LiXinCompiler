# LiXinCompiler
Referenced from the dragon-book, just a little implementation of the dragon-book language.

参考自龙书上的语言，自己实现了一个编译器的前端。

## 源语言
这个语言由一个程序和一个块组成，该块中包含可选的声名和语句。语法符号```basic```表示基本类型。

```
program     ->  block
block       ->  { declause statemets }
declause    ->  decaluse debracket | ϵ
debrcket    ->  type identity ;
type        ->  type [ num ] | basic
statements  ->  statements statement | ϵ
```
为了简化翻译工作，我们把赋值当成了一个语句.
```
statement   ->  location = bool ;
            |   if ( bool ) statement
            |   if ( bool ) stetement else statement
            |   while ( bool ) statement
            |   do statement while ( bool ) ;
            |   break ;
            |   block
location    ->  location [ bool ] | identity
```
表达式的产生式处理了运算符的结合性和优先级。它们对每个优先级都使用了一个非终结符号，而非终结符号```factor```用来表示括号中的表达式、标识符、数组引用和常量.
```
bool        ->  bool || join | join
join        ->  join && equality | equality
equality    ->  equality == relation | equality != relation | relation
relation    ->  expression < expression | expressoion <= expression | 
                expressoion >= expression | expressoion > expression | expression
expression  ->  expression + term | expression - term | term
term        ->  term * unary | term / unary | unary
factor      ->  ( bool ) | location | numeric | real | true | false
```
## 测试样例
### block:
输入
```
{
	int a; int b; a = 0; b = 0;
	{
		int b; b = 1;
		{
			int a; a = 2;
		}
		{
			int b; b = 3;
		}
		a = a + 1; b = b + 1;
	}
	a = a + 1; b = b + 1;
}
```
输出
```
L1:	a = 0
L3:	b = 0
L4:	b = 1
L6:	a = 2
L7:	b = 3
L8:	a = a + 1
L9:	b = b + 1
L5:	a = a + 1
L10:b = b + 1
L2:
```
### expression:
输入
```
{
	int i; float x; bool b;
	i = 0;
	i = 365;
	x = 0.0;
	x = 3.14159;
	b = true;
	b = false;
	i = x;
	x = i;
}
```
输出
```
   
```
### identity:
输入
```
{
	int i; int j; float[10][10] a;
	i = 0;
	while ( i < 10 ) {
		j = 0;
		while ( j < 10 ) {
			a[i][j] = 0.0;
			j = j + 1;
		}
		i = i + 1;
	}
	i = 0;
	while ( i < 10 ) {
		a[i][i] = 1.0;
		i = i + 1;
	}
}
```
输出
```

```
### jump:
输入
```
{
    int x; int y; int a; int b;
	if( true ) a = 0;
	if( false ) x = 0;
	if ( a < b ) a = b;
	if ( x <= y ) x = y;
	if ( a == b ) a = b;
	if ( x != y ) x = y;
	if ( a >= b ) b = a;
	if ( x > y ) y = x;
	if ( a == b );
        if( x < 100 || x > 200 ) x = 0;
        if( a < 100 && a > 200 ) b = 0;
        if( x < 100 || (x > 200 && x != y) ) x = 0;
        if( a < 100 || (a > 200 && a != 150) || a != 0 ) a = 0;
        if( x > 200 && x != b || x < 100 ) x = 0;
        if( a < 100 || a > 200 && a != b ) a = 0;
}
```
输出
```
L1:L4:	a = 0
L3:	goto L5
L6:	x = 0
L5:	if not a < b goto L7
L8:	a = b
L7:	if not x <= y goto L9
L10:	x = y
L9:	if not a == b goto L11
L12:	a = b
L11:	if not x != y goto L13
L14:	x = y
L13:	if not a >= b goto L15
L16:	b = a
L15:	if not x > y goto L17
L18:	y = x
L17:	if not a == b goto L19
L20:L19:	if x < 100 goto L23
	if not x > 200 goto L21
L23:L22:	x = 0
L21:	if not a < 100 goto L24
	if not a > 200 goto L24
L25:	b = 0
L24:	if x < 100 goto L28
	if not x > 200 goto L26
	if not x != y goto L26
L28:L27:	x = 0
L26:	if a < 100 goto L31
	if a != 150 goto L31
L32:	if not a != 0 goto L29
L31:L30:	a = 0
L29:	if x != b goto L35
L36:	if not x < 100 goto L33
L35:L34:	x = 0
L33:	if a < 100 goto L38
	if not a > 200 goto L2
	if not a != b goto L2
L38:L37:	a = 0
L2:
```
### program:
输入
```
{
	int i; int j; float v; float x; float[100] a;
	while( true ) {
		do i = i+1; while( a[i] < v);
		do j = j-1; while( a[j] > v);
		if( i >= j ) break;
		x = a[i]; a[i] = a[j]; a[j] = x;
	}
}
```
输出
```
L1:L3:	i = i + 1
L5:	t1=i * 8
	t2=a [ t1 ]
	if t2 < v goto L3
L4:	j = j - 1
L7:	t3=j * 8
	t4=a [ t3 ]
	if t4 > v goto L4
L6:	if not i >= j goto L8
L9:	goto L2
L8:	t5=i * 8
	x = a [ t5 ]
L10:	t6=i * 8
	t7=j * 8
	t8=a [ t7 ]
	a [ t6 ] = t8
L11:	t9=j * 8
	a [ t9 ] = x
	goto L1
L2:
```
# 关于BUG、建议与意见
请在[GitHub](https://github.com/Great-Li-Xin/LiXinCompiler)中提[issue](https://github.com/Great-Li-Xin/LiXinCompiler/issues/new)

我会尽快回答
