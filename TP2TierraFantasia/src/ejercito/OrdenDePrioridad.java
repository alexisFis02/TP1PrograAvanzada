package ejercito;

public enum OrdenDePrioridad {
	ALIADO_NO_HERIDO(1), ALIADAO_HERIDO(2), PROPIO_NO_HERIDO(3), PROPIO_HERIDO(4);
	
	private final int prioridad;
	
	OrdenDePrioridad(int prioridad){
		this.prioridad = prioridad;
	}
	
	public int getPrioridad() {
		return this.prioridad;
	}
}
