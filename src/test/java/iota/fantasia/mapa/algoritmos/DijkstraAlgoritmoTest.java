package iota.fantasia.mapa.algoritmos;

import iota.fantasia.mapa.Mapa;
import iota.fantasia.mapa.Poblado;
import iota.fantasia.mapa.records.Camino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgoritmoTest {
    
    private Mapa mapa;
    private Poblado poblado1;
    private Poblado poblado2;
    private Poblado poblado3;
    private Poblado poblado4;

    @BeforeEach
    void setUp() {
        poblado1 = new Poblado(1);
        poblado2 = new Poblado(2);
        poblado3 = new Poblado(3);
        poblado4 = new Poblado(4);

        // Crear caminos entre poblados
        poblado1.agregarCamino(2, 5);  // 1 -> 2: distancia 5
        poblado1.agregarCamino(3, 2);  // 1 -> 3: distancia 2
        poblado2.agregarCamino(4, 4);  // 2 -> 4: distancia 4
        poblado3.agregarCamino(2, 1);  // 3 -> 2: distancia 1
        poblado3.agregarCamino(4, 6);  // 3 -> 4: distancia 6

        // Crear mapa con los poblados
        Map<Integer, Poblado> poblados = new HashMap<>();
        poblados.put(1, poblado1);
        poblados.put(2, poblado2);
        poblados.put(3, poblado3);
        poblados.put(4, poblado4);
        
        mapa = new Mapa(poblados);
    }

    @Test
    void encontrarCaminoMasCorto_CaminoDirecto() {
        var resultado = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, poblado1, poblado2);
        
        assertAll("Camino directo entre poblado1 y poblado2",
            () -> assertEquals(5, resultado.distanciaTotal(), 
                "La distancia total deberia ser 5"),
            () -> assertEquals(List.of(poblado1, poblado2), resultado.camino(), 
                "El camino deberia ser directo de poblado1 a poblado2")
        );
    }

    @Test
    void encontrarCaminoMasCorto_CaminoIndirecto() {
        var resultado = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, poblado1, poblado4);
        
        assertAll("Camino indirecto de poblado1 a poblado4",
            () -> assertEquals(7, resultado.distanciaTotal(), 
                "La distancia total deberia ser 7"),
            () -> assertEquals(List.of(poblado1, poblado3, poblado2, poblado4), resultado.camino(), 
                "El camino deberia ser poblado1 -> poblado3 -> poblado2 -> poblado4")
        );
    }

    @Test
    void encontrarCaminoMasCorto_DestinoIgualOrigen() {
        var resultado = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, poblado1, poblado1);
        
        assertAll("Camino cuando origen y destino son iguales",
            () -> assertEquals(0, resultado.distanciaTotal(), 
                "La distancia total debería ser 0 cuando origen y destino son iguales"),
            () -> assertEquals(List.of(poblado1), resultado.camino(), 
                "El camino deberia contener solo el poblado de origen")
        );
    }

    @Test
    void encontrarCaminoMasCorto_CaminoInexistente() {
        Poblado pobladoAislado = new Poblado(5);
        mapa.getPoblados().put(5, pobladoAislado);
        
        var resultado = DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, poblado1, pobladoAislado);
        
        assertAll("Camino a poblado aislado",
            () -> assertTrue(resultado.camino().isEmpty(), 
                "El camino deberia estar vacío cuando no hay ruta posible"),
            () -> assertEquals(Integer.MAX_VALUE, resultado.distanciaTotal(), 
                "La distancia deberia ser MAX_VALUE cuando no hay camino posible")
        );
    }

    @Test
    void encontrarCaminoMasCorto_NodosInvalidos() {
        Poblado pobladoNoEnMapa = new Poblado(99);
        
        assertAll("Validación de casos invalidos",
            () -> assertThrows(IllegalArgumentException.class, () -> 
                DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, null, poblado2),
                "Deberia fallar con origen null"
            ),
            () -> assertThrows(IllegalArgumentException.class, () -> 
                DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, poblado1, null),
                "Deberia fallar con destino null"
            ),
            () -> assertThrows(IllegalArgumentException.class, () -> 
                DijkstraAlgoritmo.encontrarCaminoMasCorto(null, poblado1, poblado2),
                "Deberia fallar con mapa null"
            ),
            () -> assertThrows(IllegalArgumentException.class, () -> 
                DijkstraAlgoritmo.encontrarCaminoMasCorto(mapa, pobladoNoEnMapa, poblado2),
                "Deberia fallar con poblado que no existe en el mapa"
            )
        );
    }
} 