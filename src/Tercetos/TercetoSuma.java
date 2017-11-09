package Tercetos;

import compilador.ParserVal;

public class TercetoSuma extends Terceto{

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos) {
		super("+", primero, segundo, pos);
		/*
		if(primero.obj != null) {
			int referencia = ((Terceto)primero.obj).getPos(); 
			this.primero = "[" + referencia + "]";
		}
		else {
			this.primero = primero.sval;	
		}
		
		if(segundo.obj != null) {
			int referencia = ((Terceto)segundo.obj).getPos(); 
			this.segundo = "[" + referencia + "]";
		}
		else {
			this.segundo = segundo.sval;	
		}*/
	}
	
	public String getCodigo()
	{
		String s1="MOV R1 __ ";
		String s2="ADD R1 __";
		String s3="MOV __ R1";
		
		
		return s1 + "\n" + s2 + "\n" + s3 + "\n";
	}
	
}
