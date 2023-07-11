package ar.unrn.domain.model;

import ar.unrn.domain.portsout.DateTimeCheck;

public class RemeraEstampada extends Remera {

	private DateTimeCheck dateTimeCheck;

	public RemeraEstampada(double precioUnitario, DateTimeCheck dateTimeCheck) {
		super(precioUnitario);
		this.dateTimeCheck = dateTimeCheck;
	}

	@Override
	protected double descuentoDomingo() {
		if (dateTimeCheck.esDomingo()) {
			double precioConDescuento = precioUnitario * (1 - 0.1);
			return precioUnitario - precioConDescuento;
		}
		return 0;
	}

	@Override
	protected double descuentoSabado(int cantidad) {
		if (dateTimeCheck.esSabado() & cantidad > 3) {
			double precioConDescuento = precioUnitario * (1 - 0.12);
			return (precioUnitario - precioConDescuento) * cantidad;
		}
		return 0;
	}

	@Override
	protected double descuentoDe8a10DeLaMañana() {
		return 0;
	}

	@Override
	protected String nombre() {
		return "Estampada";
	}
}
