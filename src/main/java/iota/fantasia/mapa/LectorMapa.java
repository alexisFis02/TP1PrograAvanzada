package iota.fantasia.mapa;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.mapa.records.Camino;
import iota.fantasia.mapa.records.DatosArchivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class LectorMapa {

    public static DatosArchivo leerArchivo(String rutaArchivo) throws IOException {
        validarArchivo(rutaArchivo);
        try (var br = Files.newBufferedReader(Path.of(rutaArchivo))) {
            var cantidadPoblados = leerCantidadPoblados(br);
            var poblados = leerPoblados(br, cantidadPoblados);
            var ruta = leerRuta(br);
            var inicio = ruta[0];
            var destino = ruta[1];
            var caminos = leerCaminos(br);


            return new DatosArchivo(inicio, destino, poblados, caminos);
        }
    }

    private static void validarArchivo(String rutaArchivo) throws IOException {
        if (!Files.exists(Path.of(rutaArchivo))) {
            throw new IOException("El archivo no existe: " + rutaArchivo);
        }
    }

    private static int leerCantidadPoblados(BufferedReader br) throws IOException {
        String primeraLinea = br.readLine();
        if (primeraLinea == null || primeraLinea.isEmpty()) {
            throw new IOException("Archivo vacío");
        }
        int cantidadPoblados;
        try {
            cantidadPoblados = Integer.parseInt(primeraLinea);
            if (cantidadPoblados <= 0) {
                throw new IOException("La cantidad de poblados debe ser positiva: " + primeraLinea);
            }
            return cantidadPoblados;
        } catch (NumberFormatException e) {
            throw new IOException("Cantidad de poblados inválida: " + primeraLinea);
        }
    }

    private static Set<Poblado> leerPoblados(BufferedReader br, int cantidadPoblados) throws IOException {
        var poblados = new HashSet<Poblado>();
        int id, habitantes;
        Bando bando;
        Raza raza;

        for (int i = 0; i < cantidadPoblados; i++) {
            String[] datos = br.readLine().split(" ");
            if (datos.length != 4) {
                throw new IOException("Formato inválido de datos de poblado: " + String.join(" ", datos));
            }

            try {
                id = Integer.parseInt(datos[0]);

                habitantes = Integer.parseInt(datos[1]);

                raza = switch (datos[2]) {
                    case "Wrives" -> Raza.WRIVES;
                    case "Reralopes" -> Raza.RERALOPES;
                    case "Radaiteran" -> Raza.RADAITERAN;
                    case "Nortaichian" -> Raza.NORTAICHIAN;
                    default -> throw new IOException("Raza inválida: " + datos[2]);
                };
                bando = switch (datos[3]) {
                    case "aliado" -> Bando.ALIADO;
                    case "enemigo" -> Bando.ENEMIGO;
                    case "propio" -> Bando.PROPIO;
                    default -> throw new IOException("Bando inválido: " + datos[3]);
                };

                poblados.add(new Poblado(id, habitantes, raza, bando));

            } catch (NumberFormatException e) {
                throw new IOException("Datos numéricos inválidos en el poblado: " + String.join(" ", datos));
            }
        }
        return poblados;
    }

    private static int[] leerRuta(BufferedReader br) throws IOException {
        String[] ruta = br.readLine().split(" -> ");
        if (ruta.length != 2) {
            throw new IOException("Formato de ruta inválido: " + Arrays.toString(ruta));
        }
        try {
            int inicio = Integer.parseInt(ruta[0]);
            int objetivo = Integer.parseInt(ruta[1]);
            return new int[]{inicio, objetivo};
        } catch (NumberFormatException e) {
            throw new IOException("Datos numéricos inválidos en la ruta: " + String.join(" -> ", ruta));
        }
    }

    private static List<Camino> leerCaminos(BufferedReader br) throws IOException {
        String linea;
        var caminos = new ArrayList<Camino>();
        while ((linea = br.readLine()) != null) {
            var datos = linea.split(" ");
            if (datos.length != 3) {
                throw new IOException("Formato invalido de camino: " + linea);
            }

            try {
                int origen = Integer.parseInt(datos[0]);
                int destino = Integer.parseInt(datos[1]);
                int distancia = Integer.parseInt(datos[2]);

                if (distancia <= 0) {
                    throw new IOException("La distanciaEnTiempo debe ser positiva: " + linea);
                }
                if (origen == destino) {
                    throw new IOException("El origen y destino no pueden ser iguales: " + linea);
                }

                caminos.add(new Camino(origen, destino, distancia));
            } catch (NumberFormatException e) {
                throw new IOException("Datos numéricos invalidos en el camino: " + linea);
            }
        }

        return caminos;
    }
}