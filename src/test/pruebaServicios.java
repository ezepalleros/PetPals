package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import controladores.ClienteControlador;
import controladores.ServicioControlador;
import modelos.Servicio;

public class pruebaServicios {

	ClienteControlador clienteControlador = new ClienteControlador();
	ServicioControlador servicioControlador = new ServicioControlador();
	LocalDate fechaActual = LocalDate.now();

	@Test
	public void pruebaSolicitarServicioV() {
		boolean flag = true;

		clienteControlador.fechaActual(fechaActual);

		clienteControlador.solicitarServicio(100001, 301, LocalDate.of(2025, 6, 1), 200001, 1001);

		String mensajeError = clienteControlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = false;
		}

		assertEquals(true, flag);
	}

	@Test
	public void pruebaSolicitarServicioF() { // El animal seleccionado no puede recibir este servicio

		boolean flag = false;

		clienteControlador.fechaActual(fechaActual);

		clienteControlador.solicitarServicio(100001, 301, LocalDate.of(2024, 6, 1), 200004, 1001);

		String mensajeError = clienteControlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	@Test
	public void pruebaSolicitarServicioF2() { // La fecha seleccionada es anterior a la fecha actual
		boolean flag = false;

		clienteControlador.fechaActual(fechaActual);

		clienteControlador.solicitarServicio(100001, 301, LocalDate.of(2022, 6, 5), 200001, 1001);

		String mensajeError = clienteControlador.getMensajeError();

		if (mensajeError != null && !mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	@Test
	public void PruebaAgregarServicioV() {
		boolean flag = false;

		Servicio nuevoServicio = new Servicio(0, "Prueba 1", LocalDate.of(2025, 6, 12),
				LocalDateTime.of(2025, 6, 12, 14, 6), LocalDateTime.of(2025, 6, 12, 16, 6), 1, 1, 0, 0, 1, 2000, 4000,
				0, 0, 9000);

		servicioControlador.fechaActual(fechaActual);
		servicioControlador.addService(nuevoServicio);

		String mensajeError = servicioControlador.getMensajeError();

		if (mensajeError == null || mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	@Test
	public void PruebaAgregarServicioF() { // El nombre es corto, la fecha de límite del servicio es anterior a la
											// actual, los horarios están incorrectos
		boolean flag = true;

		Servicio nuevoServicio = new Servicio(0, "Pu", LocalDate.of(2022, 9, 1), LocalDateTime.of(2022, 9, 2, 9, 2),
				LocalDateTime.of(2022, 6, 2, 7, 9), 1, 1, 0, 0, 1, 2000, 4000, 0, 0, 9000);

		servicioControlador.fechaActual(fechaActual);
		servicioControlador.addService(nuevoServicio);

		String mensajeError = servicioControlador.getMensajeError();

		if (mensajeError == null || mensajeError.isEmpty()) {
			flag = false;
		}

		assertEquals(true, flag);
	}

	@Test
	public void PruebaAgregarServicioF2() { // El servicio no está disponible para ninguna variedad de mascota
		boolean flag = true;

		Servicio nuevoServicio = new Servicio(0, "Prueba 1", LocalDate.of(2024, 6, 12),
				LocalDateTime.of(2024, 6, 12, 14, 6), LocalDateTime.of(2024, 6, 12, 16, 6), 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0);

		servicioControlador.fechaActual(fechaActual);
		servicioControlador.addService(nuevoServicio);

		String mensajeError = servicioControlador.getMensajeError();

		if (mensajeError == null || mensajeError.isEmpty()) {
			flag = false;
		}

		assertEquals(true, flag);
	}

	@Test
	public void PruebaActualizarServicioV() {
		boolean flag = false;

		Servicio nuevoServicio = new Servicio(301, "Paseo", LocalDate.of(2025, 6, 3),
				LocalDateTime.of(2025, 6, 3, 10, 00), LocalDateTime.of(2025, 6, 3, 20, 30), 1, 0, 0, 0, 0, 15000, 0, 0,
				0, 0);

		servicioControlador.fechaActual(fechaActual);
		servicioControlador.updateService(nuevoServicio);

		String mensajeError = servicioControlador.getMensajeError();

		if (mensajeError == null || mensajeError.isEmpty()) {
			flag = true;
		}

		assertEquals(true, flag);
	}

	@Test
	public void PruebaActualizarServicioF() { // el codigo de servicio es incorrecto, las fechas introducidas son
												// pasadas, el servicio no está disponible para ninguna variedad de
												// mascota
		boolean flag = true;

		Servicio nuevoServicio = new Servicio(209, "Paseo", LocalDate.of(2022, 3, 1),
				LocalDateTime.of(2022, 3, 1, 7, 0), LocalDateTime.of(2022, 3, 1, 6, 0), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		servicioControlador.fechaActual(fechaActual);
		servicioControlador.updateService(nuevoServicio);

		String mensajeError = servicioControlador.getMensajeError();

		if (mensajeError == null || mensajeError.isEmpty()) {
			flag = false;
		}

		assertEquals(true, flag);
	}
}