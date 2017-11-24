package Tercetos;

import compilador.Lexico;
import compilador.ParserVal;
import complementos.Token;

public class TercetoFuncion extends Terceto {
	
	private Token tokenAux = new Token();

	public TercetoFuncion(ParserVal primero, int pos) {
		super("FN", primero, null, pos);
		
		this.setTipoDato(Lexico.getTokenFromTS(primero.sval + "@Funcion").getTipoDato());
		
	}
		
	public String getCodigo()
	{
		String s=this.generador.getNombreFuncion(this.primero);
		System.out.println("Barbie");
		System.out.println(s);
		tokenAux.setLexema("var@@aux" + this.getPos());
		tokenAux.setTipoDato(this.getTipoDato());
		tokenAux.setType("Identificador");
		tokenAux.setDestino(s+"@Funcion");
		
		Lexico.putSimbolo(tokenAux);
		
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(!this.generador.getSintactico().getNombreMarca().equals("")) {
				label = this.generador.getSintactico().getNombreMarca() + " proc\n";
				this.generador.getSintactico().setNombreMarca("");
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;
			}
		}
		
		return label + "CALL " + s + "\n";//+ "RET Label"+ this.segundo.substring(1, segundo.length()-1) + "\n";
	}
}
