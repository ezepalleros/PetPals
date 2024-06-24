package modelos;

public class Adoptar {
    private int codAdo;
    private int codMas;
    private String variMas;
    private int edadMas;
    private int codCli;
    
	public Adoptar(int codAdo, int codMas, String variMas, int edadMas, int codCli) {
		super();
		this.codAdo = codAdo;
		this.codMas = codMas;
		this.variMas = variMas;
		this.edadMas = edadMas;
		this.codCli = codCli;
	}

	public int getCodAdo() {
		return codAdo;
	}

	public void setCodAdo(int codAdo) {
		this.codAdo = codAdo;
	}

	public int getCodMas() {
		return codMas;
	}

	public void setCodMas(int codMas) {
		this.codMas = codMas;
	}

	public String getVariMas() {
		return variMas;
	}

	public void setVariMas(String variMas) {
		this.variMas = variMas;
	}

	public int getEdadMas() {
		return edadMas;
	}

	public void setEdadMas(int edadMas) {
		this.edadMas = edadMas;
	}

	public int getCodCli() {
		return codCli;
	}

	public void setCodCli(int codCli) {
		this.codCli = codCli;
	}
}
