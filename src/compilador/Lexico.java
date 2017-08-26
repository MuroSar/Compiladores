package compilador;

import java.io.File;
import java.util.Hashtable;

import interfaz.Principal;

public class Lexico {
	
	private File archivoACargar;
	private Principal ppal;
	private Hashtable<String, String> palabrasReservas;
	private Hashtable<Integer, String> simbolos;
	private int pos = 0;
	private int fila = 0;
	
	public Lexico(Principal ppal)
	{
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
		//ver como se van armando los simbolos.. hay que pregarcar algo??????
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
	}

	public String getToken() {
		String token = new String();
		// ACA VA EL CASE DE LA MUEEERRTEEEEE
		return token;
	}

}
