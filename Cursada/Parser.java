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






//#line 2 "gramaticaErrores.y"
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
    0,    1,    1,    1,    1,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    6,    6,    6,    6,    6,    6,    6,    6,
    3,    3,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   10,   10,   10,   10,   10,    8,    8,    8,
    8,    8,    7,    7,    7,    7,    7,    7,    7,    5,
    5,    5,    5,    5,    4,    4,    4,    4,    4,   14,
   14,   14,   14,   14,   14,   13,   13,   13,   13,   13,
   13,   13,   13,   15,   15,   15,   15,   15,   15,   15,
   15,   16,   16,    9,    9,    9,    9,    9,    9,   12,
   12,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,   10,    8,    8,    4,    1,
    3,    3,    3,    9,    9,    9,    9,    9,    9,    7,
    7,    7,    7,    7,    7,    7,    7,    7,    7,    8,
    8,    7,    2,    2,    2,    2,    1,    1,    1,    1,
    1,    2,   11,   12,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   11,   11,   11,   11,   11,   11,   11,
   11,   11,    4,    3,    3,    3,    3,    4,    3,    3,
    3,    3,    3,    4,    2,    3,    3,    3,    3,    4,
    5,    3,    3,    3,    3,    3,    2,    2,    2,    1,
    1,    1,    1,    1,    1,    3,    3,    1,    2,    2,
    2,    2,    2,    3,    3,    1,    2,    3,    3,    3,
    3,    1,    1,    5,    5,    4,    4,    4,    4,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,  120,  121,    0,    0,    0,    0,
    0,    0,    0,    0,    4,    5,    0,   37,   38,   39,
   40,   41,    0,    0,  113,    0,    0,    0,    0,    0,
    0,    0,   75,    0,    0,    0,  106,  112,    0,   90,
   91,   92,   93,   94,   95,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    2,    3,
    0,   33,   34,   35,   36,   42,    0,    0,    0,    0,
    0,   66,   65,   72,    0,   73,   78,    0,    0,    0,
   77,   70,    0,    0,    0,    0,    0,  107,    0,    0,
    0,    0,    0,    0,    0,   12,    0,    0,    0,    0,
    0,    0,    0,   64,   71,   79,   13,    0,    0,    0,
    0,    0,   63,   68,   74,    0,    0,  104,  105,    0,
    0,    0,    0,    0,    9,  116,    0,  117,  118,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  114,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   21,    0,    0,   24,    0,    0,   23,    0,   20,
    0,    0,    0,    0,    0,   25,   32,   28,    0,   27,
   26,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    7,    0,    0,    0,
    0,    0,    0,    8,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   15,    0,
   18,   19,   17,   14,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    6,   45,    0,   53,    0,   48,   50,   49,   51,   46,
   52,    0,    0,    0,    0,    0,    0,    0,   54,   43,
   62,    0,   57,   59,   58,   60,   55,   61,   44,
};
final static short yydgoto[] = {                         13,
   14,   15,   16,   46,  150,  134,   18,   19,   20,   21,
   22,   23,   47,   48,   36,   37,
};
final static short yysindex[] = {                       889,
  994,  -26,  -32,  -33,    0,    0,  -35, -205, -204,   61,
  -34, -190,    0,  889,    0,    0,  922,    0,    0,    0,
    0,    0,  214,  -38,    0,   -9,   73,  307,  -54,  -11,
 -178, -178,    0,   75,  330,   45,    0,    0,  859,    0,
    0,    0,    0,    0,    0,  -39,  866, -178,  146,   66,
  -29,   82, -127,   14, -118,   98,  389,  100,    0,    0,
  101,    0,    0,    0,    0,    0,   25, -107, -202,  -38,
  105,    0,    0,    0,  997,    0,    0,  119,   45,   45,
    0,    0, -178, -178,   45, -178, -178,    0,  534,  -93,
  -34, -178,   45,   45,  122,    0,  124,   10,  136,   79,
   -8,  942,   83,    0,    0,    0,    0,  942,  695,  942,
   85, -105,    0,    0,    0,   45,   45,    0,    0,  942,
  961,  942,   -5,   45,    0,    0,  152,    0,    0, -209,
  -84, -209,  -66,   17,  942,  -62,  942,  -61,  -60,  942,
  968,  942, -162,  942, -134, -119,    0,  160,  -45, -100,
  204,  -98,  -97,  217,  -13,  219,  -37,  257,  265,   78,
  942,   97,  103,  942,  298,  941,  942,  352,  942,  353,
  889,  903,  889,  139,  354,  356,  -24,  358,  361,  -34,
  368,  -34,  148,  -34,  -34,  -34,  412,  -25,  415,  421,
  210,    0,  942,  433,    0,  212,  220,    0,  225,    0,
  889,  889,  889,  889,  431,    0,    0,    0,  435,    0,
    0,  494,  -34,  893,  -34,  982,  987, 1026, 1029,  -34,
  423,  -34,  -34,  -34,  444,  -36,    0,  445,  446,  448,
  889,  208,  889,    0,  452, 1035,  457,  556,  460,  461,
  462,  463, 1044,  -34, 1054, 1057, 1077, 1084,    0,  464,
    0,    0,    0,    0,  889,  386,  466,  388,   -3,  394,
  397,  400,  404,  405,  468,  836,  485,  488,  492,  495,
    0,    0,  417,    0,  418,    0,    0,    0,    0,    0,
    0,  419,   -1,  420,  422,  424,  425,  426,    0,    0,
    0,  429,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  546,    0,    0,  201,    0,    0,    0,
    0,    0,    0,  481,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  351,    0,    0,    0,  459,    0,    0,  427,   24,   48,
    0,    0,   71,   95,  118,    0,    0,    0,    0,    0,
    0,  771,  566,  784,  475,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  154,  396,    0,    0,    0,
    0,    0,  789,  830,    0,    0,  443,    0,    0,    0,
    0,    0,    0,  234,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  500,    0,    0,    0,    0,    0,    0,
   35,    0,   37,   52,    0,    0,    0,    0,  516,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  591,
    0,    0,    0,    0,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  181,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  532,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -99,  766,  -14,  509, -101,  634,   96,   41,  384,  428,
  536,  815,  792,    7,  865, 1179,
};
final static int YYTABLESIZE=1342;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
   98,   90,  184,   12,   53,   29,   51,   10,   31,  251,
   32,   99,  173,   39,  222,  110,   31,  142,   32,   30,
   40,  208,   41,  100,  175,   12,  178,  179,   11,  152,
  153,   71,  131,   40,   77,   41,   72,   83,  151,   84,
   98,   98,  275,   98,  292,   98,   98,  102,  148,  177,
  127,   54,   91,   92,  111,  128,   10,   63,   98,  149,
   98,   98,   98,  100,  100,   55,  100,  112,  100,  100,
  101,  201,  203,  204,   12,    5,    6,   11,   38,   25,
   82,  100,   84,  100,  100,  100,   86,  102,  102,   63,
  102,   87,  102,  102,  103,   91,   33,   83,  164,  165,
  209,   56,  231,   80,   81,  102,   97,  102,  102,  102,
  101,  101,   62,  101,  132,  101,  101,   99,   73,   33,
   81,  276,  100,  293,   76,   98,  167,  168,  101,  101,
  101,  101,  101,  255,  103,  103,  102,  103,  103,  103,
  103,  169,  170,  104,   62,  106,  107,  108,  100,  109,
  113,  141,  103,   96,  103,  103,  103,   99,   99,   82,
   99,   84,   99,   99,  115,   33,  122,  125,  174,  126,
  174,  174,  102,  148,   63,   99,   83,   99,   99,   99,
   47,  129,   80,   81,  149,   10,   60,  215,   60,   60,
   31,   96,   32,   96,   96,  101,   96,  147,   96,   96,
   10,  130,   70,   12,  154,  135,   11,  140,  156,  158,
  159,   96,  172,   96,   96,   96,   60,  171,   70,  103,
   47,   52,   38,   25,    1,  250,   47,    5,    6,   62,
   38,   25,    4,  183,   42,   43,   44,   45,   47,   50,
   60,   47,   99,   98,  174,  221,   10,   42,   43,   44,
   45,   38,   25,  106,    5,    6,  180,  181,  182,   98,
   98,   98,   98,   98,   98,   98,   98,   98,   98,   98,
   98,   98,   98,    1,   98,   98,   98,   98,   96,   10,
   61,    4,  100,  100,  100,  100,  100,  100,  100,  100,
  100,  100,  100,  100,  100,  100,  185,  100,  100,  100,
  100,   38,   25,   82,  186,   84,  102,  102,  102,  102,
  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,
   83,  102,  102,  102,  102,   10,   80,   81,  176,  101,
  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,
  101,  101,  101,  192,  101,  101,  101,  101,  187,   31,
   67,   32,   74,  103,  103,  103,  103,  103,  103,  103,
  103,  103,  103,  103,  103,  103,  103,  189,  103,  103,
  103,  103,   83,  190,   84,   82,   99,   99,   99,   99,
   99,   99,   99,   99,   99,   99,   99,   99,   99,   99,
   67,   99,   99,   99,   99,   97,  205,  198,  200,  206,
   64,  207,    1,  210,   38,   25,  211,  213,   67,   95,
    4,   67,   96,   96,   96,   96,   96,   96,   96,   96,
   96,   96,   96,   96,   96,   96,   76,   96,   96,   96,
   96,   83,   64,   84,  105,   97,   97,   47,   97,   47,
   97,   97,  119,   47,   65,   47,   47,   47,   47,   47,
   47,  220,   47,   97,  223,   97,   97,   97,   69,   10,
  224,  148,  244,   10,   67,   31,   76,   32,   10,   10,
   67,  225,  149,  228,   11,   67,   65,   68,  227,   69,
  234,  229,  119,   68,   76,   69,  230,   76,  233,  249,
  252,  253,   10,  254,   10,   10,   10,  256,   69,   22,
  119,   10,  258,  119,   10,  261,  262,  263,  264,  271,
  272,  273,  274,  282,   11,   29,   69,   64,  277,   69,
   97,  278,  112,  112,  279,  112,  112,  112,  280,  281,
  285,   56,   11,  286,  235,   11,   83,  287,   84,   22,
  288,  289,  290,  291,  294,    1,  295,   89,  296,  297,
  298,   76,   66,  299,    0,   29,    0,   22,    0,    0,
   22,   65,    0,   38,   25,    0,    0,  119,    0,    0,
    0,   56,    0,   29,  121,    0,   29,   56,    0,    0,
    0,    0,    0,   69,    0,    0,   38,   25,    0,   56,
   16,    0,   56,   40,    0,   41,  259,    0,   83,   11,
   84,  260,    0,    0,    0,    0,   87,   67,   99,   67,
   99,   67,   67,   67,   67,   67,   67,   67,   67,   67,
   67,   67,   67,    0,   22,   87,    0,   87,    0,    0,
   16,    0,    0,   17,    0,    0,   49,    0,    0,    0,
   29,    0,    0,    0,    0,   38,   25,   17,   16,    0,
    0,   16,    0,    0,   97,   97,   97,   97,   97,   97,
   97,   97,   97,   97,   97,   97,   97,   97,    0,   97,
   97,   97,   97,    0,    0,    0,    0,    0,    0,   38,
   25,    0,    0,   76,    0,   76,    0,   76,   76,   76,
   76,   76,   76,   76,   76,   76,   76,   76,   76,  119,
    0,  119,    0,  119,  119,  119,  119,  119,  119,  119,
  119,  119,  119,  119,  119,   16,    0,   69,    0,   69,
   69,   69,   69,   69,   69,   69,   69,   69,   69,   69,
   69,   11,    0,   11,   10,   11,   11,   11,  112,   11,
   11,   11,   11,   11,   11,   11,   11,    0,    0,    0,
   38,   25,   12,    0,    0,   11,   22,    0,   22,    0,
   22,   22,   22,    0,   22,   22,   22,   22,   22,   22,
   22,   22,   29,    0,   29,    0,   29,   29,   29,   59,
   29,   29,   29,   29,   29,   29,   29,   29,   56,    0,
   56,    0,   35,  120,   56,    0,   56,   56,   56,   56,
   56,   56,   57,   56,   17,   17,   17,   42,   43,   44,
   45,   89,   38,   25,    0,   34,    0,  137,    0,   75,
    0,    0,    0,    0,   88,   87,   58,    0,    0,   85,
   89,    0,   89,    0,   17,   17,   17,   17,   34,   87,
   87,   87,   87,   88,   78,   88,    0,   16,   85,   16,
   85,   16,   16,   16,    0,   16,   16,   16,   16,   16,
   16,   16,   16,    0,   17,    0,   17,  133,    0,    0,
   86,    0,    0,  136,  138,  139,  283,    0,   83,    0,
   84,  284,  123,    0,   34,  143,  145,  146,   17,   86,
    0,   86,    0,    0,    0,   79,   80,    0,    0,   85,
  155,   31,  157,   32,    0,  160,  162,  163,   83,  166,
   84,   93,   94,    0,    0,    0,    0,    0,   40,    0,
   41,   85,    0,    0,    0,   40,  188,   41,   10,  191,
    0,  196,  197,  237,  199,   83,    0,   84,    0,   85,
    0,    0,   10,    0,    0,    0,   12,  116,  117,   11,
    0,    1,    0,    2,    0,    0,  124,    3,  226,    4,
  202,   10,    7,   11,    0,    0,   59,    0,   59,   59,
    0,  212,    0,  214,  216,  217,  218,  219,    0,   12,
   10,   10,   11,    0,    0,    0,  195,   85,    0,    0,
    0,    0,    0,    0,    0,    0,   59,    0,   12,   12,
   10,   11,   11,    0,  236,    0,  238,   10,    0,    0,
    0,  243,  245,  246,  247,  248,  232,    0,   12,    0,
   59,   11,  239,    0,   83,   12,   84,  240,   11,   83,
   89,   84,    0,   26,   27,  266,   31,   29,   32,   83,
    0,   84,  114,   88,   89,   89,   89,   89,   85,    0,
    0,   30,    0,    0,   28,    0,    0,   88,   88,   88,
   88,    0,   85,   85,   85,   85,  241,    0,   83,  242,
   84,   83,    0,   84,    0,  257,   85,   83,   85,   84,
   85,   85,   85,   85,  265,    0,   83,    0,   84,   86,
  161,    0,   38,   25,  267,    0,   83,  268,   84,   83,
   85,   84,   85,   86,   86,   86,   86,   85,    0,   85,
   85,   85,   85,    0,    0,   38,   25,  269,    0,   83,
    0,   84,   38,   25,  270,    0,   83,    0,   84,    0,
   85,    0,   42,   43,   44,   45,    0,    0,    0,   42,
   43,   44,   45,    0,    0,    1,    0,    2,    0,   38,
   25,    3,    0,    4,    5,    6,    7,    0,    8,    1,
    9,    2,    0,    0,    0,    3,    0,    4,    5,    6,
    7,    0,    8,    0,    9,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,   61,    4,    5,    6,    0,
    0,    8,    0,    9,    0,    0,    0,    1,    1,    2,
    2,  193,  194,    3,    3,    4,    4,    0,    7,    7,
    0,    0,    0,    0,   88,    0,    0,    1,    0,    2,
  144,    0,    0,    3,    1,    4,    2,    0,    7,    0,
    3,    0,    4,    0,    0,    7,    0,    0,   38,   25,
    0,    0,    0,   38,   25,    0,    0,    0,    0,    0,
   24,   25,    0,   38,   25,    0,    0,   88,   88,    5,
    6,    0,    0,   88,  118,  119,    0,    0,    0,    0,
    0,   88,   88,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   25,    0,   38,   25,    0,    0,    0,
    0,   38,   25,    0,   88,   88,    0,    0,    0,    0,
   38,   25,   88,    0,    0,    0,    0,    0,    0,    0,
   38,   25,    0,   38,   25,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   38,   25,    0,    0,    0,    0,    0,
   38,   25,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         14,
    0,   41,   40,   58,   40,   44,   40,   40,   43,   46,
   45,   41,   58,   40,   40,  123,   43,  123,   45,   58,
   60,   46,   62,    0,  125,   58,  125,  125,   61,  131,
  132,   41,   41,   60,   46,   62,   46,   43,  123,   45,
   40,   41,   46,   43,   46,   45,   46,    0,  258,  151,
   41,  257,   46,   47,  257,   46,   40,   17,   58,  269,
   60,   61,   62,   40,   41,  270,   43,  270,   45,   46,
    0,  171,  172,  173,   58,  266,  267,   61,  257,  258,
   46,   58,   46,   60,   61,   62,   42,   40,   41,   49,
   43,   47,   45,   46,    0,   89,    1,   46,  261,  262,
  125,   41,  202,   46,   46,   58,   41,   60,   61,   62,
   40,   41,   17,   43,  123,   45,   46,    0,   46,   24,
   46,  125,   41,  125,   29,  125,  261,  262,   58,  257,
   60,   61,   62,  233,   40,   41,  123,   43,  257,   45,
   46,  261,  262,   46,   49,   46,   46,  123,  125,  257,
   46,  257,   58,    0,   60,   61,   62,   40,   41,  125,
   43,  125,   45,   46,   46,   70,  260,   46,  269,   46,
  269,  269,  125,  258,  134,   58,  125,   60,   61,   62,
    0,   46,  125,  125,  269,   40,  201,   40,  203,  204,
   43,   46,   45,   40,   41,  125,   43,   46,   45,   46,
    0,  123,  257,   58,  271,  123,   61,  123,  271,  271,
  271,   58,  258,   60,   61,   62,  231,   58,  257,  125,
   40,  257,  257,  258,  257,  262,   46,  266,  267,  134,
  257,  258,  265,  271,  274,  275,  276,  277,   58,  273,
  255,   61,  125,  273,  269,  271,   46,  274,  275,  276,
  277,  257,  258,   46,  266,  267,   40,  271,   40,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  272,  257,  274,  275,  276,  277,  125,   46,
  264,  265,  259,  260,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  270,  271,  272,   40,  274,  275,  276,
  277,  257,  258,  269,   40,  269,  259,  260,  261,  262,
  263,  264,  265,  266,  267,  268,  269,  270,  271,  272,
  269,  274,  275,  276,  277,  125,  269,  269,  125,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  272,   46,  274,  275,  276,  277,  271,   43,
    0,   45,   46,  259,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,  271,  272,  271,  274,  275,
  276,  277,   43,  271,   45,   46,  259,  260,  261,  262,
  263,  264,  265,  266,  267,  268,  269,  270,  271,  272,
   40,  274,  275,  276,  277,    0,  258,   46,   46,   46,
   17,   46,  257,   46,  257,  258,   46,   40,   58,  264,
  265,   61,  259,  260,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  270,  271,  272,    0,  274,  275,  276,
  277,   43,   49,   45,   46,   40,   41,  257,   43,  259,
   45,   46,    0,  263,   17,  265,  266,  267,  268,  269,
  270,   40,  272,   58,   40,   60,   61,   62,    0,  259,
   40,  258,   40,  263,  257,   43,   40,   45,  268,  269,
  257,  262,  269,  262,    0,  125,   49,  270,   46,  272,
   46,  262,   40,  270,   58,  272,  262,   61,   58,   46,
   46,   46,  259,   46,  261,  262,  263,   46,   40,    0,
   58,  268,   46,   61,  271,   46,   46,   46,   46,   46,
  125,   46,  125,   46,   40,    0,   58,  134,  125,   61,
  125,  125,   42,   43,  125,   45,   46,   47,  125,  125,
   46,    0,   58,   46,   41,   61,   43,   46,   45,   40,
   46,  125,  125,  125,  125,    0,  125,   39,  125,  125,
  125,  125,   17,  125,   -1,   40,   -1,   58,   -1,   -1,
   61,  134,   -1,  257,  258,   -1,   -1,  125,   -1,   -1,
   -1,   40,   -1,   58,   41,   -1,   61,   46,   -1,   -1,
   -1,   -1,   -1,  125,   -1,   -1,  257,  258,   -1,   58,
    0,   -1,   61,   60,   -1,   62,   41,   -1,   43,  125,
   45,   46,   -1,   -1,   -1,   -1,   41,  257,   43,  259,
   45,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  271,  272,   -1,  125,   60,   -1,   62,   -1,   -1,
   40,   -1,   -1,    0,   -1,   -1,    3,   -1,   -1,   -1,
  125,   -1,   -1,   -1,   -1,  257,  258,   14,   58,   -1,
   -1,   61,   -1,   -1,  259,  260,  261,  262,  263,  264,
  265,  266,  267,  268,  269,  270,  271,  272,   -1,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,  257,   -1,  259,   -1,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,  271,  272,  257,
   -1,  259,   -1,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  271,  272,  125,   -1,  259,   -1,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  270,  271,
  272,  257,   -1,  259,   40,  261,  262,  263,  258,  265,
  266,  267,  268,  269,  270,  271,  272,   -1,   -1,   -1,
  257,  258,   58,   -1,   -1,   61,  257,   -1,  259,   -1,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,  270,
  271,  272,  257,   -1,  259,   -1,  261,  262,  263,   14,
  265,  266,  267,  268,  269,  270,  271,  272,  257,   -1,
  259,   -1,    1,  260,  263,   -1,  265,  266,  267,  268,
  269,  270,   11,  272,  171,  172,  173,  274,  275,  276,
  277,   41,  257,  258,   -1,    1,   -1,  123,   -1,   28,
   -1,   -1,   -1,   -1,   41,  260,   12,   -1,   -1,   41,
   60,   -1,   62,   -1,  201,  202,  203,  204,   24,  274,
  275,  276,  277,   60,   30,   62,   -1,  257,   60,  259,
   62,  261,  262,  263,   -1,  265,  266,  267,  268,  269,
  270,  271,  272,   -1,  231,   -1,  233,  102,   -1,   -1,
   41,   -1,   -1,  108,  109,  110,   41,   -1,   43,   -1,
   45,   46,   91,   -1,   70,  120,  121,  122,  255,   60,
   -1,   62,   -1,   -1,   -1,   31,   32,   -1,   -1,   35,
  135,   43,  137,   45,   -1,  140,  141,  142,   43,  144,
   45,   47,   48,   -1,   -1,   -1,   -1,   -1,   60,   -1,
   62,   57,   -1,   -1,   -1,   60,  161,   62,   40,  164,
   -1,  166,  167,   41,  169,   43,   -1,   45,   -1,   75,
   -1,   -1,   40,   -1,   -1,   -1,   58,   83,   84,   61,
   -1,  257,   -1,  259,   -1,   -1,   92,  263,  193,  265,
   58,   40,  268,   61,   -1,   -1,  201,   -1,  203,  204,
   -1,  180,   -1,  182,  183,  184,  185,  186,   -1,   58,
   40,   40,   61,   -1,   -1,   -1,   46,  123,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  231,   -1,   58,   58,
   40,   61,   61,   -1,  213,   -1,  215,   40,   -1,   -1,
   -1,  220,  221,  222,  223,  224,  202,   -1,   58,   -1,
  255,   61,   41,   -1,   43,   58,   45,   41,   61,   43,
  260,   45,   -1,   40,   41,  244,   43,   44,   45,   43,
   -1,   45,   46,  260,  274,  275,  276,  277,  260,   -1,
   -1,   58,   -1,   -1,   61,   -1,   -1,  274,  275,  276,
  277,   -1,  274,  275,  276,  277,   41,   -1,   43,   41,
   45,   43,   -1,   45,   -1,   41,  212,   43,  214,   45,
  216,  217,  218,  219,   41,   -1,   43,   -1,   45,  260,
  123,   -1,  257,  258,   41,   -1,   43,   41,   45,   43,
  236,   45,  238,  274,  275,  276,  277,  243,   -1,  245,
  246,  247,  248,   -1,   -1,  257,  258,   41,   -1,   43,
   -1,   45,  257,  258,   41,   -1,   43,   -1,   45,   -1,
  266,   -1,  274,  275,  276,  277,   -1,   -1,   -1,  274,
  275,  276,  277,   -1,   -1,  257,   -1,  259,   -1,  257,
  258,  263,   -1,  265,  266,  267,  268,   -1,  270,  257,
  272,  259,   -1,   -1,   -1,  263,   -1,  265,  266,  267,
  268,   -1,  270,   -1,  272,   -1,   -1,   -1,  257,   -1,
   -1,   -1,   -1,   -1,   -1,  264,  265,  266,  267,   -1,
   -1,  270,   -1,  272,   -1,   -1,   -1,  257,  257,  259,
  259,  261,  262,  263,  263,  265,  265,   -1,  268,  268,
   -1,   -1,   -1,   -1,   36,   -1,   -1,  257,   -1,  259,
  260,   -1,   -1,  263,  257,  265,  259,   -1,  268,   -1,
  263,   -1,  265,   -1,   -1,  268,   -1,   -1,  257,  258,
   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,  257,  258,   -1,   -1,   79,   80,  266,
  267,   -1,   -1,   85,   86,   87,   -1,   -1,   -1,   -1,
   -1,   93,   94,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,   -1,  257,  258,   -1,   -1,   -1,
   -1,  257,  258,   -1,  116,  117,   -1,   -1,   -1,   -1,
  257,  258,  124,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,  257,  258,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,   -1,   -1,   -1,   -1,   -1,
  257,  258,
};
}
final static short YYFINAL=13;
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
"programa : bloque_comun",
"bloque_comun : bloque_comun bloque_para_funcion",
"bloque_comun : bloque_comun declaracion_funcion",
"bloque_comun : bloque_para_funcion",
"bloque_comun : declaracion_funcion",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion END_IF '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"bloque_para_funcion : BEGIN sentencias END '.'",
"bloque_para_funcion : sentencias",
"bloque_para_funcion : BEGIN sentencias END",
"bloque_para_funcion : BEGIN sentencias '.'",
"bloque_para_funcion : sentencias END '.'",
"bloque_para_funcion : IF condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion THEN bloque_para_funcion ELSE bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF",
"bloque_para_funcion : IF '(' condicion ')' bloque_para_funcion ELSE bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF condicion ')' THEN bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion THEN bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion END_IF",
"bloque_para_funcion : IF '(' condicion ')' bloque_para_funcion END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_para_funcion '.'",
"bloque_para_funcion : SWITCH IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR '{' rep_switch '}' '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' rep_switch '}' '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' '}' '.'",
"sentencias : sentencias declaracion",
"sentencias : sentencias asignacion",
"sentencias : sentencias salida",
"sentencias : sentencias llamado_funcion",
"sentencias : declaracion",
"sentencias : asignacion",
"sentencias : salida",
"sentencias : llamado_funcion",
"declaracion_funcion : funcion",
"declaracion_funcion : sentencias funcion",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo FUNCTION IDENTIFICADOR bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.'",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '}'",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN expresion ')' '.' '}'",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion '.' '}'",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')' '.' '}'",
"funcion : tipo FUNCTION '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo MOVE IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"llamado_funcion : '(' ')' '.'",
"llamado_funcion : IDENTIFICADOR ')' '.'",
"llamado_funcion : IDENTIFICADOR '(' '.'",
"llamado_funcion : IDENTIFICADOR '(' ')'",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"asignacion : IDENTIFICADOR '=' expresion",
"asignacion : IDENTIFICADOR expresion '.'",
"asignacion : '=' expresion '.'",
"asignacion : IDENTIFICADOR '=' '.'",
"declaracion : IDENTIFICADOR ',' declaracion",
"declaracion : IDENTIFICADOR ':' tipo '.'",
"declaracion : IDENTIFICADOR declaracion",
"declaracion : IDENTIFICADOR ':' tipo",
"declaracion : IDENTIFICADOR tipo '.'",
"declaracion : IDENTIFICADOR ':' '.'",
"declaracion : ':' tipo '.'",
"rep_switch : CASE CONSTANTE ':' bloque_comun",
"rep_switch : rep_switch CASE CONSTANTE ':' bloque_comun",
"rep_switch : CONSTANTE ':' bloque_comun",
"rep_switch : CASE ':' bloque_comun",
"rep_switch : CASE CONSTANTE bloque_comun",
"condicion : condicion operador expresion",
"condicion : expresion operador termino",
"condicion : expresion termino",
"condicion : operador termino",
"condicion : expresion operador",
"operador : '<'",
"operador : '>'",
"operador : \"<=\"",
"operador : \">=\"",
"operador : \"<>\"",
"operador : \"==\"",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"expresion : expresion termino",
"expresion : '+' termino",
"expresion : expresion '+'",
"expresion : '-' termino",
"expresion : expresion '-'",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"termino : termino factor",
"termino : termino '*' factor",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : termino '/' factor",
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"salida : OUT '(' CADENA ')' '.'",
"salida : OUT '(' CADENA ')' '.'",
"salida : OUT CADENA ')' '.'",
"salida : OUT '(' CADENA '.'",
"salida : OUT '(' ')' '.'",
"salida : OUT '(' CADENA ')'",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 200 "gramaticaErrores.y"

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
	this.sintactico.showMessage(string);
	System.out.println(string);
}
//#line 707 "Parser.java"
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
case 6:
//#line 23 "gramaticaErrores.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 7:
//#line 24 "gramaticaErrores.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 8:
//#line 25 "gramaticaErrores.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 9:
//#line 26 "gramaticaErrores.y"
{this.sintactico.showMessage("Bloque: BEGIN - END");}
break;
case 11:
//#line 29 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en bloque BEGIN - END");}
break;
case 12:
//#line 30 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END' en bloque BEGIN - END");}
break;
case 13:
//#line 31 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'BEGIN' en bloque BEGIN - END");}
break;
case 14:
//#line 34 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
break;
case 15:
//#line 35 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
break;
case 16:
//#line 36 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF - ELSE");}
break;
case 17:
//#line 37 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
break;
case 18:
//#line 38 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF - ELSE");}
break;
case 19:
//#line 39 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
break;
case 20:
//#line 42 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
break;
case 21:
//#line 43 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
break;
case 22:
//#line 44 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF");}
break;
case 23:
//#line 45 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
break;
case 24:
//#line 46 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF");}
break;
case 25:
//#line 49 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia SWITCH");}
break;
case 26:
//#line 50 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia SWITCH");}
break;
case 27:
//#line 51 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en sentencia SWITCH");}
break;
case 28:
//#line 52 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en sentencia SWITCH");}
break;
case 29:
//#line 53 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia SWITCH");}
break;
case 30:
//#line 54 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH' en sentencia SWITCH");}
break;
case 31:
//#line 55 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en sentencia SWITCH");}
break;
case 32:
//#line 56 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE' en sentencia SWITCH");}
break;
case 43:
//#line 74 "gramaticaErrores.y"
{this.sintactico.showMessage("Declaracion de Funcion");}
break;
case 44:
//#line 75 "gramaticaErrores.y"
{this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
break;
case 45:
//#line 77 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion de funcion");}
break;
case 46:
//#line 78 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de funcion");}
break;
case 47:
//#line 79 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en declaracion de funcion");}
break;
case 48:
//#line 80 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion de funcion");}
break;
case 49:
//#line 81 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en declaracion de funcion");}
break;
case 50:
//#line 82 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en declaracion de funcion");}
break;
case 51:
//#line 83 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'RETURN' en declaracion de funcion");}
break;
case 52:
//#line 84 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de funcion");}
break;
case 53:
//#line 85 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de funcion");}
break;
case 54:
//#line 88 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion de funcion con MOVE");}
break;
case 55:
//#line 89 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de funcion con MOVE");}
break;
case 56:
//#line 90 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en declaracion de funcion con MOVE");}
break;
case 57:
//#line 91 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion de funcion con MOVE");}
break;
case 58:
//#line 92 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en declaracion de funcion con MOVE");}
break;
case 59:
//#line 93 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en declaracion de funcion con MOVE");}
break;
case 60:
//#line 94 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'RETURN' en declaracion de funcion con MOVE");}
break;
case 61:
//#line 95 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de funcion con MOVE");}
break;
case 62:
//#line 96 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de funcion con MOVE");}
break;
case 63:
//#line 100 "gramaticaErrores.y"
{this.sintactico.showMessage("Llamado a función");}
break;
case 64:
//#line 102 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en llamado a funcion");}
break;
case 65:
//#line 103 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a funcion");}
break;
case 66:
//#line 104 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a funcion");}
break;
case 67:
//#line 105 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en llamado a funcion");}
break;
case 68:
//#line 109 "gramaticaErrores.y"
{this.sintactico.showMessage("Asignación");}
break;
case 69:
//#line 111 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignación");}
break;
case 70:
//#line 112 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignación");}
break;
case 71:
//#line 113 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en asignación");}
break;
case 72:
//#line 114 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'EXPRESION' en asignación");}
break;
case 73:
//#line 118 "gramaticaErrores.y"
{this.sintactico.showMessage("Declaracion de variable multiple");}
break;
case 74:
//#line 119 "gramaticaErrores.y"
{this.sintactico.showMessage("Declaracion de variable");}
break;
case 75:
//#line 121 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion múltiple");}
break;
case 76:
//#line 124 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion");}
break;
case 77:
//#line 125 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en declaracion");}
break;
case 78:
//#line 126 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion");}
break;
case 79:
//#line 127 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion");}
break;
case 80:
//#line 131 "gramaticaErrores.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 82:
//#line 134 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE' en sentencia CASE");}
break;
case 83:
//#line 135 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE' en sentencia CASE");}
break;
case 84:
//#line 136 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en sentencia CASE");}
break;
case 86:
//#line 141 "gramaticaErrores.y"
{this.sintactico.showMessage("Condición");}
break;
case 87:
//#line 143 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 88:
//#line 144 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
break;
case 89:
//#line 145 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
break;
case 96:
//#line 157 "gramaticaErrores.y"
{this.sintactico.showMessage("Expresión");}
break;
case 97:
//#line 158 "gramaticaErrores.y"
{this.sintactico.showMessage("Expresión");}
break;
case 99:
//#line 161 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 100:
//#line 162 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
break;
case 101:
//#line 163 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
break;
case 102:
//#line 164 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
break;
case 103:
//#line 165 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
break;
case 104:
//#line 169 "gramaticaErrores.y"
{this.sintactico.showMessage("Término");}
break;
case 105:
//#line 170 "gramaticaErrores.y"
{this.sintactico.showMessage("Término");}
break;
case 107:
//#line 173 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 108:
//#line 174 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
break;
case 109:
//#line 175 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'factor'");}
break;
case 110:
//#line 176 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
break;
case 111:
//#line 177 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'factor'");}
break;
case 114:
//#line 185 "gramaticaErrores.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
case 115:
//#line 187 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'OUT' en sentencia OUT");}
break;
case 116:
//#line 188 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia OUT");}
break;
case 117:
//#line 189 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia OUT");}
break;
case 118:
//#line 190 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CADENA' en sentencia OUT");}
break;
case 119:
//#line 191 "gramaticaErrores.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia OUT");}
break;
//#line 1220 "Parser.java"
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
