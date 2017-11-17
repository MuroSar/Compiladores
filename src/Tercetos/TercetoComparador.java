package Tercetos;

import compilador.ParserVal;
import compilador.Sintactico;

public class TercetoComparador extends Terceto{
	
	private ParserVal primero;
	private ParserVal segundo;
	private String aux1;
	private String aux2;
	private String s1;
	private String s2;
	private String CodAux;

	public TercetoComparador(ParserVal operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador.sval, primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
		this.CodAux = "";
	}
		
	public String getCodigo()
	{
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			s1="#aux"+aux1; //es una referencia
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				s1="_"+aux1; //es una variable
			}
			else {
				CodAux="MOV R1," + aux1 + "\n";
				s1="R1"; //es un numero
			}
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			s2="#aux"+aux2;
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				s2="_"+aux2;
			}
			else {
				CodAux="MOV R2," + aux2 + "\n";
				s2="R2"; //es un numero
			}
		}
		
		if(this.operador.equals("<")) { //<>
			return CodAux + "CMP " + s1 + "," + s2 + "\nJL direccion_falso\n";
		}
		else {	
			if (this.operador.equals(">")) {
					return CodAux +"CMP " + s1 + "," + s2 + "\nJBE direccion_falso\n";
					}
			else {
				if (this.operador.equals(">=")) {
					return CodAux +"CMP " + s1 + "," + s2 + "\nJB direccion_falso\n";
				}
				else {
					if (this.operador.equals("<=")) {
						return CodAux +"CMP " + s1 + "," + s2 + "\nJG direccion_falso\n";
					}
					else {
						//es un ==
						return CodAux +"CMP " + s1 + "," + s2+ "\nJNE direccion_falso\n";
					}
				}
			}
		}
	}
}
