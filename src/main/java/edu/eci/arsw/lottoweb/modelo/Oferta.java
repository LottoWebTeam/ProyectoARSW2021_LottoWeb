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

    public void modificarOferta(){}

}
