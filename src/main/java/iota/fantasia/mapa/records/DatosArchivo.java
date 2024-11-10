package iota.fantasia.mapa.records;

import iota.fantasia.mapa.Poblado;

import java.util.List;
import java.util.Set;

public record DatosArchivo(int inicio,
                           int destino,
                           Set<Poblado> poblados,
                           List<Camino> caminos) {
}
