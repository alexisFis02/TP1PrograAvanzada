package iota.fantasia.mision.calculador;

import iota.fantasia.ejercito.Bando;
import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo.ResultadoCamino;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo;
import iota.fantasia.batalla.Batalla;

import java.util.ArrayList;
import java.util.List;

public class CalculadorMision {
	private static final int KILOMETROS_POR_DIA = 10;
	private final List<Poblado> rutaOptima;
	private final Ejercito ejercitoFinal;
	private final Poblado origen;
	public static List<Poblado> caminoAlcanzable;
	public static StringBuilder logAcciones;
	private final int tiempoTotal;

	public CalculadorMision(Mapa mapa, int origen, int destino) {
		this.origen = mapa.obtenerPoblado(origen);
		Poblado destino1 = mapa.obtenerPoblado(destino);
		ResultadoCamino resultado = DijkstraAlgoritmo.encontrarCaminoMasRapido(mapa, this.origen, destino1);
		this.rutaOptima = resultado.camino();
		this.tiempoTotal = (int) Math.ceil(resultado.distanciaTotal() / (double) KILOMETROS_POR_DIA);
		CalculadorMision.caminoAlcanzable = new ArrayList<>();
		CalculadorMision.logAcciones = new StringBuilder();
		this.ejercitoFinal = simularRuta();
	}

	public boolean esMisionFactible() {
		return ejercitoFinal.estaVivo();
	}

	public int calcularGuerrerosFinales() {
		return ejercitoFinal.contarUnidadesFinales();
	}

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	private Ejercito simularRuta() {
		Ejercito ejercitoActual = new Ejercito(this.origen.getHabitantes(), this.origen.getRaza(), Bando.PROPIO);
		CalculadorMision.caminoAlcanzable.clear();
		CalculadorMision.caminoAlcanzable.add(origen);
		CalculadorMision.logAcciones.setLength(0);
		CalculadorMision.logAcciones.append("Iniciando mision desde poblado ").append(origen.getId()).append(" con ")
				.append(this.origen.getHabitantes()).append(" guerreros. \n");
		
		for (int i = 1; i < rutaOptima.size(); i++) {
			Poblado poblado = rutaOptima.get(i);
			CalculadorMision.caminoAlcanzable.add(poblado);

			CalculadorMision.logAcciones.append("\nLlegando a poblado ").append(poblado.getId()).append(" (").append(poblado.getBando())
					.append(")\n");
			
			switch (poblado.getBando()) {
			
			case ALIADO:
				ejercitoActual.descansar();
				ejercitoActual.agregarUnidades(poblado.getHabitantes() / 2, poblado.getRaza(), Bando.ALIADO);
				CalculadorMision.logAcciones.append("Descansando y reclutando en poblado aliado: ").append(poblado).append("%)\n");
				break;

			case ENEMIGO:
				Ejercito ejercitoEnemigo = poblado.generarEjercito();
				CalculadorMision.logAcciones.append("¡Batalla! Enemigos: ").append(poblado.getRaza()).append("\n");

				Batalla batalla = new Batalla(ejercitoActual, ejercitoEnemigo);
				if(batalla.simularBatalla()) {
					ejercitoActual.actualizarUltimoHerido();
					CalculadorMision.logAcciones.append("Victoria!\n");
				}else {
					CalculadorMision.logAcciones.append("Derrota! Nuestro ejercito ha sido destruido\n");
					CalculadorMision.caminoAlcanzable.removeLast();
				}

				break;
			default:
				throw new IllegalStateException("Bando no reconocido: " + poblado.getBando());
			}
		}
		return ejercitoActual;
	}

	public String obtenerDetalleRuta() {
		StringBuilder detalle = new StringBuilder();
		detalle.append("Ruta más rápida: ");

		for (int i = 0; i < rutaOptima.size(); i++) {
			Poblado poblado = rutaOptima.get(i);
			detalle.append(poblado.getId());
			if (i < rutaOptima.size() - 1) {
				detalle.append(" -> ");
			}
		}

		if (!esMisionFactible()) {
			detalle.append("\nLlegamos hasta: ");
			for (int i = 0; i < caminoAlcanzable.size(); i++) {
				Poblado poblado = caminoAlcanzable.get(i);
				detalle.append(poblado.getId());
				if (i < caminoAlcanzable.size() - 1) {
					detalle.append(" -> ");
				}
			}
		}

		return detalle.toString();
	}

	public String obtenerLogAcciones() {
		return CalculadorMision.logAcciones.toString();
	}
}