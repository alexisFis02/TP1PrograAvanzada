package iota.fantasia.mapa.algoritmos;

import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.Mapa;

import java.util.*;

public class DijkstraAlgoritmo {
    public record NodoCamino(Poblado poblado, int distancia) implements Comparable<NodoCamino> {
        @Override
        public int compareTo(NodoCamino otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }

    public static List<Poblado> encontrarCaminoMasCorto(Mapa mapa, Poblado origen, Poblado destino) {
        Map<Poblado, Integer> distancias = new HashMap<>();
        Map<Poblado, Poblado> previos = new HashMap<>();
        PriorityQueue<NodoCamino> cola = new PriorityQueue<>();
        
        // Implementación del algoritmo de Dijkstra
        // ...
        
        return reconstruirCamino(previos, destino);
    }

    private static List<Poblado> reconstruirCamino(Map<Poblado, Poblado> previos, Poblado destino) {
        List<Poblado> camino = new ArrayList<>();
        // Reconstruir el camino desde el destino hacia el origen
        // ...
        return camino;
    }
} 