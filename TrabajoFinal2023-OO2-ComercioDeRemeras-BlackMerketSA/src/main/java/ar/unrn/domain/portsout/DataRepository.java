package ar.unrn.domain.portsout;

import java.util.ArrayList;

public interface DataRepository {

	ArrayList<String> ventasDelDia() throws InfrastructureExceptions;

}
