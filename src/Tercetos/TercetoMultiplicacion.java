package Tercetos;

import compilador.ParserVal;

public class TercetoMultiplicacion extends Terceto{

	public TercetoMultiplicacion(ParserVal primero, ParserVal segundo, int pos) {
		super("*", primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "";
	}

}
