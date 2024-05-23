package modelos;
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
    public boolean esEmpleado() {
        return true;
    }

    @Override
    public boolean esGerente() {
        return false;
    }
}
