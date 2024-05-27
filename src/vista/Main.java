package vista;
import java.time.*;
import java.util.*;

import javax.swing.JOptionPane;

import modelos.Cliente;
import modelos.Empleado;
import modelos.Gerente;
import modelos.Mascota;
import modelos.Servicio;
import modelos.Usuario;

public class Main {
	public static void main(String[] args) {
		LinkedList<Usuario> cuentasCreadas = new LinkedList<Usuario>();
		LinkedList<Mascota> mascotasRegistradas = new LinkedList<Mascota>();
		LinkedList<Servicio> serviciosDados = new LinkedList<Servicio>();
		boolean irse1 = false, irseCliente = false, irseEmpleado = false, irseGerente = false;

		Cliente cliente1 = new Cliente(100001, "LucasPanca", "lucasPanga@gmail.com", "Chacabuco 2356", "1157743897");
		Cliente cliente2 = new Cliente(100002, "MariaEngona", "mariaEngona@gmail.com", "Juan B Justo 4511 c", "1128890401");

		Empleado empleado1 = new Empleado(1001, "OscarHono", "oscarHono@guarda.com", "1167891234", LocalDate.now().minusYears(10), "NO DATA", 5);
		Empleado empleado2 = new Empleado(1002, "JuanaDominguez", "juanaDominguez@guarda.com", "1159970322", LocalDate.now().minusYears(5), "NO DATA", 4);
		Empleado empleado3 = new Empleado(1003, "LorenaPassione", "lorenaPassione@guarda.com", "1184431699", LocalDate.now().minusYears(7), "NO DATA", 4);

		Gerente gerente = new Gerente(101, "GerardoCorral", "gerardoCorral@guarda2.com", "1167339888");

		cuentasCreadas.add(cliente1);
		cuentasCreadas.add(cliente2);
		cuentasCreadas.add(empleado1);
		cuentasCreadas.add(empleado2);
		cuentasCreadas.add(empleado3);
		cuentasCreadas.add(gerente);

		Mascota mascota1 = new Mascota(200001, "Miguel", "Perro", "Labrador", 5, 1, "Amistosa", 0, 1, 0);
		Mascota mascota2 = new Mascota(200002, "Canoso", "Gato", "Persia", 3, 1, "Juguetona", 1, 1, 0);
		Mascota mascota3 = new Mascota(200003, "Stitch", "Ave", "Loro", 2, 0, "Agresiva", 0, 0, 0);
		Mascota mascota4 = new Mascota(200004, "Mickey", "Roedor", "Hamster", 1, 0, "Amistosa", 1, 0, 0);
		Mascota mascota5 = new Mascota(200005, "Adan", "Reptil", "Serpiente", 4, 1, "Agresiva", 0, 0, 0);

		mascotasRegistradas.add(mascota1);
		mascotasRegistradas.add(mascota2);
		mascotasRegistradas.add(mascota3);
		mascotasRegistradas.add(mascota4);
		mascotasRegistradas.add(mascota5);

		Servicio servicio1 = new Servicio(301, "Corte de Pelo", LocalDate.now().minusDays(1), LocalDateTime.now(), LocalDateTime.now().plusHours(1),
				1, 0, 0, 0, 0, 20, 0, 0, 0, 0);
		Servicio servicio2 = new Servicio(302, "Paseo", LocalDate.now().plusDays(4), LocalDateTime.now(), LocalDateTime.now().plusHours(2), 1,
				1, 1, 1, 1, 15, 15, 15, 15, 15);
		Servicio servicio3 = new Servicio(303, "Estancia", LocalDate.now().plusDays(10), LocalDateTime.now(), LocalDateTime.now().plusHours(3), 1,
				1, 1, 1, 1, 25, 20, 20, 20, 20);

		serviciosDados.add(servicio1);
		serviciosDados.add(servicio2);
		serviciosDados.add(servicio3);

		String[] menu1 = { "Iniciar sesión", "Registrarse", "Salir" };
		do {
			int opcion1 = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menu1,
					menu1[0]);
			switch (opcion1) {
			case 0:
				int codUsuActual = 0;
				String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico:");
				String codigoUsuario = JOptionPane.showInputDialog(null, "Ingrese su código de usuario:");

				boolean inicioSesionExitoso = false;
				String tipoUsuario = "";

				for (Usuario cuenta : cuentasCreadas) {
					if (cuenta.validarCredenciales(correoUsuario, codigoUsuario, cuentasCreadas)) {
						JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso con:\nUsuario: "
								+ cuenta.getNomUsu() + "\nCódigo: " + cuenta.getCodUsu());
						inicioSesionExitoso = true;

						codUsuActual = Integer.parseInt(codigoUsuario);

						int codigo = Integer.parseInt(codigoUsuario);
						if (codigo > 100000 && codigo < 199999) {
							tipoUsuario = "Cliente";
						} else if (codigo > 1000 && codigo < 1999) {
							tipoUsuario = "Empleado";
						} else if (codigo > 100 && codigo < 199) {
							tipoUsuario = "Gerente";
						}

						break;
					}
				}

				if (!inicioSesionExitoso) {
					JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
				} else {
					switch (tipoUsuario) {
					case "Cliente":
						String[] menuCliente = { "Registrar mascota", "Solicitar servicio", "Ver mis mascotas",
								"Adoptar", "Salir" };

						do {
							int opcionCliente = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0,
									0, null, menuCliente, menuCliente[0]);
							switch (opcionCliente) {
							case 0:
								Mascota nuevaMascota = Mascota.crearNuevaMascota(codUsuActual, mascotasRegistradas);

								mascotasRegistradas.add(nuevaMascota);

								JOptionPane.showMessageDialog(null,
										"¡Felicidades! Has registrado a " + nuevaMascota.getNomMas());
								break;

							case 1:
								String[] nombresServicios = new String[serviciosDados.size()];
								for (int i = 0; i < serviciosDados.size(); i++) {
									nombresServicios[i] = serviciosDados.get(i).getNomSer();
								}

								String servicioElegido = (String) JOptionPane.showInputDialog(null,
										"Elige el servicio que deseas:", "Servicios Disponibles",
										JOptionPane.QUESTION_MESSAGE, null, nombresServicios, nombresServicios[0]);

								for (Servicio servicio : serviciosDados) {
									if (servicio.getNomSer().equals(servicioElegido)) {
										JOptionPane.showMessageDialog(null,
												"Has elegido el servicio:\n" + servicio.toString());
										break;
									}
								}
								break;

							case 2:

								break;
							case 3:

								break;
							case 4:
								irseCliente = true;
								break;
							}

						} while (irseCliente == false);
						break;
					case "Empleado":
						String[] menuEmpleado = { "Ver solicitudes", "Ver reseñas", "Salir" };

						do {
							int opcionEmpleado = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0,
									0, null, menuEmpleado, menuEmpleado[0]);
							switch (opcionEmpleado) {
							case 0:

								break;
							case 1:

								break;
							case 2:
								irseEmpleado = true;
								break;
							}

						} while (irseEmpleado == false);
						break;
					case "Gerente":
						String[] menuGerente = { "Actualizar servicios", "Ver empleados", "Ver mascotas", "Salir" };

						do {
							int opcionGerente = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0,
									0, null, menuGerente, menuGerente[0]);
							switch (opcionGerente) {
							case 0:

								break;
							case 1:
								JOptionPane.showMessageDialog(null, mascotasRegistradas);
								break;
							case 2:
								JOptionPane.showMessageDialog(null, mascotasRegistradas);
								break;
							case 3:
								irseGerente = true;
								break;
							}

						} while (irseGerente == false);
						break;
					}
				}
				break;

			case 1:
				String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:");
				String nuevoCorreo = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico:");
				String nuevoTelefono = JOptionPane.showInputDialog(null, "Ingrese su número de teléfono:");
				String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese su dirección:");

				int nuevoCodigo = 100000;
				for (Usuario cuenta : cuentasCreadas) {
					int codigoCliente = cuenta.getCodUsu();
					if (codigoCliente >= nuevoCodigo) {
						nuevoCodigo = codigoCliente + 1;
					}
				}

				if (nuevoCorreo == null || nuevoCorreo.isEmpty() || !nuevoCorreo.endsWith("@gmail.com")) {
					JOptionPane.showMessageDialog(null,
							"Error: El correo electrónico ingresado no es válido o está vacío.");
				} else {
					boolean correoRegistrado = false;
					for (Usuario cuenta : cuentasCreadas) {
						if (cuenta.getMailUsu().equals(nuevoCorreo)) {
							correoRegistrado = true;
							break;
						}
					}

					if (correoRegistrado) {
						JOptionPane.showMessageDialog(null,
								"Error: El correo electrónico ingresado ya está registrado.");
					} else {
						Cliente nuevoCliente = new Cliente(nuevoCodigo, nuevoNombre, nuevoCorreo, nuevoTelefono,
								nuevaDireccion);
						cuentasCreadas.add(nuevoCliente);

						JOptionPane.showMessageDialog(null, "Registro exitoso como cliente:\nUsuario: "
								+ nuevoCliente.getNomUsu() + "\nCódigo: " + nuevoCliente.getCodUsu());
					}
				}
				break;

			case 2:

				irse1 = true;

				break;
			}

		} while (irse1 == false);

	}
}
