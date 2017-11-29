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
					s1= "FLD " + nombre_func ;
				}
				else {//no es funcion
					s1="FLD var@@aux" + aux1;
				}
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1= "MOV EAX," + nombre_func + "\n" + "CDQ" ;
				}
				else {
					s1="MOV EAX,var@@aux"+aux1 + "\n" + "CDQ";
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
					s1="MOV EAX,"+aux1+ "@Variable" + "\n" + "CDQ";
				}
			}
			else {
				if (aux1.toString().contains(",")) {
					String aux = "const@@"+aux1.replace(',', '_') + " DT " + aux1.replace(',', '.') + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					s1="FLD const@@"+aux1.replace(',', '_');
				}
				else { //es una constante de tipo LONG
					s1="MOV EAX,"+aux1 + "\n" + "CDQ";
					String dec = "const@@"+aux1 + " DD " + aux1 + "\n";
					if(!this.generador.delcaracionesConstContains(dec)){
						this.generador.setDeclaracionesConst(dec);
					}
				}
			}
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "FLD " + nombre_func;
				}
				else {//no es funcion
					s2="FLD var@@aux" + aux2;
				}
				chequeo_div_cero = "FTST\n" + "FSTSW aux_mem_2bytes\n" + "MOV AX,aux_mem_2bytes\n" + "SAHF\n" + "\n" + "JE _division_cero\n" + "FDIV";
				s3="FSTP var@@aux" + this.getPos() + "\n";
				tokenAux.setTipoDato("DOUBLE");
				String aux = "aux_mem_2bytes DW ?\n";
				if(!this.generador.delcaracionesConstContains(aux)){
					this.generador.setDeclaracionesConst(aux);
				}
			}
			else {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) { //son funciones de tipo LONG
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2 = "MOV EBX," + nombre_func + "\n" + "DIV EBX"; 
					op2=nombre_func;
				}
				else {
					s2="MOV EBX, var@@aux" + aux2 + "\n" + "DIV EBX";
					op2="var@@aux"+aux2;
				}
				chequeo_div_cero ="CMP " + op2 + ",0" + "\n" + "JE _division_cero";
				s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
				tokenAux.setTipoDato("LONG");
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					s2="FLD " + aux2 + "@Variable";
					chequeo_div_cero = "FTST\n" + "FSTSW aux_mem_2bytes\n"+ "MOV AX,aux_mem_2bytes\n" + "SAHF\n" + "JE _division_cero\n" + "FDIV";
					s3= "FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
					String aux = "aux_mem_2bytes DW ?\n";
					if(!this.generador.delcaracionesConstContains(aux)){
						this.generador.setDeclaracionesConst(aux);
					}
				}
				else
				{
					s2= "MOV EBX," + aux2 + "@Variable"  + "\n" + "DIV EBX";
					op2=aux2+ "@Variable";
					chequeo_div_cero = "CMP " + op2 + ",0" + "\n" + "JE _division_cero";
					s3="MOV var@@aux"+ this.getPos()+ ",EAX" + "\n";
					tokenAux.setTipoDato("LONG");
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_') + " DT " + aux2.replace(',', '.') + "\n";
					if(!this.generador.delcaracionesConstContains(aux)){
						this.generador.setDeclaracionesConst(aux);
					}
					s2= "FLD const@@"+aux2.replace(',', '_'); 
					chequeo_div_cero ="FTST\n"+ "FSTSW aux_mem_2bytes\n" + "MOV AX, aux_mem_2bytes\n" +"SAHF\n" +"JE _division_cero\n" + "FDIV";
					s3="FSTP var@@aux" + this.getPos() + "\n";
					tokenAux.setTipoDato("DOUBLE");
					String auxiliar = "aux_mem_2bytes DW ?\n";
					if(!this.generador.delcaracionesConstContains(auxiliar)){
						this.generador.setDeclaracionesConst(auxiliar);
					}
				}
				else 
				{
					s2= "MOV EBX,const@@"+ aux2 + "\n" + "DIV EBX";
					String dec = "const@@"+aux2 + " DD " + aux2 + "\n";
					if(!this.generador.delcaracionesConstContains(dec)){
						this.generador.setDeclaracionesConst(dec);
					}
					chequeo_div_cero ="CMP const@@"+aux2 + ",0"+ "\n" + "JE _division_cero";
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
		
		return label + s1  + "\n" + s2 + "\n" + chequeo_div_cero + "\n" + s3 + labelDesp;
	}
}
