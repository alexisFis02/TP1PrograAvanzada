package iota.fantasia.mapa;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

record DatosPoblado(int id, int habitantes, String raza, String bando) {}
record Camino(int origen, int destino, int distancia) {}
record DatosMapa(int cantidadPoblados,
                   List<DatosPoblado> poblados,
                   int inicio,
                   int destino,
                   List<Camino> caminos) {}

public class LectorMapa {

    public DatosMapa leerArchivo(String rutaArchivo) throws IOException {
        try (var br = new BufferedReader(new FileReader(rutaArchivo))) {
            int cantidadPoblados = Integer.parseInt(br.readLine());
            var poblados = new ArrayList<DatosPoblado>();

            // Leer poblados
            for (int i = 0; i < cantidadPoblados; i++) {
                String[] datos = br.readLine().split(" ");
                poblados.add(new DatosPoblado(
                    Integer.parseInt(datos[0]),  // id
                    Integer.parseInt(datos[1]),  // habitantes
                    datos[2],                    // raza
                    datos[3]                     // bando
                ));
            }

            // Leer inicio y destino
            var ruta = br.readLine().split(" -> ");
            int inicio = Integer.parseInt(ruta[0]);
            int destino = Integer.parseInt(ruta[1]);

            // Leer caminos
            var caminos = new ArrayList<Camino>();
            String linea;
            while ((linea = br.readLine()) != null) {
                var datos = linea.split(" ");
                caminos.add(new Camino(
                    Integer.parseInt(datos[0]),  // origen
                    Integer.parseInt(datos[1]),  // destino
                    Integer.parseInt(datos[2])   // distancia
                ));
            }

            return new DatosMapa(cantidadPoblados, poblados, inicio, destino, caminos);
        }
    }

    public DatosMapa leerArchivo2(String rutaArchivo) throws IOException {
        try (var br = Files.newBufferedReader(Path.of(rutaArchivo))) {
            // Leer cantidad de poblados
            String firstLine = br.readLine();
            if (firstLine == null) {
                throw new IOException("Archivo vacío");
            }
            var cantidadPoblados = Integer.parseInt(firstLine);
            var poblados = new ArrayList<DatosPoblado>();

            // Leer poblados
            for (int i = 0; i < cantidadPoblados; i++) {
                String lineaPoblado = br.readLine();
                if (lineaPoblado == null) {
                    throw new IOException("Archivo incompleto - faltan datos de poblados");
                }

                String[] datos = lineaPoblado.split(" ");
                // Pattern matching en el switch
                switch (datos) {
                    case String[] arr when arr.length == 4 -> {
                        try {
                            poblados.add(new DatosPoblado(
                                    Integer.parseInt(arr[0]),
                                    Integer.parseInt(arr[1]),
                                    arr[2],
                                    arr[3]
                            ));
                        } catch (NumberFormatException e) {
                            throw new IOException("Datos de poblado inválidos: " + lineaPoblado);
                        }
                    }
                    default -> throw new IOException("Formato inválido de poblado: " + lineaPoblado);
                }
            }

            // Leer inicio y destino
            String rutaStr = br.readLine();
            if (rutaStr == null) {
                throw new IOException("Faltan datos de ruta");
            }

            String[] rutaParts = rutaStr.split(" -> ");
            if (rutaParts.length != 2) {
                throw new IOException("Formato de ruta inválido: " + rutaStr);
            }

            var inicio = Integer.parseInt(rutaParts[0]);
            var destino = Integer.parseInt(rutaParts[1]);

            // Leer caminos usando streams
            var caminos = br.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(linea -> {
                        String[] parts = linea.split(" ");
                        if (parts.length != 3) {
                            throw new IllegalArgumentException("Formato de camino inválido: " + linea);
                        }
                        return new Camino(
                                Integer.parseInt(parts[0]),
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                    })
                    .toList();

            return new DatosMapa(cantidadPoblados, poblados, inicio, destino, caminos);
        }
}