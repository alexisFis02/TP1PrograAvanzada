package mapa;

public class Mapa {
	private int[][] mapa;
	
	private static Mapa instance; 
	
	private Mapa(int[][] mapa) {
		this.mapa = mapa;
	}
	
	
	public static Mapa instanciar(int[][] mapa) {
		if(instance == null) {
			instance = new Mapa(mapa);
		}
		
		return instance;
	}
	
	public int[][] devolverInstancia() {
		if(instance == null) {
			return null;
		}
		
		return this.mapa;
	}

	
	@Override
	public String toString() {
		String mapa = "";
		for(int i = 0; i < this.mapa.length; i++) {
			for(int j = 0; j < this.mapa[i].length; j++) {
				mapa = this.mapa[i][j] + " ";
			}
			
			mapa = "/n";
		}
		
		return mapa;
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
