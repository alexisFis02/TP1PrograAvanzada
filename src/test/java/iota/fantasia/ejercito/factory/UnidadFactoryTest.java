package iota.fantasia.ejercito.factory;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class UnidadFactoryTest {

    @Test
    void deberiaCrearWrives() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.WRIVES);
        assertInstanceOf(Wrives.class, unidad);
    }

    @Test
    void deberiaCrearReralopes() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.RERALOPES);
        assertInstanceOf(Reralopes.class, unidad);
    }

    @Test
    void deberiaCrearRadaiteran() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.RADAITERAN);
        assertInstanceOf(Radaiteran.class, unidad);
    }

    @Test
    void deberiaCrearNortaichian() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.NORTAICHIAN);
        assertInstanceOf(Nortaichan.class, unidad);
    }

    @Test
    void unidadesCreadasDeberianSerDiferentes() {
        Unidad unidad1 = UnidadFactory.crearUnidad(Raza.WRIVES);
        Unidad unidad2 = UnidadFactory.crearUnidad(Raza.WRIVES);
        assertNotSame(unidad1, unidad2);
    }

    @Test
    void unidadesDeberianTenerValoresIniciales() {
        for (Raza raza : Raza.values()) {
            Unidad unidad = UnidadFactory.crearUnidad(raza);
            assertTrue(unidad.getSalud() > 0);
            assertTrue(unidad.getSaludMaxima() > 0);
            assertTrue(unidad.getDanioBase() > 0);
        }
    }
}