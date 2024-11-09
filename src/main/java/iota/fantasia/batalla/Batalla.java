package iota.fantasia.batalla;

import iota.fantasia.ejercito.Ejercito;

public class Batalla {
    private final Ejercito ejercitoAliado;
    private final Ejercito ejercitoEnemigo;

    public Batalla(Ejercito ejercitoAliado, Ejercito ejercitoEnemigo) {
        this.ejercitoAliado = ejercitoAliado;
        this.ejercitoEnemigo = ejercitoEnemigo;
    }

    public boolean simularBatalla() {
        while (ejercitoAliado.tieneUnidadesVivas() && ejercitoEnemigo.tieneUnidadesVivas()) {
            ejercitoAliado.atacar(ejercitoEnemigo);
            if (ejercitoEnemigo.tieneUnidadesVivas()) {
                ejercitoEnemigo.atacar(ejercitoAliado);
            }
        }
        return ejercitoAliado.tieneUnidadesVivas();
    }
}
