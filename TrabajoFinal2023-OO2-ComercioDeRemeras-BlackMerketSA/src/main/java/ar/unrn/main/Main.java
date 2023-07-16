package ar.unrn.main;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.domain.portsin.Observer;
import ar.unrn.infrastructure.data.DefaultDateTimeCheck;
import ar.unrn.infrastructure.data.DiskDataRepository;
import ar.unrn.infrastructure.data.DiskDataWriter;
import ar.unrn.infrastructure.ui.PantallaDeCompra;
import ar.unrn.infrastructure.ui.VentasDelDiaPantalla;

public class Main {

	public static void main(String[] args) {

		List<Observer> subscriptores = new ArrayList<>();

//		subscriptores.add(new GuardarDatoTemperaturaObserver(
//				"C:\\Users\\ezehu\\git\\TP6-OO2-Observer-MedidorDeTemperatura\\RegistroTemperatura.txt", ", "));

		try {
			subscriptores.add(new VentasDelDiaPantalla(new DefaultRegistroDeVentas(new DiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\RegistroDeVenta.txt",
					" | "),
					new DiskDataRepository(
							"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\RegistroDeVenta.txt",
							" | "),
					new DefaultDateTimeCheck(), subscriptores)));

			PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas(new DiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\RegistroDeVenta.txt",
					" | "),
					new DiskDataRepository(
							"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\RegistroDeVenta.txt",
							" | "),
					new DefaultDateTimeCheck(), subscriptores));

			pantallaDeCompra.setLocationRelativeTo(null);
			pantallaDeCompra.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
