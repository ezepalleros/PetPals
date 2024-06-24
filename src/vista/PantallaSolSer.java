package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controladores.MascotaControlador;
import controladores.ServicioControlador;
import controladores.SolicitudControlador;
import modelos.Mascota;
import modelos.Servicio;
import modelos.Solicitud;

public class PantallaSolSer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ServicioControlador servicioControlador;
    private MascotaControlador mascotaControlador;
    private Servicio seleccionado;
    private JComboBox<Mascota> comboBoxMascota;
    private JTextField txtFecha, txtHoraInicio, txtHoraFin;
    private JLabel lblFecha, lblHoraInicio, lblHoraFin, lblMascota;
    private int codigoCliente;

    public PantallaSolSer(List<Mascota> mascotas, int codigoCliente) {
        this.codigoCliente = codigoCliente;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 942, 415);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        servicioControlador = new ServicioControlador();
        mascotaControlador = new MascotaControlador();

        String[] columnNames = { "ID", "Nombre", "Día", "Hora Inicio", "Hora Fin", 
                "Perro", "Gato", "Ave", "Roedor", "Reptil", 
                "PrecioPerro", "PrecioGato", "PrecioAve", "PrecioRoedor", "PrecioReptil" };

        model = new DefaultTableModel(columnNames, 0);
        contentPane.setLayout(null);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(5, 5, 911, 14);
        contentPane.add(lblSeleccionado);

        txtFecha = new JTextField();
        txtFecha.setBounds(150, 220, 200, 30);
        contentPane.add(txtFecha);
        txtFecha.setColumns(10);

        lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(5, 220, 150, 30);
        contentPane.add(lblFecha);

        txtHoraInicio = new JTextField();
        txtHoraInicio.setBounds(150, 260, 200, 30);
        contentPane.add(txtHoraInicio);
        txtHoraInicio.setColumns(10);

        lblHoraInicio = new JLabel("Hora Inicio (HH:MM):");
        lblHoraInicio.setBounds(5, 260, 150, 30);
        contentPane.add(lblHoraInicio);

        txtHoraFin = new JTextField();
        txtHoraFin.setBounds(150, 300, 200, 30);
        contentPane.add(txtHoraFin);
        txtHoraFin.setColumns(10);

        lblHoraFin = new JLabel("Hora Fin (HH:MM):");
        lblHoraFin.setBounds(5, 300, 150, 30);
        contentPane.add(lblHoraFin);

        comboBoxMascota = new JComboBox<>();
        for (Mascota mascota : mascotas) {
            comboBoxMascota.addItem(mascota);
        }
        comboBoxMascota.setBounds(150, 340, 200, 30);
        comboBoxMascota.setRenderer(new javax.swing.ListCellRenderer<Mascota>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<? extends Mascota> list, Mascota value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                label.setText(value.getNomMas() + " - " + value.getTipoMas());
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                label.setOpaque(true);
                return label;
            }
        });
        contentPane.add(comboBoxMascota);

        lblMascota = new JLabel("Seleccione Mascota:");
        lblMascota.setBounds(5, 340, 150, 30);
        contentPane.add(lblMascota);

        JButton btnSolicitar = new JButton("Solicitar Servicio");
        btnSolicitar.setBounds(380, 220, 200, 150);
        btnSolicitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarYCrearSolicitud();
            }
        });
        contentPane.add(btnSolicitar);

        JButton btnAtras = new JButton("Ir atrás");
        btnAtras.setBounds(600, 220, 150, 150);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaCliente pantallaCliente = new PantallaCliente(codigoCliente);
                pantallaCliente.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnAtras);

        cargarServicios();
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int colCount = table.getColumnCount();
                    Object[] rowData = new Object[colCount];
                    for (int i = 0; i < colCount; i++) {
                        rowData[i] = table.getValueAt(selectedRow, i);
                    }
                    try {
                    	int perro = rowData[10] != null ? Integer.valueOf(rowData[10].toString()) : 0;
                    	int gato = rowData[11] != null ? Integer.valueOf(rowData[11].toString()) : 0;
                    	int ave = rowData[12] != null ? Integer.valueOf(rowData[12].toString()) : 0;
                    	int roedor = rowData[13] != null ? Integer.valueOf(rowData[13].toString()) : 0;
                    	int reptil = rowData[14] != null ? Integer.valueOf(rowData[14].toString()) : 0;

                    	seleccionado = new Servicio(
                    	    (int) rowData[0],
                    	    (String) rowData[1],
                    	    (LocalDate) rowData[2],
                    	    (LocalDateTime) rowData[3],
                    	    (LocalDateTime) rowData[4],
                    	    ((Integer) rowData[5]).intValue(),
                    	    ((Integer) rowData[6]).intValue(),
                    	    ((Integer) rowData[7]).intValue(),
                    	    ((Integer) rowData[8]).intValue(),
                    	    ((Integer) rowData[9]).intValue(),
                    	    perro,
                    	    gato,
                    	    ave,
                    	    roedor,
                    	    reptil
                    	);
                    } catch (NullPointerException | ClassCastException ex) {
                        ex.printStackTrace();
                        seleccionado = null;
                    }
                    lblSeleccionado.setText("Seleccionado: ID=" + seleccionado.getCodSer() + ", Nombre=" + seleccionado.getNomSer());
                } else {
                    seleccionado = null;
                    lblSeleccionado.setText("Seleccionado:");
                }
            }
        });


    }

    private void cargarServicios() {
        List<Servicio> servicios = servicioControlador.getAllServices();
        for (Servicio servicio : servicios) {
            model.addRow(new Object[] {
                servicio.getCodSer(),
                servicio.getNomSer(),
                servicio.getDiaSer(),
                servicio.getHoraIniSer(),
                servicio.getHoraFinSer(),
                servicio.getPuedePerro(),
                servicio.getPuedeGato(),
                servicio.getPuedeAve(),
                servicio.getPuedeRoedor(),
                servicio.getPuedeReptil()
            });
        }
    }

    private void validarYCrearSolicitud() {
        lblFecha.setForeground(Color.BLACK);
        lblHoraInicio.setForeground(Color.BLACK);
        lblHoraFin.setForeground(Color.BLACK);
        lblMascota.setForeground(Color.BLACK);

        boolean error = false;

        if (seleccionado == null) {
            error = true;
        }

        LocalDate fecha = null;
        try {
            fecha = LocalDate.parse(txtFecha.getText());
            if (fecha.isBefore(LocalDate.now()) || fecha.isAfter(seleccionado.getDiaSer())) {
                lblFecha.setForeground(Color.RED);
                error = true;
            }
        } catch (Exception e) {
            lblFecha.setForeground(Color.RED);
            error = true;
        }

        LocalTime horaInicio = null, horaFin = null;
        try {
            horaInicio = LocalTime.parse(txtHoraInicio.getText());
            horaFin = LocalTime.parse(txtHoraFin.getText());
            if (horaInicio.isBefore(LocalTime.of(10, 0)) || horaFin.isAfter(LocalTime.of(20, 30)) || horaInicio.isAfter(horaFin)) {
                lblHoraInicio.setForeground(Color.RED);
                lblHoraFin.setForeground(Color.RED);
                error = true;
            }
        } catch (Exception e) {
            lblHoraInicio.setForeground(Color.RED);
            lblHoraFin.setForeground(Color.RED);
            error = true;
        }

        Mascota mascota = (Mascota) comboBoxMascota.getSelectedItem();
        if (mascota == null) {
            lblMascota.setForeground(Color.RED);
            error = true;
        } else {
            if (mascota.getTipoMas().contains("Perro") && seleccionado.getPuedePerro() == 0) {
                lblMascota.setForeground(Color.RED);
                error = true;
            } else if (mascota.getTipoMas().contains("Gato") && seleccionado.getPuedeGato() == 0) {
                lblMascota.setForeground(Color.RED);
                error = true;
            } else if (mascota.getTipoMas().contains("Ave") && seleccionado.getPuedeAve() == 0) {
                lblMascota.setForeground(Color.RED);
                error = true;
            } else if (mascota.getTipoMas().contains("Roedor") && seleccionado.getPuedeRoedor() == 0) {
                lblMascota.setForeground(Color.RED);
                error = true;
            } else if (mascota.getTipoMas().contains("Reptil") && seleccionado.getPuedeReptil() == 0) {
                lblMascota.setForeground(Color.RED);
                error = true;
            }
        }

        if (!error) {
            Solicitud solicitud = new Solicitud(
                0, 
                seleccionado.getCodSer(), 
                fecha, 
                LocalDateTime.of(fecha, horaInicio), 
                LocalDateTime.of(fecha, horaFin), 
                mascota.getCodMas(), 
                mascota.getTipoMas(), 
                mascota.getCaracMas()
            );
            SolicitudControlador solicitudControlador = new SolicitudControlador();
            solicitudControlador.addRequest(solicitud);
            JOptionPane.showMessageDialog(this, "Solicitud creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            PantallaCliente pantallaCliente = new PantallaCliente(mascota.getDueMas());
            pantallaCliente.setVisible(true);
            dispose();
        }
    }
}
