package iota.fantasia.mapa;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {
    private static Mapa instancia;
    private final Map<Integer, Poblado> poblados;
    private final Map<Integer, Map<Integer, Integer>> distancias;

    private Mapa(List<Poblado> poblados, List<Camino> caminos) {
        this.poblados = new HashMap<>();
        this.distancias = new HashMap<>();
        
        // Inicializar poblados
        poblados.forEach(p -> this.poblados.put(p.getId(), p));
        
        // Inicializar matriz de distancias
        caminos.forEach(c -> {
            distancias.computeIfAbsent(c.getOrigen().getId(), k -> new HashMap<>())
                     .put(c.getDestino().getId(), c.getDistancia());
            distancias.computeIfAbsent(c.getDestino().getId(), k -> new HashMap<>())
                     .put(c.getOrigen().getId(), c.getDistancia());
        });
    }

    public static synchronized Mapa getInstancia(List<Poblado> poblados, List<Camino> caminos) {
        if (instancia == null) {
            instancia = new Mapa(poblados, caminos);
        }
        return instancia;
    }

    public int obtenerDistancia(Poblado origen, Poblado destino) {
        return distancias.get(origen.getId())
                        .getOrDefault(destino.getId(), Integer.MAX_VALUE);
    }

    public Poblado obtenerPoblado(int id) {
        return poblados.get(id);
    }

    public Collection<Poblado> obtenerPoblados() {
        return poblados.values();
    }
}