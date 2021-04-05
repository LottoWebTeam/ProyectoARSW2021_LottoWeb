package edu.eci.arsw.lottoweb.servicios.impl;

import edu.eci.arsw.lottoweb.persistencia.*;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: ServiceLottoWebImpl
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@Service
@Component
@Primary
public class ServiceLottoWebImpl<ViajeEnCursoDAao> implements ServiceLottoWeb{

    @Autowired
    private ClienteDao cliente;
    @Autowired
    private ConductorDao conductor;
    @Autowired
    private OfertaDao oferta;
    @Autowired
    private RutaDao ruta;
    @Autowired
    private UbicacionDao ubicacion;
    @Autowired
    private VehiculoDao vehiculo;
    @Autowired
    private ViajeDao viaje;
    @Autowired
    private ViajeEnCursoDao viajeEnCurso;

    private ConcurrentHashMap<Integer, Subasta> subastas =new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<Oferta>> ofertas = new ConcurrentHashMap<>();

    @Override
    public Cliente getCliente(String id, String tdoc) throws ExceptionServiciosLottoWeb {
        try {
            return this.cliente.getCliente(id, tdoc);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public Cliente getCliente(String correo) throws ExceptionServiciosLottoWeb {
        try{
//            return new Cliente();
            return this.cliente.getCliente(correo);
        }catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
//            return null;
        }
    }

    @Override
    public List<Cliente> getAllClintes() throws ExceptionServiciosLottoWeb {
        try {
            return this.cliente.getClientes();
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public void saveCliente(Cliente cliente) throws ExceptionServiciosLottoWeb {
        try {
            this.cliente.save(cliente);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("No se ha podido completar la operación", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public void deleteMascota(int id) throws ExceptionServiciosLottoWeb {

    }


    @Override
    public Conductor getConductor(String documento, String tdoc) throws ExceptionServiciosLottoWeb {
        try {
            return this.conductor.getConductor(documento, tdoc);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public Conductor getConductor(String correo) throws ExceptionServiciosLottoWeb {
        try{
            return this.conductor.getConductor(correo);
        }catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public List<Conductor> getConductores() throws ExceptionServiciosLottoWeb {
        try {
            return this.conductor.getConductores();
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public List<Conductor> getConductoresOrder(String order) throws ExceptionServiciosLottoWeb {
        try{
            return this.conductor.getConductores(order);
        }catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public void saveConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {
        try {
            this.conductor.save(conductor);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la operación", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public Viaje getPaseo(int id) throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public Viaje getViaje(int id) throws ExceptionServiciosLottoWeb {
        try {
            return this.viaje.getViaje(id);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public List<Viaje> getViajes() throws ExceptionServiciosLottoWeb {
        try {
            return this.viaje.getViajes();
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public Subasta getSubasta(int id) throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public List<Subasta> getSubastas() throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public void saveSubasta(Subasta subasta, String latitud, String longitud) throws ExceptionServiciosLottoWeb {

    }

    public Oferta getOferta(int id) throws ExceptionServiciosLottoWeb, PersistenceException {
        return this.oferta.getOferta(id);
    }

    public List<Oferta> getOfertas() throws ExceptionServiciosLottoWeb, PersistenceException {
        return this.oferta.getOfertas();
    }

    @Override
    public void saveOferta(Oferta oferta, String latitud, String longitud) throws ExceptionServiciosLottoWeb {
        try {
            System.out.println(oferta.getPermitirMasVehiculos());
            oferta.getViaje().setId(this.viaje.nextId());
            oferta.getViaje().getRuta().setId(this.ruta.nextId());
            oferta.getViaje().getRuta().setPuntoInicial("lat: "+latitud+", lng: "+longitud);
            oferta.getViaje().getRuta().setPuntoInicial("lat: "+latitud+", lng: "+longitud);
            this.ruta.saveRuta(oferta.getViaje().getRuta());
            this.viaje.save(oferta.getViaje());
            oferta.setId(this.oferta.nextId());
            this.oferta.save(oferta);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la ooperación", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);
        }
    }

    @Override
    public Collection<Oferta> getOfertasIniciadas() throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public Collection<Subasta> getSubastasIniciadas() throws ExceptionServiciosLottoWeb, PersistenceException {
        actualizar();
        Collection<Subasta> s = this.subastas.values();
        return s;
    }

    @Override
    public void actualizar() throws ExceptionServiciosLottoWeb, PersistenceException {
        List<Oferta> s = this.oferta.getOfertas();
        this.ofertas.clear();
        s.forEach(oferta1 -> {
            System.out.println(oferta1 + " 66666666666");
            ofertas.put(oferta1.getId(), (List<Oferta>) oferta1);
        });
    }

    @Override
    public void addOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {

    }

    @Override
    public void cerrarOferta(int id) throws ExceptionServiciosLottoWeb {

    }

    @Override
    public List<Conductor> getConductoresEnOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public void entrarAOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb {

    }

    @Override
    public void salirDeOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb {

    }

    @Override
    public List<Oferta> getOfertasOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        return null;
    }

    @Override
    public void addSubasta(Subasta subasta){
        this.subastas.put(subasta.getId(),subasta);
    }

    @Override
    public void cerrarSubasta(int id) throws ExceptionServiciosLottoWeb {
        System.out.println("CERRANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        this.oferta.cerrarSubasta(id);
        this.subastas.remove(id);
    }

    @Override
    public List<Conductor> getConductoresEnSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb {
        try {
            return this.subastas.get(subasta.getId()).getConductores();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void entrarASubasta(Conductor conductor, Subasta subasta) throws ExceptionServiciosLottoWeb {
        try {
            AtomicBoolean flag = new AtomicBoolean(true);
            this.subastas.get(subasta.getId()).getConductores().forEach(paseador1 -> {
                if(paseador1.getCorreo().equals(conductor.getCorreo())){
                    flag.set(false);
                }
            });
            if(flag.get()) {
                this.subastas.get(subasta.getId())
                        .getConductores()
                        .add(conductor);
            }
            System.out.println(this.subastas.size() + " ppppppppppppppppppp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void salirDeSubasta(Conductor conductor, Subasta subasta) throws ExceptionServiciosLottoWeb {
        if(this.subastas.size() > 0){
            for(int i =0 ; i<this.subastas.get(subasta.getId()).getConductores().size(); i++){
                if(this.subastas.get(subasta.getId()).getConductores().get(i).getCorreo().equals(conductor.getCorreo())){
                    System.out.println("entre para eliminar");
                    this.subastas.get(subasta.getId()).getConductores().remove(i);
                }
            }
        }

    }

    @Override
    public Subasta getSubastaIniciada(int idSubasta) {
        return null;
    }

    @Override
    public void agregarOfertaSubasta(Subasta subasta, Conductor conductor, int oferta) throws ExceptionServiciosLottoWeb {
        Oferta of = new Oferta();
        of.setOferta(subasta);
        of.setConductor(conductor);
        of.setSubasta(subasta);
        this.ofertas.get(subasta.getId()).add(of);
    }

    @Override
    public List<Oferta> getOfertasSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb {
        if(this.ofertas.get(subasta.getId()) == null){
            System.out.println("no existe un arreglo todavia: " + this.ofertas.size());
            List<Oferta> c = new ArrayList<>();
            this.ofertas.put(subasta.getId(), c);
        }
        System.out.println(this.ofertas.size()+" cooooooooooooooooo "+subasta.getId());
        return this.ofertas.get(subasta.getId());
    }

    @Override
    public Oferta getOfertaIniciada(int oferta) throws ExceptionServiciosLottoWeb {
        return (Oferta) this.ofertas.get(oferta);
    }

    @Override
    public void actualizarSubasta(Subasta subasta) throws ExceptionServiciosLottoWeb {

    }

    @Override
    public void actualizarOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        try {
            this.ruta.updateRuta(oferta.getViaje().getRuta());
            this.viaje.update(oferta.getViaje());
            this.cliente.updateCliente(oferta.getCreador());
            this.oferta.updateOferta(oferta);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {
        try {
            this.conductor.update(conductor);
        } catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getAllClientes() {
        return null;
    }

    @Override
    public Object getVehiculos(String correo) {
        return null;
    }

    @Override
    public Object getVehiculos() {
        return null;
    }

    @Override
    public Object getVehiculo(int id) {
        return null;
    }

    @Override
    public void saveVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void updateVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void deleteVehiculo(int id) {

    }

    @Override
    public Object getPaseos() {
        return null;
    }

    @Override
    public Conductor getconductor(String string) {
        return null;
    }

    @Override
    public void solicitarServicio(Cliente cliente, Oferta oferta) throws ExceptionServiciosLottoWeb {
        this.oferta.guardar(oferta);
    }
}
