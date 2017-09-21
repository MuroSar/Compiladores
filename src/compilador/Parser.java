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
    0,    1,    1,    2,    2,    2,    2,    2,    6,    6,
    6,    6,    6,    6,    6,    6,    3,    3,   11,   11,
   10,    8,    7,    7,    5,    5,    4,    4,   14,   14,
   14,   14,   14,   14,   13,   13,   13,   15,   15,   15,
   16,   16,    9,   12,   12,
};
final static short yylen[] = {                            2,
    1,    1,    1,   10,    8,    8,    4,    1,    2,    2,
    2,    1,    1,    1,    1,    2,    1,    2,   11,   12,
    4,    4,    4,    3,    5,    6,    3,    3,    1,    1,
    1,    1,    1,    1,    3,    3,    1,    3,    3,    1,
    1,    1,    5,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   44,   45,    0,    0,    1,    2,
    3,    8,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    9,   10,   11,   16,
   18,    0,    0,    0,   41,   42,    0,    0,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,   21,
   22,    0,    0,    0,    0,   23,    0,   29,   30,   31,
   32,   33,   34,    0,    7,    0,    0,    0,    0,   35,
   36,   38,   39,    0,   27,    0,   43,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    5,    0,    6,    0,    0,    0,    0,    0,
    0,    4,    0,    0,    0,   26,   19,    0,   20,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   43,   83,   12,   13,   14,   15,   16,
   17,   18,   44,   64,   38,   39,
};
final static short yysindex[] = {                      -186,
  -16,  -37, -227,  -21,    0,    0,   -1,    0,    0,    0,
    0,    0, -227, -227, -227, -227, -227, -226,    2, -209,
 -192, -204, -209, -202, -216, -197,    0,    0,    0,    0,
    0, -188, -194,   40,    0,    0,   41,   27,  -10,   42,
  -33,    0,   48,  -44,   44,   50,   52,  -29, -165,    0,
    0, -209, -209, -209, -209,    0, -164,    0,    0,    0,
    0,    0,    0, -209,    0,   49,  -26, -207,  -25,    0,
    0,    0,    0, -186,    0,   27,    0, -170, -171, -207,
 -178, -157,  -23,   63, -167, -186,   59,   53,   60, -209,
   67, -154,    0, -186,    0,   68, -209,   64,   66,   69,
   72,    0, -170,  -11,   70,    0,    0,   -8,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    5,    9,   13,   17,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -31,  -39,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -27,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -7,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -74,  -46,    0,   55,   18,   51,   98,    0,    0,    0,
    0,  101,  -12,    0,  -28,    0,
};
final static int YYTABLESIZE=284;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         81,
   12,   40,   23,   40,   13,   40,   40,   37,   14,   37,
   22,   92,   15,   28,   37,   58,   17,   59,   25,   99,
   40,   79,   40,   19,   21,   72,   73,   22,   37,    1,
   37,   54,   37,   85,   37,   76,   55,    4,   26,   70,
   71,   21,   34,   32,   20,   33,   12,   35,   36,    1,
   13,    2,   41,   24,   14,    3,   46,    4,   15,   47,
    7,   45,   17,   27,   28,   29,   30,   31,   48,   52,
    1,   53,    2,    5,    6,   49,    3,   96,    4,    5,
    6,    7,   86,   87,  101,   50,   51,   56,   57,   65,
   66,   69,   67,   68,   77,   74,   78,   80,   82,   84,
   88,   89,   90,   91,   93,   95,   97,   98,  100,  102,
   94,  103,  105,  107,  104,  108,  109,   25,   75,   42,
  106,   40,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   61,   62,   63,    0,   40,   40,   40,   40,    0,    0,
    0,    0,   37,   37,   37,   37,   37,   37,   37,   37,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   12,   12,    0,   12,   13,   13,    0,   13,   14,
   14,   12,   14,   15,   15,   13,   15,   17,   17,   14,
    0,    0,    0,   15,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         74,
    0,   41,   40,   43,    0,   45,   46,   20,    0,   41,
   44,   86,    0,   41,   46,   60,    0,   62,   40,   94,
   60,   68,   62,   40,   58,   54,   55,   44,   60,  257,
   62,   42,   60,   80,   62,   64,   47,  265,   40,   52,
   53,   58,   41,  270,   61,  272,   46,  257,  258,  257,
   46,  259,  257,    3,   46,  263,  273,  265,   46,  257,
  268,  264,   46,   13,   14,   15,   16,   17,  257,   43,
  257,   45,  259,  266,  267,  270,  263,   90,  265,  266,
  267,  268,  261,  262,   97,   46,   46,   46,   41,   46,
   41,  257,   41,  123,   46,  260,  123,  123,  269,  271,
  258,  125,   40,  271,   46,   46,   40,  262,   41,   46,
   58,   46,   41,  125,   46,   46,  125,  125,   64,   22,
  103,   21,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,   -1,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,  274,  275,  276,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  261,  262,   -1,  264,  261,  262,   -1,  264,  261,
  262,  271,  264,  261,  262,  271,  264,  261,  262,  271,
   -1,   -1,   -1,  271,
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
"bloque_comun : bloque_para_funcion",
"bloque_comun : declaracion_funcion",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_comun ELSE bloque_comun END_IF '.'",
"bloque_para_funcion : IF '(' condicion ')' THEN bloque_comun END_IF '.'",
"bloque_para_funcion : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' '.'",
"bloque_para_funcion : BEGIN sentencias END '.'",
"bloque_para_funcion : sentencias",
"sentencias : declaracion sentencias",
"sentencias : asignacion sentencias",
"sentencias : salida sentencias",
"sentencias : declaracion",
"sentencias : asignacion",
"sentencias : salida",
"sentencias : llamado_funcion",
"sentencias : llamado_funcion sentencias",
"declaracion_funcion : funcion",
"declaracion_funcion : funcion sentencias",
"funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '.' '}'",
"llamado_funcion : IDENTIFICADOR '(' ')' '.'",
"asignacion : IDENTIFICADOR '=' expresion '.'",
"declaracion : IDENTIFICADOR ':' tipo '.'",
"declaracion : IDENTIFICADOR ',' declaracion",
"rep_switch : CASE CONSTANTE ':' bloque_comun '.'",
"rep_switch : CASE CONSTANTE ':' bloque_comun '.' rep_switch",
"condicion : expresion operador condicion",
"condicion : expresion operador termino",
"operador : '<'",
"operador : '>'",
"operador : \"<=\"",
"operador : \">=\"",
"operador : \"<>\"",
"operador : \"==\"",
"expresion : termino '+' expresion",
"expresion : termino '-' expresion",
"expresion : termino",
"termino : factor '*' termino",
"termino : factor '/' termino",
"termino : factor",
"factor : IDENTIFICADOR",
"factor : CONSTANTE",
"salida : OUT '(' CADENA ')' '.'",
"tipo : LONG",
"tipo : DOUBLE",
};

//#line 94 "gramatica.y"

private Lexico lexico;
private Sintactico sintactico;



public void setLexico(Lexico lexico)
{
	this.lexico = lexico;
}
public void setSintactico(Sintactico sintactico) {
	this.sintactico = sintactico;	
}
private int yylex() {
	Token token = lexico.getToken();
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
//#line 346 "Parser.java"
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
case 4:
//#line 21 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF - ELSE");}
break;
case 5:
//#line 22 "gramatica.y"
{this.sintactico.showMessage("Sentencia: IF");}
break;
case 6:
//#line 23 "gramatica.y"
{this.sintactico.showMessage("Sentencia: SWITCH");}
break;
case 7:
//#line 24 "gramatica.y"
{this.sintactico.showMessage("Bloque: BEGIN - END");}
break;
case 19:
//#line 42 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion");}
break;
case 20:
//#line 43 "gramatica.y"
{this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
break;
case 21:
//#line 46 "gramatica.y"
{this.sintactico.showMessage("Llamado a función");}
break;
case 22:
//#line 49 "gramatica.y"
{this.sintactico.showMessage("Asignación");}
break;
case 23:
//#line 52 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable");}
break;
case 24:
//#line 53 "gramatica.y"
{this.sintactico.showMessage("Declaracion de variable multiple");}
break;
case 25:
//#line 56 "gramatica.y"
{this.sintactico.showMessage("Sentencia: CASE");}
break;
case 28:
//#line 61 "gramatica.y"
{this.sintactico.showMessage("Condición");}
break;
case 35:
//#line 72 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 36:
//#line 73 "gramatica.y"
{this.sintactico.showMessage("Expresión");}
break;
case 38:
//#line 77 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 39:
//#line 78 "gramatica.y"
{this.sintactico.showMessage("Término");}
break;
case 43:
//#line 86 "gramatica.y"
{this.sintactico.showMessage("Sentencia: OUT");}
break;
//#line 563 "Parser.java"
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
