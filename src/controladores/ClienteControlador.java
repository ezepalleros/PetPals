
package controladores;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ClienteRepository;
import modelos.Cliente;

public class ClienteControlador implements ClienteRepository {
    private final Connection connection;

    public ClienteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Cliente> getAllClients() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Cliente cliente = new Cliente(resultSet.getInt("codCli"), resultSet.getString("nomCli"), resultSet.getString("mailCli"), resultSet.getString("dirCli"), resultSet.getString("numCli"));
            	clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente getClientById(int id) {
    	Cliente cliente = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE codCli = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                cliente = new Cliente(resultSet.getInt("codCli"), resultSet.getString("nomCli"), resultSet.getString("mailCli"), resultSet.getString("dirCli"), resultSet.getString("numCli"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
    
	@Override
    public void addClient(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (nomCli, mailCli, dirCli, numCli) VALUES (?, ?, ?, ?)");
            statement.setString(1, cliente.getNomUsu());
            statement.setString(2, cliente.getMailUsu());
            statement.setString(3, cliente.getDirCli());
            statement.setString(4, cliente.getTelUsu());

            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateClient(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE cliente SET nomCli = ?, mailCli = ?, dirCli = ?, numCli = ? WHERE codCli = ?");
            statement.setString(1, cliente.getNomUsu());
            statement.setString(2, cliente.getMailUsu());
            statement.setString(4, cliente.getDirCli());
            statement.setString(3, cliente.getTelUsu());

            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cliente WHERE codCli = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
