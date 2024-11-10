package iota.fantasia.ejercito.factory;

import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.*;

public class UnidadFactory {
    public static Unidad crearUnidad(Raza raza) {
        return switch (raza) {
            case WRIVES -> new Wrives();
            case RERALOPES -> new Reralopes();
            case RADAITERAN -> new Radaiteran();
            case NORTAICHIAN -> new Nortaichan();
        };
    }
}