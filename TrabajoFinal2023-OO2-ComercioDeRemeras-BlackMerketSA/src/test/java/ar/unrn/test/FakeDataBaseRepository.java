package ar.unrn.test;

import java.util.ArrayList;

import ar.unrn.domain.portsout.DataRepository;
import ar.unrn.domain.portsout.InfrastructureExceptions;

public class FakeDataBaseRepository implements DataRepository {

	private ArrayList<String> data;

	public FakeDataBaseRepository(ArrayList<String> data) {
		this.data = data;
	}

	@Override
	public ArrayList<String> sales() throws InfrastructureExceptions {
		return this.data;
	}
}
