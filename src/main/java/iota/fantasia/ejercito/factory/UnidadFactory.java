package iota.fantasia.ejercito.factory;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.unidad.*;

public class UnidadFactory {

    public static Unidad crearUnidad(Raza raza) {
        return switch (raza) {
            case WRIVES -> new Wrives();
            case RERALOPES -> new Reralopes();
            case RADAITERAN -> new Radaiteran();
            case NORTAICHIAN -> new Nortaichan();
            case DESCONOCIDA -> throw new IllegalArgumentException("No se puede crear una unidad de raza desconocida");
        };
    }

    public static Unidad crearUnidadConBando(Raza raza, Bando bando) {
        return switch (raza) {
            case WRIVES -> new Wrives(bando);
            case RERALOPES -> new Reralopes(bando);
            case RADAITERAN -> new Radaiteran(bando);
            case NORTAICHIAN -> new Nortaichan(bando);
            case DESCONOCIDA -> throw new IllegalArgumentException("No se puede crear una unidad de raza desconocida");
        };

    }
}