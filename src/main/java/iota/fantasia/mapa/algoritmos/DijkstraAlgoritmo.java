package iota.fantasia.mapa.algoritmos;

import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.records.Camino;

import java.util.*;

public class DijkstraAlgoritmo {
    public static ResultadoCamino encontrarCaminoMasCorto(Mapa mapa, Poblado origen, Poblado destino) {
        Map<Poblado, Integer> distancias = new HashMap<>();
        Map<Integer, Integer> previos = new HashMap<>();
        Set<Poblado> visitados = new HashSet<>();
        PriorityQueue<NodoCamino> cola = new PriorityQueue<>();

        // Inicializar distancias con infinito
        for (Poblado poblado : mapa.getPoblados().values()) {
            distancias.put(poblado, Integer.MAX_VALUE);
        }

        // Distancia al origen es 0
        distancias.put(origen, 0);
        cola.offer(new NodoCamino(origen, 0));

        while (!cola.isEmpty()) {
            Poblado actual = cola.poll().poblado();

            if (actual.equals(destino)) {
                break;
            }

            if (visitados.contains(actual)) {
                continue;
            }

            visitados.add(actual);

            // Explorar vecinos
            for (Camino camino : actual.getCaminos()) {
                Poblado pobladoVecino = mapa.getPoblados().get(camino.destino());
                int distanciaVecino = camino.distancia();

                int nuevaDistancia = distancias.get(actual) + distanciaVecino;

                if (nuevaDistancia < distancias.get(pobladoVecino)) {
                    distancias.put(pobladoVecino, nuevaDistancia);
                    previos.put(pobladoVecino.getId(), actual.getId());
                    cola.offer(new NodoCamino(pobladoVecino, nuevaDistancia));
                }
            }
        }

        List<Poblado> camino = reconstruirCamino(previos, origen, destino, mapa);
        int distanciaTotal = distancias.get(destino);

        return new ResultadoCamino(camino, distanciaTotal);
    }

    private static List<Poblado> reconstruirCamino(Map<Integer, Integer> previos,
                                                   Poblado origen,
                                                   Poblado destino,
                                                   Mapa mapa) {
        List<Poblado> camino = new ArrayList<>();
        Poblado actual = destino;

        // Si no hay camino al destino
        if (!previos.containsKey(destino.getId())) {
            return camino;
        }

        // Reconstruir el camino desde el destino hacia el origen
        while (actual != null) {
            camino.add(0, actual);
            actual = mapa.getPoblados().get(previos.get(actual.getId()));
        }

        return camino;
    }

    public record NodoCamino(Poblado poblado, int distancia) implements Comparable<NodoCamino> {
        @Override
        public int compareTo(NodoCamino otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }

    public record ResultadoCamino(List<Poblado> camino, int distanciaTotal) {
    }
} 