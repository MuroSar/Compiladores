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






//#line 2 "gramaticaCorregidaBarbie.y"
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
import Tercetos.TercetoEtiqueta;
import Tercetos.TercetoRet;
import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
//#line 39 "Parser.java"




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
    0,    1,    1,    1,    1,    2,    2,    6,    7,    7,
    7,    7,    4,    4,   10,   10,    9,    9,   11,   11,
   12,   12,    3,   17,   17,    5,   22,   13,   13,   13,
   13,   13,   21,   23,   21,   21,   21,   21,   25,   14,
   14,   14,   14,   14,   24,   24,   24,   24,   24,   26,
   26,   26,   26,   26,   15,   15,   16,   16,   16,   16,
   19,   19,   19,   20,   20,   20,   27,   27,   27,   27,
   27,   27,    8,    8,    8,   28,   28,   28,   30,   30,
   29,   29,   29,   18,   18,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    1,    1,
    2,    2,    1,    4,    1,    2,    1,    1,    1,    1,
    1,    1,    2,    3,    4,    4,    0,    7,    6,    6,
    6,    6,    3,    0,    6,    5,    3,    3,    0,    6,
    5,    5,    5,    5,    4,    4,    4,    4,    4,    4,
    5,    4,    4,    4,    4,    4,    5,    5,    5,    4,
    1,    3,    2,    3,    3,    2,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    1,    3,    4,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   84,   85,    0,    0,    0,
    2,    3,    6,    7,   13,   17,   18,   19,   20,   21,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   15,    0,    0,    0,    0,    0,    4,    5,    0,   23,
    0,    0,   63,    0,    0,    0,   82,    0,    0,    0,
   78,   83,    0,    0,    0,    0,    0,    0,   16,    0,
    0,    0,    0,    0,   10,    0,    9,   24,    0,    0,
   62,    0,    0,   67,   68,   69,   70,   71,   72,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   55,    0,
    0,    0,   14,    0,    0,    0,    0,    0,    0,   39,
    0,   12,   11,   25,   26,    0,   79,    0,    0,   41,
    0,    0,    0,    0,    0,   76,   77,    0,    0,    0,
   27,   58,   59,   57,   42,   43,   44,    0,    0,   80,
    0,    0,    0,    0,    0,    0,   29,   30,   31,   32,
    0,   40,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   28,    0,    0,    0,    0,    0,
    0,   46,   47,   48,   49,   45,   37,    0,   38,   33,
    0,    0,   52,   53,   54,   50,    0,    0,    0,    8,
   51,   36,    0,   35,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,  136,   14,   40,   66,   48,   15,   32,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   49,
  137,  141,  154,  110,  128,  133,   85,   50,   51,   52,
};
final static short yysindex[] = {                      -117,
   -3,  -34,  -40,  -90,  -35,    0,    0,  -28,    0, -117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -31, -174,  -11, -213, -183, -183, -183, -183,  -34,
    0, -104, -173, -162, -191, -139,    0,    0,  -59,    0,
 -138, -142,    0,  -79, -126,  122,    0,   65,   23,   12,
    0,    0,   95,   78,   91,   35,    5,   99,    0,  106,
  -23,  113,  115,  -17,    0, -127,    0,    0,  -92,  112,
    0,  -16,  -88,    0,    0,    0,    0,    0,    0, -183,
 -183,   61, -183,  -75, -183, -183, -183,    0,    0,  -71,
  -69, -166,    0,  126,  150,  159,  -88,  -88,  -88,    0,
  154,    0,    0,    0,    0,  161,    0, -208, -187,    0,
   12,   12,   61,  -73,   61,    0,    0,  -73,  -73,  -73,
    0,    0,    0,    0,    0,    0,    0,  -88, -183,    0,
  -50, -144,  -96,  -87,  -99, -207,    0,    0,    0,    0,
  -73,    0,   43,  152,  153,  -42,  -46,  167,  169,  177,
  -36,   87,  -15,  -27,    0,  181,  -73,  -73,  -73,  -73,
  171,    0,    0,    0,    0,    0,    0,   -9,    0,    0,
  -73,  129,    0,    0,    0,    0,  -73,  201,   -7,    0,
    0,    0,  210,    0,
};
final static short yyrindex[] = {                         0,
    0,   -5,    0,    0,    0,    0,    0,    0,    0,  259,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   60,    0,    0,    0,  -32,
    0,    0,  -39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -26,   17,   31,    0,   39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   10,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  266,  267,   22,    4,    0,    0,   30,   77,    0,
    0,    0,    0,    0,    0,    0,    0,  234,    0,  148,
  -68,    0,    0,  -57,    0,  175,  237,  121,  117,    0,
};
final static int YYTABLESIZE=355;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   60,   81,   81,   81,   34,   81,   81,   81,   75,  166,
   75,   36,   75,   75,   73,  160,   73,   96,   73,   73,
   81,   13,   81,  100,  107,  151,   27,   75,  148,   75,
  170,   13,   45,   73,  109,   73,   25,  149,   61,  125,
  126,  127,   65,   46,   47,   92,   44,  131,  152,  138,
  139,  140,   61,   86,  153,   54,   55,   74,   87,   74,
  132,   74,   74,   84,   74,   62,   75,   66,  134,  102,
  142,   65,  155,   53,   47,   90,   74,   82,   74,   64,
   31,  132,   74,  156,   75,   80,   66,   81,   66,  120,
   65,   39,   65,  121,   74,   41,   75,   42,   64,   60,
   64,   81,   81,   80,   81,   81,   81,   80,   59,   81,
   61,  145,  113,  146,  115,   67,   63,   64,   68,   81,
   80,   81,   81,   88,   74,   60,   75,   69,    1,    2,
   71,    3,  167,   80,   72,   81,   89,    5,    1,    2,
    8,    3,  103,  101,   93,    4,   94,    5,    6,    7,
    8,    1,   30,   97,    3,   98,  150,  105,  143,   58,
    5,   72,   73,    8,  104,    1,   30,  108,    3,  147,
  144,  122,  147,  168,    5,   56,   57,    8,  173,  174,
  175,  176,    1,   30,  114,    3,    6,    7,  118,    4,
  119,    5,  179,  129,    8,  123,    1,    2,  181,    3,
  111,  112,  116,  117,  124,    5,  130,  144,    8,  157,
  158,  161,  162,  159,  163,   28,   81,   81,   81,  165,
   33,   26,  164,   75,   75,   75,  172,   35,  177,   73,
   73,   73,   95,  171,   81,   81,   81,   81,   99,  106,
  169,   75,   75,   75,   75,   43,  182,   73,   73,   73,
   73,   61,  178,  180,  183,  184,   60,   60,    1,   60,
   91,   60,   60,   60,   60,   60,   60,   60,   60,   60,
   34,   60,   74,   74,   74,   37,   38,   70,   76,   77,
   78,   79,   66,  135,   83,    0,   65,    0,    0,    0,
   74,   74,   74,   74,   64,    0,   76,   77,   78,   79,
   66,   66,   66,   66,   65,   65,   65,   65,   76,   77,
   78,   79,   64,   64,   64,   64,   81,   81,    0,    0,
    0,   53,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   81,   81,   81,   81,    0,   76,   77,
   78,   79,    1,   30,    0,    3,    0,    0,    0,    4,
    0,    5,    0,    0,    8,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   42,   43,   40,   45,   46,   47,   41,   46,
   43,   40,   45,   46,   41,   58,   43,   41,   45,   46,
   60,    0,   62,   41,   41,  125,   61,   60,  125,   62,
   46,   10,   44,   60,  123,   62,   40,  125,   44,   97,
   98,   99,   39,  257,  258,   41,   58,  256,  256,  118,
  119,  120,   58,   42,  262,   26,   27,   41,   47,   43,
  269,   45,   46,   41,   60,  257,   62,   41,  256,   66,
  128,   41,  141,  257,  258,   41,   60,   48,   62,   41,
    4,  269,   60,   41,   62,   43,   60,   45,   62,  256,
   60,  123,   62,  260,   60,  270,   62,  272,   60,  273,
   62,   42,   43,   43,   45,   45,   47,   43,   32,   45,
  273,  256,   83,  258,   85,   39,  256,  257,  257,   60,
   43,   62,   45,   46,   60,  125,   62,  270,  256,  257,
  257,  259,   46,   43,   40,   45,   46,  265,  256,  257,
  268,  259,   66,  271,   46,  263,   41,  265,  266,  267,
  268,  256,  257,   41,  259,   41,  256,   46,  129,  264,
  265,   40,   41,  268,  257,  256,  257,  256,  259,  269,
  258,   46,  269,  152,  265,   28,   29,  268,  157,  158,
  159,  160,  256,  257,  260,  259,  266,  267,  260,  263,
  260,  265,  171,   40,  268,   46,  256,  257,  177,  259,
   80,   81,   86,   87,   46,  265,   46,  258,  268,   58,
   58,  258,   46,  256,   46,  256,  256,  257,  258,  256,
  256,  256,   46,  256,  257,  258,   46,  256,   58,  256,
  257,  258,  256,  261,  274,  275,  276,  277,  256,  256,
  256,  274,  275,  276,  277,  257,   46,  274,  275,  276,
  277,  257,  262,  125,  262,   46,  256,  257,    0,  259,
  256,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  261,  271,  256,  257,  258,   10,   10,   44,  274,  275,
  276,  277,  256,  109,   48,   -1,  256,   -1,   -1,   -1,
  274,  275,  276,  277,  256,   -1,  274,  275,  276,  277,
  274,  275,  276,  277,  274,  275,  276,  277,  274,  275,
  276,  277,  274,  275,  276,  277,  257,  258,   -1,   -1,
   -1,  257,  258,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,   -1,  274,  275,
  276,  277,  256,  257,   -1,  259,   -1,   -1,   -1,  263,
   -1,  265,   -1,   -1,  268,
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
"bloque_sentencias_funcion : sentencias",
"bloque_sentencias_funcion : declaracion",
"bloque_sentencias_funcion : bloque_sentencias_funcion sentencias",
"bloque_sentencias_funcion : bloque_sentencias_funcion declaracion",
"bloque_control : sentencias",
"bloque_control : BEGIN bloque_sentencias END '.'",
"bloque_sentencias : sentencias",
"bloque_sentencias : bloque_sentencias sentencias",
"sentencias : sentencia_unica_control",
"sentencias : sentencia_unica_ejecutable",
"sentencia_unica_control : sentencia_if",
"sentencia_unica_control : sentencia_switch",
"sentencia_unica_ejecutable : asignacion",
"sentencia_unica_ejecutable : salida",
"declaracion_funcion : encabezado_funcion bloque_funcion",
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
"rep_switch : CASE CONSTANTE ':' bloque_control",
"rep_switch : rep_switch CASE CONSTANTE ':' bloque_control",
"rep_switch : error CONSTANTE ':' bloque_control",
"rep_switch : CASE error ':' bloque_control",
"rep_switch : CASE CONSTANTE error bloque_control",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"asignacion : IDENTIFICADOR error expresion '.'",
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

//#line 422 "gramaticaCorregidaBarbie.y"

private Lexico lexico;
private Sintactico sintactico;
private Token token;
private Stack<String> funcionActual = new Stack<String>();

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
//#line 453 "Parser.java"
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
//#line 43 "gramaticaCorregidaBarbie.y"
{	Terceto ret = new TercetoRet("RET", val_peek(3), null, this.sintactico.getTercetos().size(), this.funcionActual.pop());
												   								ret.setMarcaDesp(true);
												   								this.sintactico.addTerceto(ret);
																			   }
break;
case 14:
//#line 55 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Bloque BEGIN-END");}
break;
case 23:
//#line 74 "gramaticaCorregidaBarbie.y"
{this.sintactico.decrementarAmbito();
														 this.sintactico.setFnMOVE(false);}
break;
case 24:
//#line 78 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   
												   this.sintactico.setMarcaAntes(true);
												   /*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
												   /*etiqueta.setPrimero($3.sval);*/
												   /*this.sintactico.addTerceto(etiqueta);*/
												   /*this.sintactico.funcionPosPut($3, etiqueta.getPrimero());*/
												   this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);												   
												   
												   this.sintactico.actualizaFuncion(val_peek(0), val_peek(2));
												   this.sintactico.aumentarAmbito(val_peek(0));
												   this.funcionActual.push(val_peek(0).sval);}
break;
case 25:
//#line 91 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 
											 this.sintactico.setMarcaAntes(true);
											 /*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
											 /*etiqueta.setPrimero($4.sval);*/
											 /*this.sintactico.addTerceto(etiqueta);*/
											 /*this.sintactico.funcionPosPut($4, etiqueta.getPrimero());*/
											 this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);
											 
											 this.sintactico.actualizaFuncion(val_peek(0), val_peek(3));
											 this.sintactico.aumentarAmbito(val_peek(0));
											 this.sintactico.setFnMOVE(true);
											 this.funcionActual.push(val_peek(0).sval);}
break;
case 26:
//#line 106 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1));}
break;
case 27:
//#line 110 "gramaticaCorregidaBarbie.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+2);
									  	   bFalse.setMarcaDesp(true);
										   this.sintactico.addTerceto(bFalse);
		               					   this.sintactico.pilaPush(bFalse);
		                            	  }
break;
case 29:
//#line 118 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
break;
case 30:
//#line 119 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 31:
//#line 120 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 32:
//#line 121 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
break;
case 33:
//#line 125 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
										this.sintactico.setMarcaAntes(true);
										/*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
										/*etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());*/
										/*this.sintactico.addTerceto(etiqueta);*/
			   						  }
break;
case 34:
//#line 133 "gramaticaCorregidaBarbie.y"
{	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
							this.sintactico.addTerceto(bIncondicional); 
							Terceto bFalse = this.sintactico.pilaPop();
							this.sintactico.pilaPush(bIncondicional);
							bFalse.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
							this.sintactico.setMarcaAntes(true);
							/*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
							/*etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());*/
							/*this.sintactico.addTerceto(etiqueta);*/
						 }
break;
case 35:
//#line 143 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  	this.sintactico.setMarcaAntes(true);
									  	/*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
										/*etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());*/
										/*this.sintactico.addTerceto(etiqueta);*/
									  }
break;
case 36:
//#line 152 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
break;
case 37:
//#line 153 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
break;
case 38:
//#line 154 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 39:
//#line 159 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: SWITCH");
												  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+2);
												  this.sintactico.addTerceto(bFalse);
							               		  this.sintactico.pilaPush(bFalse);
							               		  this.sintactico.setMarcaAntes(true);
							               		}
break;
case 41:
//#line 167 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
break;
case 42:
//#line 168 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 43:
//#line 169 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
break;
case 44:
//#line 170 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 45:
//#line 174 "gramaticaCorregidaBarbie.y"
{ Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
		                               	bInconditional.setMarcaDesp(true);
									  }
break;
case 46:
//#line 179 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
break;
case 47:
//#line 180 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 48:
//#line 181 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
break;
case 49:
//#line 182 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 50:
//#line 186 "gramaticaCorregidaBarbie.y"
{ Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
												 this.sintactico.addTerceto(bIncondicional); 
												 Terceto bFalse = this.sintactico.pilaPop();
												 this.sintactico.pilaPush(bIncondicional);
												 bFalse.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											   }
break;
case 51:
//#line 192 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
														  this.sintactico.addTerceto(bIncondicional); 
														  Terceto bIncon = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(bIncondicional);
														  bIncon.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											  		    }
break;
case 52:
//#line 200 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 53:
//#line 201 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
break;
case 54:
//#line 202 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
break;
case 55:
//#line 206 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Asignaci\u00f3n");

											if(this.sintactico.existeVariable(val_peek(3)))
 											{
	 											if(this.sintactico.ambitoCorrecto(val_peek(3), val_peek(1))) {
	 												if(this.sintactico.mismoTipo(val_peek(3), val_peek(1)) != null) {
	 													Terceto t =  new TercetoAsignacion(val_peek(3), val_peek(1), this.sintactico.getTercetos().size());
	 													yyval = new ParserVal(t);
														this.sintactico.addTerceto(t);
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
 												this.sintactico.addError("variable", val_peek(3));
 											}}
break;
case 56:
//#line 230 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 57:
//#line 235 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut(val_peek(2), this.sintactico.getTercetos().size());
							   	 yyval = new ParserVal(t);
								 this.sintactico.addTerceto(t);
							   }
break;
case 58:
//#line 241 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
break;
case 59:
//#line 242 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
break;
case 60:
//#line 243 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
break;
case 61:
//#line 247 "gramaticaCorregidaBarbie.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 62:
//#line 250 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 63:
//#line 254 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
break;
case 65:
//#line 259 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Condici\u00f3n");
										if(this.sintactico.existeVariable(val_peek(2))){
									     	if(this.sintactico.existeVariable(val_peek(0))){
											     Terceto t =  new TercetoComparador(val_peek(1), val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
											     yyval = new ParserVal(t);
											     this.sintactico.addTerceto(t);
											}
											else {
												this.sintactico.addError("variable", val_peek(0));
											}
										}
										else {
											this.sintactico.addError("variable", val_peek(2));
										}}
break;
case 66:
//#line 274 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
break;
case 73:
//#line 286 "gramaticaCorregidaBarbie.y"
{ if(this.sintactico.existeVariable(val_peek(2))){
										if(this.sintactico.existeVariable(val_peek(0))){
											if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {	
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoSuma(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
													this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
													yyval = new ParserVal(t);
													this.sintactico.addTerceto(t);
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
case 74:
//#line 311 "gramaticaCorregidaBarbie.y"
{ 	if(this.sintactico.existeVariable(val_peek(2))){
										if(this.sintactico.existeVariable(val_peek(0))){
		 									if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoResta(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
													this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
													yyval = new ParserVal(t);
													this.sintactico.addTerceto(t);
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
case 76:
//#line 338 "gramaticaCorregidaBarbie.y"
{ 	if(this.sintactico.existeVariable(val_peek(2))){
									if(this.sintactico.existeVariable(val_peek(0))){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
											if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoMultiplicacion(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
												this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
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
case 77:
//#line 363 "gramaticaCorregidaBarbie.y"
{ if(this.sintactico.existeVariable(val_peek(2))){
									if(this.sintactico.existeVariable(val_peek(0))){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 									if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoDivision(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
												this.sintactico.setTipoDatoTerceto(t, val_peek(2), val_peek(0));
												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
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
case 79:
//#line 390 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion(val_peek(2)))
 											{
 												Terceto t =  new TercetoFuncion(val_peek(2), this.sintactico.getTercetos().size());
 												t.setSegundo("[" + Integer.valueOf(this.sintactico.getTercetos().size()+1) + "]");
 												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
												/*Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());*/
												/*etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());*/
												/*this.sintactico.addTerceto(etiqueta);*/
 											}
 											else
 											{
 												this.sintactico.addError("funcion", val_peek(2));
 											}}
break;
case 80:
//#line 406 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
//#line 1022 "Parser.java"
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
