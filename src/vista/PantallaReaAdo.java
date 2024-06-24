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

import controladores.AdoptarControlador;
import controladores.MascotaControlador;
import modelos.Adoptar;
import modelos.Mascota;

public class PantallaReaAdo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Mascota seleccionada;

    public PantallaReaAdo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 942, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = { "ID", "Nombre", "Variedad", "Tipo", "Edad", "Vacunado", "Características", "Dieta", "Chip", "Adoptar", "Dueño" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        JLabel elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);

        JButton btnSolicitarAdopcion = new JButton("Solicitar Adopción");
        btnSolicitarAdopcion.setBounds(5, 233, 911, 58);
        btnSolicitarAdopcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionada != null && seleccionada.getAdoptar() == 1) {
                    AdoptarControlador adoptarControlador = new AdoptarControlador();
                    Adoptar nuevaAdopcion = new Adoptar(0, seleccionada.getCodMas(), seleccionada.getVariMas(), seleccionada.getEdadMas(), seleccionada.getDueMas());
                    adoptarControlador.addAdoption(nuevaAdopcion);
                    JOptionPane.showMessageDialog(null, "Solicitud de adopción creada exitosamente");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una mascota para adoptar");
                }
            }
        });
        contentPane.add(btnSolicitarAdopcion);

        cargarMascotasParaAdopcion();

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    seleccionada = new Mascota(
                        (int) table.getValueAt(selectedRow, 0),
                        (String) table.getValueAt(selectedRow, 1),
                        (String) table.getValueAt(selectedRow, 2),
                        (String) table.getValueAt(selectedRow, 3),
                        (int) table.getValueAt(selectedRow, 4),
                        (int) table.getValueAt(selectedRow, 5),
                        (String) table.getValueAt(selectedRow, 6),
                        (int) table.getValueAt(selectedRow, 7),
                        (int) table.getValueAt(selectedRow, 8),
                        (int) table.getValueAt(selectedRow, 9),
                        (int) table.getValueAt(selectedRow, 10)
                    );
                    elemento.setText("Seleccionado: ID=" + seleccionada.getCodMas() + ", Nombre=" + seleccionada.getNomMas());
                } else {
                    seleccionada = null;
                    elemento.setText("Seleccionado:");
                }
            }
        });
    }

    private void cargarMascotasParaAdopcion() {
        MascotaControlador mascotaControlador = new MascotaControlador();
        List<Mascota> mascotasAdopcion = mascotaControlador.getMascotasParaAdopcion();

        if (mascotasAdopcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay mascotas disponibles para adopción");
            dispose();
        } else {
            actualizarTabla(mascotasAdopcion);
        }
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
