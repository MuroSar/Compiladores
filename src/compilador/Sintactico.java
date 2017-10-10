package compilador;

import compilador.Parser;
import compilador.Lexico;
import interfaz.Principal;

public class Sintactico {
	
	private Principal ppal;
	private Lexico lexico;
	private Parser parser;
	private GenCodigo generador;

	public Sintactico(Principal principal, Lexico lexico, Parser parser, GenCodigo generador) {
		this.ppal = principal;
		this.lexico = lexico;
		this.parser = parser;
		this.generador = generador;
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
		this.ppal.mostrarMensaje(this.generador.showTercetos());
	}
}
