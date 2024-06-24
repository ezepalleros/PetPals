package vista;

import java.awt.Font;
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
import controladores.SolicitudControlador;
import modelos.Solicitud;

public class PantallaVerSol extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private SolicitudControlador controlador;

    /**
     * Create the frame.
     */
    public PantallaVerSol() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        controlador = new SolicitudControlador();

        JLabel lblTitulo = new JLabel("Solicitudes Pendientes");
        lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        lblTitulo.setBounds(10, 11, 774, 45);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitulo);

        String[] columnNames = {"ID", "Servicio", "Fecha", "Hora Inicio", "Hora Fin", "Mascota", "Tipo", "Caracter"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 67, 774, 324);
        contentPane.add(scrollPane);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int solicitudId = (int) table.getValueAt(selectedRow, 0);
                    controlador.deleteRequest(solicitudId);
                    JOptionPane.showMessageDialog(null, "Solicitud aceptada y eliminada correctamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una solicitud.");
                }
            }
        });
        btnAceptar.setBounds(10, 402, 120, 40);
        contentPane.add(btnAceptar);

        JButton btnDenegar = new JButton("Denegar");
        btnDenegar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDenegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int solicitudId = (int) table.getValueAt(selectedRow, 0);
                    controlador.deleteRequest(solicitudId);
                    JOptionPane.showMessageDialog(null, "Solicitud denegada y eliminada correctamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una solicitud.");
                }
            }
        });
        btnDenegar.setBounds(140, 402, 120, 40);
        contentPane.add(btnDenegar);

        JButton btnIrAtras = new JButton("Ir atrás");
        btnIrAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaEmpleado pantallaEmpleado = new PantallaEmpleado();
                pantallaEmpleado.setVisible(true);
                dispose();
            }
        });
        btnIrAtras.setBounds(644, 402, 140, 40);
        contentPane.add(btnIrAtras);

        actualizarTabla();
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        List<Solicitud> solicitudes = controlador.getAllRequests();
        for (Solicitud solicitud : solicitudes) {
            model.addRow(new Object[]{
                    solicitud.getCodSol(),
                    solicitud.getCodSer(),
                    solicitud.getDiaSer(),
                    solicitud.getHoraIniSer(),
                    solicitud.getHoraFinSer(),
                    solicitud.getCodMas(),
                    solicitud.getTipoMas(),
                    solicitud.getCaracMas()
            });
        }
    }
}
