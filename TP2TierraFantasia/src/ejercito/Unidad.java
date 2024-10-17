package ejercito;

public abstract class Unidad implements Atacable{
	protected int salud;
    protected int saludMaxima;
    protected int danioBase;
    protected int rangoAtaqueMin;
    protected int rangoAtaqueMax;
    protected Estado estado;
    protected Bando bando;

    public Unidad (int salud, int saludMaxima, int danioBase, int rangoAtaqueMin, 
    			int rangoAtaqueMax, Bando bando, Estado estado) {
        this.salud = salud;
        this.saludMaxima = saludMaxima;
        this.danioBase = danioBase;
        this.rangoAtaqueMin = rangoAtaqueMin;
        this.rangoAtaqueMax = rangoAtaqueMax;
        this.bando = bando;
        this.estado = estado;
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
    
    public void ModificarEstado(Estado estado) {
    	this.estado = estado;
    }
    
    public Estado verEstado() {
    	return this.estado;
    }
    
    public Bando verBando() {
    	return this.bando;
    }
}
