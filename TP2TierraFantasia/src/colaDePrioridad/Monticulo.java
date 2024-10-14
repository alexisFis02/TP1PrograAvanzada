package colaDePrioridad;


public class Monticulo<T> {
	private T[] monticulo;
	private Comparador<T> comparador;
	private int tam;
	
public Monticulo(int tamMonticulo, Comparador<T> comparador) {
	this.monticulo = (T[]) new Object[tamMonticulo];
	this.comparador = comparador;
	this.tam = 0;
}






public void agregarElementoMonticulo(T elem) {
	int padre = (this.tam + 1) / 2;
	int hijo = this.tam + 1;
	this.monticulo[hijo] = elem;
	
	while(padre > 0 && this.comparador.comparar(elem, this.monticulo[padre]) < 0) {
		T aux = this.monticulo[padre];
		this.monticulo[padre] = elem;
		this.monticulo[hijo] = aux;
		
		hijo = padre;
		padre = padre / 2;
	}
	
	this.tam++;
}







public T removerElementoMonticulo() {
	if(this.tam == 0) {
		throw new RuntimeException("El monticulo esta vacio");
	}
	
	T primero = this.monticulo[1];
	this.monticulo[1] = this.monticulo[this.tam];
	this.tam--;
	int alturaMonticulo = (int) Math.floor(Math.log(tam) / Math.log(2));
	
	int padre = 1;
	
	while(alturaMonticulo - 1 > 0) {
		if(this.comparador.comparar(this.monticulo[padre * 2], this.monticulo[(padre * 2) + 1]) < 0){
			T aux = this.monticulo[padre];
			this.monticulo[padre] = this.monticulo[padre * 2];
			this.monticulo[padre * 2] = aux;
			
			padre = padre * 2;
		}
		else {
			T aux = this.monticulo[padre];
			this.monticulo[padre] = this.monticulo[(padre * 2) + 1];
			this.monticulo[(padre * 2) + 1] = aux;
			
			padre = (padre * 2) + 1;
		}
		
		alturaMonticulo--;
	}
	
	
	if(this.tam > padre) {
		if(this.tam < ((padre * 2) + 1)  && 
				this.comparador.comparar(this.monticulo[padre], this.monticulo[padre * 2]) < 0) {
				T aux = this.monticulo[padre];
				this.monticulo[padre] = this.monticulo[padre * 2];
				this.monticulo[padre * 2] = aux;
		}
		else {
			int hijo;
			if(this.comparador.comparar(this.monticulo[padre * 2], this.monticulo[(padre * 2) + 1]) < 0) {
				hijo = padre + 2; 
			}
			else {
				hijo = (padre * 2) + 1;
			}
			
			if(this.comparador.comparar(this.monticulo[hijo], this.monticulo[padre]) < 0) {
				T aux = this.monticulo[padre];
				this.monticulo[padre] = this.monticulo[hijo];
				this.monticulo[hijo] = aux;
				}
			}
		}
	
	
		return primero;
}







public void mostrarMonticulo() {
	for(int i = 1; i <= this.tam; i++) {
		System.out.print(this.monticulo[i] + " ");
	}
}




}
