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
	private Stack<Terceto> pila;

	public Sintactico(Principal principal, Lexico lexico, Parser parser, GenCodigo generador) {
		this.ppal = principal;
		this.lexico = lexico;
		this.parser = parser;
		this.generador = generador;
		this.tercetos = new ArrayList<Terceto>();
		this.errores = new ArrayList<String>();
		this.pila = new Stack<Terceto>();
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
	
	public void actualizaVariables(ParserVal PVvariables, ParserVal tipo) {
		ArrayList<ParserVal> variables = new ArrayList<ParserVal>(((ArrayList<ParserVal>)PVvariables.obj));
		
		for(ParserVal var : variables) {
			Token aux = this.lexico.getTokenFromTS(var.sval);
			this.lexico.removeTokenFromTS(var.sval);
			
			String lexema = aux.getLexema();
			lexema = lexema + "@Variable";
			
			aux.setLexema(lexema);
			aux.setTipoDato(tipo.sval);		
			
			this.lexico.putSimbolo(aux);
		}
	}
	
	public boolean existeVariable(ParserVal variable)
	{
		return this.lexico.estaDeclarada(variable.sval);
	}
	
	public void showMessage(String mensaje)
	{
		this.ppal.mostrarMensaje(mensaje);
	}
	
	public void showError(String error)
	{
		this.ppal.mostrarMensaje("-------------------------------------------------");
		this.ppal.mostrarMensaje(error);
		this.ppal.mostrarMensaje("-------------------------------------------------");
	}
	
	public void start() {
		this.ppal.mostrarMensaje("----------------LISTADO DE TOKENS----------------");
		this.ppal.mostrarMensaje("");
		this.lexico.showAllTokens();
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("--------------------GRAMATICA--------------------");
		this.ppal.mostrarMensaje("");
		
		int result = parser.yyparse();
		switch (result) {
		case 0:
			this.ppal.mostrarMensaje("------------------Gramatica: OK------------------");
			break;
		default:
			this.ppal.mostrarMensaje("-----------------Gramatica: ERROR----------------");
		}
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("--------------------TERCETOS--------------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showTercetos());
		
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje("---------------------ERRORES--------------------");
		this.ppal.mostrarMensaje("");
		this.ppal.mostrarMensaje(this.showErrores());
	}
}
