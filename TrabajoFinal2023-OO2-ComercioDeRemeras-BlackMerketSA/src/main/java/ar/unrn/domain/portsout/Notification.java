package ar.unrn.domain.portsout;

public interface Notification {

	void enviarCorreo(String correoRemitente, String correoDestinatario, String contenidoSujeto,
			String contenidoMensaje);
}
