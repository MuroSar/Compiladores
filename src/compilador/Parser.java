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
   12,   12,    3,   17,   17,    5,   22,   13,   13,   13,
   13,   13,   21,   23,   21,   21,   21,   21,   25,   14,
   14,   14,   14,   14,   24,   24,   24,   24,   24,   26,
   26,   26,   26,   26,   15,   15,   16,   16,   16,   16,
   19,   19,   19,   20,   20,   20,   27,   27,   27,   27,
   27,   27,    8,    8,    8,   28,   28,   28,   30,   30,
   30,   29,   29,   29,   18,   18,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    1,    1,
    2,    2,    1,    4,    1,    2,    1,    1,    1,    1,
    1,    1,    2,    3,    4,    4,    0,    7,    6,    6,
    6,    6,    3,    0,    6,    5,    3,    3,    0,    6,
    5,    5,    5,    5,    4,    4,    4,    4,    4,    4,
    5,    4,    4,    4,    4,    4,    5,    5,    5,    4,
    1,    3,    2,    3,    3,    2,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    1,    4,    4,
    4,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,   85,   86,    0,    0,    0,
    2,    3,    6,    7,   13,   17,   18,   19,   20,   21,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   15,    0,    0,    0,    0,    0,    4,    5,    0,   23,
    0,    0,   63,    0,    0,    0,   83,    0,    0,    0,
   78,   84,    0,    0,    0,    0,    0,    0,   16,    0,
    0,    0,    0,    0,   10,    0,    9,   24,    0,    0,
   62,    0,    0,   67,   68,   69,   70,   71,   72,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   55,    0,
    0,    0,   14,    0,    0,    0,    0,    0,    0,   39,
    0,   12,   11,   25,   26,    0,    0,    0,    0,   41,
    0,    0,    0,    0,    0,   76,   77,    0,    0,    0,
   27,   58,   59,   57,   42,   43,   44,    0,    0,   80,
   81,   79,    0,    0,    0,    0,    0,    0,   29,   30,
   31,   32,    0,   40,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   28,    0,    0,    0,
    0,    0,    0,   46,   47,   48,   49,   45,   37,    0,
   38,   33,    0,    0,   52,   53,   54,   50,    0,    0,
    0,    8,   51,   36,    0,   35,
};
final static short yydgoto[] = {                          9,
   10,   11,   12,  138,   14,   40,   66,   48,   15,   32,
   16,   17,   18,   19,   20,   21,   22,   23,   24,   49,
  139,  143,  156,  110,  128,  135,   85,   50,   51,   52,
};
final static short yysindex[] = {                      -117,
   15,  -34,  -40,  -90,  -35,    0,    0,  -28,    0, -117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -79, -174,  -11, -140,  -95,  -95,  -95,  -95,  -34,
    0, -104, -221, -207, -182,  -77,    0,    0,  -66,    0,
 -165, -170,    0,  -63, -146,  165,    0,   65,   23,   12,
    0,    0,   79,   78,   91,   35,    5,   82,    0,   90,
  -23,   94,  104,  -17,    0, -127,    0,    0, -110,  108,
    0,  -16,  -88,    0,    0,    0,    0,    0,    0,  -95,
  -95,   61,  -95, -102,  -95,  -95,  -95,    0,    0,  -86,
  -83, -166,    0,  110,  119,  126,  -88,  -88,  -88,    0,
  147,    0,    0,    0,    0,  143,  -36, -195, -187,    0,
   12,   12,   61,  -71,   61,    0,    0,  -71,  -71,  -71,
    0,    0,    0,    0,    0,    0,    0,  -88,  -95,    0,
    0,    0,  -62, -144,  -96,  -87,  -99, -211,    0,    0,
    0,    0,  -71,    0,   43,  140,  142,  -42,  -45,  169,
  177,  181,  -15,   87,   -9,  -27,    0,  183,  -71,  -71,
  -71,  -71,  195,    0,    0,    0,    0,    0,    0,   -8,
    0,    0,  -71,  130,    0,    0,    0,    0,  -71,  210,
   -3,    0,    0,    0,  225,    0,
};
final static short yyrindex[] = {                         0,
    0,   -5,    0,    0,    0,    0,    0,    0,    0,  276,
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
    0,    0,    0,    0,    0,    0,    0,   16,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  268,  274,   22,    4,    0,    0,   30,   77,    0,
    0,    0,    0,    0,    0,    0,    0,  241,    0,  179,
  -70,    0,    0,  -57,    0,  180,  238,  129,  125,    0,
};
final static int YYTABLESIZE=355;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   60,   82,   82,   82,   34,   82,   82,   82,   75,  132,
   75,   36,   75,   75,   73,  162,   73,   96,   73,   73,
   82,   13,   82,  100,  107,  153,   27,   75,  150,   75,
  168,   13,   45,   73,  109,   73,  172,  151,   61,  125,
  126,  127,   65,   39,  154,   92,   44,  140,  141,  142,
  155,   60,   61,   86,   25,   54,   55,   74,   87,   74,
  133,   74,   74,   84,   74,   61,   75,   66,  136,  102,
  144,   65,  157,  134,   62,   90,   74,   82,   74,   64,
   31,  134,   74,  158,   75,   80,   66,   81,   66,  120,
   65,   68,   65,  121,   74,   41,   75,   42,   64,   69,
   64,   82,   82,   80,   82,   81,   82,   80,   59,   81,
   71,  147,  113,  148,  115,   67,   46,   47,   72,   82,
   80,   82,   81,   88,   74,   60,   75,   93,    1,    2,
   94,    3,  169,   80,   97,   81,   89,    5,    1,    2,
    8,    3,  103,  101,   98,    4,  104,    5,    6,    7,
    8,    1,   30,  105,    3,  122,  152,  114,  145,   58,
    5,   53,   47,    8,  123,    1,   30,  108,    3,  149,
  146,  124,  149,  118,    5,  170,  119,    8,   63,   64,
  175,  176,  177,  178,    1,   30,  129,    3,  130,    1,
    2,    4,    3,    5,  181,  146,    8,  159,    5,  160,
  183,    8,    6,    7,   72,   73,   56,   57,  111,  112,
  116,  117,  163,  161,  164,   28,   82,   82,   82,  131,
   33,   26,  165,   75,   75,   75,  166,   35,  174,   73,
   73,   73,   95,  173,   82,   82,   82,   82,   99,  106,
  167,   75,   75,   75,   75,   43,  171,   73,   73,   73,
   73,   61,  179,  180,  182,  184,   60,   60,  185,   60,
   91,   60,   60,   60,   60,   60,   60,   60,   60,   60,
  186,   60,   74,   74,   74,    1,   34,   37,   76,   77,
   78,   79,   66,   38,   70,   83,   65,    0,  137,    0,
   74,   74,   74,   74,   64,    0,   76,   77,   78,   79,
   66,   66,   66,   66,   65,   65,   65,   65,   76,   77,
   78,   79,   64,   64,   64,   64,   82,   82,    0,    0,
    0,   53,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   82,   82,   82,   82,    0,   76,   77,
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
   46,   10,   44,   60,  123,   62,   46,  125,   44,   97,
   98,   99,   39,  123,  256,   41,   58,  118,  119,  120,
  262,  273,   58,   42,   40,   26,   27,   41,   47,   43,
  256,   45,   46,   41,   60,  273,   62,   41,  256,   66,
  128,   41,  143,  269,  257,   41,   60,   48,   62,   41,
    4,  269,   60,   41,   62,   43,   60,   45,   62,  256,
   60,  257,   62,  260,   60,  270,   62,  272,   60,  270,
   62,   42,   43,   43,   45,   45,   47,   43,   32,   45,
  257,  256,   83,  258,   85,   39,  257,  258,   40,   60,
   43,   62,   45,   46,   60,  125,   62,   46,  256,  257,
   41,  259,   46,   43,   41,   45,   46,  265,  256,  257,
  268,  259,   66,  271,   41,  263,  257,  265,  266,  267,
  268,  256,  257,   46,  259,   46,  256,  260,  129,  264,
  265,  257,  258,  268,   46,  256,  257,  256,  259,  269,
  258,   46,  269,  260,  265,  154,  260,  268,  256,  257,
  159,  160,  161,  162,  256,  257,   40,  259,   46,  256,
  257,  263,  259,  265,  173,  258,  268,   58,  265,   58,
  179,  268,  266,  267,   40,   41,   28,   29,   80,   81,
   86,   87,  258,  256,   46,  256,  256,  257,  258,  256,
  256,  256,   46,  256,  257,  258,   46,  256,   46,  256,
  257,  258,  256,  261,  274,  275,  276,  277,  256,  256,
  256,  274,  275,  276,  277,  257,  256,  274,  275,  276,
  277,  257,   58,  262,  125,   46,  256,  257,  262,  259,
  256,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   46,  271,  256,  257,  258,    0,  261,   10,  274,  275,
  276,  277,  256,   10,   44,   48,  256,   -1,  109,   -1,
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
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"llamado_funcion : IDENTIFICADOR '(' error '.'",
"llamado_funcion : IDENTIFICADOR '(' ')' error",
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"factor : llamado_funcion",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 399 "gramaticaCorregidaBarbie.y"

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
//#line 450 "Parser.java"
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
case 23:
//#line 68 "gramaticaCorregidaBarbie.y"
{this.sintactico.decrementarAmbito();
														 this.sintactico.setFnMOVE(false);}
break;
case 24:
//#line 72 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
												   etiqueta.setPrimero(val_peek(0).sval);
												   this.sintactico.addTerceto(etiqueta);
												   this.sintactico.funcionPosPut(val_peek(0), etiqueta.getPrimero());												   
												   this.sintactico.actualizaFuncion(val_peek(0), val_peek(2));
												   this.sintactico.aumentarAmbito(val_peek(0));}
break;
case 25:
//#line 79 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
											 etiqueta.setPrimero(val_peek(0).sval);
											 this.sintactico.addTerceto(etiqueta);
											 this.sintactico.funcionPosPut(val_peek(0), etiqueta.getPrimero());
											 this.sintactico.actualizaFuncion(val_peek(0), val_peek(3));
											 this.sintactico.aumentarAmbito(val_peek(0));
											 this.sintactico.setFnMOVE(true);}
break;
case 26:
//#line 89 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1));}
break;
case 27:
//#line 93 "gramaticaCorregidaBarbie.y"
{ ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
										   this.sintactico.addTerceto(bFalse);
		               					   this.sintactico.pilaPush(bFalse);
		                            	  }
break;
case 29:
//#line 100 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
break;
case 30:
//#line 101 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 31:
//#line 102 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 32:
//#line 103 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
break;
case 33:
//#line 107 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
										Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
										etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
										this.sintactico.addTerceto(etiqueta);
			   						  }
break;
case 34:
//#line 114 "gramaticaCorregidaBarbie.y"
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
case 35:
//#line 123 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  	Terceto etiqueta = new TercetoEtiqueta("Label",null ,null , this.sintactico.getTercetos().size());
										etiqueta.setPrimero("Label" + this.sintactico.getTercetos().size());
										this.sintactico.addTerceto(etiqueta);
									  }
break;
case 36:
//#line 131 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
break;
case 37:
//#line 132 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
break;
case 38:
//#line 133 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 39:
//#line 138 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: SWITCH");
												  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
												  this.sintactico.addTerceto(bFalse);
							               		  this.sintactico.pilaPush(bFalse);
							               		}
break;
case 41:
//#line 145 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
break;
case 42:
//#line 146 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
break;
case 43:
//#line 147 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
break;
case 44:
//#line 148 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
break;
case 45:
//#line 152 "gramaticaCorregidaBarbie.y"
{ Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el IF*/
									  }
break;
case 46:
//#line 156 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
break;
case 47:
//#line 157 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 48:
//#line 158 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
break;
case 49:
//#line 159 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
break;
case 50:
//#line 163 "gramaticaCorregidaBarbie.y"
{ Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
												 this.sintactico.addTerceto(bIncondicional); 
												 Terceto bFalse = this.sintactico.pilaPop();
												 this.sintactico.pilaPush(bIncondicional);
												 bFalse.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											   }
break;
case 51:
//#line 169 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
														  this.sintactico.addTerceto(bIncondicional); 
														  Terceto bFalse = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(bIncondicional);
														  bFalse.setPrimero(this.sintactico.getTercetos().size()); /*Set linea donde termina el THEN*/
											  		    }
break;
case 52:
//#line 177 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
break;
case 53:
//#line 178 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
break;
case 54:
//#line 179 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
break;
case 55:
//#line 183 "gramaticaCorregidaBarbie.y"
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
//#line 207 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
break;
case 57:
//#line 212 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut(val_peek(2), this.sintactico.getTercetos().size());
							   	 yyval = new ParserVal(t);
								 this.sintactico.addTerceto(t);
							   }
break;
case 58:
//#line 218 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
break;
case 59:
//#line 219 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
break;
case 60:
//#line 220 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
break;
case 61:
//#line 224 "gramaticaCorregidaBarbie.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 62:
//#line 227 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 63:
//#line 231 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
break;
case 65:
//#line 236 "gramaticaCorregidaBarbie.y"
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
//#line 251 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
break;
case 73:
//#line 263 "gramaticaCorregidaBarbie.y"
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
//#line 288 "gramaticaCorregidaBarbie.y"
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
//#line 315 "gramaticaCorregidaBarbie.y"
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
//#line 340 "gramaticaCorregidaBarbie.y"
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
//#line 367 "gramaticaCorregidaBarbie.y"
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
case 80:
//#line 383 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
break;
case 81:
//#line 384 "gramaticaCorregidaBarbie.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en llamado a Funcion");}
break;
//#line 1000 "Parser.java"
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
