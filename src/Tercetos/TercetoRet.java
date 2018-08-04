package Tercetos;

import compilador.GenCodigo;
import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoRet extends Terceto{

	private String nombreFuncion;
	private String retorno;
	
	public TercetoRet(String operador, ParserVal primero, ParserVal segundo, int pos, String nombreFuncion, GenCodigo generador) {
		super(operador, primero, segundo, pos, generador);
	
		this.primeroParserVal = primero;
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
				
		if(primeroParserVal.obj != null) { //tengo un terceto..
			String tipo = ((Terceto)primeroParserVal.obj).getTipoDato();
			String aux1 = String.valueOf(((Terceto)primeroParserVal.obj).getPos());
			if (tipo.equals("DOUBLE")) {
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) {
					retorno = "ERROR";
				}
				else {
					Token token = this.generador.getSintactico().getLexico().getTokenFromTS(this.nombreFuncion + "@Funcion" + this.ambitoReal);
					//retorno = "FLD var@@aux" + aux1 + "\n" + "FSTP " + this.nombreFuncion + "@Funcion" + this.ambitoReal + "\n";
					retorno = "FLD var@@aux" + aux1 + "\n" + "FSTP " + token.getLexema() + "\n";
				}
				t.setTipoDato("DOUBLE");
			}
			else {
				if (((Terceto)primeroParserVal.obj).getOperador().equals("FN")) { 
					//idem arriba,nunca se da porque no hay anidamiento de funciones
					retorno = "ERROR";
				}
				else {
					Token token = this.generador.getSintactico().getLexico().getTokenFromTS(this.nombreFuncion + "@Funcion" + this.ambitoReal);
					retorno = "MOV EAX,var@@aux" + aux1 + "\n" + "MOV " + token.getLexema() + ",EAX" + "\n";
				}
				t.setTipoDato("LONG");
			}
		}
		else {
			Token tokenFuncion = this.generador.getSintactico().getLexico().getTokenFromTS(this.nombreFuncion + "@Funcion" + this.ambitoReal);
			String aux1 = primeroParserVal.sval;
			if(Sintactico.esVariable(primeroParserVal)) { //es una variable
				Token token = this.generador.getSintactico().getLexico().getTokenFromTS(primeroParserVal.sval + "@Variable" + this.ambitoReal);
				String tipo = token.getTipoDato();
				if (tipo.equals("DOUBLE")) { //es una variable de tipo DOUBLE
					t.setTipoDato("DOUBLE");
					retorno = "FLD "+ aux1 + "@Variable" + token.getAmbito() + "\n" + "FSTP " + tokenFuncion.getLexema() + "\n";
				}
				else { //es una variable de tipo LONG
					t.setTipoDato("LONG");
					retorno = "MOV EAX,"+ aux1 + "@Variable" + token.getAmbito() + "\n" + "MOV " + tokenFuncion.getLexema() + ",EAX" + "\n";
				}
			}
			else {
				if (primeroParserVal.sval.contains(",")) { //es una constante de tipo DOUBLE 
					t.setTipoDato("DOUBLE");
					String aux = "const@@"+aux1.replace(',', '_') + " DQ " + aux1.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}					
					retorno = "FLD const@@"+aux1.replace(',', '_') + "\n" + "FSTP " + tokenFuncion.getLexema() +"\n";
				}
				else { //es una constante de tipo LONG
					t.setTipoDato("LONG");
					retorno = "MOV EAX," + aux1 + "\n" + "MOV " + tokenFuncion.getLexema() + ",EAX" +"\n";
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
