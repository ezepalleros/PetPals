package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controladores.ClienteControlador;
import modelos.Cliente;

public class PantallaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
    private JTextField inpCorreo;
    private JTextField inpDireccion;
    private JTextField inpTelefono;

    private ClienteControlador clienteControlador = new ClienteControlador();
    private JLabel lblErrorNombre;
    private JLabel lblErrorCorreo;
    private JLabel lblErrorDireccion;
    private JLabel lblErrorTelefono;

    /**
     * Create the frame.
     */
    public PantallaRegistro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 582);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNombre.setBounds(207, 110, 215, 28);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inpNombre.setForeground(Color.GRAY);
        inpNombre.setText("oscarGomez");
        inpNombre.setBounds(207, 148, 245, 38);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);
        inpNombre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpNombre.getText().equals("oscarGomez")) {
                    inpNombre.setText("");
                    inpNombre.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inpNombre.getText().isEmpty()) {
                    inpNombre.setForeground(Color.GRAY);
                    inpNombre.setText("oscarGomez");
                }
            }
        });

        lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setBounds(207, 186, 245, 20);
        contentPane.add(lblErrorNombre);
        lblErrorNombre.setVisible(false);

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCorreo.setBounds(207, 196, 215, 28);
        contentPane.add(lblCorreo);

        inpCorreo = new JTextField();
        inpCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inpCorreo.setForeground(Color.GRAY);
        inpCorreo.setText("oscarGomez@gmail.com");
        inpCorreo.setBounds(207, 235, 245, 38);
        contentPane.add(inpCorreo);
        inpCorreo.setColumns(10);
        inpCorreo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpCorreo.getText().equals("oscarGomez@gmail.com")) {
                    inpCorreo.setText("");
                    inpCorreo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inpCorreo.getText().isEmpty()) {
                    inpCorreo.setForeground(Color.GRAY);
                    inpCorreo.setText("oscarGomez@gmail.com");
                }
            }
        });

        lblErrorCorreo = new JLabel("");
        lblErrorCorreo.setForeground(Color.RED);
        lblErrorCorreo.setBounds(207, 273, 245, 20);
        contentPane.add(lblErrorCorreo);
        lblErrorCorreo.setVisible(false);

        JLabel lblDireccion = new JLabel("Dirección");
        lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDireccion.setBounds(207, 283, 215, 28);
        contentPane.add(lblDireccion);

        inpDireccion = new JTextField();
        inpDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inpDireccion.setForeground(Color.GRAY);
        inpDireccion.setText("Aconcagua 2445 c");
        inpDireccion.setBounds(207, 321, 245, 38);
        contentPane.add(inpDireccion);
        inpDireccion.setColumns(10);
        inpDireccion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpDireccion.getText().equals("Aconcagua 2445 c")) {
                    inpDireccion.setText("");
                    inpDireccion.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inpDireccion.getText().isEmpty()) {
                    inpDireccion.setForeground(Color.GRAY);
                    inpDireccion.setText("Aconcagua 2445 c");
                }
            }
        });

        lblErrorDireccion = new JLabel("");
        lblErrorDireccion.setForeground(Color.RED);
        lblErrorDireccion.setBounds(207, 359, 245, 20);
        contentPane.add(lblErrorDireccion);
        lblErrorDireccion.setVisible(false);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTelefono.setBounds(207, 369, 215, 28);
        contentPane.add(lblTelefono);

        inpTelefono = new JTextField();
        inpTelefono.setText("1137894221");
        inpTelefono.setForeground(Color.GRAY);
        inpTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inpTelefono.setBounds(207, 407, 245, 38);
        contentPane.add(inpTelefono);
        inpTelefono.setColumns(10);
        inpTelefono.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpTelefono.getText().equals("1137894221")) {
                    inpTelefono.setText("");
                    inpTelefono.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inpTelefono.getText().isEmpty()) {
                    inpTelefono.setForeground(Color.GRAY);
                    inpTelefono.setText("1137894221");
                }
            }
        });

        lblErrorTelefono = new JLabel("");
        lblErrorTelefono.setForeground(Color.RED);
        lblErrorTelefono.setBounds(207, 445, 245, 20);
        contentPane.add(lblErrorTelefono);
        lblErrorTelefono.setVisible(false);

        JLabel lblError = new JLabel("");
        lblError.setBounds(56, 447, 500, 28);
        contentPane.add(lblError);
        lblError.setVisible(false);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = inpNombre.getText();
                String correoUsuario = inpCorreo.getText();
                String direccionUsuario = inpDireccion.getText();
                String telefonoUsuario = inpTelefono.getText();

                boolean error = false;

                if (nombreUsuario.isEmpty() || nombreUsuario.equals("oscarGomez")) {
                    lblErrorNombre.setText("Error: Ingrese un nombre válido.");
                    lblErrorNombre.setVisible(true);
                    error = true;
                } else {
                    lblErrorNombre.setVisible(false);
                }

                if (correoUsuario.isEmpty() || correoUsuario.equals("oscarGomez@gmail.com")) {
                    lblErrorCorreo.setText("Error: Ingrese un correo válido.");
                    lblErrorCorreo.setVisible(true);
                    error = true;
                } else {
                    lblErrorCorreo.setVisible(false);
                }

                if (direccionUsuario.isEmpty() || direccionUsuario.equals("Aconcagua 2445 c")) {
                    lblErrorDireccion.setText("Error: Ingrese una dirección válida.");
                    lblErrorDireccion.setVisible(true);
                    error = true;
                } else {
                    lblErrorDireccion.setVisible(false);
                }

                if (telefonoUsuario.isEmpty() || telefonoUsuario.equals("1137894221")) {
                    lblErrorTelefono.setText("Error: Ingrese un teléfono válido.");
                    lblErrorTelefono.setVisible(true);
                    error = true;
                } else {
                    lblErrorTelefono.setVisible(false);
                }

                if (!error) {
                    Cliente nuevoCliente = new Cliente(0, nombreUsuario, correoUsuario, direccionUsuario, telefonoUsuario);
                    clienteControlador.addClient(nuevoCliente);
                }
            }
        });
        btnRegistrar.setBounds(10, 485, 193, 50);
        contentPane.add(btnRegistrar);

        JButton btnIrAtras = new JButton("Ir Atrás");
        btnIrAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaOpciones pantallaOpciones = new PantallaOpciones();
                pantallaOpciones.setVisible(true);
                dispose();
            }
        });
        btnIrAtras.setBounds(444, 485, 193, 50);
        contentPane.add(btnIrAtras);
        
        JLabel lblRegistro = new JLabel("Registrarse");
        lblRegistro.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
        lblRegistro.setBounds(199, 10, 266, 86);
        contentPane.add(lblRegistro);
    }
}

