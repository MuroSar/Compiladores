package Tercetos;

import compilador.ParserVal;

public class TercetoBFalse extends Terceto {
	
	private int s1;

	public TercetoBFalse(ParserVal primero, int pos) {
		super("BF", primero, null, pos);
		setPrimero(Integer.valueOf(primero.sval));
		
	}
		
	public String getCodigo()
	{
		s1=Integer.valueOf(this.segundo.substring(1, segundo.length()-1));
		return "JLE Label"+s1+"\n";
	}
}
