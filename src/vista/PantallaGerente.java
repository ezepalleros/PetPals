package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PantallaGerente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PantallaGerente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Menú de Gerente");
		lblTitulo.setBounds(0, 10, 529, 86);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
		contentPane.add(lblTitulo);

		JButton btnGestionarServicios = new JButton("Gestionar Servicios");
		btnGestionarServicios.setBounds(108, 107, 323, 58);
		btnGestionarServicios.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnGestionarServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGesSer pantallaGesSer = new PantallaGesSer(null);
				pantallaGesSer.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnGestionarServicios);

		JButton btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setBounds(108, 180, 324, 58);
		btnGestionarEmpleados.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnGestionarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGesEmp pantallaGesEmp = new PantallaGesEmp();
				pantallaGesEmp.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnGestionarEmpleados);

		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setBounds(108, 260, 323, 58);
		btnGestionarClientes.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGesCli pantallaGesCli = new PantallaGesCli();
				pantallaGesCli.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnGestionarClientes);

		JButton btnGestionarMascotas = new JButton("Gestionar Mascotas");
		btnGestionarMascotas.setBounds(108, 340, 323, 58);
		btnGestionarMascotas.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnGestionarMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGesMas pantallaGesMas = new PantallaGesMas();
				pantallaGesMas.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnGestionarMascotas);

		JButton btnIrAtras = new JButton("Ir atrás");
		btnIrAtras.setBounds(108, 420, 323, 58);
		btnIrAtras.setFont(new Font("Stencil", Font.PLAIN, 19));
		contentPane.add(btnIrAtras);

		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaOpciones pantallaOpciones = new PantallaOpciones();
				pantallaOpciones.setVisible(true);
				dispose();
			}
		});

		contentPane.add(btnIrAtras);
	}
}
