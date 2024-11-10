package iota.fantasia.ejercito.unidad;

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
    public void atacar(Unidad enemigo) {
        if (!estaVivo() || !enemigo.estaVivo()) {
            return;
        }

        contadorAtaques++;
        // Erra 2 de cada 4 ataques
        if (contadorAtaques % 4 == 2 || contadorAtaques % 4 == 3) {
            return;
        }

        int danioTotal = danioBase;
        if (concentrado && ataquesPotenciados > 0) {
            danioTotal *= 2;
            ataquesPotenciados--;
        }

        enemigo.recibirAtaque(danioTotal);
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
}
