package vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

import modelos.*;
import controladores.*;

public class Main {
	public static void main(String[] args) {
	    ClienteControlador clienteControlador = new ClienteControlador();
	    EmpleadoControlador empleadoControlador = new EmpleadoControlador();
	    GerenteControlador gerenteControlador = new GerenteControlador();
	    MascotaControlador mascotaControlador = new MascotaControlador();
	    ServicioControlador servicioControlador = new  ServicioControlador();
	    
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
                                	
                                	String nombreMascota = JOptionPane.showInputDialog(null, "Ingrese el nombre de la mascota: (más de 3 caracteres)");
                                    String variedadMascota = JOptionPane.showInputDialog(null, "Ingrese la variedad de la mascota (Perro, Gato, Ave, Roedor, Reptil):");
                                    String tipoMascota = JOptionPane.showInputDialog(null, "Ingrese el tipo de mascota: (Labrador, Loro, etc.)");
                                    int edadMascota = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad de la mascota:"));
                                    int vacunasMascota = Integer.parseInt(JOptionPane.showInputDialog(null, "¿La mascota tiene vacunas? (1 si tiene, 0 si no tiene):"));
                                    String caracterMascota = JOptionPane.showInputDialog(null, "Ingrese el carácter de la mascota (Amistoso/a, Juguetón/a, Agresivo/a):");
                                    int dietaMascota = Integer.parseInt(JOptionPane.showInputDialog(null, "¿La mascota está en dieta? (1 para sí, 0 para no):"));
                                    int chipMascota = Integer.parseInt(JOptionPane.showInputDialog(null, "¿La mascota tiene chip? (1 si tiene, 0 si no tiene):"));
                                    int adoptarMascota = 0;
                                    int codigodeMascota = codigoCliente;
                                    
                                    Mascota nuevaMascota = new Mascota(0, nombreMascota, variedadMascota, tipoMascota, edadMascota, vacunasMascota, caracterMascota, dietaMascota, chipMascota, adoptarMascota, codigodeMascota);
                                    
                                    mascotaControlador.addPet(nuevaMascota);
                                    
                                    break;

                                case 1:
                                    if (codigoCliente != 0) {
                                        List<Mascota> mascotasCliente = new ArrayList<>();
                                        for (Mascota mascota : mascotaControlador.getAllPets()) {
                                            if (mascota.getDueMas() == codigoCliente) {
                                                mascotasCliente.add(mascota);
                                            }
                                        }

                                        if (mascotasCliente.isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "El cliente no tiene mascotas registradas.");
                                            break;
                                        }

                                        List<Servicio> servicios = servicioControlador.getAllServices();

                                        Object[] opcionesServicios = new Object[servicios.size()];
                                        for (int i = 0; i < servicios.size(); i++) {
                                            Servicio servicio = servicios.get(i);
                                            opcionesServicios[i] = servicio.getNomSer() + " (" + servicio.getDiaSer() + " - " + servicio.getHoraIniSer().toLocalTime() + " a " + servicio.getHoraFinSer().toLocalTime() + ")";
                                        }
                                        int seleccionServicioIndex = JOptionPane.showOptionDialog(null, "Seleccione un servicio", "Servicios",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesServicios, opcionesServicios[0]);
                                        Servicio servicioSeleccionado = servicios.get(seleccionServicioIndex);

                                        LocalDate diaDeseado = LocalDate.parse(JOptionPane.showInputDialog("Ingrese el día para recibir el servicio (AAAA-MM-DD):"));

                                        Object[] opcionesMascotas = new Object[mascotasCliente.size()];
                                        for (int i = 0; i < mascotasCliente.size(); i++) {
                                            Mascota mascota = mascotasCliente.get(i);
                                            opcionesMascotas[i] = mascota.getNomMas() + " (" + mascota.getTipoMas() + ")";
                                        }
                                        int seleccionMascotaIndex = JOptionPane.showOptionDialog(null, "Seleccione una mascota", "Mascotas",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesMascotas, opcionesMascotas[0]);
                                        Mascota mascotaSeleccionada = mascotasCliente.get(seleccionMascotaIndex);

                                        empleados = empleadoControlador.getAllEmployees();

                                        Object[] opcionesEmpleados = new Object[empleados.size()];
                                        for (int i = 0; i < empleados.size(); i++) {
                                            Empleado empleado = empleados.get(i);
                                            opcionesEmpleados[i] = empleado.getNomUsu();
                                        }
                                        int seleccionEmpleadoIndex = JOptionPane.showOptionDialog(null, "Seleccione un empleado", "Empleados",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesEmpleados, opcionesEmpleados[0]);
                                        Empleado empleadoSeleccionado = empleados.get(seleccionEmpleadoIndex);

                                        clienteControlador.solicitarServicio(codigoCliente, servicioSeleccionado.getCodSer(), diaDeseado, mascotaSeleccionada.getCodMas(), empleadoSeleccionado.getCodUsu());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error: Código de cliente no válido.");
                                    }
                                    break;




                                    case 2:
                                        if (codigoCliente != 0) {
                                            List<Mascota> mascotasCliente = new ArrayList<>();

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
                                    String nombreServicio = JOptionPane.showInputDialog(null, "Ingrese el nombre del servicio: (más de 3 caracteres)");
                                    LocalDate diaServicio = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha del servicio (AAAA-MM-DD):"));

                                    LocalDateTime horaInicioServicio = LocalDateTime.parse(JOptionPane.showInputDialog(null, "Ingrese la hora de inicio del servicio (AAAA-MM-DD HH:MM:SS):"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                                    LocalDateTime horaFinServicio = LocalDateTime.parse(JOptionPane.showInputDialog(null, "Ingrese la hora de fin del servicio (AAAA-MM-DD HH:MM:SS):"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                                    int puedePerro = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta perros? (1 para sí, 0 para no):"));
                                    int puedeGato = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta gatos? (1 para sí, 0 para no):"));
                                    int puedeAve = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta aves? (1 para sí, 0 para no):"));
                                    int puedeRoedor = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta roedores? (1 para sí, 0 para no):"));
                                    int puedeReptil = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta reptiles? (1 para sí, 0 para no):"));
                                    int precioPerro = 0;
                                    int precioGato = 0;
                                    int precioAve = 0;
                                    int precioRoedor = 0;
                                    int precioReptil = 0;
                                    
                                    if (puedePerro==1) {
                                    	precioPerro = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio para perros:"));
										
									} else {
										precioPerro = 0;
									}
                                    
                                    if (puedeGato==1) {
                                    	precioGato = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio para gatos:"));
									} else {
										precioGato = 0;
									}
                                    
                                    if (puedeAve == 1) {
                                    	precioAve = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio para aves:"));
									} else {
										precioAve = 0;
									}
                                    
                                    if (puedeRoedor == 1) {
                                    	precioRoedor = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio para roedores:"));
									} else {
										precioRoedor = 0;
									}
                                    
                                    if (puedeReptil == 1) {
                                    	precioReptil = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio para reptiles:"));
									} else {
										precioReptil = 0;
									}
                                    
                                    Servicio nuevoServicio = new Servicio(0, nombreServicio, diaServicio, horaInicioServicio, horaFinServicio, puedePerro, puedeGato, puedeAve, puedeRoedor, puedeReptil, precioPerro, precioGato, precioAve, precioRoedor, precioReptil);

                                    servicioControlador.fechaActual(fechaActual);
                                    servicioControlador.addService(nuevoServicio);
                                    break;

                                case 1:
                                    int codigoServicio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del servicio a actualizar:"));
                                    String nombreServicioActualizar = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del servicio: (más de 3 caracteres)");
                                    LocalDate diaServicioActualizar = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la nueva fecha del servicio (AAAA-MM-DD):"));
                                    LocalDateTime horaInicioServicioActualizar = LocalDateTime.parse(JOptionPane.showInputDialog(null, "Ingrese la nueva hora de inicio del servicio (AAAA-MM-DD HH:MM:SS):"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                    LocalDateTime horaFinServicioActualizar = LocalDateTime.parse(JOptionPane.showInputDialog(null, "Ingrese la nueva hora de fin del servicio (AAAA-MM-DD HH:MM:SS):"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                    int puedePerroActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta perros? (1 para sí, 0 para no):"));
                                    int puedeGatoActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta gatos? (1 para sí, 0 para no):"));
                                    int puedeAveActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta aves? (1 para sí, 0 para no):"));
                                    int puedeRoedorActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta roedores? (1 para sí, 0 para no):"));
                                    int puedeReptilActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿El servicio acepta reptiles? (1 para sí, 0 para no):"));
                                    int precioPerroActualizar = 0;
                                    int precioGatoActualizar = 0;
                                    int precioAveActualizar = 0;
                                    int precioRoedorActualizar = 0;
                                    int precioReptilActualizar = 0;
                                    
                                    if (puedePerroActualizar == 1) {
                                        precioPerroActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio para perros:"));
                                    }
                                    if (puedeGatoActualizar == 1) {
                                        precioGatoActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio para gatos:"));
                                    }
                                    if (puedeAveActualizar == 1) {
                                        precioAveActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio para aves:"));
                                    }
                                    if (puedeRoedorActualizar == 1) {
                                        precioRoedorActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio para roedores:"));
                                    }
                                    if (puedeReptilActualizar == 1) {
                                        precioReptilActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio para reptiles:"));
                                    }
                                    
                                    Servicio servicioActualizado = new Servicio(codigoServicio, nombreServicioActualizar, diaServicioActualizar, horaInicioServicioActualizar, horaFinServicioActualizar, puedePerroActualizar, puedeGatoActualizar, puedeAveActualizar, puedeRoedorActualizar, puedeReptilActualizar, precioPerroActualizar, precioGatoActualizar, precioAveActualizar, precioRoedorActualizar, precioReptilActualizar);

                                    servicioControlador.updateService(servicioActualizado);
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
                                        List<Mascota> mascotas = mascotaControlador.getAllPets();

                                        String mascotasInfo = "Mascotas registradas:\n";
                                        for (Mascota mascota : mascotas) {
                                        	mascotasInfo += "ID: " + mascota.getCodMas() + "\n";
                                        	mascotasInfo += "Nombre: " + mascota.getNomMas() + "\n";
                                            mascotasInfo += "Tipo: " + mascota.getTipoMas() + "\n\n";
                                        }

                                        JOptionPane.showMessageDialog(null, mascotasInfo);
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

