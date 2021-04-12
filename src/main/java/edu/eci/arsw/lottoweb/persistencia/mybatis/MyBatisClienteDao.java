package edu.eci.arsw.lottoweb.persistencia.mybatis;


import edu.eci.arsw.lottoweb.modelo.Cliente;
import edu.eci.arsw.lottoweb.modelo.Conductor;
import edu.eci.arsw.lottoweb.persistencia.ClienteDao;
import edu.eci.arsw.lottoweb.persistencia.PersistenceException;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class MyBatisClienteDao implements ClienteDao {

    @Autowired
    private ClienteMapper cliente;


    @Override
    public List<Cliente> getClientes() throws PersistenceException {
        try{
            List<Cliente> li = cliente.getClientes();
            System.out.println(li);
            return li;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }

    }

    @Override
    public Cliente getCliente(String documento, String tdoc) throws PersistenceException{
        try {
            Cliente cli = cliente.getCliente(documento, tdoc);
            return cli;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }

    }

    @Override
    public Cliente getCliente(String correo) throws PersistenceException {
        try{
            Cliente cli = this.cliente.getClienteCorreo(correo);
            return cli;
        }catch (Exception e){
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void save(Cliente cliente) throws PersistenceException{
        try{

            this.cliente.save(cliente);
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ERROR_EN_LA_SOLICITUD);
        }
    }

    @Override
    public void updateCliente(Cliente cliente) throws PersistenceException {
        this.cliente.updateCliente(cliente);
    }

    @Override
    public void updateCliente(Conductor creador) {

    }


    //SET AND GETTERS
    public void setCliente(ClienteMapper cliente){
        this.cliente = cliente;
    }
}
