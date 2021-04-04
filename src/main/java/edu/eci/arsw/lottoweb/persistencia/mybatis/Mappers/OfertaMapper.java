package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Cliente;
import edu.eci.arsw.lottoweb.modelo.Oferta;
import edu.eci.arsw.lottoweb.modelo.Viaje;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OfertaMapper {

    @Select("SELECT * FROM oferta WHERE terminada = false")
    List<Oferta> getOfertas();

    @Select("SELECT * FROM oferta WHERE id = #{id}")
    Oferta getOferta(@Param("id") int id);

    @Select("SELECT nextval('id_oferta')")
    int nextId();

    @Insert("INSERT INTO oferta (id,subasta,creador,idviaje) VALUES (#{oferta.id},#{oferta.subasta},#{oferta.creador.correo},#{oferta.viaje.id})")
    void save(@Param("oferta") Oferta oferta);

    @Update("UPDATE oferta SET terminada = true WHERE id = #{id}")
    void cerrarOferta(@Param("id") int id);

    @Select("SELECT * FROM cliente WHERE correo = (select creador from oferta where id = #{oferta.id})")
    Cliente getCreador(@Param("oferta") Oferta oferta);

    @Select("SELECT * FROM viaje WHERE id = (select idviaje from oferta where id = #{oferta.id})")
    Viaje getViaje(@Param("oferta") Oferta oferta);

    @Update("UPDATE oferta SET subasta = #{oferta.subasta}} WHERE id = #{oferta.id}")
    void updateOferta(@Param("oferta") Oferta oferta);
}