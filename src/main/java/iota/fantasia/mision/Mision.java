package iota.fantasia.mision;

import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.records.DatosArchivo;
import iota.fantasia.mision.calculador.CalculadorMision;

public class Mision {
	private final Mapa mapa;
	private final int inicio;
	private final int destino;
	private final CalculadorMision calculador;

	public Mision(DatosArchivo conf) {
		this.mapa = Mapa.getInstance(conf);
		this.inicio = conf.inicio();
		this.destino = conf.destino();
		calculador = new CalculadorMision(mapa, inicio, conf.destino());
	}

	public String cantidadDeGuerrerosFinales() {
		if (!esMisionFactible()) {
			return "La mision no es factible";
		}
		return "guerreros sobrevivientes: " + calculador.calcularGuerrerosFinales();
	}

	public boolean esMisionFactible() {
		return calculador.esMisionFactible();
	}

	public String calcularTiempoTotal() {
		if (!esMisionFactible()) {
			return "La misión no es factible";
		}
		int dias = calculador.getTiempoTotal();
		return "Tiempo total: " + dias + " días";
	}

	@Override
	public String toString() {
		StringBuilder pueblosInfo = new StringBuilder("Informacion de todos los pueblos:\n");
		
		pueblosInfo.append(" - Pueblo ").append(inicio).append(" (ORIGEN): ")
				.append(mapa.obtenerPoblado(inicio).getHabitantes()).append(" habitantes, Raza: ")
				.append(mapa.obtenerPoblado(inicio).getRaza()).append(", Bando: ")
				.append(mapa.obtenerPoblado(inicio).getBando()).append("\n");

		mapa.getPoblados().values().stream().filter(poblado -> poblado.getId() != inicio && poblado.getId() != destino)
				.forEach(poblado -> pueblosInfo.append(" - Pueblo ").append(poblado.getId()).append(": ")
						.append(poblado.getHabitantes()).append(" habitantes, Raza: ").append(poblado.getRaza())
						.append(", Bando: ").append(poblado.getBando()).append("\n"));
		pueblosInfo.append(" - Pueblo ").append(destino).append(" (DESTINO): ")
				.append(mapa.obtenerPoblado(destino).getHabitantes()).append(" habitantes, Raza: ")
				.append(mapa.obtenerPoblado(destino).getRaza()).append(", Bando: ")
				.append(mapa.obtenerPoblado(destino).getBando()).append("\n");

		return """
				%s
				misión:
				 - ¿Es factible? %s
				 - %s
				 - %s
				 - %s

				Log de acciones:
				%s
				""".formatted(pueblosInfo, esMisionFactible() ? "SI" : "NO", cantidadDeGuerrerosFinales(),
				calcularTiempoTotal(), calculador.obtenerDetalleRuta(), calculador.obtenerLogAcciones());
	}
}
