package edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers;

import edu.eci.arsw.lottoweb.modelo.Cliente;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClienteMapper {

    @Select("SELECT * FROM Cliente")
    List<Cliente> getClientes();

    @Select("SELECT * FROM cliente WHERE documento = #{documento} AND tipoDocumento = #{tdoc}")
    Cliente getCliente(@Param("documento") String documento, @Param("tdoc") String tdoc);

    @Select("SELECT * FROM cliente WHERE correo = #{correo}")
    Cliente getClienteCorreo(@Param("correo") String correo);

    @Insert("INSERT INTO cliente (documento , tipodocumento ,nombre ,correo ,telefono ,password ) VALUES (#{cliente.documento}, #{cliente.tipoDocumento}, #{cliente.nombre}, #{cliente.correo}, #{cliente.telefono}, #{cliente.password})")
    void save(@Param("cliente") Cliente cliente);

    @Update("UPDATE cliente SET nombre = #{cliente.nombre}, correo = #{cliente.correo}, telefono = #{cliente.telefono} WHERE documento = #{cliente.documento} AND tipodocumento = #{cliente.tipoDocumento}")
    void updateCliente(@Param("cliente") Cliente cliente);

}

