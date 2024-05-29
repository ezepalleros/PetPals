package modelos;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Empleado extends Usuario {
    private LocalDate antiEmp;
    private String detalleEmp;
    private int califEmp;

    public Empleado(int codUsu, String nomUsu, String mailUsu, String telUsu, LocalDate antiEmp, String detalleEmp, int califEmp) {
        super(codUsu, nomUsu, mailUsu, telUsu);
        this.antiEmp = antiEmp;
        this.detalleEmp = detalleEmp;
        this.califEmp = califEmp;
    }

    public LocalDate getAntiEmp() {
        return antiEmp;
    }

    public void setAntiEmp(LocalDate antiEmp) {
        this.antiEmp = antiEmp;
    }

    public String getDetalleEmp() {
        return detalleEmp;
    }

    public void setDetalleEmp(String detalleEmp) {
        this.detalleEmp = detalleEmp;
    }

    public int getCalifEmp() {
        return califEmp;
    }

    public void setCalifEmp(int califEmp) {
        this.califEmp = califEmp;
    }
    
    public boolean esCliente() {
        return false;
    }

    @Override
	public int iniciarSesionEmpleado(String nombre, String mail) {
        if (nombre.length() >= 8 && mail.length() >= 8) {
            if (this.getMailUsu().equals(mail)) {
                if (this.getNomUsu().equals(nombre)) {
                    return 2;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El nombre proporcionado no coincide.");
                    return 0;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: El correo electrónico proporcionado no coincide.");
                return 0;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: El nombre y el correo electrónico deben tener al menos 8 caracteres.");
            return 0;
        }
    }


    @Override
    protected int iniciarSesionGerente(String nombre, String mail) {
        return 0;
    }

    @Override
    protected int iniciarSesionCliente(String nombre, String mail) {
        return 0;
    }
}

