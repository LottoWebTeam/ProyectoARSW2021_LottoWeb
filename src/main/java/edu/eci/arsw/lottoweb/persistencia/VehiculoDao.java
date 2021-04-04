package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Vehiculo;

import java.util.List;

public interface VehiculoDao {

    Vehiculo getVehiculo(int id) throws PersistenceException;

    List<Vehiculo> getVehiculos() throws PersistenceException;

    List<Vehiculo> getVehiculos(String documento, String tipoDocumento) throws PersistenceException;

    void save(Vehiculo vehiculo) throws PersistenceException;

    void update(Vehiculo vehiculo) throws  PersistenceException;

    void delete(int id) throws PersistenceException;

    int nextId() throws PersistenceException;
}

