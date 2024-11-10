package iota.fantasia.mision.calculador;

import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo.ResultadoCamino;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo;

import java.util.ArrayList;
import java.util.List;

public class CalculadorMision {
    private static final int KILOMETROS_POR_DIA = 10;
    private final List<Poblado> rutaOptima;
    private final Ejercito ejercito;
    private final Ejercito ejercitoFinal;
    private final Poblado origen;
    private final List<Poblado> caminoAlcanzable;
    private final StringBuilder logAcciones;
    private final int tiempoTotal;

    public CalculadorMision(Mapa mapa, int origen, int destino) {
        this.origen = mapa.obtenerPoblado(origen);
        Poblado destino1 = mapa.obtenerPoblado(destino);
        this.ejercito = new Ejercito(this.origen.getHabitantes(), this.origen.getRaza());
        ResultadoCamino resultado = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, this.origen, destino1);
        this.rutaOptima = resultado.camino();
        this.tiempoTotal = (int) Math.ceil(resultado.distanciaTotal() / (double) KILOMETROS_POR_DIA);
        this.caminoAlcanzable = new ArrayList<>();
        this.logAcciones = new StringBuilder();
        this.ejercitoFinal = simularRuta();
    }

    public boolean esMisionFactible() {
        return ejercitoFinal != null && ejercitoFinal.tieneUnidadesVivas();
    }

    public int calcularGuerrerosFinales() {
        if (ejercitoFinal == null) return 0;
        return ejercitoFinal.getUnidades().size();
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    private Ejercito simularRuta() {
        Ejercito ejercitoActual = new Ejercito(new ArrayList<>(ejercito.getUnidades()));
        caminoAlcanzable.clear();
        caminoAlcanzable.add(origen);
        logAcciones.setLength(0);
        logAcciones.append("Iniciando mision desde poblado ").append(origen.getId())
                .append(" con ").append(ejercitoActual.getUnidades().size())
                .append(" guerreros (Salud: ").append(String.format("%.1f", ejercitoActual.getPorcentajeVidaTotal()))
                .append("%)\n");

        for (int i = 1; i < rutaOptima.size(); i++) {
            Poblado poblado = rutaOptima.get(i);
            caminoAlcanzable.add(poblado);

            logAcciones.append("\nLlegando a poblado ").append(poblado.getId())
                    .append(" (").append(poblado.getBando()).append(")\n");

            switch (poblado.getBando()) {
                case PROPIO:
                    ejercitoActual.descansar();
                    logAcciones.append("Descansando en poblado propio. Guerreros actuales: ")
                            .append(ejercitoActual.getUnidades().size())
                            .append(" (Salud: ").append(String.format("%.1f", ejercitoActual.getPorcentajeVidaTotal()))
                            .append("%)\n");
                    break;

                case ALIADO:
                    ejercitoActual.descansar();
                    int guerrerosAntes = ejercitoActual.getUnidades().size();
                    ejercitoActual.agregarUnidades(poblado.getHabitantes() / 2, poblado.getRaza());
                    logAcciones.append("Descansando y reclutando en poblado aliado. ")
                            .append("Guerreros antes: ").append(guerrerosAntes)
                            .append(", después: ").append(ejercitoActual.getUnidades().size())
                            .append(" (Salud: ").append(String.format("%.1f", ejercitoActual.getPorcentajeVidaTotal()))
                            .append("%)\n");
                    break;

                case ENEMIGO:
                    Ejercito ejercitoEnemigo = poblado.generarEjercito();
                    logAcciones.append("¡Batalla! Enemigos: ").append(ejercitoEnemigo.getUnidades().size())
                            .append(" (Salud: ").append(String.format("%.1f", ejercitoEnemigo.getPorcentajeVidaTotal()))
                            .append("%)")
                            .append(", Nuestros guerreros: ").append(ejercitoActual.getUnidades().size())
                            .append(" (Salud: ").append(String.format("%.1f", ejercitoActual.getPorcentajeVidaTotal()))
                            .append("%)\n");

                    boolean batallaEnCurso = true;
                    // int ronda = 1;

                    while (batallaEnCurso) {
//                        logAcciones.append("Ronda ").append(ronda).append(" - ");
                        ejercitoActual.atacar(ejercitoEnemigo);
//                        logAcciones.append("Enemigos restantes: ").append(ejercitoEnemigo.getUnidadesVivas().size())
//                                .append(" (Salud: ").append(String.format("%.1f", ejercitoEnemigo.getPorcentajeVidaTotal()))
//                                .append("%)")
//                                .append(", Nuestros guerreros: ").append(ejercitoActual.getUnidadesVivas().size())
//                                .append(" (Salud: ").append(String.format("%.1f", ejercitoActual.getPorcentajeVidaTotal()))
//                                .append("%)\n");
//
                        if (!ejercitoEnemigo.tieneUnidadesVivas()) {
                            batallaEnCurso = false;
                            logAcciones.append("Victoria!\n");
                            continue;
                        }

                        ejercitoEnemigo.atacar(ejercitoActual);
                        if (!ejercitoActual.tieneUnidadesVivas()) {
                            logAcciones.append("Derrota! Nuestro ejercito ha sido destruido\n");
                            caminoAlcanzable.removeLast();
                            return null;
                        }
                        // ronda++;
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
        return logAcciones.toString();
    }
} 