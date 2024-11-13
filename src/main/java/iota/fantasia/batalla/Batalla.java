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
        while (ejercitoAliado.estaVivo() && ejercitoEnemigo.estaVivo()) {
            ejercitoAliado.atacar(ejercitoEnemigo);
            if (ejercitoEnemigo.estaVivo()) {
                ejercitoEnemigo.atacar(ejercitoAliado);
            }
        }
        return ejercitoAliado.estaVivo();
    }
}
