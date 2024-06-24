package modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Solicitud {
    private int codSol;
    private int codSer;
    private LocalDate diaSer;
    private LocalDateTime horaIniSer;
    private LocalDateTime horaFinSer;
    private int codMas;
    private String tipoMas;
    private String caracMas;
    
	public Solicitud(int codSol, int codSer, LocalDate diaSer, LocalDateTime horaIniSer, LocalDateTime horaFinSer,
			int codMas, String tipoMas, String caracMas) {
		super();
		this.codSol = codSol;
		this.codSer = codSer;
		this.diaSer = diaSer;
		this.horaIniSer = horaIniSer;
		this.horaFinSer = horaFinSer;
		this.codMas = codMas;
		this.tipoMas = tipoMas;
		this.caracMas = caracMas;
	}

	public int getCodSol() {
		return codSol;
	}

	public void setCodSol(int codSol) {
		this.codSol = codSol;
	}

	public int getCodSer() {
		return codSer;
	}

	public void setCodSer(int codSer) {
		this.codSer = codSer;
	}

	public LocalDate getDiaSer() {
		return diaSer;
	}

	public void setDiaSer(LocalDate diaSer) {
		this.diaSer = diaSer;
	}

	public LocalDateTime getHoraIniSer() {
		return horaIniSer;
	}

	public void setHoraIniSer(LocalDateTime horaIniSer) {
		this.horaIniSer = horaIniSer;
	}

	public LocalDateTime getHoraFinSer() {
		return horaFinSer;
	}

	public void setHoraFinSer(LocalDateTime horaFinSer) {
		this.horaFinSer = horaFinSer;
	}

	public int getCodMas() {
		return codMas;
	}

	public void setCodMas(int codMas) {
		this.codMas = codMas;
	}

	public String getTipoMas() {
		return tipoMas;
	}

	public void setTipoMas(String tipoMas) {
		this.tipoMas = tipoMas;
	}

	public String getCaracMas() {
		return caracMas;
	}

	public void setCaracMas(String caracMas) {
		this.caracMas = caracMas;
	}
    
    
	

}
