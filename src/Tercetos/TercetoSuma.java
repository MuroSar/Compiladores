package Tercetos;

import compilador.GenCodigo;
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
	private String overflowDoubleNeg;
	private String overflowDoublePos;
	Token tokenAux = new Token();

	public TercetoSuma(ParserVal primero, ParserVal segundo, int pos, GenCodigo generador) {
		super("+", primero, segundo, pos, generador);		
		this.primeroParserVal = primero;
		this.segundoParserVal = segundo;
		
		if(Sintactico.getMarcaAntes()) {
			this.marcaAntes = true;
		}
	}
		
	public String getCodigo()
	{
		tokenAux.setType("Identificador");
		tokenAux.setLexema("var@@aux" + this.getPos());
		
		if(primeroParserVal.obj != null) {
			aux1 = String.valueOf(((Terceto)primeroParserVal.obj).getPos()); 
			String tipo = ((Terceto)primeroParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { //es un terceto que retorna DOUBLE
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					this.generador.getSintactico().getLexico();
					Token token = Lexico.getTokenFromTS(((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					//String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
					String nombre_func = token.getLexema();
					s1 = "FLD " + nombre_func;
				}
				else {//no es funcion
					s1= "FLD var@@aux" + aux1;
				}
			}
			else { //es un terceto que retorna LONG
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					this.generador.getSintactico().getLexico();
					Token token = Lexico.getTokenFromTS(((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					//String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
					String nombre_func = token.getLexema();
					s1 = "MOV EAX," + nombre_func;
				}
				else {
					s1="MOV EAX,var@@aux" + aux1;
				}
			}
		}
		else {
			aux1 = primeroParserVal.sval;
			if(Sintactico.esVariable(primeroParserVal)) {
				Token token = Lexico.getTokenFromTS(aux1+"@Variable" + this.ambitoReal);
				String tipo = token.getTipoDato();
				if (tipo.equals("DOUBLE")) { 
					s1= "FLD " + aux1 + "@Variable" + token.getAmbito();
				}
				else { //es una variable de tipo LONG
					s1="MOV EAX," + aux1 + "@Variable" + token.getAmbito();  
				}
			}
			else {
				if (aux1.toString().contains(",")) { 
					//es una constante de tipo DOUBLE
					
					String aux = "const@@"+aux1.replace(',', '_').replace('-', '_') + " DQ " + aux1.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					s1= "FLD const@@"+aux1.replace(',', '_').replace('-', '_');
					
				}
				else { //es una constante de tipo LONG
					s1="MOV EAX," + aux1; 
				}
			}
		}

		if(segundoParserVal.obj != null) { 
			aux2= String.valueOf(((Terceto)segundoParserVal.obj).getPos());
			String tipo = ((Terceto)segundoParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { //es un terceto de tipo DOUBLE
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					Token token = Lexico.getTokenFromTS(((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					String nombre_func = token.getLexema();
					s2 = "FLD " + nombre_func;
				}
				else {//no es funcion
					s2="FLD var@@aux" + aux2;
				}
				s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
				s3 += checkOverflowDouble(this.getPos());
				
				tokenAux.setTipoDato("DOUBLE");
			}
			else {// es un terceto de tipo LONG
				tokenAux.setTipoDato("LONG");		
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					Token token = Lexico.getTokenFromTS(((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					String nombre_func = token.getLexema();
					s2= "ADD EAX," + nombre_func;				}
				else {
					s2="ADD EAX,var@@aux" + aux2;
				}
				s3="JO _overflow\n" + "MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
			}
		}
		else {
			aux2 = segundoParserVal.sval;	
			if(Sintactico.esVariable(segundoParserVal)) {
				Token token = Lexico.getTokenFromTS(aux2 + "@Variable" + this.ambitoReal);
				String tipo = token.getTipoDato();
				if (tipo.equals("DOUBLE")) {
					//es una variable de tipo DOUBLE
					s2="FLD " + aux2 + "@Variable" + token.getAmbito();
					s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
					s3 += checkOverflowDouble(this.getPos());
					
					tokenAux.setTipoDato("DOUBLE");
				}
				else
				{ //es una variable de tipo LONG
					s2="ADD EAX," + aux2 + "@Variable" + token.getAmbito();
					s3="JO _overflow\n" + "MOV var@@aux" + this.getPos() + ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					//es una constante DOUBLE
					
					String aux = "const@@"+aux2.replace(',', '_').replace('-', '_') + " DQ " + aux2.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					
					s2= "FLD const@@"+aux2.replace(',', '_').replace('-', '_');
					s3="FADD" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
					s3 += checkOverflowDouble(this.getPos());
					
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
		String labelDesp = "";
		if(this.marcaDesp) {
			labelDesp = "Label" + (this.getPos()+1) + ":\n";
			this.marcaDesp = false;
		}
		
		if(!label.equals("") && this.generador.getUltimaLinea().equals(label.substring(0, label.length()-1))) {
			label = "";
		}
		
		return label + s1 + "\n" + s2 + "\n" + s3 + labelDesp;
	}
		
	public String checkOverflowDouble(int pos) {
		String result;
		
		result = "FLD var@@aux" + pos + "\n";
		result += "FCOM __CERO\n";
		result += "FSTSW AX\n";
		result += "SAHF\n";
		result += "JE LabelCero@@" + pos + "\n";
		result += "FLD var@@aux" + pos + "\n";
		result += "FABS\n";
		result += "FCOM __MIN_DOUBLE\n";
		result += "FSTSW AX\n";
		result += "SAHF\n";
		result += "JBE _overflow\n";
		result += "FLD var@@aux" + pos + "\n";
		result += "FABS\n";
		result += "FCOM __MAX_DOUBLE\n";
		result += "FSTSW AX\n";
		result += "SAHF\n";
		result += "JAE _overflow\n";
		result += "LabelCero@@"  + pos +  ":\n";
		
		return result;
	}
}
