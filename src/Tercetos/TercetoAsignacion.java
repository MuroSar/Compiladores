package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public class TercetoAsignacion extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s1;
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
	{/*
		if(primero.obj != null) {
			aux1 = String.valueOf(((Terceto)primero.obj).getPos()); 
			String tipo = ((Terceto)primero.obj).getTipoDato();
			if (tipo.equals("DOUBLE")) {
				s1="MOV var@@aux"+aux1+",EAX";
			}
			else {
				
			}
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				s1="MOV "+aux1+"@Variable,EAX";
			}
			else {
				s1="MOV "+aux1+",EAX";
				if (aux1.toString().contains(",")) {
					//aca que pasa???
					//estaba vacio
				}
			}
		}*/
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			s2="MOV EAX,var@@aux"+aux2;
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				s2="MOV EAX,"+aux2+"@Variable";
			}
			else {
				s2="MOV EAX,"+aux2;	
				if (aux2.toString().contains(",")) {
					//aca que pasa???
					//estaba vacio
				}
			}
			
		}
	
		Lexico.putSimboloAsm("var@@aux"+pos);
		
		Lexico.actualizarDestino(aux1, "var@@aux"+pos);
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(!this.generador.getSintactico().getNombreMarca().equals("")) {
				label = this.generador.getSintactico().getNombreMarca() + ":\n";
				this.generador.getSintactico().setNombreMarca("");
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;
			}
		}
		return label + s2 + "\n" + s1 + "\n";
	}
}
