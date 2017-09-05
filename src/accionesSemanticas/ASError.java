package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASError implements AccionSemantica{

	@Override
	public Token ejecutar(Lexico lexico, char loQueLee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "AS-ERROR";
	}
}
