package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS07 implements AccionSemantica {
//	1.	Inicializar string 
//	2.	Agregar caracter al string
//	3.	Si la posici�n que sigue es un car�cter �=� o un �>�
//		a.	Leo ese car�cter (pos++)
//		b.	Buscar en la TS
//			i.	Si est�, devolver ID + PuntTS
//			ii.	Si no est�,
//				1.	Alta en la TS
//				2.	Devolver ID + PuntTS
//	4.	Si no es ese car�cter 
//		a.	Buscar en la TS
//			i.	Si est�, devolver ID + PuntTS
//			ii.	Si no est�,
//				1.	Alta en la TS
//				2.	Devolver ID + PuntTS


	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		token = new Token();
		token.setLexema(token.getLexema() + loQueLee);
		
		lexico.aumentarLongitud();
		
		char prox = lexico.getProxPos();
		if (prox == '=' || prox == '>')
		{
			lexico.aumentarLongitud();
			lexico.setPosMasUno();
		}
		
		int key = lexico.getKeySimbolos(token.getLexema());
		if(key == -2)//significa que no existe en la tabla de simbolos todavia
		{
			lexico.putSimbolo(token.getLexema());
			key = lexico.getKeySimbolos(token.getLexema());
		}
		token.setKey(key);
	}

	@Override
	public String toString() {
		return "AS-07";
	}
}
