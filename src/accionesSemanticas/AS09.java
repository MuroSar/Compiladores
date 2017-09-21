package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS09 implements AccionSemantica {
//	1. Devolver a la entrada el último carácter leído
//	2. Pregunta por el contenido del lexema
//	3. Arma el Token correspondiente
// 	4. Devuelve el Token


	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		if(token.getLexema().equals("*"))
		{
			token.setType("Operador Aritmetico");
			token.setKey(276);
			token.setLinea(lexico.getFila());
		}
		else if(token.getLexema().equals("=") || token.getLexema().equals("<") || token.getLexema().equals(">"))
		{
			token.setType("Comparadores");
			token.setKey(275);
			token.setLinea(lexico.getFila());
		}
		
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-11";
	}
}
