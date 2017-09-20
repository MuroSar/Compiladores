%token IDENTIFICADOR CONSTANTE IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE CADENA
%start programa
%%

programa : bloque_comun
		;

bloque_comun : bloque_para_funcion
		|llamado_funcion
		;
		
bloque_para_funcion :  IF '(' condicion ')' THEN bloque_comun ELSE bloque_comun END_IF'.'
		| IF '(' condicion ')' THEN bloque_comun END_IF'.'
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.'
		| BEGIN sentencias END'.'
		| sentencias
		;

sentencias : declaracion sentencias
		| asignacion sentencias
		| salida sentencias
		;
		
llamado_funcion : funcion 
		|funcion sentencias
		;

funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')''.' '}'
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')''.' '}'
		;

asignacion : IDENTIFICADOR '=' expresion'.'
		;

declaracion : IDENTIFICADOR ':' tipo'.'
		| IDENTIFICADOR',' declaracion
		;

rep_switch : CASE CONSTANTE ':' bloque_comun'.'
		| CASE CONSTANTE ':' bloque_comun'.' rep_switch
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