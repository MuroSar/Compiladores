package complementos;

public class Token {
	
	private String lexema;
	private int key;
	private String type; // tipo de token IDENTIFICADOR LITERAL ANOTACION etc
	private int linea;
	private String ambito;
	private String tipoDato;
	
	
	//lexema para nombres de variables --> nombreVariable@Variable
	//lexema para nombres de funciones --> nombreVariable@Funcion
	
	//----------------------------------------------------
	//257 --> "Identificador"
	//258 --> "Constante"
	//259 --> "IF"
	//260 --> "THEN"
	//261 --> "ELSE"
	//262 --> "END_IF"
	//263 --> "BEGIN"
	//264 --> "END"
	//265 --> "OUT"
	//266 --> "LONG"
	//267 --> "DOUBLE"
	//268 --> "SWITCH"
	//269 --> "CASE"
	//270 --> "FUNCTION"
	//271 --> "RETURN"
	//272 --> "MOVE"
	//273 --> "Cadena"
	//274 --> "Literal"
	//275 --> "Comparador"
	//276 --> "OperadorAritmetico"
	//277 --> "OperadorAsignacion"
	//----------------------------------------------------
	
	public Token(String lexema, int key, String type, int linea, String ambito, String tipoDato)
	{
		this.lexema = lexema;
		this.key = key;
		this.type = type;
		this.linea = linea;
		this.ambito = ambito;
		this.tipoDato = tipoDato;
	}
	
	public Token ()
	{
		this.lexema = "";
		this.key = -2;
		this.type = "";
		this.linea = -2;
		this.ambito = "";
		this.tipoDato = "";
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
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}


	@Override
	public String toString() {
		return "Token [lexema=" + lexema + ", key=" + key + ", type=" + type + ", linea=" + linea + ", ambito=" + ambito + ", tipoDato= " + tipoDato + "]";
	}
	
	
	
}
