package complementos;

public class Token {
	
	private String lexema;
	private int key;
	private String type; // tipo de token IDENTIFICADOR LITERAL ANOTACION etc
	private int linea;
	private String ambito;
	
	public Token(String lexema, int key, String type, int linea, String ambito)
	{
		this.lexema = lexema;
		this.key = key;
		this.type = type;
		this.linea = linea;
		this.ambito = ambito;
	}
	
	public Token ()
	{
		this.lexema = "";
		this.key = -2;
		this.type = "";
		this.linea = -2;
		this.ambito = "";
	}
	
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLinea() {
		return linea;
	}
	public void setLinea(int linea) {
		this.linea = linea;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	@Override
	public String toString() {
		return "Token [lexema=" + lexema + ", key=" + key + ", type=" + type + ", linea=" + linea + ", ambito=" + ambito + "]";
	}
	
	
	
}
