package compilador;

import java.io.File;

import interfaz.Principal;

public class Lexico {
	
	private File archivoACargar;
	private Principal ppal;
	private int pos = 0;
	
	public Lexico(Principal ppal)
	{
		this.ppal = ppal;
	}

	public void nuevo() {
		// SE LIMPIA LA PANTALLA..
		this.pos = 0;
	}

	public void cargar(File archivoACargar) {
		this.archivoACargar = archivoACargar;
	}

	public String getToken() {
		String token = new String();
		// ACA VA EL CASE DE LA MUEEERRTEEEEE
		return token;
	}

}
