package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS09 implements AccionSemantica {
//	1. Devolver a la entrada el �ltimo car�cter le�do
//	2. Pregunta por el contenido del lexema
//	3. Arma el Token correspondiente
// 	4. Devuelve el Token


	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		if(token.getLexema().equals("*"))
		{
			token.setType("OperadorAritmetico");
			token.setKey(276);
			token.setLinea(lexico.getFila()+1);
		}
		else if(token.getLexema().equals("<") || token.getLexema().equals(">"))
		{
			token.setType("Comparadores");
			token.setKey(275);
			token.setLinea(lexico.getFila()+1);
		}
		else if(token.getLexema().equals("="))
		{
			token.setType("OperadorAsignacion");
			token.setKey(277);
			token.setLinea(lexico.getFila()+1);
		}
			
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-11";
	}
}
