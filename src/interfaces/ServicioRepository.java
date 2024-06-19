package interfaces;

import java.util.List;

import modelos.Servicio;

public interface ServicioRepository {
    List<Servicio> getAllServices(); // llama a todos los usuarios de la bdd
    
    Servicio getServiceById(int id); //llama solo a uno, por su id
    
    void addService(Servicio user); //añade usuarios a la bdd
    
    void addService2(Servicio servicio); //añade usuarios a la bdd 2
    
    void updateService(Servicio user); //actualiza los usuarios de la bdd
    
    void deleteService(int id); //eliminar usuarios de la bdd

	
}
