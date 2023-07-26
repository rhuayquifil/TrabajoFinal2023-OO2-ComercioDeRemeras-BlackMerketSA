package ar.unrn.domain.portsout;

public interface Notification {

	void sendMail(String correoRemitente, String correoDestinatario, String contenidoSujeto,
			String contenidoMensaje);
}
