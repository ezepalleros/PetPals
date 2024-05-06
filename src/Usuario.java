import java.util.List;

public abstract class Usuario {
    private int codUsu;
    private String nomUsu;
    private String mailUsu;
    private double telUsu;

    public Usuario(int codUsu, String nomUsu, String mailUsu, double telUsu) {
        this.codUsu = codUsu;
        this.nomUsu = nomUsu;
        this.mailUsu = mailUsu;
        this.telUsu = telUsu;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getMailUsu() {
        return mailUsu;
    }

    public void setMailUsu(String mailUsu) {
        this.mailUsu = mailUsu;
    }

    public double getTelUsu() {
        return telUsu;
    }

    public void setTelUsu(double telUsu) {
        this.telUsu = telUsu;
    }
    
    public abstract boolean esCliente();
    public abstract boolean esEmpleado();
    public abstract boolean esGerente();
    
    public boolean validarCredenciales(String correo, String codigo, List<Usuario> cuentasCreadas) {
        for (Usuario cuenta : cuentasCreadas) {
            if (this.mailUsu.equals(correo) && String.valueOf(this.codUsu).equals(codigo)) {
                return true;
            }
        }
        return false;
    }

}