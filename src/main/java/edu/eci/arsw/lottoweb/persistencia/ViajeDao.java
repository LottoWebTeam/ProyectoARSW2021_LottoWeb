package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Viaje;

import java.util.List;

public interface ViajeDao {

    Viaje getViaje(int id) throws PersistenceException;

    List<Viaje> getViajes() throws PersistenceException;

    void save(Viaje viaje) throws PersistenceException;

    int nextId() throws PersistenceException;

    void update(Viaje viaje) throws PersistenceException;
}
