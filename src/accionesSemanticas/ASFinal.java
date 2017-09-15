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
		
		if(token.getLexema().equals("+") || token.getLexema().equals("-") || token.getLexema().equals("/"))
		{
			token.setType("Operador aritmetico");
			token.setKey(276);
		}
		else if(token.getLexema().equals("(") || token.getLexema().equals(")") || token.getLexema().equals(":") || token.getLexema().equals(".") || token.getLexema().equals("{") || token.getLexema().equals("}"))
		{
			token.setType("Literal");
			token.setKey(274);
		}
		else
		{
			token.setType("Cadena de caracteres");
			token.setKey(273);
		}
		
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-FINAL";
	}

}
