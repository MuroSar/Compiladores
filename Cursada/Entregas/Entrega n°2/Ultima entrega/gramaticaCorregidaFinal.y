%{
package compilador;

import java.util.ArrayList;
import java.util.Stack;

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
import Tercetos.TercetoOut;
import Tercetos.TercetoRet;
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
		
bloque_funcion : '{' bloque_sentencias_funcion RETURN '(' expresion ')''.''}' {	Terceto ret = new TercetoRet("RET", $5, null, this.sintactico.getTercetos().size(), this.funcionActual.pop());
												   								if(this.sintactico.getMarcaAntes()){
																					ret.setMarcaAntes(true);
																					this.sintactico.setMarcaAntes(false);
																				}
												   								this.sintactico.addTerceto(ret);
												   								this.sintactico.addTercetoFuncion(ret);
																			   }

bloque_sentencias_funcion : sentencias
		| declaracion
		| bloque_sentencias_funcion sentencias
		| bloque_sentencias_funcion declaracion
		;

bloque_control : sentencias
		| BEGIN bloque_sentencias END'.' {this.sintactico.showMessage("Bloque BEGIN-END");}
	/* ERRORES */
		| BEGIN bloque_sentencias END error {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
	/* ERRORES */
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
		;		

declaracion_funcion : encabezado_funcion bloque_funcion {this.sintactico.decrementarAmbito();
														 this.sintactico.setFnMOVE(false);}
	/* ERRORES */
		| encabezado_funcion bloque_funcion '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Sobra '.'");}
	/* ERRORES */														 
		;
		
encabezado_funcion : tipo FUNCTION IDENTIFICADOR { this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n");
												   
												   this.sintactico.setMarcaAntes(true);
												   this.sintactico.addNombreMarca($3.sval);
												   this.sintactico.funcionPosPut($3, $3.sval);												   
												   
												   this.sintactico.actualizaFuncion($3, $1);
												   this.sintactico.aumentarAmbito($3);
												   this.funcionActual.push($3.sval);}
												   
		| tipo MOVE FUNCTION IDENTIFICADOR { this.sintactico.showMessage("Declaraci\u00f3n de Funci\u00f3n con MOVE");
											 
											 this.sintactico.setMarcaAntes(true);
											 this.sintactico.addNombreMarca($4.sval);
											 this.sintactico.funcionPosPut($4, $4.sval);
											 
											 this.sintactico.actualizaFuncion($4, $1);
											 this.sintactico.aumentarAmbito($4);
											 this.sintactico.setFnMOVE(true);
											 this.funcionActual.push($4.sval);} 
		;
				
declaracion : lista_variables ':' tipo'.' { this.sintactico.showMessage("Declaraci\u00f3n de variable");
											this.sintactico.actualizaVariables($1, $3);}
		;
		
sentencia_if : IF '(' condicion ')' THEN { ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size()-1)));
									  	   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size());
										   this.sintactico.addTerceto(bFalse);
										   if(!this.sintactico.getAmbito().equals("A")){
										   	   this.sintactico.addTercetoFuncion(bFalse);
										   }
		               					   this.sintactico.pilaPush(bFalse);
		                            	  } cuerpo_if {this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
		                            	  
	/* ERRORES */
		| error '(' condicion ')' THEN cuerpo_if {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'IF'");}
		| IF error condicion ')' THEN cuerpo_if {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
		| IF '(' condicion error THEN cuerpo_if {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
		| IF '(' condicion ')'error cuerpo_if {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'THEN'");}
	/* ERRORES */                          	  
		;
		
cuerpo_if :  bloque_control END_IF'.' { this.sintactico.showMessage("Sentencia: IF");
			   							Terceto bFalse = this.sintactico.pilaPop();
										bFalse.setSegundo(this.sintactico.getTercetos().size());
			   						  }
		| bloque_control {	Terceto bIncondicional = new TercetoBIncondicional(this.sintactico.getTercetos().size());
							this.sintactico.addTerceto(bIncondicional);
							if(!this.sintactico.getAmbito().equals("A")){
						   	   this.sintactico.addTercetoFuncion(bIncondicional);
						    } 
							Terceto bFalse = this.sintactico.pilaPop();
							this.sintactico.pilaPush(bIncondicional);
							bFalse.setSegundo(this.sintactico.getTercetos().size()); //Set linea donde termina el THEN
							this.sintactico.setMarcaAntes(true);
						 }		
		ELSE bloque_control END_IF'.' { this.sintactico.showMessage("Sentencia: IF - ELSE");
										Terceto bInconditional = this.sintactico.pilaPop();
		                               	bInconditional.setPrimero(this.sintactico.getTercetos().size()); //Set linea donde termina el IF
									  }
	/* ERRORES */      
		| bloque_control error bloque_control END_IF'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'ELSE'");}
		| bloque_control error '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
		| bloque_control END_IF error {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");}
		//| bloque_control ELSE bloque_control error '.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'END_IF'");}
	/* ERRORES */
		;
				
sentencia_switch : SWITCH '(' IDENTIFICADOR ')' { if(this.sintactico.existeVariable($3))
 												  {
													  this.sintactico.showMessage("Sentencia: SWITCH");
													  ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
										  	  	 	  Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1);
													  this.tercetoAux = bFalse;
								               		  this.sintactico.pilaPush(bFalse);
								               		  this.sintactico.setIDSwitch($3.sval);
								               	  }
								               	  else
								               	  {
								               	  	  this.sintactico.addError("variable", $3);
								               	  }
							               		} cuerpo_switch {this.sintactico.getTerceto(this.sintactico.getTercetos().size()-1).setMarcaDesp(true);}
	/* ERRORES */   
		| error '(' IDENTIFICADOR ')' cuerpo_switch {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'SWITCH'");}
		| SWITCH error IDENTIFICADOR ')' cuerpo_switch {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '('");}
		| SWITCH '(' error ')' cuerpo_switch {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'variable'");}
		| SWITCH '(' IDENTIFICADOR error cuerpo_switch {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')'");}
	/* ERRORES */   							          
		;

cuerpo_switch : '{' rep_switch '}''.' 
	/* ERRORES */
		| error rep_switch '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '{'");}
		| '{' error '}''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
		| '{' rep_switch error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '}'");}
		| '{' rep_switch '}'error {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.'");} 
	/* ERRORES */
		;
		
rep_switch : CASE CONSTANTE { if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), $2) != null) {
								  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), $2, this.sintactico.getTercetos().size());
								  this.sintactico.addTerceto(comp);
								  if(!this.sintactico.getAmbito().equals("A")){
							   	  	  this.sintactico.addTercetoFuncion(comp);
							      } 
								  this.sintactico.addTerceto(tercetoAux);
							  }
							  else
							  {
								  this.sintactico.addError("tipos", $2);
							  }
							}
						  ':' bloque_control { ParserVal aux = new ParserVal((String.valueOf(this.sintactico.getTercetos().size())));
									  		   Terceto bFalse = new TercetoBFalse(aux, this.sintactico.getTercetos().size()+1);
											   this.tercetoAux = bFalse;
											   Terceto bFalse2 = this.sintactico.pilaPop();
											   this.sintactico.pilaPush(bFalse);
											   bFalse2.setSegundo(this.sintactico.getTercetos().size()); //Set linea donde termina el THEN
										   	 }
		| rep_switch CASE CONSTANTE { if(this.sintactico.mismoTipo(new ParserVal(this.sintactico.getIDSwitch()), $3) != null) {
										  Terceto comp = new TercetoComparador( new ParserVal("=="), new ParserVal(this.sintactico.getIDSwitch()), $3, this.sintactico.getTercetos().size());
										  this.sintactico.addTerceto(comp);
										  if(!this.sintactico.getAmbito().equals("A")){
									   	  	  this.sintactico.addTercetoFuncion(comp);
									      } 
										  this.sintactico.addTerceto(tercetoAux);
										  if(!this.sintactico.getAmbito().equals("A")){
									   	  	  this.sintactico.addTercetoFuncion(tercetoAux);
									      } 
									  }
							  		  else
							  		  {
									  	  this.sintactico.addError("tipos", $3);
								  	  }
									}
									':' bloque_control  { this.sintactico.showMessage("Sentencia: CASE");
														  Terceto bFalse3 = this.sintactico.pilaPop();
														  this.sintactico.pilaPush(tercetoAux);
														  bFalse3.setSegundo(this.sintactico.getTercetos().size()); //Set linea donde termina el THEN
											  		    }
	/* ERRORES */
		| error CONSTANTE ':' bloque_control {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CASE'");}
		| CASE error ':' bloque_control {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'CONSTANTE'");}
		| CASE CONSTANTE error bloque_control {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ':'");}
	/* ERRORES */
		;
		
asignacion : IDENTIFICADOR '=' expresion'.' {this.sintactico.showMessage("Asignaci\u00f3n");

											if(this.sintactico.existeVariable($1))
 											{
 												if(this.sintactico.existeVariable($3))
 												{
		 											if(this.sintactico.ambitoCorrecto($1, $3)) {
		 												if(this.sintactico.mismoTipo($1, $3) != null) {
		 													Terceto t =  new TercetoAsignacion($1, $3, this.sintactico.getTercetos().size());
		 													if(this.sintactico.getMarcaAntes()){
		 														t.setMarcaAntes(true);
		 														this.sintactico.setMarcaAntes(false);
		 													}
		 													$$ = new ParserVal(t);
															this.sintactico.addTerceto(t);
															if(!this.sintactico.getAmbito().equals("A")){
													   	  	    this.sintactico.addTercetoFuncion(t);
													        }
														}
														else
														{
															this.sintactico.addError("tipos", $1);
														}
													}
													else {
														this.sintactico.addError("ambito", $3);
													}
												}
												else
 												{
 												this.sintactico.addError("variable", $3);
 												}
 											}
 											else
 											{
 												this.sintactico.addError("variable", $1);
 											}}
 	/* ERRORES */
		| IDENTIFICADOR error expresion'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador'");}
		| IDENTIFICADOR '=' expresion error {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en asignacion");}
	/* ERRORES */
		;
		
salida : OUT '(' CADENA ')''.' { this.sintactico.showMessage("Sentencia: OUT");
							   	 Terceto t =  new TercetoOut($3, this.sintactico.getTercetos().size());
							   	 if(this.sintactico.getMarcaAntes()){
								 	 t.setMarcaAntes(true);
								 	 this.sintactico.setMarcaAntes(false);
								 }
							   	 $$ = new ParserVal(t);
								 this.sintactico.addTerceto(t);
								 if(!this.sintactico.getAmbito().equals("A")){
						   	  	     this.sintactico.addTercetoFuncion(t);
						         }
							   }
	/* ERRORES */ 
		| OUT error CADENA ')''.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '(' en salida");}
		| OUT '(' CADENA error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en salida");}
		| OUT '(' CADENA ')' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en salida");}
	/* ERRORES */ 
		;
		
lista_variables : IDENTIFICADOR { $$.obj = new ArrayList<ParserVal>(); 
								  ((ArrayList<ParserVal>)($$.obj)).add($1);}
								  
		| lista_variables ',' IDENTIFICADOR { $$ = new ParserVal(new ArrayList<ParserVal>()); 
											  ((ArrayList<ParserVal>)$1.obj).add($3);
                                              ((ArrayList<ParserVal>)$$.obj).addAll((ArrayList<ParserVal>)$1.obj);}
	/* ERRORES */
		| lista_variables IDENTIFICADOR {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ',' en declaracion de variables");}
	/* ERRORES */  
		;
		
condicion : condicion operador expresion
		| expresion operador expresion {this.sintactico.showMessage("Condici\u00f3n");
										if(this.sintactico.existeVariable($1)){
									     	if(this.sintactico.existeVariable($3)){
									     		if(this.sintactico.ambitoCorrecto($1, $3)) {
										     		if(this.sintactico.mismoTipo($1, $3) != null) {
														Terceto t =  new TercetoComparador($2, $1, $3, this.sintactico.getTercetos().size());
														if(this.sintactico.getMarcaAntes()){
															t.setMarcaAntes(true);
															this.sintactico.setMarcaAntes(false);
														}
														$$ = new ParserVal(t);
														this.sintactico.addTerceto(t);
														if(!this.sintactico.getAmbito().equals("A")){
												   	  	    this.sintactico.addTercetoFuncion(t);
												        }
													}
													else {
														this.sintactico.addError("tipos", $1);
													}
												}
												else {
													this.sintactico.addError("ambito", $3);
												}
												
											}
											else {
												this.sintactico.addError("variable", $3);
											}
										}
										else {
											this.sintactico.addError("variable", $1);
										}}
	/* ERRORES */
		| expresion expresion {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta 'operador' en condicion");}
	/* ERRORES */
		;

operador : '<' 
		| '>'
		| '<='
		| '>='
		| '<>'
		| '=='
		;

expresion : expresion '+' termino { if(this.sintactico.existeVariable($1)){
										if(this.sintactico.existeVariable($3)){
											if(this.sintactico.ambitoCorrecto($1, $3)) {	
		 										if(this.sintactico.mismoTipo($1, $3) != null) {
													Terceto t =  new TercetoSuma($1, $3, this.sintactico.getTercetos().size());
													this.sintactico.setTipoDatoTerceto(t, $1, $3);
													if(this.sintactico.getMarcaAntes()){
														t.setMarcaAntes(true);
														this.sintactico.setMarcaAntes(false);
													}
													$$ = new ParserVal(t);
													this.sintactico.addTerceto(t);
													if(!this.sintactico.getAmbito().equals("A")){
											   	  	    this.sintactico.addTercetoFuncion(t);
											        }
												}
												else {
													this.sintactico.addError("tipos", $1);
												}
											}
											else {
												this.sintactico.addError("ambito", $3);
											}
										}
										else {
											this.sintactico.addError("variable", $3);
										}
									}
									else {
										this.sintactico.addError("variable", $1);
									}}
									
		| expresion '-' termino { 	if(this.sintactico.existeVariable($1)){
										if(this.sintactico.existeVariable($3)){
		 									if(this.sintactico.ambitoCorrecto($1, $3)) {
		 										if(this.sintactico.mismoTipo($1, $3) != null) {
													Terceto t =  new TercetoResta($1, $3, this.sintactico.getTercetos().size());
													this.sintactico.setTipoDatoTerceto(t, $1, $3);
													if(this.sintactico.getMarcaAntes()){
														t.setMarcaAntes(true);
														this.sintactico.setMarcaAntes(false);
													}
													$$ = new ParserVal(t);
													this.sintactico.addTerceto(t);
													if(!this.sintactico.getAmbito().equals("A")){
											   	  	    this.sintactico.addTercetoFuncion(t);
											        }
												}
												else {
													this.sintactico.addError("tipos", $1);
												}
											}
											else {
												this.sintactico.addError("ambito", $3);
											}
										}
										else {
											this.sintactico.addError("variable", $3);
										}
									}
									else {
										this.sintactico.addError("variable", $1);
									}}
		| termino
		;

termino : termino '*' factor { 	if(this.sintactico.existeVariable($1)){
									if(this.sintactico.existeVariable($3)){
										if(this.sintactico.ambitoCorrecto($1, $3)) {
											if(this.sintactico.mismoTipo($1, $3) != null) {
												Terceto t =  new TercetoMultiplicacion($1, $3, this.sintactico.getTercetos().size());
												this.sintactico.setTipoDatoTerceto(t, $1, $3);
												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
												$$ = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
											}
											else {
												this.sintactico.addError("tipos", $1);
											}
										}
										else {
											this.sintactico.addError("ambito", $3);
										}
									}
									else {
										this.sintactico.addError("variable", $3);
									}
								}
								else {
									this.sintactico.addError("variable", $1);
								}}
							   
		| termino '/' factor { if(this.sintactico.existeVariable($1)){
									if(this.sintactico.existeVariable($3)){
										if(this.sintactico.ambitoCorrecto($1, $3)) {
		 									if(this.sintactico.mismoTipo($1, $3) != null) {
												Terceto t =  new TercetoDivision($1, $3, this.sintactico.getTercetos().size());
												this.sintactico.setTipoDatoTerceto(t, $1, $3);
												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
												$$ = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
											}
											else {
												this.sintactico.addError("tipos", $1);
											}
										}
										else {
											this.sintactico.addError("ambito", $3);
										}
									}
									else {
										this.sintactico.addError("variable", $3);
									}
								}
								else {
									this.sintactico.addError("variable", $1);
								}}
		| factor
		;

llamado_funcion : IDENTIFICADOR '('')' { this.sintactico.showMessage("Llamado a funci\u00f3n");
											if(this.sintactico.existeFuncion($1))
 											{
 												Terceto t =  new TercetoFuncion($1, this.sintactico.getTercetos().size());
 												t.setSegundo("[" + Integer.valueOf(this.sintactico.getTercetos().size()+1) + "]");
 												if(this.sintactico.getMarcaAntes()){
													t.setMarcaAntes(true);
													this.sintactico.setMarcaAntes(false);
												}
 												$$ = new ParserVal(t);
												this.sintactico.addTerceto(t);
												if(!this.sintactico.getAmbito().equals("A")){
										   	  	    this.sintactico.addTercetoFuncion(t);
										        }
 											}
 											else
 											{
 												this.sintactico.addError("funcion", $1);
 											}}
 	/* ERRORES */ 
		| IDENTIFICADOR '('error'.' {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta ')' en llamado a Funcion");}
		//| IDENTIFICADOR '('')'error {this.sintactico.showError("ERROR Linea "+ token.getLinea() +": Falta '.' en llamado a Funcion");}
	/* ERRORES */
		;
	
factor : IDENTIFICADOR 
		| CONSTANTE 
		| llamado_funcion
		;


tipo : LONG
		| DOUBLE
		;
		
%%

private Lexico lexico;
private Sintactico sintactico;
private Token token;
private Stack<String> funcionActual = new Stack<String>();
private Terceto tercetoAux;

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