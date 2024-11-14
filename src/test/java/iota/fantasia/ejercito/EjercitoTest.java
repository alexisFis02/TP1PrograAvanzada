package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.ejercito.unidad.UnidadMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EjercitoTest {
    
    private Ejercito ejercito;
    private static final int SALUD_BASE = 100;
    
    @BeforeEach
    void setUp() {
        var unidades = new ArrayList<Unidad>();
        unidades.add(new UnidadMock(SALUD_BASE));
        ejercito = new Ejercito(unidades);
    }

    @Test
    void crearEjercitoSinUnidadesDeberiaLanzarExcepcion() {
        var unidadesVacias = new ArrayList<Unidad>();
        assertThrows(IllegalArgumentException.class, () -> new Ejercito(unidadesVacias));
    }

    @Test
    void crearEjercitoConParametrosDeberiaCrearUnidadesCorrectas() {
        var nuevoEjercito = new Ejercito(3, Raza.WRIVES, Bando.PROPIO);
        assertEquals(3, nuevoEjercito.contarUnidadesFinales());
    }

    @Test
    void agregarUnidadesPropiasDeberiaIncrementarElEjercito() {
        int cantidadInicial = ejercito.contarUnidadesFinales();
        ejercito.agregarUnidadesPropias(2, Raza.RERALOPES);
        assertEquals(cantidadInicial + 2, ejercito.contarUnidadesFinales());
    }

    @Test
    void agregarUnidadesAliadasDeberiaIncrementarElEjercito() {
        int cantidadInicial = ejercito.contarUnidadesFinales();
        ejercito.agregarUnidadesAliadas(2, Raza.NORTAICHIAN);
        assertEquals(cantidadInicial + 2, ejercito.contarUnidadesFinales());
    }

    @Test
    void ejercitoDeberiaEstarVivoConUnidades() {
        assertTrue(ejercito.estaVivo());
    }

    @Test
    void recibirAtaqueDeberiaReducirVidaDeUnidad() {
        int danio = SALUD_BASE / 2;
        ejercito.recibirAtaque(danio);
        assertTrue(ejercito.estaVivo());
        ejercito.recibirAtaque(danio + 1);
        assertEquals(0, ejercito.contarUnidadesFinales());
    }

    @Test
    void ejercitoDeberiaPoderDescansar() {
        int danioInicial = SALUD_BASE / 2;
        ejercito.recibirAtaque(danioInicial);
        ejercito.descansar();
        assertTrue(ejercito.estaVivo());
    }

    @Test
    void actualizarUltimoHeridoDeberiaFuncionarCorrectamente() {
        ejercito.actualizarUltimoHerido();
        assertEquals(1, ejercito.contarUnidadesFinales());
    }

    @Test
    void ejercitoDeberiaPoderAtacar() {
        var unidadesEnemigas = new ArrayList<Unidad>();
        unidadesEnemigas.add(new UnidadMock(SALUD_BASE));
        Ejercito ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        
        ejercito.atacar(ejercitoEnemigo);
        assertTrue(ejercitoEnemigo.estaVivo());
    }

    @Test
    void ejercitoDeberiaGestionarCorrectamenteUnidadesAliadasYPropias() {
        var unidadesIniciales = new ArrayList<Unidad>();
        unidadesIniciales.add(new UnidadMock(SALUD_BASE));
        Ejercito ejercitoMixto = new Ejercito(unidadesIniciales);
        
        ejercitoMixto.agregarUnidades(2, Raza.RERALOPES, Bando.ALIADO);
        assertEquals(3, ejercitoMixto.contarUnidadesFinales());
    }

    @Test
    void ejercitoDeberiaQuedarMuertoSinUnidades() {
        var unidadesIniciales = new ArrayList<Unidad>();
        unidadesIniciales.add(new UnidadMock(SALUD_BASE));
        Ejercito ejercitoPequenio = new Ejercito(unidadesIniciales);
        
        ejercitoPequenio.recibirAtaque(SALUD_BASE + 1);
        assertFalse(ejercitoPequenio.estaVivo());
    }

    @Test
    void prioridadDeAtaqueDeberiaSerAliadosSobrePropias() {
        var unidadesIniciales = new ArrayList<Unidad>();
        unidadesIniciales.add(new UnidadMock(SALUD_BASE));
        Ejercito ejercitoMixto = new Ejercito(unidadesIniciales);
        ejercitoMixto.agregarUnidadesAliadas(1, Raza.RERALOPES);
        
        var unidadesEnemigas = new ArrayList<Unidad>();
        unidadesEnemigas.add(new UnidadMock(SALUD_BASE));
        Ejercito ejercitoEnemigo = new Ejercito(unidadesEnemigas);
        
        ejercitoMixto.atacar(ejercitoEnemigo);
        assertEquals(2, ejercitoMixto.contarUnidadesFinales());
    }
} 