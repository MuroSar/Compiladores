package compilador;

import compilador.Parser;
import complementos.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Tercetos.Terceto;
import compilador.Lexico;
import interfaz.Principal;

public class Sintactico {
	
	private Principal ppal;
	private Lexico lexico;
	private Parser parser;
	private GenCodigo generador; 
	private ArrayList<Terceto> tercetos;
	private ArrayList<String> errores;
	
	private boolean fnMOVE;
	private String IDSwitch;
	
	private  Map<String, String> funcionPos;
	private Stack<Terceto> pila;
	
	private String ambito;
	private Map<String, String> ambitos;
	private char maxAmbito;
	
	private static boolean marcaAntes;
	private static boolean marcaDesp;
	private String nombreMarca;
	
	public Sintactico(Principal principal, Lexico lexico, Parser parser, GenCodigo generador) {
		this.ppal = principal;
		this.lexico = lexico;
		this.parser = parser;
		this.generador = generador;  
		this.tercetos = new ArrayList<Terceto>();
		this.errores = new ArrayList<String>();
		
		this.fnMOVE = false;
		
		this.funcionPos = new HashMap<String, String>();
		this.pila = new Stack<Terceto>();
		
		this.ambito = "A";
		this.maxAmbito = this.ambito.charAt(0);
		this.ambitos = new HashMap<String, String>();
		this.ambitos.put("general", this.ambito);
		
		this.marcaAntes = false;
		this.marcaDesp = false;
		this.nombreMarca = "";
	}
	
	public void nuevo() {
		this.tercetos.clear();
		this.errores.clear();
		this.fnMOVE = false;
		this.funcionPos.clear();
		this.pila.clear();
		this.ambito = "A";
		this.maxAmbito = this.ambito.charAt(0);
		this.ambitos.clear();
		this.ambitos.put("general", this.ambito);
		this.marcaAntes = false;
		this.marcaDesp = false;
		this.nombreMarca = "";
	}
	
	public String getIDSwitch() {
		return this.IDSwitch;
	}
	
	public void setIDSwitch(String id) {
		this.IDSwitch = id;
	}
	
	public static boolean getMarcaAntes() {
		return marcaAntes;
	}
	
	public static void setMarcaAntes(boolean marca) {
		marcaAntes = marca;
	}
	
	public static boolean getMarcaDesp() {
		return marcaDesp;
	}
	
	public static void setMarcaDesp(boolean marca) {
		marcaDesp = marca;
	}
	
	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public void funcionPosPut(ParserVal nombreFunc, String label) {
		this.funcionPos.put(nombreFunc.sval, label);
	}
	
	public String funcionPosGet(String nombreFunc) {
		return this.funcionPos.get(nombreFunc);
	}
	
	public void setFnMOVE(boolean valor) {
		this.fnMOVE = valor;
	}
	
	public boolean getFnMOVE() {
		return this.fnMOVE;
	}
	
	public void pilaPush(Terceto pos) {
		this.pila.push(pos);
	}
	
	public Terceto pilaPop() {
		return this.pila.pop();
	}
	
	public boolean huboErrores()
	{
		return !this.errores.isEmpty();
	}
	
	public void addError(String error, ParserVal val)
	{
		Token t = this.lexico.getTokenFromTS(val.sval);
		
		if(error.equals("variable"))
		{
			this.errores.add("La variable " + t.getLexema() + " no fue declarada. Linea " + t.getLinea());	
		}
		else if(error.equals("funcion"))
		{
			this.errores.add("La funcion " + t.getLexema() + " no fue declarada. Linea " + t.getLinea());
		}
		else if(error.equals("tipos"))
		{
			this.errores.add("Error de tipos en la operacion de la linea " + t.getLinea());
		}
		else if(error.equals("variableDeclarada"))
		{
			this.errores.add("La variable " + t.getLexema() + " ya se encuentra declarada. Linea " + t.getLinea());
		}
		else if(error.equals("funcionDeclarada"))
		{
			this.errores.add("La funci\u00f3n " + t.getLexema() + " ya se encuentra declarada. Linea " + t.getLinea());
		}
		else if(error.equals("ambito"))
		{
			this.errores.add("Ambitos no compatibles. Linea " + t.getLinea());
		}
	}
	
	public String showErrores() {
		String errors = "";
		for (String error : this.errores)
		{
			errors = errors + error + "\n";
		}
		return errors;
	}

	public void addTerceto(Terceto t) {
		int pos = this.tercetos.size();
		this.tercetos.add(t);
	}
	
	public Terceto getTerceto(int key) {
		return this.tercetos.get(key);
	}
	
	public ArrayList<Terceto> getTercetos() {
		return new ArrayList<Terceto>(this.tercetos);
	}
	
	public String showTercetos() {
		String salida = "";
		for(Terceto t : tercetos) {
			salida = salida + t.getPos() + "--> " + t.toString() + "\n";
		}
		return salida;
	}
	
	public ArrayList<Terceto> getAllTercetos(){
		return this.tercetos;
	}
	
	public void actualizaVariables(ParserVal PVvariables, ParserVal tipo) {
		ArrayList<ParserVal> variables = new ArrayList<ParserVal>(((ArrayList<ParserVal>)PVvariables.obj));
		
		for(ParserVal var : variables) {
			if(!this.existeVariable(var))
			{
				
				Token aux = this.lexico.getTokenFromTS(var.sval);
				this.lexico.removeTokenFromTS(var.sval);
				
				String lexema = aux.getLexema();
				lexema = lexema + "@Variable";
				
				aux.setLexema(lexema);
				aux.setTipoDato(tipo.sval);		
				
				aux.setAmbito(this.ambito);
				
				this.lexico.putSimbolo(aux);	
			}
			else {
				this.addError("variableDeclarada", var);
			}
		}
	}
	
	public void actualizaFuncion(ParserVal nombre, ParserVal tipo) {
		
		if(!this.existeFuncion(nombre))
		{
			Token aux = this.lexico.getTokenFromTS(nombre.sval);
			this.lexico.removeTokenFromTS(nombre.sval);
			
			String lexema = aux.getLexema();
			lexema = lexema + "@Funcion";
			
			aux.setLexema(lexema);
			aux.setTipoDato(tipo.sval);		
			
			this.lexico.putSimbolo(aux);	
		}
		else {
			this.addError("funcionDeclarada", nombre);
		}
	}
	
	public boolean ambitoCorrecto(ParserVal op1, ParserVal op2) {
		String ambito1 = "";
		String ambito2 = "";
		
		if(op1.obj == null) {
			if(esVariable(op1)) {
				ambito1 = this.lexico.getTokenFromTS(op1.sval + "@Variable").getAmbito();
			}
			else {//si entra aca es porque es una constante.. por lo que no tiene ambito todavia
				Token aux = this.lexico.getTokenFromTS(op1.sval);
				this.lexico.removeTokenFromTS(op1.sval);
											
				aux.setAmbito(this.ambito);
				
				this.lexico.putSimbolo(aux);
				
				ambito1 = this.ambito;
			}
		}
		
		if(op2.obj == null) {
			if(esVariable(op2)) {
				ambito2 = this.lexico.getTokenFromTS(op2.sval + "@Variable").getAmbito();
			}
			else {//si entra aca es porque es una constante.. por lo que no tiene ambito todavia
				Token aux = this.lexico.getTokenFromTS(op2.sval);
				this.lexico.removeTokenFromTS(op2.sval);
											
				aux.setAmbito(this.ambito);
				
				this.lexico.putSimbolo(aux);
				
				ambito2 = this.ambito;
			}
		}
		
		if(this.fnMOVE == false) { //si no estoy en una funcion MOVE
			if((this.ambito.contains(ambito1) && this.ambito.contains(ambito2)) //este caso es por si tengo dos variables.. a+b 
					|| (op1.obj != null && this.ambito.contains(ambito2)) //etos dos casos, por si tengo un terceto y una variable..
					|| (this.ambito.contains(ambito1) && op2.obj != null)
					|| (op1.obj != null && op2.obj != null)) { //este caso es solo para las operaciones ejemplo.. 2*3 + 5*6 
				return true;
			}
		}
		else { //si estoy en una funcion MOVE
			if((this.ambito.equals(ambito1) && this.ambito.equals(ambito2))
					|| (op1.obj != null && this.ambito.equals(ambito2)) //etos dos casos, por si tengo un terceto y una variable..
					|| (this.ambito.equals(ambito1) && op2.obj != null)
					|| (op1.obj != null && op2.obj != null)) { //este caso es solo para las operaciones ejemplo.. 2*3 + 5*6 
				return true;
			}
		}
		
		
		return false;
	}
	
	public String mismoTipo(ParserVal op1, ParserVal op2) {
		String tipoDato1;
		String tipoDato2;
		if(op1.obj == null) {
			if(esVariable(op1)) {
				tipoDato1 = this.lexico.getTokenFromTS(op1.sval + "@Variable").getTipoDato();	
			}
			else {
				tipoDato1 = this.lexico.getTokenFromTS(op1.sval).getTipoDato();
			}	
		}
		else {
			tipoDato1 = ((Terceto)op1.obj).getTipoDato();
		}
		
		if(op2.obj == null) {
			if(esVariable(op2)) {
				tipoDato2 = this.lexico.getTokenFromTS(op2.sval + "@Variable").getTipoDato();
			}
			else {
				tipoDato2 = this.lexico.getTokenFromTS(op2.sval).getTipoDato();
			}
		}
		else {
			tipoDato2 = ((Terceto)op2.obj).getTipoDato();
		}
				
		
		if(tipoDato1.equals(tipoDato2)){
			return tipoDato1;
		}
		return null;
	}
	
	public static boolean esVariable(ParserVal val) {
		if(val.obj != null) {
			return false;
		}
		
		if(!Character.isLetter(val.sval.charAt(0))) {
			return false;
		}
			
		return true;
	}
	
	public void setTipoDatoTerceto(Terceto t, ParserVal val1, ParserVal val2) {
		t.setTipoDato(mismoTipo(val1, val2));
	}
	
	public boolean existeVariable(ParserVal variable)
	{
		if(esVariable(variable)) {
			if(this.lexico.estaDeclarada(variable.sval, "variable")) {
				Token t = this.lexico.getTokenFromTS(variable.sval + "@Variable");
				if(t.getAmbito().equals(this.ambito)) {
					return true;
				}
				else if(t.getAmbito().contains(this.ambito)) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean existeFuncion(ParserVal funcion)
	{
		return this.lexico.estaDeclarada(funcion.sval, "funcion");
	}
	
	public void showMessage(String mensaje)
	{
		this.ppal.mostrarMensaje(mensaje);
	}
	
	public void showError(String error)
	{
		this.errores.add(error);
		
		this.ppal.mostrarMensaje("----------------------------------------------------");
		this.ppal.mostrarMensaje(error);
		this.ppal.mostrarMensaje("----------------------------------------------------");
	}
	
	public  void aumentarAmbito(ParserVal nomFuncion) {
		if(this.ambitos.containsKey(nomFuncion.sval)) {
			this.ambito = this.ambitos.get(nomFuncion.sval);
		}
		else {
			this.maxAmbito = (char)(this.maxAmbito+1); 
			this.ambito = this.ambito + ":" + this.maxAmbito;
		}
		
	}
	
	public void decrementarAmbito() {
		this.ambito = this.ambito.substring(0, this.ambito.length()-2);
	}
	
	public Lexico getLexico() {
		return lexico;
	}
	
	public void start() {
		this.ppal.mostrarMensaje("------------------LISTADO DE TOKENS------------------");
		this.ppal.mostrarMensaje("");
		this.lexico.showAllTokens();
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("----------------------GRAMATICA----------------------");
		this.ppal.mostrarMensaje("");
		
		int result = parser.yyparse();
		switch (result) {
		case 0:
			this.ppal.mostrarMensaje("--------------------Gramatica: OK-------------------");
			break;
		default:
			this.ppal.mostrarMensaje("------------------Gramatica: ERROR-----------------");
		}
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("----------------------TERCETOS----------------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showTercetos());
		
		//this.ppal.mostrarMensaje(this.lexico.printTSimbolos());
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("-----------------------ERRORES----------------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showErrores());

		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("-----------------------CODIGO-----------------------");
		this.ppal.mostrarMensaje("");
		
		this.lexico.depurarTS();
		
		//this.ppal.mostrarMensaje(this.lexico.printTSimbolos());
		
		this.generador.setListaTercetos(this.getAllTercetos()); 
		this.generador.setSintactico(this);
		this.generador.generarCodigo();
		
		//this.ppal.mostrarMensaje(this.lexico.printTSimbolos());
	}
}
