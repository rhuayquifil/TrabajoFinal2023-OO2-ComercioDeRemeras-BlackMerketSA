package ar.unrn.domain.model;

import java.util.Objects;

public abstract class Remera {

	private String nombre;

	public Remera(String nombre) {
		super();
		this.nombre = nombre;
	}

	public abstract double consultarMonto(int cantidad);

	public String nombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Remera other = (Remera) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
