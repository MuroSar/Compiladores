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
	
	public void start() {
		int result = parser.yyparse();
		switch (result) {
		case 0:
			this.ppal.mostrarMensajeSintactico("Gramatica: OK");
			break;
		default:
			this.ppal.mostrarMensajeSintactico("Gramatica: ERROR");
		}
	}
}
