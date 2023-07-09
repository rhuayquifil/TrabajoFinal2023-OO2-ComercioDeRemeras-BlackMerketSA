package ar.unrn.domain.model;

public class RemeraLisa extends Remera {

	public RemeraLisa() {
		super("Lisa");
	}

	@Override
	public double consultarMonto(int cantidad) {
		return 50;
	}

}
