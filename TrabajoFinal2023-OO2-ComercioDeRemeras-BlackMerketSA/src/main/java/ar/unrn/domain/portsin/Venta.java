package ar.unrn.domain.portsin;

import java.time.LocalDateTime;

public record Venta(LocalDateTime fecha, int cantidadRemeras, double montoTotalFacturado) {

}
