package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASFinal implements AccionSemantica {
//	1.	Agregar el car�cter al string
//	2.	Buscar en la TS
//		a.	Si est�, devolver ID + PuntTS
//		b.	Si no est�,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {

		token.setLexema(token.getLexema() + loQueLee);
		
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
		return "AS-FINAL";
	}

}
