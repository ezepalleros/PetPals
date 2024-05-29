package vista;

import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;

import modelos.*;
import controladores.*;

public class Main {
	public static void main(String[] args) {
	    ClienteControlador clienteControlador = new ClienteControlador();
	    EmpleadoControlador empleadoControlador = new EmpleadoControlador();
	    GerenteControlador gerenteControlador = new GerenteControlador();
	    LocalDate fechaActual = LocalDate.now();
	    boolean irse1 = false;
	    
	    JOptionPane.showMessageDialog(null, clienteControlador.getAllClients());
	    
	    JOptionPane.showMessageDialog(null, "El dia de hoy es: " + fechaActual);
	    
	    String[] menu1 = { "Iniciar sesión", "Registrarse", "Pasar el dia", "Salir" };
	    do {
	    	
	        int opcion1 = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menu1, menu1[0]);
	        switch (opcion1) {
	        case 0:
	            String nombreUsuario = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario: (Por ejemplo: LucasPanga)");
	            String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico: (Por ejemplo: lucasPanga@gmail.com)");
	            
	            int tipoUsuario = 0;
	            int codigoCliente = 0;

	            List<Cliente> clientes = clienteControlador.getAllClients();
	            List<Empleado> empleados = empleadoControlador.getAllEmployees();
	            List<Gerente> gerentes = gerenteControlador.getAllManagers();
	            
	            if (correoUsuario.endsWith("@gmail.com")) {
					
				for (Cliente cliente : clientes) {
	                tipoUsuario = cliente.iniciarSesion(nombreUsuario, correoUsuario);
	                if (tipoUsuario != 0) {
	                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como cliente con:\nUsuario: " + cliente.getNomUsu() + "\nCódigo: " + cliente.getCodUsu());
	                    codigoCliente = cliente.getCodUsu();
	                    break;
	                }
	            }
	            
	            }
	            
	            if (correoUsuario.endsWith("@guarda.com")) {
	                for (Empleado empleado : empleados) {
	                    tipoUsuario = empleado.iniciarSesion(nombreUsuario, correoUsuario);
	                    if (tipoUsuario != 0) {
	                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como empleado con:\nUsuario: " + empleado.getNomUsu() + "\nCódigo: " + empleado.getCodUsu());
	                        break;
	                    }
	                }
	            }

	            if (correoUsuario.endsWith("@guarda2.com")) {
	                for (Gerente gerente : gerentes) {
	                    tipoUsuario = gerente.iniciarSesion(nombreUsuario, correoUsuario);
	                    if (tipoUsuario != 0) {
	                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como gerente con:\nUsuario: " + gerente.getNomUsu() + "\nCódigo: " + gerente.getCodUsu());
	                        break;
	                    }
	                }
	            }


                if (tipoUsuario == 0) {
                    JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
                } else {
                    switch (tipoUsuario) {
                        case 1: // Cliente
                            String[] menuCliente = { "Registrar mascota", "Solicitar servicio", "Ver mis mascotas", "Adoptar", "Salir" };
                            boolean irseCliente = false;
                            do {
                                int opcionCliente = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menuCliente, menuCliente[0]);
                                switch (opcionCliente) {
                                    case 0:
                                        // Implementar lógica para registrar mascota
                                        break;
                                    case 1:
                                        // Implementar lógica para solicitar servicio
                                        break;
                                    case 2:
                                        if (codigoCliente != 0) {
                                            List<Mascota> mascotasCliente = new ArrayList<>();

                                            MascotaControlador mascotaControlador = new MascotaControlador();
                                            for (Mascota mascota : mascotaControlador.getAllPets()) {
                                                if (mascota.getDueMas() == codigoCliente) {
                                                    mascotasCliente.add(mascota);
                                                }
                                            }

                                            if (mascotasCliente.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "El cliente no tiene mascotas registradas.");
                                            } else {
                                                String mensaje = "Mascotas del cliente:\n";
                                                for (Mascota mascota : mascotasCliente) {
                                                    mensaje += "Nombre: " + mascota.getNomMas() + ", Tipo: " + mascota.getTipoMas() + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null, mensaje);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error: Código de cliente no válido.");
                                        }
                                        break;
                                    case 3:
                                        JOptionPane.showMessageDialog(null, "Adoptar");
                                        break;
                                    case 4:
                                        irseCliente = true;
                                        break;
                                }
                            } while (!irseCliente);
                            break;
                        case 2: // Empleado
                            String[] menuEmpleado = { "Ver solicitudes", "Ver reseñas", "Salir" };
                            boolean irseEmpleado = false;
                            do {
                                int opcionEmpleado = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menuEmpleado, menuEmpleado[0]);
                                switch (opcionEmpleado) {
                                    case 0:
                                    	JOptionPane.showMessageDialog(null, "Solicitudes");
                                        break;
                                    case 1:
                                    	JOptionPane.showMessageDialog(null, "Reseñas");
                                        break;
                                    case 2:
                                        irseEmpleado = true;
                                        break;
                                }
                            } while (!irseEmpleado);
                            break;
                        case 3: // Gerente
                            String[] menuGerente = { "Agregar servicios", "Actualizar servicios", "Ver empleados", "Ver mascotas", "Salir" };
                            boolean irseGerente = false;
                            do {
                                int opcionGerente = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menuGerente, menuGerente[0]);
                                switch (opcionGerente) {
                                    case 0:
                                        // Implementar lógica para agregar servicios
                                        break;
                                    case 1:
                                        // Implementar lógica para actualizar servicios
                                        break;
                                    case 2:
                                    	List<Empleado> listaEmpleados = empleadoControlador.getAllEmployees();
                                        if (listaEmpleados.isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Lista de Empleados", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            String empleadosInfo = "";
                                            for (Empleado emp : listaEmpleados) {
                                                empleadosInfo += "Nombre: " + emp.getNomUsu() + "\n";
                                                empleadosInfo += "Correo: " + emp.getMailUsu() + "\n";
                                                empleadosInfo += "Código: " + emp.getCodUsu() + "\n\n";
                                            }
                                            JOptionPane.showMessageDialog(null, empleadosInfo, "Lista de Empleados", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                    case 3:
                                        // Implementar lógica para ver mascotas
                                        break;
                                    case 4:
                                        irseGerente = true;
                                        break;
                                }
                            } while (!irseGerente);
                            break;
                    }
                }
                break;

	        case 1:
	            String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese su nombre: (Por ejemplo: oscarGomez)");
	            String nuevoCorreo = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico: (Por ejemplo: oscarGomez@gmail.com)");
	            String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese su dirección: (Por ejemplo: Aconcagua 2445 c)");
	            String nuevoTelefono = JOptionPane.showInputDialog(null, "Ingrese su número de teléfono: (Por ejemplo: 1137894221)");

	            if (nuevoNombre != null && nuevoCorreo != null && nuevaDireccion != null && nuevoTelefono != null) {
	                Cliente nuevoCliente = new Cliente(0, nuevoNombre, nuevoCorreo, nuevaDireccion, nuevoTelefono);
	                clienteControlador.addClient(nuevoCliente);
	            } else {
	                JOptionPane.showMessageDialog(null, "Error: Uno o más campos están vacíos.");
	            }
	            break;
	            
	        case 2:
                fechaActual = fechaActual.plusDays(1);
                JOptionPane.showMessageDialog(null, "El dia de hoy es: " + fechaActual);
                break;

	            case 3:
	                irse1 = true;
	                break;
	        }
	    } while (!irse1);
	}

}

