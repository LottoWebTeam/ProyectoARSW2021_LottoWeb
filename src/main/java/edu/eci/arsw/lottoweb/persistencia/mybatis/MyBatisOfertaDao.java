package edu.eci.arsw.lottoweb.persistencia.mybatis;


import edu.eci.arsw.lottoweb.modelo.Cliente;
import edu.eci.arsw.lottoweb.modelo.Oferta;
import edu.eci.arsw.lottoweb.modelo.Viaje;
import edu.eci.arsw.lottoweb.persistencia.OfertaDao;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.OfertaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public class MyBatisOfertaDao implements OfertaDao {

    @Autowired
    OfertaMapper oferta;

    @Override
    public Oferta getOferta(int id) throws PersistenceException {
        try{
            Oferta su = oferta.getOferta(id);
            Cliente c = oferta.getCreador(su);
            su.setCreador(c);
            return su;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public List<Oferta> getOfertas() throws PersistenceException {
        try {
            List<Oferta> ls = oferta.getOfertas();
            ls.forEach(sub -> {
                Cliente c = oferta.getCreador(sub);
                Viaje p = oferta.getViaje(sub);
                System.out.println(c.getCorreo() + " **************");
                sub.setCreador(c);
                sub.setViaje(p);
            });
            return ls;
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public Cliente getCreador(Oferta oferta) throws PersistenceException {
        return this.oferta.getCreador(oferta);
    }

    @Override
    public void save(Oferta oferta) throws PersistenceException {
        try{
            this.oferta.save(oferta);
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public int nextId() throws PersistenceException {
        return this.oferta.nextId();
    }

    @Override
    public void cerrarOferta(int id) throws PersistenceException {
        this.oferta.cerrarOferta(id);
    }

    @Override
    public void updateOferta(Oferta oferta) throws PersistenceException {
        this.oferta.updateOferta(oferta);
    }

    @Override
    public void guardar(Oferta oferta) {

    }

    //SET AND GETTERS
    public void setOferta(OfertaMapper oferta) {
        this.oferta = oferta;
    }
}
