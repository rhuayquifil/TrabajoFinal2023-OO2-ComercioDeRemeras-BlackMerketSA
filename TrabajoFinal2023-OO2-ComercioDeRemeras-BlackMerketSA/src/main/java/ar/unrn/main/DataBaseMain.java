package ar.unrn.main;

import javax.swing.JOptionPane;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.infrastructure.data.DataBasePropiedades;
import ar.unrn.infrastructure.data.DataBaseRepository;
import ar.unrn.infrastructure.data.DataBaseWriter;
import ar.unrn.infrastructure.data.DefaultDateTimeCheck;
import ar.unrn.infrastructure.data.EmailNotification;
import ar.unrn.infrastructure.ui.PantallaDeCompra;

public class DataBaseMain {

	public static void main(String[] args) {
		try {
			PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas(
					new DataBaseWriter(new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", ""),
							"INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)"
									+ "VALUES (?, ?, ?);"),
					new DataBaseRepository(
							new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", "")),
					new DefaultDateTimeCheck(),
					new EmailNotification("22655f44218bb3", "efb11829ac8703", "sandbox.smtp.mailtrap.io")));

			pantallaDeCompra.setLocationRelativeTo(null);
			pantallaDeCompra.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

}
