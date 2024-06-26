package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private JButton btnBuscar;
	private JTextField txtBuscar;
	private JCheckBox chckbxPerro, chckbxGato, chckbxAve, chckbxRoedor, chckbxReptil;
	private JButton btnFiltrar;

	/**
	 * Create the frame.
	 */
	public PantallaGesSer(LocalDate diaActual) {
		this.diaActual = diaActual;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 402);
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
		btnEliminar.setBounds(553, 293, 187, 58);
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
		btnEditar.setBounds(5, 293, 166, 58);
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
		btnAgregar.setBounds(181, 293, 362, 58);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAgrSer agregarServicio = new PantallaAgrSer(LocalDate.now());
				agregarServicio.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnAgregar);

		JButton btnIrAtras = new JButton("Ir atrás");
		btnIrAtras.setBounds(750, 293, 166, 58);
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGerente pantallaGerente = new PantallaGerente();
				pantallaGerente.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnIrAtras);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(5, 220, 700, 30);
		contentPane.add(txtBuscar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(710, 220, 206, 30);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarServicioPorNombre(txtBuscar.getText());
			}
		});
		contentPane.add(btnBuscar);

		chckbxPerro = new JCheckBox("Perro");
		chckbxPerro.setBounds(5, 255, 97, 23);
		contentPane.add(chckbxPerro);

		chckbxGato = new JCheckBox("Gato");
		chckbxGato.setBounds(105, 255, 97, 23);
		contentPane.add(chckbxGato);

		chckbxAve = new JCheckBox("Ave");
		chckbxAve.setBounds(205, 255, 97, 23);
		contentPane.add(chckbxAve);

		chckbxRoedor = new JCheckBox("Roedor");
		chckbxRoedor.setBounds(305, 255, 97, 23);
		contentPane.add(chckbxRoedor);

		chckbxReptil = new JCheckBox("Reptil");
		chckbxReptil.setBounds(405, 255, 97, 23);
		contentPane.add(chckbxReptil);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(505, 255, 89, 23);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarServicios();
			}
		});
		contentPane.add(btnFiltrar);

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

	private void buscarServicioPorNombre(String nombre) {
		boolean encontrado = false;
		for (int i = 0; i < table.getRowCount(); i++) {
			String nombreTabla = (String) table.getValueAt(i, 1);
			if (nombreTabla.equalsIgnoreCase(nombre)) {
				table.setRowSelectionInterval(i, i);
				int id = (int) table.getValueAt(i, 0);
				String nombreSer = (String) table.getValueAt(i, 1);
				LocalDate dia = (LocalDate) table.getValueAt(i, 2);
				LocalDateTime horaInicio = (LocalDateTime) table.getValueAt(i, 3);
				LocalDateTime horaFin = (LocalDateTime) table.getValueAt(i, 4);
				int puedePerro = (int) table.getValueAt(i, 5);
				int puedeGato = (int) table.getValueAt(i, 6);
				int puedeAve = (int) table.getValueAt(i, 7);
				int puedeRoedor = (int) table.getValueAt(i, 8);
				int puedeReptil = (int) table.getValueAt(i, 9);
				int precioPerro = (int) table.getValueAt(i, 10);
				int precioGato = (int) table.getValueAt(i, 11);
				int precioAve = (int) table.getValueAt(i, 12);
				int precioRoedor = (int) table.getValueAt(i, 13);
				int precioReptil = (int) table.getValueAt(i, 14);

				seleccionado = new Servicio(id, nombreSer, dia, horaInicio, horaFin, puedePerro, puedeGato, puedeAve,
						puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor, precioReptil);
				elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombreSer);
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			JOptionPane.showMessageDialog(null, "No se encontró ningún servicio con ese nombre.");
		}
	}

	private void filtrarServicios() {
		boolean filtrarPerro = chckbxPerro.isSelected();
		boolean filtrarGato = chckbxGato.isSelected();
		boolean filtrarAve = chckbxAve.isSelected();
		boolean filtrarRoedor = chckbxRoedor.isSelected();
		boolean filtrarReptil = chckbxReptil.isSelected();

		model.setRowCount(0);
		List<Servicio> servicios = controlador.getAllServices();
		for (Servicio servicio : servicios) {
			boolean cumpleFiltro = (!filtrarPerro || servicio.getPuedePerro() == 1)
					&& (!filtrarGato || servicio.getPuedeGato() == 1) && (!filtrarAve || servicio.getPuedeAve() == 1)
					&& (!filtrarRoedor || servicio.getPuedeRoedor() == 1)
					&& (!filtrarReptil || servicio.getPuedeReptil() == 1);

			if (cumpleFiltro) {
				model.addRow(new Object[] { servicio.getCodSer(), servicio.getNomSer(), servicio.getDiaSer(),
						servicio.getHoraIniSer(), servicio.getHoraFinSer(), servicio.getPuedePerro(),
						servicio.getPuedeGato(), servicio.getPuedeAve(), servicio.getPuedeRoedor(),
						servicio.getPuedeReptil(), servicio.getPrecioPerro(), servicio.getPrecioGato(),
						servicio.getPrecioAve(), servicio.getPrecioRoedor(), servicio.getPrecioReptil() });
			}
		}
	}
}
