package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS03 implements AccionSemantica {
//	1.	Devolver a la entrada el �ltimo car�cter le�do
//	2.	Buscar en la TPR
//		a.	Si est�, devolver la Palabra Reservada
//		b.	Si no est�,
//	3.	Buscar en la TS
//		a.	Si est�, devolver ID + PuntTS
//		b.	Si no est�,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS


	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		if(lexico.existPalabrasReservadas(token.getLexema()))
		{
			token.setType("Palabra reservada");
		}
		else
		{
			token.setType("Identificador");
			if(token.getLexema().length() >= 15)
			{
				//ACA DEBE ROMPER O ALGO POR LA CANT DE CARACTERES POSIBLES.
			}
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
		return "AS-03";
	}
}
