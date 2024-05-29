package controladores;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import javax.swing.JOptionPane;

import interfaces.ServicioRepository;
import modelos.Servicio;

public class ServicioControlador implements ServicioRepository {
    private final Connection connection;

    public ServicioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    LocalDate diaActual = null;

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
        if (servicio.getNomSer().length() <= 3) {
        	JOptionPane.showMessageDialog(null,"Error: El nombre del servicio debe contener más de 3 caracteres.");
            return;
        }

        LocalTime horaActual = LocalTime.now();

        LocalDate diaServicio = servicio.getDiaSer();
        if (diaServicio.isBefore(diaActual)) {
        	JOptionPane.showMessageDialog(null,"Error: El día del servicio debe ser en el futuro.");
            return;
        }

        LocalTime horaInicioServicio = servicio.getInicioSer().toLocalTime();
        if (diaServicio.isEqual(diaActual) && horaInicioServicio.isBefore(horaActual)) {
        	JOptionPane.showMessageDialog(null,"Error: La hora de inicio del servicio debe ser en el futuro.");
            return;
        }
        if (horaInicioServicio.isBefore(LocalTime.parse("10:00:00")) || horaInicioServicio.isAfter(LocalTime.parse("20:30:00"))) {
        	JOptionPane.showMessageDialog(null,"Error: La hora de inicio del servicio debe estar entre las 10:00:00 y las 20:30:00.");
            return;
        }

        LocalTime horaFinServicio = servicio.getFinSer().toLocalTime();
        if (diaServicio.isEqual(diaActual) && horaFinServicio.isBefore(horaActual)) {
        	JOptionPane.showMessageDialog(null,"Error: La hora de fin del servicio debe ser en el futuro.");
            return;
        }
        if (horaFinServicio.isBefore(horaInicioServicio) || horaFinServicio.isAfter(LocalTime.parse("20:30:00"))) {
            System.out.println("Error: La hora de fin del servicio debe ser en el futuro al horario de inicio y antes de las 20:30:00.");
            return;
        }

        if (servicio.getPuedePerro() != 0 && servicio.getPuedePerro() != 1) {
        	JOptionPane.showMessageDialog(null,"Error: El valor de 'puedePerro' debe ser 0 o 1.");
            return;
        }
        if (servicio.getPuedeGato() != 0 && servicio.getPuedeGato() != 1) {
        	JOptionPane.showMessageDialog(null,"Error: El valor de 'puedeGato' debe ser 0 o 1.");
            return;
        }
        if (servicio.getPuedeAve() != 0 && servicio.getPuedeAve() != 1) {
        	JOptionPane.showMessageDialog(null,"Error: El valor de 'puedeAve' debe ser 0 o 1.");
            return;
        }
        if (servicio.getPuedeRoedor() != 0 && servicio.getPuedeRoedor() != 1) {
        	JOptionPane.showMessageDialog(null,"Error: El valor de 'puedeRoedor' debe ser 0 o 1.");
            return;
        }
        if (servicio.getPuedeReptil() != 0 && servicio.getPuedeReptil() != 1) {
        	JOptionPane.showMessageDialog(null,"Error: El valor de 'puedeReptil' debe ser 0 o 1.");
            return;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO servicio (nomSer, diaSer, horaIniSer, horaFinSer, puedePerro, puedeGato, puedeAve, puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor, precioReptil) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            	JOptionPane.showMessageDialog(null,"Servicio insertado exitosamente");
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

	public void fechaActual(LocalDate fechaActual) {
		diaActual = fechaActual;
		
	}
}
