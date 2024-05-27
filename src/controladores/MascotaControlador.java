
package controladores;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.MascotaRepository;
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
            	Mascota mascota = new Mascota(resultSet.getInt("codMas"), resultSet.getString("nomMas"), resultSet.getString("variMas"), resultSet.getString("tipoMas"), resultSet.getInt("edadMas"), resultSet.getInt("vacuMas"), resultSet.getString("caracterMas"), resultSet.getInt("dietMas"), resultSet.getInt("chipMas"), resultSet.getInt("adoptar"));
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
            	mascotas = new Mascota(resultSet.getInt("codMas"), resultSet.getString("nomMas"), resultSet.getString("variMas"), resultSet.getString("tipoMas"), resultSet.getInt("edadMas"), resultSet.getInt("vacuMas"), resultSet.getString("caracterMas"), resultSet.getInt("dietMas"), resultSet.getInt("chipMas"), resultSet.getInt("adoptar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
    
	@Override
    public void addUser(Mascota mascota) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (nomMas, variMas, tipoMas, edadMas, vacuMas, caracterMas, dietMas, chipMas, adoptar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, mascota.getNomMas());
            statement.setString(2, mascota.getVariMas());
            statement.setString(3, mascota.getTipoMas());
            statement.setInt(4, mascota.getEdadMas());
            statement.setInt(5, mascota.getVacuMas());
            statement.setString(6, mascota.getCaracterMas());
            statement.setInt(7, mascota.getDietMas());
            statement.setInt(8, mascota.getChipMas());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET nomMas = ?, variMas = ?, tipoMas = ?, edadMas = ?, vacuMas = ?, caracterMas = ?, dietMas = ?, chipMas = ?, adoptar = ? WHERE codMas = ?");
            statement.setString(1, mascota.getNomMas());
            statement.setString(2, mascota.getVariMas());
            statement.setString(3, mascota.getTipoMas());
            statement.setInt(4, mascota.getEdadMas());
            statement.setInt(5, mascota.getVacuMas());
            statement.setString(6, mascota.getCaracterMas());
            statement.setInt(7, mascota.getDietMas());
            statement.setInt(8, mascota.getChipMas());
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE codMas = ?");
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
