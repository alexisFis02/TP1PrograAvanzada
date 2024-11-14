package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.unidad.Nortaichan;
import iota.fantasia.ejercito.unidad.Radaiteran;
import iota.fantasia.ejercito.unidad.Reralopes;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.ejercito.unidad.Wrives;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Ejercito extends Atacable {

	private final Queue<Unidad> unidadesPropias;
	private final Queue<Unidad> unidadesAliadas;
	private Unidad ultimaHerida;

	public Ejercito(ArrayList<Unidad> unidades) {
		if (unidades.isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un ejercito sin unidades.");
		}
		this.unidadesPropias = new LinkedList<>(unidades);
		this.unidadesAliadas = new LinkedList<>();
		this.ultimaHerida = null;
	}

	public Ejercito(int cantidad, Raza raza, Bando bando) {
		this.unidadesPropias = new LinkedList<>();
		this.unidadesAliadas = new LinkedList<>();
		this.ultimaHerida = null;
		agregarUnidades(cantidad, raza, bando);
	}

	public void agregarUnidad(Unidad unidad) {
		unidadesPropias.add(unidad);
	}

	public void agregarUnidadesPropias(int cantidad, Raza raza) {
		for (int i = 0; i < cantidad; i++) {
			unidadesPropias.add(UnidadFactory.crearUnidad(raza));
		}
	}

	public void agregarUnidadesAliadas(int cantidad, Raza raza) {
		for (int i = 0; i < cantidad; i++) {
			unidadesAliadas.add(UnidadFactory.crearUnidad(raza));
		}
	}

	public void agregarUnidades(int cantidad, Raza raza, Bando bando) {
		switch (bando) {
		case PROPIO, ENEMIGO -> agregarUnidadesPropias(cantidad, raza);
		case ALIADO -> agregarUnidadesAliadas(cantidad, raza);
        }
	}

	@Override
	public void atacar(Atacable enemigo) {
		if (!unidadesAliadas.isEmpty()) {
			unidadesAliadas.peek().atacar(enemigo);
		} else if (!unidadesPropias.isEmpty()) {
			unidadesPropias.peek().atacar(enemigo);
		} else {
			ultimaHerida.atacar(enemigo);
		}
	}

	public boolean estaVivo() {
		return (!unidadesPropias.isEmpty() || !unidadesAliadas.isEmpty())
				|| (ultimaHerida != null && ultimaHerida.estaVivo());
	}

	public void descansar() {
		int hashPrimero;
		if (!unidadesPropias.isEmpty()) {
			hashPrimero = unidadesPropias.peek().hashCode();
			unidadesPropias.peek().descansar();
			unidadesPropias.add(unidadesPropias.poll());
			while (hashPrimero != unidadesPropias.peek().hashCode()) {
				unidadesPropias.peek().descansar();
				unidadesPropias.add(unidadesPropias.poll());
			}
		}
		if (!unidadesAliadas.isEmpty()) {
			hashPrimero = unidadesAliadas.peek().hashCode();
			unidadesAliadas.peek().descansar();
			unidadesAliadas.add(unidadesAliadas.poll());
			while (hashPrimero != unidadesAliadas.peek().hashCode()) {
				unidadesAliadas.peek().descansar();
				unidadesAliadas.add(unidadesAliadas.poll());
			}
		}

		ultimaHerida.descansar();
	}

	@Override
	public void recibirAtaque(int danio) {
		if (!unidadesAliadas.isEmpty()) {
			unidadesAliadas.peek().recibirAtaque(danio);
			if (!unidadesAliadas.peek().estaVivo())
				unidadesAliadas.poll();
		} else if (!unidadesPropias.isEmpty()) {
			unidadesPropias.peek().recibirAtaque(danio);
			if (!unidadesPropias.peek().estaVivo())
				unidadesPropias.poll();
		} else {
			ultimaHerida.recibirAtaque(danio);
		}
	}

	public void actualizarUltimoHerido(){
		Unidad ultimoEnRecibirDanio;
		if (!unidadesAliadas.isEmpty()) {
			ultimoEnRecibirDanio = unidadesAliadas.peek();
		} else if (!unidadesPropias.isEmpty()) {
			ultimoEnRecibirDanio = unidadesPropias.peek();
		} else {
			ultimoEnRecibirDanio = ultimaHerida;
		}

		// reincorporo al ejercito
		if(ultimaHerida.bando == Bando.PROPIO){
			unidadesPropias.add(ultimaHerida);
		}else if(ultimaHerida.bando == Bando.ALIADO){
			unidadesAliadas.add(ultimaHerida);
		}
		ultimaHerida = ultimoEnRecibirDanio;
	}

	public int contarUnidadesFinales() {
		return unidadesAliadas.size() + unidadesPropias.size() + (ultimaHerida != null && ultimaHerida.estaVivo() ? 1 : 0);
	}
}