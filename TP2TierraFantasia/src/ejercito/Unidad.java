package ejercito;

public abstract class Unidad implements Atacable{
	protected int salud;
    protected int saludMaxima;
    protected int danioBase;
    protected int rangoAtaqueMin;
    protected int rangoAtaqueMax;
    protected OrdenDePrioridad condicionUnidad;

    public Unidad (int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, 
    			int rangoAtaqueMax) {
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
    
    public void setCondicionUnidad(OrdenDePrioridad condicion) {
    	this.condicionUnidad = condicion;
    }
    
    public OrdenDePrioridad getCondicionUnidad() {
    	return this.condicionUnidad;
    }
    
}
