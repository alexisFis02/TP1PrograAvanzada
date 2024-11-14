package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import org.junit.jupiter.api.Test;

import java.util.prefs.PreferenceChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class ReralopesTest {

    @Test
    void testAtacar() {
        Reralopes reralopes = new Reralopes();
        Atacable enemigo = new UnidadMock(100);
        
        reralopes.atacar(enemigo);
        assertEquals(73, enemigo.getSalud()); // 100 - 27 = 73
    }

    @Test
    void testDescansar() {
        Reralopes reralopes = new Reralopes();
        reralopes.descansar();
        assertTrue(reralopes.isConcentrado());
        assertEquals(3, reralopes.getAtaquesPotenciados());
        assertEquals(53, reralopes.getSalud());
        assertEquals(53, reralopes.getSaludMaxima());
    }

    @Test
    void testAtacarErrar() {
        Reralopes reralopes = new Reralopes();
        Atacable enemigo = new UnidadMock(100);
        int vidaEsperada = 100;

        // Realiza 4 ataques, debería errar 2 de ellos

        reralopes.atacar(enemigo); // 1er ataque
        vidaEsperada -= reralopes.getDanioBase();

        reralopes.atacar(enemigo); // 2do ataque (errar)

        reralopes.atacar(enemigo); // 3er ataque
        vidaEsperada -= reralopes.getDanioBase();

        reralopes.atacar(enemigo); // 4to ataque (errar)

        assertEquals(vidaEsperada, enemigo.getSalud()); // Solo 2 ataques exitosos
    }

    @Test
    void testAtacarRepetidamenteConConcentracion() {
        Reralopes reralopes = new Reralopes();
        Atacable enemigo = new UnidadMock(500);
        int vidaEsperada = 500;
        reralopes.descansar(); // Se concentra
        reralopes.atacar(enemigo); // 1er ataque (daño potenciado)
        vidaEsperada -= reralopes.getDanioBase()*2;
        assertEquals(vidaEsperada, enemigo.getSalud()); // 100 - 27 = 73

        reralopes.atacar(enemigo); // 2do ataque (errar)
        reralopes.atacar(enemigo); // 3er ataque (daño potenciado)
        vidaEsperada -= reralopes.getDanioBase()*2;
        assertEquals(vidaEsperada, enemigo.getSalud()); // 73 - 54 = 19 (27 * 2)

        reralopes.atacar(enemigo); // 4to ataque (errar)
        assertEquals(vidaEsperada, enemigo.getSalud()); // Sin cambios

        reralopes.atacar(enemigo); // 5to ataque (daño normal)
        vidaEsperada -= reralopes.getDanioBase();
        assertEquals(vidaEsperada, enemigo.getSalud()); // Sin cambios
    }

    @Test
    void testRecibirAtaqueYAtacar() {
        Reralopes reralopes = new Reralopes();
        Unidad enemigo = new UnidadMock(100);
        reralopes.descansar();
        reralopes.recibirAtaque(20);
        assertEquals(33, reralopes.getSalud()); // 53 - 20 = 33
        assertFalse(reralopes.isConcentrado()); // Se desconcentra al recibir daño
        assertEquals(0, reralopes.getAtaquesPotenciados()); // no esta concentrado

        int vidaEsperada = 100;
        reralopes.atacar(enemigo); // 1er ataque daño normal
        vidaEsperada -= reralopes.getDanioBase();
        assertEquals(vidaEsperada, enemigo.getSalud()); // recibe danio normal
    }

    @Test
    void testRecibirAtaqueYDescansar() {
        Reralopes reralopes = new Reralopes();
        int vidaEsperada = reralopes.getSalud();
        reralopes.recibirAtaque(20);
        vidaEsperada -= 20;
        assertEquals(vidaEsperada, reralopes.getSalud());

        reralopes.descansar(); // Se concentra
        assertTrue(reralopes.isConcentrado());
        assertEquals(vidaEsperada, reralopes.getSalud()); // No se cura
    }

    @Test
    void testDescansarYDescansar(){
        Reralopes reralopes = new Reralopes();
        reralopes.descansar();
        reralopes.descansar();
        assertTrue(reralopes.isConcentrado());
        assertEquals(3, reralopes.getAtaquesPotenciados());
        assertEquals(53, reralopes.getSalud());
        assertEquals(53, reralopes.getSaludMaxima());
    }

    @Test
    void testRecibirAtaqueMortalYMorir() {
        var reralopes = new Reralopes();
        reralopes.recibirAtaque(1000000000);
        assertEquals(0, reralopes.getSalud());
        assertFalse(reralopes.estaVivo());
    }
}