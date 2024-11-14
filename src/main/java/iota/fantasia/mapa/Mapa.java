package iota.fantasia.mapa;

import iota.fantasia.mapa.records.Camino;
import iota.fantasia.mapa.records.DatosArchivo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mapa {
    private static Mapa instance;
    private final Map<Integer, Poblado> poblados;

    private Mapa(Map<Integer, Poblado> poblados) {
        this.poblados = poblados;
    }

    private Mapa(DatosArchivo datosArchivo) {
        this.poblados = new HashMap<>();
        armarMapaPoblados(datosArchivo.poblados(), datosArchivo.caminos());
    }

    public static Mapa getInstance(DatosArchivo datosArchivo) {
        if (instance == null) {
            instance = new Mapa(datosArchivo);
        }
        return instance;
    }

    public static Mapa getInstance(Map<Integer, Poblado> poblados) {
        if (instance == null) {
            instance = new Mapa(poblados);
        }
        return instance;
    }


    private void armarMapaPoblados(Set<Poblado> poblados, List<Camino> caminos) {
        // agregar los poblados
        for (Poblado poblado : poblados) {
            this.poblados.put(poblado.getId(), poblado);
        }

        // agregar las rutas
        for (Camino camino : caminos) {
            this.poblados.get(camino.origen()).agregarCamino(camino.destino(), camino.distanciaEnTiempo());
            this.poblados.get(camino.destino()).agregarCamino(camino.origen(), camino.distanciaEnTiempo());
        }
    }

    public Map<Integer, Poblado> getPoblados() {
        return poblados;
    }

    public Poblado obtenerPoblado(int id) {
        return poblados.get(id);
    }

    @Override
    public String toString() {
        return "Mapa [poblados=" + poblados + "]";
    }
}
