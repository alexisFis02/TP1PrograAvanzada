package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Bando;
import iota.fantasia.ejercito.enums.Raza;
import iota.fantasia.ejercito.factory.UnidadFactory;
import iota.fantasia.ejercito.unidad.Unidad;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ejercito extends Atacable {

    private final Queue<Unidad> unidadesPropias;
    private final Queue<Unidad> unidadesAliadas;
    private Unidad ultimaHerida;

    public Ejercito(ArrayList<Unidad> unidades) {
        if (unidades.isEmpty()) {
            throw new IllegalArgumentException("No se puede crear un ejercito sin unidades.");
        }
        this.unidadesPropias = new LinkedList<>(unidades);
        this.unidadesAliadas = new LinkedList<>();
        this.ultimaHerida = null;
    }

    public Ejercito(int cantidad, Raza raza, Bando bando) {
        this.unidadesPropias = new LinkedList<>();
        this.unidadesAliadas = new LinkedList<>();
        this.ultimaHerida = null;
        agregarUnidades(cantidad, raza, bando);
    }

    public void agregarUnidadesPropias(int cantidad, Raza raza) {
        for (int i = 0; i < cantidad; i++) {
            unidadesPropias.add(UnidadFactory.crearUnidadConBando(raza, Bando.PROPIO));
        }
    }

    public void agregarUnidadesAliadas(int cantidad, Raza raza) {
        for (int i = 0; i < cantidad; i++) {
            unidadesAliadas.add(UnidadFactory.crearUnidadConBando(raza, Bando.ALIADO));
        }
    }

    public void agregarUnidades(int cantidad, Raza raza, Bando bando) {
        switch (bando) {
            case PROPIO, ENEMIGO -> agregarUnidadesPropias(cantidad, raza);
            case ALIADO -> agregarUnidadesAliadas(cantidad, raza);
        }
    }

    @Override
    public void atacar(Atacable enemigo) {
        Unidad atacante = getPrimeroEnAtacar();
        if (atacante != null) {
            atacante.atacar(enemigo);
        }
    }

    public boolean estaVivo() {
        return (!unidadesPropias.isEmpty() || !unidadesAliadas.isEmpty())
                || (ultimaHerida != null && ultimaHerida.estaVivo());
    }

    public void descansar() {
        descansarUnidades(unidadesPropias);
        descansarUnidades(unidadesAliadas);
        if (ultimaHerida != null) {
            ultimaHerida.descansar();
        }
    }

    private void descansarUnidades(Queue<Unidad> unidades) {
        if (unidades.isEmpty()) return;

        int size = unidades.size();
        for (int i = 0; i < size; i++) {
            Unidad unidad = unidades.poll();
            if (unidad != null) {
                unidad.descansar();
                unidades.add(unidad);
            }
        }
    }

    @Override
    public void recibirAtaque(int danio) {
        var defensor = getPrimeroEnDefender();
        if (defensor != null) {
            defensor.recibirAtaque(danio);
            if (!defensor.estaVivo()) {
                setUnidadDesmaya(defensor);
            }
        }
    }

    public void actualizarUltimoHerido() {
        Unidad ultimoEnRecibirDanio = sacarPrimeroEnDefender();

        // reincorporo al ejercito la ultima unidad herida de la batalla anterior
        if (ultimaHerida != null && ultimaHerida.estaVivo()) {
            switch (ultimaHerida.bando) {
                case PROPIO -> unidadesPropias.add(ultimaHerida);
                case ALIADO -> unidadesAliadas.add(ultimaHerida);
                case ENEMIGO -> {
                }
            }
        }

        ultimaHerida = ultimoEnRecibirDanio;
    }

    public int contarUnidadesFinales() {
        return unidadesAliadas.size() + unidadesPropias.size()
                + (ultimaHerida != null && ultimaHerida.estaVivo() ? 1 : 0);
    }

    public Unidad getUltimoHerido() {
        return ultimaHerida;
    }

    public Unidad getPrimeroEnDefender() {
        return !unidadesAliadas.isEmpty() ? unidadesAliadas.peek() :
                !unidadesPropias.isEmpty() ? unidadesPropias.peek() :
                        ultimaHerida
                ;
    }

    private Unidad sacarPrimeroEnDefender() {
        return !unidadesAliadas.isEmpty() ? unidadesAliadas.poll() :
                !unidadesPropias.isEmpty() ? unidadesPropias.poll() :
                        ultimaHerida
                ;
    }

    public Unidad getPrimeroEnAtacar() {
        return !unidadesAliadas.isEmpty() ? unidadesAliadas.peek() :
                !unidadesPropias.isEmpty() ? unidadesPropias.peek() :
                        ultimaHerida
                ;
    }

    private void setUnidadDesmaya(Unidad desmaya) {
        switch (desmaya.bando) {
            case PROPIO, ENEMIGO -> unidadesPropias.poll();
            case ALIADO -> unidadesAliadas.poll();
        }
    }

    public int getSalud() {
        int saludTotal = 0;

        for (Unidad unidad : unidadesPropias) {
            saludTotal += unidad.getSalud();
        }

        for (Unidad unidad : unidadesAliadas) {
            saludTotal += unidad.getSalud();
        }

        if (ultimaHerida != null && ultimaHerida.estaVivo()) {
            saludTotal += ultimaHerida.getSalud();
        }

        return saludTotal;
    }
}