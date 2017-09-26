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
	/* Errores de SWITCH */
		| SWITCH IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR '{' rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' '{' '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE' en sentencia SWITCH");}
	/* Errores de SWITCH */
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
	/* Errores de Funcion */
		| FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion '.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en declaracion de funcion");}
		| tipo FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'RETURN' en declaracion de funcion");}
		| tipo FUNCTION '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de funcion");}
		| tipo IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de funcion");}
	/* Errores de Funcion */
	/* Errores de Funcion */
		| MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion '.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloque_para_funcion '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'RETURN' en declaracion de funcion con MOVE");}
		| tipo MOVE FUNCTION '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de funcion con MOVE");}
		| tipo MOVE IDENTIFICADOR '{' bloque_para_funcion RETURN '(' expresion ')''.' '}' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de funcion con MOVE");}
	/* Errores de Funcion */
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a función");}
	/* Errores de Funcion */
		| '('')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en llamado a funcion");}
		| IDENTIFICADOR ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a funcion");}
		| IDENTIFICADOR '(''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a funcion");}
		| IDENTIFICADOR '('')' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en llamado a funcion");}
	/* Errores de Funcion */
		;

asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignación");}
	/* Errores de asignacion */	
		| IDENTIFICADOR '=' expresion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignación");}
		| IDENTIFICADOR  expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignación");}
		| '=' expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en asignación");}
		| IDENTIFICADOR '=' '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'EXPRESION' en asignación");}
	/* Errores de asignacion */
		;

declaracion : IDENTIFICADOR',' declaracion  {this.sintactico.showMessage("Declaracion de variable multiple");}
		| IDENTIFICADOR ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
	/* Errores de declaracion múltiple */
		| IDENTIFICADOR declaracion  {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion múltiple");}
	/* Errores de declaracion múltiple */
	/* Errores de declaracion */	
		| IDENTIFICADOR ':' tipo {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion");}
		| IDENTIFICADOR tipo'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en declaracion");}
		| IDENTIFICADOR ':' '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'TIPO' en declaracion");}
		| ':' tipo'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion");}
	/* Errores de declaracion */
		;

rep_switch : CASE CONSTANTE ':' bloque_comun {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloque_comun  
	/* Errores de CASE */
		| CONSTANTE ':' bloque_comun {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE' en sentencia CASE");}
		| CASE ':' bloque_comun {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE' en sentencia CASE");}
		| CASE CONSTANTE bloque_comun {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en sentencia CASE");}
	/* Errores de CASE */
		;

condicion : condicion operador expresion
		| expresion operador termino {this.sintactico.showMessage("Condición");}
	/* Errores de condicion */
		| expresion termino {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
		| operador termino {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
		| expresion operador {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
	/* Errores de condicion */
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
	/* Errores de expresion */
		| expresion termino {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
		| '+' termino {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
		| expresion '+' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
		| '-' termino {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'expresion'");}
		| expresion '-' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
	/* Errores de expresion */
		;

termino : termino '*' factor {this.sintactico.showMessage("Término");}
		| termino '/' factor {this.sintactico.showMessage("Término");}
		| factor
	/* Errores de termino */
		| termino factor {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
		| termino '*' factor {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
		| termino '*' factor {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'factor'");}
		| termino '/' factor {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'termino'");}
		| termino '/' factor {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'factor'");}
	/* Errores de termino */
		;

factor : IDENTIFICADOR 
		| CONSTANTE
		;

salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
	/* Errores de OUT */
		| OUT '(' CADENA ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'OUT' en sentencia OUT");}
		| OUT CADENA ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia OUT");}
		| OUT '(' CADENA '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia OUT");}
		| OUT '(' ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CADENA' en sentencia OUT");}
		| OUT '(' CADENA ')' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en sentencia OUT");}
	/* Errores de OUT */
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