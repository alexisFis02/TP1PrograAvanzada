package iota.fantasia.ejercito.factory;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnidadFactoryTest {

    @Test
    void testCrearWrives() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.WRIVES);
        assertInstanceOf(Wrives.class, unidad);
    }

    @Test
    void testDeberiaCrearReralopes() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.RERALOPES);
        assertInstanceOf(Reralopes.class, unidad);
    }

    @Test
    void testCrearRadaiteran() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.RADAITERAN);
        assertInstanceOf(Radaiteran.class, unidad);
    }

    @Test
    void testCrearNortaichian() {
        Unidad unidad = UnidadFactory.crearUnidad(Raza.NORTAICHIAN);
        assertInstanceOf(Nortaichan.class, unidad);
    }

    @Test
    void testUnidadesCreadasDeberianSerDiferentes() {
        Unidad unidad1 = UnidadFactory.crearUnidad(Raza.WRIVES);
        Unidad unidad2 = UnidadFactory.crearUnidad(Raza.WRIVES);
        assertNotSame(unidad1, unidad2);
    }
}