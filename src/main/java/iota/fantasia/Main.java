package iota.fantasia;

import iota.fantasia.batalla.Batalla;
import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.mapa.LectorMapa;
import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.records.DatosArchivo;
import iota.fantasia.mapa.algoritmos.DijkstraAlgoritmo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int KILOMETROS_POR_DIA = 10;

    public static void main(String[] args) {
        String pathArchivoMapa;
        System.out.println("Seleccione el archivo de mapa (1-7): ");
        pathArchivoMapa = "mapa" + new java.util.Scanner(System.in).nextInt() + ".txt";
        try {
            DatosArchivo datos = LectorMapa.leerArchivo(pathArchivoMapa);
            Mapa mapa = Mapa.getInstance(datos);
            Poblado origen = mapa.obtenerPoblado(datos.inicio());
            Poblado destino = mapa.obtenerPoblado(datos.destino());

            // Obtener el camino óptimo
            DijkstraAlgoritmo.ResultadoCamino resultado = DijkstraAlgoritmo.encontrarCaminoMasRapido(mapa, origen, destino);
            List<Poblado> rutaOptima = resultado.camino();
            int tiempoTotal = (int) Math.ceil(resultado.distanciaTotal() / (double) KILOMETROS_POR_DIA);

            // Simular la ruta
            var ejercitoActual = new Ejercito(origen.getHabitantes(), origen.getRaza(), Bando.PROPIO);
            var caminoAlcanzable = new ArrayList<Poblado>();
            caminoAlcanzable.add(origen);
            var logAcciones = new StringBuilder();

            logAcciones.append("Iniciando misión desde poblado ").append(origen.getId()).append(" con ")
                    .append(origen.getHabitantes()).append(" guerreros. \n");

            /*
            * Se recorre la ruta más rapida calculada por el algoritmo de Dijkstra
            * */
            for (int i = 1; i < rutaOptima.size(); i++) {
                Poblado poblado = rutaOptima.get(i);
                caminoAlcanzable.add(poblado);
                logAcciones.append("\nLlegando a poblado ").append(poblado.getId()).append(" (").append(poblado.getBando())
                        .append(")\n");

                switch (poblado.getBando()) {
                    case ALIADO: // Descansar y reclutar en poblado aliado
                        ejercitoActual.descansar();
                        ejercitoActual.agregarUnidades(poblado.getHabitantes() / 2, poblado.getRaza(), Bando.ALIADO);
                        logAcciones.append("Descansando y reclutando en poblado aliado: ").append(poblado).append("%)\n");
                        break;
                    case ENEMIGO: // Batalla con poblado enemigo
                        Ejercito ejercitoEnemigo = poblado.generarEjecitoConBando(Bando.ENEMIGO);
                        logAcciones.append("¡Batalla! Enemigos: ").append(poblado.getRaza()).append("\n");

                        Batalla batalla = new Batalla(ejercitoActual, ejercitoEnemigo);
                        if (batalla.simularBatalla()) {
                            ejercitoActual.actualizarUltimoHerido();
                            logAcciones.append("Victoria!\n");
                        } else {
                            logAcciones.append("Derrota! Nuestro ejercito ha sido destruido\n");
                            caminoAlcanzable.remove(caminoAlcanzable.size() - 1); // Remover el último poblado alcanzado
                        }
                        break;
                    default:
                        throw new IllegalStateException("Bando no reconocido: " + poblado.getBando());
                }
            }

            // Muestro resultados finales
            if (ejercitoActual.estaVivo()) {
                System.out.println("Guerreros sobrevivientes: " + ejercitoActual.contarUnidadesFinales());
                System.out.println("Tiempo total: " + tiempoTotal + " días");
                System.out.println("Ruta más rápida: " + obtenerDetalleRuta(rutaOptima));
                System.out.println(logAcciones);
            } else {
                System.out.println("La misión no es factible");
            }

        } catch (IOException e) {
            System.out.println("Error al crear la misión: " + e.getMessage());
        }
    }

    private static String obtenerDetalleRuta(List<Poblado> rutaOptima) {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Ruta más rápida: ");
        for (int i = 0; i < rutaOptima.size(); i++) {
            Poblado poblado = rutaOptima.get(i);
            detalle.append(poblado.getId());
            if (i < rutaOptima.size() - 1) {
                detalle.append(" -> ");
            }
        }
        return detalle.toString();
    }
}