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
	private String CodAux;

	public TercetoComparador(ParserVal operador, ParserVal primero, ParserVal segundo, int pos) {
		super(operador.sval, primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
		this.CodAux = "";
	}
		
	public String getCodigo()
	{
		this.generador.setComparador(this.operador);
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				salidaDouble="FLD #aux" + aux1; //es un terceto y el resultado es un DOUBLE
			}
			else {
				s1="#aux"+aux1;
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux1+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					salidaDouble="FLD " + aux1 + "@Variable";
				}
				else { //es una variable de tipo LONG
					s1="_"+aux1; //es una variable
				}
			}
			else {
				if (aux1.toString().contains(",")) { //es una constante de tipo DOUBLE
					salidaDouble="FLD " + aux1 + "\n"; 
				}
				else { //es una constante de tipo LONG
					CodAux="MOV R1," + aux1 + "\n";
					s1="R1"; //es un numero
				}
			}
		}
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				salidaDouble +="FCOM #aux" + aux2 + "\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n"; //porque tengo dos DOUBLE
				return salidaDouble;
			}
			else {
				s2="#aux"+aux2;
				return CodAux + "CMP " + s1 + "," + s2;
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) {
					salidaDouble +="FCOM " + aux1 + "@Variable" + "\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n";
					return salidaDouble;
				}
				else
				{
					s2="_"+aux2; 
					return CodAux + "CMP " + s1 + "," + s2 + "\n";
				}
			}
			else {
				if (aux2.toString().contains(",")) {
					salidaDouble += "FCOM " + aux2 + "\n" + "FSTSW aux_mem_2bytes" + "\n" + "MOV AX, aux_mem_2bytes" + "\n" + "SAHF" + "\n";
					return salidaDouble;
				}
				else {
					CodAux +="MOV R2," + aux2 + "\n";
					s2="R2"; 
					return CodAux + "CMP " + s1 + "," + s2;
				}
			}
		}
	}
}
