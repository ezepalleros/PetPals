package vista;

import java.util.*;
import javax.swing.JOptionPane;

import modelos.*;
import controladores.*;

public class Main {
    public static void main(String[] args) {
        ClienteControlador clienteControlador = new ClienteControlador();
        boolean irse1 = false, irseCliente = false, irseEmpleado = false, irseGerente = false;

        String[] menu1 = { "Iniciar sesión", "Registrarse", "Salir" };
        do {
            int opcion1 = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menu1, menu1[0]);
            switch (opcion1) {
                case 0:
                    int codUsuActual = 0;
                    String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico:");
                    String codigoUsuario = JOptionPane.showInputDialog(null, "Ingrese su código de usuario:");

                    boolean inicioSesionExitoso = false;
                    String tipoUsuario = "";

                    List<Cliente> clientes = clienteControlador.getAllClients();
                    for (Cliente cliente : clientes) {
                        if (cliente.getMailUsu().equals(correoUsuario) && Integer.toString(cliente.getCodUsu()).equals(codigoUsuario)) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso con:\nUsuario: " + cliente.getNomUsu() + "\nCódigo: " + cliente.getCodUsu());
                            inicioSesionExitoso = true;

                            codUsuActual = cliente.getCodUsu();
                            tipoUsuario = "Cliente";
                            break;
                        }
                    }

                    if (!inicioSesionExitoso) {
                        JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
                    } else {
                        switch (tipoUsuario) {
                            case "Cliente":
                                String[] menuCliente = { "Registrar mascota", "Solicitar servicio", "Ver mis mascotas", "Adoptar", "Salir" };
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
                                            // Implementar lógica para ver mascotas del cliente
                                            break;
                                        case 3:
                                            // Implementar lógica para adoptar una mascota
                                            break;
                                        case 4:
                                            irseCliente = true;
                                            break;
                                    }
                                } while (!irseCliente);
                                break;
                            case "Empleado":
                                String[] menuEmpleado = { "Ver solicitudes", "Ver reseñas", "Salir" };
                                do {
                                    int opcionEmpleado = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menuEmpleado, menuEmpleado[0]);
                                    switch (opcionEmpleado) {
                                        case 0:
                                            // Implementar lógica para ver solicitudes
                                            break;
                                        case 1:
                                            // Implementar lógica para ver reseñas
                                            break;
                                        case 2:
                                            irseEmpleado = true;
                                            break;
                                    }
                                } while (!irseEmpleado);
                                break;
                            case "Gerente":
                                String[] menuGerente = { "Actualizar servicios", "Ver empleados", "Ver mascotas", "Salir" };
                                do {
                                    int opcionGerente = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menuGerente, menuGerente[0]);
                                    switch (opcionGerente) {
                                        case 0:
                                            // Implementar lógica para actualizar servicios
                                            break;
                                        case 1:
                                            // Implementar lógica para ver empleados
                                            break;
                                        case 2:
                                            // Implementar lógica para ver mascotas
                                            break;
                                        case 3:
                                            irseGerente = true;
                                            break;
                                    }
                                } while (!irseGerente);
                                break;
                        }
                    }
                    break;
                case 1:
                    String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:");
                    String nuevoCorreo = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico:");
                    String nuevoTelefono = JOptionPane.showInputDialog(null, "Ingrese su número de teléfono:");
                    String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese su dirección:");

                    if (nuevoCorreo == null || nuevoCorreo.isEmpty() || !nuevoCorreo.endsWith("@gmail.com")) {
                        JOptionPane.showMessageDialog(null, "Error: El correo electrónico ingresado no es válido o está vacío.");
                    } else {
                        List<Cliente> clientes1 = clienteControlador.getAllClients();
                        boolean correoRegistrado = false;
                        for (Cliente cliente : clientes1) {
                            if (cliente.getMailUsu().equals(nuevoCorreo)) {
                                correoRegistrado = true;
                                break;
                            }
                        }

                        if (correoRegistrado) {
                            JOptionPane.showMessageDialog(null, "Error: El correo electrónico ingresado ya está registrado.");
                        } else {
                            Cliente nuevoCliente = new Cliente(0, nuevoNombre, nuevoCorreo, nuevaDireccion, nuevoTelefono);
                            clienteControlador.addClient(nuevoCliente);
                            JOptionPane.showMessageDialog(null, "Registro exitoso como cliente:\nUsuario: " + nuevoCliente.getNomUsu() + "\nCódigo: " + nuevoCliente.getCodUsu());
                        }
                    }
                    break;
                case 2:
                    irse1 = true;
                    break;
            }
        } while (!irse1);
    }
}

