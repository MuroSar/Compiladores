package Tercetos;

public class Terceto {

	private String operador;
	private Object primero;
	private Object segundo;
	
	public Terceto(String operador, Object primero, Object segundo) {
		this.operador = operador;
		this.primero = primero;
		this.segundo = segundo;
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

	public void setPrimero(Object primero) {
		this.primero = primero;
	}

	public Object getSegundo() {
		return segundo;
	}

	public void setSegundo(Object segundo) {
		this.segundo = segundo;
	}
	
	public int isReferencia(Object obj) {
		if(((String)obj).contains("[") && ((String)obj).contains("]")) {
			return Integer.parseInt(((String)obj).substring(1, ((String)obj).length()-1));
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return "(" + this.operador + ", " + (String)this.primero + ", " + (String)this.segundo + ")";
	}
}
