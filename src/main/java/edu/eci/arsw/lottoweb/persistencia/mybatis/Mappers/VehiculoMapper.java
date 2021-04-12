package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Vehiculo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface VehiculoMapper {


    @Select("SELECT * FROM vehiculo")
    List<Vehiculo> getVehiculos();

    @Select("SELECT * FROM vehiculo WHERE doccliente = #{documento} AND tipodoccliente = #{tipoDocumento}")
    List<Vehiculo> getVehiculosCliente(@Param("documento") String documento, @Param("tipoDocumento") String tipoDocumento);

    @Select("SELECT * FROM vehiculo WHERE id = #{id}")
    Vehiculo getVehiculo(@Param("id") int id);

    @Select("SELECT nextval('id_vehiculo')")
    int nextId();

    @Insert("INSERT INTO vehiculo (id,nombre,marca,tipo,modelo,placa,doccliente,tipodoccliente,docconductor,tipodocconductor,idviajeencurso) VALUES (#{vehiculo.id},#{vehiculo.nombre},#{vehiculo.marca},#{vehiculo.tipo},#{vehiculo.modelo},#{vehiculo.placa},#{vehiculo.cliente.documento},#{vehiculo.cliente.tipoDocumento},null,null,null)")
    void save(@Param("vehiculo") Vehiculo vehiculo);

    @Update("UPDATE vehiculo SET nombre = #{vehiculo.nombre}, marca = #{vehiculo.marca}, tipo = #{vehiculo.tipo}, modelo = #{vehiculo.modelo}, placa = #{vehiculo.placa}, doccliente = #{vehiculo.cliente.documento}, tipodoccliente = #{vehiculo.cliente.tipoDocumento} WHERE id = #{vehiculo.id}")
    void update(@Param("vehiculo") Vehiculo vehiculo);

    @Delete("DELETE FROM vehiculo WHERE id = #{id}")
    void delete(@Param("id") int id);
}
