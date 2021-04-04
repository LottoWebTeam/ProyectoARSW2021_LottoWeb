package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Vehiculo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface VehiculoMapper {


    @Select("SELECT * FROM Vehiculo")
    List<Vehiculo> getVehiculos();

    @Select("SELECT * FROM Vehiculo WHERE doccliente = #{documento} AND tipodoccliente = #{tipoDocumento}")
    List<Vehiculo> getVehiculosCliente(@Param("documento") String documento, @Param("tipoDocumento") String tipoDocumento);

    @Select("SELECT * FROM Vehiculo WHERE id = #{id}")
    Vehiculo getVehiculo(@Param("id") int id);

    @Select("SELECT nextval('id_Vehiculo')")
    int nextId();

    @Insert("INSERT INTO Vehiculo (id,nombre,marca,tipo,modelo,placa,doccliente,tipodoccliente,docconductor,tipodocconductor,idviajeencurso) VALUES (#{Vehiculo.id},#{Vehiculo.nombre},#{Vehiculo.marca},#{Vehiculo.tipo},#{Vehiculo.modelo},#{Vehiculo.placa},#{Vehiculo.cliente.documento},#{Vehiculo.cliente.tipoDocumento},null,null,null)")
    void save(@Param("Vehiculo") Vehiculo vehiculo);

    @Update("UPDATE Vehiculo SET nombre = #{Vehiculo.nombre}, marca = #{Vehiculo.marca}, tipo = #{Vehiculo.tipo}, modelo = #{Vehiculo.modelo}, placa = #{Vehiculo.placa}, doccliente = #{Vehiculo.cliente.documento}, tipodoccliente = #{Vehiculo.cliente.tipoDocumento} WHERE id = #{Vehiculo.id}")
    void update(@Param("Vehiculo") Vehiculo vehiculo);

    @Delete("DELETE FROM Vehiculo WHERE id = #{id}")
    void delete(@Param("id") int id);
}
