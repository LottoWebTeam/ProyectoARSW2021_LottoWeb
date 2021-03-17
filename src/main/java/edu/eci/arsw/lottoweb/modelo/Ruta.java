package edu.eci.arsw.lottoweb.modelo;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Ruta
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public class Ruta {

    private int id;
    private String puntoInicial;
    private String puntoFinal;

    public Ruta(int id, String puntoInicial, String puntoFinal){
        this.id=id;
        this.puntoInicial=puntoInicial;
        this.puntoFinal=puntoFinal;
    }

}
