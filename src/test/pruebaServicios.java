package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import controladores.ClienteControlador;

public class pruebaServicios {
	
	@Test
	public void pruebaSolicitarServicioV() {
	    ClienteControlador controlador = new ClienteControlador();
	    boolean flag = true;
	    
	    LocalDate fechaActual = LocalDate.now();
	    controlador.fechaActual(fechaActual);
	    
	    controlador.solicitarServicio(100001, 301, LocalDate.of(2024, 6, 1), 200001, 1001);
	    
	    String mensajeError = controlador.getMensajeError();
	    
	    if (mensajeError != null && !mensajeError.isEmpty()) {
	        flag = false;
	    }
	    
	    assertEquals(true, flag);
	}
	
	@Test
	public void pruebaSolicitarServicioF() { //El animal seleccionado no puede recibir este servicio
	    ClienteControlador controlador = new ClienteControlador();
	    boolean flag = false;
	    
	    LocalDate fechaActual = LocalDate.now();
	    controlador.fechaActual(fechaActual);
	    
	    controlador.solicitarServicio(100001, 301, LocalDate.of(2024, 6, 1), 200004, 1001);
	    
	    String mensajeError = controlador.getMensajeError();
	    
	    if (mensajeError != null && !mensajeError.isEmpty()) {
	        flag = true;
	    }
	    
	    assertEquals(true, flag);
	}
	
	@Test
	public void pruebaSolicitarServicioF2() { //La fecha seleccionada es anterior a la fecha actual
	    ClienteControlador controlador = new ClienteControlador();
	    boolean flag = false;
	    
	    LocalDate fechaActual = LocalDate.now();
	    controlador.fechaActual(fechaActual);
	    
	    controlador.solicitarServicio(100001, 301, LocalDate.of(2022, 6, 5), 200001, 1001);
	    
	    String mensajeError = controlador.getMensajeError();
	    
	    if (mensajeError != null && !mensajeError.isEmpty()) {
	        flag = true;
	    }
	    
	    assertEquals(true, flag);
	}

}