
package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.EmpleadoRepository;
import modelos.Usuario;
import modelos.Empleado;

public class EmpleadoControlador implements EmpleadoRepository {
    private final Connection connection;

    public EmpleadoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Empleado> getAllUsers() {
        List<Empleado> empleados = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Empleado empleado = new Empleado(resultSet.getInt("codEmp"), resultSet.getString("nomEmp"), resultSet.getString("mailEmp"), resultSet.getString("telEmp"), resultSet.getDate("antiEmp").toLocalDate(), resultSet.getString("detallEmp"), resultSet.getInt("califEmp"));
            	empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public Empleado getUserById(int id) {
    	Empleado empleados = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	empleados = new Empleado(resultSet.getInt("codEmp"), resultSet.getString("nomEmp"), resultSet.getString("mailEmp"), resultSet.getString("telEmp"), resultSet.getDate("antiEmp").toLocalDate(), resultSet.getString("detallEmp"), resultSet.getInt("califEmp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
    
	@Override
    public void addUser(Empleado empleado) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (nomEmp, mailEmp, telEmp, antiEmp, detallEmp, califEmp) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, empleado.getNomUsu());
            statement.setString(2, empleado.getMailUsu());
            statement.setString(3, empleado.getTelUsu());
            statement.setDate(4, Date.valueOf(empleado.getAntiEmp()));
            statement.setString(5, empleado.getDetalleEmp());
            statement.setInt(6, empleado.getCalifEmp());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateUser(Empleado empleado) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET nomEmp = ?, mailEmp = ?, telEmp = ?, antiEmp = ?, detallEmp = ?, califEmp = ? WHERE codEmp = ?");
            statement.setString(1, empleado.getNomUsu());
            statement.setString(2, empleado.getMailUsu());
            statement.setString(3, empleado.getTelUsu());
            statement.setDate(4, Date.valueOf(empleado.getAntiEmp()));
            statement.setString(5, empleado.getDetalleEmp());
            statement.setInt(6, empleado.getCalifEmp());
            
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE codEmp = ?");
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
