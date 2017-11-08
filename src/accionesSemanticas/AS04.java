package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS04 implements AccionSemantica {
//	1. Devolver a la entrada el último carácter leído
//	2. Revisa que no se pase del rango
//		a. Si se pasa, corrige y genera el error
//	3. Devuelve el Token armado
	
	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		int max = (int) (Math.pow(2, 31) - 1);
		if (Long.valueOf(token.getLexema()) <= max)
		{
			token.setType("Entero largo (LONG)");
			token.setTipoDato("LONG");
			token.setKey(258);
			token.setLinea(lexico.getFila()+1);
		}
		else
		{
			//ACA CARGA EL ERROR..
			ErrorToken error = new ErrorToken();
			error.setOriginal(token.getLexema());
			
			token.setLexema(String.valueOf(max));
			token.setType("Entero largo (LONG)");
			token.setTipoDato("LONG");
			token.setKey(258);
			token.setLinea(lexico.getFila()+1);
			
			error.setCorregido(token.getLexema());
			error.setNroToken(258);
			error.setError("El tamaño del LONG excede el máximo permitido");
			error.setAccionCorrectiva("Se acotó al máximo permitido");
			error.setNroLinea(lexico.getFila()+1);
			
			
			lexico.addErrorToken(error);
		}
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-04";
	}
}
