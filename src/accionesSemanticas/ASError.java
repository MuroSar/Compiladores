package accionesSemanticas;

import compilador.Lexico;

public class ASError implements AccionSemantica{

	@Override
	public String ejecutar(Lexico lexico, char loQueLee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "AS-ERROR";
	}
}
