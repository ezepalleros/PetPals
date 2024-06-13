package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelos.Servicio;
import controladores.ServicioControlador;

public class PantallaAgrSer extends JFrame {

    private JPanel contentPane;
    private ServicioControlador controlador;

    public PantallaAgrSer() {
        controlador = new ServicioControlador();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Aquí se pueden agregar los componentes necesarios para agregar un nuevo servicio.
        // Al hacer clic en el botón de guardar, se debe agregar el servicio usando controlador.addService(nuevoServicio)
    }
}
