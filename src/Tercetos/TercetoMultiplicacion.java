package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoMultiplicacion extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s1;
	private String s2;
	private String s3;
	Token tokenAux = new Token();

	public TercetoMultiplicacion(ParserVal primero, ParserVal segundo, int pos) {
		super("*", primero, segundo, pos);
		this.primeroParserVal = primero;
		this.segundoParserVal = segundo;
	}
		
	public String getCodigo()
	{
		tokenAux.setType("Identificador");
		tokenAux.setLexema("var@@aux" + this.getPos());
		
		if(primeroParserVal.obj != null) {
			aux1 = String.valueOf(((Terceto)primeroParserVal.obj).getPos()); 
			String tipo = ((Terceto)primeroParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion";
					s1= "FLD " + nombre_func ;
				}
				else {//no es funcion
					s1= "FLD var@@aux" + aux1;
				}
			}
			else {
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion";
					s1= "MOV EAX," + nombre_func ;
				}
				else {
					s1="MOV EAX,var@@aux"+aux1;
				}
			}
		}
		else {
			aux1 = primeroParserVal.sval;
			if(Sintactico.esVariable(primeroParserVal)) { //es una variable
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					s1= "FLD " + aux1 + "@Variable";
				}
				else { //es una variable de tipo LONG
					s1="MOV EAX,"+aux1+ "@Variable"; 
				}
			}
			else {
				if (aux1.toString().contains(",")) { //es una constante de tipo DOUBLE
					String aux = "const@@"+aux1.replace(',', '_').replace('-', '_') + " DQ " + aux1.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}		
					s1= "FLD const@@"+aux1.replace(',', '_').replace('-', '_');
				}
				else { //es una constante de tipo LONG
					s1="MOV EAX,"+aux1;
				}
			}
		}
		
		if(segundoParserVal.obj != null) {
			aux2= String.valueOf(((Terceto)segundoParserVal.obj).getPos());
			String tipo = ((Terceto)segundoParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion";
					s2= "FLD " + nombre_func ;
				}
				else {//no es funcion
					s2="FLD var@@aux" + aux2 + "\n";
				}
				s3="FMUL" + "\n" + "FST var@@aux" + this.getPos() + "\n";
				tokenAux.setTipoDato("DOUBLE");
			}
			else {
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion";
					s2= "MOV EDX," + nombre_func + "\n" + "MUL EDX";
				}
				else {
					s2="MOV EDX,var@@aux" + aux2 + "\n" + "MUL EDX";
				}
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				tokenAux.setTipoDato("LONG");
			}
		}
		else {
			aux2 = segundoParserVal.sval;	
			if(Sintactico.esVariable(segundoParserVal)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					s2="FLD " + aux2 + "@Variable";
					s3="FMUL" + "\n" + "FST var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else
				{
					s2="MOV EDX,"+aux2+ "@Variable" + "\n" + "MUL EDX";
					s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_').replace('-', '_') + " DQ " + aux2.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}					
					s2= "FLD const@@"+aux2.replace(',', '_').replace('-', '_');
					s3="FMUL" + "\n" + "FST var@@aux" + this.getPos() + "\n"; 
					tokenAux.setTipoDato("DOUBLE");
				}
				else {
					s2= "MOV EDX," + aux2 + "\nMUL EDX";
					s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
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
	}