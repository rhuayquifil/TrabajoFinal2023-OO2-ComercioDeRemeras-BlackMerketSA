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

			state.setInt(2, 1);
			state.setInt(3, 1);
//
//			state.setDouble(3, (double) datos.get(this.claves[2]));
//			state.setDouble(3, 2.00);

			int cantidad = state.executeUpdate();

			if (cantidad <= 0) {
				throw new InfrastructureExceptions("error al ingresar registro");
			}

		} catch (SQLException | NumberFormatException | DateTimeParseException e) {
			throw new InfrastructureExceptions("error al prosesar consulta: " + e.getMessage());
		}
	}

//	@Override
//	public void inscribir(Participante participante) throws InfrastructureExceptions {
//
//		BaseDeDatosLeerDatos lectorDatos = new BaseDeDatosLeerDatos(properties);
//
//		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
//				properties.get("contrasena"));
//				java.sql.PreparedStatement state = conn.prepareStatement(sqlInsertInscripcion)) {
//
////			"INSERT INTO participante (id, apellido, nombre, telefono, email, id_concurso)" + "VALUES (?, ?, ?, ?, ?, ?);"
//
//			state.setInt(1, Integer.valueOf(participante.id()));
//
//			state.setString(2, participante.lastName());
//
//			state.setString(3, participante.name());
//
//			state.setString(4, participante.phone());
//
//			state.setString(5, participante.email());
//
//			Concurso consurso = lectorDatos.find(participante.idConcurso());
//			state.setInt(6, Integer.valueOf(consurso.id()));
//
//			int cantidad = state.executeUpdate();
//
//			if (cantidad <= 0) {
//				throw new InfrastructureExceptions("error al ingresar Registro");
//			}
//
//		} catch (SQLException | NumberFormatException e) {
//			throw new InfrastructureExceptions("error al prosesar consulta");
//		}
//	}

}
