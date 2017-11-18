package Tercetos;

import compilador.ParserVal;

public class TercetoRet extends Terceto{

	public TercetoRet(String operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador, primero, segundo, pos);
	}
		
	public String getCodigo()
	{
		return "RET\n";
	}

}
