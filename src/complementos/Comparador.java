package complementos;

import java.util.Comparator;

import Tercetos.Terceto;

public class Comparador implements Comparator<Terceto> {

	@Override
	public int compare(Terceto t1, Terceto t2) {
		return new Integer(t1.compareTo(t2));
	}

}
