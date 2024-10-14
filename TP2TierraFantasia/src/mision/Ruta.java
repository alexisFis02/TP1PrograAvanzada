package mision;

public class Ruta {
	private int[] ruta;
	private int costoEnKilometros;
	
	public Ruta(int[] ruta, int costo) {
		this.ruta = ruta;
		this.costoEnKilometros = costo;
	}
	
	
	public int[] devolverRuta() {
		return this.ruta;
	}
	
	public int edvolverCostoEnKIlometros() {
		return this.costoEnKilometros;
	}

}
