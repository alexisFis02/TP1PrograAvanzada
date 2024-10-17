package ejercito;

/*
* Un Nortaichian tiene una salud inicial de 66. Utiliza un arco, y su rango de ataque es de 16 a 22 metros.
* Ocasiona un daño básico de 18 puntos. Cuando ataca, se cura un 4 por ciento de su salud.
* Al recibir un ataque se enfurece y sus ataques multiplican por 2 su daño (dura 2 turnos propios).
* Cuando descansa, recupera toda su salud, pero se vuelve de piedra por 2 turnos (contiguos), lo que hace que no pueda atacar, pero reduce el daño entrante en 1/2.
* */

public class Nortaichan extends Unidad{
	private boolean enfurecido = false;
    private boolean dePiedra = false;
    private int contadorAtaques = 0;
    private int contadorDescansos = 0;

    public Nortaichan(Bando bando) {
        super(66, 66, 18, 16, 22, bando, Estado.SANO);
    }

    @Override
    public void atacar(Unidad enemigo) {
        // TODO: completar metodo
    }

    @Override
    public void descansar() {
        // TODO: completar metodo
    }

    @Override
    public void recibirAtaque(int danio) {
        // TODO: completar metodo
    }
    
    @Override
    public String toString() {
    	return "[ Raza: Nortaichan, Bando: " + this.bando  + 
    			" Estado: " + this.estado  + " ]";
    }
}
