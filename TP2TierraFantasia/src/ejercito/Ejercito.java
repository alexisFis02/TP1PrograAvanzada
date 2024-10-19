package ejercito;

import colaDePrioridad.ColaDePrioridad;
import colaDePrioridad.Comparador;

public class Ejercito implements Atacable{
	private ColaDePrioridad ejercitoActual;
	private int tamEjercito;
	
	@SuppressWarnings("rawtypes")
	public Ejercito(int tamColaPri, Comparador comp) {
		this.ejercitoActual = new ColaDePrioridad(tamColaPri, comp);
        this.tamEjercito = 0;
    }

	public void agregarUnidad(Unidad unidad) {
    	this.ejercitoActual.agregarACola(unidad);
    	this.tamEjercito++;
    }
    
    
    public Unidad devolverUnidad() {
    	return this.ejercitoActual.sacarDeCola();
    }


    public void atacar(Ejercito enemigo) {
        // TODO: completar

    }

    public boolean tieneUnidadesVivas() {
        return this.ejercitoActual.colaVacia();
    }

    public void descansar() {
        // TODO: completar
    }
    
    public void recibirAtaque(int danio) {
    	
    }
    
    public void mostrarEjecito() {
    	this.ejercitoActual.mostrarColaPrioridad();
    }
    
    public int mostrarCantidadEjercito() {
    	return this.tamEjercito;
    }
}
