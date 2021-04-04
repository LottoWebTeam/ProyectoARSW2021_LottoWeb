package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Ubicacion;

public interface UbicacionDao {

    void saveUbicacion(Ubicacion ubicacion) throws PersistenceException;

}
