package iota.fantasia.batalla;

import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.ejercito.unidad.UnidadMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatallaTest {

    @Test
    void ejercitoAliadoGanaLaBatalla() {
        var unidadesAliadas = new ArrayList<Unidad>(Arrays.asList(
            new UnidadMock(100, Bando.PROPIO),
            new UnidadMock(100, Bando.PROPIO)
        ));
        var unidadesEnemigas = new ArrayList<Unidad>(List.of(
                new UnidadMock(50, Bando.ENEMIGO)
        ));
        
        var ejercitoAliado = new Ejercito(unidadesAliadas);
        var ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        var batalla = new Batalla(ejercitoAliado, ejercitoEnemigo);

        boolean resultado = batalla.simularBatalla();

        assertTrue(resultado);
        assertTrue(ejercitoAliado.estaVivo());
        assertFalse(ejercitoEnemigo.estaVivo());
    }

    @Test
    void ejercitoEnemigoGanaLaBatalla() {
        var unidadesAliadas = new ArrayList<Unidad>(List.of(
                new UnidadMock(50, Bando.PROPIO)
        ));
        var unidadesEnemigas = new ArrayList<Unidad>(Arrays.asList(
            new UnidadMock(100, Bando.ENEMIGO),
            new UnidadMock(100, Bando.ENEMIGO)
        ));
        
        var ejercitoAliado = new Ejercito(unidadesAliadas);
        var ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        var batalla = new Batalla(ejercitoAliado, ejercitoEnemigo);

        boolean resultado = batalla.simularBatalla();

        assertFalse(resultado);
        assertFalse(ejercitoAliado.estaVivo());
        assertTrue(ejercitoEnemigo.estaVivo());
    }

    @Test
    void batallaConEjercitosIguales() {
        var unidadesAliadas = new ArrayList<Unidad>(Arrays.asList(
            new UnidadMock(100, Bando.PROPIO),
            new UnidadMock(100, Bando.PROPIO)
        ));
        var unidadesEnemigas = new ArrayList<Unidad>(Arrays.asList(
            new UnidadMock(100, Bando.ENEMIGO),
            new UnidadMock(100, Bando.ENEMIGO)
        ));
        
        var ejercitoAliado = new Ejercito(unidadesAliadas);
        var ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        var batalla = new Batalla(ejercitoAliado, ejercitoEnemigo);

        boolean resultado = batalla.simularBatalla();

        assertTrue(resultado); // gana por que inicio primero
        assertFalse(ejercitoEnemigo.estaVivo());
    }

    @Test
    void batallaContraEjercitoSuperior() {
        var unidadesAliadas = new ArrayList<Unidad>(Arrays.asList(
                new UnidadMock(100, Bando.PROPIO),
                new UnidadMock(100, Bando.PROPIO)
        ));
        var unidadesEnemigas = new ArrayList<Unidad>(Arrays.asList(
                new UnidadMock(100, Bando.ENEMIGO),
                new UnidadMock(100, Bando.ENEMIGO),
                new UnidadMock(100, Bando.ENEMIGO)
        ));

        var ejercitoAliado = new Ejercito(unidadesAliadas);
        var ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        var batalla = new Batalla(ejercitoAliado, ejercitoEnemigo);

        boolean resultado = batalla.simularBatalla();

        assertFalse(resultado);
        assertFalse(ejercitoAliado.estaVivo());
        assertTrue(ejercitoEnemigo.estaVivo());
    }
} 