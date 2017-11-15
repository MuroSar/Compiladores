package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS08 implements AccionSemantica {
//	1. Borra el lexema leido
//	2. Aumenta la linea donde esta leyendo
//	3. Pone el estado en 0
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) 
	{
		token.setLexema("");
		lexico.setNuevaLinea();
		lexico.setEstado(0);
	}

	@Override
	public String toString() {
		return "AS-08";
	}
}
