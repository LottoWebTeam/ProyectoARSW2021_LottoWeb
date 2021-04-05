package edu.eci.arsw.lottoweb.modelo;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Conductor
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Conductor {

    private int documento;
    private String tipoDocumento;
    private String nombre;
    private String correo;
    private int telefono;
    private String password;
    private int calificacion;
    private Vehiculo vehiculoServicio;
    private ArrayList<Viaje> viajes;
    private ArrayList<Oferta> ofertas;

    public Conductor(){
        this.documento=documento;
        this.nombre=nombre;
        this.correo= correo;
        this.telefono= telefono;
        this.password= password;
        this.calificacion=calificacion;
        this.vehiculoServicio= vehiculoServicio;
        viajes= new ArrayList<Viaje>();
        ofertas= new ArrayList<Oferta>();
    }

    public String getCorreo(){
        return correo;
    }

    public String getPassword(){
        return password;
    }

    public Conductor getConductor(String correo){
        return this;
    }



}