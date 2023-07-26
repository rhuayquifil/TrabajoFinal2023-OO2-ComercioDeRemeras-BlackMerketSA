package ar.unrn.test;

import java.util.HashMap;
import java.util.Map.Entry;

import ar.unrn.domain.portsout.DataWriter;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class FakeDataBaseWriter implements DataWriter {

	private String[] claves;
	private String resultado;
	private String separador;

	public FakeDataBaseWriter(String separador) {
		this.resultado = "";
		this.separador = separador;
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
