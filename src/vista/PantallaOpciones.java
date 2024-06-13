package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PantallaOpciones extends JFrame {
    
    private LocalDate fechaActual = LocalDate.now();
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblDia;
    private PantallaEdiSer pantallaEdiSer; // Referencia a la instancia de PantallaEdiSer

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaOpciones frame = new PantallaOpciones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PantallaOpciones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 545, 523);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblPetPals = new JLabel("Petpals");
        lblPetPals.setHorizontalAlignment(SwingConstants.CENTER);
        lblPetPals.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
        lblPetPals.setBounds(0, 10, 529, 86);
        contentPane.add(lblPetPals);
        
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaInicioSesion pantallaInicioSesion = new PantallaInicioSesion();
                pantallaInicioSesion.setVisible(true);
                dispose();
            }
        });
        btnIniciarSesion.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnIniciarSesion.setBounds(163, 148, 190, 58);
        contentPane.add(btnIniciarSesion);
        
        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                PantallaRegistro PantallaRegistro = new PantallaRegistro();
                PantallaRegistro.setVisible(true);
                dispose();
            }
        });
        btnRegistro.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnRegistro.setBounds(163, 228, 190, 58);
        contentPane.add(btnRegistro);
        
        JButton btnPasarDia = new JButton("Pasar el dia");
        btnPasarDia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fechaActual = fechaActual.plusDays(1);
                lblDia.setText("El dia de hoy es: " + fechaActual);
                
                actualizarFechaActualEnPantallaEdiSer(fechaActual);
            }
        });
        btnPasarDia.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnPasarDia.setBounds(163, 305, 190, 58);
        contentPane.add(btnPasarDia);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnSalir.setBounds(163, 381, 190, 58);
        contentPane.add(btnSalir);
        
        lblDia = new JLabel("El dia de hoy es: " + fechaActual);
        lblDia.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
        lblDia.setBounds(0, 91, 270, 58);
        contentPane.add(lblDia);
    }
    
    // Método para actualizar la fecha actual en PantallaEdiSer
    private void actualizarFechaActualEnPantallaEdiSer(LocalDate fechaActual) {
        if (pantallaEdiSer != null) {
            pantallaEdiSer.actualizarFechaActual(fechaActual);
        }
    }

    // Setter para la instancia de PantallaEdiSer
    public void setPantallaEdiSer(PantallaEdiSer pantallaEdiSer) {
        this.pantallaEdiSer = pantallaEdiSer;
    }
}
