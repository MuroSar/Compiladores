package Tercetos;

import compilador.ParserVal;

public class TercetoResta extends Terceto{

	public TercetoResta(ParserVal primero, ParserVal segundo, int pos) {
		super("-", primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "";
	}

}
