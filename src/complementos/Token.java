package complementos;

public class Token {
	
	private String lexema;
	private int key;
	private String type; // tipo de token IDENTIFICADOR LITERAL ANOTACION etc
	
	public Token(String lexema, int key, String type)
	{
		this.lexema = lexema;
		this.key = key;
		this.type = type;
	}
	
	public Token ()
	{
		
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


}
