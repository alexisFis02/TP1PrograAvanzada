package iota.fantasia.ejercito;

public abstract class Atacable {
	protected Bando bando;
    
	public abstract void atacar(Atacable enemigo);
	
	public abstract void recibirAtaque(int danio);
	
	public abstract void descansar();
	
	protected Bando getBando() {
		return bando;
	};
}
