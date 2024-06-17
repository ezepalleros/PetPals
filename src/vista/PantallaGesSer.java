package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controladores.ServicioControlador;
import modelos.Servicio;

public class PantallaGesSer extends JFrame {

	LocalDate diaActual = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ServicioControlador controlador;
	private JLabel elemento;
	private Servicio seleccionado;

	/**
	 * Create the frame.
	 */
	public PantallaGesSer(LocalDate diaActual) {
		this.diaActual = diaActual;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		controlador = new ServicioControlador();
		seleccionado = new Servicio(0, "", null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		String[] columnNames = { "ID", "Nombre", "Día", "Hora Inicio", "Hora Fin", "Perro", "Gato", "Ave", "Roedor",
				"Reptil", "Precio Perro", "Precio Gato", "Precio Ave", "Precio Roedor", "Precio Reptil" };
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		actualizarTabla();
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 19, 911, 190);
		contentPane.add(scrollPane);

		elemento = new JLabel("Seleccionado:");
		elemento.setBounds(5, 5, 911, 14);
		contentPane.add(elemento);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(553, 280, 187, 58);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seleccionado.getCodSer() != 0) {
					controlador.deleteService(seleccionado.getCodSer());
					JOptionPane.showMessageDialog(null, "Eliminado");
					actualizarTabla();
					seleccionado = new Servicio(0, "", null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
					elemento.setText("Seleccionado:");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un servicio");
				}
			}
		});
		contentPane.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(5, 280, 166, 58);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seleccionado.getCodSer() != 0) {
					PantallaEdiSer editarServicio = new PantallaEdiSer(seleccionado, diaActual);
					editarServicio.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un servicio");
				}
			}
		});
		contentPane.add(btnEditar);

		JButton btnAgregar = new JButton("Agregar servicio");
		btnAgregar.setBounds(181, 280, 362, 58);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAgrSer agregarServicio = new PantallaAgrSer(LocalDate.now());
				agregarServicio.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnAgregar);

		JButton btnIrAtras = new JButton("Ir atrás");
		btnIrAtras.setBounds(750, 280, 166, 58);
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGerente pantallaGerente = new PantallaGerente();
				pantallaGerente.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnIrAtras);

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						int id = (int) table.getValueAt(selectedRow, 0);
						String nombre = (String) table.getValueAt(selectedRow, 1);
						LocalDate dia = (LocalDate) table.getValueAt(selectedRow, 2);
						LocalDateTime horaInicio = (LocalDateTime) table.getValueAt(selectedRow, 3);
						LocalDateTime horaFin = (LocalDateTime) table.getValueAt(selectedRow, 4);
						int puedePerro = (int) table.getValueAt(selectedRow, 5);
						int puedeGato = (int) table.getValueAt(selectedRow, 6);
						int puedeAve = (int) table.getValueAt(selectedRow, 7);
						int puedeRoedor = (int) table.getValueAt(selectedRow, 8);
						int puedeReptil = (int) table.getValueAt(selectedRow, 9);
						int precioPerro = (int) table.getValueAt(selectedRow, 10);
						int precioGato = (int) table.getValueAt(selectedRow, 11);
						int precioAve = (int) table.getValueAt(selectedRow, 12);
						int precioRoedor = (int) table.getValueAt(selectedRow, 13);
						int precioReptil = (int) table.getValueAt(selectedRow, 14);

						seleccionado = new Servicio(id, nombre, dia, horaInicio, horaFin, puedePerro, puedeGato,
								puedeAve, puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor,
								precioReptil);
						elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre);
					} else {
						seleccionado = new Servicio(0, "", null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
						elemento.setText("Seleccionado:");
					}
				}
			}
		});
	}

	private void actualizarTabla() {
		model.setRowCount(0);
		List<Servicio> servicios = controlador.getAllServices();
		for (Servicio servicio : servicios) {
			model.addRow(new Object[] { servicio.getCodSer(), servicio.getNomSer(), servicio.getDiaSer(),
					servicio.getHoraIniSer(), servicio.getHoraFinSer(), servicio.getPuedePerro(),
					servicio.getPuedeGato(), servicio.getPuedeAve(), servicio.getPuedeRoedor(),
					servicio.getPuedeReptil(), servicio.getPrecioPerro(), servicio.getPrecioGato(),
					servicio.getPrecioAve(), servicio.getPrecioRoedor(), servicio.getPrecioReptil() });
		}
	}

	public void fechaActual(LocalDate fechaActual) {
		diaActual = fechaActual;
	}
}
