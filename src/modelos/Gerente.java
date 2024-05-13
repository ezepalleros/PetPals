package modelos;
import java.util.LinkedList;

public class Gerente extends Usuario {
    private LinkedList<Mascota> mascotasRefugio;

    public Gerente(int codUsu, String nomUsu, String mailUsu, double telUsu) {
        super(codUsu, nomUsu, mailUsu, telUsu);
        this.mascotasRefugio = new LinkedList<>();
    }

    public LinkedList<Mascota> getMascotasRefugio() {
        return mascotasRefugio;
    }

    public void setMascotasRefugio(LinkedList<Mascota> mascotasRefugio) {
        this.mascotasRefugio = mascotasRefugio;
    }
    
    public boolean esCliente() {
        return false;
    }

    @Override
    public boolean esEmpleado() {
        return false;
    }

    @Override
    public boolean esGerente() {
        return true;
    }
}

