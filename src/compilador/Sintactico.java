package compilador;

import compilador.Parser;
import complementos.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tercetos.Terceto;
import compilador.Lexico;
import interfaz.Principal;

public class Sintactico {
	
	private Principal ppal;
	private Lexico lexico;
	private Parser parser;
	private GenCodigo generador;
	private ArrayList<Terceto> tercetos;

	public Sintactico(Principal principal, Lexico lexico, Parser parser, GenCodigo generador) {
		this.ppal = principal;
		this.lexico = lexico;
		this.parser = parser;
		this.generador = generador;
		this.tercetos = new ArrayList<Terceto>();
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
		
		//cada ParserVal tiene en su sval el nombre de la variable..
		//hay que buscarla en la tabla de simbolos..
		//y ponerle al final el @variable..
		//tambien hay que setearle el tipo..
		
		this.showMessage("variables --> " + variables + "----");
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
	}
}
