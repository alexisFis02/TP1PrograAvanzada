package iota.fantasia.mapa;

import iota.fantasia.mapa.records.Camino;
import iota.fantasia.mapa.records.DatosArchivo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mapa {
    private final Map<Integer, Poblado> poblados;

    public Mapa(Map<Integer, Poblado> poblados) {
        this.poblados = poblados;
    }

    public Mapa(DatosArchivo datosArchivo) {
        this.poblados = new HashMap<>();
        armarMapaPoblados(datosArchivo.poblados(), datosArchivo.caminos());
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

    public int obtenerDistancia(int origen, int destino) {
        Poblado pobladoOrigen = poblados.get(origen);
        return pobladoOrigen.getCaminos().stream()
                .filter(camino -> camino.destino() == destino)
                .findFirst()
                .map(Camino::distanciaEnTiempo)
                .orElse(Integer.MAX_VALUE);
    }

	@Override
	public String toString() {
		return "Mapa [poblados=" + poblados + "]";
	}
}
