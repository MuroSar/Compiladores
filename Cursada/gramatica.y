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
		| declaracion_funcion
		;
		
bloque_para_funcion :  IF '(' condicion ')' THEN bloque_comun ELSE bloque_comun END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}
		| IF '(' condicion ')' THEN bloque_comun END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
		| BEGIN sentencias END'.' {this.sintactico.showMessage("Bloque: BEGIN - END");}
		| sentencias
		;

sentencias : declaracion sentencias
		| asignacion sentencias
		| salida sentencias
		| declaracion 
		| asignacion
		| salida
		| llamado_funcion
		| llamado_funcion sentencias
		;
		
declaracion_funcion : funcion 
		|funcion sentencias
		;

funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a función");}
		;

asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignación");}
		;

declaracion : IDENTIFICADOR ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
		| IDENTIFICADOR',' declaracion  {this.sintactico.showMessage("Declaracion de variable multiple");}
		;

rep_switch : CASE CONSTANTE ':' bloque_comun'.' {this.sintactico.showMessage("Sentencia: CASE");}
		| CASE CONSTANTE ':' bloque_comun'.' rep_switch
		;

condicion : expresion operador condicion 
		| expresion operador termino {this.sintactico.showMessage("Condición");}
		;

operador : '<' 
		| '>'
		| '<='
		| '>='
		| '<>'
		| '=='
		;

expresion : termino '+' expresion {this.sintactico.showMessage("Expresión");}
		| termino '-' expresion {this.sintactico.showMessage("Expresión");}
		| termino
		;

termino : factor '*' termino {this.sintactico.showMessage("Término");}
		| factor '/' termino {this.sintactico.showMessage("Término");}
		| factor
		;

factor : IDENTIFICADOR 
		| CONSTANTE
		;

salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
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