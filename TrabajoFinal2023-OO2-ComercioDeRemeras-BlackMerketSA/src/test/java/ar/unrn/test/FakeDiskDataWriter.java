package ar.unrn.test;

import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class FakeDiskDataWriter implements DataWriter {

	private String url;
	private String separador;
	private String[] claves;
	private String resultado;

	public FakeDiskDataWriter(String url, String separador) {
		this.url = url;
		this.separador = separador;
		this.resultado = "";
	}

	@Override
	public void newRegister(HashMap<String, String> datos) throws InfrastructureExceptions {
		this.claves = new String[datos.size()];

		int i = 0;
		for (Entry<String, String> entry : datos.entrySet()) {
			this.claves[i] = entry.getKey();
			i++;
		}

		resultado = datos.get(this.claves[0]) + separador + datos.get(this.claves[1]) + separador
				+ datos.get(this.claves[2]);
	}

	String resultado() {
		return resultado;
	}
}
