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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.ClienteControlador;
import modelos.Cliente;
import modelos.Mascota;

public class PantallaGesCli extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ClienteControlador controlador;
    private JLabel elemento;
    private Cliente seleccionado;

    public PantallaGesCli() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new ClienteControlador();
        seleccionado = new Cliente(0, "", "", "", "");

        String[] columnNames = { "ID", "Nombre", "Email", "Dirección", "Teléfono" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 5, 770, 300);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 315, 770, 14);
        contentPane.add(elemento);

        JButton btnVerMascotas = new JButton("Ver Mascotas");
        btnVerMascotas.setBounds(5, 340, 380, 40);
        btnVerMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getCodUsu() != 0) {
                    List<Mascota> mascotas = controlador.getPetsByClient(seleccionado.getCodUsu());
                    if (mascotas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Este cliente no tiene mascotas.");
                    } else {
                        String mensaje = controlador.formatPetInfo(mascotas);
                        JOptionPane.showMessageDialog(null, mensaje);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente.");
                }
            }
        });
        contentPane.add(btnVerMascotas);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(395, 340, 380, 40);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getCodUsu() != 0) {
                    controlador.deleteClient(seleccionado.getCodUsu());
                    JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                    actualizarTabla();
                    seleccionado = new Cliente(0, "", "", "", "");
                    elemento.setText("Seleccionado:");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente.");
                }
            }
        });
        contentPane.add(btnEliminar);

        JButton btnIrAtras = new JButton("Ir atrás");
        btnIrAtras.setBounds(5, 400, 770, 40);
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
                        String email = (String) table.getValueAt(selectedRow, 2);
                        String direccion = (String) table.getValueAt(selectedRow, 3);
                        String telefono = (String) table.getValueAt(selectedRow, 4);

                        seleccionado = new Cliente(id, nombre, email, direccion, telefono);
                        elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre);
                    } else {
                        seleccionado = new Cliente(0, "", "", "", "");
                        elemento.setText("Seleccionado:");
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        List<Cliente> clientes = controlador.getAllClients();
        for (Cliente cliente : clientes) {
            model.addRow(new Object[] {
                cliente.getCodUsu(),
                cliente.getNomUsu(),
                cliente.getMailUsu(),
                cliente.getDirCli(),
                cliente.getTelUsu()
            });
        }
    }
}
