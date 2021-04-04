package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.modelo.Ubicacion;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.UbicacionDao;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.UbicacionMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisUbicacionDao implements UbicacionDao {

    @Autowired
    UbicacionMapper ubicacionMapper;

    @Override
    public void saveUbicacion(Ubicacion ubicacion) throws PersistenceException {
    }
    public void setUbicacionMapper(UbicacionMapper ubicacionMapper){
        this.ubicacionMapper = ubicacionMapper;
    }
}