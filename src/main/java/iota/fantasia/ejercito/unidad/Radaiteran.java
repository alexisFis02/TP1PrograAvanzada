package iota.fantasia.ejercito.unidad;

/*
 * Una Radaiteran tiene una salud inicial de 36. Utiliza shurikens, y su rango de ataque es de 17 a 41 metros.
 * Ocasiona un daño básico de 56 puntos. Cuando ataca, lo hace cada vez con más fuerza (3 de daño extra x la cantidad de ataques dados).
 * Al recibir un ataque lo hace normalmente. Cuando descansa, no le sucede nada.
 * */
public class Radaiteran extends Unidad {
    private int contadorAtaques = 0;

    public Radaiteran() {
        super(36, 36, 56, 17, 41);
    }

    @Override
    public void atacar(Unidad enemigo) {
        if (!estaVivo() || !enemigo.estaVivo()) {
            return;
        }

        contadorAtaques++;
        int danioTotal = danioBase + (3 * contadorAtaques);
        enemigo.recibirAtaque(danioTotal);
    }

    @Override
    public void descansar() {
        // No hace nada al descansar
    }
}
