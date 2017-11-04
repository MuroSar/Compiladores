package Tercetos;

import compilador.ParserVal;

public class TercetoSuma extends Terceto{

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos) {
		super("+", primero, segundo, pos);
	}
	
	public String getCodigo()
	{
		return "";
	}
	
}
