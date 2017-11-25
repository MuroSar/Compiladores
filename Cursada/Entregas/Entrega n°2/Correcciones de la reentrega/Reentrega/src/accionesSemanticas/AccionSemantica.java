package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public interface AccionSemantica {
	
	public abstract void ejecutar(Lexico lexico, char loQueLee, Token token);
	
	public abstract String toString();

}
