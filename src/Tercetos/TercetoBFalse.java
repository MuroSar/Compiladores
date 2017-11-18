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
		s1=Integer.valueOf(this.segundo.substring(1, segundo.length()-1));
		operador=this.generador.getComparador();
		if(this.operador.equals("<")) {
			return "JL Label"+ s1 + "\n";
		}
		else {	
			if (this.operador.equals(">")) {
					return "JBE Label"+ s1 + "\n";
					}
			else {
				if (this.operador.equals(">=")) {
					return "JB Label"+ s1 + "\n";
				}
				else {
					if (this.operador.equals("<=")) {
						return "JG Label"+ s1 + "\n";
					}
					else {
						return "JNE Label" + s1 + "\n";
					}
				}
			}
		}
	}
}
