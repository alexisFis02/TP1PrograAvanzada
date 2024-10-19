package mapa;

import java.util.Stack;

import calculadorDeRuta.DijkstraClass;

public class Camino {
	private Mapa mapa;
	private Poblado[] poblados;
	private Poblado pobladoInicial;
	private Poblado pobladoDestino;
	private int costoEnKilometros;
	private Stack<Poblado> ruta;
	
	public Camino(Mapa mapa, Poblado[] poblados, Poblado pobladoInicial, 
					Poblado pobladoFinal) {
		
		this.mapa = mapa;
		this.poblados = poblados;
		this.pobladoInicial = pobladoInicial;
		this.pobladoDestino = pobladoFinal;
		this.ruta = new Stack<>();
	}
	
	
	public void calcularCamino() {
    	int[] predecesores;
    	int[] costos;
    	
    	DijkstraClass calculadorDeRuta = new DijkstraClass(this.mapa.devolverInstancia());
    	
    	calculadorDeRuta.calcularDijkstra(this.pobladoInicial.devolverNroPoblado());
    	
    	costos = calculadorDeRuta.getVecCostoMinimo();
    	predecesores = calculadorDeRuta.getVecPredecesores();
    	
    	if(this.pobladoDestino.devolverNroPoblado() >= 0 &&
    			this.pobladoDestino.devolverNroPoblado() < costos.length
    			) {
    		
    		this.costoEnKilometros = costos[this.pobladoDestino.devolverNroPoblado()];
    	}
    	
    	
    	this.ruta.push(this.pobladoDestino);
    	
    	int i = this.pobladoDestino.devolverNroPoblado();
    	while(i >= 0 && i < predecesores.length && 
    			this.pobladoInicial.devolverNroPoblado() != predecesores[i]) {
    		
    		this.ruta.push(this.poblados[predecesores[i]]);
    		i = predecesores[i];
    	}
    	
    	this.ruta.push(this.pobladoInicial);
    }
	
	
	
	 public void mostrarCamino() {
	    	System.out.println("costo del viaje: " + this.costoEnKilometros + " kilometros");
	    	System.out.println("Recorrido de poblados: ");
	    	while(!this.ruta.isEmpty()) {
	    		Poblado poblado = this.ruta.pop();
	    		System.out.println("[ " + poblado + " ]");
	    	}
	 }
	 
	 public Poblado devolverSiguientePoblado() {
		 Poblado poblado = !this.ruta.isEmpty()? this.ruta.pop() : null;
		 
		 return poblado;
	 }
	 
	 
	
	public int devolverCostoEnKilometros() {
		return this.costoEnKilometros;
	}
	 
	 
}
