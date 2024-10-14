package ejercito;

import java.util.ArrayList;

public class Ejercito implements Atacable{
	private ArrayList<Unidad> unidades;

    public Ejercito(ArrayList<Unidad> unidades) {
        this.unidades = unidades;
    }

    public void agregarUnidad(Unidad unidad) {
        // TODO: completar
    }

    public void atacar(Ejercito enemigo) {
        // TODO: completar

    }

    public boolean tieneUnidadesVivas() {
        return true;
    }

    public void descansar() {
        // TODO: completar
    }
    
    public void recibirAtaque(int danio) {
    	
    }
}
