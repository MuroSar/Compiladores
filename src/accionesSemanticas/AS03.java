package accionesSemanticas;

import compilador.Lexico;
import complementos.ErrorToken;
import complementos.Token;

public class AS03 implements AccionSemantica {
//	1.	Devolver a la entrada el �ltimo car�cter le�do
//	2.	Buscar en la TPR
//		a.	Si est�, devolver la Palabra Reservada
//		b.	Si no est�,
//	3.	Buscar en la TS
//		a.	Si est�, devolver ID + PuntTS
//		b.	Si no est�,
//			i.	Alta en la TS
//			ii.	Devolver ID + PuntTS


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
				error.setError("El tama�o del identificador excede el maximo permitido");
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
