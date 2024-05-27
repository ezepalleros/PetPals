package modelos;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Mascota {
    private int codMas;
    private String nomMas;
    private String variMas;
    private String tipoMas;
    private int edadMas;
    private int vacuMas;
    private String caracterMas;
    private int dietMas;
    private int chipMas;
    private int adoptar;

    public Mascota(int codMas, String nomMas, String variMas, String tipoMas, int edadMas, int vacuMas,
                   String caracterMas, int dietMas, int chipMas, int adoptar) {
        this.codMas = codMas;
        this.nomMas = nomMas;
        this.variMas = variMas;
        this.tipoMas = tipoMas;
        this.edadMas = edadMas;
        this.vacuMas = vacuMas;
        this.caracterMas = caracterMas;
        this.dietMas = dietMas;
        this.chipMas = chipMas;
        this.adoptar = adoptar;
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

	public String getCaracterMas() {
        return caracterMas;
    }

    public void setCaracterMas(String caracterMas) {
        this.caracterMas = caracterMas;
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
	
		public static Mascota crearNuevaMascota(int codUsuActual, LinkedList<Mascota> mascotasRegistradas) {
			int nuevoCodigoMascota = 100000;
			for (Mascota mascota : mascotasRegistradas) {
				int codigoMascota = mascota.getCodMas();
				if (codigoMascota >= nuevoCodigoMascota) {
					nuevoCodigoMascota = codigoMascota + 1;
				}
			}
	        String nomMas = JOptionPane.showInputDialog(null, "Ingrese el nombre de la mascota:");
	        String variMas = JOptionPane.showInputDialog(null, "Ingrese la variante de la mascota (perro, gato, ave, roedor, reptil):");
	        String tipoMas = JOptionPane.showInputDialog(null, "Ingrese el tipo de mascota (labrador, persia, etc.):");
	        int edadMas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad de la mascota:"));
	        int vacuMas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿La mascota está vacunada? (1/0):"));
	        String caracterMas = JOptionPane.showInputDialog(null, "Ingrese el carácter de la mascota (Amistosa, Juguetona, Agresiva):");
	        int dietMas = Integer.parseInt(JOptionPane.showInputDialog(null, "la mascota está en dieta? (1/0):"));
	        int chipMas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿La mascota tiene chip? (true/false):"));
	
	        return new Mascota(nuevoCodigoMascota, nomMas, variMas, tipoMas, edadMas, vacuMas, caracterMas, dietMas, chipMas, codUsuActual);
	    }

		@Override
		public String toString() {
			return "Mascota " + codMas + ": \nNombre: " + nomMas + "\nEdad: " + edadMas + "\nTipo: " + tipoMas
					+ "\nVariedad: " + variMas + "\nVacunado: " + vacuMas + "\nCaracter: " + caracterMas + "\nDieta: "
					+ dietMas + "\nChip: " + chipMas + "\nAdoptar: " + adoptar + "\n-------------------\n";
		}
		
		


}
