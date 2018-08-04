package Tercetos;

import compilador.GenCodigo;
import compilador.ParserVal;

public class TercetoBIncondicional extends Terceto {

	public TercetoBIncondicional(int pos, GenCodigo generador) {
		super("BI", null, null, pos, generador);
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
