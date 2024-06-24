package interfaces;

import java.util.List;


import modelos.Solicitud;

public interface SolicitudRepository {
    List<Solicitud> getAllRequests(); // llama a todos los usuarios de la bdd
    
    Solicitud getRequestById(int id); //llama solo a uno, por su id
    
    void addRequest(Solicitud solicitud); //añade usuarios a la bdd
    
    void updateRequest(Solicitud solicitud); //actualiza los usuarios de la bdd
    
    void deleteRequest(int id); //eliminar usuarios de la bdd
}
