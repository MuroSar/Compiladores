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
		
bloque_main : bloque_comun
		| bloque_main bloque_comun
		;

bloque_comun : bloques
		| declaracion_funcion
		;
		
bloques : bloque_para_funcion
		| BEGIN bloque_para_funcion END'.' {this.sintactico.showMessage("Bloque: BEGIN - END");}	
		;
		
bloque_para_funcion : sentencia_if_else
		| sentencia_if
		| sentencia_switch
		| sentencias
		;

sentencia_if_else : IF '(' condicion ')' THEN bloques ELSE bloques END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}	
	/* ERRORES
		| IF error ELSE bloques END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
		| IF '(' error ELSE bloques  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF - ELSE");}
		| IF '(' condicion error ELSE bloques  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
		| IF '(' condicion ')' error ELSE bloques  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
		| IF '(' condicion ')' THEN error bloques  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
	ERRORES */
		;
		
sentencia_if : IF '(' condicion ')' THEN bloques END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
	/* ERRORES
		| IF error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
		| IF '(' error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF");}
		| IF '(' condicion error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
		| IF '(' condicion ')' error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
	 ERRORES */
		;
		
sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
	/* ERRORES
		| SWITCH error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia SWITCH");}
		| SWITCH '(' error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en sentencia SWITCH");}
	ERRORES */
		;
		
sentencias : sentencia_unica
		| sentencias sentencia_unica 
		;
		
sentencia_unica : declaracion 
		| asignacion
		| salida
		| llamado_funcion
		;

declaracion_funcion : tipo FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion con MOVE");} 
	/* ERRORES 
		| error FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'tipo' en declaracion de Funcion");}
		| tipo error IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de Funcion");}
		| tipo FUNCTION error '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de Funcion");}
		| tipo FUNCTION IDENTIFICADOR error bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de Funcion");}
	ERRORES */
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a función");}
	/* ERRORES 
		| IDENTIFICADOR error ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a Funcion");}
		| IDENTIFICADOR '('error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
	 ERRORES */
		;

asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignación");}
	/* ERRORES 
		| IDENTIFICADOR error expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignacion");}
	 ERRORES */
		;


declaracion : lista_variables ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
	/* ERRORES
		| lista_variables ':' error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'tipo' en declaracion de variable");}
	ERRORES */
		;

lista_variables : IDENTIFICADOR
		| lista_variables ',' IDENTIFICADOR
		;

rep_switch : CASE CONSTANTE ':' bloques {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloques  
	/* ERRORES
		| CASE CONSTANTE error bloques {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en sentencia CASE");}
	ERRORES */
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

expresion : expresion '+' termino {this.sintactico.showMessage("Expresión");}
		| expresion '-' termino {this.sintactico.showMessage("Expresión");}
		| termino
		;

termino : termino '*' factor {this.sintactico.showMessage("Término");}
		| termino '/' factor {this.sintactico.showMessage("Término");}
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