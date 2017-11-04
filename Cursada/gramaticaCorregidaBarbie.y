%{
package compilador;

import Tercetos.Terceto;
import Tercetos.TercetoSuma;
import Tercetos.TercetoAsignacion;
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
		| BEGIN bloque_sentencias END'.' {this.sintactico.showMessage("Bloque BEGIN-END");}
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
		;
				
declaracion : lista_variables ':' tipo'.' {this.sintactico.showMessage("Declaraci\u00f3n de variable");}
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
		
asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignaci\u00f3n"); 
										 	$$ = new ParserVal(new TercetoAsignacion($1, $3, this.sintactico.getTercetos().size()));
											Terceto t =  new TercetoAsignacion($1, $3, this.sintactico.getTercetos().size());
											this.sintactico.addTerceto(t);}
		;
		
salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' {this.sintactico.showMessage("Llamado a funci\u00f3n");}
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

expresion : expresion '+' termino { $$ = new ParserVal(new TercetoSuma($1, $3, this.sintactico.getTercetos().size()));
									Terceto t =  new TercetoSuma($1, $3, this.sintactico.getTercetos().size());
									this.sintactico.addTerceto(t);}
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
	
	yylval = new ParserVal(token.getLexema());
	
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