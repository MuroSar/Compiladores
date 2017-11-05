package Tercetos;

import compilador.ParserVal;

public class TercetoDivision extends Terceto{

	public TercetoDivision(ParserVal primero, ParserVal segundo, int pos) {
		super("/", primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "";
	}

}
