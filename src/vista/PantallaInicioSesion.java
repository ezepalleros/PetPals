package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelos.Cliente;
import modelos.Empleado;
import modelos.Gerente;
import controladores.ClienteControlador;
import controladores.EmpleadoControlador;
import controladores.GerenteControlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PantallaInicioSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
    private JTextField inpCorreo;

    private ClienteControlador clienteControlador = new ClienteControlador();
    private EmpleadoControlador empleadoControlador = new EmpleadoControlador();
    private GerenteControlador gerenteControlador = new GerenteControlador();

     /**
     * Create the frame.
     */
    public PantallaInicioSesion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 545, 523);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNombre.setBounds(133, 102, 215, 28);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(133, 140, 266, 38);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblCorreo.setBounds(133, 198, 215, 28);
        contentPane.add(lblCorreo);

        inpCorreo = new JTextField();
        inpCorreo.setBounds(133, 236, 266, 38);
        contentPane.add(inpCorreo);

        JLabel lblError = new JLabel("");
        lblError.setForeground(new Color(255, 0, 0));
        lblError.setBounds(133, 284, 266, 28);
        contentPane.add(lblError);
        lblError.setVisible(false);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = inpNombre.getText();
                String correoUsuario = inpCorreo.getText();

                int tipoUsuario = 0;
                int codigoCliente = 0;

                if (correoUsuario.endsWith("@gmail.com")) {
                    List<Cliente> clientes = clienteControlador.getAllClients();
                    for (Cliente cliente : clientes) {
                        tipoUsuario = cliente.iniciarSesion(nombreUsuario, correoUsuario);
                        if (tipoUsuario != 0) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como cliente con:\nUsuario: "
                                    + cliente.getNomUsu() + "\nCódigo: " + cliente.getCodUsu());
                            codigoCliente = cliente.getCodUsu();
                            //PantallaCliente pantallaCliente = new PantallaCliente();
                            //pantallaCliente.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Cliente");
                            dispose();
                            return;
                        }
                    }
                } else if (correoUsuario.endsWith("@guarda.com")) {
                    List<Empleado> empleados = empleadoControlador.getAllEmployees();
                    for (Empleado empleado : empleados) {
                        tipoUsuario = empleado.iniciarSesion(nombreUsuario, correoUsuario);
                        if (tipoUsuario != 0) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como empleado con:\nUsuario: "
                                    + empleado.getNomUsu() + "\nCódigo: " + empleado.getCodUsu());
                            //PantallaEmpleado pantallaEmpleado = new PantallaEmpleado();
                            //pantallaEmpleado.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Empleado");
                            dispose();
                            return;
                        }
                    }
                } else if (correoUsuario.endsWith("@guarda2.com")) {
                    List<Gerente> gerentes = gerenteControlador.getAllManagers();
                    for (Gerente gerente : gerentes) {
                        tipoUsuario = gerente.iniciarSesion(nombreUsuario, correoUsuario);
                        if (tipoUsuario != 0) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como gerente con:\nUsuario: "
                                    + gerente.getNomUsu() + "\nCódigo: " + gerente.getCodUsu());
                            //PantallaGerente pantallaGerente = new PantallaGerente();
                            //pantallaGerente.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Gerente");
                            dispose();
                            return;
                        }
                    }
                }

                if (tipoUsuario == 0) {
                    lblError.setText("Error: Usuario o correo incorrecto");
                    lblError.setVisible(true);
                }
            }
        });
        btnIngresar.setBounds(133, 322, 193, 50);
        contentPane.add(btnIngresar);

        JButton btnIrAtras = new JButton("Ir Atrás");
        btnIrAtras.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaOpciones pantallaOpciones = new PantallaOpciones();
                pantallaOpciones.setVisible(true);
                dispose();
            }
        });
        btnIrAtras.setBounds(328, 426, 193, 50);
        contentPane.add(btnIrAtras);
        
        JLabel lblInicioSesion = new JLabel("Iniciar sesión");
        lblInicioSesion.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
        lblInicioSesion.setBounds(133, 0, 266, 86);
        contentPane.add(lblInicioSesion);
    }
}
