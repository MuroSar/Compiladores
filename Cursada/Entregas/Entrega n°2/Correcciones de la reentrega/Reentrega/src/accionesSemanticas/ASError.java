package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASError implements AccionSemantica{

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {

	}

	@Override
	public String toString() {
		return "AS-ERROR";
	}
}
