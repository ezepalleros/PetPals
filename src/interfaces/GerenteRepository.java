package interfaces;

import java.util.List;


import modelos.Gerente;

public interface GerenteRepository {
    List<Gerente> getAllManagers(); // llama a todos los usuarios de la bdd
    
    Gerente getManagerById(int id); //llama solo a uno, por su id
    
    void addManager(Gerente gerente); //añade usuarios a la bdd
    
    void updateManager(Gerente gerente); //actualiza los usuarios de la bdd
    
    void deleteManager(int id); //eliminar usuarios de la bdd
}
