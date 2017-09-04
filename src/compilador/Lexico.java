package compilador;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import interfaz.Principal;

public class Lexico {
	
	private File archivoACargar;
	private List<String> locs;
	private Principal ppal;
	private Hashtable<String, String> palabrasReservas;
	private Hashtable<Integer, String> simbolos;
	private int pos = 0;
	private int fila = 0;
	private MatrizTransicionEstados matriz;
	
	public Lexico(Principal ppal)
	{
		this.matriz = new MatrizTransicionEstados();
		this.locs = new ArrayList<String>();
		
		this.palabrasReservas = new Hashtable<String, String>();
		this.palabrasReservas.put("IF", "IF");
		this.palabrasReservas.put("THEN", "THEN");
		this.palabrasReservas.put("ELSE", "ELSE");
		this.palabrasReservas.put("END_IF", "END_IF");
		this.palabrasReservas.put("BEGIN", "BEGIN");
		this.palabrasReservas.put("END", "END");
		this.palabrasReservas.put("OUT", "OUT");
		this.palabrasReservas.put("LONG", "LONG");
		this.palabrasReservas.put("DOUBLE", "DOUBLE");
		this.palabrasReservas.put("SWITCH", "SWITCH");
		this.palabrasReservas.put("CASE", "CASE");
		this.palabrasReservas.put("FUNCTION", "FUNCTION");
		this.palabrasReservas.put("RETURN", "RETURN");
		this.palabrasReservas.put("MOVE", "MOVE");
		
		this.simbolos= new Hashtable<Integer, String>();
				
		this.ppal = ppal;
	}

	public void nuevo() {
		// SE LIMPIA LA PANTALLA..
		this.pos = 0;
		this.fila = 0;
	}

	public void cargar(File archivoACargar) {		
		this.archivoACargar = archivoACargar;
		this.fila = 0;
		this.pos = 0;
		
		try {
			this.locs = Files.lines(archivoACargar.toPath()).collect(Collectors.toList());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public int getToken() {
		int key = -1; //esto devuelvo.. con el valor asociado al token que encontro..
		String token = new String(); //en esta variable voy armando lo que leo hasta encontrar un token
		int longitud = 0; //la longitud del token (lo uso para resaltar el codigo)
		boolean encontro = false;
		int col;
		int row = 0;
		
		// ACA VA EL CASE DE LA MUEEERRTEEEEE MUEJEJE MUEJEJE
		while (!encontro)
		{
			char loQueLee = this.locs.get(fila).charAt(pos);
	        col = matriz.getColumn(loQueLee);
	        

		}
		//this.ppal.resaltarCodigo(locs, this.fila, this.pos, longitud);
		this.ppal.resaltarCodigo(locs, 1, 2, 4);
		return key;
	}
	
	public String getLexema(int key)
	{
		return this.simbolos.get(key);
	}
}
