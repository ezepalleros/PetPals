package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import controladores.ClienteControlador;
import modelos.Cliente;
import modelos.Empleado;
import modelos.Gerente;
import modelos.Mascota;

public class pruebaInicioSesion {

 @Test
 public void PruebaIniciarSesionV() {
  ClienteControlador controlador = new ClienteControlador();
  boolean flag=false;
  for (Cliente cliente : controlador.getAllClients()) {
   if (cliente.iniciarSesionCliente("LucasPanga", "lucasPanga@gmail.com") == 1) {
    flag =true;
    break;
   } 
  }
  assertEquals(true,flag);

 }
 @Test
 public void PruebaIniciarSesionF() {
  ClienteControlador controlador = new ClienteControlador();
  boolean flag=false;
  for (Cliente cliente : controlador.getAllClients()) {
   if (cliente.iniciarSesionCliente("Papapapapa", "lucasPanga@gmail.com") == 0) {
    flag =true;
    break;
   } 
  }
  assertEquals(true,flag);

 }
}