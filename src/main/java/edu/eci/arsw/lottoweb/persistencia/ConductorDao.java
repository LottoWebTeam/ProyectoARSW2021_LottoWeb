package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Conductor;
import edu.eci.arsw.lottoweb.modelo.Oferta;

import java.util.List;

public interface ConductorDao {

    Conductor getConductor(String documento, String tdoc) throws PersistenceException;

    Conductor getConductor(String correo) throws PersistenceException;

    List<Conductor> getConductores() throws PersistenceException;

    List<Conductor> getConductores(String order) throws PersistenceException;

    void save(Conductor conductor) throws PersistenceException;

    List<Conductor> getConductoresEnOferta(Oferta oferta) throws PersistenceException;

    void entrarEnOferta(Conductor conductor, Oferta oferta) throws PersistenceException;

    void update(Conductor conductor) throws PersistenceException;
}
