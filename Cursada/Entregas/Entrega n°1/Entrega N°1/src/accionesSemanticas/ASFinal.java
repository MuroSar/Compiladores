package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class ASFinal implements AccionSemantica {
//	1. Devolver a la entrada el último carácter leído
//	2. Pregunta por el contenido del lexema
//	3. Arma el Token correspondiente
// 	4. Devuelve el Token
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {

		token.setLexema(token.getLexema() + loQueLee);
		
		if(token.getLexema().equals("+") || token.getLexema().equals("-") || token.getLexema().equals("/"))
		{
			token.setType("OperadorAritmetico");
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
			token.setType("Cadena");
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
