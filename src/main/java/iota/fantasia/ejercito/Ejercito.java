package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.unidad.Unidad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Ejercito extends Atacable {

	//reemplazar todos por colas
	private final ArrayList<Unidad> unidades;
	//private final ArrayList<Unidad> unidades_aliadas;
	//private final Unidad ultima_herida;

	public Ejercito(ArrayList<Unidad> unidades) {
		if (unidades.isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un ejercito sin unidades.");
		}
		this.unidades = new ArrayList<>(unidades);
	}

	public Ejercito(int cantidad, Raza raza) {
		this.unidades = new ArrayList<>();
		agregarUnidades(cantidad, raza);
	}

	public void agregarUnidad(Unidad unidad) {
		unidades.add(unidad);
	}

	public void agregarUnidades(int cantidad, Raza raza) {
		for (int i = 0; i < cantidad; i++) {
			unidades.add(UnidadFactory.crearUnidad(raza));
		}
		//deberia ser al unidades_aliados pero fallaria al crearlo la primera vez RESOLVER
	}

	public void atacar(Atacable enemigo) {
		Unidad atacante = unidades.stream().filter(Unidad::estaVivo).findFirst().orElse(null);
		atacante.atacar(enemigo);

	}

	public boolean estaVivo() {
		return !unidades.isEmpty() && unidades.stream().anyMatch(Unidad::estaVivo);
	}

	public void descansar() {
		//unidades.stream().filter(Unidad::estaVivo).forEach(Unidad::descansar);
		unidades.stream().forEach(Unidad::descansar);
		//deben descansar ambos ejercitos
		//unidades_aliadas.stream().forEach(Unidad::descansar);
		//ultima_herida.descansar;
	}

	@Override
	public void recibirAtaque(int danio) {
//		// El ejército distribuye el daño entre todas las unidades vivas
//		int unidadesVivas = (int) unidades.stream().filter(Unidad::estaVivo).count();
//		if (unidadesVivas > 0) {
//			int danioPorUnidad = danio / unidadesVivas;
//			unidades.stream().filter(Unidad::estaVivo).forEach(unidad -> unidad.recibirAtaque(danioPorUnidad));
//		}
		
		
		
		//el ejercito no sabe recibir ataques
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public double getPorcentajeVidaTotal() {
		if (unidades.isEmpty())
			return 0.0;

		double vidaActual = unidades.stream().mapToDouble(Unidad::getSalud).sum();

		double vidaMaxima = unidades.stream().mapToDouble(Unidad::getSaludMaxima).sum();

		return (vidaActual / vidaMaxima) * 100;
	}

	public List<Unidad> getUnidadesVivas() {
		return unidades.stream().filter(Unidad::estaVivo).toList();
	}

}

/*
 * ejercito ejercito aliado[u1,u2,u3];ejercito
 * propio[u4,u5,u6];[u1,u2,u3,u4,u5,u6,u7,u8,u9]
 * 
 * ataco,ordeno[u1,u2,u3,u7,u8,u9,u4,u5,u6]
 * 
 * ejercito aliado[u7,u8,u9]
 */