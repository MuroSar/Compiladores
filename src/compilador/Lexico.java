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
		//ver como se van armando los simbolos.. hay que precargar algo??????
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

	public String getToken() {
		String token = new String();
		// ACA VA EL CASE DE LA MUEEERRTEEEEE MUEJEJE MUEJEJE
		char loQueLee = this.locs.get(fila).charAt(pos);
        switch (loQueLee) {
        	//IDENTIFICADORES...
        
        	//CONSTANTES...
        
        	//OPERADORES ARITMETICOS...
        
        	//OPERADORES DE ASIGNACION...
        
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
            	//token = "LITERAL --> '('";
                break;
            case ':':  
            	token = "LITERAL --> ':'";
                break;
            case '.':  
            	token = "LITERAL --> '.'";
                break;
            
            //CADENAS DE CARACTERES...
            
            //PALABRAS RESERVADAS...
                
                
            default: 
            	token = "ERROR..";
                break;
        }
		
		
		marcarCodigo(this.fila, this.pos);
		return token;
	}

	private void marcarCodigo(int fila, int pos) {
		try {
			int longitud = 0;
			List<String> lines = Files.lines(archivoACargar.toPath()).collect(Collectors.toList());
			for (int i=0; i < lines.size(); i++)
			{
				if(pos == i)
				{
					
				}
			}
//			this.ppal.resaltarCodigo(lines, fila, pos, longitud);
			this.ppal.resaltarCodigo(lines, 1, 2, 4);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
