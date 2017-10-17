package complementos;

public class ErrorToken {

	private String original;
	private String corregido;
	private int nroToken;
	private String error;
	private String accionCorrectiva;
	private int nroLinea;
	
	public ErrorToken()
	{
		this.original = "";
		this.corregido = "";
		this.nroToken = -2;
		this.error = "";
		this.accionCorrectiva = "";
		this.nroLinea = -2;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getCorregido() {
		return corregido;
	}

	public void setCorregido(String corregido) {
		this.corregido = corregido;
	}

	public int getNroToken() {
		return nroToken;
	}

	public void setNroToken(int nroToken) {
		this.nroToken = nroToken;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getAccionCorrectiva() {
		return accionCorrectiva;
	}

	public void setAccionCorrectiva(String accionCorrectiva) {
		this.accionCorrectiva = accionCorrectiva;
	}

	public int getNroLinea() {
		return nroLinea;
	}

	public void setNroLinea(int nroLinea) {
		this.nroLinea = nroLinea;
	}

	@Override
	public String toString() {
		return "ERROR: " + 
				"	Original= " + original + "\n" +
				"	Corregido= " + corregido + "\n" +
				"	NroToken= " + nroToken + "\n" + 
				"	Descripcion= " + error + "\n" +
				"	AccionCorrectiva= " + accionCorrectiva + "\n" + 
				"	NroLinea= " + nroLinea + "\n";
	}
	
	
	
}
