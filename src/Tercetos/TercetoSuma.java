package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoSuma extends Terceto{
	
		private String aux1;
		private String aux2;
		private String s1;
		private String s2;
		private String s3;
		private ParserVal primero;
		private ParserVal segundo;

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos) {
		super("+", primero, segundo, pos);		
		this.primero = primero;
		this.segundo = segundo;
	}
		
	public String getCodigo()
	{
		Token tokenAux = new Token();
	
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				s1="FLD #aux" + aux1 + "\n";
				tokenAux.setLexema("#aux" + aux1);
				tokenAux.setTipoDato("DOUBLE");
				tokenAux.setType("Identificador");
			}
			else {
				s1="MOV R1,#aux" + aux1 + "\n";
				tokenAux.setLexema("#aux" + aux1);
				tokenAux.setTipoDato("LONG");
				tokenAux.setType("Identificador");
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) { //es una variable
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					s1="FLD " + aux1 + "@Variable";
				}
				else { //es una variable de tipo LONG
					s1="MOV R1,_" + aux1 + "\n";
				}
			}
			else {
				if (aux1.toString().contains(",")) { //es una constante de tipo DOUBLE
					s1="FLD " + aux1;
				}
				else { //es una constante de tipo LONG
					s1="MOV R1," + aux1 + "\n"; 
				}
			}
		}
		
		if(segundo.obj != null) { 
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				s2="FLD #aux" + aux2 + "\n";
				s3="FADD" + "\n" + "FSTP #aux" + this.getPos() + "\n";
				tokenAux.setLexema("#aux" + aux2);
				tokenAux.setTipoDato("DOUBLE");
				tokenAux.setType("Identificador");
			}
			else {
				s2="ADD R1,#aux" + aux2 + "\n";
				s3="MOV #aux"+ this.getPos()+ ",R1" + "\n";
				tokenAux.setLexema("#aux" + aux2);
				tokenAux.setTipoDato("LONG");
				tokenAux.setType("Identificador");
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					s2="FLD " + aux1 + "@Variable";
					s3="FADD" + "\n" + "FSTP #aux" + this.getPos() + "\n";
				}
				else
				{
					s2="ADD R1,_" + aux2 + "\n";
					s3="MOV #aux"+ this.getPos()+ ",R1" + "\n" + "JO _overflow\n";
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					//System.out.println("es un DOUBLE");
					s2= "FLD " + aux2;
					s3="FADD" + "\n" + "FSTP #aux" + this.getPos() + "\n"; //detalle: aux tiene q ser declarada como flotante. 
				}
				else {
					s2="ADD R1," + aux2;
					s3="MOV #aux"+ this.getPos()+ ",R1" + "\n" + "JO _overflow\n";
				}
			}
		}
		
		Lexico.putSimbolo(tokenAux); 
		
		return s1 + "\n" + s2 + "\n" + s3;
	}
}
