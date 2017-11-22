package Tercetos;

import compilador.ParserVal;

public class TercetoOut extends Terceto {

	public TercetoOut(ParserVal primero, int pos) {
		super("OUT", primero, null, pos);
	}
		
	public String getCodigo()
	{
		String nombre=this.primero;
		String aux = nombre + " DB " + nombre + ", 0\n";
		this.generador.setDeclaracionesOut(aux);
		
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(!this.generador.getSintactico().getNombreMarca().equals("")) {
				label = this.generador.getSintactico().getNombreMarca() + "\n";
				this.generador.getSintactico().setNombreMarca("");
			}
			else {
				label = "Label" + (this.getPos()) + "\n";
				this.marcaAntes = false;	
			}
		}
		
		return label + "invoke MessageBox, NULL, addr " + nombre +", addr " + nombre + ", MB_OK\n"; 
	}
}