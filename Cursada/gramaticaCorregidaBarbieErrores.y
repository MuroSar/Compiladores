%{
package compilador;

import compilador.Lexico;
import compilador.Sintactico;
import complementos.Token;
%}

%token IDENTIFICADOR CONSTANTE IF THEN ELSE END_IF BEGIN END OUT LONG DOUBLE SWITCH CASE FUNCTION RETURN MOVE CADENA
%start programa

%%

programa : bloque
		;
		
bloque : bloque_comun
		| declaracion_funcion
		| bloque bloque_comun
		|bloque declaracion_funcion
		;
		
bloque_comun : bloque_control
		| declaracion
		;
		
bloque_funcion : '{' bloque_sentencias_funcion RETURN '(' expresion ')''.''}'

bloque_sentencias_funcion : sentencias
		| declaracion
		| bloque_sentencias_funcion sentencias
		| bloque_sentencias_funcion declaracion
		;

bloque_control : sentencias
		| BEGIN bloque_sentencias END'.'
		;

bloque_sentencias : sentencias
		| bloque_sentencias sentencias
		;
		
sentencias : sentencia_unica_control
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
		
declaracion_funcion : tipo FUNCTION IDENTIFICADOR bloque_funcion {this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");}
		| tipo MOVE FUNCTION IDENTIFICADOR bloque_funcion {this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");} 
	/* ERRORES */ 
		| error FUNCTION IDENTIFICADOR bloque_funcion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'tipo' en declaracion de Funcion");}
		| tipo error IDENTIFICADOR bloque_funcion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'FUNCTION' en declaracion de Funcion");}
		| tipo FUNCTION error bloque_funcion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en declaracion de Funcion");}
	/* ERRORES */
		;
				
declaracion : lista_variables ':' tipo'.' {this.sintactico.showMessage("Declaraci\u00f3n de variable");}
		;
		
sentencia_if_else : IF '(' condicion ')' THEN bloque_control ELSE bloque_control END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}	
	/* ERRORES */
		| IF error ELSE bloque_control END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}
		| IF '(' error ELSE bloque_control  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF - ELSE");}
		| IF '(' condicion error ELSE bloque_control  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF - ELSE");}
		| IF '(' condicion ')' error ELSE bloque_control  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF - ELSE");}
		| IF '(' condicion ')' THEN error bloque_control  END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE' en sentencia IF - ELSE");}
	/* ERRORES */
		;
		
sentencia_if : IF '(' condicion ')' THEN bloque_control END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
	/* ERRORES */
		| IF error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF");}
		| IF '(' error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'condicion' en sentencia IF");}
		| IF '(' condicion error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia IF");}
		| IF '(' condicion ')' error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN' en sentencia IF");}
	/* ERRORES */
		;
		
sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
	/* ERRORES */
		| SWITCH error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia SWITCH");}
		| SWITCH '(' error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IDENTIFICADOR' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en sentencia SWITCH");}
		| SWITCH '(' IDENTIFICADOR ')' error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{' en sentencia SWITCH");}
	/* ERRORES */
		;
		
rep_switch : CASE CONSTANTE ':' bloque_control {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloque_control  
	/* ERRORES */
		| CASE CONSTANTE error bloque_control {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':' en sentencia CASE");}
	/* ERRORES */
		;
		
asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignaci\u00f3n"); if(!estaDeclarada((String)$$.obj)){System.out.println("la variable "+ (String)$$.obj + " no esta declarada..");}} 
	/* ERRORES */
		| IDENTIFICADOR error expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '=' en asignacion");}
	/* ERRORES */
		;
		
salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
	/* ERRORES */ 
		error '(' CADENA ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'OUT' en salida");}
		OUT error CADENA ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
		OUT '(' CADENA error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
	/* ERRORES */ 
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a funci\u00f3n");}
	/* ERRORES */ 
		| IDENTIFICADOR error ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en llamado a Funcion");}
		| IDENTIFICADOR '('error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
	/* ERRORES */
		;
		
lista_variables : IDENTIFICADOR
		| lista_variables ',' IDENTIFICADOR
		;
		
condicion : condicion operador expresion
		| expresion operador termino {this.sintactico.showMessage("Condici\u00f3n");}
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

public boolean estaDeclarada(String lexema) {
	return lexico.estaDeclarada(lexema);
}

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