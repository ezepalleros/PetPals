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
	ServicioControlador servicioControlador = new ServicioControlador();
	EmpleadoControlador empleadoControlador = new EmpleadoControlador();

	LocalDate diaActual = null;
	private String mensajeError = "";

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
				Cliente cliente = new Cliente(resultSet.getInt("codCli"), resultSet.getString("nomCli"),
						resultSet.getString("mailCli"), resultSet.getString("dirCli"), resultSet.getString("numCli"));
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
				cliente = new Cliente(resultSet.getInt("codCli"), resultSet.getString("nomCli"),
						resultSet.getString("mailCli"), resultSet.getString("dirCli"), resultSet.getString("numCli"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public void addClient(Cliente cliente) {
		boolean errorServicio = false;

		if (cliente.getNomUsu().length() > 20) {
			mensajeError += "Error: El nombre debe tener como máximo 20 caracteres. \n";
			errorServicio = true;
		}

		if (cliente.getMailUsu().length() < 8 || !cliente.getMailUsu().endsWith("@gmail.com")) {
			mensajeError += "Error: El correo electrónico debe tener al menos 8 caracteres y terminar con '@gmail.com'. \n";
			errorServicio = true;
		}

		if (cliente.getDirCli().length() > 30) {
			mensajeError += "Error: La dirección debe tener como máximo 30 caracteres. \n";
			errorServicio = true;
		}

		if (cliente.getTelUsu().length() > 12) {
			mensajeError += "Error: El número de teléfono no debe tener mas de 12 caracteres. \n";
			errorServicio = true;
		}

		if (errorServicio) {
			JOptionPane.showMessageDialog(null, mensajeError);
			return;
		} else {
			try {
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO cliente (nomCli, mailCli, dirCli, numCli) VALUES (?, ?, ?, ?)");
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
	}

	@Override
	public void updateClient(Cliente cliente) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE cliente SET nomCli = ?, mailCli = ?, dirCli = ?, numCli = ? WHERE codCli = ?");
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
		boolean errorServicio = false;

		for (Servicio servicio : servicioControlador.getAllServices()) {
			if (servicio.getCodSer() == codServicio) {
				servicioSeleccionado = servicio;
			}
		}

		Mascota mascotaSeleccionada = null;
		for (Mascota mascota : mascotaControlador.getAllPets()) {
			if (mascota.getCodMas() == idMascota) {
				mascotaSeleccionada = mascota;
			}
		}

		String variedadMascota = mascotaSeleccionada.getVariMas();
		boolean puedeRecibirServicio = false;

		if (variedadMascota.equalsIgnoreCase("Perro") && servicioSeleccionado.getPuedePerro() == 1) {
			puedeRecibirServicio = true;
		} else if (variedadMascota.equalsIgnoreCase("Gato") && servicioSeleccionado.getPuedeGato() == 1) {
			puedeRecibirServicio = true;
		} else if (variedadMascota.equalsIgnoreCase("Ave") && servicioSeleccionado.getPuedeAve() == 1) {
			puedeRecibirServicio = true;
		} else if (variedadMascota.equalsIgnoreCase("Roedor") && servicioSeleccionado.getPuedeRoedor() == 1) {
			puedeRecibirServicio = true;
		} else if (variedadMascota.equalsIgnoreCase("Reptil") && servicioSeleccionado.getPuedeReptil() == 1) {
			puedeRecibirServicio = true;
		}

		if (!puedeRecibirServicio) {
			mensajeError += "Error: La mascota no puede recibir este servicio \n";
			errorServicio = true;
		}

		if (diaDeseado.isBefore(diaActual) || diaDeseado.isAfter(servicioSeleccionado.getDiaSer())) {
			mensajeError += "Error: El día deseado no puede ser en el pasado o después de la última fecha del servicio. \n";
			errorServicio = true;
		}

		if (errorServicio) {
			JOptionPane.showMessageDialog(null, mensajeError);
			return;
		} else {
			JOptionPane.showMessageDialog(null, "Servicio solicitado exitosamente.");
		}

	}

	public void fechaActual(LocalDate fechaActual) {
		diaActual = fechaActual;
	}

	public String getMensajeError() {
		return mensajeError;
	}
}
