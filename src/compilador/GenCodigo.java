package compilador;

import java.util.ArrayList;
import java.util.Hashtable;

import Tercetos.Terceto;

public class GenCodigo {

	private Hashtable<Integer, Terceto> tercetos;
	
	public GenCodigo() {
		this.tercetos = new Hashtable<Integer, Terceto>();
	}
	
	public void addTerceto(Terceto t) {
		int pos = this.tercetos.size();
		this.tercetos.put(pos, t);
	}
	
	public Terceto getTerceto(int key) {
		return this.tercetos.get(key);
	}
	
	public String showTercetos() {
		String salida = "";
		ArrayList<Integer> keys = new ArrayList<Integer>(this.tercetos.keySet());
		for(Integer k : keys) {
			salida = salida + k + ": " + this.tercetos.get(k).toString() + "\n";
		}
		return salida;
	}
}
