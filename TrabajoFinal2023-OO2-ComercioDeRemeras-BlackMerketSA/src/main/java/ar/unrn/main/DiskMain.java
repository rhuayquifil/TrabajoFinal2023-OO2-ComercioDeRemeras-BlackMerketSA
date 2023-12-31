package ar.unrn.main;

import javax.swing.JOptionPane;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.infrastructure.data.DefaultDateTimeCheck;
import ar.unrn.infrastructure.data.DiskDataRepository;
import ar.unrn.infrastructure.data.DiskDataWriter;
import ar.unrn.infrastructure.data.EmailNotification;
import ar.unrn.infrastructure.ui.PantallaDeVenta;

public class DiskMain {

	public static void main(String[] args) {
		try {
			PantallaDeVenta pantallaDeCompra = new PantallaDeVenta(new DefaultRegistroDeVentas(new DiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | "),
					new DiskDataRepository(
							"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
							" | "),
					new DefaultDateTimeCheck(),
					new EmailNotification("22655f44218bb3", "efb11829ac8703", "sandbox.smtp.mailtrap.io")));

			pantallaDeCompra.setLocationRelativeTo(null);
			pantallaDeCompra.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}
}
