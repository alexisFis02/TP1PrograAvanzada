package main;

import ejercito.Bando;
import ejercito.Nortaichan;
import ejercito.Reralopes;
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
		
		Poblado poblado1 = new Poblado(0, 100, new Wrives(), Bando.PROPIO);
		Poblado poblado2 = new Poblado(1, 30, new Reralopes(), Bando.ALIADO);
		Poblado poblado3 = new Poblado(2, 40, new Nortaichan(), Bando.ENEMIGO);
		Poblado poblado4 = new Poblado(3, 60, new Nortaichan(), Bando.ENEMIGO);
		Poblado[] poblados = {poblado1, poblado2, poblado3, poblado4};
		
		Poblado pobladoInicial = poblado1;
		Poblado pobladoDestino = poblado4;
		
		
	
		Mision mision = new Mision(mapaFantacia, poblados, pobladoInicial, 
				pobladoDestino);
		
		mision.calcularRuta();
		
		mision.mostrarRuta();
		
		
	     ///System.out.println(mision);
	}

}
