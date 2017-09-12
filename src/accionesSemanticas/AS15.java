package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS15 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		token.setLexema(token.getLexema().substring(0, token.getLexema().length()-1));
		
		if(token.getLexema().equals(","))
		{
			token.setType("Literal");
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
		return "AS-15";
	}


}
