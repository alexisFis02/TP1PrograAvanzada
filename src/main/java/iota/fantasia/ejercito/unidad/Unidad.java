package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;

public abstract class Unidad extends Atacable implements Comparable<Unidad> {

    protected int salud;
    protected int saludMaxima;
    protected int danioBase;
    protected int rangoAtaqueMin;
    protected int rangoAtaqueMax;
    
    public Unidad(int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, int rangoAtaqueMax) {
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.danioBase = danioBase;
        this.rangoAtaqueMin = rangoAtaqueMin;
        this.rangoAtaqueMax = rangoAtaqueMax;
    }

    public void recibirAtaque(int danio) {
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

    public int getRangoAtaqueMin() {
        return rangoAtaqueMin;
    }

    public int getRangoAtaqueMax() {
        return rangoAtaqueMax;
    }

    @Override
    public int compareTo(Unidad otraUnidad) {
        return Integer.compare(this.salud, otraUnidad.salud);
    }

    public String getTipoUnidad() {
        return this.getClass().getSimpleName();
    }
}
