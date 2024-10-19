package colaDePrioridad;

import ejercito.Bando;
import ejercito.Estado;
import ejercito.Unidad;

public class ComparadorUnidad implements Comparador<Unidad>{
	@Override
	 public int comparar(Unidad unidad1, Unidad unidad2) {
		if(unidad1.verBando() == Bando.ALIADO && unidad2.verBando() == Bando.PROPIO) {
			return -1;
		}
		
		if(unidad1.verBando() == unidad2.verBando()) {
			if(unidad1.verEstado() == Estado.SANO && 
					unidad2.verEstado() == Estado.HERIDO) {
				
				return -1;
			}
		}
		
		return 1;
    }
}
