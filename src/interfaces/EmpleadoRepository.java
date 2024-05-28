package interfaces;

import java.util.List;


import modelos.Empleado;

public interface EmpleadoRepository {
    List<Empleado> getAllEmployees(); // llama a todos los usuarios de la bdd
    
    Empleado getEmployeeById(int id); //llama solo a uno, por su id
    
    void addEmployee(Empleado empleado); //añade usuarios a la bdd
    
    void updateEmployee(Empleado empleado); //actualiza los usuarios de la bdd
    
    void deleteEmployee(int id); //eliminar usuarios de la bdd
}
