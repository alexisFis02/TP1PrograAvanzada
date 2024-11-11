package iota.fantasia.mapa;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.mapa.records.Camino;
import iota.fantasia.mapa.records.DatosArchivo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LectorMapaTest {

    @TempDir
    Path tempDir;

    @Test
    public void leerArchivoValido() throws IOException {
        // archivo de prueba valido
        String contenido = """
                3
                1 100 Wrives aliado
                2 200 Reralopes enemigo
                3 300 Radaiteran propio
                1 -> 3
                1 2 10
                2 3 20
                1 3 15
                """;

        Path pathArchivo = crearArchivoTemporal(contenido);

        DatosArchivo datos = LectorMapa.leerArchivo(pathArchivo.toString());

        assertAll("Validar datos leídos del archivo",
                () -> assertEquals(1, datos.inicio(), "El inicio del archivo no es correcto"),
                () -> assertEquals(3, datos.destino(), "El destino del archivo no es correcto"),
                () -> assertEquals(3, datos.poblados().size(), "Número de poblados incorrecto"),
                () -> assertEquals(Set.of(
                            new Poblado(1, 100, Raza.WRIVES, Bando.ALIADO),
                            new Poblado(2, 200, Raza.RERALOPES, Bando.ENEMIGO),
                            new Poblado(3, 300, Raza.RADAITERAN, Bando.PROPIO)
                    ), datos.poblados(), "Poblados incorrectos"),
                () -> assertEquals(3, datos.caminos().size(), "Número de caminos incorrecto"),
                () -> assertEquals(List.of(
                            new Camino(1, 2, 10),
                            new Camino(2, 3, 20),
                            new Camino(1, 3, 15)
                    ), datos.caminos(), "Caminos incorrectos")
        );
    }

    @Test
    void archivoNoExiste() {
        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo("archivo_inexistente.txt")
        );
    }

    @Test
    void archivoVacio() throws IOException {
        Path archivo = crearArchivoTemporal("");
        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo(archivo.toString())
        );
    }

    @Test
    void cantidadPobladosInvalida() throws IOException {
        String contenido = """
                -1
                1 100 Wrives aliado
                """;
        Path archivo = crearArchivoTemporal(contenido);

        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo(archivo.toString())
        );
    }

    @Test
    void formatoPobladoInvalido() throws IOException {
        String contenido = """
                1
                1 100 RazaInvalida aliado
                1 -> 2
                """;
        Path archivo = crearArchivoTemporal(contenido);

        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo(archivo.toString())
        );
    }

    @Test
    void formatoRutaInvalido() throws IOException {
        String contenido = """
                1
                1 100 Wrives aliado
                1 -> 2 -> 3
                """;
        Path archivo = crearArchivoTemporal(contenido);

        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo(archivo.toString())
        );
    }

    @Test
    void formatoCaminoInvalido() throws IOException {
        String contenido = """
                1
                1 100 Wrives aliado
                1 -> 2
                1 2 -5
                """;
        Path archivo = crearArchivoTemporal(contenido);

        assertThrows(IOException.class, () ->
                LectorMapa.leerArchivo(archivo.toString())
        );
    }

    private Path crearArchivoTemporal(String contenido) throws IOException {
        Path archivo = tempDir.resolve("test.txt");
        Files.writeString(archivo, contenido);
        return archivo;
    }
} 