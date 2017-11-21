package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;

public class TercetoFuncion extends Terceto {

	public TercetoFuncion(ParserVal primero, int pos) {
		super("FN", primero, null, pos);
		
		this.setTipoDato(Lexico.getTokenFromTS(primero.sval + "@Funcion").getTipoDato());
		
	}
		
	public String getCodigo()
	{
		String s=this.generador.getNombreFuncion(this.primero);
		
		String label = "";
		if(this.marcaAntes) {
			label = "Label" + (this.getPos()-1) + "\n";
			this.marcaAntes = false;
		}
		
		return label + "CALL " + s + "\n"+"RET Label"+ this.segundo.substring(1, segundo.length()-1) + "\n";
	}
}
