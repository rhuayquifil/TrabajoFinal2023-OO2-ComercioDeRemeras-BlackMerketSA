package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public void nuevoRegistro(HashMap<String, Object> datos) throws InfrastructureExceptions {

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.PreparedStatement state = conn.prepareStatement(sqlInsertRegistro)) {
//		INSERT INTO registro_ventas (fecha, cantidad, monto_total_facturado)" + "VALUES (?, ?, ?);

			this.claves = new String[datos.size()];

			int i = 0;
			for (Entry<String, Object> entry : datos.entrySet()) {
				this.claves[i] = entry.getKey();
				i++;
			}
			for (String string : claves) {
				System.out.println(string);
			}

			state.setTimestamp(1, new Timestamp(1));
			state.setInt(2, Integer.valueOf(datos.get(this.claves[0]));
			state.setDouble(3, 2.00);

//			SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
//			Date dateOriginal = formatoOriginal.parse((String) datos.get(this.claves[0]));
//
//			Timestamp fechaVenta = new Timestamp(dateOriginal.getTime());
//
//			state.setTimestamp(1, fechaVenta);

//			state.setInt(2, (int) datos.get(this.claves[1]));
//			state.setInt(2, 1);
//
//			state.setDouble(3, (double) datos.get(this.claves[2]));
//			state.setDouble(3, 2.00);

			int cantidad = state.executeUpdate();

			if (cantidad <= 0) {
				throw new InfrastructureExceptions("error al ingresar registro");
			}

		} catch (SQLException | NumberFormatException e) {
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
