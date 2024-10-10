package iota.fantasia.ejercito;

import iota.fantasia.ejercito.unidad.Unidad;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Ejercito extends Atacable {
    private List<Atacable> unidades;

    public Ejercito(Atacable unidades) {
        this.unidades.add(unidades);
    }

    public void agregarUnidad(Atacable unidad) {
    	switch(unidad.getBando()) {
    		case ALIADO:
    			concatenarPrincipio(unidad);
    			break;
    		case PROPIO:
    			concatenarFinal(unidad);
    			break;
    		case ENEMIGO:
    			concatenarFinal(unidad);
    			break;
    	}
    }
    
    public void concatenarPrincipio(Atacable unidad) {
    	List<Atacable> auxiliar = new ArrayList<Atacable>();
    	auxiliar.add(unidad);
    	auxiliar.addAll(this.unidades);
    	this.unidades = auxiliar;
    }
    
    public void concatenarFinal(Atacable unidad) {
    	this.unidades.add(unidad);
    }

    public void atacar(Atacable enemigo) {
        // TODO: 
    }
    
    public void recibirAtaque(int danio) {
    	
    }
    
    public void descansar() {
        // TODO: completar
    }

    public boolean tieneUnidadesVivas() {
        // TODO: completar
    }

    
}

ejercito ejercito aliado[u1,u2,u3];ejercito propio[u4,u5,u6];[u1,u2,u3,u4,u5,u6,u7,u8,u9]

ataco,ordeno[u1,u2,u3,u7,u8,u9,u4,u5,u6]

ejercito aliado[u7,u8,u9]