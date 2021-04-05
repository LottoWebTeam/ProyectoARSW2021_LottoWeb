package edu.eci.arsw.lottoweb.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CLASE: Subasta
 * ---------------------------------------------------------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * @author Adriana Catañeda
 * @author Guillermo Castro
 * @version 1.0
 * ---------------------------------------------------------------------------------------------------------------------------
 */

@ApiModel("Model Subasta")
public class Subasta {

    @ApiModelProperty(value = "Valor que se dió como resultado de la subasta", required = true)
    private Oferta oferta;
    @ApiModelProperty(value = "Subasta realizada por el Conductor", required = true)
    private Conductor conductor;
    @ApiModelProperty(value = "Valor que se dió como resultado de la subasta", required = true)
    private int subasta;

    public Oferta getOferta() {
        return oferta;
    }
    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Conductor getConductor() {
        return conductor;
    }
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    public int getSubasta() {
        return subasta;
    }
    public void setSubasta(int subasta) {
        this.subasta = subasta;
    }
}
