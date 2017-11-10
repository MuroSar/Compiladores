package Tercetos;

import compilador.ParserVal;

public class TercetoBIncondicional extends Terceto {

	public TercetoBIncondicional(int pos) {
		super("BI", null, null, pos);
	}
		
	public String getCodigo()
	{
		int i=Integer.valueOf(this.primero.substring(1, primero.length()-1));
		return "JMP Label"+i
				+"me falta el label 17";
	}
}
