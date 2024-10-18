package colaDePrioridad;

import ejercito.Unidad;

public class Monticulo {
	private Unidad[] monticulo;
	private int tam;
	
public Monticulo(int tamMonticulo) {
	this.monticulo = new Unidad[tamMonticulo];
	this.tam = 0;
}






public void agregarElementoMonticulo(Unidad elem) {
	int padre = (this.tam + 1) / 2;
	int hijo = this.tam + 1;
		
	if(hijo < this.monticulo.length) {
		this.monticulo[hijo] = elem;
	}
	
	while(padre > 0 && hijo < this.monticulo.length && 
			ComparadorUnidad.comparar(elem, this.monticulo[padre]) < 0) {
		Unidad aux = this.monticulo[padre];
		this.monticulo[padre] = elem;
		this.monticulo[hijo] = aux;
		
		hijo = padre;
		padre = padre / 2;
	}
	
	this.tam++;
}







public Unidad removerElementoMonticulo() {
	if(this.tam == 0) {
		throw new RuntimeException("El monticulo esta vacio");
	}
	
	Unidad primero = this.monticulo[1];
	this.monticulo[1] = this.monticulo[this.tam];
	this.tam--;
	int alturaMonticulo = (int) Math.floor(Math.log(tam) / Math.log(2));
	
	int padre = 1;
	int hijo;
	
	while(alturaMonticulo > 0) {
		if(this.tam >= padre * 2 + 1) {
			if(ComparadorUnidad.comparar(this.monticulo[padre * 2], this.monticulo[(padre * 2) + 1]) < 0){
				hijo = padre * 2;
			}
			else {
				hijo = padre * 2 + 1;
			}
			
			if(ComparadorUnidad.comparar(this.monticulo[hijo], this.monticulo[padre]) < 0){
				Unidad aux = this.monticulo[padre];
				this.monticulo[padre] = this.monticulo[hijo];
				this.monticulo[hijo] = aux;
				
				padre = hijo;
			}
		}
		
		if(this.tam == padre * 2) {
			if(ComparadorUnidad.comparar(this.monticulo[padre * 2], this.monticulo[padre]) < 0){
				Unidad aux = this.monticulo[padre];
				this.monticulo[padre] = this.monticulo[padre * 2];
				this.monticulo[padre * 2] = aux;
				
				padre = padre * 2;
			}	
		}
		
		
		alturaMonticulo--;
	}

		return primero;
}


public boolean monticuloVacio() {
	return this.tam == 0? true : false;
}




public void mostrarMonticulo() {
	for(int i = 1; i <= this.tam && i < this.monticulo.length; i++) {
		System.out.println(this.monticulo[i] + " ");
	}
}




}
