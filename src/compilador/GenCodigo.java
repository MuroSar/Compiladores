package compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tercetos.*;
import complementos.Token;

public class GenCodigo {

	private ArrayList<Terceto> tercetos;
	private Sintactico sintactico;
	private String declaraciones_out;
	
	//agregar los dos archivos
	
	public GenCodigo() {
		declaraciones_out = new String();
	}
		
	public void setListaTercetos(ArrayList<Terceto> ter) {
		tercetos = new ArrayList<Terceto>(ter);
	}
	
	public void setSintactico(Sintactico s){
		this.sintactico=s;
	}
	
	public String getEncabezado() {
		String encabezado = "";

		encabezado += ".386\n";
        encabezado += ".model flat, stdcall\n";
        encabezado += "option casemap :none\n";
        encabezado += "include \\masm32\\include\\windows.inc\n";
        encabezado += "include \\masm32\\include\\kernel32.inc\n";
        encabezado += "include \\masm32\\include\\user32.inc\n";
        encabezado += "includelib \\masm32\\lib\\kernel32.lib\n";
        encabezado += "includelib \\masm32\\lib\\user32.lib\n";
		
		return encabezado;
	}
	
    private String getDivZero() {
        String str = new String();
        str += "_division_cero:\n"; //si se detecta una division por cero se llama aca..
        str += "invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK\n";
        str += "invoke ExitProcess, 0\n";
        return str;
    }
    
    private String getOverflow() {
        String str = new String();
        str += "_overflow:\n"; //si se detecta overflow se llama aca..
        str += "invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK\n";
        str += "invoke ExitProcess, 0\n";
        return str;
    }
    
    private String getDeclaraciones() {
        String declaracion = new String();
        declaracion += ".data\n";
        declaracion += "__MIN DD  -2147483648\n";
        declaracion += "__MAX DD  2147483647\n";
        declaracion += "_msjDC DB \"Error: Division por cero\", 0\n";
        declaracion += "_msjOverflow DB \"Error: Overflow\", 0\n";

        int numCad = 0;
        for (String key : this.sintactico.getLexico().getTSKeys()) {
        	Token t = this.sintactico.getLexico().getTokenFromTS(key);
        	if(t.getType().equals("Identificador")) {
        		if(t.getTipoDato().equals("LONG")) {
        			declaracion = declaracion + t.getLexema() + " DD ?\n"; // long = 8 bytes
        		}
        		else {
        			declaracion = declaracion + t.getLexema() + " DT ?\n"; // double = 10 bytes
        		}
        	}/*
        	else if (t.getType().equals("Cadena")) {
        		String nombre = "Cadena" + numCad;
        		//t.setNombreCadena(nombre);
        		declaracion = declaracion + nombre + " DB " + t.getLexema() + ", 0\n";
        		numCad++;
        	}*/
        }
        
        return declaracion + declaraciones_out;
    }
	
	public void generarCodigo() {
		if (!this.sintactico.getLexico().getErrores().isEmpty() || this.sintactico.huboErrores())
		{
			this.sintactico.showError("No se puede generar el Assembler porque la gramática contiene errores");
		}
		else
		{
			String asm;
			asm = this.getEncabezado();
			
			String instrucciones = "";
	        instrucciones += ".code\n";
	        //aca van los tercetos..
	        instrucciones += getDivZero(); 
	        instrucciones += getOverflow();
	        instrucciones += "start:\n";

			for (Terceto t:tercetos) {
				t.setGenerador(this);
				System.out.println(t.getCodigo());
			}

	        asm += getDeclaraciones(); // Va despues de generar las intrucciones porque se incluyen las @aux# en la TS
	        asm += instrucciones;
	        asm += "invoke ExitProcess, 0\n";
	        asm += "end start";
			
		}
	}
	
	public String getNombreFuncion(String n) {
		return this.sintactico.funcionPosGet(n);
	}
}
