package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import interfaces.SolicitudRepository;
import modelos.Solicitud;

public class SolicitudControlador implements SolicitudRepository {
	private final Connection connection;

	public SolicitudControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<Solicitud> getAllRequests() {
		List<Solicitud> solicitudes = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM solicitud ");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Solicitud solicitud = new Solicitud(resultSet.getInt("codSol"), resultSet.getInt("codSer"),
						resultSet.getDate("diaSer").toLocalDate(),
						resultSet.getTimestamp("horaIniSer").toLocalDateTime(),
						resultSet.getTimestamp("horaFinSer").toLocalDateTime(), resultSet.getInt("codMas"),
						resultSet.getString("tipoMas"), resultSet.getString("CaracMas"));
				solicitudes.add(solicitud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solicitudes;
	}

	@Override
	public Solicitud getRequestById(int id) {
		Solicitud solicitudes = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM solicitud WHERE codSol = ?");
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				solicitudes = new Solicitud(resultSet.getInt("codSol"), resultSet.getInt("codSer"),
						resultSet.getDate("diaSer").toLocalDate(),
						resultSet.getTimestamp("horaIniSer").toLocalDateTime(),
						resultSet.getTimestamp("horaFinSer").toLocalDateTime(), resultSet.getInt("codMas"),
						resultSet.getString("tipoMas"), resultSet.getString("CaracMas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solicitudes;
	}

	@Override
	public void addRequest(Solicitud solicitud) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO solicitud (codSer, diaSer, horaIniSer, horaFinSer, codMas, tipoMas, CaracMas) VALUES (?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, solicitud.getCodSer());
			statement.setDate(2, Date.valueOf(solicitud.getDiaSer()));
			statement.setTimestamp(3, Timestamp.valueOf(solicitud.getHoraIniSer()));
			statement.setTimestamp(4, Timestamp.valueOf(solicitud.getHoraFinSer()));
			statement.setInt(5, solicitud.getCodMas());
			statement.setString(6, solicitud.getTipoMas());
			statement.setString(7, solicitud.getCaracMas());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Solicitud insertada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRequest(Solicitud solicitud) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE solicitud SET codSer = ?, diaSer = ?, horaIniSer = ?, horaFinSer = ?, codMas = ?, tipoMas = ?, CaracMas = ? WHERE codSol = ?");
			statement.setInt(1, solicitud.getCodSer());
			statement.setDate(2, Date.valueOf(solicitud.getDiaSer()));
			statement.setTimestamp(3, Timestamp.valueOf(solicitud.getHoraIniSer()));
			statement.setTimestamp(4, Timestamp.valueOf(solicitud.getHoraFinSer()));
			statement.setInt(5, solicitud.getCodMas());
			statement.setString(6, solicitud.getTipoMas());
			statement.setString(7, solicitud.getCaracMas());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Solicitud actualizada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRequest(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM solicitud WHERE codSol = ?");
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Solicitud eliminada exitosamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
