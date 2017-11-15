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
import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
//#line 36 "Parser.java"




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
   12,   12,   12,    3,   18,   18,    5,   23,   13,   13,
   13,   13,   13,   22,   24,   22,   22,   22,   22,   26,
   14,   14,   14,   14,   14,   25,   25,   25,   25,   25,
   27,   27,   27,   27,   27,   15,   15,   16,   16,   16,
   16,   17,   17,   17,   17,   20,   20,   20,   21,   21,
   21,   28,   28,   28,   28,   28,   28,    8,    8,    8,
   29,   29,   29,   30,   30,   19,   19,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    1,    1,
    2,    2,    1,    4,    1,    2,    1,    1,    1,    1,
    1,    1,    1,    2,    3,    4,    4,    0,    7,    6,
    6,    6,    6,    3,    0,    6,    5,    3,    3,    0,
    6,    5,    5,    5,    5,    4,    4,    4,    4,    4,
    4,    5,    4,    4,    4,    4,    4,    5,    5,    5,
    4,    4,    4,    4,    4,    1,    3,    2,    3,    3,
    2,    1,    1,    1,    1,    1,    1,    3,    3,    1,
    3,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   86,   87,    0,    0,    0,
    2,    3,    6,    7,   13,   17,   18,   19,   20,   21,
   22,   23,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   15,    0,    0,    0,    0,    0,    4,    5,
    0,   24,    0,    0,   68,    0,    0,    0,   85,    0,
    0,    0,   83,   84,    0,    0,    0,    0,    0,    0,
    0,    0,   16,    0,    0,    0,    0,    0,   10,    0,
    9,   25,    0,    0,   67,    0,   72,   73,   74,   75,
   76,   77,    0,    0,    0,    0,    0,    0,    0,    0,
   63,   57,   64,   65,   62,   56,    0,    0,    0,   14,
    0,    0,    0,    0,    0,    0,   40,    0,   12,   11,
   26,   27,    0,    0,   42,    0,    0,    0,    0,    0,
   81,   82,    0,    0,    0,   28,   59,   60,   58,   43,
   44,   45,    0,    0,    0,    0,    0,    0,    0,    0,
   30,   31,   32,   33,    0,   41,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   29,    0,
    0,    0,    0,    0,    0,   47,   48,   49,   50,   46,
   38,    0,   39,   34,    0,    0,   53,   54,   55,   51,
    0,    0,    0,    8,   52,   37,    0,   36,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,  140,   14,   42,   70,   50,   15,   34,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   25,
   51,  141,  145,  158,  115,  133,  137,   88,   52,   53,
};
final static short yysindex[] = {                      -136,
   -4,  -31,  -25, -106,  -18,    0,    0,  -12,    0, -136,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -73, -188,  -20, -140,  -38,   -7, -133, -133,
 -133,  -31,    0, -123, -215, -200, -146,  -85,    0,    0,
 -101,    0, -129, -131,    0,  -77, -114,  111,    0,   36,
  -23,   20,    0,    0,  101,  136,  117,  -26,  140,    8,
   -1,  120,    0,  113,   -6,  127,  139,    5,    0, -149,
    0,    0,  -70,  138,    0,  -91,    0,    0,    0,    0,
    0,    0, -133, -133,   47, -133,  -67, -133, -133, -133,
    0,    0,    0,    0,    0,    0,  -63,  -62, -147,    0,
  153,  154,  155,  -91,  -91,  -91,    0,  162,    0,    0,
    0,    0, -214, -209,    0,   20,   20,   47, -119,   47,
    0,    0, -119, -119, -119,    0,    0,    0,    0,    0,
    0,    0,  -91, -133,  -55, -144,  -96,  -80,  -99, -208,
    0,    0,    0,    0, -119,    0,   60,  146,  148,  -51,
  -50,  161,  163,  164,   32,   58,   42,  -49,    0,  165,
 -119, -119, -119, -119,  156,    0,    0,    0,    0,    0,
    0,  -44,    0,    0, -119,   88,    0,    0,    0,    0,
 -119,  178,  -36,    0,    0,    0,  186,    0,
};
final static short yyrindex[] = {                         0,
    0,   39,    0,    0,    0,    0,    0,    0,    0,  243,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   29,    0,    0,
    0,  -41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -35,  -29,   15,    0,   25,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -5,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  285,  287,   13,   16,    0,    0,   14,   65,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  261,    0,
  130,  -30,    0,    0,  -53,    0,  194,  259,  108,  106,
};
final static int YYTABLESIZE=326;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         80,
   61,   80,   55,   80,   80,   78,  164,   78,   28,   78,
   78,   79,   13,   79,   31,   79,   79,   87,   80,   95,
   80,   36,   13,   47,   78,  155,   78,   38,  152,   29,
   79,  114,   79,   58,  103,   26,   77,   46,   78,   99,
   56,  135,   59,   71,  153,  107,  138,  156,   97,   41,
  130,  131,  132,  157,  136,   70,   69,   64,   77,  136,
   78,   89,   71,   85,   71,   69,   90,   77,   33,   78,
   84,   84,   65,   84,   70,   84,   70,  170,   83,  146,
   84,   43,   66,   44,   69,  109,   69,  174,   84,   83,
   84,   84,  142,  143,  144,   77,   66,   78,   63,  118,
  160,  120,   83,  171,   84,   71,    1,    2,  125,    3,
   66,  149,  126,  150,  159,    5,   48,   49,    8,    1,
    2,  108,    3,   54,   49,   61,    4,   72,    5,    6,
    7,    8,    1,   32,  110,    3,    1,   32,   73,    3,
   62,    5,   75,    4,    8,    5,   91,  147,    8,    1,
   32,   76,    3,  101,    1,    2,  154,    3,    5,   60,
   61,    8,   93,    5,  113,  100,    8,  104,  172,  151,
   67,   68,  151,  177,  178,  179,  180,  148,   83,  105,
   84,   92,   83,  112,   84,   96,  111,  183,    6,    7,
  116,  117,  119,  185,  121,  122,  123,  124,  127,  128,
  129,  134,  148,  161,  163,  162,  166,  165,  167,  168,
  176,  175,  184,  181,   80,   80,   80,  182,   54,   49,
   78,   78,   78,  186,   27,  187,   79,   79,   79,   94,
   30,  188,   80,   80,   80,   80,   45,   35,   78,   78,
   78,   78,    1,   37,   79,   79,   79,   79,   57,  102,
   79,   80,   81,   82,   98,   35,   61,   61,   71,   61,
  106,   61,   61,   61,   61,   61,   61,   61,   61,   61,
   70,   61,   79,   80,   81,   82,   71,   71,   71,   71,
   69,   79,   80,   81,   82,   84,   84,  169,   70,   70,
   70,   70,   54,   49,   39,   66,   40,  173,   69,   69,
   69,   69,   84,   84,   84,   84,   74,  139,   86,   79,
   80,   81,   82,    1,   32,    0,    3,    0,    0,    0,
    4,    0,    5,    0,    0,    8,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   43,   41,   45,   46,   41,   58,   43,   40,   45,
   46,   41,    0,   43,   40,   45,   46,   41,   60,   46,
   62,   40,   10,   44,   60,  125,   62,   40,  125,   61,
   60,  123,   62,   41,   41,   40,   60,   58,   62,   41,
   27,  256,   29,   41,  125,   41,  256,  256,   41,  123,
  104,  105,  106,  262,  269,   41,   41,  273,   60,  269,
   62,   42,   60,   50,   62,   41,   47,   60,    4,   62,
   42,   43,  273,   45,   60,   47,   62,   46,   43,  133,
   45,  270,   44,  272,   60,   70,   62,   46,   60,   43,
   62,   45,  123,  124,  125,   60,   58,   62,   34,   86,
   41,   88,   43,   46,   45,   41,  256,  257,  256,  259,
  257,  256,  260,  258,  145,  265,  257,  258,  268,  256,
  257,  271,  259,  257,  258,  125,  263,  257,  265,  266,
  267,  268,  256,  257,   70,  259,  256,  257,  270,  259,
  264,  265,  257,  263,  268,  265,   46,  134,  268,  256,
  257,   41,  259,   41,  256,  257,  256,  259,  265,   30,
   31,  268,   46,  265,  256,   46,  268,   41,  156,  269,
  256,  257,  269,  161,  162,  163,  164,  258,   43,   41,
   45,   46,   43,   46,   45,   46,  257,  175,  266,  267,
   83,   84,  260,  181,   89,   90,  260,  260,   46,   46,
   46,   40,  258,   58,  256,   58,   46,  258,   46,   46,
   46,  261,  125,   58,  256,  257,  258,  262,  257,  258,
  256,  257,  258,   46,  256,  262,  256,  257,  258,  256,
  256,   46,  274,  275,  276,  277,  257,  256,  274,  275,
  276,  277,    0,  256,  274,  275,  276,  277,  256,  256,
  274,  275,  276,  277,  256,  261,  256,  257,  256,  259,
  256,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  256,  271,  274,  275,  276,  277,  274,  275,  276,  277,
  256,  274,  275,  276,  277,  257,  258,  256,  274,  275,
  276,  277,  257,  258,   10,  257,   10,  256,  274,  275,
  276,  277,  274,  275,  276,  277,   46,  114,   50,  274,
  275,  276,  277,  256,  257,   -1,  259,   -1,   -1,   -1,
  263,   -1,  265,   -1,   -1,  268,
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
"sentencia_unica_ejecutable : llamado_funcion",
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
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"llamado_funcion : IDENTIFICADOR error ')' '.'",
"llamado_funcion : IDENTIFICADOR '(' error '.'",
"llamado_funcion : IDENTIFICADOR '(' ')' error",
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
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 398 "gramaticaCorregidaBarbie.y"

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
//#line 445 "Parser.java"
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
case 14:
//#line 49 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Bloque BEGIN-END");}
break;
case 24:
//#line 69 "gramaticaCorregidaBarbie.y"
{this.sintactico.decrementarAmbito();}
break;
case 25:
//#line 72 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
												   etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
												   this.sintactico.addTerceto(etiqueta);
												   this.sintactico.funcionPosPut(val_peek(0), etiqueta.getPrimero());												   
												   this.sintactico.actualizaFuncion(val_peek(0), val_peek(2));
												   this.sintactico.aumentarAmbito(val_peek(0));}
break;
case 26:
//#line 79 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
											 etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
											 this.sintactico.addTerceto(etiqueta);
											 this.sintactico.funcionPosPut(val_peek(0), etiqueta.getPrimero());
											 this.sintactico.actualizaFuncion(val_peek(0), val_peek(3));
											 this.sintactico.aumentarAmbito(val_peek(0));}
break;
case 27:
//#line 88 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1));}
break;
case 28:
//#line 92 "gramaticaCorregidaBarbie.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
										   this.sintactico.addTerceto(bFalse);
		               					   this.sintactico.pilaPush(bFalse);
		                            	  }
break;
case 30:
//#line 99 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
break;
case 31:
//#line 100 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 32:
//#line 101 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 33:
//#line 102 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
break;
case 34:
//#line 106 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
										Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
										etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
										this.sintactico.addTerceto(etiqueta);
			   						  }
break;
case 35:
//#line 113 "gramaticaCorregidaBarbie.y"
{	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
							this.sintactico.addTerceto(bIncondicional); 
							Terceto bFalse = this.sintactico.pilaPop();
							this.sintactico.pilaPush(bIncondicional);
							bFalse.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
							Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
							etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
							this.sintactico.addTerceto(etiqueta);
						 }
break;
case 36:
//#line 122 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  	Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
										etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
										this.sintactico.addTerceto(etiqueta);
									  }
break;
case 37:
//#line 130 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
break;
case 38:
//#line 131 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
break;
case 39:
//#line 132 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 40:
//#line 137 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: SWITCH");
												  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
												  this.sintactico.addTerceto(bFalse);
							               		  this.sintactico.pilaPush(bFalse);
							               		}
break;
case 42:
//#line 144 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
break;
case 43:
//#line 145 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 44:
//#line 146 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
break;
case 45:
//#line 147 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 46:
//#line 151 "gramaticaCorregidaBarbie.y"
{ Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  }
break;
case 47:
//#line 155 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
break;
case 48:
//#line 156 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 49:
//#line 157 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
break;
case 50:
//#line 158 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 51:
//#line 162 "gramaticaCorregidaBarbie.y"
{ Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
												 this.sintactico.addTerceto(bIncondicional); 
												 Terceto bFalse = this.sintactico.pilaPop();
												 this.sintactico.pilaPush(bIncondicional);
												 bFalse.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											   }
break;
case 52:
//#line 168 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
														  this.sintactico.addTerceto(bIncondicional); 
														  Terceto bFalse = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(bIncondicional);
														  bFalse.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											  		    }
break;
case 53:
//#line 176 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 54:
//#line 177 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
break;
case 55:
//#line 178 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
break;
case 56:
//#line 182 "gramaticaCorregidaBarbie.y"
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
case 57:
//#line 206 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 58:
//#line 211 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut(val_peek(2), this.sintactico.getTercetos().size());
							   	 yyval = new ParserVal(t);
								 this.sintactico.addTerceto(t);
							   }
break;
case 59:
//#line 217 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
break;
case 60:
//#line 218 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
break;
case 61:
//#line 219 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
break;
case 62:
//#line 223 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion(val_peek(3)))
 											{
 												Terceto t =  new TercetoFuncion(val_peek(3), this.sintactico.getTercetos().size());
 												t.setSegundo("[" + Integer.valueOf(this.sintactico.getTercetos().size()+1) + "]");
 												yyval = new ParserVal(t);
												this.sintactico.addTerceto(t);
												Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
												etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
												this.sintactico.addTerceto(etiqueta);
 											}
 											else
 											{
 												this.sintactico.addError("funcion", val_peek(3));
 											}}
break;
case 63:
//#line 239 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a Funcion");}
break;
case 64:
//#line 240 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
case 65:
//#line 241 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en llamado a Funcion");}
break;
case 66:
//#line 245 "gramaticaCorregidaBarbie.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 67:
//#line 248 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 68:
//#line 252 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
break;
case 70:
//#line 257 "gramaticaCorregidaBarbie.y"
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
case 71:
//#line 272 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
break;
case 78:
//#line 284 "gramaticaCorregidaBarbie.y"
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
case 79:
//#line 309 "gramaticaCorregidaBarbie.y"
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
case 81:
//#line 336 "gramaticaCorregidaBarbie.y"
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
case 82:
//#line 361 "gramaticaCorregidaBarbie.y"
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
//#line 997 "Parser.java"
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
