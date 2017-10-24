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






//#line 2 "gramaticaCorregida.y"
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
    0,    1,    1,    2,    2,    3,    3,    5,    5,    7,
    7,    9,    9,    9,   10,   10,   10,    8,    8,    8,
    8,    4,    4,    6,   11,   12,   13,   21,   21,   14,
   15,   16,   19,   19,   20,   20,   22,   22,   22,   22,
   22,   22,   18,   18,   18,   23,   23,   23,   24,   24,
   17,   17,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    1,    1,    4,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    2,   11,   12,    4,   10,    8,    8,    4,    5,    4,
    5,    4,    1,    3,    3,    3,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    1,    1,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   51,   52,    0,    0,    0,    2,
    4,    5,    6,    7,    8,   10,   11,   12,   13,   14,
   15,   16,   17,    0,    0,    0,    0,    0,    0,    0,
   19,   18,    0,    0,    3,    0,    0,    0,    0,    0,
   49,   50,    0,    0,   48,    0,    0,    0,   21,   20,
    0,    0,    0,    0,    0,   34,   32,   30,    0,    0,
    0,    0,   37,   38,   39,   40,   41,   42,    0,    0,
    0,    9,    0,    0,    0,    0,   24,    0,    0,   46,
   47,    0,    0,    0,   31,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   26,    0,    0,   27,    0,    0,    0,   28,    0,
    0,    0,   25,   29,    0,    0,   22,    0,   23,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   12,   13,   14,   15,   30,   16,   17,
   18,   19,   20,   21,   22,   23,   24,   43,   25,   47,
   91,   69,   44,   45,
};
final static short yysindex[] = {                      -207,
  -33,  -17, -172,   24,    0,    0,   40,    0, -207,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -200,  -20,   53, -204, -204,  -33, -202,
    0,    0, -165, -148,    0, -147, -159, -164, -145,   67,
    0,    0,   55,   37,    0,  -11,  -23,   68,    0,    0,
   74,   75,   -6, -139,   73,    0,    0,    0, -204, -204,
 -204, -204,    0,    0,    0,    0,    0,    0, -204, -140,
 -204,    0,   76,   -2, -192,    1,    0,   37,   37,    0,
    0,   37, -182,   46,    0, -146, -144, -192, -157, -133,
 -124,   86, -143, -182,   83,   72, -127,   87, -204,   92,
 -128,    0, -182,   77,    0,   47, -204,   90,    0, -182,
   91,   54,    0,    0,   13,   93,    0,   15,    0,
};
final static short yyrindex[] = {                         0,
  -14,    0,    0,    0,    0,    0,    0,    0,  141,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -41,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -35,  -29,    0,
    0,  -19,    0,  -15,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  133,  -40,    0,  -74,    0,    0,    0,   10,   12,
    0,    0,    0,    0,    0,    0,  105,  -25,    0,    0,
    0,   97,    9,   45,
};
final static int YYTABLESIZE=266;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         45,
   98,   45,   46,   45,   45,   43,   26,   43,   89,   43,
   43,   44,   31,   44,   32,   44,   44,   70,   45,  101,
   45,   36,   28,   39,   43,   35,   43,   27,  109,   33,
   44,   59,   44,   60,   87,  114,   63,   38,   64,   49,
   36,   50,   36,   33,   35,   84,   35,   93,   63,    1,
   64,    2,   41,   42,   29,    3,    2,    4,    5,    6,
    7,   48,    4,   33,    1,    7,    2,   78,   79,   36,
    3,   37,    4,  106,   29,    7,    2,   82,   61,   34,
    3,  112,    4,   62,   29,    7,    2,  111,   59,   59,
   60,   60,    4,   40,  116,    7,   59,   59,   60,   60,
   58,    5,    6,   94,   95,   80,   81,   51,   52,   53,
   54,   56,   57,   72,   73,   74,   75,   76,   77,   83,
   86,   85,   90,   88,   96,   99,   92,  100,  102,  103,
  104,  107,  105,  108,  110,  113,  115,  117,  118,  119,
    1,   35,   55,   71,   97,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   45,   45,   45,   45,    0,    0,   43,   43,
   43,   43,    0,    0,   44,   44,   44,   44,    0,    0,
   65,   66,   67,   68,   36,   36,   36,   36,   35,   35,
   35,   35,   65,   66,   67,   68,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
  125,   43,   28,   45,   46,   41,   40,   43,   83,   45,
   46,   41,    3,   43,    3,   45,   46,   41,   60,   94,
   62,   41,   40,   44,   60,   41,   62,   61,  103,   44,
   60,   43,   62,   45,   75,  110,   60,   58,   62,   30,
   60,   30,   62,   58,   60,   71,   62,   88,   60,  257,
   62,  259,  257,  258,  257,  263,  259,  265,  266,  267,
  268,  264,  265,   40,  257,  268,  259,   59,   60,  270,
  263,  272,  265,   99,  257,  268,  259,   69,   42,   40,
  263,  107,  265,   47,  257,  268,  259,   41,   43,   43,
   45,   45,  265,   41,   41,  268,   43,   43,   45,   45,
   46,  266,  267,  261,  262,   61,   62,  273,  257,  257,
  270,  257,   46,   46,   41,   41,  123,  257,   46,  260,
  123,   46,  269,  123,  258,   40,  271,  271,   46,   58,
  258,   40,   46,  262,   58,   46,   46,  125,   46,  125,
    0,    9,   38,   47,  269,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,   -1,   -1,  274,  275,
  276,  277,   -1,   -1,  274,  275,  276,  277,   -1,   -1,
  274,  275,  276,  277,  274,  275,  276,  277,  274,  275,
  276,  277,  274,  275,  276,  277,
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
"programa : bloque_main",
"bloque_main : bloque",
"bloque_main : bloque_main bloque",
"bloque : bloque_comun",
"bloque : declaracion_funcion",
"bloque_comun : bloque_control",
"bloque_comun : declaracion",
"bloque_control : bloque_sentencias",
"bloque_control : BEGIN cjto_sentencias_control END '.'",
"bloque_sentencias : sentencia_unica_control",
"bloque_sentencias : sentencia_unica_ejecutable",
"sentencia_unica_control : sentencia_if_else",
"sentencia_unica_control : sentencia_if",
"sentencia_unica_control : sentencia_switch",
"sentencia_unica_ejecutable : asignacion",
"sentencia_unica_ejecutable : salida",
"sentencia_unica_ejecutable : llamado_funcion",
"cjto_sentencias_control : sentencia_unica_ejecutable",
"cjto_sentencias_control : sentencia_unica_control",
"cjto_sentencias_control : cjto_sentencias_control sentencia_unica_ejecutable",
"cjto_sentencias_control : cjto_sentencias_control sentencia_unica_control",
"declaracion_funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_comun RETURN '(' expresion ')' '.' '}'",
"declaracion_funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_comun RETURN '(' expresion ')' '.' '}'",
"declaracion : lista_variables ':' tipo '.'",
"sentencia_if_else : IF '(' condicion ')' THEN bloque_control ELSE bloque_control END_IF '.'",
"sentencia_if : IF '(' condicion ')' THEN bloque_control END_IF '.'",
"sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"rep_switch : CASE CONSTANTE ':' bloque_control",
"rep_switch : rep_switch CASE CONSTANTE ':' bloque_control",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"salida : OUT '(' CADENA ')' '.'",
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
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
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 118 "gramaticaCorregida.y"

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
//#line 356 "Parser.java"
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
case 22:
//#line 53 "gramaticaCorregida.y"
{this.sintactico.showMessage("Declaracion de Funcion");}
break;
case 23:
//#line 54 "gramaticaCorregida.y"
{this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
break;
case 24:
//#line 57 "gramaticaCorregida.y"
{this.sintactico.showMessage("Declaracion de variable");}
break;
case 25:
//#line 60 "gramaticaCorregida.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 26:
//#line 63 "gramaticaCorregida.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 27:
//#line 66 "gramaticaCorregida.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 28:
//#line 69 "gramaticaCorregida.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 30:
//#line 73 "gramaticaCorregida.y"
{this.sintactico.showMessage("Asignación");}
break;
case 31:
//#line 76 "gramaticaCorregida.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
case 32:
//#line 79 "gramaticaCorregida.y"
{this.sintactico.showMessage("Llamado a función");}
break;
case 36:
//#line 87 "gramaticaCorregida.y"
{this.sintactico.showMessage("Condición");}
break;
//#line 549 "Parser.java"
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
