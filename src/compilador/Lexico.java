package compilador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	private static Map<String, Token> tablaSimbolos;
	private int pos = 0;
	private int fila = 0;
	private int estado = 0;
	private MatrizTransicionEstados matriz;
	
	public Token ultimoToken;
	
	public Lexico(Principal ppal)
	{
		this.matriz = new MatrizTransicionEstados();
		this.locs = new ArrayList<String>();
		this.errores = new ArrayList<ErrorToken>();
		
		this.Reservadas = new HashMap<Integer, String>();
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
		
		this.tablaSimbolos= new HashMap<String, Token>();
				
		this.ppal = ppal;
		
		this.ultimoToken = new Token();
	}

	public void nuevoArchivo() {
		//SE USA CUANDO CARGO UN ARCHIVO NUEVO..
		this.pos = 0;
		this.fila = 0;
		this.estado = 0;
		this.locs.clear();
		this.tablaSimbolos.clear();
		this.errores.clear();
		this.ultimoToken = new Token();
	}
	
	public String getPathArchivoACargar() {
		return this.archivoACargar.getPath();
	}
	
	public void cargar(File archivoACargar) {		
		this.archivoACargar = archivoACargar;
		this.fila = 0;
		this.pos = 0;
		
		try {
			InputStream is = new FileInputStream(archivoACargar.toPath().toString()); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
			String line = buf.readLine(); 
			StringBuilder sb = new StringBuilder(); 
			while(line != null){ 
				this.locs.add(line);
				line = buf.readLine(); 
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Token getToken() {
		Token token = this.getTokenParcial();
		if (ultimoToken.getLexema().equals("==") || ultimoToken.getLexema().equals("<>")
			|| ultimoToken.getLexema().equals("<") || ultimoToken.getLexema().equals(">")
			|| ultimoToken.getLexema().equals("<=") || ultimoToken.getLexema().equals(">=")
			|| ultimoToken.getLexema().equals("=") || ultimoToken.getLexema().equals("+")
			|| ultimoToken.getLexema().equals("-") || ultimoToken.getLexema().equals("*")
			|| ultimoToken.getLexema().equals("/") || ultimoToken.getLexema().equals("(")) 
		{
			if(token.getLexema().equals("-")) {
				char prox = this.locs.get(fila).charAt(pos);
				if(Character.isDigit(prox) || Character.isLetter(prox)) {
					Token tokenAux = this.getTokenParcial();
					
					tokenAux.setLexema("-" + tokenAux.getLexema());
					token = tokenAux;
					
					putSimbolo(token);
				}
			}
		}
		this.ultimoToken = token;
		return token;
	}
	
	public Token getTokenParcial() {
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
					if(loQueLee != ' ' && loQueLee != '\t')
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
	
	public static Token getTokenFromTS(String key)
	{
		Token result = tablaSimbolos.get(key);
		String auxKey = "";
		if(result == null) {
			 String[] aux = key.split("@");
			 auxKey = aux[0];
			 
			 for(int i=1; i<aux.length-1; i++) {
				 auxKey = auxKey + "@" + aux[i];
			 }
			 result = tablaSimbolos.get(auxKey);
		 }
		 return result;
	}

	public static void removeTokenFromTS(String key) 
	{
		tablaSimbolos.remove(key);
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
	
	public static void putSimbolo(Token token)
	{
		tablaSimbolos.put(token.getLexema(), token);
	}
	
	public static void putSimboloAsm(String lexema) 
	{
		Token t = new Token();
		t.setLexema(lexema);
		t.setType("Identificador");
		
		putSimbolo(t);
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
		this.tablaSimbolos.clear();
		this.errores.clear();
		this.ultimoToken = new Token();
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
		for (String key : this.tablaSimbolos.keySet()){
			result = result + key + " -> ";
			result = result + this.tablaSimbolos.get(key).toString();
			result = result + "\n";
		} 
		return result;
	}
	
	public boolean estaDeclarada(String lexema, String dato, String ambito, boolean esDeclaracion) {
		if(esDeclaracion) {
			if(dato.equals("variable")) {
				for (String key : this.tablaSimbolos.keySet()){
					if(key.equals(lexema + "@Variable" + ambito)) {
						String ambitoVarTS = this.tablaSimbolos.get(key).getAmbito();
						if(ambito.contains(ambitoVarTS)) {
							return true;						
						}
					}
				}
			}
			else if(dato.equals("funcion")) {
				for (String key : this.tablaSimbolos.keySet()){
					if(key.equals(lexema + "@Funcion" + ambito)) {
						return true;
					}
				}
			}	
		}
		else {
			if(dato.equals("variable")) {
				for (String key : this.tablaSimbolos.keySet()){
					if((lexema + "@Variable" + ambito).contains(key)) {
						String ambitoVarTS = this.tablaSimbolos.get(key).getAmbito();
						if(!ambitoVarTS.equals("") && ambito.contains(ambitoVarTS)) {
							return true;						
						}
					}
				}
			}
			else if(dato.contains("funcion")) {
				for (String key : this.tablaSimbolos.keySet()){
					if(key.equals(lexema + "@Funcion" + ambito)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public ArrayList<String> getTSKeys(){
		return new ArrayList<String>(this.tablaSimbolos.keySet());
	}
	
	public void depurarTS() {
		ArrayList<String> keys = new ArrayList<String>(this.tablaSimbolos.keySet());
		for(String k : keys) {
			if(Character.isLetter(k.charAt(0))) {
				if(!k.contains("@Variable") && !k.contains("@Funcion") && this.existPalabraReservada(k) == 257)
					this.tablaSimbolos.remove(k);
			}
		}
	}
	
	public static void actualizarDestino(String key, String destino) {
		Token t = getTokenFromTS(key+"@Variable");
		removeTokenFromTS(key+"@Variable");
		t.setDestino(destino);
		putSimbolo(t);
	}
}
