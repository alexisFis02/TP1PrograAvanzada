package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// TODO: revisar 
class RadaiteranTest {

    @Test
    void testAtacar() {
        Radaiteran radaiteran = new Radaiteran();
        Atacable enemigo = new UnidadMock(100);
        
        radaiteran.atacar(enemigo);
        assertEquals(100 - radaiteran.getDanioBase(), enemigo.getSalud());
    }

    @Test
    void testAtacarConVariosAtaques() {
        Radaiteran radaiteran = new Radaiteran();
        Atacable enemigo = new UnidadMock(500);
        int vidaEsperada = enemigo.getSalud();

        radaiteran.atacar(enemigo);
        vidaEsperada -= radaiteran.getDanioBase();
        assertEquals(vidaEsperada, enemigo.getSalud());
        
        radaiteran.atacar(enemigo);
        vidaEsperada -= radaiteran.getDanioBase() + 3;
        assertEquals(vidaEsperada, enemigo.getSalud());
        
        radaiteran.atacar(enemigo);
        vidaEsperada -= radaiteran.getDanioBase() + 3 + 3;
        assertEquals(vidaEsperada, enemigo.getSalud());
    }

//    @Test
//    void testAtacarCuandoNoEstaVivo() {
//        Radaiteran radaiteran = new Radaiteran();
//        Atacable enemigo = new UnidadMock(100);
//
//        radaiteran.recibirAtaque(36);
//        radaiteran.atacar(enemigo);
//        assertEquals(100, enemigo.getSalud());
//    }

//    @Test
//    void testAtacarEnemigoMuerto() {
//        Radaiteran radaiteran = new Radaiteran();
//        Atacable enemigo = new UnidadMock(0);
//
//        radaiteran.atacar(enemigo);
//        assertEquals(0, enemigo.getSalud());
//    }

    @Test
    void testRecibirAtaqueYDescansar() {
        Radaiteran radaiteran = new Radaiteran();
        radaiteran.recibirAtaque(10);
        radaiteran.descansar();
        assertEquals(radaiteran.getSaludMaxima()-10, radaiteran.getSalud());
    }

    @Test
    void testRecibirAtaqueMortalYMorir() {
        var radaiteran = new Radaiteran();
        radaiteran.recibirAtaque(1000000000);
        assertEquals(0, radaiteran.getSalud());
        assertFalse(radaiteran.estaVivo());
    }
}