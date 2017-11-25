package compilador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tercetos.*;
import complementos.Token;

public class GenCodigo {

	private ArrayList<Terceto> tercetos;
	private Sintactico sintactico;
	private String declaraciones_out;
	private String comparador="";
	
	private String declaraciones_const;
	
	private ArrayList<Integer> labels;
	
	private ArrayList<Terceto> tercetosFuncion;
	
	public GenCodigo() {
		declaraciones_out = "";
		this.labels = new ArrayList<Integer>();
		this.declaraciones_const = "";
	}
	
	public void nuevo() {
		this.declaraciones_out = "";
		this.tercetos.clear();
		this.comparador = "";
		this.labels.clear();
		this.tercetosFuncion.clear();
		this.declaraciones_const = "";
	}
	
	public ArrayList<Integer> getLabels(){
		return new ArrayList<Integer>(this.labels);
	}
	
	public void addLabel(Integer nro) {
		this.labels.add(nro);
	}
	
	public void removeLabel(String nro) {
		this.labels.remove(nro);
	}
	
	public void setListaTercetos(ArrayList<Terceto> ter) {
		tercetos = new ArrayList<Terceto>(ter);
	}
	
	public void setSintactico(Sintactico s){
		this.sintactico=s;
	}
	
	public Sintactico getSintactico() {
		return this.sintactico;
	}
	
	public boolean delcaracionesOutContains(String dec) {
		if(this.declaraciones_out.contains(dec)) {
			return true;
		}
		return false;
	}
	
	public void setDeclaracionesOut(String dec) { 
		this.declaraciones_out +=dec;
	}
	
	public boolean delcaracionesConstContains(String dec) {
		if(this.declaraciones_const.contains(dec)) {
			return true;
		}
		return false;
	}
	
	public void setDeclaracionesConst(String dec) { 
		this.declaraciones_const += dec;
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
        declaracion += "constCeroLong DD  0\n";
        declaracion += "__MIN DD  -2147483648\n";
        declaracion += "__MAX DD  2147483647\n";
        declaracion += "_msjDC DB \"Error: Division por cero\", 0\n";
        declaracion += "_msjOverflow DB \"Error: Overflow\", 0\n";
        for (String key : this.sintactico.getLexico().getTSKeys()) {
        	Token t = this.sintactico.getLexico().getTokenFromTS(key);
        	if(t.getType().equals("Identificador")) {
        		if(t.getTipoDato().equals("LONG")) {
        			declaracion = declaracion + t.getLexema() + " DD ?\n"; // long = 8 bytes - 32 bits
        		}
        		else {
        			declaracion = declaracion + t.getLexema() + " DT ?\n"; // double = 10 bytes - 80 bits
        		}
        	}
        }
        return declaracion + declaraciones_out + declaraciones_const; 
    }
	
	public void generarCodigo() {
		String asm = "";
		if (!this.sintactico.getLexico().getErrores().isEmpty() || this.sintactico.huboErrores())
		{
			this.sintactico.showError("No se puede generar el Assembler porque la gramática contiene errores");
		}
		else
		{
			asm = this.getEncabezado();
			String instrucciones = "";
	        instrucciones += ".code\n";
	        instrucciones += getDivZero(); 
	        instrucciones += getOverflow();
	        
	        this.tercetosFuncion = this.sintactico.getTercetosFuncion();
	        for (Terceto t : this.tercetosFuncion) {
				t.setGenerador(this);
				instrucciones += t.getCodigo();
			}

	        instrucciones += "start:\n";
			for (Terceto t : this.tercetos) {
				if(!this.tercetosFuncion.contains(t)) {
					t.setGenerador(this);
					instrucciones += t.getCodigo();	
				}
				else {
					if(t.getMarcaAntes() && this.tercetos.get(0) != t) {
						instrucciones += "Label"+t.getPos()+":\n";
					}
				}
			}
	        asm += getDeclaraciones(); // Va despues de generar las intrucciones porque se incluyen las @aux# en la TS
	        asm += instrucciones;
	        asm += "Label" + this.tercetos.size() + ":\n";
	        asm += "invoke ExitProcess, 0\n";
	        asm += "end start";
		
			String path = this.sintactico.getLexico().getPathArchivoACargar();
			File file = new File(path.substring(0, path.length()-3) + "asm");
			FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(asm);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getNombreFuncion(String n) {
		return this.sintactico.funcionPosGet(n);
	}

	public String getComparador() {
		return comparador;
	}

	public void setComparador(String comparador) {
		this.comparador = comparador;
	}
}
