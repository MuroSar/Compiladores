package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;

public class TercetoSuma extends Terceto{
	
		private String aux1;
		private String aux2;

		private String s1;
		private String s2;
		private String s3;
		
		private ParserVal primero;
		private ParserVal segundo;
		private int pos;

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos) {
		super("+", primero, segundo, pos);
				
		this.primero = primero;
		this.segundo = segundo;
		this.pos = pos;
	}
		
	public String getCodigo()
	{
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			s1="MOV R1 #aux"+aux1;
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				s1="MOV R1 _"+aux1;
			}
			else {
				s1="MOV R1 "+aux1;	
			}
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			s2="ADD R1 #aux"+aux2;
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				s2="ADD R1 _"+aux2;
			}
			else {
				s2="ADD R1 "+aux2;	
			}
			
		}
		s3="MOV #aux"+ pos + " R1";
	
		Lexico.putSimboloAsm("#aux"+pos);
		
		return s1 + "\n" + s2 + "\n" + s3 + "\n";
	}
	
	
}
