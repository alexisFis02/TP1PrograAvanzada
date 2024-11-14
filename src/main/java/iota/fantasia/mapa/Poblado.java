package iota.fantasia.mapa;

import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.mapa.records.Camino;

import java.util.ArrayList;
import java.util.List;

public class Poblado {
	private final int id;
	private final int habitantes;
	private final Raza raza;
	private final Bando bando;
	// private Ejercito ejercito;
	private final List<Camino> caminos;

	public Poblado(int id, int habitantes, Raza raza, Bando bando) {
		this.id = id;
		this.habitantes = habitantes;
		this.raza = raza;
		this.bando = bando;
		this.caminos = new ArrayList<>();
	}

	public Ejercito generarEjercito() {
		var unidades = new ArrayList<Unidad>();
		for (int i = 0; i < habitantes; i++) {
			unidades.add(UnidadFactory.crearUnidad(raza));
		}
		return new Ejercito(unidades);
	}

//    public Ejercito getEjercito() {
//        return ejercito;
//    }

	// Getters
	public int getId() {
		return id;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public Raza getRaza() {
		return raza;
	}

	public Bando getBando() {
		return bando;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

	public void agregarCamino(int destino, int distancia) {
		if (destino == this.id) {
			return;
		}
		caminos.add(new Camino(this.id, destino, distancia + 10));
	}

	public List<Camino> getCaminos() {
		return caminos;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
