package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoResta extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s1;
	private String s2;
	private String s3;
	private ParserVal primero;
	private ParserVal segundo;
	Token tokenAux = new Token();

	public TercetoResta(ParserVal primero, ParserVal segundo, int pos) {
		super("-", primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
	}
		
	public String getCodigo()
	{
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
				s1="MOV R1,#aux"+aux1;
				tokenAux.setLexema("#aux" + aux1);
				tokenAux.setTipoDato("LONG");
				tokenAux.setType("Identificador");
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					s1="FLD " + aux1 + "@Variable";
				}
				else { //es una variable de tipo LONG
					s1="MOV R1,_"+aux1;
				}
			}
			else {
				if (aux1.toString().contains(",")) {
						s1="FLD " + aux1;
				}
				else {
					s1="MOV R1,"+aux1;
				}
			}
		}
		
		if(segundo.obj != null) { //terminar
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				s2="FLD #aux" + aux2 + "\n";
				s3="FSUB" + "\n" + "FSTP #aux" + this.getPos();
				tokenAux.setLexema("#aux" + aux2);
				tokenAux.setTipoDato("DOUBLE");
				tokenAux.setType("Identificador");
			}
			else {
				s2="SUB R1 #aux"+aux2;
				s3="MOV #aux"+ this.getPos() + ",R1";
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
					s3="FSUB" + "\n" + "FSTP #aux" + this.getPos();
				}
				else
				{
					s2="SUB R1,_"+aux2;
					s3="MOV #aux"+ this.getPos() + ",R1";
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					s2= "FLD " + aux2;
					s3="FSUB" + "\n" + "FSTP #aux" + this.getPos(); 
				}
				else {
					s2="SUB R1,"+aux2;
					s3="MOV #aux"+ this.getPos() + ",R1";
				}
			}
			
		}
		Lexico.putSimbolo(tokenAux); 
		
		String label = "";
		if(this.marcaAntes) {
			label = "Label" + (this.getPos()-1) + "\n";
			this.marcaAntes = false;
		}
		
		return label + s1 + "\n" + s2 + "\n" + s3 + "\n";
	}
}
