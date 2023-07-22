package ar.unrn.test;

import java.util.ArrayList;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Propiedades;

public class FakeDataBaseRepository implements DataRepository {

	private Propiedades properties;
	private ArrayList<String> data;

	public FakeDataBaseRepository(Propiedades properties, ArrayList<String> data) {
		this.properties = properties;
		this.data = data;
	}

	@Override
	public ArrayList<String> ventas() throws InfrastructureExceptions {
		return this.data;
	}
}
