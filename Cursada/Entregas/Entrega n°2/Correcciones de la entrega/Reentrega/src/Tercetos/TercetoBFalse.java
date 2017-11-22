package Tercetos;

import compilador.ParserVal;

public class TercetoBFalse extends Terceto {
	
	private int s1;
	private String operador;

	public TercetoBFalse(ParserVal primero, int pos) {
		super("BF", primero, null, pos);
		setPrimero(Integer.valueOf(primero.sval));
	}
		
	public String getCodigo()
	{
		String label = "";
		if(this.marcaDesp) {
			label = "Label" + (this.getPos()+1) + "\n";
			this.marcaDesp = false;
		}
		
		s1=Integer.valueOf(this.segundo.substring(1, segundo.length()-1));
		
		this.generador.addLabel(s1);
		
		operador=this.generador.getComparador();
		if(this.operador.equals("<")) {
			return "JGE Label"+ s1 + "\n" + label;
		}
		else {	
			if (this.operador.equals(">")) {
					return "JLE Label"+ s1 + "\n" + label;
			}
			else {
				if (this.operador.equals(">=")) {
					return "JL Label"+ s1 + "\n" + label;
				}
				else {
					if (this.operador.equals("<=")) {
						return "JG Label"+ s1 + "\n" + label;
					}
					else 
						if (this.operador.equals("==")) {
							return "JNE Label" + s1 + "\n" + label;
						}
					else {
						return "JE Label" + s1 + "\n" + label;
					}
				}
			}
		}
	}
}
