package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS06 implements AccionSemantica {
//	1.	Inicializar string 
//	2.	Agregar caracter al string
//	3.	Si la posición que sigue es un carácter ‘=’
//		a.	Leo ese carácter (pos++)
//		b.	Buscar en la TS
//			i.	Si está, devolver ID
//			ii.	Si no está,
//				1.	Alta en la TS
//				2.	Devolver ID
//	4.	Si no es ese carácter 
//		a.	Buscar en la TS
//			i.	Si está, devolver ID
//			ii.	Si no está,
//				1.	Alta en la TS
//				2.	Devolver ID
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		token.setLexema(token.getLexema() + loQueLee);
		
		char prox = lexico.getProxPos();
		if (prox == '=')
		{
			token.setLexema(token.getLexema() + prox);
			lexico.setPosMasUno();
			
			token.setType("Comparador");
			token.setKey(275);
			
			lexico.putSimbolo(token);
		}
	}

	@Override
	public String toString() {
		return "AS-06";
	}
}
