package controladores;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.GerenteRepository;
import modelos.Gerente;

public class GerenteControlador implements GerenteRepository {
    private final Connection connection;

    public GerenteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Gerente> getAllManagers() {
        List<Gerente> gerentes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM gerente ");
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
    public Gerente getManagerById(int id) {
    	Gerente gerentes = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM gerente WHERE codGer = ?");
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
    public void addManager(Gerente gerente) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO gerente (nomGer, mailGer, numGer) VALUES (?, ?, ?)");
            statement.setString(1, gerente.getNomUsu());
            statement.setString(2, gerente.getMailUsu());
            statement.setString(3, gerente.getTelUsu());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Gerente insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateManager(Gerente gerente) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE gerente SET nomGer = ?, mailGer = ?, numGer = ? WHERE codGer = ?");
            statement.setString(1, gerente.getNomUsu());
            statement.setString(2, gerente.getMailUsu());
            statement.setString(3, gerente.getTelUsu());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Gerente actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteManager(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM gerente WHERE codGer = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Gerente eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
