package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Conductor;
import edu.eci.arsw.lottoweb.modelo.Oferta;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConductorMapper {

    @Select("SELECT * FROM conductor")
    List<Conductor> getConductores();

    @Select("SELECT * FROM conductor ORDER BY calificacion DESC")
    List<Conductor> getConductoresOrderDesc();

    @Select("SELECT * FROM conductor ORDER BY calificacion ASC")
    List<Conductor> getConductoresOrderAsc();

    @Select("SELECT * FROM conductor WHERE documento = #{documento} AND tipoDocumento = #{tDoc}")
    Conductor getConductor(@Param("documento") String documento, @Param("tDoc") String tDoc);

    @Select("SELECT * FROM conductor WHERE correo = #{correo}")
    Conductor getConductorCorreo(@Param("correo") String correo);

    @Insert("INSERT INTO conductor (documento , tipodocumento ,nombre ,correo ,telefono ,password, calificacion ) VALUES (#{Conductor.documento}, #{Conductor.tipoDocumento}, #{Conductor.nombre}, #{Conductor.correo}, #{Conductor.telefono}, #{Conductor.password}, #{Conductor.calificacion})")
    void save(@Param("conductor") Conductor conductor);

    @Select("SELECT conductor.documento,conductor.tipodocumento,conductor.nombre,conductor.correo,conductor.telefono,conductor.calificacion FROM conductor,conductor_oferta, oferta WHERE conductor.documento = conductor_oferta.docconductor AND conductor.tipodocumento = conductor_oferta.tipodocconductor AND oferta.id = conductor_oferta.idoferta AND conductor_oferta.idoferta = #{oferta.id}")
    List<Conductor> getConductoresEnOferta(@Param("oferta") Oferta oferta);

    @Insert("INSERT INTO conductor_oferta (docconductor,tipodocconductor,idoferta) VALUES (#{conductor.documento},#{conductor.tipoDocumento},#{oferta.id})")
    void entrarAOferta(@Param("oferta") Oferta oferta, @Param("conductor") Conductor conductor);

    @Update("UPDATE conductor SET numcalificaciones = #{conductor.numCalificaciones}, calificacion = #{conductor.calificacion}, documento = #{conductor.documento}, tipodocumento = #{conductor.tipoDocumento}, nombre = #{conductor.nombre}, correo = #{conductor.correo}, telefono = #{conductor.telefono} WHERE documento = #{conductor.documento} AND tipodocumento = #{conductor.tipoDocumento}")
    void update(@Param("conductor") Conductor conductor);
}

