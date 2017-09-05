package accionesSemanticas;

import compilador.Lexico;

public interface AccionSemantica {
	
	public abstract String ejecutar(Lexico lexico, char loQueLee);
	
	public abstract String toString();

}
