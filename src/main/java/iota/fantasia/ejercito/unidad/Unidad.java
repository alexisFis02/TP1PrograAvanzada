package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;

public abstract class Unidad extends Atacable implements Comparable<Unidad>{
    protected int salud;
    protected int saludMaxima;
    protected int danioBase;
    protected int rangoAtaqueMin;
    protected int rangoAtaqueMax;

    public Unidad (int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, int rangoAtaqueMax) {
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.danioBase = danioBase;
        this.rangoAtaqueMin = rangoAtaqueMin;
        this.rangoAtaqueMax = rangoAtaqueMax;
    }

    public abstract void atacar(Atacable enemigo);
    public abstract void descansar();
    public void recibirAtaque(int danio) {
        salud -= danio;
        if (salud < 0) {
            salud = 0;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }
}
