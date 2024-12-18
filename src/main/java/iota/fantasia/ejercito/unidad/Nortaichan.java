package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import iota.fantasia.ejercito.enums.Bando;

/*
 * Un Nortaichian tiene una salud inicial de 66. Utiliza un arco, y su rango de ataque es de 16 a 22 metros.
 * Ocasiona un daño básico de 18 puntos. Cuando ataca, se cura un 4 por ciento de su salud.
 * Al recibir un ataque se enfurece y sus ataques multiplican por 2 su daño (dura 2 turnos propios).
 * Cuando descansa, recupera toda su salud, pero se vuelve de piedra por 2 turnos (contiguos),
 * lo que hace que no pueda atacar, pero reduce el daño entrante en 1/2.
 * */
public class Nortaichan extends Unidad {
    private boolean enfurecido = false;
    private boolean dePiedra = false;
    private int turnosEnfurecido = 0;
    private int turnosPiedra = 0;

    public Nortaichan() {
        super(66, 66, 18, 16, 22, null);
    }

    public Nortaichan(Bando bando) {
        super(66, 66, 18, 16, 22, bando);
    }

    public void atacar(Atacable enemigo) {
        if (dePiedra) {
            turnosPiedra--;
            return;
        }

        enemigo.recibirAtaque(getDanioAlAtacar());

        recuperarSalud();
    }

    private int getDanioAlAtacar() {
        int danio = danioBase;
        if (enfurecido) {
            danio *= 2;
            turnosEnfurecido--;
            if (turnosEnfurecido <= 0) {
                enfurecido = false;
            }
        }
        return danio;
    }

    // TODO: esto esta bien?
    private void recuperarSalud() {
        int curacion = (int) (saludMaxima * 0.04);
        salud = Math.min(saludMaxima, salud + curacion);
    }

    @Override
    public void descansar() {
        // Recupera toda su salud
        salud = saludMaxima;

        // Se vuelve de piedra por 2 turnos
        dePiedra = true;
        turnosPiedra = 2;
    }

    /*
     * Se asume que no se puede enfurecer cuando esta de piedra
     * */
    @Override
    public void recibirAtaque(int danio) {
        int danioFinal = dePiedra ? danio / 2 : danio;
        super.recibirAtaque(danioFinal);

        if (dePiedra) {
            turnosPiedra--;
            if (turnosPiedra <= 0) {
                dePiedra = false;
            }
        } else {
            // Se enfurece por 2 turnos al recibir daño
            enfurecido = true;
            turnosEnfurecido = 2;
        }
    }

    public boolean isEnfurecido() {
        return enfurecido;
    }

    public int getTurnosEnfurecido() {
        return turnosEnfurecido;
    }

    public boolean isDePiedra() {
        return dePiedra;
    }
}
