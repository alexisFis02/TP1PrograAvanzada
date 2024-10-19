package calculadorDeRuta;

public class DijkstraClass {
	private int[][] matrizDeAdyacencias;
	private int[] vecCostoMinimo;
	private int[] vecPrededcesores;
	
	public DijkstraClass(int[][] matriz){
		this.matrizDeAdyacencias = matriz;
		this.vecCostoMinimo = new int[matriz.length];
		this.vecPrededcesores = new int[matriz.length];
	}
	
	public void calcularDijkstra(int nodoInicial) {
		this.vecCostoMinimo = this.matrizDeAdyacencias[nodoInicial];
		boolean[] nodosVisitados = new boolean[this.matrizDeAdyacencias.length];
		
		nodosVisitados[nodoInicial] = true;
		this.vecCostoMinimo[nodoInicial] = 0;
		this.vecPrededcesores[nodoInicial] = nodoInicial;
		
		int ariMin;
		ariMin = buscarAristaMin(this.vecCostoMinimo, nodosVisitados);
		this.vecPrededcesores[ariMin] = nodoInicial;
		
		int nodoAAnalizar = ariMin;
		for(int i = 0; i < (this.vecCostoMinimo.length - 2); i++) {
			nodosVisitados[nodoAAnalizar] = true;
			for(int j = 0; j < this.vecCostoMinimo.length; j++) {
				
				int costoIntermedio = this.vecCostoMinimo[nodoAAnalizar] == Integer.MAX_VALUE
						          	|| this.matrizDeAdyacencias[nodoAAnalizar][j] == Integer.MAX_VALUE? Integer.MAX_VALUE : this.vecCostoMinimo[nodoAAnalizar] + 
											this.matrizDeAdyacencias[nodoAAnalizar][j];
				
				if(nodosVisitados[j] == false && 
						this.vecCostoMinimo[j] > costoIntermedio) {
					
					this.vecCostoMinimo[j] = costoIntermedio;
					this.vecPrededcesores[j] = nodoAAnalizar;
				}
			}
			
			nodoAAnalizar = buscarAristaMin(this.matrizDeAdyacencias[nodoAAnalizar], nodosVisitados);
		}
	}
	
	private int buscarAristaMin(int[] vec, boolean[] vecVisitados) {
		int i = 0;
		while(vecVisitados[i] == true) {
			i++;
		}
		
		int men = i;
		for(int j = i + 1; j < vec.length; j++) {
			if(vecVisitados[j] != true && vec[j] < vec[men]){
				men = j;
			}
		}
		
		return men;
	}
	
	
	public int[] getVecPredecesores() {
		return this.vecPrededcesores;
	}
	
	public int[] getVecCostoMinimo() {
		return this.vecCostoMinimo;
	}
	
	public void mostrarDijkstraResultados() {
		
		int[] vecMin = this.vecCostoMinimo;
		System.out.println("vector de costo minimos");
		for(int i = 0; i < vecMin.length; i++) {
			System.out.print(vecMin[i] + " ");
		}
		
		int[] vecPred = this.vecPrededcesores;
		System.out.println(" ");
		System.out.println("vector de predecesores");
		
		for(int i = 0; i < vecPred.length; i++) {
			System.out.print(vecPred[i] + " ");
		}
	}

}
