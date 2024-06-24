package interfaces;

import java.util.List;


import modelos.Adoptar;

public interface AdoptarRepository {
    List<Adoptar> getAllAdoptions(); // llama a todos los usuarios de la bdd
    
    Adoptar getAdoptionById(int id); //llama solo a uno, por su id
    
    void addAdoption(Adoptar adoptar); //añade usuarios a la bdd
    
    void updateAdoption(Adoptar adoptar); //actualiza los usuarios de la bdd
    
    void deleteAdoption(int id); //eliminar usuarios de la bdd
}
