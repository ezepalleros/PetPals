package modelos;
import java.time.LocalDateTime;

public class Servicio {
    private int codSer;
    private String nomSer;
    private LocalDateTime inicioSer;
    private LocalDateTime finSer;
    private boolean puedePerro;
    private boolean puedeGato;
    private boolean puedeAve;
    private boolean puedeRoedor;
    private boolean puedeReptil;
    private int precioPerro;
    private int precioGato;
    private int precioAve;
    private int precioRoedor;
    private int precioReptil;

    public Servicio(int codSer, String nomSer, LocalDateTime inicioSer, LocalDateTime finSer, 
    		boolean puedePerro, boolean puedeGato, boolean puedeAve, boolean puedeRoedor, 
    		boolean puedeReptil, int precioPerro, int precioGato, int precioAve, int precioRoedor, int precioReptil) {
        this.codSer = codSer;
        this.nomSer = nomSer;
        this.inicioSer = inicioSer;
        this.finSer = finSer;
        this.puedePerro = puedePerro;
        this.puedeGato = puedeGato;
        this.puedeAve = puedeAve;
        this.puedeRoedor = puedeRoedor;
        this.puedeReptil = puedeReptil;
        this.precioPerro = precioPerro;
        this.precioGato = precioGato;
        this.precioAve = precioAve;
        this.precioRoedor = precioRoedor;
        this.precioReptil = precioReptil;
    }

    public int getCodSer() {
        return codSer;
    }

    public void setCodSer(int codSer) {
        this.codSer = codSer;
    }

    public String getNomSer() {
        return nomSer;
    }

    public void setNomSer(String nomSer) {
        this.nomSer = nomSer;
    }

    public LocalDateTime getInicioSer() {
        return inicioSer;
    }

    public void setInicioSer(LocalDateTime inicioSer) {
        this.inicioSer = inicioSer;
    }

    public LocalDateTime getFinSer() {
        return finSer;
    }

    public void setFinSer(LocalDateTime finSer) {
        this.finSer = finSer;
    }

    public boolean isPuedePerro() {
        return puedePerro;
    }

    public void setPuedePerro(boolean puedePerro) {
        this.puedePerro = puedePerro;
    }

    public boolean isPuedeGato() {
        return puedeGato;
    }

    public void setPuedeGato(boolean puedeGato) {
        this.puedeGato = puedeGato;
    }

    public boolean isPuedeAve() {
        return puedeAve;
    }

    public void setPuedeAve(boolean puedeAve) {
        this.puedeAve = puedeAve;
    }

    public boolean isPuedeRoedor() {
        return puedeRoedor;
    }

    public void setPuedeRoedor(boolean puedeRoedor) {
        this.puedeRoedor = puedeRoedor;
    }

    public boolean isPuedeReptil() {
        return puedeReptil;
    }

    public void setPuedeReptil(boolean puedeReptil) {
        this.puedeReptil = puedeReptil;
    }

    public int getPrecioPerro() {
        return precioPerro;
    }

    public void setPrecioPerro(int precioPerro) {
        this.precioPerro = precioPerro;
    }

    public int getPrecioGato() {
        return precioGato;
    }

    public void setPrecioGato(int precioGato) {
        this.precioGato = precioGato;
    }

    public int getPrecioAve() {
        return precioAve;
    }

    public void setPrecioAve(int precioAve) {
        this.precioAve = precioAve;
    }

    public int getPrecioRoedor() {
        return precioRoedor;
    }

    public void setPrecioRoedor(int precioRoedor) {
        this.precioRoedor = precioRoedor;
    }

    public int getPrecioReptil() {
        return precioReptil;
    }

    public void setPrecioReptil(int precioReptil) {
        this.precioReptil = precioReptil;
    }
}