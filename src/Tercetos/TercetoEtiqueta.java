package Tercetos;

import compilador.ParserVal;

public class TercetoEtiqueta extends Terceto{

	public TercetoEtiqueta(String operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador, primero, null, pos);
	}
		
	public String getCodigo()
	{
		return this.primero+":"+"\n";
	}

}
