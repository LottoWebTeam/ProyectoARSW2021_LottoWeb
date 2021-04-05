package edu.eci.arsw.lottoweb.persistencia;

import edu.eci.arsw.lottoweb.modelo.Cliente;
import edu.eci.arsw.lottoweb.modelo.Oferta;

import java.util.List;

public interface OfertaDao {

    Oferta getOferta(int id) throws PersistenceException;

    List<Oferta> getOfertas() throws PersistenceException;

    Cliente getCreador(Oferta oferta) throws PersistenceException;

    void save(Oferta oferta) throws PersistenceException;

    int nextId() throws PersistenceException;

    void cerrarOferta(int id) throws PersistenceException;

    void updateOferta(Oferta oferta) throws PersistenceException;

    void guardar(Oferta oferta);

    void cerrarSubasta(int id);
}