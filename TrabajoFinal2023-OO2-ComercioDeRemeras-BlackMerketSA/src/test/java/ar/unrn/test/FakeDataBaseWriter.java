package ar.unrn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Propiedades;

public class FakeDataBaseWriter implements DataWriter {

	private Propiedades properties;
	private String sqlInsertRegistro;
	private String[] claves;
	private String resultado;
	private String separador;

	public FakeDataBaseWriter(Propiedades properties, String sqlInsertRegistro, String separador) {
		this.properties = properties;
		this.sqlInsertRegistro = sqlInsertRegistro;
		this.resultado = "";
		this.separador = separador;
	}

	@Override
	public void newRegister(HashMap<String, String> datos) throws InfrastructureExceptions {

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.PreparedStatement state = conn.prepareStatement(sqlInsertRegistro)) {

			this.claves = new String[datos.size()];

			int i = 0;
			for (Entry<String, String> entry : datos.entrySet()) {
				this.claves[i] = entry.getKey();
				i++;
			}

			resultado = datos.get(this.claves[0]) + separador + datos.get(this.claves[1]) + separador
					+ datos.get(this.claves[2]);

		} catch (SQLException | NumberFormatException | DateTimeParseException e) {
			throw new InfrastructureExceptions("error al prosesar consulta: " + e.getMessage());
		}
	}

	String resultado() {
		return resultado;
	}
}
