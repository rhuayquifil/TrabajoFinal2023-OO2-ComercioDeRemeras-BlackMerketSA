package ar.unrn.domain.model;

public class RemeraEstampada extends Remera {

	public RemeraEstampada() {
		super("Estampada");
	}

	@Override
	public double consultarMonto(int cantidad) {
		return 33;
	}

}
