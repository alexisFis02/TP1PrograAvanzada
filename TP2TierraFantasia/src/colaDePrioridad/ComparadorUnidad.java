package colaDePrioridad;

import ejercito.Unidad;

public class ComparadorUnidad implements Comparador<Unidad>{
	@Override
	 public int comparar(Unidad unidad1, Unidad unidad2) {
		if((unidad1.getCondicionUnidad().getPrioridad() - 
				unidad2.getCondicionUnidad().getPrioridad()) < 0) {
			return -1;
		}
		else {
			return 1;
		}
    }
}
