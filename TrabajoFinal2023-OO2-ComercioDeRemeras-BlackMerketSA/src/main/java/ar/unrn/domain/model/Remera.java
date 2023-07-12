package ar.unrn.domain.model;

public abstract class Remera {

	protected double precioUnitario;

	public Remera(double precioUnitario) {
		super();
		this.precioUnitario = precioUnitario;
	}

	public double precioFinal(int cantidad) {
		double precioFinal = (precioUnitario - descuentoDomingo() - descuentoDe8a10DeLaMañana()) * cantidad;
		return precioFinal - descuentoSabado(cantidad);
	}

	protected abstract double descuentoDomingo();

	protected abstract double descuentoSabado(int cantidad);

	protected abstract double descuentoDe8a10DeLaMañana();

	protected abstract String nombre();
}
