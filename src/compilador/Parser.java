//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "gramatica.y"
package compilador;

import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short CONSTANTE=258;
public final static short IF=259;
public final static short THEN=260;
public final static short ELSE=261;
public final static short END_IF=262;
public final static short BEGIN=263;
public final static short END=264;
public final static short OUT=265;
public final static short LONG=266;
public final static short DOUBLE=267;
public final static short SWITCH=268;
public final static short CASE=269;
public final static short FUNCTION=270;
public final static short RETURN=271;
public final static short MOVE=272;
public final static short CADENA=273;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    3,    5,    5,    5,
    5,    6,    6,    6,    6,    6,    6,    7,    7,    7,
    7,    7,    8,    8,    8,    8,    8,    9,    9,   12,
   12,   12,   12,    4,    4,    4,    4,    4,    4,   16,
   16,   16,   14,   14,   13,   13,   13,   13,   11,   11,
   11,   10,   10,   19,   19,   19,   19,   19,   19,   18,
   18,   18,   20,   20,   20,   21,   21,   15,   17,   17,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    4,    1,    1,    1,
    1,   10,    6,    7,    8,    9,    9,    8,    4,    5,
    6,    7,    8,    5,    6,    7,    8,    1,    2,    1,
    1,    1,    1,   11,   12,   11,   11,   11,   11,    4,
    4,    4,    4,    4,    3,    4,    4,    4,    4,    5,
    4,    3,    3,    1,    1,    1,    1,    1,    1,    3,
    3,    1,    3,    3,    1,    1,    1,    5,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   69,   70,    0,    0,    0,
    2,    4,    5,    6,    8,    9,   10,    0,   28,   30,
   31,   32,   33,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    3,   29,    0,    0,
    0,    0,   66,   67,    0,    0,    0,    0,   65,    0,
    0,    0,    0,   45,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   41,   47,   44,    0,    0,    0,    0,   42,
   40,   43,    0,   48,   46,    0,   19,    0,    0,    0,
    0,   54,   55,   56,   57,   58,   59,    0,    0,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   63,   64,    0,    0,   20,
    0,    0,    0,    0,    0,    0,   68,    0,    0,    0,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   13,    0,    0,   21,    0,    0,    0,    0,   51,
   49,    0,   25,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   14,    0,    0,   22,    0,    0,    0,   50,
   26,    0,    0,    0,    0,    0,    0,    0,    0,   15,
    0,    0,    0,   18,   27,   23,    0,    0,    0,    0,
    0,    0,   16,   17,    0,    0,    0,    0,    0,    0,
    0,   12,    0,    0,    0,    0,    0,   36,   37,   38,
   39,   34,    0,   35,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,   13,   14,   15,   16,   17,   18,   60,
   65,   19,   20,   21,   22,   23,   24,   47,   98,   48,
   49,
};
final static short yysindex[] = {                      -182,
 -223,  -12,  -33, -151,   14,    0,    0,  -31,    0, -182,
    0,    0,    0,    0,    0,    0,    0, -212,    0,    0,
    0,    0,    0, -206, -197,  -38,  -26, -108, -179, -198,
 -110, -169, -184, -176, -160, -103,    0,    0, -154, -100,
 -157,   -8,    0,    0,   92,  100,   79,   20,    0,  109,
  112,   99,   -3,    0,  115,  120, -151,  131,  -98,   -1,
  -25,  133,  140,  -72, -101, -160,  -19,   65,   73,  -94,
  -60, -151,    0,    0,    0, -108, -108, -108, -108,    0,
    0,    0,  -97,    0,    0,  -64,    0, -151,  153,  -77,
 -166,    0,    0,    0,    0,    0,    0, -108, -108,    0,
  154,  -45,  -57,  156,  -91, -160,  -80, -151, -151, -151,
 -151,   80,  -67,   20,   20,    0,    0,  159,  -56,    0,
 -151,  161,  -71, -164,   28,   20,    0, -151, -151,  150,
    0,  163,  -89, -160, -160,  -61,  -59,  -58,  -55, -151,
  174,    0,  171,  -44,    0, -151,  176, -151,  -69,    0,
    0, -151,    0,  178,  -87,  -86,  186,  191,  192,  198,
  -28, -108,    0,  208,   -6,    0,   -4, -151,  215,    0,
    0,  225,  235, -108, -108, -108, -108,  242,   57,    0,
  237,  243,   26,    0,    0,    0,   75,   78,   90,   91,
 -108,  244,    0,    0,  245,  246,  247,  248,  249,   96,
  172,    0,  173,  175,  177,  179,  250,    0,    0,    0,
    0,    0,  180,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  299,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -41,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -35,  -29,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,   10,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  291,   19,    0,    0,    0,    0,    0,    0,    0,
  -24,  285,  277,    0,    0,    0,   22,   -2,  251,  -20,
  116,
};
final static int YYTABLESIZE=312;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
   11,   62,   45,   62,   62,   60,   32,   60,   36,   60,
   60,   61,  129,   61,   51,   61,   61,   76,   62,   77,
   62,  107,   33,  104,   60,   52,   60,   27,  111,   61,
   61,   29,   61,  132,   92,  154,   93,  172,  173,   91,
   29,  105,  135,   52,    2,   30,   25,   46,   28,   39,
   53,   56,    5,   34,   30,  114,  115,   55,   92,   42,
   93,   78,   52,   40,   52,   41,   79,    6,    7,   53,
   76,   53,   77,    1,    2,   86,    3,   53,  126,   62,
    4,  133,    5,    6,    7,    8,   59,   43,   44,  123,
  113,  148,    2,  124,    3,  125,   63,  192,    4,   76,
    5,   77,   68,    8,   46,    2,  119,    3,   64,  155,
  156,    4,   71,    5,   72,  196,    8,   76,  197,   77,
   76,   76,   77,   77,   75,   11,  136,  137,  138,  139,
  198,  199,   76,   76,   77,   77,  207,   73,   76,  144,
   77,   76,  149,   77,   82,   74,  150,  151,   43,   44,
   57,   58,   66,   67,   80,   69,   70,   81,  161,  179,
   84,  110,   88,   89,  165,   85,  167,  103,    6,    7,
  170,  187,  188,  189,  190,  134,   87,  103,  100,  103,
  101,  103,  103,  121,  122,  102,  183,  108,  200,  146,
  147,  168,  169,  116,  117,  109,  112,  118,  120,  127,
  130,  131,  140,  141,  142,  143,  145,  152,  153,  157,
  128,  158,  159,  162,   62,  160,  163,  164,   43,   44,
   60,  166,   31,  171,   35,  174,   61,    6,    7,   50,
  175,  176,   62,   62,   62,   62,  106,  177,   60,   60,
   60,   60,  178,   26,   61,   61,   61,   61,   94,   95,
   96,   97,   83,  180,   90,  181,   11,  182,   52,   11,
  184,   11,   11,   11,   11,   53,   11,   11,   11,   11,
  185,   11,   94,   95,   96,   97,   52,   52,   52,   52,
  186,  191,  193,   53,   53,   53,   53,  195,  194,  201,
  202,  203,  204,  205,  206,  213,  208,  209,    1,  210,
   37,  211,   38,  212,  214,   54,    0,    0,    0,    0,
    0,   99,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   43,   41,   45,   46,   41,   40,   43,   40,   45,
   46,   41,   58,   43,   41,   45,   46,   43,   60,   45,
   62,   41,    4,  125,   60,   28,   62,   40,  123,   32,
   60,   44,   62,  125,   60,  125,   62,  125,  125,   41,
   44,   66,  123,   41,  257,   58,  270,   26,   61,  256,
   41,   30,  265,   40,   58,   76,   77,  256,   60,  257,
   62,   42,   60,  270,   62,  272,   47,  266,  267,   60,
   43,   62,   45,  256,  257,   57,  259,  257,   99,  264,
  263,  106,  265,  266,  267,  268,  256,  257,  258,  256,
   72,  256,  257,  260,  259,   98,  273,   41,  263,   43,
  265,   45,  257,  268,   83,  257,   88,  259,  269,  134,
  135,  263,  270,  265,  123,   41,  268,   43,   41,   45,
   43,   43,   45,   45,   46,  125,  108,  109,  110,  111,
   41,   41,   43,   43,   45,   45,   41,   46,   43,  121,
   45,   43,  124,   45,   46,   46,  128,  129,  257,  258,
  261,  262,  256,  257,   46,  256,  257,   46,  140,  162,
   46,  256,  261,  262,  146,   46,  148,  269,  266,  267,
  152,  174,  175,  176,  177,  256,   46,  269,   46,  269,
   41,  269,  269,  261,  262,  258,  168,  123,  191,  261,
  262,  261,  262,   78,   79,  123,  257,  262,   46,   46,
  258,   46,  123,  271,   46,  262,   46,   58,   46,  271,
  256,  271,  271,   40,  256,  271,   46,  262,  257,  258,
  256,   46,  256,   46,  256,   40,  256,  266,  267,  256,
   40,   40,  274,  275,  276,  277,  256,   40,  274,  275,
  276,  277,  271,  256,  274,  275,  276,  277,  274,  275,
  276,  277,  256,   46,  256,  262,  256,  262,  256,  259,
   46,  261,  262,  263,  264,  256,  266,  267,  268,  269,
   46,  271,  274,  275,  276,  277,  274,  275,  276,  277,
   46,   40,   46,  274,  275,  276,  277,  262,   46,   46,
   46,   46,   46,   46,   46,   46,  125,  125,    0,  125,
   10,  125,   18,  125,  125,   29,   -1,   -1,   -1,   -1,
   -1,   61,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",null,
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IDENTIFICADOR","CONSTANTE","IF","THEN",
"ELSE","END_IF","BEGIN","END","OUT","LONG","DOUBLE","SWITCH","CASE","FUNCTION",
"RETURN","MOVE","CADENA","\"<=\"","\">=\"","\"<>\"","\"==\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : bloque_main",
"bloque_main : bloque_comun",
"bloque_main : bloque_main bloque_comun",
"bloque_comun : bloques",
"bloque_comun : declaracion_funcion",
"bloques : bloque_para_funcion",
"bloques : BEGIN bloques END '.'",
"bloque_para_funcion : sentencia_if_else",
"bloque_para_funcion : sentencia_if",
"bloque_para_funcion : sentencia_switch",
"bloque_para_funcion : sentencias",
"sentencia_if_else : IF '(' condicion ')' THEN bloques ELSE bloques END_IF '.'",
"sentencia_if_else : IF error ELSE bloques END_IF '.'",
"sentencia_if_else : IF '(' error ELSE bloques END_IF '.'",
"sentencia_if_else : IF '(' condicion error ELSE bloques END_IF '.'",
"sentencia_if_else : IF '(' condicion ')' error ELSE bloques END_IF '.'",
"sentencia_if_else : IF '(' condicion ')' THEN error bloques END_IF '.'",
"sentencia_if : IF '(' condicion ')' THEN bloques END_IF '.'",
"sentencia_if : IF error END_IF '.'",
"sentencia_if : IF '(' error END_IF '.'",
"sentencia_if : IF '(' condicion error END_IF '.'",
"sentencia_if : IF '(' condicion ')' error END_IF '.'",
"sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"sentencia_switch : SWITCH error rep_switch '}' '.'",
"sentencia_switch : SWITCH '(' error rep_switch '}' '.'",
"sentencia_switch : SWITCH '(' IDENTIFICADOR error rep_switch '}' '.'",
"sentencia_switch : SWITCH '(' IDENTIFICADOR ')' error rep_switch '}' '.'",
"sentencias : sentencia_unica",
"sentencias : sentencias sentencia_unica",
"sentencia_unica : declaracion",
"sentencia_unica : asignacion",
"sentencia_unica : salida",
"sentencia_unica : llamado_funcion",
"declaracion_funcion : tipo FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : error FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : tipo error IDENTIFICADOR '{' bloques RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : tipo FUNCTION error '{' bloques RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : tipo FUNCTION IDENTIFICADOR error bloques RETURN '(' expresion ')' '.' '}'",
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"llamado_funcion : IDENTIFICADOR error ')' '.'",
"llamado_funcion : IDENTIFICADOR '(' error '.'",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"asignacion : IDENTIFICADOR error expresion '.'",
"declaracion : IDENTIFICADOR ',' declaracion",
"declaracion : IDENTIFICADOR ':' tipo '.'",
"declaracion : IDENTIFICADOR error tipo '.'",
"declaracion : IDENTIFICADOR ':' error '.'",
"rep_switch : CASE CONSTANTE ':' bloques",
"rep_switch : rep_switch CASE CONSTANTE ':' bloques",
"rep_switch : CASE CONSTANTE error bloques",
"condicion : condicion operador expresion",
"condicion : expresion operador termino",
"operador : '<'",
"operador : '>'",
"operador : \"<=\"",
"operador : \">=\"",
"operador : \"<>\"",
"operador : \"==\"",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"salida : OUT '(' CADENA ')' '.'",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 145 "gramatica.y"

private Lexico lexico;
private Sintactico sintactico;
private Token token;


public void setLexico(Lexico lexico)
{
	this.lexico = lexico;
}
public void setSintactico(Sintactico sintactico) {
	this.sintactico = sintactico;	
}
private int yylex() {
	this.token = lexico.getToken();
	if (token.getType().equals("Identificador") || token.getType().equals("Constante") || token.getType().equals("Cadena"))	
	{
		return token.getKey();
	}
	else if (token.getType().equals("Literal") || token.getType().equals("OperadorAritmetico") || token.getType().equals("OperadorAsignacion")) 
	{
		return (int)token.getLexema().charAt(0);
	}
	
	return token.getKey();	
}
private void yyerror(String string) {
	//metodo de muestra de errores
	//this.sintactico.showMessage(string);
	System.out.println(string);
}
//#line 416 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 7:
//#line 26 "gramatica.y"
{this.sintactico.showMessage("Bloque: BEGIN - END");}
break;
case 12:
//#line 35 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 13:
//#line 37 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
break;
case 14:
//#line 38 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF - ELSE");}
break;
case 15:
//#line 39 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
break;
case 16:
//#line 40 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
break;
case 17:
//#line 41 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
break;
case 18:
//#line 45 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 19:
//#line 47 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
break;
case 20:
//#line 48 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF");}
break;
case 21:
//#line 49 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
break;
case 22:
//#line 50 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
break;
case 23:
//#line 54 "gramatica.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 24:
//#line 56 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia SWITCH");}
break;
case 25:
//#line 57 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en sentencia SWITCH");}
break;
case 26:
//#line 58 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia SWITCH");}
break;
case 27:
//#line 59 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en sentencia SWITCH");}
break;
case 34:
//#line 73 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion");}
break;
case 35:
//#line 74 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
break;
case 36:
//#line 76 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'tipo' en declaracion de Funcion");}
break;
case 37:
//#line 77 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de Funcion");}
break;
case 38:
//#line 78 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de Funcion");}
break;
case 39:
//#line 79 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de Funcion");}
break;
case 40:
//#line 83 "gramatica.y"
{this.sintactico.showMessage("Llamado a función");}
break;
case 41:
//#line 85 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a Funcion");}
break;
case 42:
//#line 86 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
case 43:
//#line 90 "gramatica.y"
{this.sintactico.showMessage("Asignación");}
break;
case 44:
//#line 92 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignacion");}
break;
case 45:
//#line 96 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable multiple");}
break;
case 46:
//#line 97 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable");}
break;
case 47:
//#line 99 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en declaracion de variable");}
break;
case 48:
//#line 100 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'tipo' en declaracion de variable");}
break;
case 49:
//#line 104 "gramatica.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 51:
//#line 107 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en sentencia CASE");}
break;
case 53:
//#line 112 "gramatica.y"
{this.sintactico.showMessage("Condición");}
break;
case 60:
//#line 123 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 61:
//#line 124 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 63:
//#line 128 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 64:
//#line 129 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 68:
//#line 137 "gramatica.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
//#line 725 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
