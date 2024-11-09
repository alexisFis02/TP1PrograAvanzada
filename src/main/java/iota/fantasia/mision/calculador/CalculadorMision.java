package iota.fantasia.mision.calculador;

import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo;
import iota.fantasia.ejercito.enums.Bando;
import java.util.ArrayList;
import java.util.List;

public class CalculadorMision {
    private static final int KILOMETROS_POR_DIA = 10;
    private final Mapa mapa;
    private final List<Poblado> rutaOptima;
    private final Ejercito ejercito;
    private final Poblado origen;
    private final Poblado destino;

    public CalculadorMision(Mapa mapa, Poblado origen, Poblado destino, Ejercito ejercitoInicial) {
        this.mapa = mapa;
        this.origen = origen;
        this.destino = destino;
        this.ejercito= ejercitoInicial;
        this.rutaOptima = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, origen, destino);
    }

    public boolean esMisionFactible() {
        Ejercito ejercitoSimulado = simularRuta();
        return ejercitoSimulado != null && ejercitoSimulado.tieneUnidadesVivas();
    }

    public int calcularGuerrerosFinales() {
        Ejercito ejercitoFinal = simularRuta();
        if (ejercitoFinal == null) return 0;
        return ejercitoFinal.getUnidades().size();
    }

    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        
        // Sumar tiempo de viaje entre poblados
        for (int i = 0; i < rutaOptima.size() - 1; i++) {
            Poblado actual = rutaOptima.get(i);
            Poblado siguiente = rutaOptima.get(i + 1);
            int distancia = mapa.obtenerDistancia(actual, siguiente);
            tiempoTotal += Math.ceil((double) distancia / KILOMETROS_POR_DIA);
        }
        
        // Sumar un día por cada batalla o descanso
        for (Poblado poblado : rutaOptima) {
            if (poblado.getBando() != Bando.PROPIO) {
                tiempoTotal++;
            }
        }
        
        return tiempoTotal;
    }

    private Ejercito simularRuta() {
        Ejercito ejercitoActual = new Ejercito(new ArrayList<>(ejercito.getUnidades()));

        for (Poblado poblado : rutaOptima) {
            switch (poblado.getBando()) {
                case PROPIO:
                case ALIADO:
                    ejercitoActual.descansar();
                    ejercitoActual.sumarPobladoAlEjecito(poblado);
                    break;
                case ENEMIGO:
                    if(!ejercitoActual.atacar(poblado.getEjercito())){
                        return null;
                    }
                    break;
                default:
                    throw new IllegalStateException("Bando no reconocido: " + poblado.getBando());
            }
        }
        return ejercitoActual;
    }
} 