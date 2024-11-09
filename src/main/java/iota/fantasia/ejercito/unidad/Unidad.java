package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;

public abstract class Unidad extends Atacable implements Comparable<Unidad> {
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

    public abstract void atacar(Unidad enemigo);
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

    /**
     * Obtiene la salud actual de la unidad
     * @return salud actual
     */
    public int getSalud() {
        return salud;
    }

    /**
     * Obtiene la salud máxima de la unidad
     * @return salud máxima
     */
    public int getSaludMaxima() {
        return saludMaxima;
    }

    /**
     * Obtiene el daño base que puede realizar la unidad
     * @return daño base
     */
    public int getDanioBase() {
        return danioBase;
    }

    /**
     * Obtiene el rango mínimo de ataque de la unidad
     * @return rango mínimo de ataque
     */
    public int getRangoAtaqueMin() {
        return rangoAtaqueMin;
    }

    /**
     * Obtiene el rango máximo de ataque de la unidad
     * @return rango máximo de ataque
     */
    public int getRangoAtaqueMax() {
        return rangoAtaqueMax;
    }

    /**
     * Compara esta unidad con otra basándose en su salud actual
     * @param otraUnidad unidad a comparar
     * @return un valor negativo si esta unidad tiene menos salud,
     *         cero si tienen la misma salud,
     *         un valor positivo si esta unidad tiene más salud
     */
    @Override
    public int compareTo(Unidad otraUnidad) {
        return Integer.compare(this.salud, otraUnidad.salud);
    }
}
