package ar.unrn.domain.portsout;

import java.util.HashMap;

public interface GuardarDatos {

//	void nuevoRegistro(HashMap<String, String> datos) throws InfrastructureExceptions;
	void nuevoRegistro(HashMap<String, Object> datos) throws InfrastructureExceptions;
}
