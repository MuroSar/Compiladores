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
		
		int key = lexico.getKeySimbolos(token.getLexema());
		if(key == -2)//significa que no existe en la tabla de simbolos todavia
		{
			lexico.putSimbolo(token);
			key = lexico.getKeySimbolos(token.getLexema());
		}
		
		if(token.getLexema().equals("+") || token.getLexema().equals("-") || token.getLexema().equals("/"))
		{
			token.setType("Operador aritmetico");
		}
		else if(token.getLexema().equals("(") || token.getLexema().equals(")") || token.getLexema().equals(":") || token.getLexema().equals("."))
		{
			token.setType("Literal");
		}
		else if(token.getLexema().equals("{") || token.getLexema().equals("}"))
		{
			token.setType("Llave");
		}
		else
		{
			token.setType("Cadena de caracteres");
		}
		
		token.setKey(key);
	}

	@Override
	public String toString() {
		return "AS-FINAL";
	}

}
