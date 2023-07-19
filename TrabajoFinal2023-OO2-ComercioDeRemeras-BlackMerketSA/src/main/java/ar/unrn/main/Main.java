package ar.unrn.main;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.infrastructure.data.DataBasePropiedades;
import ar.unrn.infrastructure.data.DataBaseRepository;
import ar.unrn.infrastructure.data.DataBaseWriter;
import ar.unrn.infrastructure.data.DefaultDateTimeCheck;
import ar.unrn.infrastructure.data.EmailNotification;
import ar.unrn.infrastructure.ui.PantallaDeCompra;

public class Main {

	public static void main(String[] args) {
		try {
//			PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas(new DiskDataWriter(
//					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
//					" | "),
//					new DiskDataRepository(
//							"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
//							" | "),
//					new DefaultDateTimeCheck(),
//					new EmailNotification("22655f44218bb3", "efb11829ac8703", "sandbox.smtp.mailtrap.io")));

			PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas(
					new DataBaseWriter(new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", ""),
							"INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)"
									+ "VALUES (?, ?, ?);"),
					new DataBaseRepository(
							new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", "")),
					new DefaultDateTimeCheck(),
					new EmailNotification("22655f44218bb3", "efb11829ac8703", "sandbox.smtp.mailtrap.io")));

			// CREA EL CONSTRUCTOR CON LA BASE DE DATOS

			pantallaDeCompra.setLocationRelativeTo(null);
			pantallaDeCompra.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
