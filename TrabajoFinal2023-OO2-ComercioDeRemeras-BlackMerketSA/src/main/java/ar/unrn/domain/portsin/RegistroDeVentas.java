package ar.unrn.domain.portsin;

import java.util.HashMap;

public interface RegistroDeVentas {

	void nuevaVenta(HashMap<String, String> datosVenta) throws DomainExceptions;

	double consultarMontoTotalDeVenta(HashMap<String, String> datosVenta) throws DomainExceptions;

	String[] listadoNombresDeLosTipoRemera();
}
