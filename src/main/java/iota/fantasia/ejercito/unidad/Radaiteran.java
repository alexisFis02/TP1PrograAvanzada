package iota.fantasia.ejercito.unidad;

/*
* Una Radaiteran tiene una salud inicial de 36. Utiliza shurikens, y su rango de ataque es de 17 a 41 metros.
* Ocasiona un daño básico de 56 puntos. Cuando ataca, lo hace cada vez con más fuerza (3 de daño extra x la cantidad de ataques dados).
* Al recibir un ataque lo hace normalmente. Cuando descansa, no le sucede nada.
* */
public class Radaiteran extends Unidad {
    private int contadorAtaques = 0;

    public Radaiteran() {
        super(53, 53, 27, 5, 46);
    }

    @Override
    public void atacar(Unidad enemigo) {

    }

    @Override
    public void descansar() {

    }
}
