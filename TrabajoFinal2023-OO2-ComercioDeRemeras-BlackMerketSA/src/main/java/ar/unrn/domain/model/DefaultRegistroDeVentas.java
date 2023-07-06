package ar.unrn.domain.model;

import java.util.HashMap;
import java.util.Objects;

import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsin.RegistroDeVentas;

public class DefaultRegistroDeVentas implements RegistroDeVentas {

	// throw RunTimeExceptions(e)
	public void nuevaVenta(HashMap<String, String> datosVenta) throws DomainExceptions {

		dataValidation(datosVenta.get("CantidadRemeras"), datosVenta.get("TipoRemera"),
				datosVenta.get("EmailComprador"));

//		PERSISTI LA VENTA

	}

	private void dataValidation(String cantidadRemeras, String tipoRemera, String emailComprador)
			throws DomainExceptions {
		Objects.requireNonNull(cantidadRemeras, "Cantidad de remeras no puede ser nulo");
		if (cantidadRemeras.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		Objects.requireNonNull(tipoRemera, "Cantidad de remeras no puede ser nulo");
		if (tipoRemera.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		Objects.requireNonNull(emailComprador, "Cantidad de remeras no puede ser nulo");
		if (emailComprador.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		if (!checkEmail(emailComprador)) {
			throw new DomainExceptions("Email invalido");
		}
	}

	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

}
