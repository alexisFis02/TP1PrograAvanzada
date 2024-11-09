package iota.fantasia.mapa;

public class Camino2 {
    private Poblado origen;
    private Poblado destino;
    private int distancia;

    public Camino(Poblado origen, Poblado destino, int distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public Poblado getOrigen() {
        return origen;
    }

    public Poblado getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}
