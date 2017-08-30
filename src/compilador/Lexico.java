package compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	public Lexico(Principal ppal)
	{
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
		this.simbolos.put(-1, "ERROR...");
		this.simbolos.put(257, "Identificador");
		this.simbolos.put(258, "Constante LONG");
		this.simbolos.put(259, "Constante DOWBLE");
		this.simbolos.put(260, "+");
		this.simbolos.put(261, "-");
		this.simbolos.put(262, "*");
		this.simbolos.put(263, "/");
		this.simbolos.put(264, "=");
		this.simbolos.put(265, ">=");
		this.simbolos.put(266, "<=");
		this.simbolos.put(267, ">");
		this.simbolos.put(268, "<");
		this.simbolos.put(269, "==");
		this.simbolos.put(270, "<>");
		this.simbolos.put(271, "(");
		this.simbolos.put(272, ")");
		this.simbolos.put(273, ",");
		this.simbolos.put(274, ":");
		this.simbolos.put(275, ".");
		this.simbolos.put(276, "cadena de caracteres???????");
		this.simbolos.put(277, "IF");
		this.simbolos.put(278, "THEN");
		this.simbolos.put(279, "ELSE");
		this.simbolos.put(280, "END_IF");
		this.simbolos.put(281, "BEGIN");
		this.simbolos.put(282, "END");
		this.simbolos.put(283, "OUT");
		this.simbolos.put(284, "LONG");
		this.simbolos.put(285, "DOUBLE");
		this.simbolos.put(286, "SWITCH");
		this.simbolos.put(287, "CASE");
		this.simbolos.put(288, "FUNCTION");
		this.simbolos.put(289, "RETURN");
		this.simbolos.put(290, "MOVE");
		this.simbolos.put(291, "Comentario????????????");
		this.simbolos.put(292, "Cadena multilinea????????????????");
		
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
		
		// ACA VA EL CASE DE LA MUEEERRTEEEEE MUEJEJE MUEJEJE
		while (!encontro)
		{
			char loQueLee = this.locs.get(fila).charAt(pos);
	        switch (loQueLee) {
	        	
	        	//IDENTIFICADORES...
	        
	        	//CONSTANTES...
	        
	        	//OPERADORES ARITMETICOS...
	        	case '+':  
	        		//en cada case tengo que revisar si estoy empezando o si vengo con algo ya..
	        		//en base a eso.. voy cambiando las cosas.. 
	        		if(token.isEmpty())
	        		{
	        			key = 260;
	        			token = "+";
	        			pos++;
	        		}
	        		else
	        		{
	        			//aca no se que carajo hacer...
	        			//puedo ya ponerle la key equivalente y si tengo que cortar aca ya lo tiene cargadoo..
	        			//entonces cada case ya pone una key
	        		}
	        		
	        		break;
	        	case '-':  
	        		token = "OPERADOR ARITMETICO --> '-'";
	        		break;
	        	case '*':  
	        		token = "OPERADOR ARITMETICO --> '*'";
	        		break;
	        	case '/':  
	        		token = "OPERADOR ARITMETICO --> '/'";
	        		break;
	        	//OPERADORES DE ASIGNACION...
	        	case '=':  
	        		token = "OPERADOR ARITMETICO --> '+'";
	        		break;
	        	
	        	//COMPARADORES...
	               
	        	//LITERALES...
	            case '(':  
	            	token = "LITERAL --> '('";
	                break;
	            case ')':  
	            	token = "LITERAL --> '('";
	                break;
	            case ',':  
	            	//revisar como hacerlo bien por los doubles
	            	//token = "";
	                break;
	            case ':':  
	            	token = "LITERAL --> ':'";
	                break;
	            case '.':  
	            	token = "LITERAL --> '.'";
	                break;
	            
	            //CADENAS DE CARACTERES...
	            
	            //PALABRAS RESERVADAS...
	                
	            case ' ':
	        		if(token.isEmpty())
	        		{
	        			pos++;
	        			return getToken();
	        		}
	        		else
	        		{
	        			token = "tenemos que revisar esto......";
	        		}
	        		break;
	                
	            default: 
	            	encontro = true;
	            	token = "ERROR..";
	                break;
	        }

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
