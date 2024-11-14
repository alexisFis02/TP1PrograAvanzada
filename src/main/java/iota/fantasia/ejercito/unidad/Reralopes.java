package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
/*
 * Una Reralopes tiene una salud inicial de 53. Utiliza una catapulta, y su rango de ataque es de 5 a 46 metros.
 * Ocasiona un daño básico de 27 puntos. Cuando ataca, erra 2 de cada 4 ataques.
 * Al recibir un ataque se desconcentra y sus ataques vuelven al valor normal inicial.
 * Cuando descansa, se concentra y sus próximos 3 ataques (de esa unidad) dañan el doble del valor correspondiente.
 * */
public class Reralopes extends Unidad {
    private int contadorAtaques = 0;
    private int ataquesPotenciados = 0;
    private boolean concentrado = false;

    public Reralopes() {
        super(53, 53, 27, 5, 46);
    }

    @Override
    public void atacar(Atacable enemigo) {
        contadorAtaques++;

        // Erra 2 de cada 4 ataques
        if (! (contadorAtaques % 2 == 0)) {
            enemigo.recibirAtaque(getDanioAlAtacar());
        }

        if (concentrado) {
            ataquesPotenciados--;
        }
    }

    private int getDanioAlAtacar() {
        return ataquesPotenciados > 0 ? danioBase * 2 : danioBase;
    }

    @Override
    public void descansar() {
        concentrado = true;
        ataquesPotenciados = 3;
    }

    @Override
    public void recibirAtaque(int danio) {
        super.recibirAtaque(danio);
        // Se desconcentra al recibir daño
        concentrado = false;
        ataquesPotenciados = 0;
    }

    public boolean isConcentrado() {
        return concentrado;
    }

    public int getAtaquesPotenciados() {
        return ataquesPotenciados;
    }
}
