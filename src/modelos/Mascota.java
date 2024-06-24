package modelos;

public class Mascota {
    private int codMas;
    private String nomMas;
    private String variMas;
    private String tipoMas;
    private int edadMas;
    private int vacuMas;
    private String caracMas;
    private int dietMas;
    private int chipMas;
    private int adoptar;
    private int dueMas;

    public Mascota(int codMas, String nomMas, String variMas, String tipoMas, int edadMas, int vacuMas,
                   String caracMas, int dietMas, int chipMas, int adoptar, int dueMas) {
        this.codMas = codMas;
        this.nomMas = nomMas;
        this.variMas = variMas;
        this.tipoMas = tipoMas;
        this.edadMas = edadMas;
        this.vacuMas = vacuMas;
        this.caracMas = caracMas;
        this.dietMas = dietMas;
        this.chipMas = chipMas;
        this.adoptar = adoptar;
        this.dueMas = dueMas;
    }

    public int getCodMas() {
        return codMas;
    }

    public void setCodMas(int codMas) {
        this.codMas = codMas;
    }

    public String getNomMas() {
        return nomMas;
    }

    public void setNomMas(String nomMas) {
        this.nomMas = nomMas;
    }

    public String getVariMas() {
        return variMas;
    }

    public void setVariMas(String variMas) {
        this.variMas = variMas;
    }

    public String getTipoMas() {
        return tipoMas;
    }

    public void setTipoMas(String tipoMas) {
        this.tipoMas = tipoMas;
    }

    public int getEdadMas() {
        return edadMas;
    }

    public void setEdadMas(int edadMas) {
        this.edadMas = edadMas;
    }

    public int getVacuMas() {
		return vacuMas;
	}

	public void setVacuMas(int vacuMas) {
		this.vacuMas = vacuMas;
	}

	public String getCaracMas() {
        return caracMas;
    }

    public void setCaracterMas(String caracterMas) {
        this.caracMas = caracterMas;
    }

    public int getDietMas() {
		return dietMas;
	}

	public void setDietMas(int dietMas) {
		this.dietMas = dietMas;
	}

	public int getChipMas() {
		return chipMas;
	}

	public void setChipMas(int chipMas) {
		this.chipMas = chipMas;
	}

	public int getAdoptar() {
		return adoptar;
	}

	public void setAdoptar(int adoptar) {
		this.adoptar = adoptar;
	}
	
	public int getDueMas() {
		return dueMas;
	}

	public void setDueMas(int dueMas) {
		this.dueMas = dueMas;
	}

}
