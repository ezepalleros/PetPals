package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controladores.ServicioControlador;
import modelos.Servicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class PantallaAgrSer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServicioControlador controlador;
	private LocalDate fechaActual = LocalDate.now();

	public PantallaAgrSer(LocalDate fechaActual) {
		this.fechaActual = fechaActual;
		controlador = new ServicioControlador();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblNombre = new JLabel("Nombre del servicio:");
		contentPane.add(lblNombre);

		JTextField txtNombre = new JTextField();
		contentPane.add(txtNombre);

		JLabel lblDia = new JLabel("Día del servicio (yyyy-MM-dd):");
		contentPane.add(lblDia);

		JTextField txtDia = new JTextField();
		contentPane.add(txtDia);

		JLabel lblHoraInicio = new JLabel("Hora de inicio (HH:mm:ss):");
		contentPane.add(lblHoraInicio);

		JTextField txtHoraInicio = new JTextField();
		contentPane.add(txtHoraInicio);

		JLabel lblHoraFin = new JLabel("Hora de fin (HH:mm:ss):");
		contentPane.add(lblHoraFin);

		JTextField txtHoraFin = new JTextField();
		contentPane.add(txtHoraFin);

		JLabel lblPerro = new JLabel("¿Puede atender perros?:");
		contentPane.add(lblPerro);

		JCheckBox chkPerro = new JCheckBox();
		contentPane.add(chkPerro);

		JLabel lblGato = new JLabel("¿Puede atender gatos?:");
		contentPane.add(lblGato);

		JCheckBox chkGato = new JCheckBox();
		contentPane.add(chkGato);

		JLabel lblAve = new JLabel("¿Puede atender aves?:");
		contentPane.add(lblAve);

		JCheckBox chkAve = new JCheckBox();
		contentPane.add(chkAve);

		JLabel lblRoedor = new JLabel("¿Puede atender roedores?:");
		contentPane.add(lblRoedor);

		JCheckBox chkRoedor = new JCheckBox();
		contentPane.add(chkRoedor);

		JLabel lblReptil = new JLabel("¿Puede atender reptiles?:");
		contentPane.add(lblReptil);

		JCheckBox chkReptil = new JCheckBox();
		contentPane.add(chkReptil);

		JLabel lblPrecioPerro = new JLabel("Precio por perro:");
		contentPane.add(lblPrecioPerro);

		JTextField txtPrecioPerro = new JTextField();
		contentPane.add(txtPrecioPerro);

		JLabel lblPrecioGato = new JLabel("Precio por gato:");
		contentPane.add(lblPrecioGato);

		JTextField txtPrecioGato = new JTextField();
		contentPane.add(txtPrecioGato);

		JLabel lblPrecioAve = new JLabel("Precio por ave:");
		contentPane.add(lblPrecioAve);

		JTextField txtPrecioAve = new JTextField();
		contentPane.add(txtPrecioAve);

		JLabel lblPrecioRoedor = new JLabel("Precio por roedor:");
		contentPane.add(lblPrecioRoedor);

		JTextField txtPrecioRoedor = new JTextField();
		contentPane.add(txtPrecioRoedor);

		JLabel lblPrecioReptil = new JLabel("Precio por reptil:");
		contentPane.add(lblPrecioReptil);

		JTextField txtPrecioReptil = new JTextField();
		contentPane.add(txtPrecioReptil);

		JButton btnGuardar = new JButton("Agregar servicio");
		contentPane.add(btnGuardar);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Resetear colores de etiquetas
				lblNombre.setForeground(Color.BLACK);
				lblDia.setForeground(Color.BLACK);
				lblHoraInicio.setForeground(Color.BLACK);
				lblHoraFin.setForeground(Color.BLACK);
				lblPrecioPerro.setForeground(Color.BLACK);
				lblPrecioGato.setForeground(Color.BLACK);
				lblPrecioAve.setForeground(Color.BLACK);
				lblPrecioRoedor.setForeground(Color.BLACK);
				lblPrecioReptil.setForeground(Color.BLACK);

				// Validaciones
				boolean error = false;

				// Validaciones de nombre
				String nombre = txtNombre.getText();
				if (nombre.length() <= 3) {
					lblNombre.setForeground(Color.RED);
					error = true;
				}

				// Validaciones de día
				String diaStr = txtDia.getText();
				LocalDate dia = null;
				if (!diaStr.isEmpty()) {
				    try {
				    	dia = LocalDate.parse(diaStr);
				        if (dia.isBefore(LocalDate.now())) {
				            lblDia.setForeground(Color.RED);
				            error = true;
				        }
				    } catch (DateTimeParseException ex) {
				        lblDia.setForeground(Color.RED);
				        error = true;
				    }
				} else {
				    lblDia.setForeground(Color.RED);
				    error = true;
				}


				// Validaciones de hora de inicio
				String horaInicioStr = txtHoraInicio.getText();
				LocalTime horaInicio = null;
				try {
					horaInicio = LocalTime.parse(horaInicioStr);
					if (dia != null && dia.isEqual(LocalDate.now()) && horaInicio.isBefore(LocalTime.now())) {
						lblHoraInicio.setForeground(Color.RED);
						error = true;
					} else {
						lblHoraInicio.setForeground(Color.BLACK);
					}
				} catch (Exception ex) {
					lblHoraInicio.setForeground(Color.RED);
					error = true;
				}


				// Validaciones de hora de fin
				String horaFinStr = txtHoraFin.getText();
				LocalTime horaFin = null;
				try {
					horaFin = LocalTime.parse(horaFinStr);
					if (dia != null && dia.isEqual(LocalDate.now()) && horaFin.isBefore(LocalTime.now())) {
						lblHoraFin.setForeground(Color.RED);
						error = true;
					} else if (horaInicio != null && horaFin.isBefore(horaInicio)) {
						lblHoraFin.setForeground(Color.RED);
						error = true;
					} else {
						lblHoraFin.setForeground(Color.BLACK);
					}
				} catch (Exception ex) {
					lblHoraFin.setForeground(Color.RED);
					error = true;
				}

				// Validar checkbox de perro y precio
				if (chkPerro.isSelected()) {
					try {
						int precioPerro = Integer.parseInt(txtPrecioPerro.getText());
						if (precioPerro < 0) {
							lblPrecioPerro.setForeground(Color.RED);
							error = true;
						}
					} catch (NumberFormatException ex) {
						lblPrecioPerro.setForeground(Color.RED);
						error = true;
					}
				}

				// Validar checkbox de gato y precio
				if (chkGato.isSelected()) {
					try {
						int precioGato = Integer.parseInt(txtPrecioGato.getText());
						if (precioGato < 0) {
							lblPrecioGato.setForeground(Color.RED);
							error = true;
						}
					} catch (NumberFormatException ex) {
						lblPrecioGato.setForeground(Color.RED);
						error = true;
					}
				}

				// Validar checkbox de ave y precio
				if (chkAve.isSelected()) {
					try {
						int precioAve = Integer.parseInt(txtPrecioAve.getText());
						if (precioAve < 0) {
							lblPrecioAve.setForeground(Color.RED);
							error = true;
						}
					} catch (NumberFormatException ex) {
						lblPrecioAve.setForeground(Color.RED);
						error = true;
					}
				}

				// Validar checkbox de roedor y precio
				if (chkRoedor.isSelected()) {
					try {
						int precioRoedor = Integer.parseInt(txtPrecioRoedor.getText());
						if (precioRoedor < 0) {
							lblPrecioRoedor.setForeground(Color.RED);
							error = true;
						}
					} catch (NumberFormatException ex) {
						lblPrecioRoedor.setForeground(Color.RED);
						error = true;
					}
				}

				// Validar checkbox de reptil y precio
				if (chkReptil.isSelected()) {
					try {
						int precioReptil = Integer.parseInt(txtPrecioReptil.getText());
						if (precioReptil < 0) {
							lblPrecioReptil.setForeground(Color.RED);
							error = true;
						}
					} catch (NumberFormatException ex) {
						lblPrecioReptil.setForeground(Color.RED);
						error = true;
					}
				}

				// Validar que al menos un tipo de animal esté seleccionado
				if (!chkPerro.isSelected() && !chkGato.isSelected() && !chkAve.isSelected() && !chkRoedor.isSelected()
						&& !chkReptil.isSelected()) {
					lblPerro.setForeground(Color.RED);
					lblGato.setForeground(Color.RED);
					lblAve.setForeground(Color.RED);
					lblRoedor.setForeground(Color.RED);
					lblReptil.setForeground(Color.RED);
					error = true;
				} else {
					lblPerro.setForeground(Color.BLACK);
					lblGato.setForeground(Color.BLACK);
					lblAve.setForeground(Color.BLACK);
					lblRoedor.setForeground(Color.BLACK);
					lblReptil.setForeground(Color.BLACK);
				}

				if (error) {
					return;
				} else {
					int puedePerro = chkPerro.isSelected() ? 1 : 0, puedeGato = chkGato.isSelected() ? 1 : 0,
							puedeAve = chkAve.isSelected() ? 1 : 0, puedeRoedor = chkRoedor.isSelected() ? 1 : 0,
							puedeReptil = chkReptil.isSelected() ? 1 : 0;
					int precioPerro = 0, precioGato = 0, precioAve = 0, precioRoedor = 0, precioReptil = 0;
					if (puedePerro == 1) {
						precioPerro = Integer.parseInt(txtPrecioPerro.getText());
					}
					if (precioGato == 1) {
						precioGato = Integer.parseInt(txtPrecioGato.getText());
					}
					if (precioAve == 1) {
						precioAve = Integer.parseInt(txtPrecioAve.getText());
					}
					if (precioRoedor == 1) {
						precioRoedor = Integer.parseInt(txtPrecioRoedor.getText());
					}
					if (precioReptil == 1) {
						precioReptil = Integer.parseInt(txtPrecioReptil.getText());
					}
					
					// Imprimir los valores que se van a enviar al constructor de Servicio
					System.out.println("Creando servicio con los siguientes valores:");
					System.out.println("Nombre: " + nombre);
					System.out.println("Día: " + dia);
					System.out.println("Hora de inicio: " + LocalDateTime.of(dia, horaInicio));
					System.out.println("Hora de fin: " + LocalDateTime.of(dia, horaFin));
					System.out.println("Puede atender perros: " + puedePerro);
					System.out.println("Puede atender gatos: " + puedeGato);
					System.out.println("Puede atender aves: " + puedeAve);
					System.out.println("Puede atender roedores: " + puedeRoedor);
					System.out.println("Puede atender reptiles: " + puedeReptil);
					System.out.println("Precio por perro: " + precioPerro);
					System.out.println("Precio por gato: " + precioGato);
					System.out.println("Precio por ave: " + precioAve);
					System.out.println("Precio por roedor: " + precioRoedor);
					System.out.println("Precio por reptil: " + precioReptil);

					Servicio servicio = new Servicio(0, nombre, dia, LocalDateTime.of(dia, horaInicio), LocalDateTime.of(dia, horaFin), puedePerro, puedeGato,
							puedeAve, puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor,
							precioReptil);

					controlador.addService2(servicio);
				}
			}
		});

		JButton btnVolver = new JButton("Ir atrás");
		contentPane.add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGesSer pantallaGesSer = new PantallaGesSer(LocalDate.now());
				pantallaGesSer.setVisible(true);
				dispose();
			}
		});
	}
}
