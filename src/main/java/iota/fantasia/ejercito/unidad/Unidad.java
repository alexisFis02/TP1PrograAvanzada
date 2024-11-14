package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import iota.fantasia.ejercito.enums.Bando;

public abstract class Unidad extends Atacable {

    protected final int danioBase;
    protected int salud;
    protected int saludMaxima;
    protected int rangoAtaqueMin;
    protected int rangoAtaqueMax;

    public Unidad(int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, int rangoAtaqueMax, Bando bando) {
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.danioBase = danioBase;
        this.rangoAtaqueMin = rangoAtaqueMin;
        this.rangoAtaqueMax = rangoAtaqueMax;
        this.bando = bando;
    }

    public Unidad(int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, int rangoAtaqueMax) {
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.danioBase = danioBase;
        this.rangoAtaqueMin = rangoAtaqueMin;
        this.rangoAtaqueMax = rangoAtaqueMax;
        this.bando = Bando.NEUTRAL;
    }

    public void recibirAtaque(int danio) {
        salud -= danio;
        if (salud <= 0) {
            salud = 0;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public int getSalud() {
        return salud;
    }

    public int getSaludMaxima() {
        return saludMaxima;
    }

    public int getDanioBase() {
        return danioBase;
    }
}
