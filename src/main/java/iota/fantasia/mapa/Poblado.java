package iota.fantasia.mapa;

import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.ejercito.unidad.Unidad;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.enums.Bando;

import java.util.ArrayList;
import java.util.List;

public class Poblado {
    private final int id;
    private final int habitantes;
    private final Raza raza;
    private final Bando bando;
    private Ejercito ejercito;

    public Poblado(int id, int habitantes, Raza raza, Bando bando) {
        this.id = id;
        this.habitantes = habitantes;
        this.raza = raza;
        this.bando = bando;
    }

    public Ejercito generarEjercito() {
        List<Unidad> unidades = new ArrayList<>();
        int cantidadUnidades = switch(bando) {
            case ALIADO -> habitantes / 2;
            case ENEMIGO -> habitantes;
            case PROPIO -> habitantes;
        };

        for (int i = 0; i < cantidadUnidades; i++) {
            unidades.add(UnidadFactory.crearUnidad(raza));
        }

        ejercito = new Ejercito(unidades);
        return ejercito;
    }

    public Ejercito getEjercito() {
        return ejercito;
    }

    // Getters
    public int getId() { return id; }
    public int getHabitantes() { return habitantes; }
    public Raza getRaza() { return raza; }
    public Bando getBando() { return bando; }
}
