package colaDePrioridad;

import ejercito.Unidad;

public class ColaDePrioridad {
	@SuppressWarnings("rawtypes")
	private Monticulo unidades;
	@SuppressWarnings({ "unused", "rawtypes" })
	private Comparador alineacionEjercito;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ColaDePrioridad(int tam, Comparador comp) {
    	this.alineacionEjercito = comp;
        this.unidades = new Monticulo(tam, comp);
    }
	
	
	@SuppressWarnings("unchecked")
	public void agregarACola(Unidad unidad) {
    	this.unidades.agregarElementoMonticulo(unidad);
    }
	
	
	public Unidad sacarDeCola() {
    	Unidad unidad = (Unidad)this.unidades.removerElementoMonticulo();
   
    	return unidad;
    }
	
	
	public boolean colaVacia() {
		return this.unidades.monticuloVacio();
	}
	
	
	public void mostrarColaPrioridad() {
		this.unidades.mostrarMonticulo();
	}
	
	
	
}
