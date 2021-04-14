package edu.eci.arsw.lottoweb.servicios.impl;

import edu.eci.arsw.lottoweb.persistencia.*;
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
public class ServiceLottoWebImpl implements ServiceLottoWeb{

    @Autowired
    private ClienteDao cliente;
    @Autowired
    private VehiculoDao vehiculo;
    @Autowired
    private ConductorDao conductor;
    @Autowired
    private ViajeDao viaje;
    @Autowired
    private OfertaDao oferta;
    @Autowired
    private RutaDao ruta;
    @Autowired
    private UbicacionDao ubicacion;
    @Autowired
    private ViajeEnCursoDao viajeEnCurso;

    private ConcurrentHashMap<Integer, Oferta> ofertas =new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<Subasta>> subastas = new ConcurrentHashMap<>();

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
            return this.cliente.getCliente(correo);
        }catch (edu.eci.arsw.lottoweb.persistencia.PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta", (edu.eci.arsw.lottoweb.persistencia.PersistenceException) e);

        }
    }

    @Override
    public List<Cliente> getAllClientes() throws ExceptionServiciosLottoWeb {
        try {
            System.out.println("bien");
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
    public Vehiculo getVehiculo(int id) throws ExceptionServiciosLottoWeb {
        try {
            return this.vehiculo.getVehiculo(id);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("No se ha podido realizar la consulta", e);
        }
    }

    @Override
    public List<Vehiculo> getVehiculos() throws ExceptionServiciosLottoWeb {
        try {
            return this.vehiculo.getVehiculos();
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public List<Vehiculo> getVehiculos(String cliente) throws ExceptionServiciosLottoWeb {
        try{
            Cliente cliente1 = this.getCliente(cliente);
            return this.vehiculo.getVehiculos(cliente1.getDocumento(), cliente1.getTipoDocumento());
        }catch (PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public void saveVehiculo(Vehiculo vehiculo) throws ExceptionServiciosLottoWeb {
        try {
            int id = this.vehiculo.nextId();
            vehiculo.setId(id);
            System.out.println("hola que hace bien o no");
            this.vehiculo.save(vehiculo);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la operación",e);
        }
    }

    @Override
    public void updateVehiculo(Vehiculo vehiculo) throws ExceptionServiciosLottoWeb {
        try {
            this.vehiculo.update(vehiculo);
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("No se ha podido realizar la operación",e);
        }
    }

    @Override
    public void deleteVehiculo(int id) throws ExceptionServiciosLottoWeb {
        try {
            this.vehiculo.delete(id);
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("no se pudo eliminar el vehiculo",e);
        }
    }

    @Override
    public Conductor getConductor(String documento, String tdoc) throws ExceptionServiciosLottoWeb {
        try {
            return this.conductor.getConductor(documento, tdoc);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public Conductor getConductor(String correo) throws ExceptionServiciosLottoWeb {
        try{
            return this.conductor.getConductor(correo);
        }catch (PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public List<Conductor> getConductores() throws ExceptionServiciosLottoWeb {
        try {
            return this.conductor.getConductores();
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public List<Conductor> getConductoresOrder(String order) throws ExceptionServiciosLottoWeb {
        try{
            return this.conductor.getConductores(order);
        }catch (PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public void saveConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {
        try {
            this.conductor.save(conductor);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la operación",e);
        }
    }

    @Override
    public Viaje getViaje(int id) throws ExceptionServiciosLottoWeb {
        try {
            return this.viaje.getViaje(id);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public List<Viaje> getViajes() throws ExceptionServiciosLottoWeb {
        try {
            return this.viaje.getViajes();
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public Oferta getOferta(int id) throws ExceptionServiciosLottoWeb {
        try {
            return this.oferta.getOferta(id);
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public List<Oferta> getOfertas() throws ExceptionServiciosLottoWeb {
        try {
            return this.oferta.getOfertas();
        } catch (PersistenceException e) {
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public void saveOferta(Oferta oferta,String latitud, String longitud) throws ExceptionServiciosLottoWeb {
        try {
            oferta.getViaje().setId(this.viaje.nextId());
            oferta.getViaje().getRuta().setId(this.ruta.nextId());
            oferta.getViaje().getRuta().setPuntoPartida("lat: "+latitud+", lng: "+longitud);
            oferta.getViaje().getRuta().setPuntoLlegada("lat: "+latitud+", lng: "+longitud);
            this.ruta.saveRuta(oferta.getViaje().getRuta());
            this.viaje.save(oferta.getViaje());
            oferta.setId(this.oferta.nextId());
            this.oferta.save(oferta);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la operación",e);
        }
    }

    @Override
    public Collection<Oferta> getOfertasIniciadas() throws ExceptionServiciosLottoWeb {
        actualizar();
        Collection<Oferta> s = this.ofertas.values();
        return s;
    }

    @Override
    public void actualizar() throws ExceptionServiciosLottoWeb {
        try{
            List<Oferta> s = this.oferta.getOfertas();
            this.ofertas.clear();
            s.forEach(oferta1 -> {
                System.out.println(oferta1 + " 66666666666");
                ofertas.put(oferta1.getId(), oferta1);
            });
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addOferta(Oferta oferta){
        this.ofertas.put(oferta.getId(),oferta);
    }

    @Override
    public void cerrarOferta(int id) throws ExceptionServiciosLottoWeb {
        try {
            System.out.println("CERRANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            this.oferta.cerrarOferta(id);
            this.ofertas.remove(id);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Conductor> getConductoresEnOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        try {
            return this.ofertas.get(oferta.getId()).getConductores();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void entrarAOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb {
        try {
            AtomicBoolean flag = new AtomicBoolean(true);
            this.ofertas.get(oferta.getId()).getConductores().forEach(conductor1 -> {
                if(conductor1.getCorreo().equals(conductor.getCorreo())){
                    flag.set(false);
                }
            });
            if(flag.get()) {
                this.ofertas.get(oferta.getId())
                        .getConductores()
                        .add(conductor);
            }
            System.out.println(this.ofertas.size() + " ppppppppppppppppppp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void salirDeOferta(Conductor conductor, Oferta oferta) throws ExceptionServiciosLottoWeb {
        if(this.ofertas.size() > 0){
            for(int i =0 ; i<this.ofertas.get(oferta.getId()).getConductores().size(); i++){
                if(this.ofertas.get(oferta.getId()).getConductores().get(i).getCorreo().equals(conductor.getCorreo())){
                    System.out.println("entre para eliminar");
                    this.ofertas.get(oferta.getId()).getConductores().remove(i);
                }
            }
        }

    }

    @Override
    public void agregarSubastaOferta(Oferta oferta, Conductor conductor, int subasta) throws ExceptionServiciosLottoWeb {
        Subasta su = new Subasta();
        su.setSubasta(subasta);
        su.setConductor(conductor);
        su.setOferta(oferta);
        this.subastas.get(oferta.getId()).add(su);
    }

    @Override
    public List<Subasta> getSubastasOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        if(this.subastas.get(oferta.getId()) == null){
            System.out.println("no existe un arreglo todavia: " + this.subastas.size());
            List<Subasta> c = new ArrayList<>();
            this.subastas.put(oferta.getId(), c);
        }
        System.out.println(this.subastas.size()+" cooooooooooooooooo "+oferta.getId());
        return this.subastas.get(oferta.getId());
    }

    @Override
    public Oferta getOfertaIniciada(int oferta) throws ExceptionServiciosLottoWeb {
        return this.ofertas.get(oferta);
    }

    @Override
    public void actualizarOferta(Oferta oferta) throws ExceptionServiciosLottoWeb {
        try {
            this.ruta.updateRuta(oferta.getViaje().getRuta());
            this.viaje.update(oferta.getViaje());
            this.cliente.updateCliente(oferta.getCreador());
            this.oferta.updateOferta(oferta);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {
        try{
            this.conductor.update(conductor);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }


//    TESTS

    public void setCliente(ClienteDao cliente) {
        this.cliente = cliente;
    }

    public void setVehiculo(VehiculoDao vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setConductor(ConductorDao conductor) {
        this.conductor = conductor;
    }

    public void setViaje(ViajeDao viaje) {
        this.viaje = viaje;
    }

    public void setOferta(OfertaDao oferta) {
        this.oferta = oferta;
    }

    public void setRuta(RutaDao ruta){
        this.ruta = ruta;
    }

    public void setViajeEnCurso(ViajeEnCursoDao viajeEnCurso){
        this.viajeEnCurso = viajeEnCurso;
    }

    public void setUbicacion(UbicacionDao ubicacion){
        this.ubicacion = ubicacion;
    }

}
