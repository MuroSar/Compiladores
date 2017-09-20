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
		token.setLexema(token.getLexema() + loQueLee);
		
		char prox = lexico.getProxPos();
		if (prox == '=' || prox == '>')
		{
			token.setLexema(token.getLexema() + prox);
			lexico.setPosMasUno();

			token.setType("Comparador");
			token.setKey(275);
			token.setLinea(lexico.getFila());
			
			lexico.putSimbolo(token);

		}		
	}

	@Override
	public String toString() {
		return "AS-07";
	}
}
