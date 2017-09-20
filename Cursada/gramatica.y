%token IDENTIFICADOR CONSTANTE IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE CADENA
%start programa

%%

programa : bloque
		;

bloque : IF '(' condicion ')' THEN bloque ELSE bloque END_IF'.'
		| IF '(' condicion ')' THEN bloque END_IF'.'
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.'
		| BEGIN sentencias END'.'
		| sentencias
		;

sentencias : declaracion
		| asignacion
		| funcion
		| salida
		;

funcion : tipo FUNCTION IDENTIFICADOR '{' bloque '(' expresion ')''.' '}'
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque '(' expresion ')''.' '}'
		;

asignacion : IDENTIFICADOR '=' expresion'.'
		;

declaracion : IDENTIFICADOR ':' tipo'.'
		| IDENTIFICADOR',' declaracion
		;

rep_switch : CASE CONSTANTE ':' bloque'.'
		| CASE CONSTANTE ':' bloque'.' rep_switch
		;

condicion : expresion operador condicion
		| expresion operador termino
		;

operador : '<'
		| '>'
		| '<='
		| '>='
		| '<>'
		| '=='
		;

expresion : termino '+' expresion
		| termino '-' expresion
		| termino
		;

termino : factor '*' termino
		| factor '/' termino
		| factor
		;

factor : IDENTIFICADOR
		| CONSTANTE
		;

salida : OUT '(' CADENA ')''.'
		;

tipo : LONG
		| DOUBLE
		;
