package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Ruta;

public interface RutaDao {

    int nextId() throws PersistenceException;

    void saveRuta(Ruta ruta) throws PersistenceException;

    Ruta getRuta(int id) throws PersistenceException;

    void updateRuta(Ruta ruta) throws PersistenceException;

}
