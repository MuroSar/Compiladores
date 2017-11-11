package Tercetos;

import compilador.ParserVal;

public class TercetoFuncion extends Terceto {

	public TercetoFuncion(ParserVal primero, int pos) {
		super("FN", primero, null, pos);
	}
		
	public String getCodigo()
	{
		String s=this.generador.getNombreFuncion(this.primero);
		int pos=this.getPos()+1;
		return "CALL " + s + "\n"+"RET "+pos+"\n";
	}
}
