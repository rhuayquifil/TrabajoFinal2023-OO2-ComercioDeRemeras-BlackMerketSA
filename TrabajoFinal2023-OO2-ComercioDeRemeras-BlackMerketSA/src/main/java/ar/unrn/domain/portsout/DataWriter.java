package ar.unrn.domain.portsout;

import java.util.HashMap;

public interface DataWriter {
	void newRegister(HashMap<String, String> data) throws InfrastructureExceptions;
}
