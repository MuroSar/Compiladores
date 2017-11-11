package compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tercetos.*;

public class GenCodigo {

	private ArrayList<Terceto> tercetos;
	private Sintactico sintactico;
	
	//agregar los dos archivos
	
	public GenCodigo() {
		/*
		tercetos.add(new TercetoSuma(new ParserVal(3),new ParserVal(4),1));
		tercetos.add(new TercetoResta(new ParserVal(5), new ParserVal(6), 2));*/
	}
	
	public void setListaTercetos(ArrayList<Terceto> ter) {
		tercetos = new ArrayList<Terceto>(ter);
	}
	
	public void setSintactico(Sintactico s){
		this.sintactico=s;
	}
	
	public void generarCodigo() {
		if (!this.sintactico.getLexico().getErrores().isEmpty() || this.sintactico.huboErrores())
		{
			this.sintactico.showError("No se puede generar el Assembler porque la gramática contiene errores");
		}
		else
		{
			//los LONG hay q declararlos de 32 bits: nomre DD?
			//los DOUBLE de 
			
			for (Terceto t:tercetos) {
				t.setGenerador(this);
				System.out.println(t.getCodigo());
			}
		}
	}
	
	public String getNombreFuncion(String n) {
		return this.sintactico.funcionPosGet(n);
	}
}
