%{
package compilador;

import java.util.ArrayList;
import Tercetos.Terceto;
import Tercetos.TercetoSuma;
import Tercetos.TercetoResta;
import Tercetos.TercetoMultiplicacion;
import Tercetos.TercetoDivision;
import Tercetos.TercetoComparador;
import Tercetos.TercetoAsignacion;
import Tercetos.TercetoBFalse;
import Tercetos.TercetoBIncondicional;
import Tercetos.TercetoFuncion;
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
		
sentencia_unica_control : sentencia_if
		| sentencia_switch
		;
		
sentencia_unica_ejecutable : asignacion
		| salida
		| llamado_funcion
		;		
		
declaracion_funcion : tipo FUNCTION IDENTIFICADOR bloque_funcion { this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
																   this.sintactico.actualizaFuncion($3, $1);}
		| tipo MOVE FUNCTION IDENTIFICADOR bloque_funcion {	this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
															this.sintactico.actualizaFuncion($4, $1);} 
		;
				
declaracion : lista_variables ':' tipo'.' { this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables($1, $3);}
		;
		
sentencia_if : IF '(' condicion ')' THEN { ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
										   this.sintactico.addTerceto(bFalse);
		               					   this.sintactico.pilaPush(bFalse);
		                            	  } cuerpo_if
		;
		
cuerpo_if :  bloque_control END_IF'.' { this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
			   						  }
		| bloque_control {	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
							this.sintactico.addTerceto(bIncondicional); 
							Terceto bFalse = this.sintactico.pilaPop();
							this.sintactico.pilaPush(bIncondicional);
							bFalse.setSegundo(this.sintactico.getTercetos().size()); //Set linea donde termina el THEN
						 }		
		ELSE bloque_control END_IF'.' { this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); //Set linea donde termina el IF
									  }
		;
				
sentencia_switch : SWITCH '(' IDENTIFICADOR ')' '{' rep_switch '}''.' {this.sintactico.showMessage("Sentencia: SWITCH");}
		;
		
rep_switch : CASE CONSTANTE ':' bloque_control {this.sintactico.showMessage("Sentencia: CASE");}
		| rep_switch CASE CONSTANTE ':' bloque_control  
		;
		
asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignaci\u00f3n");

											if(this.sintactico.existeVariable($1))
 											{
 												$$ = new ParserVal(new TercetoAsignacion($1, $3, this.sintactico.getTercetos().size()));
												Terceto t =  new TercetoAsignacion($1, $3, this.sintactico.getTercetos().size());
												this.sintactico.addTerceto(t);
 											}
 											else
 											{
 												this.sintactico.addError("variable", $1);
 											}}
		;
		
salida : OUT '(' CADENA ')''.' {this.sintactico.showMessage("Sentencia: OUT");}
		;

llamado_funcion : IDENTIFICADOR '('')''.' { this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion($1))
 											{
 												$$ = new ParserVal(new TercetoFuncion($1, this.sintactico.getTercetos().size()));
												Terceto t =  new TercetoFuncion($1, this.sintactico.getTercetos().size());
												this.sintactico.addTerceto(t);
 											}
 											else
 											{
 												this.sintactico.addError("funcion", $1);
 											}}
		;
		
lista_variables : IDENTIFICADOR { $$.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)($$.obj)).add($1);}
								  
		| lista_variables ',' IDENTIFICADOR { $$ = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)$1.obj).add($3);
                                              ((ArrayList<ParserVal>)$$.obj).addAll((ArrayList<ParserVal>)$1.obj);}
		;
		
condicion : condicion operador expresion
		| expresion operador expresion { this.sintactico.showMessage("Condici\u00f3n");
									   $$ = new ParserVal(new TercetoComparador($2, $1, $3, this.sintactico.getTercetos().size()));
									   Terceto t =  new TercetoComparador($2, $1, $3, this.sintactico.getTercetos().size());
									   this.sintactico.addTerceto(t);}
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
									
		| expresion '-' termino { $$ = new ParserVal(new TercetoResta($1, $3, this.sintactico.getTercetos().size()));
								  Terceto t =  new TercetoResta($1, $3, this.sintactico.getTercetos().size());
								  this.sintactico.addTerceto(t);}
		| termino
		;

termino : termino '*' factor { $$ = new ParserVal(new TercetoMultiplicacion($1, $3, this.sintactico.getTercetos().size()));
							   Terceto t =  new TercetoMultiplicacion($1, $3, this.sintactico.getTercetos().size());
							   this.sintactico.addTerceto(t);}
							   
		| termino '/' factor { $$ = new ParserVal(new TercetoDivision($1, $3, this.sintactico.getTercetos().size()));
							   Terceto t =  new TercetoDivision($1, $3, this.sintactico.getTercetos().size());
							   this.sintactico.addTerceto(t);}
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