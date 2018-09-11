package compilador;

import compilador.Parser;
import complementos.Token;

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
	
	private Map<String, String> funcionPos;
	private Stack<Terceto> pila;
	
	private String ambito;
	private Map<String, String> ambitos;
	private char maxAmbito;
	
	private static boolean marcaAntes;
	private static boolean marcaDesp;
	private ArrayList<String> nombreMarca;
	
	private ArrayList<Terceto> tercetosFuncion;
	
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
		this.ambitos.put("main", this.ambito);
		
		this.marcaAntes = false;
		this.marcaDesp = false;
		this.nombreMarca = new ArrayList<String>();
		
		this.tercetosFuncion = new ArrayList<Terceto>();
		this.generador.setSintactico(this);
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
		this.ambitos.put("main", this.ambito);
		this.marcaAntes = false;
		this.marcaDesp = false;
		this.nombreMarca = new ArrayList<String>();
		this.tercetosFuncion.clear();
		this.generador.setSintactico(this);
	}
	
	public String getIDSwitch() {
		return this.IDSwitch;
	}
	
	public void setIDSwitch(String id) {
		this.IDSwitch = id;
	}
	
	public GenCodigo getGenerador() {
		return generador;
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
	
	public boolean existeNombreMarca() {
		if(this.nombreMarca.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public String getNombreMarca() {
		return this.nombreMarca.get(0);
	}

	public void addNombreMarca(String nombreMarca) {
		this.nombreMarca.add(nombreMarca);
	}

	public String removeNombreMarca() {
		return this.nombreMarca.remove(0);
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
	
	public boolean pilaVacia() {
		return this.pila.empty();
	}
	
	public boolean huboErrores()
	{
		return !this.errores.isEmpty();
	}
	
	public String getFirstElement(Terceto terceto) {
		if (terceto.getPrimero() == null) {
			return getFirstElement(((Terceto)terceto.getPrimeroParserVal().obj));
		} else {
			return terceto.getPrimero();
		}
	}
	
	public void addError(String error, ParserVal val)
	{
		Token t;
		if (val.obj == null) {
			t = Lexico.getTokenFromTS(val.sval);	
		} else {
			t = Lexico.getTokenFromTS(getFirstElement((Terceto)val.obj));
		}
		
		if(error.equals("variable"))
		{
			if (!this.errores.contains("La funci\u00f3n " + t.getLexema() + " no fue declarada. Linea " + t.getLinea())) {
				String nombreCompletoFuncion = val.sval + "@Funcion@main";
				if (Lexico.getTokenFromTS(nombreCompletoFuncion) == null) {
					this.errores.add("La variable " + t.getLexema() + " no fue declarada. Linea " + t.getLinea());	
				}
				else {
					this.errores.add("Faltan los parentesis en la funci\u00f3n " + t.getLexema() + " Linea " + t.getLinea());
				}	
			}	
		}
		else if(error.equals("funcion"))
		{
			this.errores.add("La funci\u00f3n " + t.getLexema() + " no fue declarada. Linea " + t.getLinea());
		}
		else if(error.equals("tipos"))
		{
			this.errores.add("Error de tipos en la operacion de la linea " + t.getLinea());
		}
		else if(error.equals("variableDeclarada"))
		{
			boolean mostrar = true;
			String nombreCompletoVariable = val.sval + "@Variable"+ this.getNameManglingForAmbito(this.ambito);
			for(String err : this.errores) {
				if (err.contains("La funci\u00f3n " + nombreCompletoVariable.split("@")[3] + " ya se encuentra declarada. Linea ")) {
					mostrar = false;	
					break;
				}	
			}
			if(mostrar) {
				this.errores.add("La variable " + t.getLexema() + " ya se encuentra declarada. Linea " + t.getLinea());
			}
		}
		else if(error.equals("funcionDeclarada"))
		{
			this.errores.add("La funci\u00f3n " + t.getLexema() + " ya se encuentra declarada. Linea " + t.getLinea());
		}
		else if(error.equals("ambito"))
		{
			if (fnMOVE) {
				this.errores.add("Variable no declarada en la funci\u00f3n MOVE. Linea " + t.getLinea());	
			} else {
				this.errores.add("Ambitos no compatibles. Linea " + t.getLinea());	
			}
		}
	}
	
	public String showErrores() {
		this.depurarErrores();
		
		String errors = "";
		
		for (String error : this.errores)
		{
			errors = errors + error + "\n";
		}
		return errors;
	}
	
	private void depurarErrores() {
		ArrayList<String> auxErrors = new ArrayList<>(this.errores);
		this.errores = new ArrayList<>();
		
		for (String error : auxErrors) {
			if (!this.errores.contains(error)) {
				this.errores.add(error);
			}
		}
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
	
	public void addTercetoFuncion(Terceto t) {
		this.tercetosFuncion.add(t);
	}
	
	public ArrayList<Terceto> getTercetosFuncion() {
		return new ArrayList<Terceto>(this.tercetosFuncion);
	}
	
	public String showTercetos() {
		String salida = "";
		for(Terceto t : tercetos) {
			salida = salida + t.getPos() + "--> " + t.toString();
			if(t.isDeleted()) {
				salida += " ---> Borrado logico\n";
			}
			else {
				salida += "\n";
			}
		}
		return salida;
	}
	
	public ArrayList<Terceto> getAllTercetos(){
		return this.tercetos;
	}
	
	public void actualizaVariables(ParserVal PVvariables, ParserVal tipo, boolean esDeclaracion) {
		ArrayList<ParserVal> variables = new ArrayList<ParserVal>(((ArrayList<ParserVal>)PVvariables.obj));
		
		for(ParserVal var : variables) {
			if(!this.existeVariable(var, esDeclaracion))
			{
				
				String ambitoReal = this.getNameManglingForAmbito(this.ambito);
				
				Token aux = Lexico.getTokenFromTS(var.sval);
				Lexico.removeTokenFromTS(var.sval);
				
				String lexema = aux.getLexema();
				lexema = lexema + "@Variable" + ambitoReal;
				
				aux.setLexema(lexema);
				aux.setTipoDato(tipo.sval);		
				
				aux.setAmbito(ambitoReal);
				
				Lexico.putSimbolo(aux);	
			}
			else {
				this.addError("variableDeclarada", var);
			}
		}
	}
	
	public void actualizaFuncion(ParserVal nombre, ParserVal tipo, boolean esDeclaracion) {
		
		if(!this.existeFuncion(nombre, esDeclaracion))
		{
			String ambitoReal = this.getNameManglingForAmbito(this.ambito);
			
			Token aux = Lexico.getTokenFromTS(nombre.sval);
			Lexico.removeTokenFromTS(nombre.sval);
			
			String lexema = aux.getLexema();
			lexema = lexema + "@Funcion" + ambitoReal;
			
			aux.setLexema(lexema);
			aux.setTipoDato(tipo.sval);
			aux.setAmbito(ambitoReal);
			
			Lexico.putSimbolo(aux);	
		}
		else {
			this.addError("funcionDeclarada", nombre);
		}
	}
	
	public String getNameManglingForAmbito(String ambitoValue) {
		String result = "";
		int i = 0;
		String[] individuales = new String[2];
		individuales[0] = ambitoValue;
		if(ambitoValue.length() > 1) {
			individuales[1] = ambitoValue.substring(0, ambitoValue.length()-2);
			i++;
		}
		
		
		ArrayList<String> keys = new ArrayList<String>(this.ambitos.keySet());
		while(i >= 0) {
			for(String key : keys) {
				if(this.ambitos.get(key).equals(individuales[i])) {
					result += "@" + key;
				}
			}
			i--;
		}
		return result;
	}
	
	public boolean ambitoCorrecto(ParserVal op1, ParserVal op2) {
		String ambito1 = "";
		String ambito2 = "";
		String ambitoReal = this.getNameManglingForAmbito(this.ambito);
		
		if(op1.obj == null) {
			if(esVariable(op1)) {
				ambito1 = Lexico.getTokenFromTS(op1.sval + "@Variable" + ambitoReal).getAmbito();
			}
			else {//si entra aca es porque es una constante.. por lo que no tiene ambito todavia
				Token aux = Lexico.getTokenFromTS(op1.sval);
				Lexico.removeTokenFromTS(op1.sval);
											
				aux.setAmbito(ambitoReal);
				
				Lexico.putSimbolo(aux);
				
				ambito1 = ambitoReal;
			}
		}
		
		if(op2.obj == null) {
			if(esVariable(op2)) {
				ambito2 = Lexico.getTokenFromTS(op2.sval + "@Variable" + ambitoReal).getAmbito();
			}
			else {//si entra aca es porque es una constante.. por lo que no tiene ambito todavia
				Token aux = Lexico.getTokenFromTS(op2.sval);
				Lexico.removeTokenFromTS(op2.sval);
											
				aux.setAmbito(ambitoReal);
				
				Lexico.putSimbolo(aux);
				
				ambito2 = ambitoReal;
			}
		}
		
		if(this.fnMOVE == false) { //si no estoy en una funcion MOVE
			if((ambitoReal.contains(ambito1) && ambitoReal.contains(ambito2)) //este caso es por si tengo dos variables.. a+b 
					|| (op1.obj != null && ambitoReal.contains(ambito2)) //etos dos casos, por si tengo un terceto y una variable..
					|| (ambitoReal.contains(ambito1) && op2.obj != null)
					|| (op1.obj != null && op2.obj != null)) { //este caso es solo para las operaciones ejemplo.. 2*3 + 5*6 
				return true;
			}
		}
		else { //si estoy en una funcion MOVE
			if((ambitoReal.equals(ambito1) && ambitoReal.equals(ambito2))
					|| (op1.obj != null && ambitoReal.equals(ambito2)) //etos dos casos, por si tengo un terceto y una variable..
					|| (ambitoReal.equals(ambito1) && op2.obj != null)
					|| (op1.obj != null && op2.obj != null)) { //este caso es solo para las operaciones ejemplo.. 2*3 + 5*6 
				return true;
			}
		}
		
		
		return false;
	}
	
	public String mismoTipo(ParserVal op1, ParserVal op2) {
		String tipoDato1;
		String tipoDato2;
		String ambitoReal = this.getNameManglingForAmbito(this.ambito);
		
		
		if(op1.obj == null) {
			if(esVariable(op1)) {
				tipoDato1 = Lexico.getTokenFromTS(op1.sval + "@Variable" + ambitoReal).getTipoDato();	
			}
			else {
				tipoDato1 = Lexico.getTokenFromTS(op1.sval).getTipoDato();
			}	
		}
		else {
			tipoDato1 = ((Terceto)op1.obj).getTipoDato();
		}
		
		if(op2.obj == null) {
			if(esVariable(op2)) {
				tipoDato2 = Lexico.getTokenFromTS(op2.sval + "@Variable" + ambitoReal).getTipoDato();
			}
			else {
				tipoDato2 = Lexico.getTokenFromTS(op2.sval).getTipoDato();
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
		
		if(val.sval.charAt(0) == '-') {
			if(!Character.isLetter(val.sval.charAt(1))) {
				return false;
			}
			return true;
		}
		
		if(!Character.isLetter(val.sval.charAt(0))) {
			return false;
		}
			
		return true;
	}
	
	public void setTipoDatoTerceto(Terceto t, ParserVal val1, ParserVal val2) {
		t.setTipoDato(mismoTipo(val1, val2));
	}
	
	public boolean existeVariable(ParserVal variable, boolean esDeclaracion)
	{
		if(esVariable(variable)) {
			
			String ambitoReal = this.getNameManglingForAmbito(this.ambito);
			
			if(this.lexico.estaDeclarada(variable.sval, "variable", ambitoReal, esDeclaracion)) {
				Token t = Lexico.getTokenFromTS(variable.sval + "@Variable" + ambitoReal);
				if (t != null) {
					if(t.getAmbito().equals(ambitoReal) || ambitoReal.contains(t.getAmbito())) {
						return true;
					}	
				} else {
					return false;
				}
				
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean existeFuncion(ParserVal funcion, boolean esDeclaracion)
	{
		return this.lexico.estaDeclarada(funcion.sval, "funcion", this.getNameManglingForAmbito(this.ambito), esDeclaracion);
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
	
	public void aumentarAmbito(ParserVal nomFuncion) {
		if(this.ambitos.containsKey(nomFuncion.sval)) {
			this.ambito = this.ambitos.get(nomFuncion.sval);
		}
		else {
			this.maxAmbito = (char)(this.maxAmbito+1); 
			this.ambito = this.ambito + ":" + this.maxAmbito;
			this.ambitos.put(nomFuncion.sval, this.ambito);
		}
		
	}
	
	public void decrementarAmbito() {
		this.ambito = this.ambito.substring(0, this.ambito.length()-2);
	}
	
	public String getAmbito() {
		return this.ambito;
	}
	
	public Lexico getLexico() {
		return lexico;
	}
	
	public void start() {		
//		this.ppal.mostrarMensaje("------------------LISTADO DE TOKENS------------------");
//		this.ppal.mostrarMensaje("");
//		this.lexico.showAllTokens();
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("----------------------GRAMATICA----------------------");
		this.ppal.mostrarMensaje("");
		
		int result = parser.yyparse();
		switch (result) {
		case 0:
			this.ppal.mostrarMensaje("----SE OBTUVE LA LISTA DE REGLAS CORRECTAMENTE---");
			break;
		default:
			this.ppal.mostrarMensaje("------NO SE PUDO OBTENER LA LISTA DE REGLAS------");
		}
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("----------------------TERCETOS----------------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showTercetos());
		
		//realizo la optimizacion a las listas con tercetos
		this.tercetos = Optimizador.optimizacionRedundanciaSimple(this.tercetos);
//		Optimizador.optimizacionRedundanciaSimple(this.tercetosFuncion);
		//realizo la optimizacion a las listas con tercetos
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("----------------TERCETOS OPTIMIZADOS----------------");
		this.ppal.mostrarMensaje("---------------POR REDUNDANCIA SIMPLE---------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showTercetos());
		
		if(this.huboErrores()) {
			this.ppal.mostrarMensaje("");
			this.ppal.mostrarMensaje("-----------------------ERRORES----------------------");
			this.ppal.mostrarMensaje("");
			this.ppal.mostrarMensaje(this.showErrores());	
		}

		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("------------------TABLA DE SIMBOLOS-----------------");
		this.ppal.mostrarMensaje("");
		
		this.lexico.depurarTS();
		
		this.ppal.mostrarMensaje(this.lexico.printTSimbolos());
		
		this.generador.setListaTercetos(this.getAllTercetos()); 
		if (this.generador.generarCodigo()) {
			this.ppal.mostrarMensaje("----------------------------------------------------");
			this.ppal.mostrarMensaje("Se gener\u00f3 el Assembler");
			this.ppal.mostrarMensaje("----------------------------------------------------");
		}
		
		//this.ppal.mostrarMensaje(this.lexico.printTSimbolos());
	}
}
