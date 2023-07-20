package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Propiedades;

public class DataBaseWriter implements DataWriter {

	private Propiedades properties;
	private String sqlInsertRegistro;
	private String[] claves;

	public DataBaseWriter(Propiedades properties, String sqlInsertRegistro) {
		this.properties = properties;
		this.sqlInsertRegistro = sqlInsertRegistro;
	}

	@Override
	public void nuevoRegistro(HashMap<String, String> datos) throws InfrastructureExceptions {

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.PreparedStatement state = conn.prepareStatement(sqlInsertRegistro)) {
//		INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)" + "VALUES (?, ?, ?);

			this.claves = new String[datos.size()];

			int i = 0;
			for (Entry<String, String> entry : datos.entrySet()) {
				this.claves[i] = entry.getKey();
				i++;
			}
//			for (String string : claves) {
//				System.out.println(string);
//			}

			LocalDateTime dateVenta = LocalDateTime.parse(datos.get(this.claves[1]),
					DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));

			state.setTimestamp(1, Timestamp.valueOf(dateVenta));

			state.setInt(2, Integer.valueOf(datos.get(this.claves[0])));
			state.setDouble(3, Double.valueOf(datos.get(this.claves[2])));

			int cantidad = state.executeUpdate();

			if (cantidad <= 0) {
				throw new InfrastructureExceptions("error al ingresar registro");
			}

		} catch (SQLException | NumberFormatException | DateTimeParseException e) {
			throw new InfrastructureExceptions("error al prosesar consulta: " + e.getMessage());
		}
	}
}
