package ar.unrn.main;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.infrastructure.ui.PantallaDeCompra;

public class Main {

	public static void main(String[] args) {
		PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas());
		pantallaDeCompra.setLocationRelativeTo(null);
		pantallaDeCompra.setVisible(true);
	}

}
