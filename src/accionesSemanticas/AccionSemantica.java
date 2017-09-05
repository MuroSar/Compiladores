package accionesSemanticas;

import compilador.Lexico;
import complementos.Token;

public interface AccionSemantica {
	
	public abstract Token ejecutar(Lexico lexico, char loQueLee);
	
	public abstract String toString();

}
