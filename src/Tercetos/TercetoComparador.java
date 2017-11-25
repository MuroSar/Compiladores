package Tercetos;

import compilador.ParserVal;
import compilador.Sintactico;

public class TercetoComparador extends Terceto{
	
	private ParserVal primero;
	private ParserVal segundo;
	private String aux1;
	private String aux2;
	private String salidaDouble;
	private String s1;
	private String s2;
	private String s3;
	private String CodAux;

	public TercetoComparador(ParserVal operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador.sval, primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
		this.CodAux = "";
	}
		
	public String getCodigo()
	{
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(!this.generador.getSintactico().getNombreMarca().equals("")) {
				label = this.generador.getSintactico().getNombreMarca() + " proc\n";
				this.generador.getSintactico().removeNombreMarca();
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;	
			}
		}
		
		String labelFinal = "";
//		if(this.generador.getLabels().contains(this.getPos())) {
//			labelFinal = "Label" + (this.getPos()) + ":\n";
//		}
	
		this.generador.setComparador(this.operador);
		
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					salidaDouble = "FLD " + nombre_func;//+ "\n" + "FSTP " + aux1 +"\n";
				}
				else {//no es funcion
					salidaDouble = "FLD var@@aux" + aux1; //es un terceto y el resultado es un DOUBLE
				}
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1 = "MOV EAX," + nombre_func;// + "\n" + "MOV " + aux1+ ",EAX"+"\n";
				}
				else {
				s1 = "var@@aux"+aux1;
				}
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					salidaDouble="FLD " + aux1 + "@Variable\n";
				}
				else { //es una variable de tipo LONG
					s1=aux1+ "@Variable"; //es una variable
				}
			}
			else {
				if (aux1.toString().contains(",")) { //es una constante de tipo DOUBLE
					String aux = "const@@"+aux1.replace(',', '_') + " DT " + aux1 + "\n";
					this.generador.setDeclaracionesOut(aux);
					salidaDouble="FLD const@@"+aux1.replace(',', '_') + "\n"; 
				}
				else { //es una constante de tipo LONG
					CodAux="MOV EAX," + aux1 + "\n";
					s1="EAX"; //es un numero
				}
			}
		}
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");
					salidaDouble = "FLD " + nombre_func +"\nFCOM\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n";
					return label + salidaDouble + labelFinal;
					// + "\n" + "FSTP " + aux1 +"\n";
				}
				else {//no es funcion
					salidaDouble +="FLD var@@aux" + aux2 +"\nFCOM\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n"; //porque tengo dos DOUBLE
					this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");
					return label + salidaDouble + labelFinal;
				}
			}
			else {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					return label + s1 + "CMP EAX," + nombre_func + labelFinal;
					// + "\n" + "MOV " + aux1+ ",EAX"+"\n";
				}
				else {
					s2="var@@aux"+aux2;
					return label + CodAux + "CMP " + s1 + "," + s2 + "\n" + labelFinal;
				}
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					salidaDouble +="FLD "+ aux1 + "@Variable\nFCOM\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n";
					this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");
					return label + salidaDouble + labelFinal;
				}
				else
				{
					s2=aux2+ "@Variable"; 
					s3 = "MOV EAX," + s2 + "\n";
					return label + CodAux + s3 + "CMP " + s1 + ",EAX" + "\n" + labelFinal;
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_') + " DT " + aux2 + "\n";
					this.generador.setDeclaracionesOut(aux);
					
					salidaDouble += "FLD const@@"+aux2.replace(',', '_') + "\nFCOM\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n";
					this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");
					return label + salidaDouble + labelFinal;
				}
				else {
					CodAux +="MOV EDX," + aux2 + "\n";
					s2="EDX"; 
					return label + CodAux + "CMP " + s1 + "," + s2 + "\n" + labelFinal;
				}
			}
		}
	}
}
