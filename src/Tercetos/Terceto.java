package Tercetos;

import compilador.ParserVal;

public abstract class Terceto {

	protected String operador;
	protected String primero;
	protected String segundo;
	
	private int pos;
	
	public Terceto(String operador, ParserVal primero, ParserVal segundo, int pos) {
		this.operador = operador;
		this.primero = primero.sval;
		
		if(segundo.obj != null) {
			int referencia = ((Terceto)segundo.obj).getPos(); 
			this.segundo = "[" + referencia + "]";
		}
		else {
			this.segundo = segundo.sval;	
		}
		this.pos = pos;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Object getPrimero() {
		return primero;
	}

	public void setPrimero(String primero) {
		this.primero = primero;
	}

	public Object getSegundo() {
		return segundo;
	}

	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
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
		return "(" + this.operador + ", " + this.primero + ", " + this.segundo + ")";
	}
}
