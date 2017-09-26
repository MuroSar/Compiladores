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

bloque_comun : bloque_comun bloque_para_funcion
		| bloque_comun declaracion_funcion
		| bloque_para_funcion
		| declaracion_funcion
		;
		
bloque_para_funcion :  IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}
		| IF '(' condicion ')' THEN bloque_para_funcion END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
		| BEGIN sentencias END'.' {this.sintactico.showMessage("Bloque: BEGIN - END");}
		| sentencias
	/* Errores de BEGIN - END */
		| BEGIN sentencias END {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en bloque BEGIN - END");}
		| BEGIN sentencias '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END' en bloque BEGIN - END");}
		| sentencias END'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'BEGIN' en bloque BEGIN - END");}
	/* Errores de BEGIN - END */
	/* Errores de IF - ELSE */
		| IF condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
		| IF '(' condicion THEN bloque_para_funcion ELSE bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
		| IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion END_IF {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF - ELSE");}
		| IF '(' condicion ')' bloque_para_funcion ELSE bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
		| IF '(' condicion ')' THEN bloque_para_funcion ELSE bloque_para_funcion '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF - ELSE");}
		| IF '(' condicion ')' THEN bloque_para_funcion bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
	/* Errores de IF - ELSE */
	/* Errores de IF */
		| IF condicion ')' THEN bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
		| IF '(' condicion THEN bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
		| IF '(' condicion ')' THEN bloque_para_funcion END_IF {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia IF");}
		| IF '(' condicion ')' bloque_para_funcion END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
		| IF '(' condicion ')' THEN bloque_para_funcion '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF' en sentencia IF");}
	/* Errores de IF */
		;

sentencias : sentencias declaracion 
		| sentencias asignacion 
		| sentencias salida 		
		| sentencias llamado_funcion 
		| declaracion 
		| asignacion
		| salida
		| llamado_funcion
		;
		
declaracion_funcion : funcion 
		|sentencias funcion 
		;

funcion : tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a funci�n");}
		;

asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignaci�n");}
		| IDENTIFICADOR '=' expresion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignaci�n");}
		| IDENTIFICADOR  expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignaci�n");}
		;

declaracion : IDENTIFICADOR',' declaracion  {this.sintactico.showMessage("Declaracion de variable multiple");}
		| IDENTIFICADOR ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
		| IDENTIFICADOR declaracion  {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en asignaci�n m�ltiple");}
		| IDENTIFICADOR ':' tipo {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignaci�n");}
		| IDENTIFICADOR tipo'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en asignaci�n");}
		| IDENTIFICADOR ':' '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta tipo en asignaci�n");}
		;

rep_switch : CASE CONSTANTE ':' bloque_comun {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloque_comun  
		;

condicion : condicion operador expresion
		| expresion operador termino {this.sintactico.showMessage("Condici�n");}
		;

operador : '<' 
		| '>'
		| '<='
		| '>='
		| '<>'
		| '=='
		;

expresion : expresion '+' termino {this.sintactico.showMessage("Expresi�n");}
		| expresion '-' termino {this.sintactico.showMessage("Expresi�n");}
		| termino
		;

termino : termino '*' factor {this.sintactico.showMessage("T�rmino");}
		| termino '/' factor {this.sintactico.showMessage("T�rmino");}
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
	this.sintactico.showMessage(string);
	System.out.println(string);
}