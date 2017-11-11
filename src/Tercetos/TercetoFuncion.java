package Tercetos;

import compilador.ParserVal;

public class TercetoFuncion extends Terceto {

	public TercetoFuncion(ParserVal primero, int pos) {
		super("FN", primero, null, pos);
	}
		
	public String getCodigo()
	{
		return "CALL " + "algo"+ "RET";
	}
}
