package edu.eci.arsw.lottoweb.persistencia.mybatis;

import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.ViajeEnCursoDao;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.ViajeEnCursoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class MyBatisViajeEnCursoDao implements ViajeEnCursoDao {

    @Autowired
    ViajeEnCursoMapper viajeEnCursoMapper;


    @Override
    public int nextId() throws PersistenceException {
        return this.viajeEnCursoMapper.nextId();
    }


    public void setPaseoEnCursoMapper(ViajeEnCursoMapper viajeEnCursoMapper){
        this.viajeEnCursoMapper = viajeEnCursoMapper;
    }
}
