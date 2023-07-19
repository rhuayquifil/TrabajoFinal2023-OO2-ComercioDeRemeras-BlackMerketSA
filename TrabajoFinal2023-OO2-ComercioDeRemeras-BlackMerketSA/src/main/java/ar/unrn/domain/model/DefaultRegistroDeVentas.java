package ar.unrn.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsin.RegistroDeVentas;
import ar.unrn.domain.portsin.Venta;
import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.DateTimeCheck;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Notification;

public class DefaultRegistroDeVentas implements RegistroDeVentas {

	private ArrayList<Remera> listaDeTiposRemera;
	private DataWriter dataWriter;
	private DataRepository dataRepository;
	private DateTimeCheck dateTimeCheck;
	private Notification notificacion;

	public DefaultRegistroDeVentas(DataWriter dataWriter, DataRepository dataRepository, DateTimeCheck dateTimeCheck,
			Notification notificacion) {
		super();
		this.dataWriter = dataWriter;
		this.dataRepository = dataRepository;
		this.dateTimeCheck = dateTimeCheck;
		this.notificacion = notificacion;

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

			dataWriter.nuevoRegistro(registroVenta);

//			notificacion.enviarCorreo("FinalObjetos2@unrn.com", datosVenta.get("EmailComprador"),
//					"Compra BlackMarket SA",
//					fecha.toLocalDate().toString() + "\nRemeras compradas: " + datosVenta.get("CantidadRemeras")
//							+ "\nMonto Total: " + consultarMontoTotalDeVenta(datosVenta));

			// ACTUALIZAR TABLA DESPUES DE AGREGAR UNA VENTA Y EMPEZA CON LA BASE DE DATOS
			// gordo putoooooo

		} catch (InfrastructureExceptions e) {
			throw new RuntimeException(e.getMessage());
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

	@Override
	public ArrayList<Venta> ventasDelDia() {
		try {
			return filtarVentasDelDia();
		} catch (InfrastructureExceptions | NumberFormatException | DateTimeParseException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	private ArrayList<Venta> filtarVentasDelDia() throws InfrastructureExceptions {
		ArrayList<Venta> ventasDelDia = new ArrayList<Venta>();

		for (Venta venta : leerVentas(dataRepository.ventas())) {
			if (sonFechasDelMismoDia(LocalDateTime.now(), venta.fecha())) {
				ventasDelDia.add(venta);
			}
		}

		return ventasDelDia;
	}

	private boolean sonFechasDelMismoDia(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		LocalDate date1 = dateTime1.toLocalDate();
		LocalDate date2 = dateTime2.toLocalDate();

		return date1.isEqual(date2);
	}

	private ArrayList<Venta> leerVentas(ArrayList<String> datos) {

		ArrayList<Venta> ventas = new ArrayList<Venta>();

		for (int i = 0; i < datos.size(); i++) {

			LocalDateTime dateTime = leerFechaVenta(datos, i);

			ventas.add(new Venta(Integer.valueOf(datos.get(i)), dateTime, dateTimeCheck.esFeriado(dateTime),
					Double.valueOf(datos.get(i + 2))));

			i += 2;
		}
		return ventas;
	}

	private LocalDateTime leerFechaVenta(ArrayList<String> datos, int i) {
		List<String> patrones = Arrays.asList("yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

		LocalDateTime dateTime = null;

		for (String patron : patrones) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);

				dateTime = LocalDateTime.parse(datos.get(i + 1), formatter);

				break; // sino tiro exception, sale del bucle
			} catch (Exception e) {
				// Si ocurre un error al parsear con un patrón, intentar con el siguiente
			}
		}
		return dateTime;
	}
}
