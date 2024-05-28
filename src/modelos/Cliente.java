package modelos;
import javax.swing.JOptionPane;

public class Cliente extends Usuario {
    private String dirCli;

    public Cliente(int codUsu, String nomUsu, String mailUsu, String dirCli, String telUsu) {
        super(codUsu, nomUsu, mailUsu, telUsu);
        this.dirCli = dirCli;
    }

    public String getDirCli() {
        return dirCli;
    }

    public void setDirCli(String dirCli) {
        this.dirCli = dirCli;
    }
    
    public int iniciarSesionCliente(String nombre, String mail) {
        if (nombre.length() >= 8 && mail.length() >= 8) {
            if (this.getMailUsu().equals(mail)) {
                if (this.getNomUsu().equals(nombre)) {
                    return 1;
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
    protected int iniciarSesionEmpleado(String nombre, String mail) {
        return 0;
    }

    @Override
    protected int iniciarSesionGerente(String nombre, String mail) {
        return 0;
    }

}