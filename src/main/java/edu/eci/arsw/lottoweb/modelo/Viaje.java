package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

@ApiModel("Model Viaje")
public class Viaje implements Serializable {

    @ApiModelProperty(value = "Identificador del viaje", required = true)
    private int id;
    @ApiModelProperty(value = "Duracion del viaje", required = true)
    private int duracion;
    @ApiModelProperty(value = "Precio del viaje", required = true)
    private int precio;
    @ApiModelProperty(value = "Tipo de viaje", required = true)
    private String tipo;
    @ApiModelProperty(value = "Indicaciones del viaje", required = true)
    private String indicaciones;
    @ApiModelProperty(value = "Cliente del viaje", required = true)
    private Cliente cliente;

    public Viaje(int id, int duracion, int precio, String tipo, String indicaciones){
        this.id=id;
        this.duracion=duracion;
        this.precio=precio;
        this.tipo=tipo;
        this.indicaciones=indicaciones;
    }

    public Viaje() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public void setRuta(Ruta ruta) {
    }

    public void setEspecificaciones(String string) {
    }

    public Ruta getRuta() {
        Ruta ruta= new Ruta();
        return ruta;
    }
}