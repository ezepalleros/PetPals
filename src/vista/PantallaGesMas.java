package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import controladores.MascotaControlador;
import modelos.Mascota;

public class PantallaGesMas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private MascotaControlador controlador;
	private JLabel elemento;
	private Mascota seleccionado;
	private JCheckBox chckbxPerro, chckbxGato, chckbxAve, chckbxRoedor, chckbxReptil;
	private JButton btnFiltrar;

	/**
	 * Create the frame.
	 */
	public PantallaGesMas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		controlador = new MascotaControlador();
		seleccionado = new Mascota(0, "", "", "", 0, 0, "", 0, 0, 0, 0);

		String[] columnNames = { "ID", "Nombre", "Variedad", "Tipo", "Edad", "Vacunado", "Características", "Dieta",
				"Chip", "Adoptar", "Dueño" };
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		actualizarTabla();
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 19, 911, 230);
		contentPane.add(scrollPane);

		elemento = new JLabel("Seleccionado:");
		elemento.setBounds(5, 5, 911, 14);
		contentPane.add(elemento);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(5, 293, 401, 58);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seleccionado.getCodMas() != 0) {
					controlador.deletePet(seleccionado.getCodMas());
					JOptionPane.showMessageDialog(null, "Eliminado");
					actualizarTabla();
					seleccionado = new Mascota(0, "", "", "", 0, 0, "", 0, 0, 0, 0);
					elemento.setText("Seleccionado:");
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una mascota");
				}
			}
		});
		contentPane.add(btnEliminar);

		JButton btnIrAtras = new JButton("Ir atrás");
		btnIrAtras.setBounds(515, 293, 401, 58);
		btnIrAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGerente pantallaGerente = new PantallaGerente();
				pantallaGerente.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnIrAtras);

		// Añadir checkboxes para filtrar por tipo de animal
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
				filtrarMascotas();
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
						String variedad = (String) table.getValueAt(selectedRow, 2);
						String tipo = (String) table.getValueAt(selectedRow, 3);
						int edad = (int) table.getValueAt(selectedRow, 4);
						int vacunado = (int) table.getValueAt(selectedRow, 5);
						String caracteristicas = (String) table.getValueAt(selectedRow, 6);
						int dieta = (int) table.getValueAt(selectedRow, 7);
						int chip = (int) table.getValueAt(selectedRow, 8);
						int adoptar = (int) table.getValueAt(selectedRow, 9);
						int duenio = (int) table.getValueAt(selectedRow, 10);

						seleccionado = new Mascota(id, nombre, variedad, tipo, edad, vacunado, caracteristicas, dieta,
								chip, adoptar, duenio);
						elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre);
					} else {
						seleccionado = new Mascota(0, "", "", "", 0, 0, "", 0, 0, 0, 0);
						elemento.setText("Seleccionado:");
					}
				}
			}
		});
	}

	private void actualizarTabla() {
		model.setRowCount(0);
		List<Mascota> mascotas = controlador.getAllPets();
		for (Mascota mascota : mascotas) {
			model.addRow(new Object[] { mascota.getCodMas(), mascota.getNomMas(), mascota.getVariMas(),
					mascota.getTipoMas(), mascota.getEdadMas(), mascota.getVacuMas(), mascota.getCaracMas(),
					mascota.getDietMas(), mascota.getChipMas(), mascota.getAdoptar(), mascota.getDueMas() });
		}
	}

	private void filtrarMascotas() {
		model.setRowCount(0);
		List<Mascota> mascotas = controlador.getAllPets();
		for (Mascota mascota : mascotas) {
			boolean agregarFila = false;
			switch (mascota.getVariMas().toLowerCase()) {
			case "perro":
				agregarFila = chckbxPerro.isSelected();
				break;
			case "gato":
				agregarFila = chckbxGato.isSelected();
				break;
			case "ave":
				agregarFila = chckbxAve.isSelected();
				break;
			case "roedor":
				agregarFila = chckbxRoedor.isSelected();
				break;
			case "reptil":
				agregarFila = chckbxReptil.isSelected();
				break;
			}
			if (agregarFila) {
				model.addRow(new Object[] { mascota.getCodMas(), mascota.getNomMas(), mascota.getVariMas(),
						mascota.getTipoMas(), mascota.getEdadMas(), mascota.getVacuMas(), mascota.getCaracMas(),
						mascota.getDietMas(), mascota.getChipMas(), mascota.getAdoptar(), mascota.getDueMas() });
			}
		}
	}
}
