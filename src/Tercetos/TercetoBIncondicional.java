package Tercetos;

import compilador.ParserVal;

public class TercetoBIncondicional extends Terceto {

	public TercetoBIncondicional(int pos) {
		super("BI", null, null, pos);
	}
		
	public String getCodigo()
	{
		int i=Integer.valueOf(this.primero.substring(1, primero.length()-1));
		
		this.generador.addLabel(i);
		
		String label = "";
		if(this.marcaDesp) {
			label = "Label" + (this.getPos()+1) + ":\n";
			this.marcaDesp = false;
		}
		
		return "JMP Label"+ i + "\n" + label;
	}
}
