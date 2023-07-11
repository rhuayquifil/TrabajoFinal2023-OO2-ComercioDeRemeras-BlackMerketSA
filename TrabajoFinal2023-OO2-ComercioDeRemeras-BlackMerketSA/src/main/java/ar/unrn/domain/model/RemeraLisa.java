package ar.unrn.domain.model;

import ar.unrn.domain.portsout.DateTimeCheck;

public class RemeraLisa extends Remera {

	private DateTimeCheck dateTimeCheck;

	public RemeraLisa(double precioUnitario, DateTimeCheck dateTimeCheck) {
		super(precioUnitario);
		this.dateTimeCheck = dateTimeCheck;
	}

	@Override
	protected double descuentoDomingo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double descuentoSabado(int cantidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double descuentoDe8a10DeLaMañana() {
		if (dateTimeCheck.horarioEntreLas8amY10am()) {
			double precioConDescuento = precioUnitario * (1 - 0.05);
			return precioUnitario - precioConDescuento;
		}
		return 0;
	}

	@Override
	protected String nombre() {
		return "Lisa";
	}

}
