package controladores;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.MascotaRepository;
import modelos.Mascota;

public class MascotaControlador implements MascotaRepository {
	private final Connection connection;

	public MascotaControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<Mascota> getAllPets() {
		List<Mascota> mascotas = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM mascota ");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Mascota mascota = new Mascota(resultSet.getInt("codMas"), resultSet.getString("nomMas"),
						resultSet.getString("variMas"), resultSet.getString("tipoMas"), resultSet.getInt("edadMas"),
						resultSet.getInt("vacuMas"), resultSet.getString("caracMas"), resultSet.getInt("dietMas"),
						resultSet.getInt("chipMas"), resultSet.getInt("adoptar"), resultSet.getInt("dueMas"));
				mascotas.add(mascota);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mascotas;
	}

	@Override
	public Mascota getPetById(int codCli) {
		Mascota mascotas = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM mascota WHERE codMas = ?");
			statement.setInt(1, codCli);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				mascotas = new Mascota(resultSet.getInt("codMas"), resultSet.getString("nomMas"),
						resultSet.getString("variMas"), resultSet.getString("tipoMas"), resultSet.getInt("edadMas"),
						resultSet.getInt("vacuMas"), resultSet.getString("caracMas"), resultSet.getInt("dietMas"),
						resultSet.getInt("chipMas"), resultSet.getInt("adoptar"), resultSet.getInt("dueMas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mascotas;
	}
	
	@Override
	public Mascota getPetsByClient(int id) {
		Mascota mascotas = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM mascota WHERE dueMas = ?");
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				mascotas = new Mascota(resultSet.getInt("codMas"), resultSet.getString("nomMas"),
						resultSet.getString("variMas"), resultSet.getString("tipoMas"), resultSet.getInt("edadMas"),
						resultSet.getInt("vacuMas"), resultSet.getString("caracMas"), resultSet.getInt("dietMas"),
						resultSet.getInt("chipMas"), resultSet.getInt("adoptar"), resultSet.getInt("dueMas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mascotas;
	}

	@Override
	public void addPet(Mascota mascota) {
		if (mascota.getNomMas().length() < 3 || mascota.getNomMas().matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null,
					"Error: El nombre de la mascota debe tener al menos 3 caracteres y no debe contener números.");
			return;
		}

		String variedadMascota = mascota.getVariMas().toLowerCase();
		if (!variedadMascota.equalsIgnoreCase("perro") && !variedadMascota.equalsIgnoreCase("gato")
				&& !variedadMascota.equalsIgnoreCase("ave") && !variedadMascota.equalsIgnoreCase("roedor")
				&& !variedadMascota.equalsIgnoreCase("reptil")) {
			JOptionPane.showMessageDialog(null, "Error: La variedad de la mascota no es válida.");
			return;
		}

		if (mascota.getTipoMas().length() < 3 || mascota.getTipoMas().matches(".*\\d.*")) {
			JOptionPane.showMessageDialog(null,
					"Error: El tipo de la mascota debe tener al menos 3 caracteres y no debe contener números.");
			return;
		}

		if (mascota.getEdadMas() <= 0) {
			JOptionPane.showMessageDialog(null, "Error: La edad de la mascota debe ser mayor que 0.");
			return;
		}

		if (mascota.getVacuMas() != 0 && mascota.getVacuMas() != 1) {
			JOptionPane.showMessageDialog(null, "Error: El valor de vacunación de la mascota debe ser 0 o 1.");
			return;
		}

		String caracterMascota = mascota.getCaracMas().toLowerCase();
		if (!caracterMascota.equalsIgnoreCase("amistoso") && !caracterMascota.equalsIgnoreCase("jugueton")
				&& !caracterMascota.equalsIgnoreCase("agresivo") && !caracterMascota.equalsIgnoreCase("amistosa")
				&& !caracterMascota.equalsIgnoreCase("juguetona") && !caracterMascota.equalsIgnoreCase("agresiva")) {
			JOptionPane.showMessageDialog(null, "Error: El carácter de la mascota no es válido.");
			return;
		}

		if (mascota.getDietMas() != 0 && mascota.getDietMas() != 1) {
			JOptionPane.showMessageDialog(null, "Error: El valor de dieta de la mascota debe ser 0 o 1.");
			return;
		}

		if (mascota.getChipMas() != 0 && mascota.getChipMas() != 1) {
			JOptionPane.showMessageDialog(null,
					"Error: El valor del chip de identificación de la mascota debe ser 0 o 1.");
			return;
		}

		int codigoCliente = mascota.getDueMas();

		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO mascota (nomMas, variMas, tipoMas, edadMas, vacuMas, caracMas, dietMas, chipMas, adoptar, dueMas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, mascota.getNomMas());
			statement.setString(2, mascota.getVariMas());
			statement.setString(3, mascota.getTipoMas());
			statement.setInt(4, mascota.getEdadMas());
			statement.setInt(5, mascota.getVacuMas());
			statement.setString(6, mascota.getCaracMas());
			statement.setInt(7, mascota.getDietMas());
			statement.setInt(8, mascota.getChipMas());
			statement.setInt(9, mascota.getAdoptar());
			statement.setInt(10, codigoCliente);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Mascota insertada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addPet2(Mascota mascota) {
		
		int codigoCliente = mascota.getDueMas();
		
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO mascota (nomMas, variMas, tipoMas, edadMas, vacuMas, caracMas, dietMas, chipMas, adoptar, dueMas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, mascota.getNomMas());
			statement.setString(2, mascota.getVariMas());
			statement.setString(3, mascota.getTipoMas());
			statement.setInt(4, mascota.getEdadMas());
			statement.setInt(5, mascota.getVacuMas());
			statement.setString(6, mascota.getCaracMas());
			statement.setInt(7, mascota.getDietMas());
			statement.setInt(8, mascota.getChipMas());
			statement.setInt(9, mascota.getAdoptar());
			statement.setInt(10, codigoCliente);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Mascota insertada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePet(Mascota mascota) {
	    try {
	        PreparedStatement statement = connection.prepareStatement(
	            "UPDATE mascota SET nomMas = ?, variMas = ?, tipoMas = ?, edadMas = ?, vacuMas = ?, " +
	            "caracMas = ?, dietMas = ?, chipMas = ?, adoptar = ?, dueMas = ? WHERE codMas = ?");
	        statement.setString(1, mascota.getNomMas());
	        statement.setString(2, mascota.getVariMas());
	        statement.setString(3, mascota.getTipoMas());
	        statement.setInt(4, mascota.getEdadMas());
	        statement.setInt(5, mascota.getVacuMas());
	        statement.setString(6, mascota.getCaracMas());
	        statement.setInt(7, mascota.getDietMas());
	        statement.setInt(8, mascota.getChipMas());
	        statement.setInt(9, mascota.getAdoptar());
	        statement.setInt(10, mascota.getDueMas());
	        statement.setInt(11, mascota.getCodMas());

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Mascota actualizada exitosamente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deletePet(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM mascota WHERE codMas = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Mascota eliminada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Mascota> getMascotasParaAdopcion() {
        List<Mascota> mascotas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM mascota WHERE adoptar = 1");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mascota mascota = new Mascota(
                        resultSet.getInt("codMas"),
                        resultSet.getString("nomMas"),
                        resultSet.getString("variMas"),
                        resultSet.getString("tipoMas"),
                        resultSet.getInt("edadMas"),
                        resultSet.getInt("vacuMas"),
                        resultSet.getString("caracMas"),
                        resultSet.getInt("dietMas"),
                        resultSet.getInt("chipMas"),
                        resultSet.getInt("adoptar"),
                        resultSet.getInt("dueMas")
                );
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
}
