package Tercetos;

import compilador.GenCodigo;
import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoAsignacion extends Terceto{
	
	private String aux1;
	private String aux2; 
	private String s2;
	private int pos;

	Token tokenAux = new Token();

	public TercetoAsignacion(ParserVal primero, ParserVal segundo, int pos, GenCodigo generador) {
		super("=", primero, segundo, pos, generador);
		this.primeroParserVal = primero;
		this.segundoParserVal = segundo;
		this.pos = pos;
	}
		
	public String getCodigo()
	{
		if(primeroParserVal.obj != null) {
			aux1 = String.valueOf(((Terceto)primeroParserVal.obj).getPos()); 
		}
		else {
			Token token = Lexico.getTokenFromTS(primeroParserVal.sval + "@Variable" + this.ambitoReal);
			aux1 = token.getLexema();
		}
		
		if(segundoParserVal.obj != null) {
			aux2= String.valueOf(((Terceto)segundoParserVal.obj).getPos());
			String tipo = ((Terceto)segundoParserVal.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { 
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					Token token = Lexico.getTokenFromTS(((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					String nombre_func = token.getLexema();
					s2= "FLD " + nombre_func + "\n" + "FSTP " + aux1;
				}
				else {//no es funcion
					s2 = "FLD var@@aux" + aux2 + "\n" + "FSTP " + aux1;
				}
			}
			else { //TIPO = A LONG
				if (((Terceto)segundoParserVal.obj).getOperador().equals("FN")) {
					Token token = Lexico.getTokenFromTS(((Terceto)segundoParserVal.obj).getPrimero() + "@Funcion" + this.ambitoReal);
					String nombre_func = token.getLexema();
					s2 = "MOV EAX,"+ nombre_func + "\nMOV " + aux1 + ",EAX" ;
				}
				else {//NO ES FUNCION
					s2 = "MOV EAX," + "var@@aux" + aux2 + "\n" + "MOV " + aux1 + ",EAX";  
				}
			}
		}
		else {
			aux2 = segundoParserVal.sval;	
			if(Sintactico.esVariable(segundoParserVal)) {//ES VARIABLE
				String tipo = Lexico.getTokenFromTS(aux2 + "@Variable" + this.ambitoReal).getTipoDato();
				if (tipo.equals("DOUBLE")) { //TIPO DOUBLE
					s2 = "FLD " + aux2 + "@Variable" + this.ambitoReal + "\n" + "FSTP " + aux1;
				}
				else {//TIPO LONG
					s2 = "MOV EAX," + aux2 + "@Variable" + this.ambitoReal + "\n" + "MOV " + aux1 + ",EAX";
				}
			}
			else {	//ES UNA CONSTANTE
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_').replace('-', '_') + " DQ " + aux2.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}//TIPO DOUBLE
					s2 = "FLD const@@"+aux2.replace(',', '_').replace('-', '_') + "\n" + "FSTP " + aux1;
				}
				else { //TIPO LONG
					s2="MOV " + aux1 + "," + aux2; 
				}
			}
		}
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
		
		return label + s2 + "\n" + labelDesp;
	}
}
