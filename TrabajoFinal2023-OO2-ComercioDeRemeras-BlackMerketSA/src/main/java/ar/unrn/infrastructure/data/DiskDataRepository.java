package ar.unrn.infrastructure.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class DiskDataRepository implements DataRepository {

	private String urlArchivo;
	private String separador;

	public DiskDataRepository(String urlArchivo, String separador) throws InfrastructureExceptions {
		super();
		Objects.requireNonNull(urlArchivo);
		this.urlArchivo = urlArchivo;

		Objects.requireNonNull(separador);
		this.separador = separador;

	}

	@Override
	public ArrayList<String> ventas() throws InfrastructureExceptions {

		ArrayList<String> data = new ArrayList<>();

		try {
			String cadena;

			FileReader f = new FileReader(urlArchivo);

			BufferedReader b = new BufferedReader(f);

			readData(data, b);

			b.close();
		} catch (FileNotFoundException e) {
			throw new InfrastructureExceptions("EnDiscoLeerDatos FileNotFoundException");
		} catch (IOException e) {
			throw new InfrastructureExceptions("EnDiscoLeerDatos IOException");
		} catch (NullPointerException e) {
			throw new InfrastructureExceptions("EnDiscoLeerDatos NullPointerException");
		}

		return data;
	}

	private void readData(ArrayList<String> data, BufferedReader b) throws IOException {
		String cadena;
		while ((cadena = b.readLine()) != null) {
			String[] parts = cadena.split(separador);

			data.add(parts[0]);
			data.add(parts[2]);
			data.add(parts[4]);
		}
	}

}
