package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Ruta;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RutaMapper {

    @Select("SELECT nextval('id_ruta')")
    int nextId();

    @Insert("INSERT INTO ruta (id,puntopartida,puntollegada) VALUES (#{ruta.id}, #{ruta.puntoPartida}, #{ruta.puntoLlegada})")
    void saveRuta(@Param("ruta") Ruta ruta);

    @Update("UPDATE ruta SET puntopartida = #{ruta.puntoPartida}, puntollegada = #{ruta.puntoLlegada} WHERE id = #{ruta.id}")
    void updateRuta(@Param("ruta") Ruta ruta);
}
