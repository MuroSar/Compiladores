package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS05 implements AccionSemantica {
//	1. Devolver a la entrada el �ltimo car�cter le�do
//	2. Si el lexema es una ","
//		a. Genera el literal
//	3. Si no, 
//		a. Revisa que no se pase del rango
//		b. Si se pasa, corrige y genera el error
//	4. Devuelve el Token armado

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		if(token.getLexema().equals(","))
		{
			token.setType("Literal");
			token.setKey(274);
			token.setLinea(lexico.getFila()+1);
		}
		else
		{
			double max = Double.MAX_VALUE;
			String lexema = token.getLexema().replaceAll(",", ".");
			if (Double.valueOf(lexema) <= max)
			{
				token.setType("Dobles (DOUBLE)");
				token.setTipoDato("DOUBLE");
				token.setKey(258);
				token.setLinea(lexico.getFila()+1);
			}
			else
			{
				//ACA CARGA EL ERROR..
				ErrorToken error = new ErrorToken();
				error.setOriginal(token.getLexema());
				
				token.setLexema(String.valueOf(max));
				token.setType("Dobles (DOUBLE)");
				token.setTipoDato("DOUBLE");
				token.setKey(258);
				token.setLinea(lexico.getFila()+1);
				
				error.setCorregido(token.getLexema());
				error.setNroToken(258);
				error.setError("El tama�o del DOUBLE excede el m�ximo permitido");
				error.setAccionCorrectiva("Se acot� al m�ximo permitido");
				error.setNroLinea(lexico.getFila()+1);
				
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
