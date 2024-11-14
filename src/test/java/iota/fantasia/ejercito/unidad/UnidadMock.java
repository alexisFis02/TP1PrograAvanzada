package iota.fantasia.ejercito.unidad;

import iota.fantasia.ejercito.Atacable;
import iota.fantasia.ejercito.enums.Bando;

public class UnidadMock extends Unidad {
    public UnidadMock(int salud) {
        super(salud, salud, 0, 0, 0);
    }

    public UnidadMock(int salud, Bando bando){
        super(salud, salud, 0, 0, 0, bando);
    }

    @Override
    public void atacar(Atacable enemigo) {
    }

    @Override
    public void descansar() {
        salud += 10;
    }
}