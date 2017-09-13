package compilador;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import accionesSemanticas.AccionSemantica;
import complementos.ErrorToken;
import complementos.Pair;
import complementos.Token;
import interfaz.Principal;

public class Lexico {
	
	private final int FINAL = -1;
	private final int ERROR = -2;
	
	private File archivoACargar;
	private List<String> locs; //lines of code
	private List<ErrorToken> errores;
	private Principal ppal;
	private Hashtable<String, String> palabrasReservas;
	private Hashtable<Integer, Token> simbolos;
	private int pos = 0;
	private int fila = 0;
	private int estado = 0;
	private MatrizTransicionEstados matriz;
	
	public Lexico(Principal ppal)
	{
		this.matriz = new MatrizTransicionEstados();
		this.locs = new ArrayList<String>();
		this.errores = new ArrayList<ErrorToken>();
		
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
		
		this.simbolos= new Hashtable<Integer, Token>();
				
		this.ppal = ppal;
	}

	public void nuevo() {
		// SE LIMPIA LA PANTALLA..
		this.pos = 0;
		this.fila = 0;
		this.estado = 0;
		this.simbolos = new Hashtable<Integer, Token>();
		this.locs = new ArrayList<String>();
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
		Token token = new Token();
		boolean terminoArchivo = false;
		int col;
		estado = 0;
		int posInicial = pos;
				
		while (estado != this.FINAL && fila != this.locs.size() && !terminoArchivo)
		{
			//reviso que no haya terminado de leer el archivo.
			if(fila < this.locs.size())
			{
				//reviso que no haya terminado la linea.
				if(pos < this.locs.get(fila).length())
				{
					char loQueLee = this.locs.get(fila).charAt(pos);
					if(loQueLee != ' ')
					{
						while (estado != this.FINAL && !terminoArchivo)
						{
							col = matriz.getColumn(loQueLee, token.getLexema());
							Pair<Integer, AccionSemantica> actual = this.matriz.getPair(estado, col);
							estado = actual.getFirst();
							AccionSemantica as = actual.getSecond();
					        as.ejecutar(this, loQueLee, token);
					        pos++;
					        if(fila < this.locs.size())
					        {
					        	if(pos == this.locs.get(fila).length())
						        {
						        	loQueLee = '\n';
						        }
						        else
						        {
						        	loQueLee = this.locs.get(fila).charAt(pos);
						        }
					        }
					        else 
					        {
					        	terminoArchivo = true;	
					        }
						}
					}
					else
					{
						pos++;
					}
				}
				else
				{
					pos = 0;
					fila++;
					posInicial = pos;
				}
			}
		}
		
		this.ppal.resaltarCodigo(this.locs, this.fila, posInicial, this.pos);
		if (estado == this.FINAL)
		{
			return token;
		}
		
		return null;
	}
	
	public char getProxPos()
	{
		if(pos + 1 == this.locs.get(fila).length())
        {
        	return '\n';
        }
		pos++;
		char result =this.locs.get(fila).charAt(pos);
		pos--;
		
		return result;
	}
	
	public String getLexema(int key)
	{
		return this.simbolos.get(key).getLexema();
	}
	
	public String getType(int key)
	{
		return this.simbolos.get(key).getType();
	}
	
	public void setEstado(int estado)
	{
		this.estado = estado;
	}
	
	public void setNuevaLinea() 
	{
		this.pos = -1;
		this.fila++;
		//comento esto porque no anda para la cadena de caracteres si no..
		//this.estado = 0;
	}
	
	public int getFila()
	{
		return this.fila;
	}
	
	public void setPosMenosUno()
	{
		this.pos--;
	}

	public void setPosMasUno()
	{
		this.pos++;
	}
	
	public void setFila(int fila) 
	{
		this.fila = fila;
	}
	
	public void putSimbolo(Token token)
	{
		this.simbolos.put(this.getMaxKeySimbolos(), token);
	}
	
	public int getMaxKeySimbolos()
	{
		int max = 0;
		ArrayList<Integer> keys = new ArrayList<Integer>(this.simbolos.keySet());
		
		if(keys.isEmpty())
		{
			return 257; //si es el primer simbolo le asigno un 257 porque el YACC asigna a partir de ahi
		}
		else
		{
			for(int key : keys)
			{
				if(key > max)
				{
					max = key;
				}
			}
			return max + 1;
		}
	}
	
	public int getKeySimbolos(String lexema)
	{
		ArrayList<Token> values = new ArrayList<Token>(this.simbolos.values());
		for(Token t : values)
		{
			if(t.getLexema().equals(lexema))
			{
				ArrayList<Integer> keys = new ArrayList<Integer>(this.simbolos.keySet());
				for(int key : keys)
				{
					if(t.getLexema().equals(this.simbolos.get(key).getLexema()))
					{
						return key;
					}
				}
			}	
		}
			
		return this.ERROR;
	}
	
	public boolean existPalabrasReservadas(String palabraReservada)
	{
		ArrayList<String> palabrasReservadas = new ArrayList<String>(this.palabrasReservas.keySet());
		if(palabrasReservadas.contains(palabraReservada))
		{
			return true;
		}
	
		return false;
	}
	
	public void addErrorToken(ErrorToken error)
	{
		this.errores.add(error);
	}
	
	public ArrayList<ErrorToken> getErrores()
	{
		return new ArrayList<ErrorToken>(this.errores);
	}
}
