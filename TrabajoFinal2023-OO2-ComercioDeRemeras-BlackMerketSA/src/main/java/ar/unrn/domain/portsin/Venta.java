package ar.unrn.domain.portsin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Venta(int cantidadRemeras, LocalDateTime fecha, boolean feriado, double montoTotalFacturado) {
	public String fechaYYYYMMDDHHSS() {
		String outputPattern = "dd-MM-yyyy - HH:mm";
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
		return fecha.format(outputFormatter);
	}
}
