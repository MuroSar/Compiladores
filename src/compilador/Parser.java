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
    0,    1,    1,    1,    1,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    6,    6,    6,    6,    6,    6,
    6,    6,    3,    3,   11,   11,   10,    8,    8,    8,
    7,    7,    7,    7,    7,    7,    5,    5,    4,    4,
   14,   14,   14,   14,   14,   14,   13,   13,   13,   15,
   15,   15,   16,   16,    9,   12,   12,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,   10,    8,    8,    4,    1,
    3,    3,    3,    9,    9,    9,    9,    9,    9,    7,
    7,    7,    7,    7,    2,    2,    2,    2,    1,    1,
    1,    1,    1,    2,   11,   12,    4,    4,    3,    3,
    3,    4,    2,    3,    3,    3,    5,    6,    3,    3,
    1,    1,    1,    1,    1,    1,    3,    3,    1,    3,
    3,    1,    1,    1,    5,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   66,   67,    0,    0,    0,    4,
    5,    0,   29,   30,   31,   32,   33,    0,    0,   64,
    0,    0,    0,    0,   43,    0,    0,    0,   62,   63,
    0,    0,    0,    0,    0,    0,    2,    3,    0,   25,
   26,   27,   28,   34,    0,    0,    0,    0,    0,   41,
   46,    0,   45,   40,    0,    0,    0,    0,    0,    0,
   51,   52,   53,   54,   55,   56,    0,    0,    0,   12,
    0,    0,   13,    0,    0,   37,   38,   42,    0,    0,
   60,   61,    0,    0,    0,    0,    0,    9,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   21,    0,    0,   24,    0,    0,   23,
    0,   20,    0,    0,    0,    0,    0,    0,    0,    7,
    0,    0,    0,    0,    0,    8,    0,    0,   15,    0,
   18,   19,   17,   14,    0,    0,    0,    0,    6,   47,
    0,    0,    0,   48,   35,    0,   36,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   32,  110,   94,   13,   14,   15,   16,
   17,   18,   33,   67,   28,   29,
};
final static short yysindex[] = {                      -102,
  131,  -37, -183,   49,    0,    0,   52,    0, -102,    0,
    0, -163,    0,    0,    0,    0,    0, -237,  -40,    0,
  -33, -159, -240,  -44,    0,   70,  100,   85,    0,    0,
 -159,  -28,  -23,  169, -178, -135,    0,    0,   79,    0,
    0,    0,    0,    0, -108,  -91,  -40,  105,  124,    0,
    0,  116,    0,    0, -159, -159, -159, -159,  -41,  -79,
    0,    0,    0,    0,    0,    0, -159, -159,  136,    0,
  142,  143,    0,   62,  -71,    0,    0,    0,   85,   85,
    0,    0, -184, -142, -184,   65,   85,    0,  141,   71,
 -184,   72, -133, -187, -184,  -89,  -85,    0,  -81,  -78,
 -184, -184,  144,  145, -184,  150, -184,  151,  -59, -119,
  160,  -70,  -60,    0, -184,  157,    0,  -58,  -57,    0,
  -56,    0,  149,  -50,  163, -159,  171,  166,  -31,    0,
  167,  168,  170, -102,  172,    0,   94, -159,    0,  179,
    0,    0,    0,    0,  152, -102,  183,   97,    0,    0,
  164,  107,  191,    0,    0,  125,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,  218,    0,
    0,   11,    0,    0,    0,    0,    0,    0,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   67,    0,
    0,   80,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   93,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   23,   45,
    0,    0,    0,    0,    0,  -36,  -32,    0,    0,    0,
    0,    0,    0,  132,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  106,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  119,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -84,   29,    3,  224,    0,    7,   53,    2,   15,   17,
  244,   24,   30,  226,  -15,  102,
};
final static int YYTABLESIZE=434;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         84,
   59,   51,   31,   23,   49,  125,   12,   48,   50,   34,
   10,   38,   60,   41,  141,   12,   47,   24,   61,   55,
   62,   56,   57,   49,   26,   49,   42,   50,   43,   50,
   27,   61,   45,   62,   46,   41,   61,   37,   62,   79,
   80,   59,   26,   59,   58,   59,   59,   52,   42,  145,
   43,   49,   87,   25,   63,   63,   10,   63,   63,   63,
   59,  151,   59,   57,   40,   57,   39,   57,   57,    1,
   26,   25,    1,    1,    2,   50,   39,    4,    3,   44,
    4,    4,   57,    7,   57,   58,   40,   58,   35,   58,
   58,   36,   11,    1,   71,   41,   86,   30,   20,   25,
   39,    4,    5,    6,   58,   22,   58,   55,   42,   56,
   43,   93,   96,   97,    1,   53,    2,   95,   16,  100,
    3,   72,    4,  104,   73,    7,   57,  102,  103,  112,
  113,   58,  118,  119,  147,  121,   55,  153,   56,   55,
   12,   56,   55,  129,   56,   54,   40,   38,   74,  124,
   76,   12,   12,   38,    1,  137,    2,   12,   81,   82,
    3,   78,    4,    5,    6,    7,   55,  148,   56,   77,
   21,  105,  106,   37,   23,  107,  108,   10,   75,   37,
   85,   88,   89,   90,   91,   92,   98,  109,   24,  114,
  117,   22,  111,   99,  101,  120,  122,  150,  123,  126,
  127,  128,  130,  131,  132,  133,  134,  135,  136,  154,
  138,  139,  142,  143,   70,  144,   47,    1,   83,   30,
   20,    5,    6,   49,  149,    5,    6,   50,  152,  146,
  140,  155,   63,   64,   65,   66,  156,   49,   49,   49,
   49,   50,   50,   50,   50,   63,   64,   65,   66,  157,
   63,   64,   65,   66,   59,   44,    0,   59,   68,   59,
   59,   59,   59,   59,   59,   59,   59,   59,   59,   10,
    0,   59,    0,   10,   59,   59,   59,   59,   10,   57,
    0,   57,   57,   57,   57,   57,   57,   57,   57,   57,
   57,    0,    0,   57,    0,    0,   57,   57,   57,   57,
    0,   58,    0,   58,   58,   58,   58,   58,   58,   58,
   58,   58,   58,    0,    0,   58,    0,    0,   58,   58,
   58,   58,    0,   39,    0,   39,    0,   39,   39,   39,
   39,   39,   39,   39,   39,    0,   44,   39,   44,    0,
   44,   44,   44,   44,   44,   44,   44,   44,    0,   11,
   44,   11,    0,   11,   11,   11,    0,   11,   11,   11,
   11,    0,   22,   11,   22,    0,   22,   22,   22,    0,
   22,   22,   22,   22,    0,   16,   22,   16,    0,   16,
   16,   16,    0,   16,   16,   16,   16,   19,   20,   16,
   10,    0,   10,   10,   10,    0,    5,    6,    0,   10,
    0,    1,   10,    2,    0,  115,  116,    3,    1,    4,
    2,    0,    7,    0,    3,    0,    4,    5,    6,    7,
    1,    0,    2,    0,    0,    1,    3,    0,    4,    5,
    6,    7,   69,    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   46,   40,   44,   41,  125,    0,   41,   41,    3,
    0,    9,   41,   12,   46,    9,  257,   58,   60,   43,
   62,   45,    0,   60,    1,   62,   12,   60,   12,   62,
    1,   60,  270,   62,  272,   34,   60,    9,   62,   55,
   56,   41,   19,   43,    0,   45,   46,   24,   34,  134,
   34,   22,   68,    1,   42,   43,   46,   45,   46,   47,
   60,  146,   62,   41,   12,   43,    0,   45,   46,  257,
   47,   19,  257,  257,  259,   23,  264,  265,  263,    0,
  265,  265,   60,  268,   62,   41,   34,   43,   40,   45,
   46,   40,    0,  257,  273,   94,   67,  257,  258,   47,
  264,  265,  266,  267,   60,    0,   62,   43,   94,   45,
   94,   83,   84,   85,  257,   46,  259,  260,    0,   91,
  263,  257,  265,   95,   46,  268,   42,  261,  262,  101,
  102,   47,  104,  105,   41,  107,   43,   41,   45,   43,
  134,   45,   43,  115,   45,   46,   94,  145,  257,  269,
   46,  145,  146,  151,  257,  126,  259,  151,   57,   58,
  263,   46,  265,  266,  267,  268,   43,  138,   45,   46,
   40,  261,  262,  145,   44,  261,  262,   46,  270,  151,
  260,   46,   41,   41,  123,  257,   46,  269,   58,   46,
   46,   61,  271,  123,  123,   46,   46,   46,  258,   40,
  271,  262,   46,  262,  262,  262,   58,  258,   46,   46,
   40,   46,   46,   46,   46,   46,  257,    0,  260,  257,
  258,  266,  267,  260,   46,  266,  267,  260,   46,   58,
  262,  125,  274,  275,  276,  277,   46,  274,  275,  276,
  277,  274,  275,  276,  277,  274,  275,  276,  277,  125,
  274,  275,  276,  277,   31,   12,   -1,  257,   33,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  259,
   -1,  271,   -1,  263,  274,  275,  276,  277,  268,  257,
   -1,  259,  260,  261,  262,  263,  264,  265,  266,  267,
  268,   -1,   -1,  271,   -1,   -1,  274,  275,  276,  277,
   -1,  257,   -1,  259,  260,  261,  262,  263,  264,  265,
  266,  267,  268,   -1,   -1,  271,   -1,   -1,  274,  275,
  276,  277,   -1,  257,   -1,  259,   -1,  261,  262,  263,
  264,  265,  266,  267,  268,   -1,  257,  271,  259,   -1,
  261,  262,  263,  264,  265,  266,  267,  268,   -1,  257,
  271,  259,   -1,  261,  262,  263,   -1,  265,  266,  267,
  268,   -1,  257,  271,  259,   -1,  261,  262,  263,   -1,
  265,  266,  267,  268,   -1,  257,  271,  259,   -1,  261,
  262,  263,   -1,  265,  266,  267,  268,  257,  258,  271,
  259,   -1,  261,  262,  263,   -1,  266,  267,   -1,  268,
   -1,  257,  271,  259,   -1,  261,  262,  263,  257,  265,
  259,   -1,  268,   -1,  263,   -1,  265,  266,  267,  268,
  257,   -1,  259,   -1,   -1,  257,  263,   -1,  265,  266,
  267,  268,  264,  265,
};
}
final static short YYFINAL=8;
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
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"asignacion : IDENTIFICADOR '=' expresion",
"asignacion : IDENTIFICADOR expresion '.'",
"declaracion : IDENTIFICADOR ',' declaracion",
"declaracion : IDENTIFICADOR ':' tipo '.'",
"declaracion : IDENTIFICADOR declaracion",
"declaracion : IDENTIFICADOR ':' tipo",
"declaracion : IDENTIFICADOR tipo '.'",
"declaracion : IDENTIFICADOR ':' '.'",
"rep_switch : CASE CONSTANTE ':' bloque_comun '.'",
"rep_switch : rep_switch CASE CONSTANTE ':' bloque_comun '.'",
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

//#line 122 "gramatica.y"

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
//#line 417 "Parser.java"
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
//#line 23 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 7:
//#line 24 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 8:
//#line 25 "gramatica.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 9:
//#line 26 "gramatica.y"
{this.sintactico.showMessage("Bloque: BEGIN - END");}
break;
case 11:
//#line 29 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en bloque BEGIN - END");}
break;
case 12:
//#line 30 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END' en bloque BEGIN - END");}
break;
case 13:
//#line 31 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'BEGIN' en bloque BEGIN - END");}
break;
case 14:
//#line 34 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
break;
case 15:
//#line 35 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
break;
case 16:
//#line 36 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF - ELSE");}
break;
case 17:
//#line 37 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
break;
case 18:
//#line 38 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF - ELSE");}
break;
case 19:
//#line 39 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
break;
case 20:
//#line 42 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
break;
case 21:
//#line 43 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
break;
case 22:
//#line 44 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF");}
break;
case 23:
//#line 45 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
break;
case 24:
//#line 46 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF");}
break;
case 35:
//#line 64 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion");}
break;
case 36:
//#line 65 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
break;
case 37:
//#line 68 "gramatica.y"
{this.sintactico.showMessage("Llamado a función");}
break;
case 38:
//#line 71 "gramatica.y"
{this.sintactico.showMessage("Asignación");}
break;
case 39:
//#line 72 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignación");}
break;
case 40:
//#line 73 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignación");}
break;
case 41:
//#line 76 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable multiple");}
break;
case 42:
//#line 77 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable");}
break;
case 43:
//#line 78 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en asignación múltiple");}
break;
case 44:
//#line 79 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignación");}
break;
case 45:
//#line 80 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en asignación");}
break;
case 46:
//#line 81 "gramatica.y"
{this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta tipo en asignación");}
break;
case 47:
//#line 84 "gramatica.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 50:
//#line 89 "gramatica.y"
{this.sintactico.showMessage("Condición");}
break;
case 57:
//#line 100 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 58:
//#line 101 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 60:
//#line 105 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 61:
//#line 106 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 65:
//#line 114 "gramatica.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
//#line 714 "Parser.java"
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
