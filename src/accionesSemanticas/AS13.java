package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS13 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		int max = (int) (Math.pow(2, 31) - 1);
		if (Integer.valueOf(token.getLexema()) <= max)
		{
			token.setType("Entero largo (LONG)");
		}
		
		int key = lexico.getKeySimbolos(token.getLexema());
		if(key == -2)//significa que no existe en la tabla de simbolos todavia
		{
			lexico.putSimbolo(token);
			key = lexico.getKeySimbolos(token.getLexema());
		}
		token.setKey(key);
		
	}

	@Override
	public String toString() {
		return "AS-13";
	}
}
