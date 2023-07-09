package ar.unrn.infrastructure.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class EnDiscoGuardarDatos implements GuardarDatos {

	private String url;
	private String separador;
	private String[] claves;
//	private HashMap<String, Object> datos;

	public EnDiscoGuardarDatos(String url, String separador) {
		this.url = url;
		this.separador = separador;
	}

	@Override
	public void nuevoRegistro(HashMap<String, Object> datos) throws InfrastructureExceptions {

		this.claves = new String[datos.size()];

		int i = 0;
		for (Entry<String, Object> entry : datos.entrySet()) {
			this.claves[i] = entry.getKey();
			i++;
		}

//		for (i = 0; i < this.claves.length; i++) {
//			String elemento = this.claves[i];
//			System.out.println(elemento);
//		}

		File file = new File(url);

		if (!file.exists()) {
			crearArchivoYEscribirEnDisco(file, datos);
		}

		escribirEnDisco(file, datos);
	}

	private void escribirEnDisco(File file, HashMap<String, Object> datos) throws InfrastructureExceptions {
		try {
			FileWriter writer = new FileWriter(file, true);

			escribir(writer, datos);

		} catch (IOException e) {
			throw new InfrastructureExceptions("Ocurrio un error al editar el archivo.");
		}
	}

	private void crearArchivoYEscribirEnDisco(File file, HashMap<String, Object> datos)
			throws InfrastructureExceptions {
		try {
			FileWriter writer = new FileWriter(file);

			escribir(writer, datos);

		} catch (IOException e) {
			throw new InfrastructureExceptions("Ocurrio un error al crear el archivo.");
		}
	}

	private void escribir(FileWriter writer, HashMap<String, Object> datos) throws IOException {

		writer.write(datos.get(this.claves[0]) + separador + datos.get(this.claves[1]) + separador
				+ datos.get(this.claves[2]) + '\n');
		writer.close();
	}

}
