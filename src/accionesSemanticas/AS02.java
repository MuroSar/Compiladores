package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS02 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		token.setLexema(token.getLexema().substring(0, token.getLexema().length()-1));
		
		if(token.getLexema().equals(","))
		{
			token.setType("Literal");
			token.setKey(274);
		}

		lexico.putSimbolo(token);	
	}

	@Override
	public String toString() {
		return "AS-02";
	}


}
