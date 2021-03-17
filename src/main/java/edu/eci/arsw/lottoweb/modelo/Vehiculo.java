package edu.eci.arsw.lottoweb.modelo;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Vehiculo
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

public class Vehiculo {

    private int id;
    private String marca;
    private String tipo;
    private int modelo;
    private String placa;

    public Vehiculo(int id, String marca, String tipo, int modelo, String placa){
        this.id=id;
        this.marca=marca;
        this.tipo=tipo;
        this.modelo=modelo;
        this.placa=placa;
    }
}
