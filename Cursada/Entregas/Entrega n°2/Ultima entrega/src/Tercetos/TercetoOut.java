package Tercetos;

import compilador.ParserVal;

public class TercetoOut extends Terceto {

	public TercetoOut(ParserVal primero, int pos) {
		super("OUT", primero, null, pos);
	}
		
	public String getCodigo()
	{
		
		String nombre=this.primero;
		
		//Saco las " del principio y fin
		String soloNombre = nombre.substring(1, nombre.length()-1);
		//Borro los espacios en blanco
		soloNombre = soloNombre.replaceAll("\\s+","_");
		//Reviso que no haya letras con acentos..
		//Si las hay las remplazo por la letra sin acento
		if(soloNombre.contains("�")) {
			soloNombre = soloNombre.replaceAll("�","a");
		}
		if(soloNombre.contains("�")) {
			soloNombre = soloNombre.replaceAll("�","e");
		}
		if(soloNombre.contains("�")) {
			soloNombre = soloNombre.replaceAll("�","i");
		}
		if(soloNombre.contains("�")) {
			soloNombre = soloNombre.replaceAll("�","o");
		}
		if(soloNombre.contains("�")) {
			soloNombre = soloNombre.replaceAll("�","u");
		}
		
		
		String aux = soloNombre + " DB " + nombre + ", 0\n";
		if(!this.generador.delcaracionesOutContains(aux)) {
			this.generador.setDeclaracionesOut(aux);	
		}
		
		String label = "";
		if(this.marcaAntes || this.generador.getLabels().contains(this.getPos())) {
			if(this.generador.getSintactico().existeNombreMarca()) {
				label = this.generador.getSintactico().getNombreMarca() + " proc\n";
				this.generador.getSintactico().removeNombreMarca();
			}
			else {
				label = "Label" + (this.getPos()) + ":\n";
				this.marcaAntes = false;	
			}
		}
		String labelDesp = "";
		if(this.marcaDesp) {
			labelDesp = "Label" + (this.getPos()+1) + ":\n";
			this.marcaDesp = false;
		}
		/*
		String s="1,0e308";
		String[] valor=s.split("e");
		Double d=Double.valueOf(valor[0]);
		int pot=Integer.valueOf(valor[1]);
		Double result = Math.pow(d, pot);
		System.out.println(result);
*/
		if(!label.equals("") && this.generador.getUltimaLinea().equals(label.substring(0, label.length()-1))) {
			label = "";
		}

		
		return label + "invoke MessageBox, NULL, addr " + soloNombre +", addr " + soloNombre + ", MB_OK\n" + labelDesp; 
	}
}