package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ViajeEnCursoMapper {


    @Select("SELECT nextval('id_viajeEnCurso')")
    int nextId();


}
