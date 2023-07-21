package ar.unrn.test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.domain.portsin.Venta;
import ar.unrn.domain.portsout.Propiedades;
import ar.unrn.infrastructure.data.DataBasePropiedades;

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
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 2000.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void ventaDeDiezRemerasEstampadaSinDescuento() {
		try {

			Propiedades properties = new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", "");

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(properties,
					"INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)" + "VALUES (?, ?, ?);",
					" | ");

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(properties);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "10");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("10 | " + fakeDefaultDateTimeCheck.now() + " | 25000.0", fakeDataBaseWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 25000.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void ventaCuatroRemerasEstampadasDiaDomingo() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 23, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "4");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("4 | " + fakeDefaultDateTimeCheck.now() + " | 9000.0", fakeDiskDataWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 9000.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void ventaCincoRemerasEstampadasDiaSabado() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 22, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "5");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("5 | " + fakeDefaultDateTimeCheck.now() + " | 11000.0", fakeDiskDataWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 11000.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void ventaTresRemerasEstampadasDiaSabado() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 22, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "3");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("3 | " + fakeDefaultDateTimeCheck.now() + " | 7500.0", fakeDiskDataWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 7500.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void ventaDeDosRemeraLisaSabadoEntre8y10DeLaMañana() {
		try {

			Propiedades properties = new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", "");

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(properties,
					"INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)" + "VALUES (?, ?, ?);",
					" | ");

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(properties);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 15, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "2");
			datosVenta.put("TipoRemera", "Lisa");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("2 | " + fakeDefaultDateTimeCheck.now() + " | 3800.0", fakeDataBaseWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 3800.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

//	@Test
	void leerVentasDelDia() {
		try {

			Propiedades properties = new DataBasePropiedades("jdbc:mysql://127.0.0.1/blackmarket_sa", "root", "");

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(properties,
					"INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)" + "VALUES (?, ?, ?);",
					" | ");

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(properties);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 15, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			ArrayList<Venta> ventas = registroDeVentas.ventasDelDia();

			assertEquals(2, ventas.size());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	@Test
	void errorCantidadRemerasInvalido() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 22, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "0");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			fail("Fallo, se registro la venta con datos invalidos");

		} catch (Exception e) {
			assertEquals("Valores Invalidos", e.getMessage());
		}
	}

	@Test
	void errorStringCantidadRemerasInvalido() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 22, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "rodrigo");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			fail("Fallo, se registro la venta con datos invalidos");

		} catch (Exception e) {
			assertEquals("Valores Invalidos", e.getMessage());
		}
	}

	@Test
	void errorEmailInvalido() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(
					"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\BlackMarketSA.txt",
					" | ");

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 21, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification("22655f44218bb3", "efb11829ac8703",
					"sandbox.smtp.mailtrap.io");

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDiskDataWriter,
					fakeDiskDataRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "4");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailinvalido@unrn");

			registroDeVentas.nuevaVenta(datosVenta);

			fail("Fallo, se registro la venta con datos invalidos");

		} catch (Exception e) {
			assertEquals("Valores Invalidos", e.getMessage());
		}
	}
}
