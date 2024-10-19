package main;

import colaDePrioridad.Comparador;
import colaDePrioridad.ComparadorUnidad;
import ejercito.Bando;
import ejercito.Ejercito;
import ejercito.Estado;
import ejercito.RazaNativa;
import ejercito.Reralopes;
import ejercito.Unidad;
import ejercito.Wrives;
import mapa.Mapa;
import mapa.Poblado;
import mision.Mision;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		int[][] mapa = {
				{Integer.MAX_VALUE,10,20,Integer.MAX_VALUE},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,5,Integer.MAX_VALUE},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,7},
				{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE}
					 };
		
		Mapa mapaFantacia = Mapa.instanciar(mapa);
		
		Poblado poblado1 = new Poblado(0, 100, RazaNativa.WRIVES, Bando.PROPIO);
		Poblado poblado2 = new Poblado(1, 30, RazaNativa.RERALOPES, Bando.ALIADO);
		Poblado poblado3 = new Poblado(2, 40, RazaNativa.NORTAICHAN, Bando.ENEMIGO);
		Poblado poblado4 = new Poblado(3, 60, RazaNativa.NORTAICHAN, Bando.ENEMIGO);
		Poblado[] poblados = {poblado1, poblado2, poblado3, poblado4};
		
		Poblado pobladoInicial = poblado1;
		Poblado pobladoDestino = poblado4;
		
		
	
		Mision mision = new Mision(mapaFantacia, poblados, pobladoInicial, 
				pobladoDestino);
		
		mision.calcularRuta();
		
		mision.mostrarRuta();
		
		Comparador alineacionEjercito = new ComparadorUnidad();
		
		Ejercito ejercito = new Ejercito(20, alineacionEjercito);
		for(int i = 0; i < 5; i++) {
			ejercito.agregarUnidad(new Wrives(Bando.PROPIO));
		}
		
		for(int i = 0; i < 5; i++) {
			ejercito.agregarUnidad(new Reralopes(Bando.ALIADO));
		}
		
		for(int i = 0; i < 4; i++) {
			Unidad unidad = new Reralopes(Bando.ALIADO);
			unidad.ModificarEstado(Estado.HERIDO);
			ejercito.agregarUnidad(unidad);
		}
		
		
		System.out.println("Formacion Ejecito: ");
		while(!ejercito.tieneUnidadesVivas()) {
			Unidad unidad = ejercito.devolverUnidad();
			System.out.println(unidad);
		}
		
	     ///System.out.println(mision);
	}

}
