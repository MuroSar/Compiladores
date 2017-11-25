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
		tokenAux.setType("Identificador");
		tokenAux.setLexema("var@@aux" + this.getPos());
		
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { //es un terceto que retorna DOUBLE
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1 = "FLD " + nombre_func;
				}
				else {//no es funcion
					s1="FLD var@@aux" + aux1;/*
					tokenAux.setLexema("var@@aux" + this.getPos());
					tokenAux.setTipoDato("DOUBLE");
					tokenAux.setType("Identificador");*/
				}
			}
			else { //es un terceto que retorna LONG
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1 = "MOV EAX," + nombre_func; //+ "MOV var@@aux" + aux1+ ",EAX"+"\n";
				}
				else {
					s1="MOV EAX,var@@aux" + aux1;/*
					tokenAux.setLexema("var@@aux" + this.getPos());
					tokenAux.setTipoDato("LONG");
					tokenAux.setType("Identificador");*/
				}
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
					
					String aux = "const@@"+aux1.replace(',', '_') + " DT " + aux1 + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					
					s1="FLD const@@"+aux1.replace(',', '_');
					
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
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2 = "FLD " + nombre_func;
				}
				else {//no es funcion
					s2="FLD var@@aux" + aux2;
				}
				s3="FADD" + "\n" + "JO _overflow\n" + "FSTP var@@aux" + this.getPos() + "\n";
				tokenAux.setTipoDato("DOUBLE");
			}
			else {// es un terceto de tipo LONG
				tokenAux.setTipoDato("LONG");		
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "ADD EAX," + nombre_func;//+ "MOV " + aux1+ ",EAX"+"\n";
				}
				else {
					s2="ADD EAX,var@@aux" + aux2;
				}
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					//es una variable de tipo DOUBLE
					s2="FLD " + aux1 + "@Variable";
					s3="FADD" + "\n" + "JO _overflow\n" + "FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else
				{ //es una variable de tipo LONG
					s2="ADD EAX," + aux2 + "@Variable";
					s3="JO _overflow\n" + "MOV var@@aux" + this.getPos() + ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					//es una constante DOUBLE
					
					String aux = "const@@"+aux2.replace(',', '_') + " DT " + aux2 + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					
					s2= "FLD const@@"+aux2.replace(',', '_');
					s3="FADD" + "\n" + "JO _overflow\n" + "FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else {
					s2="ADD EAX," + aux2;
					s3="JO _overflow\n" + "MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
		}
		
		Lexico.putSimbolo(tokenAux); 
		
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(this.generador.getSintactico().existeNombreMarca()) {
				label = this.generador.getSintactico().getNombreMarca() + " proc\n";
				this.generador.getSintactico().removeNombreMarca();
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;
			}
		}
		return label + s1 + "\n" + s2 + "\n" + s3;
	}
}
