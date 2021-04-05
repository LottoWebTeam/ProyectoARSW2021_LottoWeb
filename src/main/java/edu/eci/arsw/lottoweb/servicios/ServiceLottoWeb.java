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

    Cliente getCliente(String doocumento, String tdoc) throws ExceptionServiciosLottoWeb;

    Cliente getCliente(String correo) throws ExceptionServiciosLottoWeb;

    List<Cliente> getAllClintes() throws ExceptionServiciosLottoWeb;

    void saveCliente(Cliente cliente) throws ExceptionServiciosLottoWeb;

    void deleteMascota(int id) throws ExceptionServiciosLottoWeb;

    Conductor getConductor(String documento, String tdoc) throws ExceptionServiciosLottoWeb;

    Conductor getConductor(String correo) throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductores() throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductoresOrder(String order) throws ExceptionServiciosLottoWeb;

    void saveConductor(Conductor conductor) throws ExceptionServiciosLottoWeb;

    Viaje getPaseo(int id) throws ExceptionServiciosLottoWeb;

    Viaje getViaje(int id) throws ExceptionServiciosLottoWeb;

    List<Viaje> getViajes() throws ExceptionServiciosLottoWeb;

//    void savePaseo(Viaje paseo, String latitud, String longitud) throws ExceptionServiciosLottoWeb;

    Oferta getOferta(int id) throws ExceptionServiciosLottoWeb, PersistenceException;

    List<Oferta> getOfertas() throws ExceptionServiciosLottoWeb, PersistenceException;

    void saveOferta(Oferta oferta,String latitud, String longitud) throws ExceptionServiciosLottoWeb;



    Collection<Oferta> getOfertasIniciadas() throws ExceptionServiciosLottoWeb;

    Collection<Subasta> getSubastasIniciadas() throws ExceptionServiciosLottoWeb, PersistenceException;

    void actualizar() throws ExceptionServiciosLottoWeb, PersistenceException;

    void addOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void cerrarOferta(int id) throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductoresEnOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void entrarAOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb;

    void salirDeOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb;



    List<Oferta> getOfertasOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void agregarOfertaSubasta(Subasta subasta, Conductor conductor, int oferta) throws ExceptionServiciosLottoWeb;

    List<Oferta> getOfertasSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb;

    Oferta getOfertaIniciada(int oferta) throws ExceptionServiciosLottoWeb;


    void actualizarSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb;

    void actualizarOferta(Oferta oferta) throws ExceptionServiciosLottoWeb;

    void actualizarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb;

    Object getAllClientes();

    Object getVehiculos(String correo);

    Object getVehiculos();

    Object getVehiculo(int id);

    void saveVehiculo(Vehiculo vehiculo);

    void updateVehiculo(Vehiculo vehiculo);

    void deleteVehiculo(int id);

    Object getPaseos();

    Conductor getconductor(String string);

    void solicitarServicio(Cliente cliente, Oferta oferta) throws ExceptionServiciosLottoWeb;

    List<Subasta> getSubastas() throws ExceptionServiciosLottoWeb;

    void saveSubasta(Subasta subasta, String latitud, String longitud) throws ExceptionServiciosLottoWeb;

    void addSubasta(Subasta subasta);

    Subasta getSubasta(int numSubasta) throws ExceptionServiciosLottoWeb;

    void cerrarSubasta(int numSubasta) throws ExceptionServiciosLottoWeb;

    List<Conductor> getConductoresEnSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb;

    void entrarASubasta(Conductor conductor, Subasta subasta) throws ExceptionServiciosLottoWeb;

    void salirDeSubasta(Conductor conductor, Subasta subasta) throws ExceptionServiciosLottoWeb;

    Subasta getSubastaIniciada(int idSubasta);
}
