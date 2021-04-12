package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
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


@ApiModel("Model Ruta")
public class Oferta implements Serializable {

    @ApiModelProperty(value = "Identificador de la subasta", required = true)
    private int id;
    @ApiModelProperty(value = "Valor que se dió como resultado de la subasta", required = true)
    private int subasta;
    @ApiModelProperty(value = "Creador de la subasta", required = true)
    private Cliente creador;
    @ApiModelProperty(value = "paseo de la subasta", required = true)
    private Viaje viaje;
    @ApiModelProperty(value = "Paseadores en la subasta", required = true)
    List<Conductor> conductores = new ArrayList<>();

    public Oferta(){

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

    public void setSubasta(int subasta) {
        this.subasta = subasta;
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

    public void setViaje(Viaje idviaje) {
        this.viaje = idviaje;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }
}
