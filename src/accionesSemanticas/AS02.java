package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS02 implements AccionSemantica {
//	1.	Agregar la letra, digito o carácter al string

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		token.setLexema(token.getLexema() + loQueLee);
		
		lexico.aumentarLongitud();
	}

	@Override
	public String toString() {
		return "AS-02";
	}
}
