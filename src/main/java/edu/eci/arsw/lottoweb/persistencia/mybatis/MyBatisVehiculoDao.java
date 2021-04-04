package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.modelo.Vehiculo;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.VehiculoDao;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.VehiculoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class MyBatisVehiculoDao implements VehiculoDao {


    @Autowired
    VehiculoMapper vehiculo;

    @Override
    public Vehiculo getVehiculo(int id) throws PersistenceException {
        try {
            System.out.println(id);
            Vehiculo ma = vehiculo.getVehiculo(id);
            return ma;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }

    }

    @Override
    public List<Vehiculo> getVehiculos() throws PersistenceException {
        try {
            List<Vehiculo> ls = vehiculo.getVehiculos();
            return ls;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Vehiculo> getVehiculos(String documento, String tipoDocumento) throws PersistenceException {
        try {
            System.out.println(documento + " " + tipoDocumento);
            List<Vehiculo> ls = vehiculo.getVehiculosCliente(documento, tipoDocumento);
            return ls;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void save(Vehiculo vehiculo) throws PersistenceException {
        try {
            this.vehiculo.save(vehiculo);
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }

    }

    @Override
    public void update(Vehiculo vehiculo) throws PersistenceException {
        try {
            this.vehiculo.update(vehiculo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void delete(int id) throws PersistenceException {
        try {
            this.vehiculo.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException((PersistenceException.ERROR_EN_LA_SOLICITUD));
        }
    }

    @Override
    public int nextId() throws PersistenceException {
        return this.vehiculo.nextId();
    }

    //SET AND GETTERS

    public void setVehiculo(VehiculoMapper vehiculo) {
        this.vehiculo = vehiculo;

    }
}