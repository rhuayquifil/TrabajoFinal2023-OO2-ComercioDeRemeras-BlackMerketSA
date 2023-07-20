package ar.unrn.test;

import ar.unrn.domain.portsout.Notification;

public class FakeEmailNotification implements Notification {

	private final String username;
	private final String password;
	private String hostAddress;
	private String resultado;

	public FakeEmailNotification(String username, String password, String hostAddress) {
		super();
		this.username = username;
		this.password = password;
		this.hostAddress = hostAddress;
		this.resultado = "";
	}

	public void enviarCorreo(String correoRemitente, String correoDestinatario, String contenidoSujeto,
			String contenidoMensaje) {
		resultado = correoRemitente + '\n' + correoDestinatario + '\n' + contenidoSujeto + '\n' + contenidoMensaje;
	}

	String resultado() {
//		System.out.println(resultado);
		return resultado;
	}
}
