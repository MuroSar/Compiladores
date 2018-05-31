package compilador;

import java.util.ArrayList;

import Tercetos.Terceto;

public class Optimizador {

	
	public static ArrayList<Terceto> optimizacionRedundanciaSimple(ArrayList<Terceto> tercetos) {
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
				tercetosOptimizados.add(ter);	
				agregoTerceto = false;
			}
			
			if(agregoTerceto) {
				tercetosOptimizados.add(ter);	
			}
			
			pos++;
		}
		
		return tercetosOptimizados;
	}

	private static void optimizar(ArrayList<Terceto> tercetosAsignacion) {
		int pos = 0;
		int posicionTerceto;
		int posicionTercetoRepetido;
		
		while (pos < tercetosAsignacion.size()) {
			//para cada terceto de la asignacion..
			Terceto terceto = tercetosAsignacion.get(pos);
			
			//reviso los tercetos que quedan para ver si hay repetidos
			for (int i=pos+1; i<tercetosAsignacion.size(); i++) {
				if(terceto.equals(tercetosAsignacion.get(i))) {
					//si hay alguno repetido, borro el terceto y actualizo las referencias
					//posicionTerceto = terceto.getPos();
					//posicionTercetoRepetido = tercetosAsignacion.get(i).getPos();
					
					//tercetosAsignacion.remove(i);
					tercetosAsignacion.get(i).setDeleted(true);
					
					actualizaReferencias(tercetosAsignacion, terceto, tercetosAsignacion.get(i));
				}
			}
			
			pos++;
		}
	}

	private static void actualizaReferencias(ArrayList<Terceto> tercetosAsignacion, Terceto terceto, Terceto tercetoRepetido) {
		for (Terceto t : tercetosAsignacion) {
			if(t.getPrimero().equals("[" + tercetoRepetido.getPos() + "]")) {
				t.setPrimero("[" + terceto.getPos() + "]");
				if(t.getPrimeroParserVal().obj != null) {
					t.setPrimeroParserVal(terceto.getPrimeroParserVal());
				}
			}
			if(t.getSegundo().equals("[" + tercetoRepetido.getPos() + "]")) {
				t.setSegundo("[" + terceto.getPos() + "]");
				if(t.getSegundoParserVal().obj != null) {
					t.setSegundoParserVal(terceto.getSegundoParserVal());
				}
				
				///TODO: actualizar bien todas las referencias..
				///esta generando lineas de asm de mas porque el aux1 o aux2 
				///(depende del cual esta repetido)
				///lo mismo con las variables auxiliares que esta generando..
				///creo que con modificar solamente los aux 1 o aux2 se acomodan las axiliares
			}
		}
	}
}
