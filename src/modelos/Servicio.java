package modelos;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Servicio {
    private int codSer;
    private String nomSer;
    private LocalDate diaSer;
    private LocalDateTime inicioSer;
    private LocalDateTime finSer;
    private int puedePerro;
    private int puedeGato;
    private int puedeAve;
    private int puedeRoedor;
    private int puedeReptil;
    private int precioPerro;
    private int precioGato;
    private int precioAve;
    private int precioRoedor;
    private int precioReptil;

    public Servicio(int codSer, String nomSer, LocalDate diaSer, LocalDateTime inicioSer, LocalDateTime finSer, 
    		int puedePerro, int puedeGato, int puedeAve, int puedeRoedor, 
    		int puedeReptil, int precioPerro, int precioGato, int precioAve, int precioRoedor, int precioReptil) {
        this.codSer = codSer;
        this.nomSer = nomSer;
        this.diaSer = diaSer;
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
    
    public LocalDate getDiaSer() {
		return diaSer;
	}

	public void setDiaSer(LocalDate diaSer) {
		this.diaSer = diaSer;
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

    public int getPuedePerro() {
		return puedePerro;
	}

	public void setPuedePerro(int puedePerro) {
		this.puedePerro = puedePerro;
	}

	public int getPuedeGato() {
		return puedeGato;
	}

	public void setPuedeGato(int puedeGato) {
		this.puedeGato = puedeGato;
	}

	public int getPuedeAve() {
		return puedeAve;
	}

	public void setPuedeAve(int puedeAve) {
		this.puedeAve = puedeAve;
	}

	public int getPuedeRoedor() {
		return puedeRoedor;
	}

	public void setPuedeRoedor(int puedeRoedor) {
		this.puedeRoedor = puedeRoedor;
	}

	public int getPuedeReptil() {
		return puedeReptil;
	}

	public void setPuedeReptil(int puedeReptil) {
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