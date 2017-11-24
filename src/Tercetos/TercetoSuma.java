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
		Token tokenAux = new Token();

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos) {
		super("+", primero, segundo, pos);		
		this.primero = primero;
		this.segundo = segundo;
		
		if(Sintactico.getMarcaAntes()) {
			this.marcaAntes = true;
		}
	}
		
	public String getCodigo()
	{
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { //es un terceto que retorna DOUBLE
				s1="FLD var@@aux" + aux1;
				tokenAux.setLexema("var@@aux" + aux1);
				tokenAux.setTipoDato("DOUBLE");
				tokenAux.setType("Identificador");
			}
			else { //es un terceto que retorna LONG
				s1="MOV EAX,var@@aux" + aux1 + "\n";
				tokenAux.setLexema("var@@aux" + aux1);
				tokenAux.setTipoDato("LONG");
				tokenAux.setType("Identificador");
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { 
					//es una variable de tipo DOUBLE
					s1="FLD " + aux1 + "@Variable";
				}
				else { //es una variable de tipo LONG
					s1="MOV EAX," + aux1 + "@Variable";  
				}
			}
			else {
				if (aux1.toString().contains(",")) { 
					//es una constante de tipo DOUBLE
					s1="FLD " + aux1;
				}
				else { //es una constante de tipo LONG
					s1="MOV EAX," + aux1; 
				}
			}
		}
		
		if(segundo.obj != null) { 
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { //es un terceto de tipo DOUBLE
				s2="FLD var@@aux" + aux2;
				s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
				tokenAux.setLexema("var@@aux" + aux2);
				tokenAux.setTipoDato("DOUBLE");
				tokenAux.setType("Identificador");
			}
			else {// es un terceto de tipo LONG
				s2="ADD EAX,var@@aux" + aux2;
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				tokenAux.setLexema("var@@aux" + aux2);
				tokenAux.setTipoDato("LONG");
				tokenAux.setType("Identificador");
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					//es una variable de tipo DOUBLE
					s2="FLD " + aux1 + "@Variable";
					s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
				}
				else
				{ //es una variable de tipo LONG
					s2="ADD EAX," + aux2 + "@Variable";
					s3="JO _overflow\n" + "MOV var@@aux" + this.getPos() + ",EAX" + "\n";;
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					//es una constante DOUBLE
					s2= "FLD " + aux2;
					s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n"; 
				}
				else {
					s2="ADD EAX," + aux2;
					s3="JO _overflow\n" + "MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				}
			}
		}
		
		Lexico.putSimbolo(tokenAux); 
		
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(!this.generador.getSintactico().getNombreMarca().equals("")) {
				label = this.generador.getSintactico().getNombreMarca() + " proc\n";
				this.generador.getSintactico().setNombreMarca("");
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;
			}
		}
		return label + s1 + "\n" + s2 + "\n" + s3;
	}
}
