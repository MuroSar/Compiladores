%{
package compilador;

import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
%}

%token IDENTIFICADOR CONSTANTE IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE CADENA
%start programa

%%

programa : bloque_main
		;
		
bloque_main : bloque	
		| bloque_main bloque		
		;
		
bloque : bloque_comun
		| declaracion_funcion
		;
		
bloque_comun : bloque_control
		| declaracion
		;
		
bloque_control : bloque_sentencias
		| BEGIN cjto_sentencias_control END'.'
		;

bloque_sentencias : sentencia_unica_control
		| sentencia_unica_ejecutable
		;
		
sentencia_unica_control : sentencia_if_else
		| sentencia_if
		| sentencia_switch
		;
		
sentencia_unica_ejecutable : asignacion
		| salida
		| llamado_funcion
		;		
	
cjto_sentencias_control : sentencia_unica_ejecutable
		| sentencia_unica_control
		| cjto_sentencias_control sentencia_unica_ejecutable
		| cjto_sentencias_control sentencia_unica_control
		;

declaracion_funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_comun RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_comun RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion con MOVE");} 
		;
				
declaracion : lista_variables ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
		;
		
sentencia_if_else : IF '(' condicion ')' THEN bloque_control ELSE bloque_control END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}	
		;
		
sentencia_if : IF '(' condicion ')' THEN bloque_control END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
		;
		
sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
		;
		
rep_switch : CASE CONSTANTE ':' bloque_control {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloque_control  
		;
		
asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignación");}
		;
		
salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a función");}
		;
		
lista_variables : IDENTIFICADOR
		| lista_variables ',' IDENTIFICADOR
		;
		
condicion : condicion operador expresion
		| expresion operador termino {this.sintactico.showMessage("Condición");}
		;

operador : '<' 
		| '>'
		| '<='
		| '>='
		| '<>'
		| '=='
		;

expresion : expresion '+' termino 
		| expresion '-' termino 
		| termino
		;

termino : termino '*' factor 
		| termino '/' factor 
		| factor
		;

factor : IDENTIFICADOR
		| CONSTANTE
		;


tipo : LONG
		| DOUBLE
		;
		
%%

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