package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS05 implements AccionSemantica {

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		if(token.getLexema().equals(","))
		{
			token.setType("Literal");
			token.setKey(274);
			token.setLinea(lexico.getFila());
		}
		else
		{
			double max = Double.MAX_VALUE;
			String lexema = token.getLexema().replaceAll(",", ".");
			if (Double.valueOf(lexema) <= max)
			{
				token.setType("Dobles (DOUBLE)");
				token.setKey(258);
				token.setLinea(lexico.getFila());
			}
			else
			{
				//ACA CARGA EL ERROR..
				ErrorToken error = new ErrorToken();
				error.setOriginal(token.getLexema());
				
				token.setLexema(String.valueOf(max));
				
				error.setCorregido(token.getLexema());
				error.setNroToken(258);
				error.setError("El tamaño del DOUBLE excede el máximo permitido");
				error.setAccionCorrectiva("Se acotó al máximo permitido");
				error.setNroLinea(lexico.getFila());
				
				lexico.addErrorToken(error);
			}
		}
		lexico.putSimbolo(token);

	}

	@Override
	public String toString() {
		return "AS-05";
	}

}
