#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#define IF 257
#define THEN 258
#define ELSE 259
#define END 260
#define END_IF 261
#define FUNCTION 262
#define RETURN 263
#define SWITCH 264
#define CASE 265
#define OUT 266
#define LONG 267
#define DOUBLE 268
#define Identificador 269
#define Constante 270
#define Cadena 271
#define BEGIN 272
#define MOVE 273
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    1,    1,    1,    4,    4,    4,    4,
    7,    7,    6,    5,    5,    3,    3,    2,    2,   11,
   11,   11,   11,   11,   11,   10,   10,   10,   12,   12,
   12,   13,   13,    8,    9,    9,
};
short yylen[] = {                                         2,
    1,   10,    8,    8,    4,    1,    1,    1,    1,    1,
   10,   11,    4,    4,    3,    5,    6,    3,    3,    1,
    1,    1,    1,    1,    1,    3,    3,    1,    3,    3,
    1,    1,    1,    5,    1,    1,
};
short yydefred[] = {                                      0,
    0,    0,    0,   35,   36,    0,    0,    0,    1,    6,
    7,    8,    9,   10,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,   33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    0,
   20,   21,   22,   23,   24,   25,    0,    0,    0,    0,
    0,    0,    0,   13,   14,    5,    0,    0,    0,   18,
    0,   26,   27,   29,   30,    0,   34,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    3,    0,    4,    0,    0,    0,    0,    0,    0,
    2,    0,   11,    0,   17,   12,
};
short yydgoto[] = {                                       8,
    9,   27,   72,   10,   11,   12,   13,   14,   15,   28,
   47,   29,   30,
};
short yysindex[] = {                                   -224,
  -25,  -14,   -6,    0,    0,  -26, -217,    0,    0,    0,
    0,    0,    0,    0, -253, -223, -215, -213, -223, -211,
 -210, -200, -207, -199,    0,    0,   23,  -59,   -4,  -19,
   24,   25,   21,   26,  -36,    0,   27,  -54, -198, -188,
    0,    0,    0,    0,    0,    0, -223, -223, -223, -223,
 -223,  -49,   29,    0,    0,    0, -224,  -47, -224,    0,
   -4,    0,    0,    0,    0, -187,    0,   37, -224, -206,
 -191,  -44, -223,   40, -224,   36,   28,   38,   42, -223,
 -176,    0, -224,    0,   41,   47,   43,   44,  -33,   45,
    0, -187,    0,  -32,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -35,  -41,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -31,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -30,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
  -45,   49,    2,   90,   77,    0,    0,    0,   79,  -12,
    0,  -34,    0,
};
#define YYTABLESIZE 246
short yytable[] = {                                      31,
   41,   31,   42,   31,   31,   28,   33,   21,   23,   19,
   28,   68,   61,   70,   16,   64,   65,   21,   31,   24,
   31,   20,   50,   74,   28,   17,   28,   51,   28,   81,
   28,   20,    1,   18,   19,   62,   63,   88,   48,    2,
   49,    3,    4,    5,    6,   25,   26,    7,    3,    4,
    5,    6,   75,   31,   76,    4,    5,   32,   35,   37,
   79,   38,   39,   40,   52,   53,   54,   86,   57,   59,
   58,   55,   56,   66,   67,   69,   73,   71,   77,   80,
   78,   82,   85,   84,   87,   83,   89,   90,   91,   92,
   94,   93,   96,   95,   16,   60,   22,   36,   34,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   43,   44,   45,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,   31,   31,   31,    0,    0,   28,   28,
   28,   28,   28,   28,   28,   28,
};
short yycheck[] = {                                      41,
   60,   43,   62,   45,   46,   41,   19,   44,  262,   41,
   46,   57,   47,   59,   40,   50,   51,   44,   60,  273,
   62,   58,   42,   69,   60,   40,   62,   47,   60,   75,
   62,   58,  257,   40,   61,   48,   49,   83,   43,  264,
   45,  266,  267,  268,  269,  269,  270,  272,  266,  267,
  268,  269,  259,  269,  261,  267,  268,  271,  269,  260,
   73,  269,  262,   41,   41,   41,   46,   80,  123,  258,
  269,   46,   46,  123,   46,  123,   40,  265,  270,   40,
  125,   46,   41,   46,  261,   58,   46,   41,   46,   46,
   46,  125,  125,   92,  125,   47,    7,   21,   20,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,   -1,   -1,  274,  275,
  276,  277,  274,  275,  276,  277,
};
#define YYFINAL 8
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 277
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'","'.'","'/'",0,0,0,0,0,0,0,0,0,0,
"':'",0,"'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,"'}'",
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,"IF","THEN","ELSE","END","END_IF","FUNCTION","RETURN",
"SWITCH","CASE","OUT","LONG","DOUBLE","Identificador","Constante","Cadena",
"BEGIN","MOVE","\"<=\"","\">=\"","\"<>\"","\"==\"",
};
char *yyrule[] = {
"$accept : programa",
"programa : bloque",
"bloque : IF '(' condicion ')' THEN bloque ELSE bloque END_IF '.'",
"bloque : IF '(' condicion ')' THEN bloque END_IF '.'",
"bloque : SWITCH '(' Identificador ')' '{' rep_switch '}' '.'",
"bloque : BEGIN sentencias END '.'",
"bloque : sentencias",
"sentencias : declaracion",
"sentencias : asignacion",
"sentencias : funcion",
"sentencias : salida",
"funcion : tipo FUNCTION Identificador '{' bloque '(' expresion ')' '.' '}'",
"funcion : tipo MOVE FUNCTION Identificador '{' bloque '(' expresion ')' '.' '}'",
"asignacion : Identificador '=' expresion '.'",
"declaracion : Identificador ':' tipo '.'",
"declaracion : Identificador ',' declaracion",
"rep_switch : CASE Constante ':' bloque '.'",
"rep_switch : CASE Constante ':' bloque '.' rep_switch",
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
"factor : Identificador",
"factor : Constante",
"salida : OUT '(' Cadena ')' '.'",
"tipo : LONG",
"tipo : DOUBLE",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
