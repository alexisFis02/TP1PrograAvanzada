package iota.fantasia.batalla;

import iota.fantasia.ejercito.Atacable;
import iota.fantasia.ejercito.Ejercito;
import iota.fantasia.mision.calculador.CalculadorMision;

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
			System.out.println("Despues de atacar");
			if (!ejercitoAliado.estaVivo()) {
				System.out.println("Entra aca");
				
				batallaEnCurso = false;
				resultado = false;
			}
		}
		System.out.println("Despues de pelear");
		return resultado;
    }
}
