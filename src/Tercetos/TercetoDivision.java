package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoDivision extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s1;
	private String s2;
	private String s3;
	private ParserVal primero;
	private ParserVal segundo;
	private String op2;
	private Token tokenAux = new Token();
	private String chequeo_div_cero;
	String nombre_func;

	public TercetoDivision(ParserVal primero, ParserVal segundo, int pos) {
		super("/", primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
	}
		
	public String getCodigo()
	{

		tokenAux.setType("Identificador");
		tokenAux.setLexema("var@@aux" + this.getPos());
		
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					//ES UNA FUNCION DOUBLE
					nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1= "FLD " + nombre_func ;
				}
				else {
					//ES UN VALOR DOUBLE
					//revisar esto
					//this.generador.setDeclaracionesConst("var@@aux" + aux1 + " DD ?\n");
					s1= "FLD var@@aux" + aux1;
				}
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					//ES UNA FUNCION LONG
					nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1= "MOV EAX," + nombre_func + "\n" + "CDQ" ;
				}
				else {
					//ES UN VALOR LONG
					this.generador.setDeclaracionesConst("var@@aux" + aux1 + " DD ?\n");
					s1="MOV EAX,var@@aux"+aux1 + "\n" + "CDQ";
				}
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { 
					//ES UNA VARIABLE DOUBLE
					s1= "FLD " + aux1 + "@Variable";
				}
				else {
					//ES UNA VARIABLE LONG
					s1="MOV EAX,"+aux1+ "@Variable" + "\n" + "CDQ";
				}
			}
			else {
				if (aux1.toString().contains(",")) {
					String aux = "const@@"+aux1.replace(',', '_') + " DQ " + aux1.replace(',', '.') + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					//ES UN VALOR DOUBLE
					s1= "FLD const@@"+aux1.replace(',', '_');
				}
				else { 
					//ES UN VALOR LONG
					s1="MOV EAX,"+aux1 + "\n" + "CDQ";
				}
			}
		}
		
		
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					//ES UNA FUNCION DOUBLE
					nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "FLD " + nombre_func;
				}
				else {
					//ES UN VALOR DOUBLE
					//revisar
					//this.generador.setDeclaracionesConst("var@@aux" + aux2 + " DD ?\n");
					s2="FLD var@@aux" + aux2;
				}
				chequeo_div_cero = "FTST\n" + "FSTSW AX\n" + "SAHF\n" + "\n" + "JE _division_cero\n" + "FDIV";
				s3="FST var@@aux" + this.getPos() + "\n";
				tokenAux.setTipoDato("DOUBLE");
			}
			else {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) { 
					//ES UNA FUNCION LONG
					nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2 = ""; 
					op2=nombre_func;
				}
				else {
					//ES UN VALOR LONG
					s2="";
					this.generador.setDeclaracionesConst("var@@aux" + aux2 + " DD ?\n");
					op2="var@@aux"+aux2;
				}
				chequeo_div_cero ="CMP " + op2 + ",0" + "\n" + "JE _division_cero" + "\n" + "MOV EBX, " + op2 + "\n" + "MOV EBX," + nombre_func +  "\n" + "CDQ" + "\n"+ "IDIV EBX";
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				tokenAux.setTipoDato("LONG");
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					//ES UNA VARIABLE DOUBLE
					s2="FLD " + aux2 + "@Variable";
					chequeo_div_cero = "FTST\n" + "FSTSW AX\n" + "SAHF\n" + "JE _division_cero\n" + "FDIV";
					s3= "FST var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else {
					//ES UNA VARIABLE LONG
					s2= "";
					op2=aux2+ "@Variable";
					chequeo_div_cero = "CMP " + op2 + ",0" + "\n" + "JE _division_cero" + "\n" + "MOV EBX," + op2 + "\n" + "CDQ" + "\n" + "IDIV EBX";
					s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_') + " DQ " + aux2.replace(',', '.') + "\n";
					if(!this.generador.delcaracionesConstContains(aux)){
						this.generador.setDeclaracionesConst(aux);
					}
					//ES UN VALOR DOUBLE
					s2= "FLD const@@"+aux2.replace(',', '_'); 
					chequeo_div_cero ="FTST\n"+ "FSTSW AX\n" +"SAHF\n" +"JE _division_cero\n" + "FDIV";
					s3="FST var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else {
					//ES UN VALOR LONG
					s2= "";
					chequeo_div_cero ="CMP ECX" + ",0"+ "\n" + "JE _division_cero" + "\n" + "MOV ECX," + aux2 + "\n" + "MOV EBX,"+ aux2 + "\n" + "CDQ" + "\n" + "IDIV EBX";
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
		
		if (s2.equals("")) {
			return label + s1  + "\n" + chequeo_div_cero + "\n" + s3 + labelDesp;
		}
		
		return label + s1  + "\n" + s2 + "\n" + chequeo_div_cero + "\n" + s3 + labelDesp;
	}
}
