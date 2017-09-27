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

bloque_comun : bloques
		| declaracion_funcion
		| bloque_comun declaracion_funcion
		;
		
bloques : bloque_para_funcion
		| bloques bloque_para_funcion
		| BEGIN bloques END'.' {this.sintactico.showMessage("Bloque: BEGIN - END");}
		;
		
bloque_para_funcion :  IF '(' condicion ')' THEN bloques ELSE bloques END_IF'.' {this.sintactico.showMessage("Sentencia: IF - ELSE");}
		| IF '(' condicion ')' THEN bloques END_IF'.' {this.sintactico.showMessage("Sentencia: IF");}
		| SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
		| sentencias
	/* ERRORES IF-ELSE */
		| IF error END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en sentencia IF - ELSE");}		
	/* FIN IF-ELSE */
		;

sentencias : sentencias sentencia_unica
		| sentencia_unica 
		;
		
sentencia_unica : declaracion 
		| asignacion
		| salida
		| llamado_funcion
		;

declaracion_funcion : funcion 
		;

funcion : tipo FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion");}
		| tipo MOVE FUNCTION IDENTIFICADOR '{' bloques RETURN '(' expresion ')''.' '}' {this.sintactico.showMessage("Declaracion de Funcion con MOVE");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a función");}
		;

asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignación");}
		;

declaracion : IDENTIFICADOR',' declaracion  {this.sintactico.showMessage("Declaracion de variable multiple");}
		| IDENTIFICADOR ':' tipo'.' {this.sintactico.showMessage("Declaracion de variable");}
		| IDENTIFICADOR ':' tipo {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en declaracion");}
		;

rep_switch : CASE CONSTANTE ':' bloques {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloques  
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
	this.sintactico.showMessage(string);
	System.out.println(string);
}