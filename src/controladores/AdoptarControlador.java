package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import interfaces.AdoptarRepository;
import modelos.Adoptar;

public class AdoptarControlador implements AdoptarRepository {
	private final Connection connection;

	public AdoptarControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<Adoptar> getAllAdoptions() {
		List<Adoptar> adopciones = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM adoptar ");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Adoptar adoptar = new Adoptar(resultSet.getInt("codAdo"), resultSet.getInt("codMas"),
						resultSet.getString("variMas"), resultSet.getInt("edadMas"), resultSet.getInt("codCli"), resultSet.getString("numCli"));
				adopciones.add(adoptar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adopciones;
	}

	@Override
	public Adoptar getAdoptionById(int id) {
		Adoptar adopciones = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM adoptar WHERE codAdo = ?");
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				adopciones = new Adoptar(resultSet.getInt("codAdo"), resultSet.getInt("codMas"),
						resultSet.getString("variMas"), resultSet.getInt("edadMas"), resultSet.getInt("codCli"), resultSet.getString("numCli"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adopciones;
	}

	@Override
	public void addAdoption(Adoptar adoptar) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO adoptar (codMas, variMas, edadMas, codCli, numCli) VALUES (?, ?, ?, ?, ?)");
			statement.setInt(1, adoptar.getCodMas());
			statement.setString(2, adoptar.getVariMas());
			statement.setInt(3, adoptar.getEdadMas());
			statement.setInt(4, adoptar.getCodCli());
			statement.setString(5, adoptar.getNumCli());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Adopción insertada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdoption(Adoptar adoptar) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE adoptar SET codMas = ?, variMas = ?, edadMas = ?, codCli = ?, numCli = ? WHERE codAdo = ?");
			statement.setInt(1, adoptar.getCodMas());
			statement.setString(2, adoptar.getVariMas());
			statement.setInt(3, adoptar.getEdadMas());
			statement.setInt(4, adoptar.getCodCli());
			statement.setString(5, adoptar.getNumCli());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Adopción actualizada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAdoption(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM adoptar WHERE codAdo = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Adopción eliminada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
