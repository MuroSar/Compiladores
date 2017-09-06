package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS01 implements AccionSemantica{
//	1.	Inicializar string (con la máxima logitud permitida --> 15)
//	2.	Agregar letra al string


	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		token.setLexema(token.getLexema() + loQueLee);
	}

	@Override
	public String toString() {
		return "AS-01";
	}
}
