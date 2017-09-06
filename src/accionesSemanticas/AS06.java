package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS06 implements AccionSemantica {
//	1.	Inicializar string 
//	2.	Agregar caracter al string
//	3.	Si la posici�n que sigue es un car�cter �=�
//		a.	Leo ese car�cter (pos++)
//		b.	Buscar en la TS
//			i.	Si est�, devolver ID
//			ii.	Si no est�,
//				1.	Alta en la TS
//				2.	Devolver ID
//	4.	Si no es ese car�cter 
//		a.	Buscar en la TS
//			i.	Si est�, devolver ID
//			ii.	Si no est�,
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
			
			int key = lexico.getKeySimbolos(token.getLexema());
			if(key == -2)//significa que no existe en la tabla de simbolos todavia
			{
				lexico.putSimbolo(token);
				key = lexico.getKeySimbolos(token.getLexema());
			}
			token.setType("Comparador");
			token.setKey(key);
		}
	}

	@Override
	public String toString() {
		return "AS-06";
	}
}
