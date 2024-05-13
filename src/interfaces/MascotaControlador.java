
package interfaces;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controladores.DatabaseConnection;
import modelos.Mascota;

public class MascotaControlador implements MascotaRepository {
    private final Connection connection;

    public MascotaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Mascota> getAllUsers() {
        List<Mascota> mascotas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Mascota mascota = new Mascota(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("variety"), resultSet.getString("type"), resultSet.getInt("age"), resultSet.getBoolean("vacuna"), resultSet.getString("carac"), resultSet.getString("diet"), resultSet.getBoolean("chip"), resultSet.getInt("adopt"));
            	mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }

    @Override
    public Mascota getUserById(int id) {
    	Mascota mascotas = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	mascotas = new Mascota(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("variety"), resultSet.getString("type"), resultSet.getInt("age"), resultSet.getBoolean("vacuna"), resultSet.getString("carac"), resultSet.getString("diet"), resultSet.getBoolean("chip"), resultSet.getInt("adopt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
    
	@Override
    public void addUser(Mascota mascota) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, variety, type, age, vacuna, carac, diet, chip, adopt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, mascota.getNomMas());
            statement.setString(2, mascota.getVariMas());
            statement.setString(3, mascota.getTipoMas());
            statement.setInt(4, mascota.getEdadMas());
            statement.setBoolean(5, mascota.isVacuMas());
            statement.setString(6, mascota.getCaracterMas());
            statement.setString(7, mascota.getDietMas());
            statement.setBoolean(8, mascota.isChipMas());
            statement.setInt(9, mascota.getAdoptar());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateUser(Mascota mascota) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, variety = ?, type = ?, age = ?, vacuna = ?, carac = ?, diet = ?, chip = ?, adopt = ? WHERE id = ?");
            statement.setString(1, mascota.getNomMas());
            statement.setString(2, mascota.getVariMas());
            statement.setString(3, mascota.getTipoMas());
            statement.setInt(4, mascota.getEdadMas());
            statement.setBoolean(5, mascota.isVacuMas());
            statement.setString(6, mascota.getCaracterMas());
            statement.setString(7, mascota.getDietMas());
            statement.setBoolean(8, mascota.isChipMas());
            statement.setInt(9, mascota.getAdoptar());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
