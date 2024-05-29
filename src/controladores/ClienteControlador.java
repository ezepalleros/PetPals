
package controladores;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.ClienteRepository;
import modelos.*;

public class ClienteControlador implements ClienteRepository {
    private final Connection connection;
    MascotaControlador mascotaControlador = new MascotaControlador();
    ServicioControlador servicioControlador = new  ServicioControlador();
    EmpleadoControlador empleadoControlador = new EmpleadoControlador();
    
    LocalDate diaActual = null;

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
	    if (cliente.getNomUsu().length() > 20) {
	        JOptionPane.showMessageDialog(null, "Error: El nombre debe tener como máximo 20 caracteres.");
	        return;
	    }

	    if (cliente.getMailUsu().length() < 8 || !cliente.getMailUsu().endsWith("@gmail.com")) {
	    	JOptionPane.showMessageDialog(null, "Error: El correo electrónico debe tener al menos 8 caracteres y terminar con '@gmail.com'.");
	        return;
	    }

	    if (cliente.getDirCli().length() > 30) {
	    	JOptionPane.showMessageDialog(null, "Error: La dirección debe tener como máximo 30 caracteres.");
	        return;
	    }

	    if (cliente.getTelUsu().length() > 12) {
	    	JOptionPane.showMessageDialog(null, "Error: El número de teléfono no debe tener mas de 12 caracteres.");
	        return;
	    }

	    try {
	        PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (nomCli, mailCli, dirCli, numCli) VALUES (?, ?, ?, ?)");
	        statement.setString(1, cliente.getNomUsu());
	        statement.setString(2, cliente.getMailUsu());
	        statement.setString(3, cliente.getDirCli());
	        statement.setString(4, cliente.getTelUsu());

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	        	JOptionPane.showMessageDialog(null, "Cliente insertado exitosamente");
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
    
    public void solicitarServicio(int clienteId, int codServicio, LocalDate diaDeseado, int idMascota, int idEmpleado) {
        Servicio servicioSeleccionado = null;
        for (Servicio servicio : servicioControlador.getAllServices()) {
            if (servicio.getCodSer() == codServicio) {
                servicioSeleccionado = servicio;
            }
        }

        Mascota mascotaSeleccionada = null;
        for (Mascota mascota : mascotaControlador.getAllPets()) {
            if (mascota.getCodMas() == idMascota) {
                mascotaSeleccionada = mascota;
                break;
            }
        }
        
        String puedeMascota = null;
        
        if (servicioSeleccionado.getPuedePerro() == 1) {
        	puedeMascota += "Perro ";
		}
        if (servicioSeleccionado.getPuedeGato() == 1) {
        	puedeMascota += "Gato ";
		}
        if (servicioSeleccionado.getPuedeAve() == 1) {
        	puedeMascota += "Ave ";
		}
        if (servicioSeleccionado.getPuedeRoedor() == 1) {
        	puedeMascota += "Roedor ";
		}
        if (servicioSeleccionado.getPuedeReptil() == 1) {
        	puedeMascota += "Reptil ";
		}

        if (mascotaSeleccionada.getVariMas().contains(puedeMascota)) {
            JOptionPane.showMessageDialog(null, "Error: La mascota no puede recibir este servicio");
            return;
        }

        if (diaDeseado.isBefore(diaActual) || diaDeseado.isAfter(servicioSeleccionado.getDiaSer())) {
            JOptionPane.showMessageDialog(null, "Error: El día deseado no puede ser en el pasado o después de la última fecha del servicio.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Servicio solicitado exitosamente.");
    }
    
	public void fechaActual(LocalDate fechaActual) {
		diaActual = fechaActual;
	}
}
