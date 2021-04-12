package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@ApiModel("Model Vehiculo")
public class Vehiculo implements Serializable {

    @ApiModelProperty(value = "Id del vehiculo", required = true)
    private int id;
    @ApiModelProperty(value = "Marca del vehiculo", required = true)
    private String marca;
    @ApiModelProperty(value = "Tipo de vehiculo", required = true)
    private String tipo;
    @ApiModelProperty(value = "Modelo del vehiculo", required = true)
    private int modelo;
    @ApiModelProperty(value = "Placa del vehiculo", required = true)
    private String placa;
    @ApiModelProperty(value = "Ubicacion del vehiculo", required = true)
    private Ubicacion ubicacion;

    public Vehiculo(int id, String marca, String tipo, int modelo, String placa){
        this.id=id;
        this.marca=marca;
        this.tipo=tipo;
        this.modelo=modelo;
        this.placa=placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
