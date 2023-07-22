package ar.unrn.test;

import java.util.ArrayList;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class FakeDiskDataRepository implements DataRepository {

	private String urlArchivo;
	private ArrayList<String> data;
	private String separador;

	public FakeDiskDataRepository(String urlArchivo, String separador, ArrayList<String> data) {
		this.urlArchivo = urlArchivo;
		this.separador = separador;
		this.data = data;
	}

	@Override
	public ArrayList<String> ventas() throws InfrastructureExceptions {
		return this.data;
	}
}
