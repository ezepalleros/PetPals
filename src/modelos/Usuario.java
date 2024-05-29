package modelos;

public abstract class Usuario {
    private int codUsu;
    private String nomUsu;
    private String mailUsu;
    private String telUsu;

    public Usuario(int codUsu, String nomUsu, String mailUsu, String telUsu) {
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

    public String getTelUsu() {
        return telUsu;
    }

    public void setTelUsu(String telUsu) {
        this.telUsu = telUsu;
    }
    
    public int iniciarSesion(String nombre, String mail) {
        if (mail.endsWith("@guarda.com")) {
            return iniciarSesionEmpleado(nombre, mail);
        } else if (mail.endsWith("@guarda2.com")) {
            return iniciarSesionGerente(nombre, mail);
        } else {
            return iniciarSesionCliente(nombre, mail);
        }
    }

    protected abstract int iniciarSesionEmpleado(String nombre, String mail);

    protected abstract int iniciarSesionGerente(String nombre, String mail);

    protected abstract int iniciarSesionCliente(String nombre, String mail);


}