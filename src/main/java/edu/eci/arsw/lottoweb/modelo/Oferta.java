package edu.eci.arsw.lottoweb.modelo;

import java.util.ArrayList;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Oferta
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Oferta {

    private int id;
    private int precio;
    private ArrayList<Conductor> conductores;
    private Viaje viaje;

    public Oferta(int id, int precio,Viaje viaje){
        this.id=id;
        this.precio=precio;
        this.viaje=viaje;
        conductores= new ArrayList<>();
    }

    public int getPrecio(){
        return precio;
    }

    public void setOferta(int precio) {
        this.precio = precio;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Viaje getViaje(){
        return Viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public ArrayList<Conductor> getConductores(){
        return conductores;
    }

    public void setConductores(ArrayList<Conductor> conductores) {
        this.conductores = conductores;
    }




}
