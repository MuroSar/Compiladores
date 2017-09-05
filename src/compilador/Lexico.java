package compilador;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import accionesSemanticas.AccionSemantica;
import complementos.Pair;
import complementos.Token;
import interfaz.Principal;

public class Lexico {
	
	private final int FINAL = -1;
	private final int ERROR = -2;
	
	private File archivoACargar;
	private List<String> locs;
	private Principal ppal;
	private Hashtable<String, String> palabrasReservas;
	private Hashtable<Integer, String> simbolos;
	private int pos = 0;
	private int fila = 0;
	private MatrizTransicionEstados matriz;
	int longitud = 0; //la longitud del token (lo uso para resaltar el codigo)
	
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

	public Token getToken() {
		Token token = new Token();//en esta variable voy armando lo que leo hasta encontrar un token
		int col;
		int estado = 0;
				
		//reviso que no haya terminado de leer el archivo.
		if(fila < this.locs.size() && estado != this.FINAL)
		{
			//reviso que no haya terminado la linea.
			if(pos < this.locs.get(fila).length())
			{
				char loQueLee = this.locs.get(fila).charAt(pos);
				col = matriz.getColumn(loQueLee);	
				while (estado != this.FINAL)
				{
					Pair<Integer, AccionSemantica> actual = this.matriz.getPair(estado, col);
					estado = actual.getFirst();
					AccionSemantica as = actual.getSecond();
			        token = as.ejecutar(this, loQueLee);
				}
			}
			else
			{
				pos = 0;
				fila++;
				
			}
		}
		
		//this.ppal.resaltarCodigo(this.locs, this.fila, this.pos, this.longitud);
		this.ppal.resaltarCodigo(locs, 1, 2, 4);
		if (estado == this.FINAL)
		{
			return token;
		}
		
		return new Token("Fin de archivo.", 0, "Fin de archivo");
	}
	
	public String getLexema(int key)
	{
		return this.simbolos.get(key);
	}
	
	public void setPos(int pos) 
	{
		this.pos = pos;
	}
	
	public void setFila(int fila) 
	{
		this.fila = fila;
	}
	
	public void aumentarLongitud() 
	{
		this.longitud++;
	}
	
	public String getValueSimbolos(int key)
	{	
		return this.simbolos.get(key);
	}
	
	public int getKeySimbolos(String value)
	{
		ArrayList<String> values = new ArrayList<String>(this.simbolos.values());
		if(values.contains(value))
		{
			ArrayList<Integer> keys = new ArrayList<Integer>(this.simbolos.keySet());
			for(int key : keys)
			{
				if(value.equals(this.simbolos.get(key)))
				{
					return key;
				}
			}
		}
	
		return this.ERROR;
	}
	
	public boolean getKeyPalabrasReservadas(String palabraReservada)
	{
		ArrayList<String> palabrasReservadas = new ArrayList<String>(this.palabrasReservas.keySet());
		if(palabrasReservadas.contains(palabraReservada))
		{
			return true;
		}
	
		return false;
	}
}
