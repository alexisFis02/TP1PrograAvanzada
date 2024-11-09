package iota.fantasia.ejercito;

import iota.fantasia.ejercito.unidad.Unidad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ejercito extends Atacable {
    private final List<Unidad> unidades;

    public Ejercito(List<Unidad> unidades) {
        this.unidades = new ArrayList<>(unidades);
    }

    public void agregarUnidad(Unidad unidad) {
        unidades.add(unidad);
    }

    public void atacar(Ejercito enemigo) {
        if (!tieneUnidadesVivas() || !enemigo.tieneUnidadesVivas()) {
            return;
        }

        unidades.stream()
                .filter(Unidad::estaVivo)
                .forEach(unidad -> {
                    Unidad objetivo = enemigo.obtenerObjetivoMasDebil();
                    if (objetivo != null) {
                        unidad.atacar(objetivo);
                    }
                });
    }

    private Unidad obtenerObjetivoMasDebil() {
        return unidades.stream()
                .filter(Unidad::estaVivo)
                .min(Comparator.comparingInt(Unidad::getSalud))
                .orElse(null);
    }

    public boolean tieneUnidadesVivas() {
        return unidades.stream().anyMatch(Unidad::estaVivo);
    }

    public void descansar() {
        unidades.stream()
                .filter(Unidad::estaVivo)
                .forEach(Unidad::descansar);
    }

    @Override
    public void recibirAtaque(int danio) {
        // El ejército distribuye el daño entre todas las unidades vivas
        int unidadesVivas = (int) unidades.stream().filter(Unidad::estaVivo).count();
        if (unidadesVivas > 0) {
            int danioPorUnidad = danio / unidadesVivas;
            unidades.stream()
                    .filter(Unidad::estaVivo)
                    .forEach(unidad -> unidad.recibirAtaque(danioPorUnidad));
        }
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }
}
