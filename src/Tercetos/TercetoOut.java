package Tercetos;

import compilador.ParserVal;

public class TercetoOut extends Terceto {

	public TercetoOut(ParserVal primero, int pos) {
		super("OUT", primero, null, pos);
	}
		
	public String getCodigo()
	{
		String nombre=this.primero;
		String aux = nombre + " DB " + nombre + ", 0\n";
		this.generador.setDeclaracionesOut(aux);
		return "invoke MessageBox, NULL, addr " + nombre +", addr " + nombre + ", MB_OK\n"; 
	}
}