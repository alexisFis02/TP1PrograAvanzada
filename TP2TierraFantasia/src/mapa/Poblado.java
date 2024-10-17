package mapa;

import ejercito.Bando;
import ejercito.RazaNativa;

public class Poblado {
	private int poblado;
	private int cantHabitantes;
	private RazaNativa raza;
	private Bando bando;
	
	public Poblado(int nroPoblado,  int cantHabitantes, RazaNativa raza, Bando bando) {
		this.poblado = nroPoblado;
		this.cantHabitantes = cantHabitantes;
		this.raza = raza;
		this.bando = bando;
	}
	
	public int devolverNroPoblado() {
		return this.poblado;
	}
	
	public int devolverCantHabitantes() {
		return this.cantHabitantes;
	}
	
	public RazaNativa devolverRazaNativas() {
		return this.raza;
	}
	
	public Bando devolverBandoHabitantes() {
		return this.bando;
	}
	
	@Override
	public String toString() {
		int nroPoblado = this.devolverNroPoblado() + 1;
		return "Numero de poblado: " + nroPoblado + 
				" Cantidad de habitantes: " + this.devolverCantHabitantes() +
				" Razas  nativas: " + this.devolverRazaNativas() +
				" Bando del poblado: " + this.devolverBandoHabitantes();
	}
}
