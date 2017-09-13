package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS03 implements AccionSemantica {
//	1.	Devolver a la entrada el último carácter leído
//	2.	Buscar en la TPR
//		a.	Si está, devolver la Palabra Reservada
//		b.	Si no está,
//	3.	Buscar en la TS
//		a.	Si está, devolver ID + PuntTS
//		b.	Si no está,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS


	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		
		String lexemaLower = token.getLexema().toLowerCase();
		token.setLexema(lexemaLower);
		
		if(lexico.existPalabrasReservadas(token.getLexema()))
		{
			token.setType("Palabra reservada");
		}
		else
		{
			token.setType("Identificador");
			if(token.getLexema().length() > 15)
			{
				//ACA CARGA EL ERROR..
				ErrorToken error = new ErrorToken();
				error.setOriginal(token.getLexema());
				
				token.setLexema(token.getLexema().substring(0, 14));
				
				error.setCorregido(token.getLexema());
				error.setError("El tamaño del identificador excede el maximo permitido");
				error.setAccionCorrectiva("Se corto el identificador a los 15 caracteres");
				error.setNroLinea(lexico.getFila());
				
				lexico.addErrorToken(error);
			}
		}
		int key = lexico.getKeySimbolos(token.getLexema());
		if(key == -2)//significa que no existe en la tabla de simbolos todavia
		{
			lexico.putSimbolo(token);
			key = lexico.getKeySimbolos(token.getLexema());
		}
		
		token.setKey(key);
	}

	@Override
	public String toString() {
		return "AS-03";
	}
}
