package iota.fantasia.mision;

import iota.fantasia.mapa.Mapa;
import iota.fantasia.mision.calculador.CalculadorMision;
public class Mision {
    private final Mapa mapa;
    private final CalculadorMision calculador;

    public Mision(String archivoConfiguracion) {
        this.mapa = new Mapa(archivoConfiguracion);
        this.calculador = new CalculadorMision(mapa);
    }

    public String cantidadDeGuerrerosFinales() {
        if (!esMisionFactible()) {
            return "La misión no es factible";
        }
        return "Guerreros sobrevivientes: " + calculador.calcularGuerrerosFinales();
    }

    public boolean esMisionFactible() {
        return calculador.esMisionFactible();
    }

    public String calcularTiempoTotal() {
        if (!esMisionFactible()) {
            return "La misión no es factible";
        }
        int dias = calculador.calcularTiempoTotal();
        return "Tiempo total: " + dias + " días";
    }

    @Override
    public String toString() {
        return """
               Resumen de la misión: 
                - ¿Es factible? %s
                - 
                
               """;
    }
}
