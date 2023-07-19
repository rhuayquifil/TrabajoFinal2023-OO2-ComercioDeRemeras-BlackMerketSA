package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Propiedades;

public class DataBaseRepository implements DataRepository {

	private Propiedades properties;

	public DataBaseRepository(Propiedades properties) {
		this.properties = properties;
	}

	@Override
	public ArrayList<String> ventas() throws InfrastructureExceptions {

		ArrayList<String> data = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.Statement sent = conn.createStatement();
				ResultSet resul = sent.executeQuery("SELECT * FROM registro_ventas")) {

			while (resul.next()) {
				// fecha venta
				Timestamp dateVenta = resul.getTimestamp("fecha");

				LocalDateTime fechaVenta = dateVenta.toLocalDateTime();

				int cantidad = resul.getInt("cantidad");

				double montoTotalFacturado = resul.getDouble("monto_total_facturado");

				data.add(String.valueOf(cantidad));
				data.add(String.valueOf(fechaVenta));
				data.add(String.valueOf(montoTotalFacturado));
			}
		} catch (SQLException e) {
			throw new InfrastructureExceptions("error al procesar consulta");
		}

		return data;
	}
}
