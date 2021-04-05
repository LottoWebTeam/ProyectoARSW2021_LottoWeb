package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Cliente;
import edu.eci.arsw.lottoweb.modelo.Conductor;

import java.util.List;

public interface ClienteDao {


    List<Cliente> getClientes() throws PersistenceException;

    Cliente getCliente(String documento,String tdoc) throws PersistenceException;

    Cliente getCliente(String correo) throws PersistenceException;

    void save(Cliente cliente) throws PersistenceException;

    void updateCliente(Cliente cliente) throws PersistenceException;

    void updateCliente(Conductor creador);
}
