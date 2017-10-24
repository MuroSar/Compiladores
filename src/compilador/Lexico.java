package compilador;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
	private Map<Integer, String> Reservadas;
	private Map<Integer, ArrayList<Token>> tablaSimbolos;
	private int pos = 0;
	private int fila = 0;
	private int estado = 0;
	private MatrizTransicionEstados matriz;
	
	public Lexico(Principal ppal)
	{
		this.matriz = new MatrizTransicionEstados();
		this.locs = new ArrayList<String>();
		this.errores = new ArrayList<ErrorToken>();
		
		this.Reservadas = new HashMap<Integer, String>();
		//this.Reservadas.put(257, "Identificador");
		//this.Reservadas.put(258, "Constante");
		this.Reservadas.put(259, "IF");
		this.Reservadas.put(260, "THEN");
		this.Reservadas.put(261, "ELSE");
		this.Reservadas.put(262, "END_IF");
		this.Reservadas.put(263, "BEGIN");
		this.Reservadas.put(264, "END");
		this.Reservadas.put(265, "OUT");
		this.Reservadas.put(266, "LONG");
		this.Reservadas.put(267, "DOUBLE");
		this.Reservadas.put(268, "SWITCH");
		this.Reservadas.put(269, "CASE");
		this.Reservadas.put(270, "FUNCTION");
		this.Reservadas.put(271, "RETURN");
		this.Reservadas.put(272, "MOVE");
		//this.Reservadas.put(273, "Cadena");
		//this.Reservadas.put(274, "Literal");
		//this.Reservadas.put(275, "Comparador");
		//this.Reservadas.put(276, "OperadorAritmetico");
		//this.Reservadas.put(277, "OperadorAsignacion");
		
		this.tablaSimbolos= new HashMap<Integer, ArrayList<Token>>();
		this.tablaSimbolos.put(257, new ArrayList<Token>()); //Identificador
		this.tablaSimbolos.put(258, new ArrayList<Token>()); //Constante
		this.tablaSimbolos.put(259, new ArrayList<Token>());
		this.tablaSimbolos.put(260, new ArrayList<Token>());
		this.tablaSimbolos.put(261, new ArrayList<Token>());
		this.tablaSimbolos.put(262, new ArrayList<Token>());
		this.tablaSimbolos.put(263, new ArrayList<Token>());
		this.tablaSimbolos.put(264, new ArrayList<Token>());
		this.tablaSimbolos.put(265, new ArrayList<Token>());
		this.tablaSimbolos.put(266, new ArrayList<Token>());
		this.tablaSimbolos.put(267, new ArrayList<Token>());
		this.tablaSimbolos.put(268, new ArrayList<Token>());
		this.tablaSimbolos.put(269, new ArrayList<Token>());
		this.tablaSimbolos.put(270, new ArrayList<Token>());
		this.tablaSimbolos.put(271, new ArrayList<Token>());
		this.tablaSimbolos.put(272, new ArrayList<Token>());
		this.tablaSimbolos.put(273, new ArrayList<Token>()); //Cadena
		this.tablaSimbolos.put(274, new ArrayList<Token>()); //Literal
		this.tablaSimbolos.put(275, new ArrayList<Token>()); //Comparador
		this.tablaSimbolos.put(276, new ArrayList<Token>()); //OperadorAritmetico
		this.tablaSimbolos.put(277, new ArrayList<Token>()); //OperadorAsignacion
				
		this.ppal = ppal;
	}

	public void nuevoArchivo() {
		//SE USA CUANDO CARGO UN ARCHIVO NUEVO..
		this.pos = 0;
		this.fila = 0;
		this.estado = 0;
		this.locs = new ArrayList<String>();
		this.tablaSimbolos = new HashMap<Integer, ArrayList<Token>>();
		this.Reservadas = new HashMap<Integer, String>();
		this.errores = new ArrayList<ErrorToken>();
	}
	
	public void cargar(File archivoACargar) {		
		this.archivoACargar = archivoACargar;
		this.fila = 0;
		this.pos = 0;
		
		try {
			List<Object> aux = Files.lines(archivoACargar.toPath()).collect(Collectors.toList());
			for (Object object : aux) {
			    this.locs.add(Objects.toString(object, null));
			}
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
		
		token.setKey(0);
		token.setType("FINAL");
		return token;
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
		return this.tablaSimbolos.get(key).get(this.tablaSimbolos.get(key).size()-1).getLexema();
	}
	
	public String getType(int key)
	{
		return this.tablaSimbolos.get(key).get(this.tablaSimbolos.get(key).size()-1).getType();
	}
	
	public void setEstado(int estado)
	{
		this.estado = estado;
	}
	
	public void setNuevaLinea() 
	{
		this.pos = -1;
		this.fila++;
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
		boolean esta = false;
		ArrayList<Token> aux = new ArrayList<Token>(this.tablaSimbolos.get(token.getKey()));
		for(Token t : aux)
		{
			if(t.getLexema().equals(token.getLexema()))
			{
				esta = true;
			}
		}
		if(!esta)
		{
			aux.add(token);
		}
		this.tablaSimbolos.put(token.getKey(), aux);
	}
	
	public int existPalabraReservada(String palabraReservada)
	{
		ArrayList<Integer> keys = new ArrayList<Integer>(this.Reservadas.keySet());
		for(Integer k : keys)
		{
			if(this.Reservadas.get(k).equals(palabraReservada))
				return k;
		}
		return 257;//el Id del identificador.
	}
	
	public void addErrorToken(ErrorToken error)
	{
		this.errores.add(error);
	}
	
	public ArrayList<ErrorToken> getErrores()
	{
		return new ArrayList<ErrorToken>(this.errores);
	}

	public void empiezaDeNuevo() {
		//SE USA PARA POSICIONARSE DE NUEVO EN EL PRIMER TOKEN
		this.pos = 0;
		this.fila = 0;
		this.estado = 0;
	}
	
	public void showAllTokens() {
		while(fila != this.locs.size())
		{
			this.ppal.mostrarToken();
		}
		this.empiezaDeNuevo();
	}

	public String printTSimbolos() {
		String result = "";
		ArrayList<Integer> keys = new ArrayList<Integer>(this.tablaSimbolos.keySet());
		for(Integer k : keys)
		{
			result = result + k + " -> "; 
			ArrayList<Token> aux = this.tablaSimbolos.get(k);
			for(Token t : aux)
			{
				result = result + t.toString() + " - "; 
			}
			result = result + "\n";
		}
		return result;
	}
}
