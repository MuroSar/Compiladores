package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoRet extends Terceto{

	private ParserVal primero;
	private String nombreFuncion;
	
	public TercetoRet(String operador, ParserVal primero, ParserVal segundo, int pos, String nombreFuncion) {
		super(operador, primero, segundo, pos);
	
		this.primero = primero;
		this.nombreFuncion = nombreFuncion;
	}
		
	public String getCodigo()
	{
		Token t = new Token();
		t.setLexema("#" + nombreFuncion + "Ret");
		String destino = "";
				
		if(primero.obj != null) { //tengo un terceto..
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				t.setTipoDato("DOUBLE");
				t.setType("Identificador");
			}
			else {
				t.setTipoDato("LONG");
				t.setType("Identificador");
			}
		}
		else {
			if(Sintactico.esVariable(primero)) { //es una variable
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(primero.sval+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					t.setTipoDato("DOUBLE");
					t.setType("Identificador");
					t.setDestino(this.generador.getSintactico().getLexico().getTokenFromTS(primero.sval+"@Variable").getDestino());
				}
				else { //es una variable de tipo LONG
					t.setTipoDato("LONG");
					t.setType("Identificador");
					t.setDestino(this.generador.getSintactico().getLexico().getTokenFromTS(primero.sval+"@Variable").getDestino());
				}
			}
			else {
				if (primero.sval.contains(",")) { //es una constante de tipo DOUBLE
					t.setTipoDato("DOUBLE");
					t.setType("Identificador");
					t.setDestino(primero.sval);
				}
				else { //es una constante de tipo LONG
					t.setTipoDato("LONG");
					t.setType("Identificador");
					t.setDestino(primero.sval); 
				}
			}
		}

		Lexico.putSimbolo(t);
		
		String label = "";
		if(this.marcaDesp) {
			label = "Label" + (this.getPos()+1) + "\n";
			this.marcaDesp = false;
		}
		
		return "RET\n" + label;
	}

}
