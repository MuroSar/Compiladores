package compilador;

import java.util.ArrayList;

import Tercetos.Terceto;

public class Optimizador {

	
	public static void optimizacionRedundanciaSimple(ArrayList<Terceto> tercetos) {
		//trabajo sobre la lista de tercetos que me pasan asi modifico esa directamente..
		ArrayList<Terceto> tercetosOptimizados = new ArrayList<Terceto>();
		ArrayList<Terceto> tercetosAsignacion = new ArrayList<Terceto>();
		
		boolean agregoTerceto = true;
		
		int pos = 0;
		while (pos < tercetos.size()) {
			agregoTerceto = true;
			Terceto ter = tercetos.get(pos);
			
			if (ter.getOperador() == "+" || ter.getOperador() == "-" || ter.getOperador() == "*" || ter.getOperador() == "/") {
				//armo la asignacion..
				tercetosAsignacion.add(ter);
				agregoTerceto = false;
			}
			else if (ter.getOperador() == "=") {
				//consegui toda la asignacion, optimizo..
				optimizar(tercetosAsignacion);
				tercetosOptimizados.addAll(tercetosAsignacion);
				agregoTerceto = false;
			}
			
			if(agregoTerceto) {
				tercetosOptimizados.add(ter);	
			}
		}
		
		tercetos = tercetosOptimizados;
	}

	private static void optimizar(ArrayList<Terceto> tercetosAsignacion) {
		int pos = 0;
		int posicionTerceto;
		int posicionTercetoRepetido;
		
		while (pos < tercetosAsignacion.size()) {
			//para cada terceto de la asignacion..
			Terceto terceto = tercetosAsignacion.get(pos);
			
			//reviso los tercetos que quedan para ver si hay repetidos
			for (int i=pos; i<tercetosAsignacion.size(); i++) {
				if(terceto.equals(tercetosAsignacion.get(i))) {
					//si hay alguno repetido, borro el terceto y actualizo las referencias
					posicionTerceto = terceto.getPos();
					posicionTercetoRepetido = tercetosAsignacion.get(i).getPos();
					tercetosAsignacion.remove(i);
					actualizaReferencias(tercetosAsignacion, posicionTerceto, posicionTercetoRepetido);
				}
			}
		}
	}

	private static void actualizaReferencias(ArrayList<Terceto> tercetosAsignacion, int posicionTerceto, int posicionTercetoRepetido) {
		for (Terceto t : tercetosAsignacion) {
			if(t.getPrimero().equals("[" + posicionTercetoRepetido + "]")) {
				t.setPrimero("[" + posicionTerceto + "]");
			}
			if(t.getSegundo().equals("[" + posicionTercetoRepetido + "]")) {
				t.setSegundo("[" + posicionTerceto + "]");
			}
		}
	}
}
