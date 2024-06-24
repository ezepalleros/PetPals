package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import controladores.MascotaControlador;
import modelos.Mascota;

public class PantallaVerMas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private MascotaControlador mascotaControlador;
    private JLabel elemento;
    private Mascota seleccionada;
    private JButton btnEditar;
    private JButton btnEliminar;
    private int codCli;

    /**
     * Create the frame.
     */
    public PantallaVerMas(int codCli) {
        this.codCli = codCli;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 942, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        mascotaControlador = new MascotaControlador();

        List<Mascota> mascotasCliente = mascotaControlador.getPetsByClient(codCli);
        if (mascotasCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El cliente no tiene mascotas registradas");
            dispose();
        }

        String[] columnNames = { "ID", "Nombre", "Variedad", "Tipo", "Edad", "Vacunado", "Características", "Dieta", "Chip", "Adoptar", "Dueño" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla(mascotasCliente);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(553, 293, 187, 58);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionada != null && seleccionada.getCodMas() != 0) {
                    mascotaControlador.deletePet(seleccionada.getCodMas());
                    JOptionPane.showMessageDialog(null, "Mascota Eliminada");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una mascota");
                }
            }
        });
        contentPane.add(btnEliminar);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(5, 293, 166, 58);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	PantallaAgrMas agregarMascota = new PantallaAgrMas(codCli);
                    agregarMascota.setVisible(true);
                    dispose();
            }
        });
        contentPane.add(btnAgregar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(181, 293, 166, 58);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionada != null && seleccionada.getCodMas() != 0) {
                    PantallaEdiMas editarMascota = new PantallaEdiMas(seleccionada);
                    editarMascota.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una mascota");
                }
            }
        });
        contentPane.add(btnEditar);

        JButton btnIrAtras = new JButton("Ir atrás");
        btnIrAtras.setBounds(750, 293, 166, 58);
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaCliente pantallaCliente = new PantallaCliente(codCli);
                pantallaCliente.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnIrAtras);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
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

                    seleccionada = new Mascota(id, nombre, variedad, tipo, edad, vacunado, caracteristicas, dieta, chip, adoptar, codCli);
                    elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre);
                } else {
                    seleccionada = null;
                    elemento.setText("Seleccionado:");
                }
            }
        });
    }

    private void actualizarTabla(List<Mascota> mascotas) {
        model.setRowCount(0);
        for (Mascota mascota : mascotas) {
            model.addRow(new Object[] {
                mascota.getCodMas(),
                mascota.getNomMas(),
                mascota.getVariMas(),
                mascota.getTipoMas(),
                mascota.getEdadMas(),
                mascota.getVacuMas(),
                mascota.getCaracMas(),
                mascota.getDietMas(),
                mascota.getChipMas(),
                mascota.getAdoptar(),
                mascota.getDueMas()
            });
        }
    }
}
