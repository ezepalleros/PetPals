package vista;

import java.util.*;
import javax.swing.JOptionPane;

import modelos.*;
import controladores.*;

public class Main {
	public static void main(String[] args) {
	    ClienteControlador clienteControlador = new ClienteControlador();
	    EmpleadoControlador empleadoControlador = new EmpleadoControlador();
	    GerenteControlador gerenteControlador = new GerenteControlador();
	    boolean irse1 = false;
	    
	    JOptionPane.showMessageDialog(null, clienteControlador.getAllClients());

	    String[] menu1 = { "Iniciar sesión", "Registrarse", "Salir" };
	    do {
	        int opcion1 = JOptionPane.showOptionDialog(null, args, "Elige qué quieres hacer.", 0, 0, null, menu1, menu1[0]);
	        switch (opcion1) {
	        case 0:
	            String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese su correo electrónico:");
	            String codigoUsuario = JOptionPane.showInputDialog(null, "Ingrese su código de usuario:");

	            int tipoUsuario = 0;

	            List<Cliente> clientes = clienteControlador.getAllClients();
	            List<Empleado> empleados = empleadoControlador.getAllEmployees();
	            List<Gerente> gerentes = gerenteControlador.getAllManagers();
	            
	            for (Cliente cliente : clientes) {
	                if (cliente.getMailUsu().equals(correoUsuario) && Integer.toString(cliente.getCodUsu()).equals(codigoUsuario)) {
	                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso con:\nUsuario: " + cliente.getNomUsu() + "\nCódigo: " + cliente.getCodUsu());

	                    tipoUsuario = 1;
	                    break;
	                }
	            }

	            if (tipoUsuario == 0) {
	                for (Empleado empleado : empleados) {
	                    if (empleado.getMailUsu().equals(correoUsuario) && Integer.toString(empleado.getCodUsu()).equals(codigoUsuario)) {
	                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como empleado con:\nUsuario: " + empleado.getNomUsu() + "\nCódigo: " + empleado.getCodUsu());

	                        tipoUsuario = 2;
	                        break;
	                    }
	                }
	            }

	            if (tipoUsuario == 0) {
	                for (Gerente gerente : gerentes) {
	                    if (gerente.getMailUsu().equals(correoUsuario) && Integer.toString(gerente.getCodUsu()).equals(codigoUsuario)) {
	                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como gerente con:\nUsuario: " + gerente.getNomUsu() + "\nCódigo: " + gerente.getCodUsu());

	                        tipoUsuario = 3;
	                        break;
	                    }
	                }
	            }

	            if (tipoUsuario == 0) {
	                JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
	            } else {
	                switch (tipoUsuario) {
	                    case 1: // Cliente
	                        JOptionPane.showMessageDialog(null, "Cliente");
	                        break;
	                    case 2: // Empleado
	                    	JOptionPane.showMessageDialog(null, "Empleado");
	                        break;
	                    case 3: // Gerente
	                    	JOptionPane.showMessageDialog(null, "Gerente");
	                        break;
	                }
	            }
	            break;

	            case 1:
	                // Implementar lógica para el registro de un nuevo cliente
	                break;
	            case 2:
	                irse1 = true;
	                break;
	        }
	    } while (!irse1);
	}

}

