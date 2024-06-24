package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
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
import controladores.EmpleadoControlador;
import modelos.Empleado;

public class PantallaGesEmp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private EmpleadoControlador controlador;
    private JLabel elemento;
    private Empleado seleccionado;
    private JTextField txtCalificacion;

    /**
     * Create the frame.
     */
    public PantallaGesEmp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        controlador = new EmpleadoControlador();
        seleccionado = new Empleado(0, "", "", "", null, "", 0);

        String[] columnNames = { "ID", "Nombre", "Email", "Teléfono", "Antigüedad", "Detalle", "Calificación" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 5, 770, 300);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 315, 770, 14);
        contentPane.add(elemento);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(395, 373, 380, 40);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getCodUsu() != 0) {
                    controlador.deleteEmployee(seleccionado.getCodUsu());
                    JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
                    actualizarTabla();
                    seleccionado = new Empleado(0, "", "", "", null, "", 0);
                    elemento.setText("Seleccionado:");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un empleado");
                }
            }
        });
        contentPane.add(btnEliminar);

        JButton btnIrAtras = new JButton("Ir atrás");
        btnIrAtras.setBounds(5, 373, 380, 40);
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaGerente pantallaGerente = new PantallaGerente();
                pantallaGerente.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnIrAtras);

        JLabel lblCalificacion = new JLabel("Calificación mayor que:");
        lblCalificacion.setBounds(5, 340, 150, 20);
        contentPane.add(lblCalificacion);

        txtCalificacion = new JTextField();
        txtCalificacion.setBounds(160, 340, 100, 20);
        contentPane.add(txtCalificacion);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(270, 340, 100, 20);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrarEmpleados();
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
                        String email = (String) table.getValueAt(selectedRow, 2);
                        String telefono = (String) table.getValueAt(selectedRow, 3);
                        LocalDate antiguedad = (LocalDate) table.getValueAt(selectedRow, 4);
                        String detalle = (String) table.getValueAt(selectedRow, 5);
                        int calificacion = (int) table.getValueAt(selectedRow, 6);

                        seleccionado = new Empleado(id, nombre, email, telefono, antiguedad, detalle, calificacion);
                        elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre);
                    } else {
                        seleccionado = new Empleado(0, "", "", "", null, "", 0);
                        elemento.setText("Seleccionado:");
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        List<Empleado> empleados = controlador.getAllEmployees();
        for (Empleado empleado : empleados) {
            model.addRow(new Object[] { empleado.getCodUsu(), empleado.getNomUsu(), empleado.getMailUsu(),
                    empleado.getTelUsu(), empleado.getAntiEmp(), empleado.getDetalleEmp(), empleado.getCalifEmp() });
        }
    }

    private void filtrarEmpleados() {
        int calificacionFiltro;
        try {
            calificacionFiltro = Integer.parseInt(txtCalificacion.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una calificación válida.");
            return;
        }

        model.setRowCount(0);
        List<Empleado> empleados = controlador.getAllEmployees();
        for (Empleado empleado : empleados) {
            if (empleado.getCalifEmp() > calificacionFiltro) {
                model.addRow(new Object[] { empleado.getCodUsu(), empleado.getNomUsu(), empleado.getMailUsu(),
                        empleado.getTelUsu(), empleado.getAntiEmp(), empleado.getDetalleEmp(), empleado.getCalifEmp() });
            }
        }
    }
}
