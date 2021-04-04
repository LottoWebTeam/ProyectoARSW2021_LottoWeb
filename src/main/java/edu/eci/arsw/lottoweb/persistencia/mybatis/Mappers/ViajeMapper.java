package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Viaje;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ViajeMapper {
    @Select("SELECT * FROM viaje")
    List<Viaje> getViajes();

    @Select("SELECT * FROM viaje WHERE id = #{id}")
    Viaje getViaje(@Param("id") int id);

    @Select("SELECT nextval('id_viaje')")
    int nexId();

    @Insert("INSERT INTO viaje (id,duracion,precio,especificaciones,idruta) VALUES (#{viaje.id},#{viaje.duracion},#{viaje.precio},#{viaje.especificaciones},#{viaje.ruta.id})")
    void save(@Param("viaje") Viaje viaje);

    @Update("UPDATE viaje SET duracion = #{viaje.duracion}, precio = #{viaje.precio}, especificaciones = #{viaje.especificaciones} WHERE id = #{viaje.id}")
    void update(@Param("viaje") Viaje viaje);
}

