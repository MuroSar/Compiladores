package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoRet extends Terceto{

	private ParserVal primero;
	private String nombreFuncion;
	private String retorno;
	
	public TercetoRet(String operador, ParserVal primero, ParserVal segundo, int pos, String nombreFuncion) {
		super(operador, primero, segundo, pos);
	
		this.primero = primero;
		this.nombreFuncion = nombreFuncion;
	}
		
	public String getCodigo()
	{
		Token t = new Token();
		t.setLexema("var@@" + nombreFuncion + "Ret");
		String destino = "";
		retorno = "";
		t.setType("Identificador");
		t.setLexema("var@@aux" + this.getPos());
				
		if(primero.obj != null) { //tengo un terceto..
			String tipo = ((Terceto)primero.obj).getTipoDato();
			String aux1 = String.valueOf(((Terceto)primero.obj).getPos());
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primero.obj).getOperador().equals("FN")) {
					retorno = "ERROR";
				}
				else {//no es funcion
					retorno = "FLD var@@aux" + aux1 + "\n" + "FSTP " + this.nombreFuncion + "@Funcion" + "\n";
				}
				t.setTipoDato("DOUBLE");
			}
			else {
				if (((Terceto)primero.obj).getOperador().equals("FN")) { 
					//idem arriba,nunca se da porque no hay anidamiento de funciones
					retorno = "ERROR";
				}
				else {
					retorno = "MOV EAX,var@@aux" + aux1 + "\n" + "MOV " + this.nombreFuncion + "@Funcion,EAX" + "\n";
				}
				t.setTipoDato("LONG");
			}
		}
		else {
			String aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) { //es una variable
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(primero.sval+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					t.setTipoDato("DOUBLE");
					retorno = "FLD "+ aux1 + "@Variable" + "\n" + "FSTP " + this.nombreFuncion + "@Funcion" + "\n";
				}
				else { //es una variable de tipo LONG
					t.setTipoDato("LONG");
					retorno = "MOV EAX,"+ aux1 + "@Variable" + "\n" + "MOV " + this.nombreFuncion + "@Funcion,EAX" + "\n";
					//t.setDestino(this.generador.getSintactico().getLexico().getTokenFromTS(primero.sval+"@Variable").getDestino());
				}
			}
			else {
				if (primero.sval.contains(",")) { //es una constante de tipo DOUBLE 
					t.setTipoDato("DOUBLE");
					String aux = "const@@"+aux1.replace(',', '_') + " DQ " + aux1.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}					
					retorno = "FLD const@@"+aux1.replace(',', '_') + "\n" + "FSTP " + this.nombreFuncion  + "@Funcion" +"\n";
				}
				else { //es una constante de tipo LONG
					t.setTipoDato("LONG");
					retorno = "MOV EAX," + aux1 + "\n" + "MOV " + this.nombreFuncion  + "@Funcion,EAX" +"\n";
				}
			}
		}

		Lexico.putSimbolo(t);
		
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
		
		return label + retorno + "RET\n" + nombreFuncion + " endp\n" + labelDesp;
	}

}
