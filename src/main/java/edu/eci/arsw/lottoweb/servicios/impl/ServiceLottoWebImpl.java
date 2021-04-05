package edu.eci.arsw.lottoweb.servicios.impl;

import edu.eci.arsw.lottoweb.persistencia.*;
import edu.eci.arsw.lottoweb.persistencia.mybatis.*;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.*;
import edu.eci.arsw.lottoweb.modelo.*;
import edu.eci.arsw.lottoweb.persistencia.*;
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

    private ConcurrentHashMap<Integer, List<Oferta>> ofertas = new ConcurrentHashMap<>();

    @Override
    public Cliente getCliente(String correo) throws ExceptionServiciosLottoWeb {
        try{
            return this.cliente.getCliente(correo);
        }catch (PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public void guardarCliente(Cliente cliente) throws ExceptionServiciosLottoWeb {
        try {
            this.cliente.guardar(cliente);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("No se ha podido completar la operación",e);
        }
    }

    @Override
    public Cliente getConductor(String correo) throws ExceptionServiciosLottoWeb {
        try{
            return this.conductor.getConductor(correo);
        }catch (PersistenceException e){
            throw new ExceptionServiciosLottoWeb("no se ha podido realizar la consulta",e);
        }
    }

    @Override
    public void guardarConductor(Conductor conductor) throws ExceptionServiciosLottoWeb {
        try {
            this.cliente.guardar(conductor);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("No se ha podido completar la operación",e);
        }
    }

    @Override
    public void solicitarServicio(Cliente cliente, Oferta oferta) throws ExceptionServiciosLottoWeb {
        try {
            this.oferta.guardar(oferta);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ExceptionServiciosLottoWeb("No se ha podido completar la operación",e);
        }
    }
}
