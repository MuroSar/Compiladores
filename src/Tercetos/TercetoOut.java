package Tercetos;

import compilador.ParserVal;

public class TercetoOut extends Terceto {

	public TercetoOut(ParserVal primero, int pos) {
		super("OUT", primero, null, pos);
	}
		
	public String getCodigo()
	{
		
		//lista + nombre + " DB " + t.getLexema() + ", 0\n";
		return "OUTS "+this.primero+"\n";
	}
}