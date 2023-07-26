package ar.unrn.infrastructure.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.unrn.domain.portsin.RegistroDeVentas;
import ar.unrn.domain.portsin.Venta;

public class VentasDelDiaPantalla extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private RegistroDeVentas registroVentas;
	private ArrayList<Venta> ventasDelDia;

	public VentasDelDiaPantalla(RegistroDeVentas registroVentas) {

		this.registroVentas = registroVentas;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Ventas Del Dia " + LocalDate.now());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 261);
		contentPane.add(scrollPane);

		table = new JTable();

		modelo = new DefaultTableModel();

		modelo.addColumn("Fecha Venta");
		modelo.addColumn("Monto Total Facturado");
		modelo.addColumn("Es Feriado");

		table.setModel(modelo);

		actualizarTabla();

		scrollPane.setViewportView(table);
	}

	public void actualizar() {
		limpiarTabla(table); // limpia la tabla y despues la rellena de nuevo con nuevos datos
		actualizarTabla();
	}

	private void actualizarTabla() {

		Object[] fila = new Object[3];
		for (Venta venta : registroVentas.ventasDelDia()) {
			fila[0] = venta.fechaYYYYMMDDHHSS();
			fila[1] = venta.montoTotalFacturado();

			if (venta.feriado()) {
				fila[2] = "FERIADO";
			} else {
				fila[2] = "NO FERIADO";
			}

//			triana agustina  tana  peña
//			0-1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34-35-36-37-38-39-40-41-42-43-43-44-45-46-47
//			48-49-50

			modelo.addRow(fila);
		}
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	private void limpiarTabla(JTable tabla) {
		try {
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			int filas = tabla.getRowCount();
			for (int i = 0; filas > i; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
		}
	}
}
