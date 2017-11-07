package Tercetos;

import compilador.ParserVal;

public class TercetoBFalse extends Terceto {

	public TercetoBFalse(ParserVal primero, int pos) {
		super("BF", primero, null, pos);
		setPrimero(Integer.valueOf(primero.sval));
	}
		
	public String getCodigo()
	{
		return "";
	}
}
