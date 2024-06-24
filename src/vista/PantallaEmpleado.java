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

public class PantallaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PantallaEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Menú de Empleado");
		lblTitulo.setBounds(0, 10, 529, 86);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
		contentPane.add(lblTitulo);

		JButton btnVerSolicitudes = new JButton("Ver Solicitudes");
		btnVerSolicitudes.setBounds(108, 107, 323, 58);
		btnVerSolicitudes.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnVerSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaVerSol pantallaVerSol = new PantallaVerSol();
				pantallaVerSol.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVerSolicitudes);

		JButton btnVerAdopciones = new JButton("Ver Adopciones");
		btnVerAdopciones.setBounds(108, 180, 324, 58);
		btnVerAdopciones.setFont(new Font("Stencil", Font.PLAIN, 19));
		btnVerAdopciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaVerAdo pantallaVerAdo = new PantallaVerAdo();
				pantallaVerAdo.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVerAdopciones);

		JButton btnIrAtras = new JButton("Ir atrás");
		btnIrAtras.setBounds(108, 260, 323, 58);
		btnIrAtras.setFont(new Font("Stencil", Font.PLAIN, 19));
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
