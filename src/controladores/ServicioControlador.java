package controladores;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import interfaces.ServicioRepository;
import modelos.Servicio;

public class ServicioControlador implements ServicioRepository {
    private final Connection connection;

    public ServicioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Servicio> getAllServices() {
        List<Servicio> servicios = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM servicio ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Servicio servicio = new Servicio(resultSet.getInt("codSer"), resultSet.getString("nomSer"), resultSet.getDate("diaSer").toLocalDate(), resultSet.getTimestamp("inicioSer").toLocalDateTime(), resultSet.getTimestamp("finSer").toLocalDateTime(), resultSet.getInt("puedePerro"), resultSet.getInt("puedeGato"), resultSet.getInt("puedeAve"), resultSet.getInt("puedeRoedor"), resultSet.getInt("puedeReptil"), resultSet.getInt("precioPerro"), resultSet.getInt("precioGato"), resultSet.getInt("precioAve"), resultSet.getInt("precioRoedor"), resultSet.getInt("precioReptil"));
            	servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }

    @Override
    public Servicio getServiceById(int id) {
    	Servicio servicios = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM servicio WHERE codSer = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	servicios = new Servicio(resultSet.getInt("codSer"), resultSet.getString("nomSer"), resultSet.getDate("diaSer").toLocalDate(), resultSet.getTimestamp("inicioSer").toLocalDateTime(), resultSet.getTimestamp("finSer").toLocalDateTime(), resultSet.getInt("puedePerro"), resultSet.getInt("puedeGato"), resultSet.getInt("puedeAve"), resultSet.getInt("puedeRoedor"), resultSet.getInt("puedeReptil"), resultSet.getInt("precioPerro"), resultSet.getInt("precioGato"), resultSet.getInt("precioAve"), resultSet.getInt("precioRoedor"), resultSet.getInt("precioReptil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }
    
	@Override
	public void addService(Servicio servicio) {
	    try {
	        PreparedStatement statement = connection.prepareStatement("INSERT INTO servicio (nomSer, diaSer, inicioSer, finSer, puedePerro, puedeGato, puedeAve, puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor, precioReptil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        statement.setString(1, servicio.getNomSer());
	        statement.setDate(2, Date.valueOf(servicio.getDiaSer()));
	        statement.setTimestamp(3, Timestamp.valueOf(servicio.getInicioSer()));
	        statement.setTimestamp(4, Timestamp.valueOf(servicio.getFinSer()));
	        statement.setInt(5, servicio.getPuedePerro());
	        statement.setInt(6, servicio.getPuedeGato());
	        statement.setInt(7, servicio.getPuedeAve());
	        statement.setInt(8, servicio.getPuedeRoedor());
	        statement.setInt(9, servicio.getPuedeReptil());
	        statement.setInt(10, servicio.getPrecioPerro());
	        statement.setInt(11, servicio.getPrecioGato());
	        statement.setInt(12, servicio.getPrecioAve());
	        statement.setInt(13, servicio.getPrecioRoedor());
	        statement.setInt(14, servicio.getPrecioReptil());

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Servicio insertado exitosamente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void updateService(Servicio servicio) {
	    try {
	        PreparedStatement statement = connection.prepareStatement("UPDATE servicio SET nomSer = ?, diaSer = ?, inicioSer = ?, finSer = ?, puedePerro = ?, puedeGato = ?, puedeAve = ?, puedeRoedor = ?, puedeReptil = ?, precioPerro = ?, precioGato = ?, precioAve = ?, precioRoedor = ?, precioReptil = ? WHERE codSer = ?");
	        statement.setString(1, servicio.getNomSer());
	        statement.setDate(2, Date.valueOf(servicio.getDiaSer()));
	        statement.setTimestamp(3, Timestamp.valueOf(servicio.getInicioSer()));
	        statement.setTimestamp(4, Timestamp.valueOf(servicio.getFinSer()));
	        statement.setInt(5, servicio.getPuedePerro());
	        statement.setInt(6, servicio.getPuedeGato());
	        statement.setInt(7, servicio.getPuedeAve());
	        statement.setInt(8, servicio.getPuedeRoedor());
	        statement.setInt(9, servicio.getPuedeReptil());
	        statement.setInt(10, servicio.getPrecioPerro());
	        statement.setInt(11, servicio.getPrecioGato());
	        statement.setInt(12, servicio.getPrecioAve());
	        statement.setInt(13, servicio.getPrecioRoedor());
	        statement.setInt(14, servicio.getPrecioReptil());
	        statement.setInt(15, servicio.getCodSer());

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Servicio actualizado exitosamente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteService(int id) {
	    try {
	        PreparedStatement statement = connection.prepareStatement("DELETE FROM servicio WHERE codSer = ?");
	        statement.setInt(1, id);
	        
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Servicio eliminado exitosamente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
