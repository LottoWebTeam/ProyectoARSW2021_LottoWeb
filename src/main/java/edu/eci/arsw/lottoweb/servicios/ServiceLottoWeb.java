package edu.eci.arsw.lottoweb.servicios;

import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ServiceLottoWeb
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public interface ServiceLottoWeb {

    Cliente getCliente(String documento, String tdoc) throws ExceptionServiciosLottoWeb;

    Cliente getCliente(String correo) throws ExceptionServiciosLottoWeb;

    List<Cliente> getAllClientes() throws ExceptionServiciosLottoWeb;

    void saveCliente(Cliente cliente) throws ExceptionServiciosLottoWeb;

    Vehiculo getVehiculo(int id) throws ExceptionServiciosLottoWeb;
    
    List<Vehiculo> getVehiculos() throws ExceptionServiciosLottoWeb;

    List<Vehiculo> getVehiculos(String cliente) throws ExceptionServiciosLottoWeb;

    void saveVehiculo(Vehiculo vehiculo) throws ExceptionServiciosLottoWeb;

    void updateVehiculo(Vehiculo vehiculo) throws ExceptionServiciosLottoWeb;

    void deleteVehiculo(int id) throws ExceptionServiciosLottoWeb;
    
    Conductor getConductor(String documento, String tdoc) throws ExceptionServiciosLottoWeb;

    Conductor getConductor(String correo) throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductores() throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductoresOrder(String order) throws ExceptionServiciosLottoWeb;

    void saveConductor(Conductor conductor) throws ExceptionServiciosLottoWeb;

    Viaje getViaje(int id) throws ExceptionServiciosLottoWeb;

    List<Viaje> getViajes() throws ExceptionServiciosLottoWeb;

    Oferta getOferta(int id) throws ExceptionServiciosLottoWeb;

    List<Oferta> getOfertas() throws ExceptionServiciosLottoWeb;

    void saveOferta(Oferta oferta,String latitud, String longitud) throws ExceptionServiciosLottoWeb;

    Collection<Oferta> getOfertasIniciadas() throws ExceptionServiciosLottoWeb;

    void actualizar() throws ExceptionServiciosLottoWeb;

    void addOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void cerrarOferta(int id) throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductoresEnOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void entrarAOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb;

    void salirDeOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb;

    void agregarSubastaOferta(Oferta oferta, Conductor conductor, int subasta) throws ExceptionServiciosLottoWeb;

    List<Subasta> getSubastasOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;


    Oferta getOfertaIniciada(int oferta) throws ExceptionServiciosLottoWeb;

    void actualizarOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void actualizarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb;

}