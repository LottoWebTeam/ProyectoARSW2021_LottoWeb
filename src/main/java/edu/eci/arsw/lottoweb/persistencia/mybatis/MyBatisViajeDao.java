package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.modelo.Viaje;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.ViajeDao;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.ViajeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MyBatisViajeDao implements ViajeDao {

    @Autowired
    ViajeMapper viaje;

    @Override
    public Viaje getViaje(int id) throws PersistenceException {
        try{
            Viaje pa = viaje.getViaje(id);
            return pa;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Viaje> getViajes() throws PersistenceException {
        try{
            List<Viaje> ls = viaje.getViajes();
            return ls;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void save(Viaje viaje) throws PersistenceException {
        try {
            this.viaje.save(viaje);
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public int nextId() throws PersistenceException {
        return this.viaje.nexId();
    }

    @Override
    public void update(Viaje viaje) throws PersistenceException {
        this.viaje.update(viaje);
    }

    //SET AND GETTERS
    public void setViaje(ViajeMapper viaje) {
        this.viaje = viaje;
    }
}
