package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS04 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		int max = (int) (Math.pow(2, 31) - 1);
		if (Long.valueOf(token.getLexema()) <= max)
		{
			token.setType("Entero largo (LONG)");
			token.setKey(258);
		}
		else
		{
			//ACA CARGA EL ERROR..
			ErrorToken error = new ErrorToken();
			error.setOriginal(token.getLexema());
			
			token.setLexema(String.valueOf(max));
			
			error.setCorregido(token.getLexema());
			error.setNroToken(258);
			error.setError("El tamaño del LONG excede el máximo permitido");
			error.setAccionCorrectiva("Se acotó al máximo permitido");
			error.setNroLinea(lexico.getFila());
			
			lexico.addErrorToken(error);
		}
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-04";
	}
}
