package ar.unrn.test;

import java.util.ArrayList;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Propiedades;

public class FakeDataBaseRepository implements DataRepository {

	private Propiedades properties;

	public FakeDataBaseRepository(Propiedades properties) {
		this.properties = properties;
	}

	@Override
	public ArrayList<String> ventas() throws InfrastructureExceptions {

		ArrayList<String> data = new ArrayList<>();

		data.add(String.valueOf(1));
		data.add(String.valueOf("2023-07-20T16:33:47"));
		data.add(String.valueOf(2000));

		data.add(String.valueOf(2));
		data.add(String.valueOf("2023-07-21T16:33:47"));
		data.add(String.valueOf(4000));

		data.add(String.valueOf(3));
		data.add(String.valueOf("2023-07-21T16:34:40"));
		data.add(String.valueOf(7500));

		return data;
	}
}
