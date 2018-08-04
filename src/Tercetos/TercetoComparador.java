package Tercetos;

import compilador.GenCodigo;
import compilador.ParserVal;
import compilador.Sintactico;

public class TercetoComparador extends Terceto{
	
	private String aux1;
	private String aux2;
	private String salidaDouble;
	private String s1;
	private String s2;
	private String s3;
	private String CodAux;

	public TercetoComparador(ParserVal operador, ParserVal primero, ParserVal segundo, int pos, GenCodigo generador) {
		super(operador.sval, primero, segundo, pos, generador);
		this.primeroParserVal = primero;
		this.segundoParserVal = segundo;
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
		
		if(primeroParserVal.obj != null) {
			aux1 = String.valueOf(((Terceto)primeroParserVal.obj).getPos()); 
			String tipo = ((Terceto)primeroParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
					salidaDouble = "FLD " + nombre_func;
				}
				else {//no es funcion
					salidaDouble = "FLD var@@aux" + aux1 + "\n"; //es un terceto y el resultado es un DOUBLE
				}
			}
			else {
				//tipo igual a LONG
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)primeroParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
					s1 = "MOV EAX," + nombre_func;
				}
				else {
				s1 = "var@@aux"+aux1;
				CodAux = "MOV EAX,"  + s1 + "\n";
				}
			}
		}
		else {
			aux1 = primeroParserVal.sval;
			if(Sintactico.esVariable(primeroParserVal)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1 + "@Variable" + this.ambitoReal).getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					salidaDouble="FLD " + aux1 + "@Variable" + this.ambitoReal + "\n";
				}
				else { //es una variable de tipo LONG
					s1=aux1+ "@Variable" + this.ambitoReal; //es una variable
					CodAux="MOV EAX," + s1 + "\n";
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
		if(segundoParserVal.obj != null) {
			aux2= String.valueOf(((Terceto)segundoParserVal.obj).getPos());
			String tipo = ((Terceto)segundoParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					//es una funcion de tipo DOUBLE
					String nombre_func = ((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
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
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					//es una funcion LONG
					String nombre_func = ((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal;
					return label + CodAux + "CMP EAX," + nombre_func + "\n" + labelDesp;
				}
				else {
					s2="var@@aux"+aux2;
					CodAux += "MOV EAX," + s2 + "\n";
					return label + CodAux + "CMP " + s1 + ",EAX" + "\n" + labelDesp;
				}
			}
		}
		else {
			aux2 = segundoParserVal.sval;	
			if(Sintactico.esVariable(segundoParserVal)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2 + "@Variable" + this.ambitoReal).getTipoDato();
				if (tipo.equals("DOUBLE")) {
					salidaDouble +="FLD "+ aux2 + "@Variable"+ this.ambitoReal + "\nFCOM\n" + "FSTSW AX\n" + "SAHF" + "\n";
					if(!this.generador.delcaracionesConstContains("aux_mem_2bytes DW ?\n")) {
						this.generador.setDeclaracionesConst("aux_mem_2bytes DW ?\n");	
					}
					return label + salidaDouble + labelDesp;
				}
				else
				{
					s2=aux2 + "@Variable" + this.ambitoReal; 
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
