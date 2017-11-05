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
import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
//#line 31 "Parser.java"




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
   11,   12,   12,   12,    3,    3,    5,   13,   14,   15,
   22,   22,   16,   17,   18,   20,   20,   21,   21,   23,
   23,   23,   23,   23,   23,    8,    8,    8,   24,   24,
   24,   25,   25,   19,   19,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    8,    1,    1,
    2,    2,    1,    4,    1,    2,    1,    1,    1,    1,
    1,    1,    1,    1,    4,    5,    4,   10,    8,    8,
    4,    5,    4,    5,    4,    1,    3,    3,    3,    1,
    1,    1,    1,    1,    1,    3,    3,    1,    3,    3,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   54,   55,    0,    0,    0,    2,
    3,    6,    7,   13,   17,   18,   19,   20,   21,   22,
   23,   24,    0,    0,    0,    0,    0,    0,   15,    0,
    0,    0,    4,    5,    0,    0,    0,    0,    0,   52,
   53,    0,    0,   51,    0,    0,    0,   16,    0,    0,
    0,    0,    0,   37,   35,   33,    0,    0,    0,    0,
   40,   41,   42,   43,   44,   45,    0,    0,    0,   14,
    0,    0,    0,   25,    0,   27,    0,    0,   49,   50,
    0,    0,    0,   34,    0,   10,    0,    9,   26,    0,
    0,    0,    0,   12,   11,    0,    0,    0,    0,    0,
    0,    0,   29,    0,    0,   30,    0,    0,   31,    0,
    0,   28,   32,    0,    8,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   12,   13,   74,   87,   42,   14,   30,
   15,   16,   17,   18,   19,   20,   21,   22,   23,   24,
   46,   92,   67,   43,   44,
};
final static short yysindex[] = {                      -199,
  -33,  -37, -173,  -17,    0,    0,   -5,    0, -199,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -197,   -4,  -12, -155, -155,  -33,    0, -187,
 -231, -174,    0,    0, -163, -164, -162, -160,   65,    0,
    0,   55,  -27,    0,  -11,  -23,   68,    0,   74,   75,
   -6, -139,   73,    0,    0,    0, -155, -155, -155, -155,
    0,    0,    0,    0,    0,    0, -155, -140, -155,    0,
   76,   -2, -169,    0,   -6,    0,  -27,  -27,    0,    0,
   46, -183,   46,    0, -146,    0, -209,    0,    0, -154,
 -134, -124,   85,    0,    0, -183,   80,   69, -130,   83,
 -155, -132,    0, -183,   77,    0,   20,   86,    0, -183,
   87,    0,    0,    9,    0,
};
final static short yyrindex[] = {                         0,
   13,    0,    0,    0,    0,    0,    0,    0,  131,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -41,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -35,  -29,    0,    0,
  -19,    0,  -15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  127,  128,  -58,  -43,   63,    0,  -14,    6,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  102,    0,
    0,    0,   94,   52,   53,
};
final static int YYTABLESIZE=266;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
  100,   48,   27,   48,   48,   46,   25,   46,   29,   46,
   46,   47,   45,   47,   59,   47,   47,   68,   48,   60,
   48,   39,   31,   90,   46,   38,   46,   26,   39,   86,
   47,   57,   47,   58,   32,   48,   61,  102,   62,   38,
   39,   49,   39,   94,   38,  109,   38,    1,   61,    2,
   62,  113,   81,   37,   83,    4,   36,    1,    7,    2,
  111,   93,   57,    3,   58,    4,    5,    6,    7,   28,
   36,    2,   35,   28,   36,    2,   47,    4,   88,    3,
    7,    4,   50,   28,    7,    2,  107,    1,   57,    2,
   58,    4,   95,   51,    7,    4,   54,   57,    7,   58,
   56,   40,   41,    5,    6,   52,   96,   97,   77,   78,
   55,   79,   80,   70,   71,   72,   73,   75,   76,   82,
   85,   84,   91,   98,  101,  103,  104,  105,  106,  108,
    1,  112,  114,  115,  110,   33,   34,   89,   53,   69,
    0,    0,    0,    0,   99,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   48,   48,   48,   48,    0,    0,   46,   46,
   46,   46,    0,    0,   47,   47,   47,   47,    0,    0,
   63,   64,   65,   66,   39,   39,   39,   39,   38,   38,
   38,   38,   63,   64,   65,   66,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
  125,   43,   40,   45,   46,   41,   40,   43,    3,   45,
   46,   41,   27,   43,   42,   45,   46,   41,   60,   47,
   62,   41,   40,   82,   60,   41,   62,   61,   41,   73,
   60,   43,   62,   45,   40,   30,   60,   96,   62,   44,
   60,  273,   62,   87,   60,  104,   62,  257,   60,  259,
   62,  110,   67,   58,   69,  265,   44,  257,  268,  259,
   41,  271,   43,  263,   45,  265,  266,  267,  268,  257,
   58,  259,  270,  257,  272,  259,  264,  265,   73,  263,
  268,  265,  257,  257,  268,  259,  101,  257,   43,  259,
   45,  265,   87,  257,  268,  265,  257,   43,  268,   45,
   46,  257,  258,  266,  267,  270,  261,  262,   57,   58,
   46,   59,   60,   46,   41,   41,  123,  257,   46,  260,
  123,   46,  269,  258,   40,   46,   58,  258,   46,  262,
    0,   46,   46,  125,   58,    9,    9,   75,   37,   46,
   -1,   -1,   -1,   -1,  269,   -1,   -1,   -1,   -1,   -1,
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
"sentencia_unica_control : sentencia_if_else",
"sentencia_unica_control : sentencia_if",
"sentencia_unica_control : sentencia_switch",
"sentencia_unica_ejecutable : asignacion",
"sentencia_unica_ejecutable : salida",
"sentencia_unica_ejecutable : llamado_funcion",
"declaracion_funcion : tipo FUNCTION IDENTIFICADOR bloque_funcion",
"declaracion_funcion : tipo MOVE FUNCTION IDENTIFICADOR bloque_funcion",
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
"condicion : expresion operador expresion",
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

//#line 151 "gramaticaCorregidaBarbie.y"

private Lexico lexico;
private Sintactico sintactico;
private Token token;

public boolean estaDeclarada(String lexema) {
	return lexico.estaDeclarada(lexema);
}

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
//#line 373 "Parser.java"
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
//#line 44 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Bloque BEGIN-END");}
break;
case 25:
//#line 65 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");}
break;
case 26:
//#line 66 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");}
break;
case 27:
//#line 69 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables(val_peek(3), val_peek(1));}
break;
case 28:
//#line 73 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 29:
//#line 76 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 30:
//#line 79 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 31:
//#line 82 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 33:
//#line 86 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Asignaci\u00f3n"); 
										 	yyval = new ParserVal(new TercetoAsignacion(val_peek(3), val_peek(1), this.sintactico.getTercetos().size()));
											Terceto t =  new TercetoAsignacion(val_peek(3), val_peek(1), this.sintactico.getTercetos().size());
											this.sintactico.addTerceto(t);}
break;
case 34:
//#line 92 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
case 35:
//#line 95 "gramaticaCorregidaBarbie.y"
{this.sintactico.showMessage("Llamado a funci\u00f3n");}
break;
case 36:
//#line 98 "gramaticaCorregidaBarbie.y"
{ yyval.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)(yyval.obj)).add(val_peek(0));}
break;
case 37:
//#line 101 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)val_peek(2).obj).add(val_peek(0));
                                              ((ArrayList<ParserVal>)yyval.obj).addAll((ArrayList<ParserVal>)val_peek(2).obj);}
break;
case 39:
//#line 107 "gramaticaCorregidaBarbie.y"
{ this.sintactico.showMessage("Condici\u00f3n");
									   yyval = new ParserVal(new TercetoComparador(val_peek(1), val_peek(2), val_peek(0), this.sintactico.getTercetos().size()));
									   Terceto t =  new TercetoComparador(val_peek(1), val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
									   this.sintactico.addTerceto(t);}
break;
case 46:
//#line 121 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new TercetoSuma(val_peek(2), val_peek(0), this.sintactico.getTercetos().size()));
									Terceto t =  new TercetoSuma(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
									this.sintactico.addTerceto(t);}
break;
case 47:
//#line 125 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new TercetoResta(val_peek(2), val_peek(0), this.sintactico.getTercetos().size()));
								  Terceto t =  new TercetoResta(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
								  this.sintactico.addTerceto(t);}
break;
case 49:
//#line 131 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new TercetoMultiplicacion(val_peek(2), val_peek(0), this.sintactico.getTercetos().size()));
							   Terceto t =  new TercetoMultiplicacion(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
							   this.sintactico.addTerceto(t);}
break;
case 50:
//#line 135 "gramaticaCorregidaBarbie.y"
{ yyval = new ParserVal(new TercetoDivision(val_peek(2), val_peek(0), this.sintactico.getTercetos().size()));
							   Terceto t =  new TercetoDivision(val_peek(2), val_peek(0), this.sintactico.getTercetos().size());
							   this.sintactico.addTerceto(t);}
break;
//#line 612 "Parser.java"
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
