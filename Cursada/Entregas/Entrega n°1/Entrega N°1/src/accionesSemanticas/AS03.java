package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS03 implements AccionSemantica {
//	1.	Devolver a la entrada el último carácter leído
//	2.	Obtiene el ID de token
//		a.	Si corresponde a una palabra reservada
//			i. Completa datos y devuelve
//		b. Si no, completa datos para un identificador
//			i. Revisa que no se pase del limite de caracteres
//				1.Si se pasa corrige y genera el error
//	3. Devuelve el token generado


	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		lexico.setPosMenosUno(); //para no perder este char y volver a leerlo la prox
		

		int key = lexico.existPalabraReservada(token.getLexema());
		
		if(key != 257)
		{
			token.setType("PalabraReservada");
			token.setKey(key);
			token.setLinea(lexico.getFila());
		}
		else
		{
			String lexemaLower = token.getLexema().toLowerCase();
			token.setLexema(lexemaLower);
			token.setKey(key);
			token.setLinea(lexico.getFila());
			
			token.setType("Identificador");
			if(token.getLexema().length() > 15)
			{
				//ACA CARGA EL ERROR..
				ErrorToken error = new ErrorToken();
				error.setOriginal(token.getLexema());
				
				token.setLexema(token.getLexema().substring(0, 14));
				
				error.setCorregido(token.getLexema());
				error.setNroToken(key);
				error.setError("El tamaño del identificador excede el maximo permitido");
				error.setAccionCorrectiva("Se corto el identificador a los 15 caracteres");
				error.setNroLinea(lexico.getFila());
				
				lexico.addErrorToken(error);
			}
		}
		
		lexico.putSimbolo(token);
	}

	@Override
	public String toString() {
		return "AS-03";
	}
}
