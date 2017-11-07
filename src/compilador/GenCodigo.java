package compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tercetos.*;

public class GenCodigo {

	private ArrayList<Terceto> tercetos;
	
	public GenCodigo() {
		/*
		tercetos.add(new TercetoSuma(new ParserVal(3),new ParserVal(4),1));
		tercetos.add(new TercetoResta(new ParserVal(5), new ParserVal(6), 2));*/
	}
	
	public void setListaTercetos(ArrayList<Terceto> ter) {
		tercetos = new ArrayList<Terceto>(ter);
	}
	
	public void generarCodigo() {
		int contador=1;
		for (Terceto t:tercetos) {
			System.out.println(t.getCodigo()+"numero de variable: "+contador);
			contador++;
		}
	}
	
}
