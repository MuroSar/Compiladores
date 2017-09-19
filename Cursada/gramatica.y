%token Identificador Constante IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE Cadena
%start programa

%%

programa : bloque
		;

bloque : IF '(' condicion ')' THEN bloque ELSE bloque END_IF'.'
		| IF '(' condicion ')' THEN bloque END_IF'.'
		| SWITCH '(' Identificador ')' '{' rep_switch '}''.'
		| BEGIN sentencias END'.'
		| sentencias
		;

sentencias : declaracion
		| asignacion
		| funcion
		| salida
		;

funcion : tipo FUNCTION Identificador '{' bloque '(' expresion ')''.' '}'
		| tipo MOVE FUNCTION Identificador '{' bloque '(' expresion ')''.' '}'
		;

asignacion : Identificador '=' expresion'.'
		;

declaracion : Identificador ':' tipo'.'
		| Identificador',' declaracion
		;

rep_switch : CASE Constante ':' bloque'.'
		| CASE Constante ':' bloque'.' rep_switch
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

factor : Identificador
		| Constante
		;

salida : OUT '(' Cadena ')''.'
		;

tipo : LONG
		| DOUBLE
		;
