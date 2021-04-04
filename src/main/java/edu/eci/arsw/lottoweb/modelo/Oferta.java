package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

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
    private int subasta;
    private Cliente creador;
    private Viaje viaje;
    List<Conductor> conductores = new ArrayList<>();

    public void Oferta(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubasta() {
        return subasta;
    }

    public void setSubasta(int oferta) {
        this.subasta = oferta;
    }

    public Cliente getCreador() {
        return creador;
    }

    public void setCreador(Cliente creador) {
        this.creador = creador;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje idViaje) {
        this.viaje = idViaje;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }
}
