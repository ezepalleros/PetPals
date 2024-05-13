package interfaces;

import java.util.List;


import modelos.Mascota;

public interface MascotaRepository {
    List<Mascota> getAllUsers(); // llama a todos los usuarios de la bdd
    
    Mascota getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Mascota mascota); //añade usuarios a la bdd
    
    void updateUser(Mascota mascota); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
