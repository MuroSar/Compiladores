package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS10 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		token.setLexema(token.getLexema() + loQueLee);	
	}

	@Override
	public String toString() {
		return "AS-10";
	}
}
