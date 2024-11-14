package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WrivesTest {

    @Test
    void testAtacar() {
        Wrives wrives = new Wrives();
        Atacable enemigo = new UnidadMock(100);

        wrives.atacar(enemigo); // 100 - 113 = 0
        assertEquals(0, enemigo.getSalud()); // 113
    }

    @Test
    void testDescansar() {
        Wrives wrives = new Wrives();
        wrives.descansar();
        assertEquals(158, wrives.getSalud()); // 108 + 50
        assertEquals(158, wrives.getSaludMaxima()); // 108 + 50
    }

    @Test
    void testAtacarConDobleDanio() {
        Wrives wrives = new Wrives();
        Atacable enemigo = new UnidadMock(500);

        wrives.atacar(enemigo); // 100 - 113 = 0
        wrives.atacar(enemigo); // 100 - 113 = 0
        wrives.atacar(enemigo); // con doble danio
        assertEquals(500 - 113 - 113 - (2 * 113), enemigo.getSalud());

    }

    @Test
    void testDescansarYAtacar() {
        Wrives wrives = new Wrives();
        Atacable enemigo = new UnidadMock(500);
        wrives.descansar();
        wrives.atacar(enemigo); // no deberia atacar
        assertEquals(500, enemigo.getSalud());
    }

    @Test
    void testRecibirAtaque() {
        Wrives wrives = new Wrives();
        wrives.recibirAtaque(50); // Recibe 50 * 2 = 100 de daño
        assertEquals(8, wrives.getSalud()); // 108 - 100 = 8
    }

    @Test
    void testRecibirAtaqueYDescansar() {
        Wrives wrives = new Wrives();
        wrives.recibirAtaque(50); // Recibe 50 * 2 = 100 de daño
        assertEquals(8, wrives.getSalud()); // 108 - 100 = 8

        wrives.descansar(); // Aumenta salud y salud máxima
        assertEquals(58, wrives.getSalud()); // 8 + 50 = 58
        assertEquals(158, wrives.getSaludMaxima()); // Salud máxima aumentada
    }

    @Test
    void testRecibirAtaqueMortalYMorir() {
        var wrives = new Wrives();
        wrives.recibirAtaque(1000000000);
        assertEquals(0, wrives.getSalud());
        assertFalse(wrives.estaVivo());
    }
} 