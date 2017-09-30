package compilador;

import compilador.Parser;
import compilador.Lexico;
import interfaz.Principal;

public class Sintactico {
	
	private Principal ppal;
	private Lexico lexico;
	private Parser parser;

	public Sintactico(Principal principal, Lexico lexico, Parser parser) {
		this.ppal = principal;
		this.lexico = lexico;
		this.parser = parser;
	}

	public void showMessage(String mensaje)
	{
		this.ppal.mostrarMensajeSintactico(mensaje);
	}
	
	public void showError(String error)
	{
		this.ppal.mostrarMensajeSintactico("-------------------------------------------------");
		this.ppal.mostrarMensajeSintactico(error);
		this.ppal.mostrarMensajeSintactico("-------------------------------------------------");
	}
	
	public void start() {
		this.ppal.mostrarMensajeSintactico("----------------LISTADO DE TOKENS----------------");
		this.ppal.mostrarMensajeSintactico("");
		this.lexico.showAllTokens();
		this.ppal.mostrarMensajeSintactico("");
		this.ppal.mostrarMensajeSintactico("--------------------GRAMATICA--------------------");
		this.ppal.mostrarMensajeSintactico("");
		
		int result = parser.yyparse();
		switch (result) {
		case 0:
			this.ppal.mostrarMensajeSintactico("------------------Gramatica: OK------------------");
			break;
		default:
			this.ppal.mostrarMensajeSintactico("-----------------Gramatica: ERROR----------------");
		}
	}
}
