package ar.unrn.infrastructure.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class DiskDataWriter implements DataWriter {

	private String url;
	private String separador;
	private String[] claves;

	public DiskDataWriter(String url, String separador) {
		this.url = url;
		this.separador = separador;
	}

	@Override
	public void nuevoRegistro(HashMap<String, String> datos) throws InfrastructureExceptions {

		this.claves = new String[datos.size()];

		int i = 0;
		for (Entry<String, String> entry : datos.entrySet()) {
			this.claves[i] = entry.getKey();
			i++;
		}

		File file = new File(url);

		if (!file.exists()) {
			crearArchivoYEscribirEnDisco(file, datos);
		}

		escribirEnDisco(file, datos);
	}

	private void escribirEnDisco(File file, HashMap<String, String> datos) throws InfrastructureExceptions {
		try {
			FileWriter writer = new FileWriter(file, true);

			escribir(writer, datos);

		} catch (IOException e) {
			throw new InfrastructureExceptions("Ocurrio un error al editar el archivo.");
		}
	}

	private void crearArchivoYEscribirEnDisco(File file, HashMap<String, String> datos)
			throws InfrastructureExceptions {
		try {
			FileWriter writer = new FileWriter(file);

			escribir(writer, datos);

		} catch (IOException e) {
			throw new InfrastructureExceptions("Ocurrio un error al crear el archivo.");
		}
	}

	private void escribir(FileWriter writer, HashMap<String, String> datos) throws IOException {

		writer.write(datos.get(this.claves[0]) + separador + datos.get(this.claves[1]) + separador
				+ datos.get(this.claves[2]) + '\n');
		writer.close();
	}

}
