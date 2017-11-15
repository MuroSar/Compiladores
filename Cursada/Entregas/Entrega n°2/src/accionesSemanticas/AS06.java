package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS06 implements AccionSemantica {
//	1. Si el proximo caracter es un "="
//		a. avanza la posicion para no leer dos veces lo mismo
//		b. Genera el token Comparador
//		c. Devuelve el Token armado
	
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
			token.setLinea(lexico.getFila()+1);
			
			lexico.putSimbolo(token);
		}
	}

	@Override
	public String toString() {
		return "AS-06";
	}
}
