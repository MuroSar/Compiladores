package Tercetos;

import compilador.ParserVal;

public class TercetoAsignacion extends Terceto{

	public TercetoAsignacion(ParserVal primero, ParserVal segundo, int pos) {
		super("=", primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "";
	}
}
