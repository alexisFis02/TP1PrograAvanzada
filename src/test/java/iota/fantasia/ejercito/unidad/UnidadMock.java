package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;

// renombrar a maniqui
public class UnidadMock extends Unidad {
    public UnidadMock(int salud) {
        super(salud, salud, 0, 0, 0);
    }

    @Override
    public void atacar(Atacable enemigo) {}

    @Override
    public void descansar() {}
}