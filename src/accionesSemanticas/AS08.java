package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS08 implements AccionSemantica {
//	1.	Agregar caracter al string
//	2.	Descarto todo hasta el fin de línea.
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
//		token.setLexema(token.getLexema() + loQueLee);
//		
//		char prox = loQueLee;
//		while (prox != '\n')
//		{
//			prox = lexico.getProxPos();
//			lexico.setPosMasUno();
//		}
		token.setLexema("");
		lexico.setNuevaLinea();
	}

	@Override
	public String toString() {
		return "AS-08";
	}
}
