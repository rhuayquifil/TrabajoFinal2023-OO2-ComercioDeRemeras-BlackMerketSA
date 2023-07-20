package ar.unrn.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import ar.unrn.domain.model.DefaultRegistroDeVentas;

class PruebasUnitarias {

	@Test
	void ventaDeUnaRemeraLisaSinDescuento() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "1");
			datosVenta.put("TipoRemera", "Lisa");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("1 | " + fakeDefaultDateTimeCheck.now() + " | 2000.0", fakeDiskDataWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now().toLocalDate().toString() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 2000.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

}
