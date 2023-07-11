package ar.unrn.domain.model;

public abstract class Remera {

	protected double precioUnitario;

	public Remera(double precioUnitario) {
		super();
		this.precioUnitario = precioUnitario;
	}

//	protected DateTimeCheck dateTimeCheck;
//
//	public Remera(double precioUnitario, DateTimeCheck dateTimeCheck) {
//		super();
//		this.precioUnitario = precioUnitario;
//		this.dateTimeCheck = dateTimeCheck;
//	}

	// APLICA TEMPLATHE METHOD Y RESOLVE LOS CALCULOS DE LAS REMERAS

	public double precioFinal(int cantidad) {

		// RESOLVE EL CALCULO DE LOS DESCUENTOS
		double precioFinal = (precioUnitario - descuentoDomingo() - descuentoDe8a10DeLaMañana()) * cantidad;
		System.out.println(precioFinal);

		return precioFinal - descuentoSabado(cantidad);
//		return descuentoSabado(cantidad);
	}

	protected abstract double descuentoDomingo();

	protected abstract double descuentoSabado(int cantidad);

	protected abstract double descuentoDe8a10DeLaMañana();

	protected abstract String nombre();
}
