package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controladores.MascotaControlador;
import modelos.Mascota;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaEdiMas extends JFrame {

    private JPanel contentPane;
    private Mascota mascota;
    private MascotaControlador controlador;

    /**
     * Create the frame.
     */
    public PantallaEdiMas(Mascota mascota) {
        this.mascota = mascota;
        controlador = new MascotaControlador();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNomMas = new JLabel("Nombre de la mascota:");
        lblNomMas.setBounds(5, 7, 283, 52);
        contentPane.add(lblNomMas);

        JTextField txtNomMas = new JTextField(mascota.getNomMas());
        txtNomMas.setBounds(298, 7, 283, 52);
        contentPane.add(txtNomMas);

        JLabel lblVariMas = new JLabel("Variante de la mascota:");
        lblVariMas.setBounds(5, 69, 283, 52);
        contentPane.add(lblVariMas);

        JTextField txtVariMas = new JTextField(mascota.getVariMas());
        txtVariMas.setBounds(298, 69, 283, 52);
        contentPane.add(txtVariMas);

        JLabel lblTipoMas = new JLabel("Tipo de mascota:");
        lblTipoMas.setBounds(5, 131, 283, 52);
        contentPane.add(lblTipoMas);

        JTextField txtTipoMas = new JTextField(mascota.getTipoMas());
        txtTipoMas.setBounds(298, 131, 283, 52);
        contentPane.add(txtTipoMas);

        JLabel lblEdadMas = new JLabel("Edad de la mascota:");
        lblEdadMas.setBounds(5, 193, 283, 52);
        contentPane.add(lblEdadMas);

        JTextField txtEdadMas = new JTextField(String.valueOf(mascota.getEdadMas()));
        txtEdadMas.setBounds(298, 193, 283, 52);
        contentPane.add(txtEdadMas);

        JLabel lblVacuMas = new JLabel("¿Vacunada?:");
        lblVacuMas.setBounds(5, 255, 283, 52);
        contentPane.add(lblVacuMas);

        JCheckBox chkVacuMas = new JCheckBox();
        chkVacuMas.setBounds(298, 255, 283, 52);
        chkVacuMas.setSelected(mascota.getVacuMas() == 1);
        contentPane.add(chkVacuMas);

        JLabel lblCaracMas = new JLabel("Carácter (Amistoso/a, Agresivo/a, Jugueton):");
        lblCaracMas.setBounds(5, 317, 283, 52);
        contentPane.add(lblCaracMas);

        JTextField txtCaracMas = new JTextField(mascota.getCaracMas());
        txtCaracMas.setBounds(298, 317, 283, 52);
        contentPane.add(txtCaracMas);

        JLabel lblDietMas = new JLabel("¿Dieta especial?:");
        lblDietMas.setBounds(5, 379, 283, 52);
        contentPane.add(lblDietMas);

        JCheckBox chkDietMas = new JCheckBox();
        chkDietMas.setBounds(298, 379, 283, 52);
        chkDietMas.setSelected(mascota.getDietMas() == 1);
        contentPane.add(chkDietMas);

        JLabel lblChipMas = new JLabel("¿Con chip?:");
        lblChipMas.setBounds(5, 441, 283, 52);
        contentPane.add(lblChipMas);

        JCheckBox chkChipMas = new JCheckBox();
        chkChipMas.setBounds(298, 441, 283, 52);
        chkChipMas.setSelected(mascota.getChipMas() == 1);
        contentPane.add(chkChipMas);

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBounds(5, 503, 283, 52);
        contentPane.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Restablecer colores de etiquetas
                lblNomMas.setForeground(Color.BLACK);
                lblVariMas.setForeground(Color.BLACK);
                lblTipoMas.setForeground(Color.BLACK);
                lblEdadMas.setForeground(Color.BLACK);
                lblVacuMas.setForeground(Color.BLACK);
                lblCaracMas.setForeground(Color.BLACK);
                lblDietMas.setForeground(Color.BLACK);
                lblChipMas.setForeground(Color.BLACK);

                boolean error = false;

                // Validaciones de nombre de la mascota
                String nomMas = txtNomMas.getText();
                if (nomMas.length() < 3 || contieneNumeros(nomMas)) {
                    lblNomMas.setForeground(Color.RED);
                    error = true;
                }

                // Validaciones de variante de la mascota
                String variMas = txtVariMas.getText();
                if (!variMas.equals("Perro") && !variMas.equals("Gato") && !variMas.equals("Ave")
                        && !variMas.equals("Roedor") && !variMas.equals("Reptil")) {
                    lblVariMas.setForeground(Color.RED);
                    error = true;
                }

                // Validaciones de tipo de mascota
                String tipoMas = txtTipoMas.getText();
                if (tipoMas.length() < 3 || contieneNumeros(tipoMas)) {
                    lblTipoMas.setForeground(Color.RED);
                    error = true;
                }

                // Validaciones de edad de la mascota
                try {
                    int edadMas = Integer.parseInt(txtEdadMas.getText());
                    if (edadMas <= 0 || edadMas >= 100) {
                        lblEdadMas.setForeground(Color.RED);
                        error = true;
                    }
                } catch (NumberFormatException ex) {
                    lblEdadMas.setForeground(Color.RED);
                    error = true;
                }

                // Validaciones de vacunación
                int vacuMas = chkVacuMas.isSelected() ? 1 : 0;

                // Validaciones de caracter
                String caracMas = txtCaracMas.getText().toLowerCase();
                if (!caracMas.equals("amistoso") && !caracMas.equals("amistosa") && !caracMas.equals("jugueton")
                        && !caracMas.equals("agresivo") && !caracMas.equals("agresiva")) {
                    lblCaracMas.setForeground(Color.RED);
                    error = true;
                }

                int dietMas = chkDietMas.isSelected() ? 1 : 0;
                int chipMas = chkChipMas.isSelected() ? 1 : 0;

                if (!error) {
                    Mascota mascotaActualizada = new Mascota(mascota.getCodMas(), nomMas, variMas, tipoMas,
                            Integer.parseInt(txtEdadMas.getText()), vacuMas, caracMas, dietMas, chipMas, 0, mascota.getDueMas());

                    controlador.updatePet(mascotaActualizada);
                    dispose();
                    PantallaVerMas pantallaVerMas = new PantallaVerMas(mascota.getDueMas());
                    pantallaVerMas.setVisible(true);
                }
            }
        });
    }

    private boolean contieneNumeros(String texto) {
        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
