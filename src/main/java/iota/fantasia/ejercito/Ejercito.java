package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.unidad.Unidad;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

	public void agregarUnidadesPropias(int cantidad, Raza raza) {
		for (int i = 0; i < cantidad; i++) {
			unidadesPropias.add(UnidadFactory.crearUnidad(raza,Bando.PROPIO));
		}
	}

	public void agregarUnidadesAliadas(int cantidad, Raza raza) {
		for (int i = 0; i < cantidad; i++) {
			unidadesAliadas.add(UnidadFactory.crearUnidad(raza, Bando.ALIADO));
		}
	}

	public void agregarUnidades(int cantidad, Raza raza, Bando bando) {
		switch (bando) {
		case PROPIO -> agregarUnidadesPropias(cantidad, raza);
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
		descansarUnidades(unidadesPropias);
		descansarUnidades(unidadesAliadas);
		if (ultimaHerida != null) {
			ultimaHerida.descansar();
		}
	}

	private void descansarUnidades(Queue<Unidad> unidades) {
		if (unidades.isEmpty()) return;
		
		int size = unidades.size();
		for (int i = 0; i < size; i++) {
			Unidad unidad = unidades.poll();
            if (unidad != null) {
                unidad.descansar();
				unidades.add(unidad);
            }
		}
	}

	@Override
	public void recibirAtaque(int danio) {
		var unidadesObjetivo = !unidadesAliadas.isEmpty() ? unidadesAliadas : unidadesPropias;
		
		if (!unidadesObjetivo.isEmpty()) {
			var unidadActual = unidadesObjetivo.peek();
			unidadActual.recibirAtaque(danio);
			
			if (!unidadActual.estaVivo()) {
				unidadesObjetivo.poll();
			}
		} else if (ultimaHerida != null) {
			ultimaHerida.recibirAtaque(danio);
		}
	}

	public void actualizarUltimoHerido(){
		Unidad ultimoEnRecibirDanio;
		if (!unidadesAliadas.isEmpty()) {
			ultimoEnRecibirDanio = unidadesAliadas.poll();
		} else if (!unidadesPropias.isEmpty()) {
			ultimoEnRecibirDanio = unidadesPropias.poll();
		} else {
			ultimoEnRecibirDanio = ultimaHerida;
		}

		// reincorporo al ejercito la ultima unidad herida de la batalla anterior
		if(ultimaHerida != null && ultimaHerida.estaVivo()){
			switch (ultimaHerida.bando) {
				case PROPIO -> unidadesPropias.add(ultimaHerida);
				case ALIADO -> unidadesAliadas.add(ultimaHerida);
				case ENEMIGO -> {}
			}
		}

		ultimaHerida = ultimoEnRecibirDanio;
	}

	public int contarUnidadesFinales() {
		return unidadesAliadas.size() + unidadesPropias.size()
				+ (ultimaHerida != null && ultimaHerida.estaVivo() ? 1 : 0);
	}
}