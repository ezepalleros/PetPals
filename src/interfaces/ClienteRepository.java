package interfaces;

import java.util.List;

import modelos.Cliente;

public interface ClienteRepository {
    List<Cliente> getAllClients(); // llama a todos los usuarios de la bdd
    
    Cliente getClientById(int id); //llama solo a uno, por su id
    
    void addClient(Cliente cliente); //añade usuarios a la bdd
    
    void updateClient(Cliente cliente); //actualiza los usuarios de la bdd
    
    void deleteClient(int id); //eliminar usuarios de la bdd
}