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
	
		this.generador.setComparador(this.operador);
		
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					salidaDouble = "FLD " + nombre_func;
				}
				else {//no es funcion
					salidaDouble = "FLD var@@aux" + aux1 + "\n"; //es un terceto y el resultado es un DOUBLE
				}
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primero.obj).getPrimero() + "@Funcion";
					s1 = "MOV EAX," + nombre_func;
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
					String aux = "const@@"+aux1.replace(',', '_').replace('-', '_') + " DQ " + aux1.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}
					salidaDouble="FLD const@@"+aux1.replace(',', '_').replace('-', '_') + "\n"; 
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
					if(!this.generador.delcaracionesConstContains("aux_mem_2bytes DW ?\n")) {
						this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");	
					}
					salidaDouble = "FLD " + nombre_func +"\nFCOM\n" + "FSTSW AX\n" + "SAHF" + "\n";
					return label + salidaDouble + labelDesp;
				}
				else {//no es funcion
					salidaDouble +="FLD var@@aux" + aux2 +"\nFCOM\n" + "FSTSW AX\n" + "SAHF" + "\n";
					if(!this.generador.delcaracionesConstContains("aux_mem_2bytes DW ?\n")) {
						this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");	
					}
					return label + salidaDouble + labelDesp;
				}
			}
			else {
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					return label + s1 + "CMP EAX," + nombre_func + labelDesp;
				}
				else {
					s2="var@@aux"+aux2;
					CodAux = "MOV EAX," + s2;
					CodAux = "MOV EAX," + s2 + "\n";
					return label + CodAux + "CMP " + s1 + ",EAX" + "\n" + labelDesp;
				}
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					salidaDouble +="FLD "+ aux2 + "@Variable\nFCOM\n" + "FSTSW AX\n" + "SAHF" + "\n";
					if(!this.generador.delcaracionesConstContains("aux_mem_2bytes DW ?\n")) {
						this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");	
					}
					return label + salidaDouble + labelDesp;
				}
				else
				{
					s2=aux2+ "@Variable"; 
					s3 = "MOV EAX," + s2 + "\n";
					return label + CodAux + s3 + "CMP " + s1 + ",EAX" + "\n" + labelDesp;
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_').replace('-', '_') + " DQ " + aux2.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);
					}
					salidaDouble += "FLD const@@"+aux2.replace(',', '_').replace('-', '_') + "\nFCOM\n" + "FSTSW AX\n" + "SAHF" + "\n";
					if(!this.generador.delcaracionesConstContains("aux_mem_2bytes DW ?\n")) {
						this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");	
					}
					return label + salidaDouble + labelDesp;
				}
				else {
					CodAux +="MOV EDX," + aux2 + "\n";
					s2="EDX"; 
					return label + CodAux + "CMP " + s1 + "," + s2 + "\n" + labelDesp;
				}
			}
		}
	}
}
