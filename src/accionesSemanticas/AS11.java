package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS11 implements AccionSemantica {
//	1.	Devolver a la entrada el �ltimo car�cter le�do
//	2.	Buscar en la TS
//		a.	Si est�, devolver ID + PuntTS
//		b.	Si no est�,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS

	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		int key = lexico.getKeySimbolos(token.getLexema());
		if(key == -2)//significa que no existe en la tabla de simbolos todavia
		{
			lexico.putSimbolo(token);
			key = lexico.getKeySimbolos(token.getLexema());
		}
		if(token.getLexema().equals("*"))
		{
			token.setType("Operador Aritmetico");
		}
		else if(token.getLexema().equals("=") || token.getLexema().equals("<") || token.getLexema().equals(">"))
		{
			token.setType("Comparadores");
		}
		
		token.setKey(key);
	}

	@Override
	public String toString() {
		return "AS-11";
	}
}
