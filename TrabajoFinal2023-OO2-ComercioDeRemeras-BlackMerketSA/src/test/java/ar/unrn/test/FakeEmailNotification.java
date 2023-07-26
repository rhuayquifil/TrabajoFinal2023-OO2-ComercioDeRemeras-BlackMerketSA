package ar.unrn.test;

import ar.unrn.domain.portsout.Notification;

public class FakeEmailNotification implements Notification {

	private String resultado;

	public FakeEmailNotification() {
		super();
		this.resultado = "";
	}

	public void sendMail(String correoRemitente, String correoDestinatario, String contenidoSujeto,
			String contenidoMensaje) {
		resultado = correoRemitente + '\n' + correoDestinatario + '\n' + contenidoSujeto + '\n' + contenidoMensaje;
	}

	String resultado() {
		return resultado;
	}
}
