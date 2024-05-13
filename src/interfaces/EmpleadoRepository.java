package interfaces;

import java.util.List;


import modelos.Empleado;

public interface EmpleadoRepository {
    List<Empleado> getAllUsers(); // llama a todos los usuarios de la bdd
    
    Empleado getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Empleado empleado); //añade usuarios a la bdd
    
    void updateUser(Empleado empleado); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
