package Tercetos;

import compilador.ParserVal;

public class TercetoFuncion extends Terceto {

	public TercetoFuncion(ParserVal primero, int pos) {
		super("FN", primero, null, pos);
	}
		
	public String getCodigo()
	{
		String s=this.generador.getNombreFuncion(this.primero);
		return "CALL " + s + "\n"+"RET Label"+ this.segundo.substring(1, segundo.length()-1) + "\n";
	}
}
