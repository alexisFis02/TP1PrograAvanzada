package iota.fantasia.ejercito.unidad;

/*
* Una Reralopes tiene una salud inicial de 53. Utiliza una catapulta, y su rango de ataque es de 5 a 46 metros.
* Ocasiona un daño básico de 27 puntos. Cuando ataca, erra 2 de cada 4 ataques.
* Al recibir un ataque se desconcentra y sus ataques vuelven al valor normal inicial.
* Cuando descansa, se concentra y sus próximos 3 ataques (de esa unidad) dañan el doble del valor correspondiente.
* */
public class Reralopes extends Unidad{
    private int erroresConsecutivos = 0;
    private int ataquesConsecutivos = 0;

    public Reralopes() {
        super(53, 53, 27, 5, 46);
    }

    @Override
    public void atacar(Unidad enemigo) {
        // TODO: completar metodo
    }

    @Override
    public void descansar() {
        // TODO: completar metodo
    }
}
