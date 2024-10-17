package ejercito;

import colaDePrioridad.Monticulo;

public class Ejercito implements Atacable{
	private Monticulo unidades;
	private int tamEjercito;
	
    public Ejercito(int tam) {
        this.unidades = new Monticulo(tam);
    }

    public void agregarUnidad(Unidad unidad) {
    	this.unidades.agregarElementoMonticulo(unidad);
    }
    
    
    public Unidad devolverUnidad() {
    	Unidad unidad = (Unidad)this.unidades.removerElementoMonticulo();
    	
    	return unidad;
    }


    public void atacar(Ejercito enemigo) {
        // TODO: completar

    }

    public boolean tieneUnidadesVivas() {
        return this.unidades.monticuloVacio();
    }

    public void descansar() {
        // TODO: completar
    }
    
    public void recibirAtaque(int danio) {
    	
    }
    
    public void mostrarEjecito() {
    	this.unidades.mostrarMonticulo();
    }
}
