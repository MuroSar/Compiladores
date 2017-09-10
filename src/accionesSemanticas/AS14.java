package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS14 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		double max = Double.MAX_VALUE;
		String lexema = token.getLexema().replaceAll(",", ".");
		if (Double.valueOf(lexema) <= max)
		{
			token.setType("Dobles (DOUBLE)");
		}
		else
		{
			//DEBERIAMOS MOSTRAR ALGUN ERROR O ALGO..
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
		return "AS-14";
	}

}
