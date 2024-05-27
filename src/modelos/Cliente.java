package modelos;
import java.util.LinkedList;

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
    
    public boolean esCliente() {
        return true;
    }

    public boolean esEmpleado() {
        return false;
    }

    public boolean esGerente() {
        return false;
    }

	public boolean IniciarSesion(String string, String string2) {
		// TODO Auto-generated method stub
		return false;
	}
}