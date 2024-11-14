package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NortaichanTest {

    @Test
    void testAtacar() {
        Nortaichan nortaichan = new Nortaichan();
        Atacable enemigo = new UnidadMock(100);

        nortaichan.atacar(enemigo);
        assertEquals(100 - 18, enemigo.getSalud()); // 18
    }

    @Test
    void testDescansar() {
        Nortaichan nortaichan = new Nortaichan();
        nortaichan.recibirAtaque(nortaichan.getSalud() - 1);
        nortaichan.descansar();
        assertEquals(nortaichan.getSaludMaxima(), nortaichan.getSalud()); // Recupera toda su salud
        assertTrue(nortaichan.isDePiedra()); // Debería estar de piedra
    }

    @Test
    void testCuracionAlAtacar() {
        Nortaichan nortaichan = new Nortaichan();
        nortaichan.atacar(new UnidadMock(100)); // Ataca para causar daño
        int saludEsperada = nortaichan.getSalud() + (int) (nortaichan.getSaludMaxima() * 0.04);
        nortaichan.atacar(new UnidadMock(100)); // Ataca nuevamente para curarse
        assertEquals(Math.min(nortaichan.getSaludMaxima(), saludEsperada), nortaichan.getSalud());
    }

    @Test
    void testEnfurecerseAlRecibirAtaque() {
        Nortaichan nortaichan = new Nortaichan();
        nortaichan.recibirAtaque(20); // Recibe daño
        assertTrue(nortaichan.isEnfurecido()); // Debería estar enfurecido
        assertEquals(2, nortaichan.getTurnosEnfurecido()); // Debería durar 2 turnos
    }

    @Test
    void testAtacarEnfurecido() {
        Nortaichan nortaichan = new Nortaichan();
        Atacable enemigo = new UnidadMock(100);
        nortaichan.recibirAtaque(20); // Se enfurece
        nortaichan.atacar(enemigo); // Ataca mientras está enfurecido
        assertEquals(100 - (18 * 2), enemigo.getSalud()); // Debería causar el doble de daño
    }

    @Test
    void testTurnosEnfurecido() {
        Nortaichan nortaichan = new Nortaichan();
        Atacable enemigo = new UnidadMock(500);
        int vidaEsperada = enemigo.getSalud();

        nortaichan.recibirAtaque(20); // Se enfurece
        nortaichan.atacar(enemigo); // 1er ataque
        vidaEsperada -= nortaichan.getDanioBase() * 2;
        nortaichan.atacar(enemigo); // 2do ataque
        vidaEsperada -= nortaichan.getDanioBase() * 2;
        assertEquals(vidaEsperada, enemigo.getSalud());

        nortaichan.atacar(enemigo); // Ataque normal
        vidaEsperada -= nortaichan.getDanioBase();
        assertEquals(vidaEsperada, enemigo.getSalud());
        assertFalse(nortaichan.isEnfurecido());
    }

    @Test
    void testRecibirAtaqueYVolverseDePiedra() {
        Nortaichan nortaichan = new Nortaichan();
        nortaichan.recibirAtaque(20); // Recibe daño
        assertEquals(46, nortaichan.getSalud()); // 66 - 20 = 46
        nortaichan.descansar(); // Se vuelve de piedra
        assertTrue(nortaichan.isDePiedra()); // Debería estar de piedra
    }

    @Test
    void testRecibirAtaqueMientrasEsDePiedra() {
        Nortaichan nortaichan = new Nortaichan();
        nortaichan.descansar(); // Se vuelve de piedra
        nortaichan.recibirAtaque(20); // Recibe daño
        assertEquals(66 - (20 / 2), nortaichan.getSalud()); // Debera recibir la mitad del daño
    }

    @Test
    void testTurnosDePiedra() {
        Nortaichan nortaichan = new Nortaichan();
        int vidaEsperada = nortaichan.getSalud();

        nortaichan.descansar(); // Se vuelve de piedra
        assertTrue(nortaichan.isDePiedra()); // Debería estar de piedra
        nortaichan.recibirAtaque(20);
        vidaEsperada -= 20 / 2; // recibe la mitad del daño
        assertEquals(vidaEsperada, nortaichan.getSalud());

        nortaichan.recibirAtaque(20);
        vidaEsperada -= 20 / 2; // recibe la mitad del daño
        assertEquals(vidaEsperada, nortaichan.getSalud());
        assertFalse(nortaichan.isDePiedra()); // Debería dejar de estar de piedra

    }

    @Test
    void testRecibirAtaqueMortalYMorir() {
        var nortaichan = new Nortaichan();
        nortaichan.recibirAtaque(1000000000);
        assertEquals(0, nortaichan.getSalud());
        assertFalse(nortaichan.estaVivo());
    }
} 