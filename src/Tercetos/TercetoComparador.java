package Tercetos;

import compilador.ParserVal;

public class TercetoComparador extends Terceto{

	public TercetoComparador(ParserVal operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador.sval, primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "CMP";
	}

}
