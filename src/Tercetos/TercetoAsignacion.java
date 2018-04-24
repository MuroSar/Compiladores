package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoAsignacion extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s2;
	private ParserVal primero;
	private ParserVal segundo;
	private int pos;

	Token tokenAux = new Token();

	public TercetoAsignacion(ParserVal primero, ParserVal segundo, int pos) {
		super("=", primero, segundo, pos);
		this.primero = primero;
		this.segundo = segundo;
		this.pos = pos;
	}
		
	public String getCodigo()
	{
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
		}
		else {
				aux1 = primero.sval + "@Variable";
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			String tipo = ((Terceto)segundo.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) { 
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2= "FLD " + nombre_func + "\n" + "FST " + aux1;
				}
				else {//no es funcion
					s2 = "FLD var@@aux" + aux2 + "\n" + "FST " + aux1;
				}
			}
			else { //TIPO = A LONG
				if (((Terceto)segundo.obj).getOperador().equals("FN")) {
					String nombre_func = ((Terceto)segundo.obj).getPrimero() + "@Funcion";
					s2 = "MOV EAX,"+ nombre_func + "\nMOV " + aux1 + ",EAX" ;
				}
				else {//NO ES FUNCION
					s2 = "MOV EAX," + "var@@aux" + aux2 + "\n" + "MOV " + aux1 + ",EAX";  
				}
			}
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {//ES VARIABLE
				String tipo=this.generador.getSintactico().getLexico().getTokenFromTS(aux2+"@Variable").getTipoDato();
				if (tipo.equals("DOUBLE")) { //TIPO DOUBLE
					s2 = "FLD " + aux2 + "@Variable" + "\n" + "FST " + aux1;
				}
				else {//TIPO LONG
					s2 = "MOV EAX," + aux2 + "@Variable" + "\n" + "MOV " + aux1 + ",EAX";
				}
			}
			else {	//ES UNA CONSTANTE
				if (aux2.toString().contains(",")) {
					String aux = "const@@"+aux2.replace(',', '_').replace('-', '_') + " DQ " + aux2.replace(",", ".") + "\n";
					if(!this.generador.delcaracionesConstContains(aux)) {
						this.generador.setDeclaracionesConst(aux);	
					}//TIPO DOUBLE
					s2 = "FLD const@@"+aux2.replace(',', '_').replace('-', '_') + "\n" + "FST " + aux1;
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
