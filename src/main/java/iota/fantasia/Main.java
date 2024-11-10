package iota.fantasia;

import iota.fantasia.mapa.LectorMapa;
import iota.fantasia.mapa.records.DatosArchivo;
import iota.fantasia.mision.Mision;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Mision mision = null;
        try {
            DatosArchivo datos = LectorMapa.leerArchivo("mapa1.txt");
            mision = new Mision(datos);
        } catch (IOException e) {
            System.out.println("Error al crear la mision: " + e.getMessage());
        }
        System.out.println(mision);
    }
}