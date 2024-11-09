package iota.fantasia.ejercito.unidad;

/*
* Un Wrives tiene una salud inicial de 108. Utiliza magia, y su rango de ataque es de 14 a 28 metros.
* Ocasiona un daño básico de 113 puntos. Cuando ataca, lo hace con 2 veces su daño, cada 2 ataques.
* Al recibir un ataque recibe 2 veces el daño, ya que no lleva armadura.
* Cuando descansa, medita, y como considera la violencia como algo malo, se rehúsa a atacar hasta que lo ataquen.
* Gracias a esto, aumenta su salud y su salud máxima en 50.
* */
public class Wrives extends Unidad{
    private boolean haSidoAtacado = false;
    private int contadorAtaques = 0;

    public Wrives() {
        // TODO: pasar a constantes
        super(108,108,113,14,28);
    }

    @Override
    public void atacar(Unidad enemigo) {
        if (!estaVivo() || !enemigo.estaVivo() || !haSidoAtacado) {
            return;
        }

        contadorAtaques++;
        int danioTotal = danioBase;
        if (contadorAtaques % 2 == 0) {
            danioTotal *= 2;
        }
        
        enemigo.recibirAtaque(danioTotal);
    }

    @Override
    public void descansar() {
        haSidoAtacado = false;
        saludMaxima += 50;
        salud += 50;
    }

    @Override
    public void recibirAtaque(int danio) {
        haSidoAtacado = true;
        super.recibirAtaque(danio * 2);
    }
}
