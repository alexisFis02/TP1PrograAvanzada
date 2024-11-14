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
        assertEquals(41, enemigo.getSalud()); 
    }

    @Test
    void testAtacarConVariosAtaques() {
        Radaiteran radaiteran = new Radaiteran();
        Atacable enemigo = new UnidadMock(100);
        
        radaiteran.atacar(enemigo);
        assertEquals(41, enemigo.getSalud()); 
        
        radaiteran.atacar(enemigo);
        assertEquals(35, enemigo.getSalud()); // TODO: revisar
        
        radaiteran.atacar(enemigo);
        assertEquals(26, enemigo.getSalud()); 
    }

    @Test
    void testAtacarCuandoNoEstaVivo() {
        Radaiteran radaiteran = new Radaiteran();
        Atacable enemigo = new UnidadMock(100);
        
        radaiteran.recibirAtaque(36);
        radaiteran.atacar(enemigo);
        assertEquals(100, enemigo.getSalud()); 
    }

    @Test
    void testAtacarEnemigoMuerto() {
        Radaiteran radaiteran = new Radaiteran();
        Atacable enemigo = new UnidadMock(0);
        
        radaiteran.atacar(enemigo);
        assertEquals(0, enemigo.getSalud());
    }

    @Test
    void testDescansar() {
        Radaiteran radaiteran = new Radaiteran();
        assertDoesNotThrow(radaiteran::descansar);
    }
} 