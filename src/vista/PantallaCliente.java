package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelos.Mascota;
import controladores.MascotaControlador;



public class PantallaCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    /**
     * Create the frame.
     */
    public PantallaCliente(int codigoCliente) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 545, 523);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Menú de Cliente");
        lblTitulo.setBounds(0, 10, 529, 86);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
        contentPane.add(lblTitulo);

        JButton btnModificarMascotas = new JButton("Ver mis Mascotas");
        btnModificarMascotas.setBounds(108, 107, 323, 58);
        btnModificarMascotas.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnModificarMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaVerMas pantallaVerMas = new PantallaVerMas(codigoCliente);
                pantallaVerMas.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnModificarMascotas);

        JButton btnSolicitarServicio = new JButton("Solicitar Servicio");
        btnSolicitarServicio.setBounds(108, 180, 324, 58);
        btnSolicitarServicio.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnSolicitarServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MascotaControlador mascotaControlador = new MascotaControlador();
                List<Mascota> mascotas = mascotaControlador.getPetsByClient(codigoCliente);
                PantallaSolSer pantallaSolSer = new PantallaSolSer(mascotas, codigoCliente);
                pantallaSolSer.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnSolicitarServicio);



        JButton btnRealizarAdopcion = new JButton("Realizar Adopción");
        btnRealizarAdopcion.setBounds(108, 260, 323, 58);
        btnRealizarAdopcion.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnRealizarAdopcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaReaAdo pantallaReaAdo = new PantallaReaAdo(codigoCliente);
                pantallaReaAdo.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRealizarAdopcion);

        JButton btnIrAtras = new JButton("Ir atrás");
        btnIrAtras.setBounds(108, 340, 323, 58);
        btnIrAtras.setFont(new Font("Stencil", Font.PLAIN, 19));
        btnIrAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaOpciones pantallaOpciones = new PantallaOpciones();
                pantallaOpciones.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnIrAtras);
    }
}
