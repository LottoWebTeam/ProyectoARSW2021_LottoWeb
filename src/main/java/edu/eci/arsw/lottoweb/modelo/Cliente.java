package edu.eci.arsw.lottoweb.modelo;

import java.util.ArrayList;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: LottoWebApp
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Cliente {

    private int documento;
    private String tipoDocumento;
    private String nombre;
    private String correo;
    private int telefono;
    private String password;
    private ArrayList<Ruta> rutas;
    private ArrayList<Oferta> ofertas;
    private ArrayList<Viaje> viajes;

    public Cliente(int documento, String tipoDocumento, String nombre, String correo, int telefono, String password){
        this.documento=documento;
        this.tipoDocumento=tipoDocumento;
        this.nombre=nombre;
        this.correo=correo;
        this.telefono=telefono;
        this.password=password;
        rutas= new ArrayList<Ruta>();
        viajes= new ArrayList<Viaje>();
        ofertas= new ArrayList<Oferta>();
    }

    public Cliente() {

    }

    public String getCorreo(){
        return correo;
    }

    public String getPassword(){
        return password;
    }

    public void calificarMundanza(){

    }

    public Cliente getCliente(String correo){
        return this;
    }

}
