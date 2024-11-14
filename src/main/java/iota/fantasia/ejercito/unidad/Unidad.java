package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;

public abstract class Unidad extends Atacable {

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
        salud -= danio;
        if (salud <= 0) {
            salud = 0;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    /**
     * Obtiene la salud actual de la unidad
     *
     * @return salud actual
     */
    public int getSalud() {
        return salud;
    }

    /**
     * Obtiene la salud máxima de la unidad
     *
     * @return salud máxima
     */
    public int getSaludMaxima() {
        return saludMaxima;
    }

    /**
     * Obtiene el daño base que puede realizar la unidad
     *
     * @return daño base
     */
    public int getDanioBase() {
        return danioBase;
    }
}
