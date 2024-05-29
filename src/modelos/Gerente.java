package modelos;

import javax.swing.JOptionPane;

public class Gerente extends Usuario {
	private int salGer;

    public Gerente(int codUsu, String nomUsu, String mailUsu, String telUsu, int salGer) {
		super(codUsu, nomUsu, mailUsu, telUsu);
		this.salGer = salGer;
	}
    
    public int getSalGer() {
		return salGer;
	}

    public void setSalGer(int salGer) {
		this.salGer = salGer;
	}

    public int iniciarSesionGerente(String nombre, String mail) {
        if (nombre.length() >= 8 && mail.length() >= 8) {
            if (this.getMailUsu().equals(mail)) {
                if (this.getNomUsu().equals(nombre)) {
                    return 3;
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
    protected int iniciarSesionCliente(String nombre, String mail) {
        return 0;
    }
}
