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
        boolean batallaEnCurso = true;
        boolean resultado = false;

        while (batallaEnCurso) {
            ejercitoAliado.atacar(ejercitoEnemigo);
            if (!ejercitoEnemigo.estaVivo()) {
                batallaEnCurso = false;
                resultado = true;
                continue;
            }

            ejercitoEnemigo.atacar(ejercitoAliado);
            if (!ejercitoAliado.estaVivo()) {
                batallaEnCurso = false;
            }
        }
        return resultado;
    }
}
