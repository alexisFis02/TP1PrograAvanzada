package mision;

import ejercito.Ejercito;
import mapa.Camino;
import mapa.Mapa;
import mapa.Poblado;

public class Mision {
	private Camino camino;
	private Ejercito ejercito;
	
	public Mision(Mapa mapa, Poblado[] poblados, Poblado pobladoInicial, 
			Poblado pobladoFinal) {
		this.camino = new Camino(mapa, poblados, pobladoInicial, pobladoFinal);
	}
	
	public String cantidadDeGuerrerosFinales() {
		return "";
    	
    }

    public boolean esMisionFactible() {
    	this.camino.calcularCamino();
    	
    	Poblado primerPoblado = this.camino.devolverSiguientePoblado();
    	for(int i = 0; i < primerPoblado.devolverCantHabitantes(); i++) {
    		
    	}
    	
    	
    	return true;
    }

    public String calcularTiempoTotal() {
    	return "";
    }
    
    public void calcularRuta() {
    	this.camino.calcularCamino();
    }
    
    
    public int devolverCostoDelViaje() {
    	return this.camino.devolverCostoEnKilometros();
    }
    
    
    public void mostrarRuta() {
    	this.camino.mostrarCamino();
    }
}
