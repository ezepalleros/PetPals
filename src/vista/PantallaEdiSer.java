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

public class PantallaEdiSer extends JFrame {

    private JPanel contentPane;
    private Servicio servicio;
    private ServicioControlador controlador;
    private LocalDate fechaActual;

    private JTextField txtNombre;
    private JTextField txtDia;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;
    private JCheckBox chkPerro;
    private JCheckBox chkGato;
    private JCheckBox chkAve;
    private JCheckBox chkRoedor;
    private JCheckBox chkReptil;
    private JTextField txtPrecioPerro;
    private JTextField txtPrecioGato;
    private JTextField txtPrecioAve;
    private JTextField txtPrecioRoedor;
    private JTextField txtPrecioReptil;

    /**
     * Create the frame.
     */
    public PantallaEdiSer(Servicio servicio, LocalDate fechaActual) {
        this.servicio = servicio;
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

        txtNombre = new JTextField(servicio.getNomSer());
        contentPane.add(txtNombre);

        JLabel lblDia = new JLabel("Día del servicio (yyyy-MM-dd):");
        contentPane.add(lblDia);

        txtDia = new JTextField(servicio.getDiaSer().toString());
        contentPane.add(txtDia);

        JLabel lblHoraInicio = new JLabel("Hora de inicio (HH:mm:ss):");
        contentPane.add(lblHoraInicio);

        txtHoraInicio = new JTextField(servicio.getHoraIniSer().toLocalTime().toString());
        contentPane.add(txtHoraInicio);

        JLabel lblHoraFin = new JLabel("Hora de fin (HH:mm:ss):");
        contentPane.add(lblHoraFin);

        txtHoraFin = new JTextField(servicio.getHoraFinSer().toLocalTime().toString());
        contentPane.add(txtHoraFin);

        JLabel lblPerro = new JLabel("¿Puede atender perros?:");
        contentPane.add(lblPerro);

        chkPerro = new JCheckBox();
        chkPerro.setSelected(servicio.getPuedePerro() == 1);
        contentPane.add(chkPerro);

        JLabel lblGato = new JLabel("¿Puede atender gatos?:");
        contentPane.add(lblGato);

        chkGato = new JCheckBox();
        chkGato.setSelected(servicio.getPuedeGato() == 1);
        contentPane.add(chkGato);

        JLabel lblAve = new JLabel("¿Puede atender aves?:");
        contentPane.add(lblAve);

        chkAve = new JCheckBox();
        chkAve.setSelected(servicio.getPuedeAve() == 1);
        contentPane.add(chkAve);

        JLabel lblRoedor = new JLabel("¿Puede atender roedores?:");
        contentPane.add(lblRoedor);

        chkRoedor = new JCheckBox();
        chkRoedor.setSelected(servicio.getPuedeRoedor() == 1);
        contentPane.add(chkRoedor);

        JLabel lblReptil = new JLabel("¿Puede atender reptiles?:");
        contentPane.add(lblReptil);

        chkReptil = new JCheckBox();
        chkReptil.setSelected(servicio.getPuedeReptil() == 1);
        contentPane.add(chkReptil);

        JLabel lblPrecioPerro = new JLabel("Precio por perro:");
        contentPane.add(lblPrecioPerro);

        txtPrecioPerro = new JTextField(String.valueOf(servicio.getPrecioPerro()));
        contentPane.add(txtPrecioPerro);

        JLabel lblPrecioGato = new JLabel("Precio por gato:");
        contentPane.add(lblPrecioGato);

        txtPrecioGato = new JTextField(String.valueOf(servicio.getPrecioGato()));
        contentPane.add(txtPrecioGato);

        JLabel lblPrecioAve = new JLabel("Precio por ave:");
        contentPane.add(lblPrecioAve);

        txtPrecioAve = new JTextField(String.valueOf(servicio.getPrecioAve()));
        contentPane.add(txtPrecioAve);

        JLabel lblPrecioRoedor = new JLabel("Precio por roedor:");
        contentPane.add(lblPrecioRoedor);

        txtPrecioRoedor = new JTextField(String.valueOf(servicio.getPrecioRoedor()));
        contentPane.add(txtPrecioRoedor);

        JLabel lblPrecioReptil = new JLabel("Precio por reptil:");
        contentPane.add(lblPrecioReptil);

        txtPrecioReptil = new JTextField(String.valueOf(servicio.getPrecioReptil()));
        contentPane.add(txtPrecioReptil);

        JButton btnGuardar = new JButton("Guardar cambios");
        contentPane.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean error = false;

                // Validaciones de nombre
                String nombre = txtNombre.getText();
                if (nombre.length() <= 3) {
                    lblNombre.setForeground(Color.RED);
                    error = true;
                } else {
                    lblNombre.setForeground(Color.BLACK);
                }

                // Validaciones de día
                String diaStr = txtDia.getText();
                LocalDate dia = null;
                try {
                    dia = LocalDate.parse(diaStr);
                    if (dia.isBefore(LocalDate.now())) {
                        lblDia.setForeground(Color.RED);
                        error = true;
                    } else {
                        lblDia.setForeground(Color.BLACK);
                    }
                } catch (Exception ex) {
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

                // Validar checkbox de perro
                if (chkPerro.isSelected()) {
                    try {
                        int precioPerro = Integer.parseInt(txtPrecioPerro.getText());
                        if (precioPerro < 0) {
                            lblPrecioPerro.setForeground(Color.RED);
                            error = true;
                        } else {
                            lblPrecioPerro.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblPrecioPerro.setForeground(Color.RED);
                        error = true;
                    }
                }

                // Validar checkbox de gato
                if (chkGato.isSelected()) {
                    try {
                        int precioGato = Integer.parseInt(txtPrecioGato.getText());
                        if (precioGato < 0) {
                            lblPrecioGato.setForeground(Color.RED);
                            error = true;
                        } else {
                            lblPrecioGato.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblPrecioGato.setForeground(Color.RED);
                        error = true;
                    }
                }

                // Validar checkbox de ave
                if (chkAve.isSelected()) {
                    try {
                        int precioAve = Integer.parseInt(txtPrecioAve.getText());
                        if (precioAve < 0) {
                            lblPrecioAve.setForeground(Color.RED);
                            error = true;
                        } else {
                            lblPrecioAve.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblPrecioAve.setForeground(Color.RED);
                        error = true;
                    }
                }

                // Validar checkbox de roedor
                if (chkRoedor.isSelected()) {
                    try {
                        int precioRoedor = Integer.parseInt(txtPrecioRoedor.getText());
                        if (precioRoedor < 0) {
                            lblPrecioRoedor.setForeground(Color.RED);
                            error = true;
                        } else {
                            lblPrecioRoedor.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblPrecioRoedor.setForeground(Color.RED);
                        error = true;
                    }
                }

             // Validar checkbox de reptil
                if (chkReptil.isSelected()) {
                    try {
                        int precioReptil = Integer.parseInt(txtPrecioReptil.getText());
                        if (precioReptil < 0) {
                            lblPrecioReptil.setForeground(Color.RED);
                            error = true;
                        } else {
                            lblPrecioReptil.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        lblPrecioReptil.setForeground(Color.RED);
                        error = true;
                    }
                }

                // Validar que al menos un tipo de animal esté seleccionado
                if (!chkPerro.isSelected() && !chkGato.isSelected() && !chkAve.isSelected()
                        && !chkRoedor.isSelected() && !chkReptil.isSelected()) {
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

                // Si no hay errores, actualizar el servicio
                if (!error) {
                    int puedePerro = chkPerro.isSelected() ? 1 : 0;
                    int puedeGato = chkGato.isSelected() ? 1 : 0;
                    int puedeAve = chkAve.isSelected() ? 1 : 0;
                    int puedeRoedor = chkRoedor.isSelected() ? 1 : 0;
                    int puedeReptil = chkReptil.isSelected() ? 1 : 0;
                    int precioPerro = Integer.parseInt(txtPrecioPerro.getText());
                    int precioGato = Integer.parseInt(txtPrecioGato.getText());
                    int precioAve = Integer.parseInt(txtPrecioAve.getText());
                    int precioRoedor = Integer.parseInt(txtPrecioRoedor.getText());
                    int precioReptil = Integer.parseInt(txtPrecioReptil.getText());

                    Servicio servicioActualizado = new Servicio(servicio.getCodSer(), nombre, dia,
                            LocalDateTime.of(dia, horaInicio), LocalDateTime.of(dia, horaFin),
                            puedePerro, puedeGato, puedeAve, puedeRoedor, puedeReptil,
                            precioPerro, precioGato, precioAve, precioRoedor, precioReptil);

                    controlador.updateService(servicioActualizado);
                    dispose();
                    PantallaGesSer pantallaGesSer = new PantallaGesSer(fechaActual);
                    pantallaGesSer.setVisible(true);
                }
            }
        });
    }

    public void actualizarFechaActual(LocalDate nuevaFecha) {
        this.fechaActual = nuevaFecha;
    }
}