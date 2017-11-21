package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;

public class TercetoAsignacion extends Terceto{
	
	private String aux1;
	private String aux2;
	private String s1;
	private String s2;
	private ParserVal primero;
	private ParserVal segundo;
	private int pos;

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
			s1="MOV #aux"+aux1+",R1";
		}
		else {
			aux1 = primero.sval;
			if(Sintactico.esVariable(primero)) {
				s1="MOV _"+aux1+",R1";
			}
			else {
				s1="MOV "+aux1+",R1";
				if (aux1.toString().contains(",")) {
				}
			}
		}
		
		if(segundo.obj != null) {
			aux2= String.valueOf(((Terceto)segundo.obj).getPos());
			s2="MOV R1,#aux"+aux2;
		}
		else {
			aux2 = segundo.sval;	
			if(Sintactico.esVariable(segundo)) {
				s2="MOV R1,_"+aux2;
			}
			else {
				s2="MOV R1,"+aux2;	
				if (aux2.toString().contains(",")) {
				}
			}
			
		}
	
		Lexico.putSimboloAsm("#aux"+pos);
		
		Lexico.actualizarDestino(aux1, "#aux"+pos);
		
		String label = "";
		if(this.marcaAntes) {
			label = "Label" + (this.getPos()-1) + "\n";
			this.marcaAntes = false;
		}
		
		return label + s2 + "\n" + s1 + "\n";
	}
}
