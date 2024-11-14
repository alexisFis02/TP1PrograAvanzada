package iota.fantasia.ejercito;

import iota.fantasia.ejercito.enums.Bando;

public abstract class Atacable {
	protected Bando bando;
    
	public abstract void atacar(Atacable enemigo);
	
	public abstract void recibirAtaque(int danio);
	
	public abstract void descansar();
	
	public abstract boolean estaVivo();
	
	protected Bando getBando() {
		return bando;
	};

	public int getSalud() {
		return 0;
	}
}
