package interfaces;

import java.util.List;


import modelos.Gerente;

public interface GerenteRepository {
    List<Gerente> getAllUsers(); // llama a todos los usuarios de la bdd
    
    Gerente getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Gerente gerente); //añade usuarios a la bdd
    
    void updateUser(Gerente gerente); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
