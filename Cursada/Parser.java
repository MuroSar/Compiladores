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






//#line 2 "gramaticaCorregidaFinal.y"
package compilador;

import java.util.ArrayList;
import java.util.Stack;

import Tercetos.Terceto;
import Tercetos.TercetoSuma;
import Tercetos.TercetoResta;
import Tercetos.TercetoMultiplicacion;
import Tercetos.TercetoDivision;
import Tercetos.TercetoComparador;
import Tercetos.TercetoAsignacion;
import Tercetos.TercetoBFalse;
import Tercetos.TercetoBIncondicional;
import Tercetos.TercetoFuncion;
import Tercetos.TercetoOut;
import Tercetos.TercetoRet;
import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
//#line 38 "Parser.java"




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
    0,    1,    1,    1,    1,    2,    2,    6,    6,    7,
    7,    7,    7,    4,    4,    4,   10,   10,    9,    9,
   11,   11,   12,   12,    3,    3,   17,   17,    5,   22,
   13,   13,   13,   13,   13,   21,   23,   21,   21,   21,
   21,   25,   14,   14,   14,   14,   14,   24,   24,   24,
   24,   24,   27,   26,   28,   26,   26,   26,   26,   15,
   15,   15,   16,   16,   16,   16,   19,   19,   19,   20,
   20,   20,   29,   29,   29,   29,   29,   29,    8,    8,
    8,   30,   30,   30,   32,   32,   31,   31,   31,   18,
   18,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    7,    1,
    1,    2,    2,    1,    4,    4,    1,    2,    1,    1,
    1,    1,    1,    1,    2,    3,    3,    4,    4,    0,
    7,    6,    6,    6,    6,    3,    0,    6,    5,    3,
    3,    0,    6,    5,    5,    5,    5,    4,    4,    4,
    4,    4,    0,    5,    0,    6,    4,    4,    4,    4,
    4,    4,    5,    5,    5,    4,    1,    3,    2,    3,
    3,    2,    1,    1,    1,    1,    1,    1,    3,    3,
    1,    3,    3,    1,    3,    4,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   90,   91,    0,    0,    0,
    2,    3,    6,    7,   14,   19,   20,   21,   22,   23,
   24,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,    0,    0,    0,    0,    0,    4,    5,    0,    0,
    0,    0,   69,    0,    0,    0,   88,    0,    0,    0,
   84,   89,    0,    0,    0,    0,    0,    0,   18,    0,
    0,    0,    0,    0,    0,   11,    0,   10,   26,   27,
    0,    0,   68,    0,    0,   73,   74,   75,   76,   77,
   78,    0,    0,    0,    0,    0,    0,    0,    0,   61,
   62,   60,    0,    0,    0,   16,   15,    0,    0,    0,
    0,    0,    0,   42,    0,    0,   13,   12,   28,   29,
    0,   85,    0,    0,   44,    0,    0,    0,    0,    0,
   82,   83,    0,    0,    0,   30,   64,   65,   63,   45,
   46,   47,    0,    0,    0,   86,    0,    0,    0,    0,
    0,    0,   32,   33,   34,   35,    0,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   31,    0,    0,    0,    0,    0,    0,   55,   49,
   50,   51,   52,   48,   40,    0,   41,   36,    0,    9,
    0,   57,   58,   59,    0,    0,    0,    0,    8,   54,
    0,   39,    0,   56,   38,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,  142,   14,   40,   67,   48,   15,   32,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   49,
  143,  147,  161,  115,  133,  139,  168,  186,   87,   50,
   51,   52,
};
final static short yysindex[] = {                      -109,
   39,  -34,  -40,  -63,  -28,    0,    0,  -16,    0, -109,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   -8, -209,  -11, -207,  -91,  -91,  -91,  -91,  -34,
    0,  -94, -231, -182, -162,  -81,    0,    0, -132,   51,
 -156, -138,    0,  -89, -116,  139,    0,   78,   47,   18,
    0,    0,  113,   99,    3,   52,  -26,  -41,    0,  114,
  -25,  119,  132,  -23,  142,    0, -122,    0,    0,    0,
  -72,  145,    0,  -15,  -88,    0,    0,    0,    0,    0,
    0,  -91,  -91,   25,  -91,  -54,  -91,  -91,  -91,    0,
    0,    0,  -52,  -51, -171,    0,    0,  164,  165,  166,
  -88,  -88,  -88,    0,  -91,  174,    0,    0,    0,    0,
  215,    0, -225, -211,    0,   18,   18,   25,  -73,   25,
    0,    0,  -73,  -73,  -73,    0,    0,    0,    0,    0,
    0,    0,  -88,   65,  -91,    0,   13, -176, -100,  -86,
 -105, -184,    0,    0,    0,    0,  -73,    0,  229,   75,
  223,  231,   34,   33,  247,  248,  254,  -17,  -36,   -9,
   40,    0,  177,  257,  -73,  -73,  -73,  250,    0,    0,
    0,    0,    0,    0,    0,   54,    0,    0,  -73,    0,
  184,    0,    0,    0,  -73,  267,  284,   76,    0,    0,
  -73,    0,  291,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   -1,    0,    0,    0,    0,    0,    0,    0,  339,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,
    0,    0,    0,    0,    0,   57,    0,    0,    0,  -32,
    0,    0,  -39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -22,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   21,   30,   36,    0,   43,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   79,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  283,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  332,  333,   22,    2,    0,    0,   26,   55,    0,
    0,    0,    0,    0,    0,    0,    0,  300,    0,  169,
    5,    0,    0,  -47,    0,  232,    0,    0,  297,  117,
  115,    0,
};
final static int YYTABLESIZE=355;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   66,   87,   87,   87,   97,   87,   87,   87,   81,  175,
   81,   34,   81,   81,   95,  100,   25,  104,   72,  158,
   87,   13,   87,   36,  155,  112,   27,   81,  174,   81,
  137,   13,   45,   76,  114,   77,  178,   72,  156,   72,
   66,   60,   67,  138,  140,   82,   44,   83,   92,   46,
   47,   54,   55,  130,  131,  132,   67,  138,   31,   88,
   41,   79,   42,   79,   89,   79,   79,   82,  107,   83,
   80,  159,   80,   84,   80,   80,   71,  160,   25,  152,
   79,  153,   79,   70,  125,  148,   59,   86,  126,   80,
   61,   80,   93,   68,   62,   71,   69,   71,   87,   87,
   70,   87,   70,   87,   70,  149,   76,   82,   77,   83,
  118,   76,  120,   77,   39,  164,   87,   82,   87,   83,
   82,  108,   83,    1,    2,   66,    3,  144,  145,  146,
  134,   71,    5,    1,    2,    8,    3,   76,   65,   77,
   73,   82,    5,   83,   90,    8,    1,    2,  106,    3,
  157,  162,   74,    4,   98,    5,    6,    7,    8,  101,
  150,    1,   30,  154,    3,   53,   47,  113,  154,   58,
    5,  151,  102,    8,   63,   64,    6,    7,   74,   75,
  176,  105,    1,   30,  109,    3,  182,  183,  184,    4,
  110,    5,    1,   30,    8,    3,   56,   57,  116,  117,
  188,    5,  121,  122,    8,  119,  190,  123,  124,  127,
  128,  129,  194,  135,   96,   28,   87,   87,   87,    1,
   30,   26,    3,   81,   81,   81,    4,   33,    5,   94,
   99,    8,  103,   72,   87,   87,   87,   87,  173,   35,
  111,   81,   81,   81,   81,   43,  177,   78,   79,   80,
   81,   72,   72,   72,   72,   67,   66,   66,   91,   66,
  136,   66,   66,   66,   66,   66,   66,   66,   66,   66,
  151,   66,   25,   25,  163,   25,   79,   79,   79,   25,
  165,   25,   25,   25,   25,   80,   80,   80,  166,  167,
  169,   71,  170,  171,   79,   79,   79,   79,   70,  172,
  179,  180,  181,   80,   80,   80,   80,  185,  189,   71,
   71,   71,   71,   87,   87,  187,   70,   70,   70,   70,
   78,   79,   80,   81,  191,   78,   79,   80,   81,  192,
   87,   87,   87,   87,   53,   47,  195,  193,    1,   37,
   53,   37,   38,   72,   85,  141,    0,    0,    0,    0,
    0,   78,   79,   80,   81,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   42,   43,   46,   45,   46,   47,   41,   46,
   43,   40,   45,   46,   41,   41,    0,   41,   41,  125,
   60,    0,   62,   40,  125,   41,   61,   60,   46,   62,
  256,   10,   44,   60,  123,   62,   46,   60,  125,   62,
   39,  273,   44,  269,  256,   43,   58,   45,   46,  257,
  258,   26,   27,  101,  102,  103,   58,  269,    4,   42,
  270,   41,  272,   43,   47,   45,   46,   43,   67,   45,
   41,  256,   43,   48,   45,   46,   41,  262,   40,  256,
   60,  258,   62,   41,  256,  133,   32,   41,  260,   60,
  273,   62,   41,   39,  257,   60,   46,   62,   42,   43,
  257,   45,   60,   47,   62,   41,   60,   43,   62,   45,
   85,   60,   87,   62,  123,   41,   60,   43,   62,   45,
   43,   67,   45,  256,  257,  125,  259,  123,  124,  125,
  105,  270,  265,  256,  257,  268,  259,   60,  271,   62,
  257,   43,  265,   45,   46,  268,  256,  257,  271,  259,
  256,  147,   40,  263,   41,  265,  266,  267,  268,   41,
  135,  256,  257,  269,  259,  257,  258,  256,  269,  264,
  265,  258,   41,  268,  256,  257,  266,  267,   40,   41,
  159,   40,  256,  257,  257,  259,  165,  166,  167,  263,
   46,  265,  256,  257,  268,  259,   28,   29,   82,   83,
  179,  265,   88,   89,  268,  260,  185,  260,  260,   46,
   46,   46,  191,   40,  256,  256,  256,  257,  258,  256,
  257,  256,  259,  256,  257,  258,  263,  256,  265,  256,
  256,  268,  256,  256,  274,  275,  276,  277,  256,  256,
  256,  274,  275,  276,  277,  257,  256,  274,  275,  276,
  277,  274,  275,  276,  277,  257,  256,  257,  256,  259,
   46,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  258,  271,  256,  257,   46,  259,  256,  257,  258,  263,
   58,  265,  266,  267,  268,  256,  257,  258,   58,  256,
  258,  256,   46,   46,  274,  275,  276,  277,  256,   46,
  261,  125,   46,  274,  275,  276,  277,   58,  125,  274,
  275,  276,  277,  257,  258,  262,  274,  275,  276,  277,
  274,  275,  276,  277,   58,  274,  275,  276,  277,   46,
  274,  275,  276,  277,  257,  258,   46,  262,    0,  261,
   58,   10,   10,   44,   48,  114,   -1,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,
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
"programa : bloque",
"bloque : bloque_comun",
"bloque : declaracion_funcion",
"bloque : bloque bloque_comun",
"bloque : bloque declaracion_funcion",
"bloque_comun : bloque_control",
"bloque_comun : declaracion",
"bloque_funcion : '{' bloque_sentencias_funcion RETURN '(' expresion ')' '.' '}'",
"bloque_funcion : '{' RETURN '(' expresion ')' '.' '}'",
"bloque_sentencias_funcion : sentencias",
"bloque_sentencias_funcion : declaracion",
"bloque_sentencias_funcion : bloque_sentencias_funcion sentencias",
"bloque_sentencias_funcion : bloque_sentencias_funcion declaracion",
"bloque_control : sentencias",
"bloque_control : BEGIN bloque_sentencias END '.'",
"bloque_control : BEGIN bloque_sentencias END error",
"bloque_sentencias : sentencias",
"bloque_sentencias : bloque_sentencias sentencias",
"sentencias : sentencia_unica_control",
"sentencias : sentencia_unica_ejecutable",
"sentencia_unica_control : sentencia_if",
"sentencia_unica_control : sentencia_switch",
"sentencia_unica_ejecutable : asignacion",
"sentencia_unica_ejecutable : salida",
"declaracion_funcion : encabezado_funcion bloque_funcion",
"declaracion_funcion : encabezado_funcion bloque_funcion '.'",
"encabezado_funcion : tipo FUNCTION IDENTIFICADOR",
"encabezado_funcion : tipo MOVE FUNCTION IDENTIFICADOR",
"declaracion : lista_variables ':' tipo '.'",
"$$1 :",
"sentencia_if : IF '(' condicion ')' THEN $$1 cuerpo_if",
"sentencia_if : error '(' condicion ')' THEN cuerpo_if",
"sentencia_if : IF error condicion ')' THEN cuerpo_if",
"sentencia_if : IF '(' condicion error THEN cuerpo_if",
"sentencia_if : IF '(' condicion ')' error cuerpo_if",
"cuerpo_if : bloque_control END_IF '.'",
"$$2 :",
"cuerpo_if : bloque_control $$2 ELSE bloque_control END_IF '.'",
"cuerpo_if : bloque_control error bloque_control END_IF '.'",
"cuerpo_if : bloque_control error '.'",
"cuerpo_if : bloque_control END_IF error",
"$$3 :",
"sentencia_switch : SWITCH '(' IDENTIFICADOR ')' $$3 cuerpo_switch",
"sentencia_switch : error '(' IDENTIFICADOR ')' cuerpo_switch",
"sentencia_switch : SWITCH error IDENTIFICADOR ')' cuerpo_switch",
"sentencia_switch : SWITCH '(' error ')' cuerpo_switch",
"sentencia_switch : SWITCH '(' IDENTIFICADOR error cuerpo_switch",
"cuerpo_switch : '{' rep_switch '}' '.'",
"cuerpo_switch : error rep_switch '}' '.'",
"cuerpo_switch : '{' error '}' '.'",
"cuerpo_switch : '{' rep_switch error '.'",
"cuerpo_switch : '{' rep_switch '}' error",
"$$4 :",
"rep_switch : CASE CONSTANTE $$4 ':' bloque_control",
"$$5 :",
"rep_switch : rep_switch CASE CONSTANTE $$5 ':' bloque_control",
"rep_switch : error CONSTANTE ':' bloque_control",
"rep_switch : CASE error ':' bloque_control",
"rep_switch : CASE CONSTANTE error bloque_control",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"asignacion : IDENTIFICADOR error expresion '.'",
"asignacion : IDENTIFICADOR '=' expresion error",
"salida : OUT '(' CADENA ')' '.'",
"salida : OUT error CADENA ')' '.'",
"salida : OUT '(' CADENA error '.'",
"salida : OUT '(' CADENA ')'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"lista_variables : lista_variables IDENTIFICADOR",
"condicion : condicion operador expresion",
"condicion : expresion operador expresion",
"condicion : expresion expresion",
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
"llamado_funcion : IDENTIFICADOR '(' ')'",
"llamado_funcion : IDENTIFICADOR '(' error '.'",
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"factor : llamado_funcion",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 524 "gramaticaCorregidaFinal.y"

private Lexico lexico;
private Sintactico sintactico;
private Token token;
private Stack<String> funcionActual = new Stack<String>();
private Terceto tercetoAux;

public void setLexico(Lexico lexico)
{
	this.lexico = lexico;
}
public void setSintactico(Sintactico sintactico) {
	this.sintactico = sintactico;	
}
private int yylex() {
	this.token = lexico.getToken();
	
	yylval = new ParserVal(token.getLexema());
	
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
//#line 466 "Parser.java"
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
case 8:
//#line 42 "gramaticaCorregidaFinal.y"
{	Terceto ret = new TercetoRet("RET", val_peek(3), null, this.sintactico.getTercetos().size(), this.funcionActual.pop(), this.sintactico.getGenerador());
												   								if(this.sintactico.getMarcaAntes()){
																					ret.setMarcaAntes(true);
																					this.sintactico.setMarcaAntes(false);
																				}
												   								this.sintactico.addTerceto(ret);
												   								this.sintactico.addTercetoFuncion(ret);
																			   }
break;
case 9:
//#line 50 "gramaticaCorregidaFinal.y"
{ this.sintactico.showError("ERROR Linea "+ token.getLinea() +": El cuerpo de una funci\u00f3n no puede estar vac\u00EDo");
											   }
break;
case 15:
//#line 60 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Bloque BEGIN-END");}
break;
case 16:
//#line 62 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 25:
//#line 82 "gramaticaCorregidaFinal.y"
{this.sintactico.decrementarAmbito();
														 this.sintactico.setFnMOVE(false);}
break;
case 26:
//#line 85 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Sobra '.'");}
break;
case 27:
//#line 89 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   
												   this.sintactico.setMarcaAntes(true);
												   this.sintactico.addNombreMarca(val_peek(0).sval);
												   this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);												   
												   
												   this.sintactico.actualizaFuncion(val_peek(0), val_peek(2), true);
												   this.sintactico.aumentarAmbito(val_peek(0));
												   this.funcionActual.push(val_peek(0).sval);}
break;
case 28:
//#line 99 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 
											 this.sintactico.setMarcaAntes(true);
											 this.sintactico.addNombreMarca(val_peek(0).sval);
											 this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);
											 
											 this.sintactico.actualizaFuncion(val_peek(0), val_peek(3), true);
											 this.sintactico.aumentarAmbito(val_peek(0));
											 this.sintactico.setFnMOVE(true);
											 this.funcionActual.push(val_peek(0).sval);}
break;
case 29:
//#line 111 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1), true);}
break;
case 30:
//#line 115 "gramaticaCorregidaFinal.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
										   this.sintactico.addTerceto(bFalse);
										   if(!this.sintactico.getAmbito().equals("A")){
										   	   this.sintactico.addTercetoFuncion(bFalse);
										   }
		               					   this.sintactico.pilaPush(bFalse);
		                            	  }
break;
case 31:
//#line 122 "gramaticaCorregidaFinal.y"
{this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
break;
case 32:
//#line 125 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
break;
case 33:
//#line 126 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 34:
//#line 127 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 35:
//#line 128 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
break;
case 36:
//#line 132 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
			   						  }
break;
case 37:
//#line 136 "gramaticaCorregidaFinal.y"
{	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
							this.sintactico.addTerceto(bIncondicional);
							if(!this.sintactico.getAmbito().equals("A")){
						   	   this.sintactico.addTercetoFuncion(bIncondicional);
						    } 
							Terceto bFalse = this.sintactico.pilaPop();
							this.sintactico.pilaPush(bIncondicional);
							bFalse.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
							this.sintactico.setMarcaAntes(true);
						 }
break;
case 38:
//#line 146 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  }
break;
case 39:
//#line 151 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
break;
case 40:
//#line 152 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
break;
case 41:
//#line 153 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 42:
//#line 158 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(1), false))
 												  {
													  this.sintactico.showMessage("Sentencia: SWITCH");
													  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
										  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1, this.sintactico.getGenerador());
													  this.tercetoAux = bFalse;
								               		  this.sintactico.pilaPush(bFalse);
								               		  this.sintactico.setIDSwitch(val_peek(1).sval);
								               	  }
								               	  else
								               	  {
								               	  	  this.sintactico.addError("variable", val_peek(1));
								               	  }
							               		}
break;
case 43:
//#line 171 "gramaticaCorregidaFinal.y"
{this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
break;
case 44:
//#line 173 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
break;
case 45:
//#line 174 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 46:
//#line 175 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
break;
case 47:
//#line 176 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 49:
//#line 182 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
break;
case 50:
//#line 183 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 51:
//#line 184 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
break;
case 52:
//#line 185 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 53:
//#line 189 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), val_peek(0)) != null) {
								  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
								  this.sintactico.addTerceto(comp);
								  if(!this.sintactico.getAmbito().equals("A")){
							   	  	  this.sintactico.addTercetoFuncion(comp);
							      } 
								  this.sintactico.addTerceto(tercetoAux);
							  }
							  else
							  {
								  this.sintactico.addError("tipos", val_peek(0));
							  }
							}
break;
case 54:
//#line 202 "gramaticaCorregidaFinal.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
									  		   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1, this.sintactico.getGenerador());
											   this.tercetoAux = bFalse;
											   Terceto bFalse2 = this.sintactico.pilaPop();
											   this.sintactico.pilaPush(bFalse);
											   bFalse2.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
										   	 }
break;
case 55:
//#line 209 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), val_peek(0)) != null) {
										  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
										  this.sintactico.addTerceto(comp);
										  if(!this.sintactico.getAmbito().equals("A")){
									   	  	  this.sintactico.addTercetoFuncion(comp);
									      } 
										  this.sintactico.addTerceto(tercetoAux);
										  if(!this.sintactico.getAmbito().equals("A")){
									   	  	  this.sintactico.addTercetoFuncion(tercetoAux);
									      } 
									  }
							  		  else
							  		  {
									  	  this.sintactico.addError("tipos", val_peek(0));
								  	  }
									}
break;
case 56:
//#line 225 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bFalse3 = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(tercetoAux);
														  bFalse3.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											  		    }
break;
case 57:
//#line 231 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 58:
//#line 232 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
break;
case 59:
//#line 233 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
break;
case 60:
//#line 237 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Asignaci\u00f3n");

											if(this.sintactico.existeVariable(val_peek(3), false))
 											{
 												if(this.sintactico.existeVariable(val_peek(1), false))
 												{
		 											if(this.sintactico.ambitoCorrecto(val_peek(3), val_peek(1))) {
		 												if(this.sintactico.mismoTipo(val_peek(3), val_peek(1)) != null) {
		 													Terceto t =  new TercetoAsignacion(val_peek(3), val_peek(1), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
		 													if(this.sintactico.getMarcaAntes()){
		 														t.setMarcaAntes(true);
		 														this.sintactico.setMarcaAntes(false);
		 													}
		 													yyval = new ParserVal(t);
															this.sintactico.addTerceto(t);
															if(!this.sintactico.getAmbito().equals("A")){
													   	  	    this.sintactico.addTercetoFuncion(t);
													        }
														}
														else
														{
															this.sintactico.addError("tipos", val_peek(3));
														}
													}
													else {
														this.sintactico.addError("ambito", val_peek(1));
													}
												}
												else
 												{
 												this.sintactico.addError("variable", val_peek(1));
 												}
 											}
 											else
 											{
 												this.sintactico.addError("variable", val_peek(3));
 											}}
break;
case 61:
//#line 275 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 62:
//#line 276 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignacion");}
break;
case 63:
//#line 280 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut(val_peek(2), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
							   	 if(this.sintactico.getMarcaAntes()){
								 	 t.setMarcaAntes(true);
								 	 this.sintactico.setMarcaAntes(false);
								 }
							   	 yyval = new ParserVal(t);
								 this.sintactico.addTerceto(t);
								 if(!this.sintactico.getAmbito().equals("A")){
						   	  	     this.sintactico.addTercetoFuncion(t);
						         }
							   }
break;
case 64:
//#line 293 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
break;
case 65:
//#line 294 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
break;
case 66:
//#line 295 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
break;
case 67:
//#line 299 "gramaticaCorregidaFinal.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 68:
//#line 302 "gramaticaCorregidaFinal.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 69:
//#line 306 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
break;
case 71:
//#line 311 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Condici\u00f3n");
										if(this.sintactico.existeVariable(val_peek(2), false)){
									     	if(this.sintactico.existeVariable(val_peek(0), false)){
									     		if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
										     		if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
														Terceto t =  new TercetoComparador(val_peek(1), val_peek(2), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
														if(this.sintactico.getMarcaAntes()){
															t.setMarcaAntes(true);
															this.sintactico.setMarcaAntes(false);
														}
														yyval = new ParserVal(t);
														this.sintactico.addTerceto(t);
														if(!this.sintactico.getAmbito().equals("A")){
												   	  	    this.sintactico.addTercetoFuncion(t);
												        }
													}
													else {
														this.sintactico.addError("tipos", val_peek(2));
													}
												}
												else {
													this.sintactico.addError("ambito", val_peek(0));
												}
												
											}
											else {
												this.sintactico.addError("variable", val_peek(0));
											}
										}
										else {
											this.sintactico.addError("variable", val_peek(2));
										}}
break;
case 72:
//#line 344 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
break;
case 79:
//#line 356 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(2), false)){
										if(this.sintactico.existeVariable(val_peek(0), false)){
											if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {	
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoSuma(val_peek(2), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
													this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
													if(this.sintactico.getMarcaAntes()){
														t.setMarcaAntes(true);
														this.sintactico.setMarcaAntes(false);
													}
													yyval = new ParserVal(t);
													this.sintactico.addTerceto(t);
													if(!this.sintactico.getAmbito().equals("A")){
											   	  	    this.sintactico.addTercetoFuncion(t);
											        }
												}
												else {
													this.sintactico.addError("tipos", val_peek(2));
												}
											}
											else {
												this.sintactico.addError("ambito", val_peek(0));
											}
										}
										else {
											this.sintactico.addError("variable", val_peek(0));
										}
									}
									else {
										this.sintactico.addError("variable", val_peek(2));
									}}
break;
case 80:
//#line 388 "gramaticaCorregidaFinal.y"
{ 	if(this.sintactico.existeVariable(val_peek(2), false)){
										if(this.sintactico.existeVariable(val_peek(0), false)){
		 									if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoResta(val_peek(2), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
													this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
													if(this.sintactico.getMarcaAntes()){
														t.setMarcaAntes(true);
														this.sintactico.setMarcaAntes(false);
													}
													yyval = new ParserVal(t);
													this.sintactico.addTerceto(t);
													if(!this.sintactico.getAmbito().equals("A")){
											   	  	    this.sintactico.addTercetoFuncion(t);
											        }
												}
												else {
													this.sintactico.addError("tipos", val_peek(2));
												}
											}
											else {
												this.sintactico.addError("ambito", val_peek(0));
											}
										}
										else {
											this.sintactico.addError("variable", val_peek(0));
										}
									}
									else {
										this.sintactico.addError("variable", val_peek(2));
									}}
break;
case 82:
//#line 422 "gramaticaCorregidaFinal.y"
{ 	if(this.sintactico.existeVariable(val_peek(2), false)){
									if(this.sintactico.existeVariable(val_peek(0), false)){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
											if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoMultiplicacion(val_peek(2), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
												this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
											}
											else {
												this.sintactico.addError("tipos", val_peek(2));
											}
										}
										else {
											this.sintactico.addError("ambito", val_peek(0));
										}
									}
									else {
										this.sintactico.addError("variable", val_peek(0));
									}
								}
								else {
									this.sintactico.addError("variable", val_peek(2));
								}}
break;
case 83:
//#line 454 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(2), false)){
									if(this.sintactico.existeVariable(val_peek(0), false)){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 									if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoDivision(val_peek(2), val_peek(0), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
												this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
											}
											else {
												this.sintactico.addError("tipos", val_peek(2));
											}
										}
										else {
											this.sintactico.addError("ambito", val_peek(0));
										}
									}
									else {
										this.sintactico.addError("variable", val_peek(0));
									}
								}
								else {
									this.sintactico.addError("variable", val_peek(2));
								}}
break;
case 85:
//#line 488 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion(val_peek(2), false))
 											{
 												Terceto t =  new TercetoFuncion(val_peek(2), this.sintactico.getTercetos().size(), this.sintactico.getGenerador());
 												t.setSegundo("[" + Integer.valueOf(this.sintactico.getTercetos().size()+1) + "]");
 												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
 												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
 											}
 											else
 											{
 												this.sintactico.addError("funcion", val_peek(2));
 											}}
break;
case 86:
//#line 508 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
//#line 1157 "Parser.java"
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
