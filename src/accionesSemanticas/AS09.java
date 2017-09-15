package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS09 implements AccionSemantica {
//	1.	Devolver a la entrada el �ltimo car�cter le�do
//	2.	Buscar en la TS
//		a.	Si est�, devolver ID + PuntTS
//		b.	Si no est�,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS

	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		if(token.getLexema().equals("*"))
		{
			token.setType("Operador Aritmetico");
			token.setKey(276);
		}
		else if(token.getLexema().equals("=") || token.getLexema().equals("<") || token.getLexema().equals(">"))
		{
			token.setType("Comparadores");
			token.setKey(275);
		}
		
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-11";
	}
}
