package interfaces;

import java.util.List;


import modelos.Mascota;

public interface MascotaRepository {
    List<Mascota> getAllPets(); // llama a todos los usuarios de la bdd
    
    Mascota getPetById(int id); //llama solo a uno, por su id
    
    void addPet(Mascota mascota); //añade usuarios a la bdd
    
    void updatePet(Mascota mascota); //actualiza los usuarios de la bdd
    
    void deletePet(int id); //eliminar usuarios de la bdd
}
