package ar.unrn.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsin.RegistroDeVentas;
import ar.unrn.domain.portsout.DateTimeCheck;
import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class DefaultRegistroDeVentas implements RegistroDeVentas {

	private ArrayList<Remera> listaDeTiposRemera;
	private GuardarDatos guardarDatos;

	public DefaultRegistroDeVentas(GuardarDatos guardarDatos, DateTimeCheck dateTimeCheck) {
		super();
		this.guardarDatos = guardarDatos;

		this.listaDeTiposRemera = new ArrayList<Remera>();
		listaDeTiposRemera.add(new RemeraLisa(2000, dateTimeCheck));
		listaDeTiposRemera.add(new RemeraEstampada(2500, dateTimeCheck));
	}

	// throw new RuntimeException(e);
	public void nuevaVenta(HashMap<String, String> datosVenta) throws DomainExceptions {

		try {
			dataValidation(datosVenta.get("CantidadRemeras"), datosVenta.get("TipoRemera"),
					datosVenta.get("EmailComprador"));

			LocalDateTime fecha = LocalDateTime.now();

			datosVenta.put("FechaVenta", fecha.toString());

			HashMap<String, Object> registroVenta = new HashMap<String, Object>();
			registroVenta.put("FechaVenta", fecha);
			registroVenta.put("CantidadRemeras", datosVenta.get("CantidadRemeras"));
			registroVenta.put("MontoTotalFacturado", consultarMontoTotalDeVenta(datosVenta));

			guardarDatos.nuevoRegistro(registroVenta);
		} catch (InfrastructureExceptions e) {
			throw new RuntimeException(e);
		}
	}

	private void dataValidation(String cantidadRemeras, String tipoRemera, String emailComprador)
			throws DomainExceptions {
		Objects.requireNonNull(cantidadRemeras, "Complete todos los campos");
		if (cantidadRemeras.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		try {
			if (Integer.valueOf(cantidadRemeras) <= 0) {
				throw new DomainExceptions("Valores Invalidos");
			}
		} catch (NumberFormatException e) {
			throw new DomainExceptions("Valores Invalidos");
		}

		Objects.requireNonNull(tipoRemera, "Complete todos los campos");
		if (tipoRemera.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		Objects.requireNonNull(emailComprador, "Complete todos los campos");
		if (emailComprador.isEmpty()) {
			throw new DomainExceptions("Campos con * son obligatorios");
		}

		if (!checkEmail(emailComprador)) {
			throw new DomainExceptions("Valores Invalidos");
		}
	}

	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	@Override
	public double consultarMontoTotalDeVenta(HashMap<String, String> datosVenta) throws DomainExceptions {
		for (Remera remera : listaDeTiposRemera) {
			if (remera.nombre().equals(datosVenta.get("TipoRemera"))) {
				return remera.precioFinal(Integer.valueOf(datosVenta.get("CantidadRemeras")));
			}
		}
		throw new DomainExceptions("error al procesar consulta");
	}

	@Override
	public String[] listadoNombresDeLosTipoRemera() {
		String[] listadoNombresDeRemeras = new String[listaDeTiposRemera.size()];
		int i = 0;
		for (Remera remera : listaDeTiposRemera) {
			listadoNombresDeRemeras[i] = remera.nombre();
			i++;
		}
		return listadoNombresDeRemeras;
	}

}
