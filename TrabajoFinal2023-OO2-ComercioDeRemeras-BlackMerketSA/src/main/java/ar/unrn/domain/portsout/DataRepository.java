package ar.unrn.domain.portsout;

import java.util.ArrayList;

public interface DataRepository {

	ArrayList<String> ventas() throws InfrastructureExceptions;

}
