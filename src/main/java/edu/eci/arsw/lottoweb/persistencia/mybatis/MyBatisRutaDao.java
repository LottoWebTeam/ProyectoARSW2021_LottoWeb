package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.modelo.Ruta;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.RutaDao;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.RutaMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisRutaDao implements RutaDao {

    @Autowired
    RutaMapper rutaMapper;




    public void setRutaMapper(RutaMapper rutaMapper){
        this.rutaMapper = rutaMapper;
    }

    @Override
    public int nextId() throws PersistenceException {
        return this.rutaMapper.nextId();
    }

    @Override
    public void saveRuta(Ruta ruta) throws PersistenceException {
        this.rutaMapper.saveRuta(ruta);
    }

    @Override
    public Ruta getRuta(int id) throws PersistenceException {
        return null;
    }

    @Override
    public void updateRuta(Ruta ruta) throws PersistenceException {
        this.rutaMapper.updateRuta(ruta);
    }
}