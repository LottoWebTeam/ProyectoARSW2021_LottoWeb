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
    private Subasta subasta;
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

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta oferta) {
        this.subasta = oferta;
    }

    public Cliente getCreador() {
        return creador;
    }

    public void setOferta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Viaje getViaje(){
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public List<Conductor> getConductores(){
        return conductores;
    }

    public void setConductores(ArrayList<Conductor> conductores) {
        this.conductores = conductores;
    }

    public void setCreador(Cliente creador) {
        this.creador = creador;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }

    public void setConductor(Conductor conductor) {
    }
}
