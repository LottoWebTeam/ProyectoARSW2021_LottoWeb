package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.modelo.Conductor;
import edu.eci.arsw.lottoweb.modelo.Oferta;
import edu.eci.arsw.lottoweb.persistencia.ConductorDao;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.ConductorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class MyBatisConductorDao implements ConductorDao {

    @Autowired
    ConductorMapper conductor;

    @Override
    public Conductor getConductor(String documento, String tdoc) throws PersistenceException {
        try{
            Conductor pa = conductor.getConductor(documento,tdoc);
            return pa;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public Conductor getConductor(String correo) throws PersistenceException {
        try{
            Conductor pa = conductor.getConductorCorreo(correo);
            return pa;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Conductor> getConductores() throws PersistenceException {
        try{
            List<Conductor> ls = conductor.getConductores();
            return ls;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Conductor> getConductores(String order) throws PersistenceException {
        try{
            if(order.equals("ASC")){
                List<Conductor> ls = conductor.getConductoresOrderAsc();
                return ls;
            }else{
                List<Conductor> ls = conductor.getConductoresOrderDesc();
                return ls;
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void save(Conductor Conductor) throws PersistenceException {
        try{
            this.conductor.save(Conductor);
            System.out.println("amigos creo que ya casi");
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Conductor> getConductoresEnOferta(Oferta oferta) throws PersistenceException {
        try{
            return this.conductor.getConductoresEnOferta(oferta);
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void entrarEnOferta(Conductor Conductor, Oferta oferta) throws PersistenceException {
        try{
            this.conductor.entrarAOferta(oferta, Conductor);
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void update(Conductor Conductor) throws PersistenceException {
        try{
            this.conductor.update(Conductor);
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    //SETS AND GETTERS

    public void setConductor(ConductorMapper conductor){
        this.conductor = conductor;
    }
}