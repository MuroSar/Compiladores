package Tercetos;

import compilador.GenCodigo;
import compilador.Lexico;
import compilador.ParserVal;
import complementos.Token;

public class TercetoFuncion extends Terceto {
	
	private Token tokenAux = new Token();

	public TercetoFuncion(ParserVal primero, int pos, GenCodigo generador) {
		super("FN", primero, null, pos);
		
		String ambitoReal = generador.getSintactico().getNameManglingForAmbito(generador.getSintactico().getAmbito());
		
		this.setTipoDato(Lexico.getTokenFromTS(primero.sval + "@Funcion" + ambitoReal).getTipoDato());
		
	}
		
	public String getCodigo()
	{
		String s=this.generador.getNombreFuncion(this.primero);
		tokenAux.setLexema(s + "@Funcion" + this.ambitoReal);
		tokenAux.setTipoDato(this.getTipoDato());
		tokenAux.setType("Identificador");
		
		Lexico.putSimbolo(tokenAux);
		
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
		
		return label + "CALL " + s + "\n" + labelDesp;
	}
}
