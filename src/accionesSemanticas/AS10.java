package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public class AS10 implements AccionSemantica {
//	1.	Si viene un salto de l�nea 
//		a.	Se borran los �ltimos 3 caracteres que ser�n los �.�
//	2.	Si no se agrega el car�cter al string.

	@Override
	public void ejecutar(Lexico lexico, char loQueLee, Token token) {
		
		if (loQueLee == '\n')
		{
			//borro los ultimos 3 caracteres que son los ...
			String lexema = token.getLexema();
			String aux = lexema.substring(0, lexema.length()-3);
			token.setLexema(aux);
			
			lexico.setNuevaLinea();
		}
		else
		{
			token.setLexema(token.getLexema() + loQueLee);
		}
	}
	
	@Override
	public String toString() {
		return "AS-10";
	}
}
