package ar.unrn.infrastructure.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsin.RegistroDeVentas;

public class PantallaDeCompra extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnRemeraLisa;
	private JRadioButton rdbtnRemeraEstampada;
	private JTextField cajaCantidadRemeras;
	private JTextField cajaEmailComprador;
	private RegistroDeVentas registroVentas;

	public PantallaDeCompra(RegistroDeVentas registroVentas) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Compras");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		this.registroVentas = registroVentas;

		JLabel lblRemeras = new JLabel("Cantidad Remeras  *");
		lblRemeras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRemeras.setBounds(90, 30, 131, 15);
		contentPane.add(lblRemeras);

		cajaCantidadRemeras = new JTextField();
		cajaCantidadRemeras.setText("");
		cajaCantidadRemeras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;

				if (!numeros) // para que solo ingrese numeros
				{
					e.consume();
				}
			}
		});
		cajaCantidadRemeras.setBounds(210, 30, 130, 20);
		contentPane.add(cajaCantidadRemeras);
		cajaCantidadRemeras.setColumns(10);

		JLabel lblTipoDeRemera = new JLabel("Tipo de Remera");
		lblTipoDeRemera.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoDeRemera.setBounds(90, 80, 122, 14);
		contentPane.add(lblTipoDeRemera);

		rdbtnRemeraLisa = new JRadioButton("Lisa");
		rdbtnRemeraLisa.setSelected(true);
		rdbtnRemeraLisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnRemeraEstampada.setSelected(false);
			}
		});
		rdbtnRemeraLisa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRemeraLisa.setBounds(140, 100, 71, 23);
		contentPane.add(rdbtnRemeraLisa);

		rdbtnRemeraEstampada = new JRadioButton("Estampada");
		rdbtnRemeraEstampada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnRemeraLisa.setSelected(false);
			}
		});
		rdbtnRemeraEstampada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRemeraEstampada.setBounds(220, 100, 94, 23);
		contentPane.add(rdbtnRemeraEstampada);

		JLabel lblEmailComprador = new JLabel("Email Comprador  *");
		lblEmailComprador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmailComprador.setBounds(90, 140, 105, 14);
		contentPane.add(lblEmailComprador);

		cajaEmailComprador = new JTextField();
		cajaCantidadRemeras.setText("");
		cajaEmailComprador.setBounds(90, 160, 250, 20);
		contentPane.add(cajaEmailComprador);
		cajaEmailComprador.setColumns(10);

		JButton btnConsultarMontoTotal = new JButton("Monto Total");
		btnConsultarMontoTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnConsultarMontoTotal.setBounds(90, 210, 105, 23);
		contentPane.add(btnConsultarMontoTotal);

		JButton btnConfirmarCompra = new JButton("Confirmar Compra");
		btnConfirmarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> datosVenta = new HashMap<String, String>();
				datosVenta.put("CantidadRemeras", cajaCantidadRemeras.getText());
				datosVenta.put("TipoRemera", tipoRemeraSeleccionada());
				datosVenta.put("EmailComprador", cajaEmailComprador.getText());

				try {
					registroVentas.nuevaVenta(datosVenta);
				} catch (DomainExceptions e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			private String tipoRemeraSeleccionada() {
				if (rdbtnRemeraLisa.isSelected()) {
					return "Lisa";
				}
				return "Estampada";
			}
		});
		btnConfirmarCompra.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnConfirmarCompra.setBounds(209, 210, 131, 23);
		contentPane.add(btnConfirmarCompra);
	}
}
