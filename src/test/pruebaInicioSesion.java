package test;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import controladores.ClienteControlador;
import modelos.Cliente;

public class pruebaInicioSesion {

	ClienteControlador controlador = new ClienteControlador();

	@Test
	public void PruebaIniciarSesionV() {
		boolean flag = false;
		for (Cliente cliente : controlador.getAllClients()) {
			if (cliente.iniciarSesionCliente("LucasPanga", "lucasPanga@gmail.com") == 1) {
				flag = true;
				break;
			}
		}
		assertEquals(true, flag);

	}

	@Test
	public void PruebaIniciarSesionF() { // La cuenta no existe
		boolean flag = false;
		for (Cliente cliente : controlador.getAllClients()) {
			if (cliente.iniciarSesionCliente("Papapapapa", "lucasPanga@gmail.com") == 0) {
				flag = true;
				break;
			}
		}
		assertEquals(true, flag);

	}

	@Test
	public void PruebaIniciarSesionF2() { // El nombre está vacio
		boolean flag = false;
		for (Cliente cliente : controlador.getAllClients()) {
			if (cliente.iniciarSesionCliente("", "hola.com") == 0) {
				flag = true;
				break;
			}
		}
		assertEquals(true, flag);

	}

	@Test
	public void pruebaRegistroV() {
		boolean flag = true;

		Cliente nuevoCliente = new Cliente(0, "Eugenio Martinez", "martinezEugenio@gmail.com", "Cuenca 1223",
				"1142887521");

		controlador.addClient(nuevoCliente);

		String mensajeError = controlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = false;
		}

		assertEquals(true, flag);
	}

	@Test
	public void pruebaRegistroF() { // el nombre contiene menos de 3 caracteres, el numero tiene más de 12
									// caracteres
		boolean flag = false;

		Cliente nuevoCliente = new Cliente(0, "ss", "martinezEugenio@gmail.com", "Cuenca 1223", "11223344556677");

		controlador.addClient(nuevoCliente);

		String mensajeError = controlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	@Test
	public void pruebaRegistroF2() { // el mail termina diferente
		boolean flag = false;

		Cliente nuevoCliente = new Cliente(0, "Eugenio Martinez", "martinezEugenio@panza.com", "Cuenca 1223",
				"1142887521");

		controlador.addClient(nuevoCliente);

		String mensajeError = controlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

}