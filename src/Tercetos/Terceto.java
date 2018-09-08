package Tercetos;

import compilador.GenCodigo;
import compilador.Lexico;
import compilador.ParserVal;
import compilador.Sintactico;
import complementos.Token;

public abstract class Terceto implements Comparable<Terceto>{

	protected String operador;
	protected String primero;
	protected String segundo;
	protected String nombrePrimero;
	protected String nombreSegundo;
	protected GenCodigo generador;
	private int pos;
	private String tipoDato;
	protected boolean marcaAntes = false;
	protected boolean marcaDesp = false;
	protected ParserVal primeroParserVal;
	protected ParserVal segundoParserVal;
	protected String ambitoReal;
	
	//para el borrado logico de la optimizacion
	private boolean isDeleted = false;

	public Terceto(String operador, ParserVal primero, ParserVal segundo, int pos, GenCodigo generador) {
		this.operador = operador;
		this.generador = generador;
		this.ambitoReal = generador.getSintactico().getNameManglingForAmbito(generador.getSintactico().getAmbito());
				
		if(primero != null) {
			if(primero.obj != null) {
				int referencia = ((Terceto)primero.obj).getPos(); 
				this.primero = "[" + referencia + "]";
			}
			else {
				this.primero = primero.sval;
				
				//esto es para imprimir los nombres en los tercetos nomas..
				if (Sintactico.esVariable(primero)) {
					Token t = Lexico.getTokenFromTS(primero.sval + "@Variable" + ambitoReal);
					if (t == null) {
						t = Lexico.getTokenFromTS(primero.sval + "@Funcion" + ambitoReal);
					}
					
					String ambito = t.getAmbito();
					this.nombrePrimero = primero.sval + ambito;
				}
				else {
					this.nombrePrimero = primero.sval;	
				}
				//esto es para imprimir los nombres en los tercetos nomas..
			}
		}
		else { //significa que viene de un BIncondificonal
			this.primero = "";
		}
		
		if(segundo != null) {
			if(segundo.obj != null) {
				int referencia = ((Terceto)segundo.obj).getPos(); 
				this.segundo = "[" + referencia + "]";
			}
			else {
				this.segundo = segundo.sval;
				
				//esto es para imprimir los nombres en los tercetos nomas..
				if (Sintactico.esVariable(segundo)) {
					String ambito = Lexico.getTokenFromTS(segundo.sval + "@Variable" + ambitoReal).getAmbito();
					this.nombreSegundo = segundo.sval + ambito;
				}
				else {
					this.nombreSegundo = segundo.sval;
				}
				//esto es para imprimir los nombres en los tercetos nomas..
			}
		}
		else { //significa que viene de un BFalse
			this.segundo = "";
		}
			
		this.pos = pos;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getPrimero() {
		return primero;
	}

	public void setPrimero(String primero) {
		this.primero = primero;
	}

	public void setPrimero(int primero) {
		this.primero = "[" + primero + "]";
	}
	
	public String getSegundo() {
		return segundo;
	}

	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
	
	public void setSegundo(int segundo) {
		this.segundo = "[" + segundo + "]";
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	public boolean getMarcaAntes() {
		return marcaAntes;
	}

	public void setMarcaAntes(boolean marca) {
		this.marcaAntes = marca;
	}

	public boolean getMarcaDesp() {
		return marcaDesp;
	}

	public void setMarcaDesp(boolean marca) {
		this.marcaDesp = marca;
	}

	public abstract String getCodigo();
	
	public int isReferencia(Object obj) {
		if(((String)obj).contains("[") && ((String)obj).contains("]")) {
			return Integer.parseInt(((String)obj).substring(1, ((String)obj).length()-1));
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return "(" + this.operador + ", " + this.nombrePrimero + ", " + this.nombreSegundo + ")";
	}
	
	public void setGenerador(GenCodigo gen) {
		this.generador=gen;
	}
	
	public boolean equals(Terceto terceto) {
		if(this.operador.equals(terceto.operador) && this.primero.equals(terceto.getPrimero()) && this.segundo.equals(terceto.getSegundo())) {
			return true;
		}
		return false;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ParserVal getPrimeroParserVal() {
		return primeroParserVal;
	}

	public void setPrimeroParserVal(ParserVal primeroParserVal) {
		this.primeroParserVal = primeroParserVal;
	}

	public ParserVal getSegundoParserVal() {
		return segundoParserVal;
	}

	public void setSegundoParserVal(ParserVal segundoParserVal) {
		this.segundoParserVal = segundoParserVal;
	}

	@Override
    public int compareTo(Terceto t) {
        if (this.pos > t.getPos()) {
        	return 1;
        }
        else if (this.pos < t.getPos()) {
        	return -1;
        }
        else return 0;
    }
	
}
