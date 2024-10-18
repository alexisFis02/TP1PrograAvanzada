package main;

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

	public static void main(String[] args) {
		int[][] mapa = {
				{1000000,10,20,1000000},
				{1000000,1000000,5,1000000},
				{1000000,1000000,1000000,7},
				{1000000,1000000,1000000,1000000}
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
		
		Ejercito ejercito = new Ejercito(20);
		for(int i = 0; i < 5; i++) {
			ejercito.agregarUnidad(new Wrives(Bando.PROPIO));
		}
		
		for(int i = 0; i < 5; i++) {
			ejercito.agregarUnidad(new Reralopes(Bando.ALIADO));
		}
		
		for(int i = 0; i < 3; i++) {
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
