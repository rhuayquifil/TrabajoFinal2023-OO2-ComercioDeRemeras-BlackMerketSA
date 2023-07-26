package ar.unrn.test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.domain.portsin.Venta;

class PruebasUnitarias {

	@Test
	void ventaDeUnaRemeraLisaSinDescuento() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void ventaDeDiezRemerasEstampadaSinDescuento() {
		try {

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void ventaCuatroRemerasEstampadasDiaDomingo() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 23, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void ventaCincoRemerasEstampadasDiaSabado() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 22, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void ventaTresRemerasEstampadasDiaSabado() {
		try {

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "3");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailprueba@unrn.com");

			registroDeVentas.nuevaVenta(datosVenta);

			assertEquals("3 | " + fakeDefaultDateTimeCheck.now() + " | 7500.0", fakeDataBaseWriter.resultado());

			assertEquals(
					"FinalObjetos2@unrn.com\n" + datosVenta.get("EmailComprador") + "\nCompra BlackMarket SA" + "\n"
							+ fakeDefaultDateTimeCheck.now() + "\nRemeras compradas: "
							+ datosVenta.get("CantidadRemeras") + "\nMonto Total: " + 7500.0,
					fakeEmailNotification.resultado());

		} catch (Exception e) {
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void ventaDeDosRemeraLisaSabadoEntre8y10DeLaMañana() {
		try {

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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
			fail("Fallo, valores invalidos");
		}
	}

	@Test
	void leerVentasDelDia() {
		try {

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(LocalDateTime.now());

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			ArrayList<Venta> ventas = registroDeVentas.ventasDelDia();

			assertEquals(2, ventas.size());

		} catch (Exception e) {
			fail("Fallo, no se leyeron las ventas");
		}
	}

	@Test
	void errorCantidadRemerasInvalido() {
		try {

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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

			FakeDiskDataWriter fakeDiskDataWriter = new FakeDiskDataWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDiskDataRepository fakeDiskDataRepository = new FakeDiskDataRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 16, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

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

			FakeDataBaseWriter fakeDataBaseWriter = new FakeDataBaseWriter(" | ");

			ArrayList<String> data = new ArrayList<>();

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now().plusDays(-1)));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(1));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(2500));

			data.add(String.valueOf(2));
			data.add(String.valueOf(LocalDateTime.now()));
			data.add(String.valueOf(4000));

			data.add(String.valueOf(3));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(7500));

			data.add(String.valueOf(8));
			data.add(String.valueOf(LocalDateTime.now().plusDays(2)));
			data.add(String.valueOf(16000));

			FakeDataBaseRepository fakeDataBaseRepository = new FakeDataBaseRepository(data);

			FakeDefaultDateTimeCheck fakeDefaultDateTimeCheck = new FakeDefaultDateTimeCheck(
					LocalDateTime.of(2023, 07, 20, 9, 30, 0, 0));

			FakeEmailNotification fakeEmailNotification = new FakeEmailNotification();

			DefaultRegistroDeVentas registroDeVentas = new DefaultRegistroDeVentas(fakeDataBaseWriter,
					fakeDataBaseRepository, fakeDefaultDateTimeCheck, fakeEmailNotification);

			HashMap<String, String> datosVenta = new HashMap<String, String>();
			datosVenta.put("CantidadRemeras", "4");
			datosVenta.put("TipoRemera", "Estampada");
			datosVenta.put("EmailComprador", "emailinvalido.unrn");

			registroDeVentas.nuevaVenta(datosVenta);

			fail("Fallo, se registro la venta con datos invalidos");

		} catch (Exception e) {
			assertEquals("Valores Invalidos", e.getMessage());
		}
	}
}
