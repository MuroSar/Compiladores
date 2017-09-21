%{
package compilador;

import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
%}

%token IDENTIFICADOR CONSTANTE IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE CADENA
%start programa

%%

programa : bloque_comun
		;

bloque_comun : bloque_para_funcion
		|llamado_funcion
		;
		
bloque_para_funcion :  IF '(' condicion ')' THEN bloque_comun ELSE bloque_comun END_IF'.'
		| IF '(' condicion ')' THEN bloque_comun END_IF'.' {his.sintactico.showMessage("Sentencia: IF");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.'
		| BEGIN sentencias END'.'
		| sentencias
		;

sentencias : declaracion sentencias
		| asignacion sentencias
		| salida sentencias
		| declaracion 
		| asignacion
		| salida
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
		
%%

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