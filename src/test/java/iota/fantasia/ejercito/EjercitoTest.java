package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.ejercito.unidad.UnidadMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EjercitoTest {

    private Ejercito ejercito;

    @BeforeEach
    void setUp() {
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();
        unidades.add(new UnidadMock(100, Bando.PROPIO));
        ejercito = new Ejercito(unidades);
    }

    @Test
    void constructor_ConListaVacia_LanzaExcepcion() {
        var unidadesVacias = new ArrayList<Unidad>();
        assertThrows(IllegalArgumentException.class, () -> new Ejercito(unidadesVacias));
    }

    @Test
    void constructor_ConCantidadRazaYBando_CreaEjercitoCorrectamente() {
        var ejercito = new Ejercito(3, Raza.WRIVES, Bando.PROPIO);
        assertEquals(3, ejercito.contarUnidadesFinales());
    }

    @Test
    void agregarUnidades_UnidadesPropias_AumentaTamanioEjercito() {
        ejercito.agregarUnidadesPropias(2, Raza.RADAITERAN);
        assertEquals(3, ejercito.contarUnidadesFinales());
    }

    @Test
    void agregarUnidades_UnidadesAliadas_AumentaTamanioEjercito() {
        ejercito.agregarUnidadesAliadas(2, Raza.RERALOPES);
        assertEquals(3, ejercito.contarUnidadesFinales());
    }

    @Test
    void estaVivo_EjercitoConUnidades_RetornaTrue() {
        assertTrue(ejercito.estaVivo());
    }

    @Test
    void estaVivo_EjercitoSinUnidades_RetornaFalse() {
        ejercito.recibirAtaque(1000); // Daño suficiente para matar todas las unidades
        assertFalse(ejercito.estaVivo());
    }

    @Test
    void recibirAtaque_DanioMenorAVida_UnidadSobrevive() {
        ejercito.recibirAtaque(50);
        assertTrue(ejercito.estaVivo());
        assertEquals(1, ejercito.contarUnidadesFinales());
    }

    @Test
    void recibirAtaque_DanioMayorAVida_UnidadMuere() {
        ejercito.recibirAtaque(150);
        assertFalse(ejercito.estaVivo());
        assertEquals(0, ejercito.contarUnidadesFinales());
    }

    @Test
    void atacar_EjercitoAtacaAOtroEjercito_CausaDanio() {
        var ejercitoEnemigo = new Ejercito(1, Raza.WRIVES, Bando.ENEMIGO);
        var unidadesInicialesEnemigo = ejercitoEnemigo.contarUnidadesFinales();

        ejercito.atacar(ejercitoEnemigo);

        int saludEsperada = ejercitoEnemigo.getPrimeroEnDefender().getSalud() - ejercito.getPrimeroEnAtacar().getDanioBase();

        assertEquals(ejercitoEnemigo.getPrimeroEnDefender().getSalud(), saludEsperada);
        assertTrue(ejercitoEnemigo.estaVivo());
        assertEquals(unidadesInicialesEnemigo, ejercitoEnemigo.contarUnidadesFinales());
    }

    @Test
    void descansar_UnidadesDescansanCorrectamente() {
        ejercito.recibirAtaque(50);
        ejercito.descansar();

        var ejercitoComparacion = new Ejercito(1, Raza.NORTAICHIAN, Bando.PROPIO);

        var danio = 30;
        ejercito.recibirAtaque(danio);
        ejercitoComparacion.recibirAtaque(danio);

        assertTrue(ejercito.estaVivo());
        assertTrue(ejercitoComparacion.estaVivo());
    }

    @Test
    void actualizarUltimoHerido_ActualizaCorrectamente() {
        Unidad unidad1 = new UnidadMock(100, Bando.PROPIO);
        Unidad unidad2 = new UnidadMock(220, Bando.PROPIO);

        var unidades = new ArrayList<Unidad>();
        unidades.add(unidad1);
        unidades.add(unidad2);

        var ejercito = new Ejercito(unidades);

        ejercito.recibirAtaque(20);
        ejercito.actualizarUltimoHerido();

        assertEquals(unidad1, ejercito.getUltimoHerido());
        assertNotEquals(unidad2, ejercito.getUltimoHerido());
        assertEquals(2, ejercito.contarUnidadesFinales());
    }

    @Test
    void contarUnidadesFinales_RetornaCantidadCorrecta() {
        ejercito.agregarUnidadesPropias(2, Raza.NORTAICHIAN);
        ejercito.agregarUnidadesAliadas(3, Raza.WRIVES);

        assertEquals(6, ejercito.contarUnidadesFinales()); // 6 por que ya tiene la unidad mock
    }
} 