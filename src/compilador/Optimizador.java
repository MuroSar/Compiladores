package compilador;

import java.util.ArrayList;
import java.util.Collections;

import Tercetos.Terceto;
import complementos.Comparador;

public class Optimizador {
	
	public static ArrayList<Terceto> optimizacionRedundanciaSimple(ArrayList<Terceto> tercetos) {
		//trabajo sobre la lista de tercetos que me pasan asi modifico esa directamente..
		ArrayList<Terceto> tercetosOptimizados = new ArrayList<Terceto>();
		ArrayList<Terceto> tercetosAsignacion= new ArrayList<Terceto>();
		
		boolean agregoTerceto = true;
		
		//int pos = 0;
		//while (pos < tercetos.size()) {
		for(Terceto ter : tercetos) {
			agregoTerceto = true;
			//Terceto ter = tercetos.get(pos);
			
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
				tercetosAsignacion = new ArrayList<Terceto>();
			}
			else if (ter.getOperador().equals("==")){
				tercetosOptimizados.addAll(tercetosAsignacion);
				tercetosOptimizados.add(ter);	
				agregoTerceto = false;
				tercetosAsignacion = new ArrayList<Terceto>();
			}
			
			if(agregoTerceto) {
				tercetosOptimizados.add(ter);	
			}
			
			//pos++;
		}
		
		Collections.sort(tercetosOptimizados, new Comparador());
		return tercetosOptimizados;
	}

	private static void optimizar(ArrayList<Terceto> tercetosAsignacion) {
		int pos = 0;
		
		while (pos < tercetosAsignacion.size()) {
			//para cada terceto de la asignacion..
			Terceto terceto = tercetosAsignacion.get(pos);
			
			if(!terceto.isDeleted()) {
				//reviso los tercetos que quedan para ver si hay repetidos
				for (int i=pos+1; i<tercetosAsignacion.size(); i++) {
					if(terceto.equals(tercetosAsignacion.get(i))) {
						//si hay alguno repetido, marco el terceto como borrado y actualizo las referencias
						
						tercetosAsignacion.get(i).setDeleted(true);
						
						actualizaReferencias(tercetosAsignacion, terceto, tercetosAsignacion.get(i));
					}
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
					t.setPrimeroParserVal(new ParserVal(terceto));
				}
			}
			if(t.getSegundo().equals("[" + tercetoRepetido.getPos() + "]")) {
				t.setSegundo("[" + terceto.getPos() + "]");
				if(t.getSegundoParserVal().obj != null) {
					t.setSegundoParserVal(new ParserVal(terceto));
				}
			}
		}
	}
}
