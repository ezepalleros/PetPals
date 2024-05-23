
package controladores;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.GerenteRepository;
import modelos.Cliente;
import modelos.Gerente;

public class GerenteControlador implements GerenteRepository {
    private final Connection connection;

    public GerenteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Gerente> getAllUsers() {
        List<Gerente> gerentes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Gerente gerente = new Gerente(resultSet.getInt("codGer"), resultSet.getString("nomGer"), resultSet.getString("mailGer"), resultSet.getString("numGer"));
            	gerentes.add(gerente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gerentes;
    }

    @Override
    public Gerente getUserById(int id) {
    	Gerente gerentes = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE codGer = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	gerentes = new Gerente(resultSet.getInt("codGer"), resultSet.getString("nomGer"), resultSet.getString("mailGer"), resultSet.getString("numGer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gerentes;
    }
    
	@Override
    public void addUser(Gerente gerente) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email, number) VALUES (?, ?, ?)");
            statement.setString(1, gerente.getNomUsu());
            statement.setString(2, gerente.getMailUsu());
            statement.setString(3, gerente.getTelUsu());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateUser(Gerente gerente) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, email = ?, number = ? WHERE id = ?");
            statement.setString(1, gerente.getNomUsu());
            statement.setString(2, gerente.getMailUsu());
            statement.setString(3, gerente.getTelUsu());
            
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
