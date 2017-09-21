package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASDescarta implements AccionSemantica{
//	1. Si viene un salto de línea 
//		a. Se aumenta la linea y se pone el estado en cero

	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		if(loQueLee == '\n')
		{
			lexico.setNuevaLinea();
			lexico.setEstado(0);
		}
	}

	@Override
	public String toString() {
		return "AS-VEREMOS";
	}

}
