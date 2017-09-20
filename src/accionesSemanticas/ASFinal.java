package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASFinal implements AccionSemantica {
//	1.	Agregar el carácter al string
//	2.	Buscar en la TS
//		a.	Si está, devolver ID + PuntTS
//		b.	Si no está,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {

		token.setLexema(token.getLexema() + loQueLee);
		
		if(token.getLexema().equals("+") || token.getLexema().equals("-") || token.getLexema().equals("/"))
		{
			token.setType("Operador aritmetico");
			token.setKey(276);
			token.setLinea(lexico.getFila());
		}
		else if(token.getLexema().equals("(") || token.getLexema().equals(")") || token.getLexema().equals(":") || token.getLexema().equals(".") || token.getLexema().equals("{") || token.getLexema().equals("}"))
		{
			token.setType("Literal");
			token.setKey(274);
			token.setLinea(lexico.getFila());
		}
		else
		{
			token.setType("Cadena de caracteres");
			token.setKey(273);
			token.setLinea(lexico.getFila());
		}
		
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-FINAL";
	}

}
