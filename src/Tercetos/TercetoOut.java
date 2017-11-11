package Tercetos;

import compilador.ParserVal;

public class TercetoOut extends Terceto {

	public TercetoOut(ParserVal primero, int pos) {
		super("OUT", primero, null, pos);
	}
		
	public String getCodigo()
	{
		String aux = this.generador.getDeclaracionesOut();
		String nombre=this.primero.substring(1,this.primero.length()-1);
		aux += nombre + " DB " + nombre + ", 0\n";// ver que nombre se le pone
		this.generador.setDeclaracionesOut(aux);
		//return "OUTS "+this.primero+"\n"; //esto es lo que estaba antes
		return "invoke MessageBox, NULL, addr " + nombre +", addr " + nombre + ", MB_OK"; //esto es creo lo que va.
	}
}