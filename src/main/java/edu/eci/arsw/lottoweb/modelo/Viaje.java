package edu.eci.arsw.lottoweb.modelo;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Viaje
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Viaje {

    private int id;
    private int duracion;
    private int precio;
    private String tipo;
    private String indicaciones;
    private Cliente cliente;

    public Viaje(int id, int duracion, int precio, String tipo, String indicaciones){
        this.id=id;
        this.duracion=duracion;
        this.precio=precio;
        this.tipo=tipo;
        this.indicaciones=indicaciones;
    }
}
