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
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					return "FLD " + nombre_func ;//+ "\n" + "FSTP " + aux1 +"\n";
				}
				else {//no es funcion
					s1="FLD var@@aux" + aux1;
				}
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					return "MOV EAX," + nombre_func ;//+ "\n" + "MOV " + aux1+ ",EAX"+"\n";
				}
				else {
					s1="MOV EAX,var@@aux"+aux1;
				}
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					s1="FLD " + aux1 + "@Variable";
				}
				else {
					s1="MOV EAX,"+aux1+ "@Variable";
				}
			}
			else {
				if (aux1.toString().contains(",")) {
					String aux = "const@@"+aux1.replace(',', '_') + " DT " + aux1 + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					s1="FLD const@@"+aux1.replace(',', '_');
				}
				else { //es una constante de tipo LONG
					s1="MOV EAX,"+aux1;
				}
			}
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "FLD " + nombre_func + "\n" + "FDIV";//+ "\n" + "FSTP " + aux1 +"\n";
				}
				else {//no es funcion
					s2="FLD var@@aux" + aux2 + "\n" + "FDIV";
				}
				chequeo_div_cero = "FTST" + "\n" + "JE _division_cero" + "\n";
				s3="FSTP var@@aux" + this.getPos() + "\n";
				tokenAux.setTipoDato("DOUBLE");
			}
			else {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "DIV EAX," + nombre_func ;//+ "\n" + "MOV " + aux1+ ",EAX"+"\n";
					op2=nombre_func;
				}
				else {
					s2="DIV EAX,var@@aux"+aux2;
					op2="var@@aux"+aux2;
				}
				chequeo_div_cero = "MOV EDX," + op2 + "CMP 0,EDX" + "\n" + "JE _division_cero" + "\n";
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				tokenAux.setTipoDato("LONG");
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					s2="FLD " + aux1 + "@Variable" + "\n" + "FDIV" ;
					chequeo_div_cero = "FTST" + "\n" + "JE _division_cero";
					s3= "FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else
				{
					s2="DIV EAX,"+aux2+ "@Variable";
					op2="_"+aux2+ "@Variable";
					chequeo_div_cero = "MOV EDX," + op2 + "\nCMP EDX,0" + "\n" + "JE _division_cero";
					s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_') + " DT " + aux2 + "\n";
					this.generador.setDeclaracionesOut(aux);
					s2= "FLD const@@"+aux2.replace(',', '_');
					chequeo_div_cero ="FTST" + "\n" + "JE _division_cero";
					s3="FDIV" + "\n" + "FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
				}
				else 
				{
					s2="DIV EAX,"+aux2;	
					op2=aux2;
					chequeo_div_cero = "MOV EDX," + op2 + "\nCMP EDX,0" + "\n" + "JE _division_cero";
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
		return label + s1 + "\n" + s2 + "\n" + chequeo_div_cero + "\n" + s3;
	}
}
