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
    0,    1,    1,    1,    1,    2,    2,    6,    7,    7,
    7,    7,    4,    4,    4,   10,   10,    9,    9,   11,
   11,   12,   12,    3,    3,   17,   17,    5,   22,   13,
   13,   13,   13,   13,   21,   23,   21,   21,   21,   21,
   25,   14,   14,   14,   14,   14,   24,   24,   24,   24,
   24,   27,   26,   28,   26,   26,   26,   26,   15,   15,
   15,   16,   16,   16,   16,   19,   19,   19,   20,   20,
   20,   29,   29,   29,   29,   29,   29,    8,    8,    8,
   30,   30,   30,   32,   32,   31,   31,   31,   18,   18,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    1,    1,
    2,    2,    1,    4,    4,    1,    2,    1,    1,    1,
    1,    1,    1,    2,    3,    3,    4,    4,    0,    7,
    6,    6,    6,    6,    3,    0,    6,    5,    3,    3,
    0,    6,    5,    5,    5,    5,    4,    4,    4,    4,
    4,    0,    5,    0,    6,    4,    4,    4,    4,    4,
    4,    5,    5,    5,    4,    1,    3,    2,    3,    3,
    2,    1,    1,    1,    1,    1,    1,    3,    3,    1,
    3,    3,    1,    3,    4,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   89,   90,    0,    0,    0,
    2,    3,    6,    7,   13,   18,   19,   20,   21,   22,
   23,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,    0,    0,    4,    5,    0,    0,
    0,    0,   68,    0,    0,    0,   87,    0,    0,    0,
   83,   88,    0,    0,    0,    0,    0,    0,   17,    0,
    0,    0,    0,    0,   10,    0,    9,   25,   26,    0,
    0,   67,    0,    0,   72,   73,   74,   75,   76,   77,
    0,    0,    0,    0,    0,    0,    0,    0,   60,   61,
   59,    0,    0,    0,   15,   14,    0,    0,    0,    0,
    0,    0,   41,    0,   12,   11,   27,   28,    0,   84,
    0,    0,   43,    0,    0,    0,    0,    0,   81,   82,
    0,    0,    0,   29,   63,   64,   62,   44,   45,   46,
    0,    0,   85,    0,    0,    0,    0,    0,    0,   31,
   32,   33,   34,    0,   42,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   30,    0,    0,
    0,    0,    0,   54,   48,   49,   50,   51,   47,   39,
    0,   40,   35,    0,    0,   56,   57,   58,    0,    0,
    0,    0,    8,   53,    0,   38,    0,   55,   37,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,  139,   14,   40,   66,   48,   15,   32,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   49,
  140,  144,  157,  113,  131,  136,  163,  180,   86,   50,
   51,   52,
};
final static short yysindex[] = {                      -113,
    4,  -34,  -40,  -68,  -28,    0,    0,  -16,    0, -113,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -63, -192,  -11, -123, -121, -121, -121, -121,  -34,
    0,  -94, -210, -199, -178, -109,    0,    0,  -54,   40,
 -163, -169,    0, -107, -151,  126,    0,   78,   47,   14,
    0,    0,   80,   79,    3,   52,  -26,  -41,    0,   77,
  -25,   91,  100,  -23,    0, -126,    0,    0,    0, -108,
  115,    0,  -15,  -88,    0,    0,    0,    0,    0,    0,
 -121, -121,   42, -121, -103, -121, -121, -121,    0,    0,
    0,  -87,  -80, -215,    0,    0,  140,  152,  153,  -88,
  -88,  -88,    0,  164,    0,    0,    0,    0,  160,    0,
 -214, -204,    0,   14,   14,   42,  -78,   42,    0,    0,
  -78,  -78,  -78,    0,    0,    0,    0,    0,    0,    0,
  -88, -121,    0,  -50, -161, -100,  -86, -105, -203,    0,
    0,    0,    0,  -78,    0,   70,  151,  154,  -46,  -45,
  215,  225,  229,  -17,  -36,   -9,   20,    0,  243,  -78,
  -78,  -78,  232,    0,    0,    0,    0,    0,    0,    0,
   29,    0,    0,  -78,  168,    0,    0,    0,  -78,  236,
  254,   39,    0,    0,  -78,    0,  256,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   -1,    0,    0,    0,    0,    0,    0,    0,  303,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,
    0,    0,    0,    0,    0,   57,    0,    0,    0,  -32,
    0,    0,  -39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -22,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   21,   30,   36,    0,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   48,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  250,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  306,  315,   22,   -8,    0,    0,   24,   50,    0,
    0,    0,    0,    0,    0,    0,    0,  286,    0,  147,
  -53,    0,    0,   27,    0,  226,    0,    0,  289,  111,
  107,    0,
};
final static int YYTABLESIZE=355;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   65,   86,   86,   86,   96,   86,   86,   86,   80,  170,
   80,   34,   80,   80,   94,   99,   24,  103,   71,  154,
   86,   13,   86,   36,  151,  110,   27,   80,  169,   80,
   65,   13,   45,   75,  112,   76,  173,   71,  152,   71,
  123,  134,   66,   25,  124,   81,   44,   82,   91,   54,
   55,  137,  155,   31,  135,   87,   66,  105,  156,   39,
   88,   78,   60,   78,  135,   78,   78,  141,  142,  143,
   79,   83,   79,   61,   79,   79,   70,   41,   62,   42,
   78,   59,   78,   69,   81,   68,   82,   85,   67,   79,
  158,   79,   92,   69,  148,   70,  149,   70,   86,   86,
   70,   86,   69,   86,   69,   72,   75,  116,   76,  118,
  159,   75,   81,   76,   82,  106,   86,   97,   86,   73,
   81,   81,   82,   82,   89,   65,  128,  129,  130,    1,
    2,  100,    3,   46,   47,   53,   47,   75,    5,   76,
  101,    8,    1,    2,  104,    3,   63,   64,  107,    4,
  153,    5,    6,    7,    8,  146,  117,  145,    6,    7,
  108,    1,   30,  150,    3,   73,   74,  111,  150,   58,
    5,  147,  121,    8,   56,   57,  171,    1,   30,  122,
    3,  176,  177,  178,    4,  125,    5,    1,   30,    8,
    3,  114,  115,  119,  120,  182,    5,  126,  127,    8,
  184,    1,    2,  132,    3,  133,  188,  147,  160,  162,
    5,  161,  164,    8,   95,   28,   86,   86,   86,    1,
   30,   26,    3,   80,   80,   80,    4,   33,    5,   93,
   98,    8,  102,   71,   86,   86,   86,   86,  168,   35,
  109,   80,   80,   80,   80,   43,  172,   77,   78,   79,
   80,   71,   71,   71,   71,   66,   65,   65,   90,   65,
  165,   65,   65,   65,   65,   65,   65,   65,   65,   65,
  166,   65,   24,   24,  167,   24,   78,   78,   78,   24,
  174,   24,   24,   24,   24,   79,   79,   79,  175,  179,
  181,   70,  183,  185,   78,   78,   78,   78,   69,  186,
  187,  189,    1,   79,   79,   79,   79,   52,   36,   70,
   70,   70,   70,   86,   86,   37,   69,   69,   69,   69,
   77,   78,   79,   80,   38,   77,   78,   79,   80,   71,
   86,   86,   86,   86,   53,   47,   84,  138,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   77,   78,   79,   80,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   42,   43,   46,   45,   46,   47,   41,   46,
   43,   40,   45,   46,   41,   41,    0,   41,   41,  125,
   60,    0,   62,   40,  125,   41,   61,   60,   46,   62,
   39,   10,   44,   60,  123,   62,   46,   60,  125,   62,
  256,  256,   44,   40,  260,   43,   58,   45,   46,   26,
   27,  256,  256,    4,  269,   42,   58,   66,  262,  123,
   47,   41,  273,   43,  269,   45,   46,  121,  122,  123,
   41,   48,   43,  273,   45,   46,   41,  270,  257,  272,
   60,   32,   62,   41,   43,   46,   45,   41,   39,   60,
  144,   62,   41,  257,  256,   60,  258,   62,   42,   43,
  270,   45,   60,   47,   62,  257,   60,   84,   62,   86,
   41,   60,   43,   62,   45,   66,   60,   41,   62,   40,
   43,   43,   45,   45,   46,  125,  100,  101,  102,  256,
  257,   41,  259,  257,  258,  257,  258,   60,  265,   62,
   41,  268,  256,  257,  271,  259,  256,  257,  257,  263,
  256,  265,  266,  267,  268,  132,  260,  131,  266,  267,
   46,  256,  257,  269,  259,   40,   41,  256,  269,  264,
  265,  258,  260,  268,   28,   29,  155,  256,  257,  260,
  259,  160,  161,  162,  263,   46,  265,  256,  257,  268,
  259,   81,   82,   87,   88,  174,  265,   46,   46,  268,
  179,  256,  257,   40,  259,   46,  185,  258,   58,  256,
  265,   58,  258,  268,  256,  256,  256,  257,  258,  256,
  257,  256,  259,  256,  257,  258,  263,  256,  265,  256,
  256,  268,  256,  256,  274,  275,  276,  277,  256,  256,
  256,  274,  275,  276,  277,  257,  256,  274,  275,  276,
  277,  274,  275,  276,  277,  257,  256,  257,  256,  259,
   46,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   46,  271,  256,  257,   46,  259,  256,  257,  258,  263,
  261,  265,  266,  267,  268,  256,  257,  258,   46,   58,
  262,  256,  125,   58,  274,  275,  276,  277,  256,   46,
  262,   46,    0,  274,  275,  276,  277,   58,  261,  274,
  275,  276,  277,  257,  258,   10,  274,  275,  276,  277,
  274,  275,  276,  277,   10,  274,  275,  276,  277,   44,
  274,  275,  276,  277,  257,  258,   48,  112,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
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

//#line 522 "gramaticaCorregidaFinal.y"

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
//#line 460 "Parser.java"
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
{	Terceto ret = new TercetoRet("RET", val_peek(3), null, this.sintactico.getTercetos().size(), this.funcionActual.pop());
												   								if(this.sintactico.getMarcaAntes()){
																					ret.setMarcaAntes(true);
																					this.sintactico.setMarcaAntes(false);
																				}
												   								this.sintactico.addTerceto(ret);
												   								this.sintactico.addTercetoFuncion(ret);
																			   }
break;
case 14:
//#line 58 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Bloque BEGIN-END");}
break;
case 15:
//#line 60 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 24:
//#line 80 "gramaticaCorregidaFinal.y"
{this.sintactico.decrementarAmbito();
														 this.sintactico.setFnMOVE(false);}
break;
case 25:
//#line 83 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Sobra '.'");}
break;
case 26:
//#line 87 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   
												   this.sintactico.setMarcaAntes(true);
												   this.sintactico.addNombreMarca(val_peek(0).sval);
												   this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);												   
												   
												   this.sintactico.actualizaFuncion(val_peek(0), val_peek(2));
												   this.sintactico.aumentarAmbito(val_peek(0));
												   this.funcionActual.push(val_peek(0).sval);}
break;
case 27:
//#line 97 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 
											 this.sintactico.setMarcaAntes(true);
											 this.sintactico.addNombreMarca(val_peek(0).sval);
											 this.sintactico.funcionPosPut(val_peek(0), val_peek(0).sval);
											 
											 this.sintactico.actualizaFuncion(val_peek(0), val_peek(3));
											 this.sintactico.aumentarAmbito(val_peek(0));
											 this.sintactico.setFnMOVE(true);
											 this.funcionActual.push(val_peek(0).sval);}
break;
case 28:
//#line 109 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1));}
break;
case 29:
//#line 113 "gramaticaCorregidaFinal.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
										   this.sintactico.addTerceto(bFalse);
										   if(!this.sintactico.getAmbito().equals("A")){
										   	   this.sintactico.addTercetoFuncion(bFalse);
										   }
		               					   this.sintactico.pilaPush(bFalse);
		                            	  }
break;
case 30:
//#line 120 "gramaticaCorregidaFinal.y"
{this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
break;
case 31:
//#line 123 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
break;
case 32:
//#line 124 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 33:
//#line 125 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 34:
//#line 126 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
break;
case 35:
//#line 130 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
			   						  }
break;
case 36:
//#line 134 "gramaticaCorregidaFinal.y"
{	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
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
case 37:
//#line 144 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  }
break;
case 38:
//#line 149 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
break;
case 39:
//#line 150 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
break;
case 40:
//#line 151 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 41:
//#line 156 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(1)))
 												  {
													  this.sintactico.showMessage("Sentencia: SWITCH");
													  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
										  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1);
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
case 42:
//#line 169 "gramaticaCorregidaFinal.y"
{this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
break;
case 43:
//#line 171 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
break;
case 44:
//#line 172 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 45:
//#line 173 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
break;
case 46:
//#line 174 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 48:
//#line 180 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
break;
case 49:
//#line 181 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 50:
//#line 182 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
break;
case 51:
//#line 183 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 52:
//#line 187 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), val_peek(0)) != null) {
								  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), val_peek(0), this.sintactico.getTercetos().size());
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
case 53:
//#line 200 "gramaticaCorregidaFinal.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
									  		   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1);
											   this.tercetoAux = bFalse;
											   Terceto bFalse2 = this.sintactico.pilaPop();
											   this.sintactico.pilaPush(bFalse);
											   bFalse2.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
										   	 }
break;
case 54:
//#line 207 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), val_peek(0)) != null) {
										  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), val_peek(0), this.sintactico.getTercetos().size());
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
case 55:
//#line 223 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bFalse3 = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(tercetoAux);
														  bFalse3.setSegundo(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											  		    }
break;
case 56:
//#line 229 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 57:
//#line 230 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
break;
case 58:
//#line 231 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
break;
case 59:
//#line 235 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Asignaci\u00f3n");

											if(this.sintactico.existeVariable(val_peek(3)))
 											{
 												if(this.sintactico.existeVariable(val_peek(1)))
 												{
		 											if(this.sintactico.ambitoCorrecto(val_peek(3), val_peek(1))) {
		 												if(this.sintactico.mismoTipo(val_peek(3), val_peek(1)) != null) {
		 													Terceto t =  new TercetoAsignacion(val_peek(3), val_peek(1), this.sintactico.getTercetos().size());
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
case 60:
//#line 273 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 61:
//#line 274 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignacion");}
break;
case 62:
//#line 278 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut(val_peek(2), this.sintactico.getTercetos().size());
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
case 63:
//#line 291 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
break;
case 64:
//#line 292 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
break;
case 65:
//#line 293 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
break;
case 66:
//#line 297 "gramaticaCorregidaFinal.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 67:
//#line 300 "gramaticaCorregidaFinal.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 68:
//#line 304 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
break;
case 70:
//#line 309 "gramaticaCorregidaFinal.y"
{this.sintactico.showMessage("Condici\u00f3n");
										if(this.sintactico.existeVariable(val_peek(2))){
									     	if(this.sintactico.existeVariable(val_peek(0))){
									     		if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
										     		if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
														Terceto t =  new TercetoComparador(val_peek(1), val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
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
case 71:
//#line 342 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
break;
case 78:
//#line 354 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(2))){
										if(this.sintactico.existeVariable(val_peek(0))){
											if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {	
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoSuma(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
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
case 79:
//#line 386 "gramaticaCorregidaFinal.y"
{ 	if(this.sintactico.existeVariable(val_peek(2))){
										if(this.sintactico.existeVariable(val_peek(0))){
		 									if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 										if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
													Terceto t =  new TercetoResta(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
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
case 81:
//#line 420 "gramaticaCorregidaFinal.y"
{ 	if(this.sintactico.existeVariable(val_peek(2))){
									if(this.sintactico.existeVariable(val_peek(0))){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
											if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoMultiplicacion(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
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
//#line 452 "gramaticaCorregidaFinal.y"
{ if(this.sintactico.existeVariable(val_peek(2))){
									if(this.sintactico.existeVariable(val_peek(0))){
										if(this.sintactico.ambitoCorrecto(val_peek(2), val_peek(0))) {
		 									if(this.sintactico.mismoTipo(val_peek(2), val_peek(0)) != null) {
												Terceto t =  new TercetoDivision(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
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
case 84:
//#line 486 "gramaticaCorregidaFinal.y"
{ this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion(val_peek(2)))
 											{
 												Terceto t =  new TercetoFuncion(val_peek(2), this.sintactico.getTercetos().size());
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
case 85:
//#line 506 "gramaticaCorregidaFinal.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
//#line 1146 "Parser.java"
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
