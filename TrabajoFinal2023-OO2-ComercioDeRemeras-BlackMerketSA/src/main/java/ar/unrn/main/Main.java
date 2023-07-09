package ar.unrn.main;

import ar.unrn.domain.model.DefaultRegistroDeVentas;
import ar.unrn.infrastructure.data.EnDiscoGuardarDatos;
import ar.unrn.infrastructure.ui.PantallaDeCompra;

public class Main {

	public static void main(String[] args) {
		PantallaDeCompra pantallaDeCompra = new PantallaDeCompra(new DefaultRegistroDeVentas(new EnDiscoGuardarDatos(
				"C:\\Users\\ezehu\\git\\TrabajoFinal2023-OO2-ComercioDeRemeras-BlackMerketSA\\RegistroDeVenta.txt",
				" | ")));
		pantallaDeCompra.setLocationRelativeTo(null);
		pantallaDeCompra.setVisible(true);
	}

}
